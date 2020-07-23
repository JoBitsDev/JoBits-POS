/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.servicios.impresion.formatter;

import com.jobits.pos.logs.RestManagerHandler;
import com.jobits.pos.persistencia.Cocina;
import com.jobits.pos.persistencia.Orden;
import com.jobits.pos.persistencia.ProductovOrden;
import com.jobits.pos.persistencia.modelos.ProductovOrdenDAO;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.servicios.impresion.Ticket;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;

/**
 *
 * @author Javier
 */
public class CancelacionCocinaFormatter extends AbstractTicketFormatter {

    Orden orden;
    Cocina cocina;

    public CancelacionCocinaFormatter(Orden orden, Cocina cocina) {
        this.orden = orden;
        this.cocina = cocina;
    }

    @Override
    public Doc formatPrint(String printingOutput) {
        if (printingOutput.equals(PrintFormatter.TICKET_PRINTER)) {
            return formatCancelationKitchen();
        }
        throw new IllegalArgumentException("Bad call");
    }

    private Doc formatCancelationKitchen() {
        boolean ordenSinPlatos = true;

        Ticket t = new Ticket();

        addHeader(t);

        t.emphasized(true);
        t.setText(COCINA + cocina.getNombreCocina());
        t.emphasized(false);
        t.newLine();

        addMetaData(t, orden, new Date());

        addFocusedMessage(t, CANCELACION);

        t.alignLeft();

        for (ProductovOrden x : orden.getProductovOrdenList()) {
            log(orden, x);

            if (x.getEnviadosacocina() > x.getCantidad()
                    && x.getProductoVenta().getCocinacodCocina().equals(cocina)) {
                t.setText(x.getCantidad() - x.getEnviadosacocina() + " " + x.getProductoVenta().getNombre());
                t.newLine();
                t.alignRight();
                t.setText((x.getCantidad() - x.getEnviadosacocina()) * x.getProductoVenta().getPrecioVenta() + " " + MONEDA);
                t.newLine();
                t.alignLeft();

                ordenSinPlatos = false;
                x.setEnviadosacocina(x.getCantidad());
            }
            try {
                ProductovOrdenDAO.getInstance().edit(x);
            } catch (Exception ex) {
                Logger.getLogger(Impresion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        addFocusedMessage(t, "");

        t.feed((byte) 3);
        t.finit();

        if (!ordenSinPlatos) {

            return createDoc(t.finalCommandSet().getBytes());

        } else {
            System.out.println("No existen productos del punto de elaboracion "
                    + cocina.getNombreCocina() + " de la orden " + orden.getCodOrden() + " para cancelar");
            t.resetAll();
        }
        return createDoc(new Ticket().finalCommandSet().getBytes());
    }

    private void log(Orden o, ProductovOrden x) {
        RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.IMPRIMIENDO_PRODUCTO,
                Level.FINEST,
                o, x.getProductoVenta(),
                x.getEnviadosacocina() - x.getCantidad());
    }

    
    private Orden printCancelationKitchenForced(Orden o) {

        ArrayList<String> entrantes = new ArrayList<>();
        entrantes.add("Entrantes Calientes");
        entrantes.add("Entrantes Frios");

        ArrayList<ProductovOrden> items = new ArrayList<>(o.getProductovOrdenList());
        items.sort((ProductovOrden o1, ProductovOrden o2) -> {
            ArrayList<String> entrantes1 = new ArrayList<>();
            entrantes1.add("Entrantes Calientes");
            entrantes1.add("Entrantes Frios");
            if (entrantes1.contains(o1.getProductoVenta().getSeccionnombreSeccion().getNombreSeccion())) {
                return -1;
            }
            if (entrantes1.contains(o2.getProductoVenta().getSeccionnombreSeccion().getNombreSeccion())) {
                return 1;
            }
            if (o1.getProductoVenta().getSeccionnombreSeccion().getNombreSeccion().matches("Postres")) {
                return 1;
            }
            if (o2.getProductoVenta().getSeccionnombreSeccion().getNombreSeccion().matches("Postres")) {
                return -1;
            }
            return 0;
        });

        Ticket t = new Ticket();
        boolean ordenSinPlatos = true;

        addHeader(t);

        addMetaData(t, o, new Date());

        addFocusedMessage(t, CANCELACION);

        t.alignLeft();

        boolean entrante = false;
        boolean postre = false;

        for (ProductovOrden x : items) {
            log(o, x);

            if (x.getCantidad() < x.getEnviadosacocina()) {
                if (!entrantes.contains(x.getProductoVenta().getSeccionnombreSeccion().getNombreSeccion()) && !entrante) {
                    t.addLineSeperator();
                    t.newLine();
                    entrante = true;
                }
                if (x.getProductoVenta().getSeccionnombreSeccion().getNombreSeccion().equals("Postres") && !postre) {
                    t.addLineSeperator();
                    t.newLine();
                    postre = true;
                }
                if (x.getNota() != null) {
                    t.alignCenter();
                    t.emphasized(true);
                    t.setText(x.getNota().getDescripcion().replace('%', ' '));
                    t.newLine();
                    t.alignLeft();
                    t.setText("*NOTA* " + (x.getCantidad() - x.getEnviadosacocina()) + " " + x.getProductoVenta().getNombre());
                } else {
                    t.setText(x.getCantidad() - x.getEnviadosacocina() + " " + x.getProductoVenta().getNombre());
                }
                t.newLine();
                t.alignRight();
                t.setText((x.getCantidad() - x.getEnviadosacocina()) * x.getProductoVenta().getPrecioVenta() + " " + MONEDA);
                t.newLine();
                t.alignLeft();
                x.setEnviadosacocina(x.getCantidad());
                try {
                    ProductovOrdenDAO.getInstance().edit(x);
                } catch (Exception ex) {
                    Logger.getLogger(Impresion.class.getName()).log(Level.SEVERE, null, ex);
                }
                ordenSinPlatos = false;
                x.setEnviadosacocina(x.getCantidad());
            }
        }

        addFocusedMessage(t, "");
        t.addLineSeperator();
        t.alignCenter();
        t.newLine();
        t.feed((byte) 3);
        t.finit();

        if (!ordenSinPlatos) {
            //feedPrinter(t.finalCommandSet().getBytes(), DEFAULT_KITCHEN_PRINTER_LOCATION, TipoImpresion.COCINA);
        }

        return o;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.servicios.impresion.formatter;

import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.ProductovOrden;
import com.jobits.pos.recursos.R;
import com.jobits.pos.servicios.impresion.Ticket;
import java.util.Date;
import java.util.List;
import javax.print.Doc;

/**
 *
 * @author Javier
 */
public class PuntoElaboracionFormatter extends AbstractTicketFormatter {

    List<ProductovOrden> po;
    Cocina c;
    Date fecha;

    public PuntoElaboracionFormatter(List<ProductovOrden> po, Cocina c, Date fecha) {
        this.po = po;
        this.c = c;
        this.fecha = fecha;
    }

    @Override
    public Doc formatPrint(String printingOutput) {
        if (printingOutput.equals(PrintFormatter.TICKET_PRINTER)) {
            return formatPuntoElaoracion();
        }
        throw new IllegalArgumentException("Bad call");
    }

    private Doc formatPuntoElaoracion() {

        Ticket t = new Ticket();

        addHeader(t);

        t.addLineSeperator();
        t.newLine();
        t.setText(RESUMEN_VENTAS_COCINA);
        t.newLine();
        t.alignLeft();
        t.setText(COCINA);
        t.newLine();
        t.alignRight();
        t.setText(c.getNombreCocina());
        t.newLine();
        t.alignLeft();
        t.setText(FECHA + R.DATE_FORMAT.format(fecha));
        t.newLine();
        t.addLineSeperator();
        t.newLine();

        float total = addPvOrden(t, po);

        addTotalAndFinal(t, total);

        return createDoc(t.finalCommandSet().getBytes());
    }

}

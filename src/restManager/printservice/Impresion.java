/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.printservice;


import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

import restManager.persistencia.Carta;
import restManager.persistencia.Cocina;
import restManager.persistencia.IpvRegistro;
import restManager.persistencia.Orden;
import restManager.persistencia.Personal;
import restManager.persistencia.ProductovOrden;
import restManager.persistencia.jpa.ProductovOrdenJpaController;
import restManager.persistencia.jpa.staticContent;
import restManager.util.comun;

/**
 *
 * @author Jorge
 */
public class Impresion {

    /**
     * @param args the command line arguments
     */
    private String nombreRest = null;
    private boolean monedaCUC = false;
    private float cambio = 24;
    
    /**
     * String referentes a la impresion de ordenes
     */
    private String 
            CABECERA = "Restaurante",
            COCINA = "Cocina: ",
            DELACASA = "(Pedido por la casa)",
            ORDEN = "Orden No: ",
            MESA = "Mesa: ",
            FECHA = "Fecha: ",
            CAMARERO = "Camarero(a): ",
            SUBTOTAL = "SubTotal: ",
            TOTAL = "Total: ",
            PIE = "Vuelva Pronto",
            MONEDA = "",
            CUC = " CUC",
            MN = " MN",
            SYNC = "Sale con: ";
    
    
    /**
     * Strings referentes a la impresion de resumenes de ventas
     */
    private String 
            RESUMEN_VENTAS_CAMAREROS = "Resumen de ventas personal ",
            RESUMEN_VENTAS_COCINA = "Resumen de ventas por área ",
            TOTAL_VENTAS = "Total Vendido: ",
            RESUMEN_CONSUMO_CASA = "Resumen del consumo de la casa "
            ;        
    
    private String 
            IPV_TABLE_HEADER = "Ini. |Ent. |Disp.|Cons.|Final.",
            IPV_HEADER = "Resumen de gasto de insumos",
            IPV_PUNTO_ELAB = "Punto de elaboracion";
    
    SimpleDateFormat Format = new SimpleDateFormat("dd'/'MM'/'yy ' ' ");
    SimpleDateFormat TimaFormat = new SimpleDateFormat(" hh ':' mm ' ' a ");
    
   
 
    
    private static void feedPrinter(byte[] b) throws PrintException {
        //DocPrintJob job = PrintServiceLookup.lookupPrintServices(null, attrSet)[0].createPrintJob();       
        DocPrintJob job = PrintServiceLookup.lookupDefaultPrintService().createPrintJob();  
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(b, flavor, null);

        job.print(doc, null);
    }

    /**
     * Constructor por defecto
     * @param m una instancia de una carta especifica
     */
    public Impresion(Carta m) {
        this(m, false, 24);
        
    }

    /**
     * 
     * @param m
     * @param monedaCUC
     * @param cambio 
     */
    public Impresion(Carta m , boolean monedaCUC, float cambio) {
        nombreRest = m.getNombreCarta();
        this.cambio = cambio;
        if(this.monedaCUC = monedaCUC)
            MONEDA = CUC;
        else
            MONEDA = MN;
        
    }
    
    /**
     * 
     * @param m
     * @param footer 
     */
    public Impresion(Carta m, String footer) {
        this.nombreRest = m.getNombreCarta();
        PIE = footer;
    }

    public Impresion(Carta m, String footer, boolean monedaCUC, float cambio) {
        this.nombreRest = m.getNombreCarta();
        this.cambio = cambio;
        PIE = footer;
        if(this.monedaCUC = monedaCUC)
            MONEDA = CUC;
        else
            MONEDA = MN;
    }

    public static Impresion getDefaultInstance(){
    return new Impresion(staticContent.cartaJPA.findCarta("Mnu-1"));
    
    
    }
    
    
    public void print(Orden o) throws PrintException {

        float total = 0;
        
        Ticket p = new Ticket();
        p.resetAll();
        p.initialize();
//p.feedBack((byte)2);
        p.alignCenter();
        p.setText(CABECERA);
        p.newLine();
        p.setText(this.nombreRest);
        p.newLine();
        if(o.getDeLaCasa()){
            p.doubleStrik(true);
            p.setText(DELACASA);
            p.doubleStrik(false);
            p.newLine();
        }
        p.addLineSeperator();
        p.newLine();
        p.alignRight();
        p.setText(FECHA + this.Format.format(o.getVentafecha().getFecha()) + TimaFormat.format(o.getHoraComenzada()));
        p.newLine();
        p.setText(ORDEN + o.getCodOrden());
        p.newLine();
        p.setText(MESA + o.getMesacodMesa().getCodMesa());
        p.newLine();
        p.alignLeft();
        p.setText(CAMARERO);
        p.newLine();
        p.alignRight();
        p.setText(o.getPersonalusuario().getDatosPersonales().getNombre());
        
        p.newLine();
        p.addLineSeperator();
        p.newLine();
        
        for (ProductovOrden x : o.getProductovOrdenList()) {
            p.alignLeft();
            p.setText(x.getCantidad() + " " + x.getProductoVenta().getNombre());
            p.newLine();
            p.alignRight();
            p.setText(x.getCantidad()*x.getProductoVenta().getPrecioVenta()+ MONEDA);
            p.newLine();
            total+=x.getCantidad()*x.getProductoVenta().getPrecioVenta();
        }
        
        String totalPrint = comun.redondeoDeMonedaMN_CUC((int) (total*100));
        
        p.alignRight();
        p.newLine();
        p.setText(SUBTOTAL + totalPrint + MONEDA);
        p.newLine();
        p.addLineSeperator();
        p.setText(TOTAL + totalPrint + MONEDA);
        p.newLine();
        
        if(monedaCUC){
        p.setText(TOTAL + comun.redondeoDeMonedaMN_CUC((int) (total*cambio*100)) + MN); 
        }
        else{
        p.setText(TOTAL + comun.redondeoDeMonedaMN_CUC((int) (100*total/cambio)) + CUC);
        }
        
        p.newLine();
        p.newLine();
        
        p.alignCenter();
        p.setText(this.PIE);
        p.newLine();
        p.feed((byte)3);
        p.finit();
        
        
     
            feedPrinter(p.finalCommandSet().getBytes());
      
    }

    /**
     * imprime un ticket con los productos que van a ser enviados a to
     * @param o
     * @return
     * @throws PrintException 
     * @deprecated usar <code>printKitchen(Orden o,Cocina c,String sync)</code>
     */
    public Orden printKitchen(Orden o) throws PrintException {
        
        
        
        Ticket p = new Ticket();
        p.resetAll();
        p.initialize();
//p.feedBack((byte)2);
        p.alignCenter();
        p.setText(this.nombreRest);
        p.newLine();
        p.addLineSeperator();
        p.newLine();
        p.alignRight();
        p.setText(FECHA + this.Format.format(o.getVentafecha().getFecha()) + TimaFormat.format(new Date()));
        p.newLine();
        p.setText(ORDEN + o.getCodOrden());
        p.newLine();
        p.setText(MESA + o.getMesacodMesa().getCodMesa());
        p.newLine();
        p.addLineSeperator();
        p.newLine();
        p.alignLeft();
        
        List<Cocina> cocinasExistentesEnLaOrden = new ArrayList<>();
        for (ProductovOrden x : o.getProductovOrdenList()) {
            if(!cocinasExistentesEnLaOrden.contains(x.getProductoVenta().getCocinacodCocina()) && 
                    !x.getProductoVenta().getCocinacodCocina().getNombreCocina().equals("Barra")){
                cocinasExistentesEnLaOrden.add(x.getProductoVenta().getCocinacodCocina());
            }
        }
        if(cocinasExistentesEnLaOrden.size()>1){
           for (int i = 0; i < cocinasExistentesEnLaOrden.size(); i++) {
            String sync = SYNC;
            for (int j = 0; j < cocinasExistentesEnLaOrden.size(); j++) {
                if(i == j){
                    continue;
                }
                sync += cocinasExistentesEnLaOrden.get(j).getNombreCocina()+" ";
            }
               printKitchen(o, cocinasExistentesEnLaOrden.get(i), sync);
        } 
        }else{
            if(cocinasExistentesEnLaOrden.size() > 0){
                printKitchen(o, cocinasExistentesEnLaOrden.get(0), p.newLine());
            }
            
        }
        
   
      
    
    return o;
    }
   
    /**
     * imprime una orden por la impresora predeterminada hacia la cocina pasada por parametro
     * @param o la orden que se va a imprimir
     * @param c la cocina hacia donde se va a imprimir
     * @param sync es string de sincronizacion. ej: si los productos 
     * van a salir con los de otra cocina
     * @return la orden actualizada con los productos ya enviados a la cocina
     * @throws PrintException 
     */
    public Orden printKitchen(Orden o,Cocina c,String sync) throws PrintException {
        boolean ordenSinPlatos = true;
        
        
        Ticket p = new Ticket();
        p.resetAll();
        p.initialize();
        //p.feedBack((byte)2);
        p.alignCenter();
        p.setText(this.nombreRest);
        p.newLine();
        p.emphasized(true);
        p.setText(COCINA + c.getNombreCocina());
        p.emphasized(false);
        p.newLine();
        p.addLineSeperator();
        p.newLine();
        p.alignRight();
        p.setText(FECHA + this.Format.format(o.getVentafecha().getFecha()) 
                + TimaFormat.format(new Date()));
        p.newLine();
        p.setText(ORDEN + o.getCodOrden());
        p.newLine();
        p.setText(MESA + o.getMesacodMesa().getCodMesa());
        p.newLine();
        p.addLineSeperator();
        p.newLine();
        p.alignLeft();
        for (ProductovOrden x : o.getProductovOrdenList()) {
            if(x.getEnviadosacocina()<x.getCantidad() &&
              !x.getProductoVenta().getCocinacodCocina().getNombreCocina().equals("Barra") && 
                    x.getProductoVenta().getCocinacodCocina().equals(c)){
            p.setText(x.getCantidad()-x.getEnviadosacocina() + " " + x.getProductoVenta().getNombre());
            p.newLine();
            p.alignRight();
            p.setText((x.getCantidad()-x.getEnviadosacocina())*x.getProductoVenta().getPrecioVenta() + " " + MONEDA);
            p.newLine();
            p.alignLeft();
            x.setEnviadosacocina(x.getCantidad());
                try {
                    staticContent.productovOrdenJpa.edit(x);
                } catch (Exception ex) {
                    Logger.getLogger(Impresion.class.getName()).log(Level.SEVERE, null, ex);
                }
            ordenSinPlatos = false;
            }
        }
        
        p.addLineSeperator();
        p.alignCenter();
        p.newLine();p.emphasized(true);
        p.setText(sync);
        p.newLine();
        p.feed((byte)3);
        p.finit();
        
            if(!ordenSinPlatos){
            feedPrinter(p.finalCommandSet().getBytes());
            }
            else{
                System.out.println("No existen platos de la cocina "
                        +c.getNombreCocina()+" de la orden "+ o.getCodOrden()+" para imprimir");
                p.resetAll();
            }
    
    return o;
    }
    
    
    /** 
     * Imprime en un ticket con un resumen de las ventas que un camarero(a)
     * ha realizado en una(s) fecha determinadas.
     * Pre-Condiciones:
     * @param po lista de todas las ordenes ya resumidas en un arreglo de ProductovOrden
     * @param p el personal por el que se ya a llevar a cabo el resumen
     * @param fecha la fecha del resumen
     */
    public void printPersonalResumen(List<ProductovOrden> po, Personal p, Date fecha){
        
        
        Ticket t = new Ticket();
        t.resetAll();
        t.initialize();
//p.feedBack((byte)2);
        t.alignCenter();
        t.setText(CABECERA);
        t.newLine();
        t.setText(this.nombreRest);
        t.newLine();
        t.addLineSeperator();
        t.newLine();
        t.setText(RESUMEN_VENTAS_CAMAREROS);
        t.newLine();
        t.alignLeft();
        t.setText(CAMARERO);
        t.newLine();
        t.alignRight();
        t.setText(p.getDatosPersonales().getNombre());
        t.newLine();
        t.alignLeft();
        t.setText(FECHA + this.Format.format(fecha));
        t.newLine();
        t.addLineSeperator();
        t.newLine();
        
        float total = 0;
        for (ProductovOrden x : po) {
                t.alignLeft();
                t.setText(x.getCantidad() + " " + x.getProductoVenta().getNombre());
                t.newLine();
                t.alignRight();
                t.setText(x.getCantidad()*x.getProductoVenta().getPrecioVenta()+ MONEDA);
                t.newLine();
                total+=x.getCantidad()*x.getProductoVenta().getPrecioVenta();
            } 
        
        
        
        t.alignRight();
        t.setText(TOTAL_VENTAS + total + MONEDA);
        t.newLine();
        
        if(monedaCUC){
        t.setText(TOTAL_VENTAS + total*cambio + MN); 
        }
        else{
        t.setText(TOTAL_VENTAS + Math.rint((total/cambio)*100)/100 + CUC);
        }
        
        t.newLine();
        t.newLine();
        
        t.feed((byte)3);
        t.finit();
        
     
        try {
            feedPrinter(t.finalCommandSet().getBytes());
        } catch (PrintException ex) {
            Logger.getLogger(Impresion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
    
    /** 
     * Imprime en un ticket con un resumen de las ventas que una cocina
     * ha realizado en una(s) fecha determinadas.
     * Pre-Condiciones:
     * @param po lista de todas las ordenes ya resumidas en un arreglo de ProductovOrden
     * @param c la cocina por la que se lleva a cabo el resumen
     * @param fecha la fecha del resumen
     */
    public void printCocinaResumen(List<ProductovOrden> po, Cocina c, Date fecha){
        
        Ticket t = new Ticket();
        t.resetAll();
        t.initialize();
//p.feedBack((byte)2);
        t.alignCenter();
        t.setText(CABECERA);
        t.newLine();
        t.setText(this.nombreRest);
        t.newLine();
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
        t.setText(FECHA + this.Format.format(fecha));
        t.newLine();
        t.addLineSeperator();
        t.newLine();
        
        float total = 0;
        for (ProductovOrden x : po) {
                t.alignLeft();
                t.setText(x.getCantidad() + " " + x.getProductoVenta().getNombre());
                t.newLine();
                t.alignRight();
                t.setText(x.getCantidad()*x.getProductoVenta().getPrecioVenta()+ MONEDA);
                t.newLine();
                total+=x.getCantidad()*x.getProductoVenta().getPrecioVenta();
            } 
        
        
        t.addLineSeperator();
        t.alignRight();
        t.setText(TOTAL_VENTAS + total + MONEDA);
        t.newLine();
        
        if(monedaCUC){
        t.setText(TOTAL_VENTAS + total*cambio + MN); 
        }
        else{
        t.setText(TOTAL_VENTAS + Math.rint((total/cambio)*100)/100 + CUC);
        }
        
        t.newLine();
        t.newLine();
        
        t.feed((byte)3);
        t.finit();
        
     
        try {
            feedPrinter(t.finalCommandSet().getBytes());
        } catch (PrintException ex) {
            Logger.getLogger(Impresion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
      
    /**
     * imprime un ticket con un resumen de un IPV de un punto de elaboracion determinado
     * @param registros 
     */
    public void printResumenIPVDePuntoElaboracion(List<IpvRegistro> registros){
        //throw new UnsupportedOperationException("Operacion en desarrollo");
        Cocina c = registros.get(0).getIpv().getCocina();
        Date fecha = registros.get(0).getIpvRegistroPK().getFecha();
        Collections.sort(registros,
                (o1,o2) -> {return o1.getIpv().getInsumo().getNombre().
                         compareTo(o2.getIpv().getInsumo().getNombre());
        });
         
        Ticket t = new Ticket();
        t.resetAll();
        t.initialize();
//p.feedBack((byte)2);
        t.alignCenter();
        t.setText(CABECERA);
        t.newLine();
        t.setText(this.nombreRest);
        t.newLine();
        t.addLineSeperator();
        t.newLine();
        t.setText(IPV_HEADER);
        t.newLine();
        t.alignLeft();
        t.setText(IPV_PUNTO_ELAB);
        t.newLine();
        t.alignRight();
        t.setText(c.getNombreCocina());
        t.newLine();
        t.alignLeft();
        t.setText(FECHA + this.Format.format(fecha));
        t.newLine();
        t.addLineSeperator();
        t.alignCenter();
        t.newLine();
        t.setText(IPV_TABLE_HEADER);
        t.newLine();
        t.addLineSeperator();
        t.newLine();
       
     
        for (IpvRegistro x : registros) {
        t.setText(x.getIpv().getInsumo().getNombre());
        t.newLine();
        t.setText(createTableLineForIPVReg(x));
        t.newLine();
        t.newLine();
        } 
        
        t.newLine();
        
        t.feed((byte)3);
        t.finit();
        
     
        try {
            feedPrinter(t.finalCommandSet().getBytes());
        } catch (PrintException ex) {
            Logger.getLogger(Impresion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    //Metodos privados para el funcionameiento de la clase
    
    private String createTableLineForIPVReg(IpvRegistro x) {
        //"Ini. |Ent. |Disp.|Cons.|Final."
        
      
        
        String ret = "";
        String separador = "|";
        int LenghtPerSeparator = 5;
        int Separators = 5;
        ret += fillSpace(x.getInicio(),LenghtPerSeparator) + separador;
        ret += fillSpace(x.getEntrada(),LenghtPerSeparator)+ separador;
        ret += fillSpace(x.getDisponible(),LenghtPerSeparator) + separador;
        ret += fillSpace(x.getConsumo(),LenghtPerSeparator) + separador;
        ret += fillSpace(x.getFinal1(),LenghtPerSeparator);
        
                
        return ret;
    }
    
    private String fillSpace(Object number,int finalLenght){
        String ret =String.format(" %.0f" , number);
       // ret = ret.split(".")[0];
        boolean pivotBack = true;
        while(ret.length() < finalLenght){
                ret+= " ";
        }
        
        return ret;
    }

    public void printResumenCasa(List<ProductovOrden> resumenVentasCasa,Date fecha) {
         Ticket t = new Ticket();
        t.resetAll();
        t.initialize();
//p.feedBack((byte)2);
        t.alignCenter();
        t.setText(CABECERA);
        t.newLine();
        t.setText(this.nombreRest);
        t.newLine();
        t.addLineSeperator();
        t.newLine();
        t.setText(RESUMEN_CONSUMO_CASA);
        t.newLine();
        t.newLine();
        t.alignLeft();
        t.setText(FECHA + this.Format.format(fecha));
        t.newLine();
        t.addLineSeperator();
        t.newLine();
        
        float total = 0;
        for (ProductovOrden x : resumenVentasCasa) {
                t.alignLeft();
                t.setText(x.getCantidad() + " " + x.getProductoVenta().getNombre());
                t.newLine();
                t.alignRight();
                t.setText(x.getCantidad()*x.getProductoVenta().getPrecioVenta()+ MONEDA);
                t.newLine();
                total+=x.getCantidad()*x.getProductoVenta().getPrecioVenta();
            } 
        
        
        t.addLineSeperator();
        t.alignRight();
        t.setText(TOTAL_VENTAS + total + MONEDA);
        t.newLine();
        
        if(monedaCUC){
        t.setText(TOTAL_VENTAS + total*cambio + MN); 
        }
        else{
        t.setText(TOTAL_VENTAS + Math.rint((total/cambio)*100)/100 + CUC);
        }
        
        t.newLine();
        t.newLine();
        
        t.feed((byte)3);
        t.finit();
        
     
        try {
            feedPrinter(t.finalCommandSet().getBytes());
        } catch (PrintException ex) {
            Logger.getLogger(Impresion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
   
}
    
    


    
    


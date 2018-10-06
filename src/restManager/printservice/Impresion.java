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
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.event.PrintJobEvent;
import javax.print.event.PrintJobListener;

import javax.swing.JOptionPane;
import restManager.persistencia.Almacen;

import restManager.persistencia.Carta;
import restManager.persistencia.Cocina;
import restManager.persistencia.Control.VentaDAO;
import restManager.persistencia.Insumo;
import restManager.persistencia.IpvRegistro;
import restManager.persistencia.Orden;
import restManager.persistencia.Personal;
import restManager.persistencia.ProductovOrden;
import restManager.persistencia.Venta;
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
    private String nombreRest;
    private boolean monedaCUC;
    private float cambio = 24;
    private static EstadoImpresion estadoImpresion = EstadoImpresion.UKNOWN;
    private boolean showPrices = true;
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd'/'MM'/'yy ' ' ");
    private final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat(" hh ':' mm ' ' a ");
    private final String DEFAULT_KITCHEN_PRINTER_LOCATION = "Cocina";
    private final String DEFAULT_PRINT_LOCATION = null;
    private int cantidadCopias = 1;

    ArrayList<CopiaTicket> RAM = new ArrayList<>();

    /**
     * String referentes a la impresion de ordenes
     */
    private final String CABECERA = "Restaurante",
            COCINA = "Cocina: ",
            DELACASA = "(Pedido por la casa)",
            ORDEN = "Orden No: ",
            MESA = "Mesa: ",
            FECHA = "Fecha: ",
            CAMARERO = "Camarero(a): ",
            SUBTOTAL = "SubTotal: ",
            TOTAL = "Total: ",
            CUC = " CUC",
            MN = " MN",
            SYNC = "Sale con: ",
            PREVIEW = "(Cierre parcial de cuenta)",
            PORCIENTO = "% : ",
            Z = "Impresión de Z";

    private String PIE = "Vuelva Pronto",
            MONEDA = "";

    /**
     * Strings referentes a la impresion de resumenes de ventas
     */
    private final String 
            RESUMEN_VENTAS_CAMAREROS = "Resumen de ventas personal ",
            RESUMEN_VENTAS_COCINA = "Resumen de ventas por área ",
            TOTAL_VENTAS = "Total Vendido: ",
            RESUMEN_CONSUMO_CASA = "Resumen del consumo de la casa ";

    private final String 
            IPV_TABLE_HEADER = "Ini. |Ent. |Disp.|Cons.|Final.",
            IPV_HEADER = "Resumen de gasto de insumos",
            IPV_PUNTO_ELAB = "Punto de elaboracion";

    /**
     * String referentes al almacen
     */
    private final String 
            STOCK_BALANCE = "Balance de stock en almacen",
            STOCK_FORMAT = "En Almacen | Diferencia ";

    //
    //Constructors
    //
    public Impresion() {
        this(staticContent.cartaJPA.findCarta("Mnu-1"));
    }

    /**
     * Constructor por defecto
     *
     * @param m una instancia de una carta especifica
     */
    public Impresion(Carta m) {
        this(m, m.getMonedaPrincipal().equals("CUC"), 25);

    }

    /**
     *
     * @param m
     * @param monedaCUC
     * @param cambio
     */
    public Impresion(Carta m, boolean monedaCUC, float cambio) {
        this(m, null, monedaCUC, cambio, 1);

    }

    /**
     *
     * @param m
     * @param footer
     */
    public Impresion(Carta m, String footer) {
        this(m, m.getMonedaPrincipal().equals("CUC"), 25);
        PIE = footer;
    }

    public Impresion(Carta m, String footer, boolean monedaCUC, float cambio, int cantidadCopias) {
        this.nombreRest = m.getNombreCarta();
        this.cambio = cambio;
        this.cantidadCopias = cantidadCopias;
        if (footer != null) {
            PIE = footer;
        }
        if (this.monedaCUC = monedaCUC) {
            MONEDA = CUC;
        } else {
            MONEDA = MN;
        }
    }

    public static Impresion getDefaultInstance() {
        return new Impresion(staticContent.cartaJPA.findCarta("Mnu-1"));
    }

    //
    //Metodos Publicos
    //
    public void print(Orden o, boolean preview) throws PrintException {

        float total;

        Ticket t = new Ticket();

        addHeader(t);

        if (preview) {
            t.setText(PREVIEW);
            t.newLine();
        }
        if (o.getDeLaCasa()) {
            t.doubleStrik(true);
            t.setText(DELACASA);
            t.doubleStrik(false);
            t.newLine();
        }

        addMetaData(t, o, o.getHoraComenzada());

        total = addPvOrden(t, o.getProductovOrdenList());

        String subTotalPrint = comun.redondeoPorExceso((int) (total * 100));
        String sumaPorciento = comun.redondeoPorExceso((int) ((Float.valueOf(subTotalPrint) / 10) * 100));
        String totalPrint = subTotalPrint;
        t.alignRight();
        t.newLine();
        t.setText(SUBTOTAL + subTotalPrint + MONEDA);
        if (o.getPorciento() != 0) {
            t.newLine();
            t.setText("+ " + o.getPorciento() + PORCIENTO + sumaPorciento + MONEDA);
            totalPrint = comun.redondeoPorExceso((int) ((Float.valueOf(subTotalPrint) + Float.valueOf(sumaPorciento)) * 100));

        }
        t.newLine();

        addTotal(t, total);

        t.newLine();
        t.newLine();
        t.alignCenter();
        t.setText(this.PIE);

        addFinal(t);

        feedPrinter(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);

    }

    /**
     * imprime un ticket con los productos que van a ser enviados a to
     *
     * @param o
     * @return
     * @throws PrintException
     * @deprecated usar <code>printKitchen(Orden o,Cocina c,String sync)</code>
     */
    public Orden printKitchen(Orden o) throws PrintException {

        Ticket t = new Ticket();

        addHeader(t);

        addMetaData(t, o, new Date());

        List<Cocina> cocinasExistentesEnLaOrden = new ArrayList<>();
        for (ProductovOrden x : o.getProductovOrdenList()) {
            if (!cocinasExistentesEnLaOrden.contains(x.getProductoVenta().getCocinacodCocina())) {
                cocinasExistentesEnLaOrden.add(x.getProductoVenta().getCocinacodCocina());
            }
        }
        if (cocinasExistentesEnLaOrden.size() > 1) {
            for (int i = 0; i < cocinasExistentesEnLaOrden.size(); i++) {
                String sync = SYNC;
                for (int j = 0; j < cocinasExistentesEnLaOrden.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    sync += cocinasExistentesEnLaOrden.get(j).getNombreCocina() + " ";
                }
                printKitchen(o, cocinasExistentesEnLaOrden.get(i), sync);
            }
        } else {
            if (cocinasExistentesEnLaOrden.size() > 0) {
                printKitchen(o, cocinasExistentesEnLaOrden.get(0), "");
            }

        }

        cleanAndPrintRAM();

        return o;
    }

    /**
     * imprime una orden por la impresora predeterminada hacia la cocina pasada
     * por parametro
     *
     * @param o la orden que se va a imprimir
     * @param c la cocina hacia donde se va a imprimir
     * @param sync es string de sincronizacion. ej: si los productos van a salir
     * con los de otra cocina
     * @return la orden actualizada con los productos ya enviados a la cocina
     * @throws PrintException
     */
    public Orden printKitchen(Orden o, Cocina c, String sync) throws PrintException {
        boolean ordenSinPlatos = true;

        Ticket t = new Ticket();

        addHeader(t);

        t.emphasized(true);
        t.setText(COCINA + c.getNombreCocina());
        t.emphasized(false);
        t.newLine();

        addMetaData(t, o, new Date());

        t.alignLeft();

        for (ProductovOrden x : o.getProductovOrdenList()) {
            if (x.getEnviadosacocina() < x.getCantidad()
                    && x.getProductoVenta().getCocinacodCocina().equals(c)) {
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
                    staticContent.productovOrdenJpa.edit(x);
                } catch (Exception ex) {
                    Logger.getLogger(Impresion.class.getName()).log(Level.SEVERE, null, ex);
                }
                ordenSinPlatos = false;
            }
        }

        t.addLineSeperator();
        t.alignCenter();
        t.newLine();
        t.emphasized(true);
        t.setText(sync);
        t.newLine();
        t.feed((byte) 3);
        t.finit();

        if (!ordenSinPlatos) {
            for (int i = 0; i < cantidadCopias; i++) {
                RAM.add(new CopiaTicket(c.getNombreCocina(), t.finalCommandSet().getBytes()));
            }

            feedPrinter(t.finalCommandSet().getBytes(), c.getNombreCocina());

        } else {
            System.out.println("No existen platos de la cocina "
                    + c.getNombreCocina() + " de la orden " + o.getCodOrden() + " para imprimir");
            t.resetAll();
        }

        return o;
    }

    /**
     * Imprime en un ticket con un resumen de las ventas que un camarero(a) ha
     * realizado en una(s) fecha determinadas. Pre-Condiciones:
     *
     * @param po lista de todas las ordenes ya resumidas en un arreglo de
     * ProductovOrden
     * @param p el personal por el que se ya a llevar a cabo el resumen
     * @param fecha la fecha del resumen
     */
    public void printPersonalResumen(List<ProductovOrden> po, Personal p, Date fecha) {

        Ticket t = new Ticket();

        addHeader(t);

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
        t.setText(FECHA + this.DATE_FORMAT.format(fecha));
        t.newLine();
        t.addLineSeperator();
        t.newLine();

        float total = addPvOrden(t, po);

        addTotalAndFinal(t, total);

        try {
            feedPrinter(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);
        } catch (PrintException ex) {
            Logger.getLogger(Impresion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Imprime en un ticket con un resumen de las ventas que una cocina ha
     * realizado en una(s) fecha determinadas. Pre-Condiciones:
     *
     * @param po lista de todas las ordenes ya resumidas en un arreglo de
     * ProductovOrden
     * @param c la cocina por la que se lleva a cabo el resumen
     * @param fecha la fecha del resumen
     */
    public void printResumenPuntoElab(List<ProductovOrden> po, Cocina c, Date fecha) {

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
        t.setText(FECHA + this.DATE_FORMAT.format(fecha));
        t.newLine();
        t.addLineSeperator();
        t.newLine();

        float total = addPvOrden(t, po);

        addTotalAndFinal(t, total);

        try {
            feedPrinter(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);
        } catch (PrintException ex) {
            Logger.getLogger(Impresion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * imprime un ticket con un resumen de un IPV de un punto de elaboracion
     * determinado
     *
     * @param registros
     */
    public void printResumenIPVDePuntoElaboracion(List<IpvRegistro> registros) {
        Cocina c = registros.get(0).getIpv().getCocina();
        Date fecha = registros.get(0).getIpvRegistroPK().getFecha();
        Collections.sort(registros,
                (o1, o2) -> {
                    return o1.getIpv().getInsumo().getNombre().
                            compareTo(o2.getIpv().getInsumo().getNombre());
                });

        Ticket t = new Ticket();

        addHeader(t);

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
        t.setText(FECHA + this.DATE_FORMAT.format(fecha));
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

        addFinal(t);

        try {
            feedPrinter(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);
        } catch (PrintException ex) {
            Logger.getLogger(Impresion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * imprime por la impresora de ticket una Z del dia pasado por parametro
     *
     * @param v
     */
    public void printZ(Venta v) {

        Ticket t = new Ticket();

        addHeader(t);

        addCustomMetaData(t, Z, v.getFecha());

        float total = addPvOrden(t, VentaDAO.getResumenVentas(v));

        addTotalAndFinal(t, total);

        try {
            feedPrinter(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);
        } catch (PrintException ex) {
            Logger.getLogger(Impresion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void printResumenCasa(List<ProductovOrden> resumenVentasCasa, Date fecha) {
        Ticket t = new Ticket();
        addHeader(t);

        addCustomMetaData(t, RESUMEN_CONSUMO_CASA, fecha);

        float total = addPvOrden(t, resumenVentasCasa);

        addTotalAndFinal(t, total);

        try {
            feedPrinter(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);
        } catch (PrintException ex) {
            Logger.getLogger(Impresion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void printStockBalance(List<Insumo> items, boolean printOverStoraged) {
        Ticket t = new Ticket();
        addHeader(t);
        addCustomMetaData(t, STOCK_BALANCE, new Date());
        
        t.alignCenter();
        t.setText(STOCK_FORMAT);
        t.newLine();
        t.newLine();
        
        for (Insumo in : items) {
            t.alignLeft();
            t.setText(in.getNombre() +"("+in.getUm()+")");
            t.newLine();
            t.alignRight();
            t.setText(String.format("%.2f | %+.2f", in.getCantidadExistente(), in.getCantidadExistente()-in.getStockEstimation()));
        }
        
        addFinal(t);

    }

    //
    //Getters And Setters
    //
    public static EstadoImpresion getEstadoImpresion() {
        return estadoImpresion;
    }

    //
    // Private printing format methods
    //
    private void addTotalAndFinal(Ticket t, float total) {
        addTotal(t, total);
        addFinal(t);
    }

    private void addTotal(Ticket t, float total) {
        t.addLineSeperator();
        if (showPrices) {
            t.alignRight();
            t.setText(TOTAL_VENTAS + total + MONEDA);
            t.newLine();

            if (monedaCUC) {
                t.setText(TOTAL_VENTAS + total * cambio + MN);
            } else {
                t.setText(TOTAL_VENTAS + Math.rint((total / cambio) * 100) / 100 + CUC);
            }

        }
    }

    private void addFinal(Ticket t) {
        t.newLine();
        t.newLine();
        t.feed((byte) 3);
        t.finit();
    }

    private void addHeader(Ticket t) {
        t.resetAll();
        t.initialize();
        //p.feedBack((byte)2);
        t.alignCenter();
        t.setText(CABECERA);
        t.newLine();
        t.setText(this.nombreRest);
        t.newLine();
    }

    private void addCustomMetaData(Ticket t, String customHeader, Date fecha) {

        t.addLineSeperator();
        t.newLine();
        t.setText(customHeader);
        t.newLine();
        t.alignLeft();
        t.setText(FECHA + this.DATE_FORMAT.format(fecha));
        t.newLine();
        t.addLineSeperator();
        t.newLine();
        

    }

    private void addMetaData(Ticket t, Orden o, Date date) {
        t.addLineSeperator();
        t.newLine();
        t.alignRight();
        t.setText(FECHA + this.DATE_FORMAT.format(o.getVentafecha().getFecha()) + TIME_FORMAT.format(date));
        t.newLine();
        t.setText(ORDEN + o.getCodOrden());
        t.newLine();
        t.setText(MESA + o.getMesacodMesa().getCodMesa());
        t.newLine();
        t.alignLeft();
        t.setText(CAMARERO);
        t.newLine();
        t.alignRight();
        t.setText(o.getPersonalusuario().getDatosPersonales().getNombre());
        t.newLine();
        t.addLineSeperator();
        t.newLine();

    }

    private float addPvOrden(Ticket t, List<ProductovOrden> prods) {
        float total = 0;
        for (ProductovOrden x : prods) {
            t.alignLeft();
            t.setText(x.getCantidad() + " " + x.getProductoVenta().getNombre());
            t.newLine();
            t.alignRight();
            t.setText(comun.redondeoPorExceso((int) (x.getCantidad() * x.getProductoVenta().getPrecioVenta() * 100)) + MONEDA);
            t.newLine();
            total += x.getCantidad() * x.getProductoVenta().getPrecioVenta();
        }

        return total;
    }

    //
    //Private Methods
    //
    private void feedPrinter(byte[] b, String printerName) throws PrintException {

        PrintService[] prints = PrintServiceLookup.lookupPrintServices(null, null);
        DocPrintJob job = PrintServiceLookup.lookupDefaultPrintService().createPrintJob();
        job.addPrintJobListener(new JobListener());

        for (int i = 0; i < prints.length; i++) {
            if (prints[i].getName().equals(printerName)) {
                job = prints[i].createPrintJob();
            }
        }

        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(b, flavor, null);

        job.print(doc, null);

    }

    private boolean existPrinter(String printerName) {
        PrintService[] prints = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService print : prints) {
            if (print.getName().equals(printerName)) {
                return true;
            }
        }
        return false;
    }

    private String createTableLineForIPVReg(IpvRegistro x) {
        //"Ini. |Ent. |Disp.|Cons.|Final."

        String ret = "";
        String separador = "|";
        int LenghtPerSeparator = 5;
        int Separators = 5;
        ret += fillSpace(x.getInicio(), LenghtPerSeparator) + separador;
        ret += fillSpace(x.getEntrada(), LenghtPerSeparator) + separador;
        ret += fillSpace(x.getDisponible(), LenghtPerSeparator) + separador;
        ret += fillSpace(x.getConsumo(), LenghtPerSeparator) + separador;
        ret += fillSpace(x.getFinal1(), LenghtPerSeparator);

        return ret;
    }

    private String fillSpace(Object number, int finalLenght) {
        String ret = String.format(" %.0f", number);
        while (ret.length() < finalLenght) {
            ret += " ";
        }
        return ret;
    }

    private void cleanAndPrintRAM() {
        while (!RAM.isEmpty()) {
            try {
                feedPrinter(RAM.get(0).getImpresionData(), RAM.get(0).getNombreImpresora());
                RAM.remove(0);
            } catch (PrintException ex) {
                Logger.getLogger(Impresion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //
    //Inner Classes
    //
    private class JobListener implements PrintJobListener {

        private JOptionPane progressDialog;

        public JobListener() {
        }

        public EstadoImpresion getStatus() {
            return estadoImpresion;
        }

        @Override
        public void printDataTransferCompleted(PrintJobEvent pje) {
            estadoImpresion = EstadoImpresion.SEND;
            System.out.println(getStatus());
        }

        @Override
        public void printJobCompleted(PrintJobEvent pje) {
            estadoImpresion = EstadoImpresion.COMPLETED;
            System.out.println(getStatus());
        }

        @Override
        public void printJobFailed(PrintJobEvent pje) {
            estadoImpresion = EstadoImpresion.FAILED;
            System.out.println(getStatus());
        }

        @Override
        public void printJobCanceled(PrintJobEvent pje) {
            estadoImpresion = EstadoImpresion.CANCELED;
            System.out.println(getStatus());
        }

        @Override
        public void printJobNoMoreEvents(PrintJobEvent pje) {
            estadoImpresion = EstadoImpresion.NO_MORE_EVENTS;
            System.out.println(getStatus());
        }

        @Override
        public void printJobRequiresAttention(PrintJobEvent pje) {
            estadoImpresion = EstadoImpresion.REQUIERE_ATTENTION;
            System.out.println(getStatus());
        }
    }

    private enum EstadoImpresion {

        START("ENVIANDO"),
        SEND("ENVIADO"),
        COMPLETED("COMPLETADO"),
        FAILED("FALLIDO"),
        CANCELED("CANCELADO"),
        NO_MORE_EVENTS("NO MAS EVENTOS"),
        REQUIERE_ATTENTION("REQUIERE ATENCION"),
        UKNOWN("Desconocido");

        private final String Messagge;

        private EstadoImpresion(String Msg) {
            Messagge = Msg;
        }

        public String getMessagge() {
            return Messagge;
        }

        @Override
        public String toString() {
            return "Estado: " + Messagge + "...";
        }

    }

    private class CopiaTicket {

        private final String nombreImpresora;
        private final byte[] impresionData;

        public CopiaTicket(String nombreImpresora, byte[] impresionData) {
            this.nombreImpresora = nombreImpresora;
            this.impresionData = impresionData;
        }

        public String getNombreImpresora() {
            return nombreImpresora;
        }

        public byte[] getImpresionData() {
            return impresionData;
        }

    }
}

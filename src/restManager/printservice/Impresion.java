package restManager.printservice;

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
import restManager.exceptions.DevelopingOperationException;
import restManager.logs.RestManagerHandler;
import restManager.persistencia.Almacen;
import restManager.persistencia.AsistenciaPersonal;

import restManager.persistencia.Cocina;
import restManager.persistencia.Control.VentaDAO1;
import restManager.persistencia.GastoVenta;
import restManager.persistencia.Insumo;
import restManager.persistencia.InsumoAlmacen;
import restManager.persistencia.IpvRegistro;
import restManager.persistencia.Orden;
import restManager.persistencia.Personal;
import restManager.persistencia.ProductovOrden;
import restManager.persistencia.Transaccion;
import restManager.persistencia.Venta;
import restManager.persistencia.models.CocinaDAO;
import restManager.persistencia.models.PersonalDAO;
import restManager.persistencia.models.ProductovOrdenDAO;
import restManager.resources.R;
import restManager.util.comun;

/**
 *
 * @author Jorge
 */
public class Impresion {

    /**
     * @param args the command line arguments
     */
    private static final Logger LOGGER = Logger.getLogger(Venta.class.getSimpleName());

    private boolean monedaCUC;
    private static EstadoImpresion estadoImpresion = EstadoImpresion.UKNOWN;
    private boolean SHOW_PRICES = true;
    public static boolean PRINT_IN_CENTRAL_KITCHEN = true;
    public static boolean PRINT_GASTOS_EN_AUTORIZOS = false;
    public static boolean SHOW_HEADER = true;
    public static boolean SHOW_SUBTOTAL = true;
    public static String DEFAULT_KITCHEN_PRINTER_LOCATION = "Cocina";
    public static String DEFAULT_PRINT_LOCATION = null;
    public static boolean IMPRIMIR_TICKET_COCINA = true;
    public static int cantidadCopias = 0;
    public static boolean REDONDEO_POR_EXCESO = true;

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
            CAMARERO = "Cajero(a): ",
            SUBTOTAL = "SubTotal: ",
            TOTAL = "Total: ",
            CUC = " CUC",
            MN = " MN",
            SYNC = "Sale con: ",
            PREVIEW = "(Cierre parcial de cuenta)",
            PORCIENTO = "% : ",
            Z = "Impresión de Z",
            CANCELACION = "CANCELACION";

    private String PIE = "Vuelva Pronto",
            MONEDA = "";

    /**
     * Strings referentes a la impresion de resumenes de ventas
     */
    private final String RESUMEN_VENTAS_CAMAREROS = "Resumen de ventas personal ",
            RESUMEN_VENTAS_COCINA = "Resumen de ventas por area ",
            TOTAL_VENTAS = "Total Vendido: ",
            RESUMEN_CONSUMO_CASA = "Resumen del consumo de la casa ";

    private final String IPV_TABLE_HEADER = "Ini. |Ent. |Disp.|Cons.|Final.",
            IPV_HEADER = "Resumen de gasto de insumos",
            IPV_PUNTO_ELAB = "Punto de elaboracion";

    private final String GASTO_HEADER = "Resumen de gastos";

    private final String PAGO_POR_VENTA_HEADER = "Pago por ventas";

    private final String RESUMEN_AREA = "Resumen de area de venta";
    /**
     * String referentes al almacen
     */
    private final String STOCK_BALANCE = "Balance de stock en almacen",
            STOCK_FORMAT = "En Almacen | Diferencia ",
            COMPROBANTE_TRANSACCION = "Comprobante de Transaccion";

    /**
     * String referentes a los pagos de trabajadores
     */
    private final String PAGO_TRABAJADOR = "Comprobante de pago a trabajador";

    //
    //Constructors
    //
    public Impresion() {
        this(null);
    }

    //
    //Constructores
    //
    /**
     *
     * @param footer
     */
    public Impresion(String footer) {
        this(null, cantidadCopias);
    }

    public Impresion(String footer, int cantidadCopias) {
        Impresion.cantidadCopias = cantidadCopias;
        if (footer != null) {
            PIE = footer;
        }
        if ((monedaCUC = R.COIN_SUFFIX.toUpperCase().equals(CUC))) {
            MONEDA = CUC;
        } else {
            MONEDA = MN;
        }
    }

    public static Impresion getDefaultInstance() {
        return new Impresion();
    }

    //
    //Metodos Publicos
    //
    public void print(Orden o, boolean preview) {

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

        float subTotalPrint = comun.redondeoPorExcesoFloat(total);
        float sumaPorciento = comun.redondeoPorExcesoFloat((subTotalPrint * o.getPorciento()) / 100);
        float totalPrint = subTotalPrint;
        t.alignRight();
        t.newLine();
        if (SHOW_SUBTOTAL) {
        t.setText(SUBTOTAL + subTotalPrint + MONEDA);
        }
        if (o.getPorciento() != 0) {
            t.newLine();
            t.setText("+ " + o.getPorciento() + PORCIENTO + sumaPorciento + MONEDA);
            totalPrint = comun.redondeoPorExcesoFloat(subTotalPrint + sumaPorciento);

        }
        t.newLine();

        addTotal(t, totalPrint);

        t.newLine();
        t.newLine();
        t.alignCenter();
        t.setText(this.PIE);

        addFinal(t);

        for (int i = 0; i < cantidadCopias; i++) {
            RAM.add(new CopiaTicket(o.getMesacodMesa().getAreacodArea().getNombre(), t.finalCommandSet().getBytes()));
        }
        feedPrinter(t.finalCommandSet().getBytes(), o.getMesacodMesa().getAreacodArea().getNombre(), TipoImpresion.ORDEN);

        cleanAndPrintRAM();
    }

    /**
     * imprime un ticket con los productos que van a ser enviados a to
     *
     * @param o
     * @return
     * @deprecated usar <code>printKitchen(Orden o,Cocina c,String sync)</code>
     */
    public Orden printKitchen(Orden o) {
        if (PRINT_IN_CENTRAL_KITCHEN) {
            return printKitchenForced(printKitchen(printCancelationTicket(o), CocinaDAO.getInstance().find("C-2"), ""));
        } else {
            printCancelationTicket(o);
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
    }

    public Orden printCancelationTicket(Orden o) {
        if (PRINT_IN_CENTRAL_KITCHEN) {
            return printCancelationKitchenForced(printCancelationKitchen(o, CocinaDAO.getInstance().find("C-2")));
        } else {
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
                    printCancelationKitchen(o, cocinasExistentesEnLaOrden.get(i));
                }
            } else {
                if (cocinasExistentesEnLaOrden.size() > 0) {
                    printCancelationKitchen(o, cocinasExistentesEnLaOrden.get(0));
                }

            }

            cleanAndPrintRAM();

            return o;
        }
//    }
//
//    public Orden printKitchenForced(Orden o) throws PrintException {
//
//        Ticket t = new Ticket();
//        boolean ordenSinPlatos = true;
//
//        addHeader(t);
//
//        addMetaData(t, o, new Date());
//
//        ArrayList<String> entrantes = new ArrayList<>();
//        entrantes.add("Entrantes Calientes");
//        entrantes.add("Entrantes Frios");
//
//        ArrayList<ProductovOrden> items = new ArrayList<>(o.getProductovOrdenList());
//        items.sort((ProductovOrden o1, ProductovOrden o2) -> {
//            ArrayList<String> entrantes1 = new ArrayList<>();
//            entrantes1.add("Entrantes Calientes");
//            entrantes1.add("Entrantes Frios");
//            if (entrantes1.contains(o1.getProductoVenta().getSeccionnombreSeccion().getNombreSeccion())) {
//                return -1;
//            }
//            if (entrantes1.contains(o2.getProductoVenta().getSeccionnombreSeccion().getNombreSeccion())) {
//                return 1;
//            }
//            if (o1.getProductoVenta().getSeccionnombreSeccion().getNombreSeccion().matches("Postres")) {
//                return 1;
//            }
//            if (o2.getProductoVenta().getSeccionnombreSeccion().getNombreSeccion().matches("Postres")) {
//                return -1;
//            }
//            return 0;
//        });
//
//        t.alignLeft();
//
//        boolean entrante = false;
//        boolean postre = false;
//
//        for (ProductovOrden x : items) {
//            if (x.getEnviadosacocina() < x.getCantidad()) {
//                if (!entrantes.contains(x.getProductoVenta().getSeccionnombreSeccion().getNombreSeccion()) && !entrante) {
//                    t.addLineSeperator();
//                    t.newLine();
//                    entrante = true;
//                }
//                if (x.getProductoVenta().getSeccionnombreSeccion().getNombreSeccion().equals("Postres") && !postre) {
//                    t.addLineSeperator();
//                    t.newLine();
//                    postre = true;
//                }
//                if (x.getNota() != null) {
//                    t.alignCenter();
//                    t.emphasized(true);
//                    t.setText(x.getNota().getDescripcion().replace('%', ' '));
//                    t.newLine();
//                    t.alignLeft();
//                    t.setText("*NOTA* " + (x.getCantidad() - x.getEnviadosacocina()) + " " + x.getProductoVenta().getNombre());
//                } else {
//                    t.setText(x.getCantidad() - x.getEnviadosacocina() + " " + x.getProductoVenta().getNombre());
//                }
//                t.newLine();
//                t.alignRight();
//                t.setText((x.getCantidad() - x.getEnviadosacocina()) * x.getProductoVenta().getPrecioVenta() + " " + MONEDA);
//                t.newLine();
//                t.alignLeft();
//                x.setEnviadosacocina(x.getCantidad());
//                try {
//                    staticContent.productovOrdenJpa.edit(x);
//                } catch (Exception ex) {
//                    Logger.getLogger(Impresion.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                ordenSinPlatos = false;
//            }
//        }
//
//        t.addLineSeperator();
//        t.alignCenter();
//        t.newLine();
//        t.feed((byte) 3);
//        t.finit();
//
//        if (!ordenSinPlatos) {
//            feedPrinter(t.finalCommandSet().getBytes(), DEFAULT_KITCHEN_PRINTER_LOCATION);
//        }
//        cleanAndPrintRAM();
//
//        return o;
    }

    public Orden printCancelationKitchen(Orden o, Cocina c) {
        boolean ordenSinPlatos = true;

        Ticket t = new Ticket();

        addHeader(t);

        t.emphasized(true);
        t.setText(COCINA + c.getNombreCocina());
        t.emphasized(false);
        t.newLine();

        addMetaData(t, o, new Date());

        addFocusedMessage(t, CANCELACION);

        t.alignLeft();

        for (ProductovOrden x : o.getProductovOrdenList()) {
            log(o, x);

            if (x.getEnviadosacocina() > x.getCantidad()
                    && x.getProductoVenta().getCocinacodCocina().equals(c)) {
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
            for (int i = 0; i < cantidadCopias; i++) {
                //RAM.add(new CopiaTicket(c.getNombreCocina(), t.finalCommandSet().getBytes()));
            }

            feedPrinter(t.finalCommandSet().getBytes(), c.getNombreCocina(), TipoImpresion.COCINA);

        } else {
            System.out.println("No existen platos de la cocina "
                    + c.getNombreCocina() + " de la orden " + o.getCodOrden() + " para cancelar");
            t.resetAll();
        }

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
     */
    public Orden printKitchen(Orden o, Cocina c, String sync) {
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
            log(o, x);

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

                ordenSinPlatos = false;
                x.setEnviadosacocina(x.getCantidad());
            }
            try {

                ProductovOrdenDAO.getInstance().edit(x);
            } catch (Exception ex) {
                Logger.getLogger(Impresion.class.getName()).log(Level.SEVERE, null, ex);
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
                // RAM.add(new CopiaTicket(c.getNombreCocina(), t.finalCommandSet().getBytes()));
            }

            feedPrinter(t.finalCommandSet().getBytes(), c.getNombreCocina(), TipoImpresion.COCINA);

        } else {
            System.out.println("No existen platos de la cocina "
                    + c.getNombreCocina() + " de la orden " + o.getCodOrden() + " para imprimir");
            t.resetAll();
        }

        return o;
    }

    public Orden printKitchenForced(Orden o) {
        Ticket t = new Ticket();
        boolean ordenSinPlatos = true;

        addHeader(t);

        addMetaData(t, o, new Date());

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

        t.alignLeft();

        boolean entrante = false;
        boolean postre = false;

        for (ProductovOrden x : items) {
            log(o, x);

            if (x.getCantidad() > x.getEnviadosacocina()) {
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

        t.addLineSeperator();
        t.alignCenter();
        t.newLine();
        t.feed((byte) 3);
        t.finit();

        if (!ordenSinPlatos) {
            feedPrinter(t.finalCommandSet().getBytes(), DEFAULT_KITCHEN_PRINTER_LOCATION, TipoImpresion.COCINA);
        }
        cleanAndPrintRAM();

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
        t.setText(FECHA + R.DATE_FORMAT.format(fecha));
        t.newLine();
        t.addLineSeperator();
        t.newLine();

        float total = addPvOrden(t, po);

        addTotalAndFinal(t, total);

        sendToPrinterStatistics(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);

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
        t.setText(FECHA + R.DATE_FORMAT.format(fecha));
        t.newLine();
        t.addLineSeperator();
        t.newLine();

        float total = addPvOrden(t, po);

        addTotalAndFinal(t, total);

        sendToPrinterStatistics(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);

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
        t.setText(FECHA + R.DATE_FORMAT.format(fecha));
        t.newLine();
        t.addLineSeperator();
        t.alignCenter();
        t.newLine();
        t.setText(IPV_TABLE_HEADER);
        t.newLine();
        t.addLineSeperator();
        t.newLine();

        for (IpvRegistro x : registros) {
            t.setText(x.getIpv().getInsumo().toString());
            t.newLine();
            t.setText(createTableLineForIPVReg(x));
            t.newLine();
            t.newLine();
        }

        addFinal(t);

        sendToPrinterStatistics(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);

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

        float total = addPvOrden(t, VentaDAO1.getResumenVentas(v));

        addTotalAndFinal(t, total);

        sendToPrinterStatistics(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);

    }

    public void printResumenCasa(List<ProductovOrden> resumenVentasCasa, Date fecha) {
        Ticket t = new Ticket();
        addHeader(t);

        addCustomMetaData(t, RESUMEN_CONSUMO_CASA, fecha);

        float total = addPvOrden(t, resumenVentasCasa);

        addTotalAndFinal(t, total);

        sendToPrinterStatistics(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);
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
            t.setText(in.getNombre() + "(" + in.getUm() + ")");
            t.newLine();
            t.alignRight();
            t.newLine();
        }

        t.newLine();
        t.addLineSeperator();

        addFinal(t);

        sendToPrinterStatistics(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);

    }

    //
    //Getters And Setters
    //
    public static EstadoImpresion getEstadoImpresion() {
        return estadoImpresion;
    }

    public boolean SHOW_PRICES() {
        return SHOW_PRICES;
    }

    public void setSHOW_PRICES(boolean SHOW_PRICES) {
        this.SHOW_PRICES = SHOW_PRICES;
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
        t.newLine();
        if (SHOW_PRICES) {
            t.alignRight();
            t.setText(String.format(TOTAL_VENTAS + "%.2f" + MONEDA, total));
            t.newLine();

            if (monedaCUC) {
                if (REDONDEO_POR_EXCESO) {
                    t.setText(TOTAL_VENTAS + comun.redondeoPorExcesoFloat(total * R.COINCHANGE) + MN);
                } else {
                    t.setText(String.format(TOTAL_VENTAS + "%.2f" + MN, comun.setDosLugaresDecimalesFloat(total * R.COINCHANGE)));
                }
            } else {
                if (REDONDEO_POR_EXCESO) {
                    t.setText(TOTAL_VENTAS + comun.redondeoPorExcesoFloat(total / R.COINCHANGE) + CUC);
                } else {
                    t.setText(String.format(TOTAL_VENTAS + "%.2f" + CUC, comun.setDosLugaresDecimalesFloat(total / R.COINCHANGE)));
                }
            }

        }
    }

    private void addFinal(Ticket t) {
        t.feed((byte) 3);
        t.finit();
    }

    public void forceDrawerKick() {
        Ticket t = new Ticket();
        t.resetAll();
        t.initialize();
        t.drawerKick();
        sendToPrinterStatistics(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);

    }

    public void forceBell() {
        Ticket t = new Ticket();
        t.resetAll();
        t.initialize();
        t.soundBuzzer();
        sendToPrinterStatistics(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);

    }

    private void sendToPrinterStatistics(byte[] byteData, String printLocation) {
        feedPrinter(byteData, printLocation, TipoImpresion.RESUMEN);
    }

    private void addHeader(Ticket t) {
        t.resetAll();
        t.initialize();
        t.feedBack((byte) 2);
            t.alignCenter();
        if (SHOW_HEADER) {
            t.setText(CABECERA);
            t.newLine();
            t.setText(R.REST_NAME);
            t.newLine();
        }else{
            t.setText("BIENVENIDO");
            t.newLine();
            t.newLine();
        }
    }

    private void addCustomMetaData(Ticket t, String customHeader, Date fecha) {

        t.addLineSeperator();
        t.newLine();
        t.setText(customHeader);
        t.newLine();
        t.alignLeft();
        t.setText(FECHA + R.DATE_FORMAT.format(fecha));
        t.newLine();
        t.addLineSeperator();
        t.newLine();

    }

    private void addMetaData(Ticket t, Orden o, Date date) {
        t.addLineSeperator();
        t.newLine();
        t.alignRight();
        t.setText(FECHA + R.DATE_FORMAT.format(o.getVentafecha().getFecha()) + R.TIME_FORMAT.format(date));
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
            if (SHOW_PRICES) {
                if (x.getOrden().getDeLaCasa()) {
                    if (PRINT_GASTOS_EN_AUTORIZOS) {
                        t.setText(comun.setDosLugaresDecimales(x.getCantidad() * x.getProductoVenta().getGasto()));
                    } else {
                        t.setText(comun.setDosLugaresDecimales(x.getCantidad() * x.getProductoVenta().getPrecioVenta()));
                    }
                } else {
                    t.setText(comun.setDosLugaresDecimales(x.getCantidad() * x.getProductoVenta().getPrecioVenta()));
                }
                t.newLine();
            }
            if (x.getOrden().getDeLaCasa()) {
                if (PRINT_GASTOS_EN_AUTORIZOS) {
                    total += x.getCantidad() * x.getProductoVenta().getGasto();
                } else {
                    total += x.getCantidad() * x.getProductoVenta().getPrecioVenta();
                }
            } else {
                total += x.getCantidad() * x.getProductoVenta().getPrecioVenta();
            }
        }

        return total;
    }

    //
    //Private Methods
    //
    private void feedPrinter(byte[] b, String printerName, TipoImpresion modo) {

        PrintService[] prints = PrintServiceLookup.lookupPrintServices(null, null);
        DocPrintJob job = PrintServiceLookup.lookupDefaultPrintService().createPrintJob();
        job.addPrintJobListener(new JobListener());
        if (printerName != null) {
            for (int i = 0; i < prints.length; i++) {
                if (prints[i].getName().contains(printerName)) {
                    job = prints[i].createPrintJob();
                    break;
                }
            }
        }

        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(b, flavor, null);

        try {
            if (printerName != null) {
                switch (modo) {
                    case COCINA:
                        if (IMPRIMIR_TICKET_COCINA) {
                            job.print(doc, null);
                        }
                        break;
                    default:
                        job.print(doc, null);
                        break;
                }
            } else {
                job.print(doc, null);

            }
        } catch (PrintException ex) {
            Logger.getLogger(Impresion.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        int Separators = 5;
        int LenghtPerSeparator = (int) ((Ticket.PAPER_LENGHT - Separators) / 5);
        ret += fillSpace(x.getInicio(), LenghtPerSeparator) + separador;
        ret += fillSpace(x.getEntrada(), LenghtPerSeparator) + separador;
        ret += fillSpace(x.getDisponible(), LenghtPerSeparator) + separador;
        ret += fillSpace(x.getConsumo(), LenghtPerSeparator) + separador;
        ret += fillSpace(x.getFinal1(), LenghtPerSeparator);

        return ret;
    }

    private String fillSpace(float number, int finalLenght) {
        String ret = "" + comun.setDosLugaresDecimalesFloat(number);
        while (ret.length() < finalLenght) {
            ret += " ";
        }
        return ret;
    }

    private void cleanAndPrintRAM() {
        while (!RAM.isEmpty()) {
            sendToPrinterStatistics(RAM.get(0).getImpresionData(), RAM.get(0).getNombreImpresora());
            RAM.remove(0);
        }
    }

    private void log(Orden o, ProductovOrden x) {
        if (x.getEnviadosacocina() > x.getCantidad()) {
            RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.BORRAR,
                    x.getEnviadosacocina() > 0 && o.getHoraTerminada() != null ? Level.SEVERE : Level.WARNING,
                    o, x.getProductoVenta(),
                    x.getEnviadosacocina() - x.getCantidad());
        } else {
            if (x.getEnviadosacocina() < x.getCantidad()) {
                RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.AGREGAR,
                        Level.FINER, o.getCodOrden(), x.getProductoVenta(), x.getCantidad() - x.getEnviadosacocina());
            }
        }
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
            feedPrinter(t.finalCommandSet().getBytes(), DEFAULT_KITCHEN_PRINTER_LOCATION, TipoImpresion.COCINA);
        }
        cleanAndPrintRAM();

        return o;
    }

    private void addFocusedMessage(Ticket t, String sms) {
        t.addLineSeperator();
        t.newLine();
        t.addLineSeperator();
        t.newLine();
        t.alignCenter();
        t.setText(sms);
        t.newLine();
        t.addLineSeperator();
        t.newLine();
    }

    public void printResumenAlmacen(Almacen a) {
        ArrayList<InsumoAlmacen> ret = new ArrayList<>(a.getInsumoAlmacenList());
        Collections.sort(ret, (InsumoAlmacen o1, InsumoAlmacen o2) -> o1.getInsumo().getNombre().compareTo(o2.getInsumo().getNombre()));
        Ticket t = new Ticket();
        addHeader(t);
        addCustomMetaData(t, STOCK_BALANCE, new Date());

        t.alignCenter();
        t.setText(STOCK_FORMAT);
        t.newLine();
        t.newLine();

        for (InsumoAlmacen in : ret) {
            t.alignLeft();
            t.setText(in.getInsumo().toString());
            t.newLine();
            t.alignRight();
            t.setText("" + in.getCantidad());
//            t.setText(String.format("%.2f | %+.2f", in.getCantidadExistente(), in.getCantidadExistente() - in.getStockEstimation()));
            t.newLine();
        }

        t.newLine();
        t.addLineSeperator();

        addFinal(t);

        sendToPrinterStatistics(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);
    }

    public void printComprobanteTransaccion(List<Transaccion> selectedsObjects) {
        Collections.sort(selectedsObjects, (o1, o2) -> {
            return o1.getInsumo().getNombre().compareTo(o2.getInsumo().getNombre());
        });
        Ticket t = new Ticket();
        addHeader(t);
        addCustomMetaData(t, COMPROBANTE_TRANSACCION, new Date());

        t.alignCenter();
        t.alignCenter();
        Transaccion tr = selectedsObjects.get(0);
        String tipoTrans = "Tipo Transaccion: ";
        if (tr.getTransaccionEntrada() != null) {
            tipoTrans += "ENTRADA";
        } else if (tr.getTransaccionMerma() != null) {
            tipoTrans += tr.getTransaccionMerma().getRazon().toUpperCase();
        } else {
            tipoTrans += "SALIDA: " + tr.getCocina();
        }

        t.setText(tipoTrans);
        t.newLine();
        t.newLine();

        for (Transaccion in : selectedsObjects) {
            t.alignLeft();

            t.setText(in.getInsumo().toString() + "{" + in.getInsumo().getUm() + "}");
            t.newLine();
            t.alignRight();
            t.setText("" + in.getCantidad());
//            t.setText(String.format("%.2f | %+.2f", in.getCantidadExistente(), in.getCantidadExistente() - in.getStockEstimation()));
            t.newLine();
        }

        t.newLine();
        t.addLineSeperator();

        addFinal(t);

        sendToPrinterStatistics(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);
    }

    public void printResumenGastos(List<GastoVenta> lista) {
        Collections.sort(lista, (GastoVenta o1, GastoVenta o2) -> o1.getGasto().getNombre().compareTo(o2.getGasto().getNombre()));

        Ticket t = new Ticket();

        addHeader(t);

        t.addLineSeperator();
        t.newLine();
        t.alignCenter();
        t.setText(GASTO_HEADER);
        t.newLine();
        t.alignRight();
        t.setText(FECHA + R.DATE_FORMAT.format(lista.get(0).getVenta().getFecha()));
        t.newLine();
        t.addLineSeperator();
        t.newLine();

        float total = 0;
        for (GastoVenta x : lista) {
            t.alignLeft();
            t.setText(x.getGasto().getNombre());
            if (x.getDescripcion() != null) {
                t.setText(" (" + x.getDescripcion() + ")");
            }
            t.newLine();
            t.alignRight();
            t.setText(x.getImporte().toString() + R.COIN_SUFFIX);
            t.newLine();
            t.newLine();
            total += x.getImporte();
        }

        addTotalAndFinal(t, total);

        sendToPrinterStatistics(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);
    }

    public void prinPagoPorVenta(Venta instance, String usuario) {
        Personal p = PersonalDAO.getInstance().find(usuario);
        ArrayList<Orden> lista = new ArrayList<>(instance.getOrdenList());
        Collections.sort(lista);

        Ticket t = new Ticket();

        addHeader(t);

        t.addLineSeperator();
        t.newLine();
        t.alignCenter();
        t.setText(PAGO_POR_VENTA_HEADER);
        t.newLine();
        t.alignRight();
        t.setText(FECHA + R.DATE_FORMAT.format(instance.getFecha()));
        t.newLine();
        t.setText(usuario);
        t.newLine();
        t.addLineSeperator();
        t.newLine();

        float total = 0;
        for (ProductovOrden pv : VentaDAO1.getResumenVentasCamarero(instance, p)) {
            if (pv.getProductoVenta().getPagoPorVenta() != null) {
                if (pv.getProductoVenta().getPagoPorVenta() != 0) {
                    t.alignLeft();
                    t.setText(pv.getCantidad() + " " + pv.getProductoVenta().getNombre());
                    t.newLine();
                    t.alignRight();
                    if (SHOW_PRICES) {
                        t.setText(comun.setDosLugaresDecimales(pv.getCantidad() * pv.getProductoVenta().getPagoPorVenta()));
                    }
                    t.newLine();
                    total += pv.getCantidad() * pv.getProductoVenta().getPagoPorVenta();
                }

            }
        }

        addTotalAndFinal(t, total);

        sendToPrinterStatistics(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);
    }
    //
    //Inner Classes
    //

    public void printComprobantePago(Personal personal) {
        ArrayList<AsistenciaPersonal> lista = new ArrayList<>(personal.getAsistenciaPersonalList());
        Collections.sort(lista);

        Ticket t = new Ticket();

        addHeader(t);

        t.addLineSeperator();
        t.newLine();
        t.alignCenter();
        t.setText(PAGO_TRABAJADOR);
        t.newLine();
        t.alignRight();
        t.setText(FECHA + R.DATE_FORMAT.format(R.TODAYS_DATE));
        t.newLine();
        t.setText(personal.getDatosPersonales().getNombre());
        t.newLine();
        t.addLineSeperator();
        t.newLine();

        float total = 0;
        for (AsistenciaPersonal a : lista) {
            if (a.getVenta().getFecha().after(personal.getUltimodiaPago())) {
                t.alignLeft();
                t.setText(R.DATE_FORMAT.format(a.getAsistenciaPersonalPK().getVentafecha()));
                t.newLine();
                t.alignRight();
                t.setText(comun.setDosLugaresDecimales(a.getPago()));
                t.newLine();
            }
        }

        addTotalAndFinal(t, total);

        sendToPrinterStatistics(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);
    }

    public void printResumenVentaArea(List<ProductovOrden> resumenVentaPorArea, Date fecha) {
        Ticket t = new Ticket();

        addHeader(t);

        addCustomMetaData(t, RESUMEN_AREA, fecha);

        float total = addPvOrden(t, resumenVentaPorArea);

        addTotalAndFinal(t, total);

        sendToPrinterStatistics(t.finalCommandSet().getBytes(), DEFAULT_PRINT_LOCATION);
    }

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

    private enum TipoImpresion {
        COCINA, RESUMEN, ORDEN

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

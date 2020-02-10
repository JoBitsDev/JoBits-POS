/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.resources;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javax.swing.UnsupportedLookAndFeelException;
import org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel;
import restManager.persistencia.Personal;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class R {

    public static final String SEPARADOR = "_";

    public static String REST_NAME = null;

    public static String logFilePath = "logs/";

    public static String COIN_SUFFIX;

    public static Personal loggedUser = null;

    public static final Date TODAYS_DATE = new Date();

    public static int COINCHANGE = 25;

    public static String MAIN_COIN = null;

    public static boolean CONSUMO_DE_LA_CASA_EN_ESTADISTICAS = true;

    public static boolean VARIOS_TURNOS = false;

    public static boolean CAJERO_PERMISOS_ESPECIALES = false;

    public static String NO_MESA_CAJA = "M-0";

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd'/'MM'/'yy");

    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat(" hh ':' mm ' ' a ");

    public static final SimpleDateFormat DATE_FORMAT_FOR_LOGS = new SimpleDateFormat("yyyy'_'MM'_'dd");

    public static DecimalFormat formatoMoneda = new DecimalFormat("0.00");

    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("Strings");

    public static String PERIRSTENCE_UNIT_NAME = R.RESOURCE_BUNDLE.getString("unidad_persistencia_remota");

    public static String RELEASE_VERSION = "Version 2.6.9.0";

    public static int BUILD_VERSION = 29;

    public static String CONFIG_FILE_PATH = "y.cfg";

    public static void setLookAndFeels() {
        try {
            javax.swing.UIManager.setLookAndFeel(new SubstanceBusinessLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static enum SettingID {

        //
        //GENERALES
        //
        GENERAL_CAMBIO_MONEDA("GENERAL_CAMBIO_MONEDA", 24, null),
        GENERAL_TURNOS_VARIOS("GENERAL_MULTIPLES_TURNOS", 0, null),
        GENERAL_CAJERO_PERMISOS_ESP("GENERAL_CAJERO_PERMISOS_ESP", 0, null),
        GENERAL_CONSUMO_CASA_ESTADISTICAS("GENERAL_CONSUMO_CASA_ESTADISTICAS", 0, null),
        GENERAL_SERVER_IP("GENERAL_SERVIDOR_IP", -1, "192.168.173.1"),
        GENERAL_MESA_FIJA_CAJERO("GENERAL_MESA_FIJA_CAJERO", 1, null),
        GENERAL_ULTIMA_ORDEN_PRUEBA("Y", -1, null),
        //
        //IMPRESION
        //
        IMPRESION_TICKET_VALOR_ENCABEZADO("PRINTING_TICKET_HEADER_VALUE", -1, "Restaurante"),
        IMPRESION_TICKET_TAMANO_PAPEL("PRINTING_TICKET_PAPER_SIZE", 38, null),
        IMPRESION_TICKET_CARACTER_SEPARADOR("PRINTING_TICKET_SEPARATOR_CHAR", -1, "*"),
        IMPRESION_TICKET_ENCABEZADO_RESTAURANTE("PRINTING_TICKET_HEADER", 1, null),
        IMPRESION_TICKET_SUBTOTAL("PRINTING_TICKET_SUBTOTAL", 1, null),
        IMPRESION_IMPRIMIR_COCINA_CENTRAL("PRINTING_CENTRAL_KITCHEN", 0, null),
        IMPRESION_IMPRIMIR_GASTOS_AUTORIZOS("PRINTING_EXPENSES_IN_HAUSE_TICKETS", 0, null),
        IMPRESION_IMPRIMIR_TICKET_EN_COCINA("PRINTING_PRINT_KITCHEN_TICKET", 1, null),
        IMPRESION_CANTIDAD_COPIAS("PRINTING_COPIES", 0, null),
        IMPRESION_REDONDEO_EXCESO("PRINTING_ROUNDING", 1, null),
        IMPRESION_IMPRIMIR_MONEDA_SECUNDARIA("PRINTING_SECOND_COIN", 1, null);

        private final String value;
        private final int integerValue;
        private final String stringValue;

        private SettingID(String value, Integer valorInt, String valorString) {
            this.value = value;
            this.integerValue = valorInt;
            this.stringValue = valorString;
        }

        public String getValue() {
            return value;
        }

        public int getIntegerValue() {
            return integerValue;
        }

        public String getStringValue() {
            return stringValue;
        }

        @Override
        public String toString() {
            return getValue();
        }

    }

    public static enum UM {
        U("U"),
        Gr("Gr"),
        Kg("Kg"),
        Lbs("Lbs"),
        Lts("Lts");

        private final String valor;

        private UM(String valor) {
            this.valor = valor;
        }

        public String getValor() {
            return valor;
        }

        @Override
        public String toString() {
            return valor;
        }

    }

    public enum NivelAcceso {
        DEPENDIENTE(0),
        CAJERO(1),
        ALMACENERO(2),
        ECONOMICO(3),
        ADMINISTRADOR(4),
        DESARROLLADOR(5);

        private final int nivel;

        private NivelAcceso(int nivel) {
            this.nivel = nivel;
        }

        public int getNivel() {
            return nivel;
        }
    }

    public enum TipoGasto {
        UNSPECIFIED("-"),
        CONSUMIBLE("CONSUMIBLE"),
        FIJO("FIJO"),
        IMPUESTO("IMPUESTO"),
        INVERSION("INVERSION"),
        TRANSPORTE("TRANSPORTE"),
        SERVICIO("SERVICIO"),
        ACTIVO_FIJO("ACTIVO_FIJO"),
        OTROS("OTROS");

        private final String nombre;

        private TipoGasto(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public String toString() {
            return nombre;
        }

        public String getNombre() {
            return nombre;
        }

    }

}

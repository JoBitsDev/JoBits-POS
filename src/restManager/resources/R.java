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
import restManager.persistencia.models.NegocioDAO;

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

    public static String LICENCE_KEY_PATH = "lic.key";
            
    public static String COIN_SUFFIX;

    public static Personal loggedUser = null;

    public static final Date TODAYS_DATE = new Date();

    public static final float PERCENTAGE = 0;

    public static final int COINCHANGE = 24;

    public static String MAIN_COIN = null;

    public static final boolean CONSUMO_DE_LA_CASA_EN_ESTADISTICAS = true;
    
    public static final boolean VARIOS_TURNOS = false;
    
    public static final boolean CAJERO_PERMISOS_ESPECIALES = false;

    public static final String NO_MESA = "M-0";

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd'/'MM'/'yy");

    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat(" hh ':' mm ' ' a ");

    public static final SimpleDateFormat DATE_FORMAT_FOR_LOGS = new SimpleDateFormat("yyyy'_'MM'_'dd");

    public static DecimalFormat formatoMoneda = new DecimalFormat("0.00");

    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("Strings");

    public static String PERIRSTENCE_UNIT_NAME = R.RESOURCE_BUNDLE.getString("unidad_persistencia_remota");

    public static String RELEASE_VERSION = "Version 2.6.2(BETA)";

    public static int BUILD_VERSION = 10;

    public static void setLookAndFeels() {
        try {
            javax.swing.UIManager.setLookAndFeel(new SubstanceBusinessLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static enum UM {
        U("U"),
        Gr("Gr"),
        Kg("Kg"),
        Lbs("Lbs"),
        Lts("Lts");

        private String valor;

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

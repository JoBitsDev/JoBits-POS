/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.resources;

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

    public static String coinSuffix;

    public static Personal loggerUser = null;

    public static final Date TODAYS_DATE = new Date();

    public static final float PERCENTAGE = 0;

    public static final int COINCHANGE = 24;

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd'/'MM'/'yy");

    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("Strings");

    public static String PERIRSTENCE_UNIT_NAME = R.RESOURCE_BUNDLE.getString("unidad_persistencia_remota");

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
        ADMINISTRADOR(4);

        private final int nivel;

        private NivelAcceso(int nivel) {
            this.nivel = nivel;
        }

        public int getNivel() {
            return nivel;
        }
    }

}

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

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class R {
    
    public static final Date TODAYS_DATE = new Date();
    
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd'/'MM'/'yy");
    
    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("Strings");
    
    public static  String PERIRSTENCE_UNIT_NAME = R.RESOURCE_BUNDLE.getString("unidad_persistencia_remota");
    
    public static void setLookAndFeels(){
        try {
                    javax.swing.UIManager.setLookAndFeel(new SubstanceBusinessLookAndFeel());
            } catch (UnsupportedLookAndFeelException ex) {
                System.out.println(ex.getMessage());
        }
    }
    
}

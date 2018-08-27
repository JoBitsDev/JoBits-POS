/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.resources;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class staticValues {
    
    public static final Date TODAYS_DATE = new Date();
    
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd'/'MM'/'yy");
    
    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("/Strings");

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.resources.values;

import java.awt.Font;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class Fonts {
     
    public static final String 
            PRIMARY_FONT_NAME = "Lucida Grande",
            SECONDARY_FONT_NAME = "Arial",
            BUTTON_FONT_NAME = "Arial";
            
    
    public static final Font 
            TITLE = new Font(PRIMARY_FONT_NAME, Font.BOLD, 22),
            SUBTITLE = new Font(PRIMARY_FONT_NAME, Font.BOLD, 18),
            BODY = new Font(PRIMARY_FONT_NAME, Font.BOLD, 14),
            BUTTON = new Font(BUTTON_FONT_NAME, Font.PLAIN, 14);
 
}

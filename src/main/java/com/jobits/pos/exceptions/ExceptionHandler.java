/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.exceptions;

import java.awt.Component;
import java.awt.Container;
import javax.swing.JOptionPane;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.OldView;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ExceptionHandler {

    private Exception e;
    private OldView v;
    private String message;

    public static void showExceptionToUser(Exception e, OldView v, String message) {
        JOptionPane.showMessageDialog((Component) v, message+"\n" + e.getMessage(),
                R.RESOURCE_BUNDLE.getString("label_error") + e.getMessage(), JOptionPane.ERROR_MESSAGE);
    }

    public static void showExceptionToUser(Exception e, OldView v) {
        showExceptionToUser(e, v, null);
    }

    public static void showExceptionToUser(Exception e) {
        showExceptionToUser(e, null, null);

    }
    
    public static void showExceptionToUser(Exception e,String s) {
        showExceptionToUser(e, null, s);

    }
    
    

}

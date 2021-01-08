/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.exceptions;

import com.jobits.pos.main.Application;
import java.awt.Component;
import javax.swing.JOptionPane;
import com.jobits.pos.recursos.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ExceptionHandler {

    private Exception e;
    private String message;

    public static void showExceptionToUser(Exception e, String message) {
        JOptionPane.showMessageDialog((Component) Application.getInstance().getMainWindow(), message + "\n" + e.getMessage(),
                R.RESOURCE_BUNDLE.getString("label_error") + e.getMessage(), JOptionPane.ERROR_MESSAGE);
    }

}

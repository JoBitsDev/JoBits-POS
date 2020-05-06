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

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class RestManagerRuntimeException extends RuntimeException {

    protected Component throwedFrom;
    protected boolean quietMode;

    public RestManagerRuntimeException() {
    }

    public RestManagerRuntimeException(boolean quietMode) {
        super();
        this.quietMode = quietMode;
    }
    

    public RestManagerRuntimeException(String message) {
        super(message);
    }

    public RestManagerRuntimeException(Component throwedFrom) {
        this.throwedFrom = throwedFrom;
    }

    public RestManagerRuntimeException(Component throwedFrom, String message) {
        super(message);
        this.throwedFrom = throwedFrom;
    }

    public RestManagerRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    protected void showMessage(String message) {
        JOptionPane.showMessageDialog(throwedFrom, message,
                R.RESOURCE_BUNDLE.getString("label_error"), JOptionPane.ERROR_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/alerta.png")));
    }

}

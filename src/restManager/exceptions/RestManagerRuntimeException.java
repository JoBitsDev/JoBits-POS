/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.exceptions;

import java.awt.Component;
import java.awt.Container;
import javax.swing.JOptionPane;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class RestManagerRuntimeException extends RuntimeException {

    protected Component throwedFrom;

    public RestManagerRuntimeException() {
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

    protected void showMessage(String message) {
        JOptionPane.showMessageDialog(throwedFrom, message,
                R.RESOURCE_BUNDLE.getString("label_error"), JOptionPane.ERROR_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/alerta.png")));
    }

}

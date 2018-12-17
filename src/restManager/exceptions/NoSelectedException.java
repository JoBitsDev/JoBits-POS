/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.exceptions;

import java.awt.Component;
import javax.swing.JOptionPane;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class NoSelectedException extends RuntimeException {

    public NoSelectedException() {
        showMessage(null, R.RESOURCE_BUNDLE.getString("exception_no_selected"));
    }

    public NoSelectedException(String message) {
        super(message);
        showMessage(null, message);
    }

    public NoSelectedException(Component parent) {
        showMessage(parent,R.RESOURCE_BUNDLE.getString("exception_no_selected"));
    }

    private void showMessage(Component parent, String message){
           JOptionPane.showMessageDialog(parent, message,
                R.RESOURCE_BUNDLE.getString("label_error"), JOptionPane.ERROR_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/alerta.png")));
    }
}

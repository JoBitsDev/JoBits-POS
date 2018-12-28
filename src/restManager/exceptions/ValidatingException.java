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
public class ValidatingException extends RuntimeException {

    public ValidatingException() {
        JOptionPane.showMessageDialog(null, R.RESOURCE_BUNDLE.getString("exception_validating"),
                R.RESOURCE_BUNDLE.getString("label_error"), JOptionPane.ERROR_MESSAGE);
    }

     public ValidatingException(Component container) {
        JOptionPane.showMessageDialog(container, R.RESOURCE_BUNDLE.getString("exception_validating"),
                R.RESOURCE_BUNDLE.getString("label_error"), JOptionPane.ERROR_MESSAGE);
    }
    
    public ValidatingException(String message) {
        super(message);
        JOptionPane.showMessageDialog(null, message,
                R.RESOURCE_BUNDLE.getString("label_error"), JOptionPane.ERROR_MESSAGE);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.exceptions;

import javax.swing.JOptionPane;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class DevelopingOperationException extends RuntimeException {

    public DevelopingOperationException() {
        JOptionPane.showMessageDialog(null, R.RESOURCE_BUNDLE.getString("exception_developing"),
                R.RESOURCE_BUNDLE.getString("label_error"), JOptionPane.ERROR_MESSAGE);
    }

    public DevelopingOperationException(String message) {
        super(message);
        JOptionPane.showMessageDialog(null, message,
                R.RESOURCE_BUNDLE.getString("label_error"), JOptionPane.ERROR_MESSAGE);
    }

}

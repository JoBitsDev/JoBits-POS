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
public class UnExpectedErrorException extends RestManagerRuntimeException {

    public UnExpectedErrorException() {
        super();
        showMessage(R.RESOURCE_BUNDLE.getString("exception_unexpected"));
    }

    public UnExpectedErrorException(String message) {
        super(message);
        showMessage(message);
    }

    public UnExpectedErrorException(Component parent) {
        super(parent);
        showMessage(R.RESOURCE_BUNDLE.getString("exception_unexpected"));
    }

    public UnExpectedErrorException(Component throwedFrom, String message) {
        super(throwedFrom, message);
        showMessage(message);
    }
    
    

}

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
public class ValidatingException extends RestManagerRuntimeException {

    private final String EXCEPTION_MESSAGE = R.RESOURCE_BUNDLE.getString("exception_validating");

    public ValidatingException() {
        super();
        showMessage(EXCEPTION_MESSAGE);
    }

    public ValidatingException(Component container) {
        super(container);
        showMessage(EXCEPTION_MESSAGE);
    }

    public ValidatingException(String message) {
        super(message);
        showMessage(message);
    }

    public ValidatingException(Component throwedFrom, String message) {
        super(throwedFrom, message);
        showMessage(message);
    }

}

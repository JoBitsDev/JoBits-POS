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
public class NoSelectedException extends RestManagerRuntimeException {

    public NoSelectedException() {
        super();
        showMessage(R.RESOURCE_BUNDLE.getString("exception_no_selected"));
    }

    public NoSelectedException(String message) {
        super(message);
        showMessage(message);
    }

    public NoSelectedException(Component parent) {
        super(parent);
        showMessage(R.RESOURCE_BUNDLE.getString("exception_no_selected"));
    }

}

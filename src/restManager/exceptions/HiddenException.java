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
public class HiddenException extends RestManagerRuntimeException {

    
    public HiddenException() {
        super();
    }

    public HiddenException(Component container) {
       super(container);
    }

    public HiddenException(String message) {
        super(message);
    }

}

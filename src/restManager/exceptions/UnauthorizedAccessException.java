/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.exceptions;

import java.awt.Component;
import restManager.resources.R;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class UnauthorizedAccessException extends RestManagerRuntimeException{

    private final String EXCEPTION_MESSAGE = R.RESOURCE_BUNDLE.getString("exception_unaothorized");
    
    public UnauthorizedAccessException() {
        super();
        showMessage(EXCEPTION_MESSAGE);
    }

    public UnauthorizedAccessException(String message) {
        super(message);
        showMessage(EXCEPTION_MESSAGE);
    }

    public UnauthorizedAccessException(Component throwedFrom) {
        super(throwedFrom);
        showMessage(EXCEPTION_MESSAGE);
        
    }

    public UnauthorizedAccessException(Component throwedFrom, String message) {
        super(throwedFrom, message);
        showMessage(message);
    }

    

}

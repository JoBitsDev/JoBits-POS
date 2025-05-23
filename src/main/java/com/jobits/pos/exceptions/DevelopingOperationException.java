/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.exceptions;

import java.awt.Component;
import com.jobits.pos.recursos.R;
import com.root101.clean.core.domain.services.ResourceHandler;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class DevelopingOperationException extends RestManagerRuntimeException {

    private final String EXCEPTION_MESSAGE = ResourceHandler.getString("exception_developing");
    
    public DevelopingOperationException() {
        super();
        showMessage(EXCEPTION_MESSAGE);
    }

    public DevelopingOperationException(String message) {
        super(message);
        showMessage(message);
    }

    public DevelopingOperationException(Component throwedFrom) {
        super(throwedFrom);
        showMessage(EXCEPTION_MESSAGE);
    }
    
    

}

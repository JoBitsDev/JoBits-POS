/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.exceptions;

import java.awt.Component;
import javax.swing.JOptionPane;
import com.jobits.pos.recursos.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class DuplicatedException extends RestManagerRuntimeException {

    private final String EXCEPTION_MESSAGE = R.RESOURCE_BUNDLE.getString("exception_duplicated");
    
    public DuplicatedException() {
        super();
        showMessage(EXCEPTION_MESSAGE);
    }

    public DuplicatedException(Component container) {
       super(container);
        showMessage(EXCEPTION_MESSAGE);
    }

    public DuplicatedException(String message) {
        super(message);
        showMessage(message);
    }

}

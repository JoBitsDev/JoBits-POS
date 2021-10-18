/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.exceptions;

import java.awt.Component;
import javax.swing.JOptionPane;
import com.jobits.pos.recursos.R;
import com.root101.clean.core.domain.services.ResourceHandler;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class NoSelectedException extends RestManagerRuntimeException {

    public NoSelectedException() {
        super();
        showMessage(ResourceHandler.getString("exception_no_selected"));
    }

    public NoSelectedException(String message) {
        super(message);
        showMessage(message);
    }

    public NoSelectedException(Component parent) {
        super(parent);
        showMessage(ResourceHandler.getString("exception_no_selected"));
    }

}

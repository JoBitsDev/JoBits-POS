/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller;

import com.jobits.pos.ui.View;
import java.beans.PropertyChangeListener;
import com.jobits.pos.adapters.repo.Model;

/**
 *
 * @author Jorge
 */
public interface Controller extends PropertyChangeListener{
    
        public Model getModel();
        
        public View getView();
    
}

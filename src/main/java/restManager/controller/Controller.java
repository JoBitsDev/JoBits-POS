/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller;

import GUI.Views.View;
import java.beans.PropertyChangeListener;
import restManager.persistencia.models.Model;

/**
 *
 * @author Jorge
 */
public interface Controller extends PropertyChangeListener{
    
        public Model getModel();
        
        public View getView();
    
}

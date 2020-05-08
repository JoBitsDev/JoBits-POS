/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.cordinator;


/**
 * 
 * JoBits
 * @author Jorge
 * 
 */
public class MainCoordinator implements Coordinator {

    
    @Override
    public void start() {
        MainNavigator.showLoginView();
    }

}

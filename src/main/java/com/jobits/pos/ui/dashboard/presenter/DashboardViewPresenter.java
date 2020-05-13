/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.ui.dashboard.presenter;

import com.jobits.pos.ui.presenters.AbstractViewPresenter;

/**
 * 
 * JoBitsDas
 * @author Jorge
 * 
 */
public class DashboardViewPresenter extends AbstractViewPresenter<DashBoardViewModel>{

    public DashboardViewPresenter() {
        super(new DashBoardViewModel());
    }
    
    
    @Override
    protected void registerOperations() {
        
    }

}

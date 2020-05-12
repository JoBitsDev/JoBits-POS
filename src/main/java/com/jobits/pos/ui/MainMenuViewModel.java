/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui;

import com.jobits.pos.ui.viewmodel.AbstractViewModel;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class MainMenuViewModel extends AbstractViewModel {

    private boolean collapsed;

    public static final String PROP_COLLAPSED = "collapsed";

    /**
     * Get the value of collapsed
     *
     * @return the value of collapsed
     */
    public boolean isCollapsed() {
        return collapsed;
    }

    /**
     * Set the value of collapsed
     *
     * @param collapsed new value of collapsed
     */
    public void setCollapsed(boolean collapsed) {
        boolean oldCollapsed = this.collapsed;
        this.collapsed = collapsed;
        firePropertyChange(PROP_COLLAPSED, oldCollapsed, collapsed, true);
    }

}

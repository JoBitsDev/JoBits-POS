/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.cordinator;

import com.jobits.pos.main.PresenterFacade;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class NavigationNode {

    private NavigationNode parentNode;
    
    private AbstractViewPresenter presenter;

    private String viewUIDName;

    public NavigationNode(NavigationNode parentNode, String viewUIDName, AbstractViewPresenter presenter) {
        this.parentNode = parentNode;
        this.viewUIDName = viewUIDName;
        this.presenter = presenter;
    }

    public static NavigationNode of(NavigationNode parentNode, String viewUIDName, AbstractViewPresenter presenter) {
        return new NavigationNode(parentNode, viewUIDName, presenter);
    }

    public NavigationNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(NavigationNode parentNode) {
        this.parentNode = parentNode;
    }

    public String getViewUIDName() {
        return viewUIDName;
    }

    public void setViewUIDName(String viewUIDName) {
        this.viewUIDName = viewUIDName;
    }

    public AbstractViewPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(AbstractViewPresenter presenter) {
        this.presenter = presenter;
    }
    
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.cordinator;

import com.jobits.pos.ui.RootView;
import com.jobits.pos.ui.View;
import com.jobits.pos.ui.ViewFactory;
import java.awt.Component;
import java.util.List;
import java.util.Observable;
import javax.swing.JFrame;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class MainNavigator implements Navigator {

    private Coordinator coordinator;

    private NavigationNode activeNode;

    private static MainNavigator instance;

    private MainNavigator() {
        this.activeNode = NavigationNode.of(null, ViewFactory.getRootView());
    }

    public void navigateTo(String viewUniqueName) {
        if (coordinator.canNavigateTo(activeNode.getView().getViewName(), viewUniqueName)) {
            this.activeNode = NavigationNode.of(activeNode, ViewFactory.getView(viewUniqueName));
            RootView.getInstance().showView(activeNode.getView());
        }
    }

    public void navigateUp() {
        if (activeNode.getParentNode() != null) {
            activeNode = activeNode.getParentNode();
            RootView.getInstance().showView(activeNode.getView());
        }
    }

}

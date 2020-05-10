/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.cordinator;

import com.jobits.pos.ui.RootView;
import com.jobits.pos.ui.View;
import com.jobits.pos.ui.ViewFacade;
import com.jobits.pos.ui.login.LogInView;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
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

    private Coordinator coordinator = MainCoordinator.getInstance();

    private NavigationNode activeNode;

    private static MainNavigator instance;

    public static MainNavigator getInstance() {
        if (instance == null) {
            instance = new MainNavigator();
        }
        return instance;
    }

    private MainNavigator() {
        this.activeNode = NavigationNode.of(null, ViewFacade.getView(LogInView.VIEW_NAME,null).getViewName());
        showView();

    }

    public void navigateTo(String viewUniqueName) {
        if (coordinator.canNavigateTo(activeNode.getViewUIDName(), viewUniqueName)) {
            this.activeNode = NavigationNode.of(activeNode, viewUniqueName);
            showView();

        }
    }

    public void navigateTo(String viewUniqueName, AbstractViewPresenter presenter) {
        if (coordinator.canNavigateTo(activeNode.getViewUIDName(), viewUniqueName)) {
            this.activeNode = NavigationNode.of(activeNode, viewUniqueName);
            showView(presenter);

        }
    }

    public void navigateUp() {
        if (activeNode.getParentNode() != null) {
            activeNode = activeNode.getParentNode();
            showView();
        }
    }

    private void showView() {
        RootView.getInstance().showView(activeNode.getViewUIDName());
    }

    private void showView(AbstractViewPresenter presenter) {
        RootView.getInstance().showView(activeNode.getViewUIDName(), presenter);
    }

}

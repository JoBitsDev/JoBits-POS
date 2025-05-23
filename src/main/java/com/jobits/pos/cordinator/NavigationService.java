/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.cordinator;

import com.jobits.pos.main.Application;
import com.jobits.pos.main.ViewFacade;
import com.jobits.pos.ui.login.LogInView;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import org.jboss.logging.Logger;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class NavigationService implements Navigator {

    private Coordinator coordinator = CoordinatorService.getInstance();

    private NavigationNode activeNode;

    private static NavigationService instance;

    public static NavigationService getInstance() {
        if (instance == null) {
            instance = new NavigationService();
        }
        return instance;
    }

    private NavigationService() {
        this.activeNode = NavigationNode.of(null, ViewFacade.getView(LogInView.VIEW_NAME, null).getViewName(),null);
    }

    public void startNavigation() {
        showView(null, DisplayType.NORMAL);
    }

    public void navigateTo(String viewUniqueName) {
        if (coordinator.canNavigateTo(activeNode.getViewUIDName(), viewUniqueName)) {
            this.activeNode = NavigationNode.of(activeNode, viewUniqueName,null);
            showView(null, DisplayType.NORMAL);

        } else {
            Logger.getLogger(getClass()).log(Logger.Level.WARN, "No route to " + viewUniqueName + " defined in the main coordinator");
        }
    }

    public void navigateTo(String viewUniqueName, DisplayType displayType) {
        if (coordinator.canNavigateTo(activeNode.getViewUIDName(), viewUniqueName)) {
            this.activeNode = NavigationNode.of(activeNode, viewUniqueName,null);
            showView(null, displayType);

        } else {
            Logger.getLogger(getClass()).log(Logger.Level.WARN, "No route to " + viewUniqueName + " defined in the main coordinator");
        }
    }

    public void navigateTo(String viewUniqueName, AbstractViewPresenter presenter) {
        if (coordinator.canNavigateTo(activeNode.getViewUIDName(), viewUniqueName)) {
            this.activeNode = NavigationNode.of(activeNode, viewUniqueName,presenter);
            showView(presenter, DisplayType.NORMAL);

        }
    }

    public void navigateTo(String viewUniqueName, AbstractViewPresenter presenter, DisplayType displayType) {
        if (coordinator.canNavigateTo(activeNode.getViewUIDName(), viewUniqueName)) {
            this.activeNode = NavigationNode.of(activeNode, viewUniqueName,presenter);
            showView(presenter, displayType);

        }
    }

    public void navigateUp() {
        if (activeNode.getParentNode() != null) {
            activeNode = activeNode.getParentNode();
            showView(activeNode.getPresenter(), DisplayType.NORMAL);
        }
    }

    private void showView(AbstractViewPresenter presenter, DisplayType displayType) {
        Application.getInstance().showView(activeNode.getViewUIDName(), presenter, displayType);
    }

}

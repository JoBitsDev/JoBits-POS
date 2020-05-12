/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui;

import com.jobits.pos.controller.login.MainMenuController;
import com.jobits.pos.cordinator.MainNavigator;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.Optional;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class MainMenuPresenter extends AbstractViewPresenter<MainMenuViewModel> {

    MainMenuController controller;

    public MainMenuPresenter(MainMenuController controller) {
        super(new MainMenuViewModel());
        this.controller = controller;

    }

    @Override
    protected void registerOperations() {
        for (MainMenuController.MenuButtons v : MainMenuController.MenuButtons.values()) {
            registerOperation(new AbstractViewAction(v.toString()) {
                @Override
                public Optional doAction() {
                    MainNavigator.getInstance().navigateTo(v.toString());
                    return Optional.empty();
                }
            });
        }
    }

}

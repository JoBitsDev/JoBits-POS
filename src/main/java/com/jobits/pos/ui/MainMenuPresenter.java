/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui;

import com.jobits.pos.controller.login.MainMenuController;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.jobits.pos.main.ViewFacade;
import com.jobits.pos.ui.licencia.LicenceDialogView;
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
            if (v == MainMenuController.MenuButtons.LICENCIA) {
                registerOperation(new AbstractViewAction(v.toString()) {
                    @Override
                    public Optional doAction() {
                        ViewFacade.getView(LicenceDialogView.VIEW_NAME, null);
                        return Optional.empty();
                    }
                });
            } else {
                registerOperation(new AbstractViewAction(v.toString()) {
                    @Override
                    public Optional doAction() {
                        NavigationService.getInstance().navigateTo(v.toString());
                        return Optional.empty();
                    }
                });
            }
        }
    }

}

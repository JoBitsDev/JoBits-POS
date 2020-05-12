/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.main;

import com.jobits.pos.controller.login.MainMenuController;
import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.controller.login.UbicacionConexionController;
import com.jobits.pos.ui.View;
import com.jobits.pos.ui.dashboard.presenter.DashboardViewPresenter;
import com.jobits.pos.ui.dashboard.presenter.MainMenuPresenter;
import com.jobits.pos.ui.dashboard.DashBoardView;
import com.jobits.pos.ui.login.MainMenuView;
import com.jobits.pos.ui.login.LogInView;
import com.jobits.pos.ui.login.UbicacionView;
import com.jobits.pos.ui.login.presenter.LoginViewPresenter;
import com.jobits.pos.ui.login.presenter.UbicacionViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class PresenterFacade {

    public static AbstractViewPresenter getPresenterFor(String viewUIDName) {
        switch (viewUIDName) {
            case LogInView.VIEW_NAME:
                return new LoginViewPresenter(new LogInController());
            case UbicacionView.VIEW_NAME:
                return new UbicacionViewPresenter(new UbicacionConexionController());
            case MainMenuView.VIEW_NAME:
                throw new IllegalStateException("Bad call on view: " + MainMenuView.VIEW_NAME);
            case DashBoardView.VIEW_NAME:
                return new DashboardViewPresenter();
            default:
                throw new IllegalArgumentException("No presenter register for " + viewUIDName);
        }

    }

}

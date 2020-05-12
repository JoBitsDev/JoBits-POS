/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui;

import com.jobits.pos.main.PresenterFacade;
import com.jobits.pos.ui.dashboard.DashBoardView;
import com.jobits.pos.ui.login.LogInView;
import com.jobits.pos.ui.login.UbicacionView;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import javax.swing.JPanel;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class ViewFacade {

    public static View getRootView(AbstractViewPresenter presenter) {
        return new LogInView(PresenterFacade.getPresenterFor(LogInView.VIEW_NAME));
    }

    public static View getView(String viewUniqueName, AbstractViewPresenter presenter) {
        AbstractViewPresenter p;
        if (presenter == null) {
            p = PresenterFacade.getPresenterFor(viewUniqueName);
        } else {
            p = presenter;
        }
        switch (viewUniqueName) {
            case UbicacionView.VIEW_NAME:
                return new UbicacionView(p);
            case LogInView.VIEW_NAME:
                return getRootView(p);
            case MainMenuView.VIEW_NAME:
                return new MainMenuView(p);
            case DashBoardView.VIEW_NAME:
                return new DashBoardView(p);
            default:
                throw new IllegalArgumentException("Vista no registrada para UID " + viewUniqueName);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.autorizo;

import com.jobits.pos.controller.login.Authorizer;
import com.jobits.pos.controller.login.LogInService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.autorizo.presenter.AutorizoViewPresenter;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class AuthorizerImpl implements Authorizer {

    
    @Override
    public void authorize(LogInService service, String windowTitle) {
        Application.getInstance().getNavigator().navigateTo(AutorizoView.VIEW_NAME,
                new AutorizoViewPresenter(service, windowTitle), DisplayType.POPUP);
    }

}

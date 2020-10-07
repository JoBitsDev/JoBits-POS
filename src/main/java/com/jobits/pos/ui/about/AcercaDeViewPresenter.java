/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.about;

import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import static com.jobits.pos.ui.statusbar.StatusBarPresenter.ACTION_LICENCIA;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.Calendar;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class AcercaDeViewPresenter extends AbstractViewPresenter<AcercaDeViewModel> {

    public static final String ACTION_CLOSE = "Close";

    public AcercaDeViewPresenter() {
        super(new AcercaDeViewModel());
        refreshBean();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_CLOSE) {
            @Override
            public Optional doAction() {
                Application.getInstance().getNavigator().navigateUp();
                return Optional.empty();
            }
        });
    }

    private void refreshBean() {
        getBean().setVersion_sistema(R.RELEASE_VERSION);
        Calendar date = Calendar.getInstance();
        String a = R.RESOURCE_BUNDLE.getString("label_copyrigth") + " (2016 - " + date.get(Calendar.YEAR) + ") JoBits POS.";
        getBean().setAnno_copyrigth(a);
    }

}

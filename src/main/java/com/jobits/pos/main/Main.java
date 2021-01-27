/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.main;

import com.jobits.pos.controller.configuracion.ConfiguracionController;
import java.util.Locale;
import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.ui.RootView;
import com.jobits.pos.ui.login.LogInView;
import com.jobits.pos.ui.login.presenter.LoginViewPresenter;
import com.jobits.pos.ui.utils.LoadingWindow;
import java.awt.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class Main {

    public static void main(String[] args) {

        Application app = Application.createApplication(true);

        SplashScreen sp = new SplashScreen();
        app.init();
        sp.dispose();
        java.awt.EventQueue.invokeLater(() -> {
            app.start();
        });

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.main;

import java.util.Locale;
import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.ui.RootView;
import com.jobits.pos.ui.login.LogInView;
import com.jobits.pos.ui.login.presenter.LoginViewPresenter;
import com.jobits.pos.ui.utils.LoadingWindow;
import java.awt.Window;
import javax.swing.JFrame;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class Main {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            Application app = Application.getInstance();
            app.start();
        });
    }

}

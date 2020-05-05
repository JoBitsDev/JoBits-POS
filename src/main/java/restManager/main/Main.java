/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.main;

import java.util.Locale;
import restManager.controller.login.LogInController;
import restManager.util.LoadingWindow;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class Main {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            Locale.setDefault(Locale.Category.FORMAT, Locale.ENGLISH); // But the formatting in English
            LogInController loginController = new LogInController();
            loginController.constructView(null);
        });
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.main;

import GUI.FirstTimeDialog;
import GUI.Views.login.LogInDialogView;
import java.util.Arrays;
import javax.swing.SwingWorker;
import restManager.controller.login.LogInController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.jpa.staticContent;
import restManager.resources.R;
import restManager.util.LoadingWindow;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class Main {

    public static void main(String[] args) {
        LoadingWindow.show(null);
     
        java.awt.EventQueue.invokeLater(() -> {
            LogInController loginController = new LogInController();
            loginController.constructView(null);
        });
    }

}

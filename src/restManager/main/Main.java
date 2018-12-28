/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.main;

import GUI.FirstTimeDialog;
import GUI.Views.login.LogInDialog;
import java.util.Arrays;
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
        staticContent.init(R.PERIRSTENCE_UNIT_NAME);
        boolean newDatabase = checkIfDatabaseIsNew();

        if (newDatabase) {
            new FirstTimeDialog(new javax.swing.JFrame(), true);
        } else {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    LogInDialog dialog = new LogInDialog( null);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                }
            });
        }

    }

    private static boolean checkIfDatabaseIsNew() {
        if(staticContent.isCONECTADO()){
        return staticContent.personalJPA.findPersonalEntities().isEmpty();
    }else{
            return false;
        }
    }

}

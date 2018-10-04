/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.main;

import GUI.FirstTimeDialog;
import GUI.LogInDialog;
import restManager.persistencia.jpa.staticContent;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class Main {

    public static void main(String[] args) {
        
        
        staticContent.init(R.PERIRSTENCE_UNIT_NAME);
        boolean newDatabase = checkIfDatabaseIsNew();

        if (newDatabase) {

            new FirstTimeDialog(new javax.swing.JFrame(), true);

        } else {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    LogInDialog dialog = new LogInDialog(new javax.swing.JFrame(), true);
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

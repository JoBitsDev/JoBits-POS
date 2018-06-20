/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.main;

import GUI.FirstTimeDialog;
import GUI.LogInDialog;
import restManager.persistencia.jpa.PersonalJpaController;
import restManager.persistencia.jpa.staticContent;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class Main {
    
    public static void main(String[] args) {
        if(checkIfDatabaseIsNew()){
            
            new FirstTimeDialog(new javax.swing.JFrame(), true);
            
        }
        else{
             java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LogInDialog dialog = new LogInDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
        }
            
    }

    private static boolean checkIfDatabaseIsNew() {
        return staticContent.personalJPA.findPersonalEntities().isEmpty();
    }
}

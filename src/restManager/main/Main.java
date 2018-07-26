/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.main;

import GUI.FirstTimeDialog;
import GUI.LogInDialog;
import restManager.backup.BackUp;
import restManager.persistencia.jpa.staticContent;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class Main {

    public static void main(String[] args) {
        
        //back_up();
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
        return staticContent.personalJPA.findPersonalEntities().isEmpty();
    }

    private static void back_up() {
        BackUp bu = new BackUp();
        bu.startBackup();

        // backup cocinas
        bu.backUPCocina(staticContent.cocinaJPA.findCocinaEntities());
        // backup secciones
        bu.backUPSecciones(staticContent.seccionJPA.findSeccionEntities());
        // backup ingredientes
        // backup platos
        bu.backUpProd(staticContent.productoJPA.findProductoVentaEntities());
        // backup mesas
        bu.backUPMesa(staticContent.mesasJPA.findMesaEntities());
        bu.commitBackup();

    }
}

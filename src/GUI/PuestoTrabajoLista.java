/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import restManager.persistencia.PuestoTrabajo;
import restManager.persistencia.jpa.staticContent;
import restManager.util.comun;

/**
 *
 * @author Jorge
 */
public class PuestoTrabajoLista extends javax.swing.JDialog {

    /**
     * Creates new form ListaTrabajadores
     */
    private ArrayList<PuestoTrabajo> puestosTrabajo;
    
    public PuestoTrabajoLista(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        puestosTrabajo = new ArrayList<>(staticContent.puestosJPA.findPuestoTrabajoEntities());
        llenarTabla();
        setVisible(true);
        
    }
    
    
    private void llenarTabla() {

        DefaultTableModel model = (DefaultTableModel) jTableListaPuestoTrabajo.getModel();
       

        if (jTableListaPuestoTrabajo.getRowCount() != 0) {
            comun.limpiarTabla(jTableListaPuestoTrabajo);
        }

        for (PuestoTrabajo x : puestosTrabajo) {
            Object[] row = {
                x.getNombrePuesto(),
                x.getSalarioFijo(),
                x.getNivelAcceso(),
                x.getPuestosDisponibles()
            };
             model.addRow(row);
        
    }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRect1 = new org.edisoncor.gui.panel.PanelRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListaPuestoTrabajo = new javax.swing.JTable();
        buttonAgregar = new org.edisoncor.gui.button.ButtonTextDown();
        buttonModificar = new org.edisoncor.gui.button.ButtonTextDown();
        buttonEliminar = new org.edisoncor.gui.button.ButtonTextDown();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista Trabajadores");

        jTableListaPuestoTrabajo.setAutoCreateRowSorter(true);
        jTableListaPuestoTrabajo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Salario fijo", "Nivel de acceso", "Puestos disponibles"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableListaPuestoTrabajo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListaPuestoTrabajoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableListaPuestoTrabajo);

        buttonAgregar.setText("Agregar");
        buttonAgregar.setEnabled(Main.NIVEL_4);
        buttonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarActionPerformed(evt);
            }
        });

        buttonModificar.setText("Modificar");
        buttonModificar.setEnabled(Main.NIVEL_4);
        buttonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificarActionPerformed(evt);
            }
        });

        buttonEliminar.setText("Eliminar");
        buttonEliminar.setEnabled(Main.NIVEL_4);
        buttonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRect1Layout = new javax.swing.GroupLayout(panelRect1);
        panelRect1.setLayout(panelRect1Layout);
        panelRect1Layout.setHorizontalGroup(
            panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRect1Layout.createSequentialGroup()
                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRect1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE))
                    .addGroup(panelRect1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(buttonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124)
                        .addComponent(buttonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addContainerGap())
        );
        panelRect1Layout.setVerticalGroup(
            panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRect1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(panelRect1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableListaPuestoTrabajoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaPuestoTrabajoMouseClicked
        if (evt.getClickCount() == 2) {
            String nombre = (String) jTableListaPuestoTrabajo.
                    getValueAt(jTableListaPuestoTrabajo.getSelectedRow(), 0);
            
           new PuestoTrabajoCrear(this,true, staticContent.puestosJPA.findPuestoTrabajo(nombre));
        }
    }//GEN-LAST:event_jTableListaPuestoTrabajoMouseClicked

    private void buttonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarActionPerformed
         try {
            if (jTableListaPuestoTrabajo.getSelectedRow() != -1) {
//                String usuario = (String) jTableListaPuestoTrabajo.getValueAt(jTableListaPuestoTrabajo.getSelectedRow(), 0);
//                if (JOptionPane.showConfirmDialog(this, "Seguro desea eliminar el trabajador " + usuario ) == JOptionPane.YES_OPTION) {
//                    staticContent.personalJPA.destroy(usuario);
//                    
//                    llenarTabla();
//                }//TODO: falta terminar esto
//                
//                
                
            } else {
                JOptionPane.showMessageDialog(this, "No se ha seleccionado ningun elemento.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el puesto.");
        }

    }//GEN-LAST:event_buttonEliminarActionPerformed

    private void buttonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarActionPerformed
       // PuestoTrabajoJpaController puestoJPA  = new PuestoTrabajoJpaController(Main.emf);
       new PuestoTrabajoCrear(this, true,null);
    }//GEN-LAST:event_buttonAgregarActionPerformed

    private void buttonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarActionPerformed
        try {
            if (jTableListaPuestoTrabajo.getSelectedRow() != -1) {
                String usuario = (String) jTableListaPuestoTrabajo.getValueAt(
                        jTableListaPuestoTrabajo.getSelectedRow(), 0);
               new PuestoTrabajoCrear(this, true, staticContent.puestosJPA.findPuestoTrabajo(usuario));
                
                
            } else {
                JOptionPane.showMessageDialog(this, "No se ha seleccionado ningun elemento.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e.getMessage());
        }
    }//GEN-LAST:event_buttonModificarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonTextDown buttonAgregar;
    private org.edisoncor.gui.button.ButtonTextDown buttonEliminar;
    private org.edisoncor.gui.button.ButtonTextDown buttonModificar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListaPuestoTrabajo;
    private org.edisoncor.gui.panel.PanelRect panelRect1;
    // End of variables declaration//GEN-END:variables
}

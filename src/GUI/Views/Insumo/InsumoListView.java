/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.Insumo;

import GUI.AbstractDialog;
import GUI.CocinaLista;
import GUI.Main;
import java.awt.Dialog;
import java.awt.Frame;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import restManager.controller.AbstractController;
import restManager.controller.insumo.InsumoListController;
import restManager.persistencia.Insumo;
import restManager.persistencia.ProductoInsumo;
import restManager.persistencia.jpa.exceptions.IllegalOrphanException;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.staticContent;

/**
 *
 * @author Jorge
 */
public class InsumoListView extends AbstractDialog {

    private MyJTableModel model;

    public InsumoListView(AbstractController controller, Frame owner, boolean modal) {
        super(DialogType.LIST, controller, owner, modal);
        initComponents();
    }

    public InsumoListView(AbstractController controller, Dialog owner, boolean modal) {
        super(DialogType.LIST, controller, owner, modal);
        initComponents();
    }

    /**
     * Creates new form CocinaLista
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXPanelLista = new org.jdesktop.swingx.JXPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListaInsumos = new javax.swing.JTable();
        jXPanelControles = new org.jdesktop.swingx.JXPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        setTitle(bundle.getString("label_insumo")); // NOI18N
        setMaximumSize(getMaximumSize());
        setMinimumSize(getMinimumSize());
        setPreferredSize(getPreferredSize());
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jXPanelLista.setLayout(new java.awt.BorderLayout());

        jTableListaInsumos.setAutoCreateRowSorter(true);
        jTableListaInsumos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "UM", "En Almacen", "Elaborado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableListaInsumos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTableListaInsumos.setCellSelectionEnabled(false);
        jTableListaInsumos.setRowSelectionAllowed(true);
        jTableListaInsumos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableListaInsumos.setShowGrid(false);
        jTableListaInsumos.getTableHeader().setReorderingAllowed(false);
        jTableListaInsumos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListaInsumosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableListaInsumos);
        jTableListaInsumos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jXPanelLista.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jButtonAdd.setText(bundle.getString("label_agregar")); // NOI18N
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        jXPanelControles.add(jButtonAdd);

        jButtonEdit.setText(bundle.getString("label_editar")); // NOI18N
        jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActionPerformed(evt);
            }
        });
        jXPanelControles.add(jButtonEdit);

        jButtonDelete.setText(bundle.getString("label_eliminar")); // NOI18N
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        jXPanelControles.add(jButtonDelete);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXPanelLista, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                    .addComponent(jXPanelControles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXPanelLista, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jXPanelControles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableListaInsumosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaInsumosMouseClicked
        if (evt.getClickCount() == 2 && Main.NIVEL_4) {
           
        }
    }//GEN-LAST:event_jTableListaInsumosMouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
    }//GEN-LAST:event_formWindowGainedFocus

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        ((InsumoListController) super.getController()).popupInsumoNewView();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        getController().setSelected(model.getInsumoAt(jTableListaInsumos.getSelectedRow()));
        ((InsumoListController) super.getController()).deleteInsumo();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        getController().setSelected(model.getInsumoAt(jTableListaInsumos.getSelectedRow()));
        ((InsumoListController) super.getController()).editInsumo();
    }//GEN-LAST:event_jButtonEditActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListaInsumos;
    private org.jdesktop.swingx.JXPanel jXPanelControles;
    private org.jdesktop.swingx.JXPanel jXPanelLista;
    // End of variables declaration//GEN-END:variables

   
    public void updateView(List<Insumo> items) {
        model = new MyJTableModel(items);
        jTableListaInsumos.setModel(model);
    }

    public class MyJTableModel extends AbstractTableModel {

        private final List<Insumo> items;

        public MyJTableModel(List<Insumo> items) {
            this.items = items;
        }

        @Override
        public int getRowCount() {
            return items.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return items.get(rowIndex).getCodInsumo();
                case 1:
                    return items.get(rowIndex).getNombre();
                case 2:
                    return items.get(rowIndex).getUm();
                case 3:
                    return items.get(rowIndex).getCantidadExistente();
                case 4:
                    return items.get(rowIndex).getElaborado();
                default:
                    return null;

            }
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "Codigo";
                case 1:
                    return "Nombre";
                case 2:
                    return "UM";
                case 3:
                    return "En Almacen";
                case 4:
                    return "Elaborado";
                default:
                    return null;

            }
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 4:
                    return Boolean.class;
                default:
                    return super.getColumnClass(columnIndex);

            }

        }

        public Insumo getInsumoAt(int rowIndex) {
            return items.get(rowIndex);

        }

    }

}

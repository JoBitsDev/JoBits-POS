/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import restManager.persistencia.Cocina;
import restManager.persistencia.Insumo;
import restManager.persistencia.ProductoInsumo;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.jpa.exceptions.IllegalOrphanException;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.staticContent;
import restManager.util.comun;

/**
 *
 * @author Jorge
 */
public class InsumoLista extends javax.swing.JDialog {

    private List<Insumo> insumos = staticContent.insumoJPA.findInsumoEntities();

    /**
     * Creates new form CocinaLista
     */
    public InsumoLista(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        updateDialog();
        setVisible(true);

    }
    
     /**
     * Creates new form CocinaLista
     */
    public InsumoLista(JDialog parent) {
        super(parent);
        initComponents();
        updateDialog();


    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        panelNice1 = new org.edisoncor.gui.panel.PanelNice();
        jXLabelNombre = new org.jdesktop.swingx.JXLabel();
        jXLabelUM = new org.jdesktop.swingx.JXLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jComboBoxUM = new javax.swing.JComboBox<>();
        buttonCrear = new org.edisoncor.gui.button.ButtonAction();
        jLabelCocinas = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListaInsumos = new javax.swing.JTable();
        jButtonAnadir = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonCerrar = new javax.swing.JButton();

        jDialog1.setTitle("Nuevo Insumo");
        jDialog1.setModal(true);
        jDialog1.setResizable(false);

        panelNice1.setBackground(new java.awt.Color(255, 255, 153));

        jXLabelNombre.setText("Nombre");

        jXLabelUM.setText("UM");

        jTextFieldNombre.setText("jTextField1");

        jComboBoxUM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "U", "Kg", "Gr", "Lbs", "Lts" }));

        buttonCrear.setText("Crear");
        buttonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCrearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNice1Layout = new javax.swing.GroupLayout(panelNice1);
        panelNice1.setLayout(panelNice1Layout);
        panelNice1Layout.setHorizontalGroup(
            panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelNice1Layout.createSequentialGroup()
                        .addComponent(jXLabelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jXLabelUM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxUM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelNice1Layout.setVerticalGroup(
            panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxUM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabelUM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(buttonCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jDialog1.getContentPane().add(panelNice1, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cocinas");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jLabelCocinas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelCocinas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCocinas.setText("Insumos");

        jTableListaInsumos.setAutoCreateRowSorter(true);
        jTableListaInsumos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "UM", "Cantidad en Almacen", "Elaborado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Boolean.class
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
        jTableListaInsumos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableListaInsumos.setColumnSelectionAllowed(true);
        jTableListaInsumos.getTableHeader().setReorderingAllowed(false);
        jTableListaInsumos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListaInsumosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableListaInsumos);
        jTableListaInsumos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jButtonAnadir.setText("Añadir ");
        jButtonAnadir.setEnabled(Main.NIVEL_4);
        jButtonAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnadirActionPerformed(evt);
            }
        });

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.setEnabled(Main.NIVEL_4);
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonCerrar.setText("Cerrar");
        jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCocinas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAnadir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEliminar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jButtonCerrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCocinas)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAnadir)
                    .addComponent(jButtonEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCerrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableListaInsumosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaInsumosMouseClicked
        if (evt.getClickCount() == 2 && Main.NIVEL_4) {
            modificarInsumo();
        }
    }//GEN-LAST:event_jTableListaInsumosMouseClicked

    private void jButtonAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnadirActionPerformed
        jDialog1.pack();
        jDialog1.setLocationRelativeTo(this);
        jDialog1.setVisible(true);
       

    }//GEN-LAST:event_jButtonAnadirActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        eliminarInsumo();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed

        dispose();
    }//GEN-LAST:event_jButtonCerrarActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        updateDialog();
    }//GEN-LAST:event_formWindowGainedFocus

    private void buttonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCrearActionPerformed
        añadirInsumo();
    }//GEN-LAST:event_buttonCrearActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonAction buttonCrear;
    private javax.swing.JButton jButtonAnadir;
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JComboBox<String> jComboBoxUM;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabelCocinas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListaInsumos;
    private javax.swing.JTextField jTextFieldNombre;
    private org.jdesktop.swingx.JXLabel jXLabelNombre;
    private org.jdesktop.swingx.JXLabel jXLabelUM;
    private org.edisoncor.gui.panel.PanelNice panelNice1;
    // End of variables declaration//GEN-END:variables

    private void añadirInsumo() {
        try {
            int resp = JOptionPane.showConfirmDialog(this, "Desea crear el insumo");
            if (resp == JOptionPane.YES_OPTION) {

                String nombre = jTextFieldNombre.getText();
                String um = jComboBoxUM.getSelectedItem().toString();
                String cod = getInsumoCod();

                Insumo newInsumo = new Insumo(cod, nombre);
                newInsumo.setUm(um);
                newInsumo.setElaborado(false);

                staticContent.insumoJPA.create(newInsumo);
                JOptionPane.showMessageDialog(this, "Operación realizada exitosamente");
                jDialog1.setVisible(false);
                jTextFieldNombre.setText("Nuevo insumo");
                
            }
        } catch (Exception ex) {
            Logger.getLogger(CocinaLista.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarInsumo() {

        if (jTableListaInsumos.getSelectedRow() != -1) {
            int acc = JOptionPane.showConfirmDialog(this,
                    "Seguro desea eliminar el insumo seleccionado.");
            int cont = 0;
            if (acc == JOptionPane.YES_OPTION) {
                try {
                    String cod = (String) jTableListaInsumos.getValueAt(jTableListaInsumos.getSelectedRow(), 0);
                    List<ProductoInsumo> productosInsumos = staticContent.productoInsumo.findProductoInsumoEntities();
                    for (ProductoInsumo x : productosInsumos) {
                        if (x.getInsumo().getCodInsumo().equals(cod)) {
                            try {
                                staticContent.productoInsumo.destroy(x.getProductoInsumoPK());
                                cont++;
                            } catch (NonexistentEntityException ex) {
                                Logger.getLogger(CocinaLista.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }

                    staticContent.insumoJPA.destroy(cod);

                    JOptionPane.showMessageDialog(null, "Se han quedado sin el insumo " + cont + " platos.");
                } catch (IllegalOrphanException | NonexistentEntityException ex) {
                    Logger.getLogger(CocinaLista.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "No hay insumos para eliminar.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay ningun insumo seleccionada.");
        }
    }

    private void modificarInsumo() {
        throw new UnsupportedOperationException("Operacion en desarrollo");
//        try {
//            
//            int selectedRow = jTableListaInsumos.getSelectedRow();
//
//            if (selectedRow == -1) {
//                throw new NullPointerException("No hay ningun insumo seleccionado.");
//            }
//
//            int acc = JOptionPane.showConfirmDialog(this, "Seguro desea modificar este insumo.");
//
//            if (acc == JOptionPane.YES_OPTION) {
//
//                String oldName = (String) jTableListaInsumos.getValueAt(selectedRow, 1);
//                String oldUm = (String) jTableListaInsumos.getValueAt(selectedRow, 2);
//                String newName = JOptionPane.showInputDialog(null,
//                        "Introduca el nombre nuevo para la cocina " + oldName,
//                        "Modificar cocina",
//                        JOptionPane.OK_CANCEL_OPTION);
//
//                if (newName == null || newName.isEmpty()) {
//                    throw new NullPointerException("El nombre esta vacio \n "
//                            + "por favor rellene en nombre");
//                }
//
////                    if (newName.isEmpty()) {
////                        throw new NullPointerException("El nombre esta vacio \n "
////                                + "por favor rellene en nombre");
////                    }
//                String codigoCocina = (String) jTableListaInsumos.getValueAt(selectedRow, 0);
//                Cocina c = staticContent.cocinaJPA.findCocina(codigoCocina);
//                c.setNombreCocina(newName);
//                staticContent.cocinaJPA.edit(c);
//
//                JOptionPane.showMessageDialog(this, "Operacion realizada exitosamente");
//
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(CocinaLista.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }

    }

    private void updateDialog() {

        insumos = staticContent.insumoJPA.findInsumoEntities();

        ArrayList[] rowData = comun.initArray(new ArrayList[5]);
        for (Insumo x : insumos) {
            rowData[0].add(x.getCodInsumo());
            rowData[1].add(x.getNombre());
            rowData[2].add(x.getUm());
            rowData[3].add(x.getCantidadExistente());
            rowData[4].add(x.getElaborado());

        }
        try {
            comun.UpdateTable(rowData, jTableListaInsumos);
        } catch (Exception ex) {
            Logger.getLogger(CocinaLista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getInsumoCod() {
        String retCode = "In-";
        int sufijo = 1;
        Insumo i = staticContent.insumoJPA.findInsumo(retCode + "" + sufijo);
        while (i != null) {
            sufijo++;
            i = staticContent.insumoJPA.findInsumo(retCode + "" + sufijo);
        }
        return retCode + "" + sufijo;
    }

    public void mostrarDialogParaAddInsumo() {
        jDialog1.pack();
        jDialog1.setLocationRelativeTo(this);
        jDialog1.setVisible(true);
        
    }

}

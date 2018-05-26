/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import restManager.persistencia.Cocina;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.jpa.exceptions.IllegalOrphanException;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.staticContent;
import restManager.util.comun;

/**
 *
 * @author Jorge
 */
public class CocinaLista extends javax.swing.JDialog {

    private List<Cocina> cocinas = staticContent.cocinaJPA.findCocinaEntities();
    /**
     * Creates new form ListaCocinas
     */
    public CocinaLista(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        updateDialog();
        setVisible(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelCocinas = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListaCocinas = new javax.swing.JTable();
        jButtonAnadir = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonCerrar = new javax.swing.JButton();

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
        jLabelCocinas.setText("Cocinas");

        jTableListaCocinas.setAutoCreateRowSorter(true);
        jTableListaCocinas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Cantidad Platos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableListaCocinas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableListaCocinas.getTableHeader().setReorderingAllowed(false);
        jTableListaCocinas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListaCocinasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableListaCocinas);
        jTableListaCocinas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAnadir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                        .addComponent(jButtonEliminar)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jButtonCerrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCocinas)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAnadir)
                    .addComponent(jButtonEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCerrar)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableListaCocinasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaCocinasMouseClicked
       if(evt.getClickCount() == 2 && Main.NIVEL_4){
        modificarCocina();}
    }//GEN-LAST:event_jTableListaCocinasMouseClicked

    private void jButtonAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnadirActionPerformed
        añadirCocina();

    }//GEN-LAST:event_jButtonAnadirActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        eliminarCocina();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed

        dispose();
    }//GEN-LAST:event_jButtonCerrarActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
            updateDialog();        
    }//GEN-LAST:event_formWindowGainedFocus

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnadir;
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JLabel jLabelCocinas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListaCocinas;
    // End of variables declaration//GEN-END:variables

    private void añadirCocina() {
        String nombre = JOptionPane.showInputDialog(null, "Introduca el "
                + "nombre de la cocina", "Nueva cocina", JOptionPane.OK_CANCEL_OPTION);
        if (nombre == null) {
            nombre = "";
        }

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre esta vacio \n "
                    + "por favor rellene en nombre", "Error", JOptionPane.ERROR_MESSAGE);//ecepcion
        } else {
            try {
                String codigo = getCocinaCod();
                staticContent.cocinaJPA.create(new Cocina(codigo, nombre));
            } catch (Exception ex) {
                Logger.getLogger(CocinaLista.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this,
                        "Ocurrio un error creando la cocina. \n" + ex.getMessage());
            }
        }
    }

    private void eliminarCocina() {
        if (jTableListaCocinas.getSelectedRow() != -1) {
            int acc = JOptionPane.showConfirmDialog(this,
                    "Seguro desea eliminar la cocina seleccionada.");
            int cont = 0;
            if (acc == JOptionPane.YES_OPTION) {
                try {
                    String cod = (String) jTableListaCocinas.getValueAt(
                            jTableListaCocinas.getSelectedRow(), 0);
                    List<ProductoVenta> pv = staticContent.productoJPA.findProductoVentaEntities();
                    for (ProductoVenta x : pv) {
                        if (x.getCocinacodCocina().getCodCocina().equals(cod)) {
                            try {
                                x.setCocinacodCocina(null);
                                staticContent.productoJPA.edit(x);
                                cont++;
                            } catch (Exception ex) {
                                Logger.getLogger(CocinaLista.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    
                    staticContent.cocinaJPA.destroy(cod);
                    

                    JOptionPane.showMessageDialog(null, "Se han quedado sin cocina " + cont + " platos.");
                } catch (NonexistentEntityException | IllegalOrphanException ex) {
                    Logger.getLogger(CocinaLista.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(null, "No hay cocinas para eliminar");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay cocinas seleccionadas.");
        }
    }

    private void modificarCocina() {
        try {
            int selectedRow = jTableListaCocinas.getSelectedRow();

            if (selectedRow == -1) {
                throw new NullPointerException("No hay ninguna cocina seleccionada.");
            }

            int acc = JOptionPane.showConfirmDialog(this, "Seguro desea modificar esta cocina.");
            
            if (acc == JOptionPane.YES_OPTION) {

                String oldName = (String) jTableListaCocinas.getValueAt(selectedRow, 1);
                String newName = JOptionPane.showInputDialog(null,
                        "Introduca el nombre nuevo para la cocina " + oldName,
                        "Modificar cocina",
                        JOptionPane.OK_CANCEL_OPTION);

                if (newName == null || newName.isEmpty()) {
                    throw new NullPointerException("El nombre esta vacio \n "
                            + "por favor rellene en nombre");
                }

//                    if (newName.isEmpty()) {
//                        throw new NullPointerException("El nombre esta vacio \n "
//                                + "por favor rellene en nombre");
//                    }

                String codigoCocina = (String) jTableListaCocinas.getValueAt(selectedRow, 0);
                Cocina c = staticContent.cocinaJPA.findCocina(codigoCocina);
                c.setNombreCocina(newName);
                staticContent.cocinaJPA.edit(c);
                
                JOptionPane.showMessageDialog(this, "Operacion realizada exitosamente");

            }
        } catch (Exception ex) {
            Logger.getLogger(CocinaLista.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void updateDialog() {
    
        cocinas = staticContent.cocinaJPA.findCocinaEntities();
        
        ArrayList[] rowData =  comun.initArray(new ArrayList[3]);
        for (Cocina x: cocinas) {
        rowData[0].add(x.getCodCocina());
        rowData[1].add(x.getNombreCocina());
        rowData[2].add(x.getProductoVentaList().size());
        }
        try {
            comun.UpdateTable(rowData, jTableListaCocinas);
        } catch (Exception ex) {
            Logger.getLogger(CocinaLista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getCocinaCod() {
        String retCode = "C-";
        retCode += (staticContent.cocinaJPA.getCocinaCount()+1);
        return retCode;
    }
}

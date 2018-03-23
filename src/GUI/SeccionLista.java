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
import restManager.persistencia.Cocina;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.Seccion;
import restManager.persistencia.jpa.exceptions.IllegalOrphanException;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.exceptions.PreexistingEntityException;
import restManager.persistencia.jpa.staticContent;
import restManager.util.comun;

/**
 *
 * @author Jorge
 */
public class SeccionLista extends javax.swing.JDialog {

    private List<Seccion> secciones = staticContent.seccionJPA.findSeccionEntities();

    /**
     * Creates new form CocinaLista
     */
    public SeccionLista(java.awt.Frame parent, boolean modal) {
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
        jTableListaSecciones = new javax.swing.JTable();
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
        jLabelCocinas.setText("Secciones");

        jTableListaSecciones.setAutoCreateRowSorter(true);
        jTableListaSecciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Cantidad Platos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableListaSecciones.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableListaSecciones.setColumnSelectionAllowed(true);
        jTableListaSecciones.getTableHeader().setReorderingAllowed(false);
        jTableListaSecciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListaSeccionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableListaSecciones);
        jTableListaSecciones.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

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

    private void jTableListaSeccionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaSeccionesMouseClicked
        if (evt.getClickCount() == 2 && Main.NIVEL_4) {
            modificarSeccion();
        }
    }//GEN-LAST:event_jTableListaSeccionesMouseClicked

    private void jButtonAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnadirActionPerformed
        añadirSeccion();

    }//GEN-LAST:event_jButtonAnadirActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        eliminarSeccion();
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
    private javax.swing.JTable jTableListaSecciones;
    // End of variables declaration//GEN-END:variables

    private void añadirSeccion() {
        try {
            String sel = JOptionPane.showInputDialog(this,
                    "Introduzca el nombre de la sección a crear",
                    "Nueva Sección");
            if (sel == null || sel.isEmpty()) {
                throw new NullPointerException("El nombre no puede ser vacio");
            }
            if (sel.length() > 30) {
                throw new IndexOutOfBoundsException("El nombre no puede exceder de 30 caracteres");
            }
            if (staticContent.seccionJPA.findSeccion(sel) != null) {
                throw new PreexistingEntityException("La Sección " + sel + " ya existe en el sistema");
            }

            Seccion s = new Seccion(sel);
            s.setCartacodCarta("Mnu-1");
            staticContent.seccionJPA.create(s);
            updateDialog();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarSeccion() {
        try {
            int selectedRow = jTableListaSecciones.getSelectedRow();
            if (selectedRow == -1) {
                throw new NullPointerException("No hay ninguna sección seleccionada.");
            }

            int acc = JOptionPane.showConfirmDialog(this,
                    "Seguro desea eliminar la sección seleccionada.");
            if (acc == JOptionPane.YES_OPTION) {

                String nombre = (String) jTableListaSecciones.
                        getValueAt(jTableListaSecciones.getSelectedRow(), 0);
                List<ProductoVenta> pv = staticContent.productoJPA.findProductoVentaEntities();
                int cont = 0;
                for (ProductoVenta x : pv) {
                    if (x.getSeccionnombreSeccion().getNombreSeccion().
                            equals(nombre)) {

                        x.setSeccionnombreSeccion(null);
                        staticContent.productoJPA.edit(x);
                        cont++;
                    }
                }

                staticContent.seccionJPA.destroy(nombre);
                JOptionPane.showMessageDialog(null,
                        "Se han quedado sin seccion " + cont + " platos.");

            }
        }catch (Exception ex) {
            Logger.getLogger(SeccionLista.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(),"Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void modificarSeccion() {
        try {
            int selectedRow = jTableListaSecciones.getSelectedRow();

            if (selectedRow == -1) {
                throw new NullPointerException("No hay ninguna sección seleccionada.");
            }

            int acc = JOptionPane.showConfirmDialog(this, "Seguro desea modificar esta sección.");

            if (acc == JOptionPane.YES_OPTION) {

                String oldName = (String) jTableListaSecciones.getValueAt(selectedRow, 0);
                String newName = JOptionPane.showInputDialog(null,
                        "Introduca el nombre nuevo para la sección " + oldName,
                        "Modificar sección",
                        JOptionPane.OK_CANCEL_OPTION);

                if (newName == null || newName.isEmpty()) {
                    throw new NullPointerException("El nombre esta vacio \n "
                            + "por favor rellene en nombre");
                }

                Seccion newSeccion = new Seccion(newName);
                newSeccion.setCartacodCarta("Mnu-1");
                staticContent.seccionJPA.create(newSeccion);
                List<ProductoVenta> pv = staticContent.productoJPA.findProductoVentaEntities();
                for (ProductoVenta x : pv) {
                    if (x.getSeccionnombreSeccion().getNombreSeccion().
                            equals(oldName)) {

                        x.setSeccionnombreSeccion(newSeccion);
                        staticContent.productoJPA.edit(x);
                        
                    }
                }
                staticContent.seccionJPA.destroy(oldName);
                

                JOptionPane.showMessageDialog(this, "Operacion realizada exitosamente");

            }
        } catch (Exception ex) {
            Logger.getLogger(CocinaLista.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void updateDialog() {

        secciones = staticContent.seccionJPA.findSeccionEntities();

        ArrayList[] rowData = comun.initArray(new ArrayList[2]);
        for (Seccion x : secciones) {
            rowData[0].add(x.getNombreSeccion());
            rowData[1].add(x.getProductoVentaList().size());

        }
        try {
            comun.UpdateTable(rowData, jTableListaSecciones);
        } catch (Exception ex) {
            Logger.getLogger(SeccionLista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

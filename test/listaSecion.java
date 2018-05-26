package GUI;


import GUI.*;
import java.awt.Dialog;
import java.awt.Frame;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.Seccion;
import restManager.persistencia.jpa.staticContent;
import restManager.util.comun;

public class listaSecion  extends javax.swing.JDialog{

    private List <Seccion> elements;

    public listaSecion(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        this.elements = staticContent.seccionJPA.findSeccionEntities();
        initComponents();
        setVisible(true);
    }

    public listaSecion(Dialog owner, String title, boolean modal) {
        super(owner, title, modal);
        this.elements = staticContent.seccionJPA.findSeccionEntities();
        initComponents();
        setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMain = new javax.swing.JTable();
        jButtonAdd = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonClose = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButtonMod = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Secciones");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTableMain.setAutoCreateRowSorter(true);
        jTableMain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableMain.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableMain.setColumnSelectionAllowed(true);
        jTableMain.getTableHeader().setReorderingAllowed(false);
        jTableMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMainMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMain);
        jTableMain.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTableMain.getColumnModel().getColumnCount() > 0) {
            jTableMain.getColumnModel().getColumn(0).setResizable(false);
        }

        jButtonAdd.setText("Añadir ");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Eliminar");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonClose.setText("Cerrar");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        jTextField1.setText("Buscar...");
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jButtonMod.setText("Modificar");
        jButtonMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonDelete))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonMod)
                    .addComponent(jButtonClose))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdd)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonMod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonClose)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        String nombre = JOptionPane.showInputDialog(
                null, "Introduca el nombre de la nueva " + this.getTitle(), 
                "Nueva " + this.getTitle(), JOptionPane.OK_CANCEL_OPTION);
        if (nombre == null) {
            nombre = "";
        } else if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre esta vacio, por favor rellene en nombre", "Error", JOptionPane.ERROR_MESSAGE);//exepcion
        } else {
            elements.add(new Seccion(nombre));
            DefaultTableModel model = (DefaultTableModel) jTableMain.getModel();
            Object[] row = {nombre};
            model.addRow(row);
        }


    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed

        dispose();
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        if (jTableMain.getSelectedRow() != -1) {
            if (!elements.isEmpty() || jTableMain.getSelectedRow() == -1) {
                int acc = JOptionPane.showConfirmDialog(null, "Seguro desea eliminar esta seccion.");
                int q = 0;
                if (acc == 0) {
                    String nombre = (String) jTableMain.getValueAt(jTableMain.getSelectedRow(), 0);

                    for (ProductoVenta x : staticContent.productoJPA.findProductoVentaEntities()) {
                        if (x.getSeccionnombreSeccion().getNombreSeccion().equals(nombre)) {
                            x.setSeccionnombreSeccion(null);
                                q++;
                            }
                    }
                    
                    comun.limpiarTabla(jTableMain);
                    ActualizarTabla();
                    JOptionPane.showMessageDialog(null, "Se han quedado sin sección " + q + " platos.");
                  
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hay secciones para eliminar");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay secciones seleccionadas.");
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jTableMainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMainMouseClicked
        String nombre, aux;
        int index;
        int q = 0;
        if (evt.getClickCount() == 2) {
            nombre = (String) jTableMain.getValueAt(jTableMain.getSelectedRow(), 0);
            index = getSecIndex(nombre);
            aux = JOptionPane.showInputDialog(null, "Cambiar nombre de " + nombre + " a: ", "Modificacion", JOptionPane.OK_CANCEL_OPTION);
            if (aux != null && !nombre.equals(aux) && !aux.matches("")) {
                secciones.set(index, new Seccion(aux));
                jTableMain.setValueAt(aux, jTableMain.getSelectedRow(), 0);

                for (int i = 0; i < platos.size(); i++) {
                    if (platos.get(i).getNombreDeSeccion().matches(nombre)) {
                        platos.get(i).setNombreDeSeccion(aux);
                        q++;
                    }

                }
                String Z = "Se le han cambiado la seccion a " + q + " platos.";
                JOptionPane.showMessageDialog(null, Z);

            }

            RE.Guardar_Secciones();
            RE.Guardar_Platos();
        }

    }//GEN-LAST:event_jTableMainMouseClicked

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        String index = jTextField1.getText();
        DefaultTableModel model = (DefaultTableModel) jTableMain.getModel();
        LimpiarTabla();
        for (int i = 0; i < secciones.size(); i++) {
            Object[] row = {secciones.get(i).getNombre()};
            if ((secciones.get(i)).getNombre().contains(true, 0, index, 0, index.length() - 1)) {
                model.addRow(row);
            }
        }

    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        jTextField1.setText("");
        jTextField1.setCaretPosition(0);
    }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        jTextField1.setText("Buscar...");
    }//GEN-LAST:event_jTextField1FocusLost

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        RE.Guardar_Secciones();
        RE.Guardar_Platos();
    }//GEN-LAST:event_formWindowClosing

    private void jButtonModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonModActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonMod;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMain;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    
    private void ActualizarTabla() {
        for (int i = 0; i < elements.size(); i++) {
            DefaultTableModel model = (DefaultTableModel) jTableMain.getModel();
            Object[] row = {elements.get(i).getNombreSeccion()};
            model.addRow(row);

        }
    }

  

}

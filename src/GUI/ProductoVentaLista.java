  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import restManager.persistencia.Cocina;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.Seccion;
import restManager.persistencia.jpa.exceptions.IllegalOrphanException;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.staticContent;
import restManager.util.LoadingWindow;
import restManager.util.comun;

/**
 *
 * @author Jorge
 */
public class ProductoVentaLista extends javax.swing.JDialog {

    /**
     * Creates new form ProductoVentaLista
     */
    private ArrayList<ProductoVenta> listaProducto;
    private ArrayList<ProductoVenta> selectedProds = new ArrayList<>();
    private int cantVisibles = 0;

    public ProductoVentaLista(Main parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loading();
        setVisible(true);

    }

    private void updateFrame() {
        staticContent.EMF.getCache().evict(ProductoVenta.class);
        listaProducto = new ArrayList<>(staticContent.productoJPA.findProductoVentaEntities());
        labelCANTPROD.setText(listaProducto.size() + " Platos");
        Collections.sort(listaProducto, (o1, o2) -> {
            return o1.getPCod().compareTo(o2.getPCod());
        });
        cantVisibles = 0;
        updateTable();
        RellenarFrame(staticContent.cocinaJPA.findCocinaEntities(),
                staticContent.seccionJPA.findSeccionEntities());
    }

    private void updateTable() {
        
        ArrayList[] rowData = comun.initArray(new ArrayList[7]);
        
        for (ProductoVenta x : listaProducto) {
            rowData[0].add(false);
            rowData[1].add(x.getPCod());
            rowData[2].add(x.getNombre());
            rowData[3].add(x.getPrecioVenta());
            if(x.getSeccionnombreSeccion() != null){
                rowData[4].add(x.getSeccionnombreSeccion().getNombreSeccion());
            }
            else{
                rowData[4].add(null);
            }
            if(x.getCocinacodCocina() != null){
                rowData[5].add(x.getCocinacodCocina().getNombreCocina());
            }
            else{
                rowData[5].add(null);
            }
            rowData[6].add(x.getVisible());
            
            if (x.getVisible()) {
                cantVisibles++;
            }
        }
        try {
            comun.UpdateTable(rowData, jTableMAIN);
            buscarEnTabla();
        } catch (Exception ex) {
            Logger.getLogger(ProductoVentaLista.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.labelCANTVISIBLE.setText("(" + cantVisibles + " Visibles)");
    }

    private void RellenarFrame(List<Cocina> cocinas, List<Seccion> secciones) {
        for (Cocina x : cocinas) {
            jComboBoxCocinas.addItem(x.getNombreCocina());
        }

        for (Seccion x : secciones) {
            jComboBoxSecciones.addItem(x.getNombreSeccion());
        }

    }

    private void ModificarMultiple(ArrayList<ProductoVenta> selecteds) throws Exception {
        String cocina = null, seccion = null;
        if (jCheckBox5.isSelected()) {
            if (!jComboBoxCocinas.getSelectedItem().equals("Indefinido")) {
                cocina = (String) jComboBoxCocinas.getSelectedItem();
            } else {
                throw new NullPointerException("Valor de cocina indefinido");
            }
        }

        if (jCheckBox6.isSelected()) {
            if (!jComboBoxSecciones.getSelectedItem().equals("Indefinido")) {
                seccion = (String) jComboBoxSecciones.getSelectedItem();
            } else {
                throw new NullPointerException("Valor de seccion indefinido");
            }
        }

        for (ProductoVenta x : selecteds) {
            if (cocina != null) {
                x.setCocinacodCocina(staticContent.cocinaJPA.findCocina(cocina));
            }
            if (seccion != null) {
                x.setSeccionnombreSeccion(staticContent.seccionJPA.findSeccion(seccion));
            }
            staticContent.productoJPA.edit(x);
        }

    }

    private void loading() {

        LoadingWindow.show(this);

        SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {

            @Override
            protected Boolean doInBackground() throws Exception {

                updateFrame();
                return true;
            }

            @Override
            protected void done() {
                boolean status;
                try {
                    status = get();
                    LoadingWindow.hide();
                } catch (Exception ex) {
                    System.out.println("ERROR"+ ex.getMessage());
                    LoadingWindow.hide();
                }
            }
        };
        worker.execute();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        panelRound1 = new org.edisoncor.gui.panel.PanelRound();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jComboBoxCocinas = new javax.swing.JComboBox();
        jComboBoxSecciones = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        panelRect2 = new org.edisoncor.gui.panel.PanelRect();
        labelCANTPROD = new org.edisoncor.gui.label.LabelMetric();
        textFieldBUSCAR = new org.edisoncor.gui.textField.TextFieldRectIcon();
        jCheckBoxSELECTALL = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMAIN = new javax.swing.JTable();
        buttonADDPROD = new org.edisoncor.gui.button.ButtonTextDown();
        buttonREMOVEPROD = new org.edisoncor.gui.button.ButtonTextDown();
        buttonEDITPROD = new org.edisoncor.gui.button.ButtonTextDown();
        jCheckBoxVistas = new javax.swing.JCheckBox();
        labelCANTVISIBLE = new org.edisoncor.gui.label.LabelMetric();

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Modificacion Conjunta");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Cambiar Cocina");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Cambiar Seccion");

        jButton6.setText("Modificar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setText("Cancelar");

        jComboBoxCocinas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Indefinido" }));

        jComboBoxSecciones.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Indefinido" }));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("");

        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelRound1Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(jButton8))
                                    .addGroup(panelRound1Layout.createSequentialGroup()
                                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCheckBox5, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jCheckBox6))
                                        .addGap(18, 18, 18)
                                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxSecciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxCocinas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                                        .addComponent(jButton6)
                                        .addGap(19, 19, 19))))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(11, 11, 11))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(19, 19, 19)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jComboBoxCocinas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jComboBoxSecciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 19, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton8))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
            .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 203, Short.MAX_VALUE)
            .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista Productos");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        labelCANTPROD.setText("0 Platos");

        textFieldBUSCAR.setText("Buscar...");
        textFieldBUSCAR.setFont(new java.awt.Font("BlairMdITC TT", 0, 13)); // NOI18N
        textFieldBUSCAR.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textFieldBUSCARFocusGained(evt);
            }
        });
        textFieldBUSCAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldBUSCARActionPerformed(evt);
            }
        });
        textFieldBUSCAR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textFieldBUSCARKeyTyped(evt);
            }
        });

        jCheckBoxSELECTALL.setFont(new java.awt.Font("BlairMdITC TT", 0, 13)); // NOI18N
        jCheckBoxSELECTALL.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxSELECTALL.setText("Seleccionar Todo");
        jCheckBoxSELECTALL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSELECTALLActionPerformed(evt);
            }
        });

        jTableMAIN.setAutoCreateRowSorter(true);
        jTableMAIN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "ID", "Nombre", "Precio Venta", "Sección", "Cocina", "Visible"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableMAIN.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableMAIN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMAINMouseClicked(evt);
            }
        });
        jTableMAIN.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableMAINPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMAIN);
        if (jTableMAIN.getColumnModel().getColumnCount() > 0) {
            jTableMAIN.getColumnModel().getColumn(0).setMinWidth(2);
        }

        buttonADDPROD.setText("Agregar Producto");
        buttonADDPROD.setEnabled(Main.NIVEL_2);
        buttonADDPROD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonADDPRODActionPerformed(evt);
            }
        });

        buttonREMOVEPROD.setText("Eliminar Producto");
        buttonREMOVEPROD.setEnabled(Main.NIVEL_4);
        buttonREMOVEPROD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonREMOVEPRODActionPerformed(evt);
            }
        });

        buttonEDITPROD.setText("Editar Producto");
        buttonEDITPROD.setEnabled(Main.NIVEL_4);
        buttonEDITPROD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEDITPRODActionPerformed(evt);
            }
        });

        jCheckBoxVistas.setFont(new java.awt.Font("BlairMdITC TT", 0, 13)); // NOI18N
        jCheckBoxVistas.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxVistas.setText("Hacer Visible Todo");
        jCheckBoxVistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxVistasActionPerformed(evt);
            }
        });

        labelCANTVISIBLE.setText("(0 Visibles)");

        javax.swing.GroupLayout panelRect2Layout = new javax.swing.GroupLayout(panelRect2);
        panelRect2.setLayout(panelRect2Layout);
        panelRect2Layout.setHorizontalGroup(
            panelRect2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRect2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelRect2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRect2Layout.createSequentialGroup()
                        .addComponent(buttonADDPROD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonREMOVEPROD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                    .addGroup(panelRect2Layout.createSequentialGroup()
                        .addComponent(jCheckBoxSELECTALL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBoxVistas))
                    .addGroup(panelRect2Layout.createSequentialGroup()
                        .addComponent(labelCANTPROD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelCANTVISIBLE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textFieldBUSCAR, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRect2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonEDITPROD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRect2Layout.setVerticalGroup(
            panelRect2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRect2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelRect2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCANTPROD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldBUSCAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCANTVISIBLE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(panelRect2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxSELECTALL)
                    .addComponent(jCheckBoxVistas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panelRect2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonADDPROD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonREMOVEPROD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonEDITPROD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        getContentPane().add(panelRect2, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldBUSCARFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textFieldBUSCARFocusGained
        textFieldBUSCAR.setText("");
        textFieldBUSCAR.setCaretPosition(0);
        buscarEnTabla();
    }//GEN-LAST:event_textFieldBUSCARFocusGained

    private void textFieldBUSCARKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldBUSCARKeyTyped
        buscarEnTabla();  
    }//GEN-LAST:event_textFieldBUSCARKeyTyped

    private void jCheckBoxSELECTALLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSELECTALLActionPerformed
        if (jCheckBoxSELECTALL.isSelected()) {
            for (int i = 0; i < jTableMAIN.getRowCount(); i++) {
                jTableMAIN.setValueAt(true, i, 0);

            }
        } else {
            for (int i = 0; i < jTableMAIN.getRowCount(); i++) {
                jTableMAIN.setValueAt(false, i, 0);

            }
        }
    }//GEN-LAST:event_jCheckBoxSELECTALLActionPerformed

    private void textFieldBUSCARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldBUSCARActionPerformed

    }//GEN-LAST:event_textFieldBUSCARActionPerformed

    private void buttonADDPRODActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonADDPRODActionPerformed
        new ProductoVentaCrearEditar(this, true, null);
    }//GEN-LAST:event_buttonADDPRODActionPerformed

    private void buttonEDITPRODActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEDITPRODActionPerformed
        editarProductos();
    }//GEN-LAST:event_buttonEDITPRODActionPerformed

    private void buttonREMOVEPRODActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonREMOVEPRODActionPerformed
        eliminarPlatos(jTableMAIN);
    }//GEN-LAST:event_buttonREMOVEPRODActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            ModificarMultiple(selectedProds);
            JOptionPane.showMessageDialog(null, "Se han modificado los platos correctamente ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void jTableMAINPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableMAINPropertyChange
        if (evt.getPropertyName().matches("tableCellEditor")) {
            if (jTableMAIN.getEditingColumn() == 6) {
                int row = jTableMAIN.getEditingRow();
                String cod = (String) jTableMAIN.getValueAt(row, 1);
                boolean visible = (boolean) jTableMAIN.getValueAt(row, 6);

                updateVisible(row, cod, visible);

                this.labelCANTVISIBLE.setText("(" + cantVisibles + " Visibles)");
            }
        }
    }//GEN-LAST:event_jTableMAINPropertyChange

    private void jCheckBoxVistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxVistasActionPerformed
        for (int i = 0; i < jTableMAIN.getRowCount(); i++) {
            jTableMAIN.setValueAt(jCheckBoxVistas.isSelected(), i, 6);
            updateVisible(i, (String) jTableMAIN.getValueAt(i, 1),
                    jCheckBoxVistas.isSelected());
        }

        this.labelCANTVISIBLE.setText("(" + cantVisibles + " Visibles)");
    }//GEN-LAST:event_jCheckBoxVistasActionPerformed

    private void jTableMAINMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMAINMouseClicked
     if(evt.getClickCount()==2 && Main.NIVEL_4){
         int sel = jTableMAIN.getSelectedRow();
         if(sel!=-1){
             String cod = (String) jTableMAIN.getValueAt(sel, 1);
             new ProductoVentaCrearEditar(this, rootPaneCheckingEnabled,
                     staticContent.productoJPA.findProductoVenta(cod));
         }
     }
    }//GEN-LAST:event_jTableMAINMouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
      update();
    }//GEN-LAST:event_formWindowGainedFocus


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonTextDown buttonADDPROD;
    private org.edisoncor.gui.button.ButtonTextDown buttonEDITPROD;
    private org.edisoncor.gui.button.ButtonTextDown buttonREMOVEPROD;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBoxSELECTALL;
    private javax.swing.JCheckBox jCheckBoxVistas;
    private javax.swing.JComboBox jComboBoxCocinas;
    private javax.swing.JComboBox jComboBoxSecciones;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMAIN;
    private org.edisoncor.gui.label.LabelMetric labelCANTPROD;
    private org.edisoncor.gui.label.LabelMetric labelCANTVISIBLE;
    private org.edisoncor.gui.panel.PanelRect panelRect2;
    private org.edisoncor.gui.panel.PanelRound panelRound1;
    private org.edisoncor.gui.textField.TextFieldRectIcon textFieldBUSCAR;
    // End of variables declaration//GEN-END:variables

    private void updateVisible(int row, String cod, boolean visible) {
        
        ProductoVenta p = staticContent.productoJPA.findProductoVenta(cod);
        boolean oldState = p.getVisible();
        p.setVisible(visible);

        try {
            staticContent.productoJPA.edit(p);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProductoVentaLista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProductoVentaLista.class.getName()).log(Level.SEVERE, null, ex);
        }

        listaProducto.set(Integer.valueOf(cod.substring(3)) - 1, p);

        if (oldState != visible) {
            if(visible){
            cantVisibles++;
            } 
            else {
            cantVisibles--;}
        }
        
    }

    private void eliminarPlatos(JTable jTableMAIN) {
    int cont = 0, y, index = -1;
        for (int i = 0; i < jTableMAIN.getRowCount(); i++) {
            if ((boolean) jTableMAIN.getValueAt(i, 0)) {
                String pcod = (String) jTableMAIN.getValueAt(jTableMAIN.getSelectedRow(), 1);
                selectedProds.add(staticContent.productoJPA.findProductoVenta(pcod));
            }
        }
        if (selectedProds.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay platos seleccionados para eliminar ");
            return;
        }
        if(selectedProds.size() == 1){
            y = JOptionPane.showConfirmDialog(null,
                    "Seguro que desea eliminar "+ selectedProds.get(0).getNombre()
                            +"\n de la lista de platos");
        }
        else{
         y = JOptionPane.showConfirmDialog(null, 
                 "Seguro que desea eliminar " + selectedProds.size() + " platos.");
           
        }
        
        if (y == JOptionPane.YES_OPTION) {
            for (ProductoVenta x : selectedProds) {
                try {
                    staticContent.productoJPA.destroy(x.getPCod());
                    listaProducto.remove(x);
                    JOptionPane.showMessageDialog(null, "Acción realizada correctamente.");
                } catch (IllegalOrphanException | NonexistentEntityException ex) {
                    Logger.getLogger(ProductoVentaLista.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
            
        }
        //JOptionPane.showMessageDialog(null, "Operación cancelada.");
        selectedProds.clear();
        
}   

    private void editarProductos() {
    int cont = 0, y;
        ArrayList<ProductoVenta> prods = new ArrayList<>();
        for (int i = 0; i < jTableMAIN.getRowCount(); i++) {
            if ((boolean) jTableMAIN.getValueAt(i, 0)) {
                String pcod = (String) jTableMAIN.getValueAt(i, 1);
                prods.add(staticContent.productoJPA.findProductoVenta(pcod));
                cont++;

            }

        }
        if (cont > 1) {
            jDialog1.pack();
            jLabel12.setText(cont + " Platos");
            jDialog1.setVisible(true);
        } else if (jTableMAIN.getSelectedRow() != -1) {

            String pcod = (String) jTableMAIN.getValueAt(jTableMAIN.getSelectedRow(), 1);
            ProductoVenta p = (staticContent.productoJPA.findProductoVenta(pcod));

            new ProductoVentaCrearEditar(this, true, p);

        } else {
            JOptionPane.showMessageDialog(null, "No hay elementos seleccionados para editar");
        }
 }

    private void buscarEnTabla() {
       String aux = textFieldBUSCAR.getText();
        DefaultTableModel model = (DefaultTableModel) jTableMAIN.getModel();
        comun.limpiarTabla(jTableMAIN);
        listaProducto.forEach((ProductoVenta p) -> {
            Object[] row = {
                false,
                p.getPCod(),
                p.getNombre(),
                p.getPrecioVenta(),
                p.getSeccionnombreSeccion().getNombreSeccion(),
                p.getCocinacodCocina().getNombreCocina(),
                p.getVisible()
            };
            if (p.getNombre().toLowerCase().contains(aux.toLowerCase())) {
                model.addRow(row);
            }
        });
    }
    
    private void update(){
        staticContent.clearConnCache();
        loading();
    }

}

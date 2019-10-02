/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.Almacen;

import GUI.Views.AbstractDetailView;
import GUI.Views.AbstractView;
import GUI.Views.util.AbstractCrossReferenePanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.AbstractTableModel;
import javax.xml.ws.handler.MessageContext;
import mdlaf.components.tabbedpane.MaterialTabbedPaneUI;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractDialogController;
import restManager.controller.Controller;
import restManager.controller.almacen.AlmacenManageController;
import restManager.controller.almacen.TransaccionDetailController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.ValidatingException;
import restManager.persistencia.Almacen;
import restManager.persistencia.Cocina;
import restManager.persistencia.Insumo;
import restManager.persistencia.InsumoAlmacen;
import restManager.persistencia.InsumoAlmacenPK;
import restManager.persistencia.models.InsumoAlmacenDAO;
import restManager.resources.R;
import restManager.util.RestManagerAbstractTableModel;
import restManager.util.RestManagerComboBoxModel;
import restManager.util.utils;

/**
 *
 * @author Jorge
 */
public class AlmacenEditView extends AbstractDetailView<Almacen> {

    /**
     * Creates new form AlmacenMain
     *
     * @param parent
     * @param modal
     */
    AbstractCrossReferenePanel<InsumoAlmacen, Insumo> model;
    final Color elaboracionColor = new Color(255, 255, 204);

    public AlmacenEditView(AbstractDetailController<Almacen> controller, AbstractView owner, Almacen instance) {
        super(instance, DialogType.FULL_SCREEN, controller, owner);
        initComponents();
        buttonGroup1.add(jRadioButtonSalida);
        buttonGroup1.add(jRadioButtonEntrada);
        buttonGroup1.add(jRadioButtonRebaja);
        buttonGroup1.add(jRadioButtonTraspaso);
        jComboBoxPuntoElab.setModel(new RestManagerComboBoxModel<>(getController().getCocinaList()));
        jComboBoxAlmacen.setModel(new RestManagerComboBoxModel<>(getController().getItems()));
        jComboBoxPuntoElab.setSelectedIndex(0);
        jComboBoxAlmacen.setSelectedIndex(0);

    }

    @Override
    public void updateView() {
        if (model != null) {
            model.getHandler().getTableModel().setItems(getController().getInsumoAlmacenList(getInstance()));
        } else {
            model = new AbstractCrossReferenePanel<InsumoAlmacen, Insumo>("Insumos", getController().getInsumoList()) {
                @Override
                public RestManagerAbstractTableModel<InsumoAlmacen> getTableModel() {
                    return new RestManagerAbstractTableModel<InsumoAlmacen>(getController().getInsumoAlmacenList(getInstance()), getjTableCrossReference()) {
                        @Override
                        public int getColumnCount() {
                            return 6;
                        }

                        @Override
                        public Object getValueAt(int rowIndex, int columnIndex) {
                            switch (columnIndex) {
                                case 0:
                                    return this.items.get(rowIndex).getInsumo().getCodInsumo();
                                case 2:
                                    return this.items.get(rowIndex).getInsumo().getNombre();
                                case 1:
                                    return this.items.get(rowIndex).getInsumo().getUm();
                                case 3:
                                    return this.items.get(rowIndex).getCantidad();
                                case 4:
                                    return this.items.get(rowIndex).getCantidad() != 0
                                            ? utils.setDosLugaresDecimales(this.items.get(rowIndex).getValorMonetario() / this.items.get(rowIndex).getCantidad()) : 0;
                                case 5:
                                    return utils.setDosLugaresDecimales(this.items.get(rowIndex).getValorMonetario());
                                default:
                                    return null;

                            }
                        }

                        @Override
                        public String getColumnName(int column) {
                            switch (column) {
                                case 0:
                                    return "Codigo";
                                case 2:
                                    return "Nombre";
                                case 1:
                                    return "UM";
                                case 3:
                                    return "En Almacen";
                                case 4:
                                    return "Costo Unitario (Prom)";
                                case 5:
                                    return "Valor Total";
                                default:
                                    return null;
                            }
                        }
                    };
                }

                @Override
                public InsumoAlmacen transformK_T(Insumo selected) {
                    InsumoAlmacenPK newInsumoPK = new InsumoAlmacenPK(selected.getCodInsumo(), getInstance().getCodAlmacen());
                    InsumoAlmacen newInsumo = new InsumoAlmacen(newInsumoPK);
                    newInsumo.setAlmacen(getInstance());
                    newInsumo.setCantidad((float) 0);
                    newInsumo.setInsumo(selected);
                    newInsumo.setValorMonetario((float) 0);
                    InsumoAlmacenDAO.getInstance().create(newInsumo);
                    return newInsumo;
                }

                @Override
                public void removeObjectSelected() {
                    getController().removeInsumoFromStorage(getHandler().getTableModel().getObjectAtSelectedRow());
                }

            };

        }

        jXLabelValorTotal.setText(utils.setDosLugaresDecimales(getController().getInstance().getValorMonetario()));
        jTabbedPane1.setUI(new MaterialTabbedPaneUI());
    }

    @Override
    public void fetchComponentData() {
        jPanelTabla.add(model);
        jLabelNombreAlmacen.setText(getInstance().getNombre());
        if (getInstance().getCentroElaboracion()) {
            jCheckBox1.setSelected(true);
            jLabelNombreAlmacen.setForeground(elaboracionColor);
        }
    }

    @Override
    public AlmacenManageController getController() {
        return (AlmacenManageController) super.getController(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabelNombreAlmacen = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelTransaccion = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jSpinnerCantidad = new javax.swing.JSpinner();
        jPanel8 = new javax.swing.JPanel();
        jPanelEntrada = new javax.swing.JPanel();
        jRadioButtonEntrada = new javax.swing.JRadioButton();
        jPanel10 = new javax.swing.JPanel();
        jSpinnerMonto = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jPanelTraspaso = new javax.swing.JPanel();
        jRadioButtonTraspaso = new javax.swing.JRadioButton();
        jComboBoxAlmacen = new javax.swing.JComboBox<>();
        jPaneldestino = new javax.swing.JPanel();
        jRadioButtonSalida = new javax.swing.JRadioButton();
        jComboBoxPuntoElab = new javax.swing.JComboBox<>();
        jPanelRazon = new javax.swing.JPanel();
        jRadioButtonRebaja = new javax.swing.JRadioButton();
        jTextFieldRebaja = new javax.swing.JTextField();
        jPanelTransformacion = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jButtonConfirmar = new javax.swing.JButton();
        jPanelTabla = new javax.swing.JPanel();
        jXPanelControles = new org.jdesktop.swingx.JXPanel();
        jPanel1 = new javax.swing.JPanel();
        jXLabelTotalAlmacen = new org.jdesktop.swingx.JXLabel();
        jXLabelValorTotal = new org.jdesktop.swingx.JXLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonDarReporte = new javax.swing.JButton();
        jButtonModificarStock = new javax.swing.JButton();
        jButtonVerFichasEntrada = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFont(getFont());
        setMinimumSize(getMinimumSize());
        setUndecorated(true);
        getContentPane().setLayout(new java.awt.BorderLayout(0, 5));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(new org.edisoncor.gui.util.DropShadowBorder());
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabelNombreAlmacen.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabelNombreAlmacen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNombreAlmacen.setText("<Nombre Almacen>");
        jPanel3.add(jLabelNombreAlmacen, java.awt.BorderLayout.CENTER);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/botonlogout.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, java.awt.BorderLayout.WEST);

        jCheckBox1.setText("Centro Elaboracion");
        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jCheckBox1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckBox1, java.awt.BorderLayout.LINE_END);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new org.edisoncor.gui.util.DropShadowBorder(), "Operaciones"));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanelTransaccion.setOpaque(false);
        jPanelTransaccion.setLayout(new java.awt.BorderLayout());

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jSpinnerCantidad.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(1000000.0f), Float.valueOf(1.0f)));
        jSpinnerCantidad.setToolTipText("Cantidad");
        jSpinnerCantidad.setBorder(javax.swing.BorderFactory.createTitledBorder("Cantidad"));
        jPanel7.add(jSpinnerCantidad);

        jPanelTransaccion.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        jPanel8.setLayout(new java.awt.GridLayout(4, 0));

        jPanelEntrada.setLayout(new java.awt.BorderLayout());

        jRadioButtonEntrada.setSelected(true);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jRadioButtonEntrada.setText(bundle.getString("label_entrada")); // NOI18N
        jRadioButtonEntrada.setToolTipText("");
        jRadioButtonEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonEntradaActionPerformed(evt);
            }
        });
        jPanelEntrada.add(jRadioButtonEntrada, java.awt.BorderLayout.CENTER);

        jSpinnerMonto.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(1000000.0f), Float.valueOf(1.0f)));
        jSpinnerMonto.setToolTipText("Monto");
        jPanel10.add(jSpinnerMonto);

        jLabel1.setText(R.COIN_SUFFIX);
        jPanel10.add(jLabel1);

        jPanelEntrada.add(jPanel10, java.awt.BorderLayout.SOUTH);

        jPanel8.add(jPanelEntrada);

        jPanelTraspaso.setLayout(new java.awt.BorderLayout());

        jRadioButtonTraspaso.setText(bundle.getString("label_traspaso")); // NOI18N
        jRadioButtonTraspaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTraspasoActionPerformed(evt);
            }
        });
        jPanelTraspaso.add(jRadioButtonTraspaso, java.awt.BorderLayout.CENTER);

        jComboBoxAlmacen.setEnabled(false);
        jComboBoxAlmacen.setMinimumSize(new java.awt.Dimension(150, 27));
        jComboBoxAlmacen.setPreferredSize(new java.awt.Dimension(150, 27));
        jPanelTraspaso.add(jComboBoxAlmacen, java.awt.BorderLayout.SOUTH);

        jPanel8.add(jPanelTraspaso);

        jPaneldestino.setMinimumSize(new java.awt.Dimension(200, 61));
        jPaneldestino.setLayout(new java.awt.BorderLayout());

        jRadioButtonSalida.setText(bundle.getString("label_salida")); // NOI18N
        jRadioButtonSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSalidaActionPerformed(evt);
            }
        });
        jPaneldestino.add(jRadioButtonSalida, java.awt.BorderLayout.CENTER);

        jComboBoxPuntoElab.setEnabled(false);
        jComboBoxPuntoElab.setMinimumSize(new java.awt.Dimension(150, 27));
        jComboBoxPuntoElab.setPreferredSize(new java.awt.Dimension(150, 27));
        jPaneldestino.add(jComboBoxPuntoElab, java.awt.BorderLayout.SOUTH);

        jPanel8.add(jPaneldestino);

        jPanelRazon.setLayout(new java.awt.BorderLayout());

        jRadioButtonRebaja.setText(bundle.getString("label_rebaja")); // NOI18N
        jRadioButtonRebaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonRebajaActionPerformed(evt);
            }
        });
        jPanelRazon.add(jRadioButtonRebaja, java.awt.BorderLayout.CENTER);

        jTextFieldRebaja.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jTextFieldRebaja.setToolTipText("Razon de rebaja");
        jTextFieldRebaja.setEnabled(false);
        jTextFieldRebaja.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanelRazon.add(jTextFieldRebaja, java.awt.BorderLayout.SOUTH);

        jPanel8.add(jPanelRazon);

        jPanelTransaccion.add(jPanel8, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Transaccion", jPanelTransaccion);
        jTabbedPane1.addTab("Transformacion", jPanelTransformacion);

        jPanel6.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
        flowLayout1.setAlignOnBaseline(true);
        jPanel9.setLayout(flowLayout1);

        jButtonConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/confirmar.png"))); // NOI18N
        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.setToolTipText(bundle.getString("label_confirmar")); // NOI18N
        jButtonConfirmar.setBorderPainted(false);
        jButtonConfirmar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonConfirmar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarActionPerformed(evt);
            }
        });
        jPanel9.add(jButtonConfirmar);

        jPanel6.add(jPanel9, java.awt.BorderLayout.SOUTH);

        jPanel5.add(jPanel6, java.awt.BorderLayout.EAST);

        jPanelTabla.setLayout(new java.awt.BorderLayout());
        jPanel5.add(jPanelTabla, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        jXPanelControles.setBackground(new java.awt.Color(204, 204, 204));
        jXPanelControles.setBorder(new org.pushingpixels.lafwidget.utils.ShadowPopupBorder());
        jXPanelControles.setLayout(new javax.swing.BoxLayout(jXPanelControles, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.BorderLayout());

        jXLabelTotalAlmacen.setText(bundle.getString("label_valor_almacen")); // NOI18N
        jXLabelTotalAlmacen.setFont(new java.awt.Font("Apple Braille", 0, 24)); // NOI18N
        jPanel1.add(jXLabelTotalAlmacen, java.awt.BorderLayout.WEST);

        jXLabelValorTotal.setText("0.00 CUC");
        jXLabelValorTotal.setFont(new java.awt.Font("Apple Braille", 0, 24)); // NOI18N
        jPanel1.add(jXLabelValorTotal, java.awt.BorderLayout.EAST);

        jXPanelControles.add(jPanel1);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        jButtonDarReporte.setText(bundle.getString("label_reporte")); // NOI18N
        jButtonDarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDarReporteActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonDarReporte);

        jButtonModificarStock.setText(bundle.getString("label_mod_stock")); // NOI18N
        jButtonModificarStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonModificarStockMouseClicked(evt);
            }
        });
        jButtonModificarStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarStockActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonModificarStock);

        jButtonVerFichasEntrada.setText(bundle.getString("label_transacciones")); // NOI18N
        jButtonVerFichasEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerFichasEntradaActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonVerFichasEntrada);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButton2.setText("Imprimir Resumen");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jXPanelControles.add(jPanel2);

        getContentPane().add(jXPanelControles, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDarReporteActionPerformed
        getController().imprimirReporteParaCompras(getInstance());
    }//GEN-LAST:event_jButtonDarReporteActionPerformed

    private void jButtonModificarStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarStockActionPerformed

    }//GEN-LAST:event_jButtonModificarStockActionPerformed

    private void jButtonVerFichasEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerFichasEntradaActionPerformed
        getController().verTransacciones(getInstance());
    }//GEN-LAST:event_jButtonVerFichasEntradaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButtonEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonEntradaActionPerformed
        onCheckedCheckBox(CheckBoxType.ENTRADA);
    }//GEN-LAST:event_jRadioButtonEntradaActionPerformed

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed
        ejecutarTransaccion();
    }//GEN-LAST:event_jButtonConfirmarActionPerformed

    private void jRadioButtonSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSalidaActionPerformed
        onCheckedCheckBox(CheckBoxType.SALIDA);
    }//GEN-LAST:event_jRadioButtonSalidaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        getController().imprimirResumenAlmacen(getInstance());        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButtonRebajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonRebajaActionPerformed
        onCheckedCheckBox(CheckBoxType.REBAJA);
    }//GEN-LAST:event_jRadioButtonRebajaActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            jLabelNombreAlmacen.setForeground(elaboracionColor);
        } else {
            jLabelNombreAlmacen.setForeground(Color.BLACK);
        }
        getController().setCentroElaboracion(jCheckBox1.isSelected());
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButtonModificarStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonModificarStockMouseClicked
        if (evt.getClickCount() == 2) {
            getController().modificarStock(model.getTableModel().getObjectAtSelectedRow().getInsumo());
        }
    }//GEN-LAST:event_jButtonModificarStockMouseClicked

    private void jRadioButtonTraspasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTraspasoActionPerformed
        onCheckedCheckBox(CheckBoxType.TRASPASO);
    }//GEN-LAST:event_jRadioButtonTraspasoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JButton jButtonDarReporte;
    private javax.swing.JButton jButtonModificarStock;
    private javax.swing.JButton jButtonVerFichasEntrada;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<Almacen> jComboBoxAlmacen;
    private javax.swing.JComboBox<Cocina> jComboBoxPuntoElab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelNombreAlmacen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelEntrada;
    private javax.swing.JPanel jPanelRazon;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JPanel jPanelTransaccion;
    private javax.swing.JPanel jPanelTransformacion;
    private javax.swing.JPanel jPanelTraspaso;
    private javax.swing.JPanel jPaneldestino;
    private javax.swing.JRadioButton jRadioButtonEntrada;
    private javax.swing.JRadioButton jRadioButtonRebaja;
    private javax.swing.JRadioButton jRadioButtonSalida;
    private javax.swing.JRadioButton jRadioButtonTraspaso;
    private javax.swing.JSpinner jSpinnerCantidad;
    private javax.swing.JSpinner jSpinnerMonto;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldRebaja;
    private org.jdesktop.swingx.JXLabel jXLabelTotalAlmacen;
    private org.jdesktop.swingx.JXLabel jXLabelValorTotal;
    private org.jdesktop.swingx.JXPanel jXPanelControles;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setEditingMode() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCreatingMode() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validateData() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    private void ejecutarTransaccion() {
        InsumoAlmacen ins = model.getHandler().getTableModel().getObjectAtSelectedRow();
        float cantidad = (float) jSpinnerCantidad.getValue();
        float monto = (float) jSpinnerMonto.getValue();
        String causa = jTextFieldRebaja.getText();
        if (cantidad == 0) {
            throw new ValidatingException(this,"La cantidad no puede ser 0");
        }
        if (jRadioButtonEntrada.isSelected()) {
            getController().crearTransaccion(ins, 0, null, null,cantidad, monto, causa);
        }
        if (jRadioButtonSalida.isSelected()) {
            getController().crearTransaccion(ins, 1, (Cocina) jComboBoxPuntoElab.getSelectedItem(), null,cantidad, monto, causa);
        }
        if (jRadioButtonRebaja.isSelected()) {
            if (causa == null  || causa.isEmpty()) {
                throw new ValidatingException(this, "La causa de la rebaja no puede estar vacía");
            }
            getController().crearTransaccion(ins, 2, null, null,cantidad, monto, causa);
        }
        if (jRadioButtonTraspaso.isSelected()) {
            if (getInstance().equals(jComboBoxAlmacen.getSelectedItem())) {
                throw new ValidatingException(this,"El almacen destino para el traspaso debe ser diferente al abierto");
            }
            getController().crearTransaccion(ins, 3, null, (Almacen) jComboBoxAlmacen.getSelectedItem(),cantidad, monto, causa);
        }

//        model.getHandler().getTableModel().fireTableDataChanged();
    }

    private void onCheckedCheckBox(CheckBoxType tipo) {
        jTextFieldRebaja.setEnabled(tipo == CheckBoxType.REBAJA);
        jComboBoxAlmacen.setEnabled(tipo == CheckBoxType.TRASPASO);
        jComboBoxPuntoElab.setEnabled(tipo == CheckBoxType.SALIDA);
        jSpinnerMonto.setEnabled(tipo == CheckBoxType.ENTRADA);
    }

    private enum CheckBoxType {
        ENTRADA, REBAJA, SALIDA, TRASPASO
    }

}

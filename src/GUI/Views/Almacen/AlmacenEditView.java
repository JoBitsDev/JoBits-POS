/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.Almacen;

import GUI.Views.AbstractDetailView;
import GUI.Views.AbstractView;
import GUI.Views.util.AbstractCrossReferenePanel;
import java.awt.Container;
import java.awt.Dialog;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.AbstractTableModel;
import javax.xml.ws.handler.MessageContext;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractDialogController;
import restManager.controller.Controller;
import restManager.controller.almacen.AlmacenManageController;
import restManager.controller.almacen.TransaccionDetailController;
import restManager.exceptions.DevelopingOperationException;
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

    public AlmacenEditView(AbstractDetailController<Almacen> controller, AbstractView owner, Almacen instance) {
        super(instance, DialogType.FULL_SCREEN, controller, owner);
        initComponents();
        buttonGroup1.add(jRadioButtonSalida);
        buttonGroup1.add(jRadioButtonEntrada);
        buttonGroup1.add(jRadioButtonRebaja);
        jPaneldestino.setVisible(false);
        jComboBoxPuntoElab.setModel(new RestManagerComboBoxModel<>(getController().getCocinaList()));
        jComboBoxPuntoElab.setSelectedIndex(0);

    }

    @Override
    public void updateView() {
        if (model != null) {
            model.getHandler().getTableModel().setItems(getController().getInsumoAlmacenList(getInstance()));
        }
        else{
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

    }

    @Override
    public void fetchComponentData() {
        jXPanelTabla.add(model);
        jLabelNombreAlmacen.setText(getInstance().getNombre());
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
        jPanel3 = new javax.swing.JPanel();
        jLabelNombreAlmacen = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jXPanelTabla = new org.jdesktop.swingx.JXPanel();
        jXPanelControles = new org.jdesktop.swingx.JXPanel();
        jPanel1 = new javax.swing.JPanel();
        jXLabelTotalAlmacen = new org.jdesktop.swingx.JXLabel();
        jXLabelValorTotal = new org.jdesktop.swingx.JXLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonDarReporte = new javax.swing.JButton();
        jButtonModificarStock = new javax.swing.JButton();
        jButtonVerFichasEntrada = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jRadioButtonEntrada = new javax.swing.JRadioButton();
        jRadioButtonSalida = new javax.swing.JRadioButton();
        jPaneldestino = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxPuntoElab = new javax.swing.JComboBox<>();
        jRadioButtonRebaja = new javax.swing.JRadioButton();
        jButtonConfirmar = new javax.swing.JButton();
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

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jXPanelTabla.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jXPanelTabla, java.awt.BorderLayout.CENTER);

        jXPanelControles.setBackground(new java.awt.Color(204, 204, 204));
        jXPanelControles.setBorder(new org.pushingpixels.lafwidget.utils.ShadowPopupBorder());
        jXPanelControles.setLayout(new javax.swing.BoxLayout(jXPanelControles, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.BorderLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Transaccion"));
        jPanel4.setOpaque(false);

        jRadioButtonEntrada.setSelected(true);
        jRadioButtonEntrada.setText(bundle.getString("label_entrada")); // NOI18N
        jRadioButtonEntrada.setToolTipText("");
        jRadioButtonEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonEntradaActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButtonEntrada);

        jRadioButtonSalida.setText(bundle.getString("label_salida")); // NOI18N
        jRadioButtonSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSalidaActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButtonSalida);

        jLabel1.setText(bundle.getString("label_destino")); // NOI18N
        jPaneldestino.add(jLabel1);

        jComboBoxPuntoElab.setPreferredSize(new java.awt.Dimension(100, 27));
        jPaneldestino.add(jComboBoxPuntoElab);

        jPanel4.add(jPaneldestino);

        jRadioButtonRebaja.setText(bundle.getString("label_rebaja")); // NOI18N
        jRadioButtonRebaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonRebajaActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButtonRebaja);

        jButtonConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/confirmar.png"))); // NOI18N
        jButtonConfirmar.setToolTipText(bundle.getString("label_confirmar")); // NOI18N
        jButtonConfirmar.setBorderPainted(false);
        jButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonConfirmar);

        jPanel2.add(jPanel4);

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
        getController().modificarStock(model.getTableModel().getObjectAtSelectedRow().getInsumo());
    }//GEN-LAST:event_jButtonModificarStockActionPerformed

    private void jButtonVerFichasEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerFichasEntradaActionPerformed
        getController().verTransacciones(getInstance());
    }//GEN-LAST:event_jButtonVerFichasEntradaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButtonEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonEntradaActionPerformed
        jPaneldestino.setVisible(false);
    }//GEN-LAST:event_jRadioButtonEntradaActionPerformed

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed
        ejecutarTransaccion();
    }//GEN-LAST:event_jButtonConfirmarActionPerformed

    private void jRadioButtonSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSalidaActionPerformed
        jPaneldestino.setVisible(true);
    }//GEN-LAST:event_jRadioButtonSalidaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        getController().imprimirResumenAlmacen(getInstance());        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButtonRebajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonRebajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonRebajaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JButton jButtonDarReporte;
    private javax.swing.JButton jButtonModificarStock;
    private javax.swing.JButton jButtonVerFichasEntrada;
    private javax.swing.JComboBox<Cocina> jComboBoxPuntoElab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelNombreAlmacen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPaneldestino;
    private javax.swing.JRadioButton jRadioButtonEntrada;
    private javax.swing.JRadioButton jRadioButtonRebaja;
    private javax.swing.JRadioButton jRadioButtonSalida;
    private org.jdesktop.swingx.JXLabel jXLabelTotalAlmacen;
    private org.jdesktop.swingx.JXLabel jXLabelValorTotal;
    private org.jdesktop.swingx.JXPanel jXPanelControles;
    private org.jdesktop.swingx.JXPanel jXPanelTabla;
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
        if (jRadioButtonEntrada.isSelected()) {
            getController().crearTransaccion(ins, 0, null);
        }
        if (jRadioButtonSalida.isSelected()) {
            getController().crearTransaccion(ins, 1, (Cocina) jComboBoxPuntoElab.getSelectedItem());
        }
        if (jRadioButtonRebaja.isSelected()) {
            getController().crearTransaccion(ins, 2, null);
        }

//        model.getHandler().getTableModel().fireTableDataChanged();
    }

}

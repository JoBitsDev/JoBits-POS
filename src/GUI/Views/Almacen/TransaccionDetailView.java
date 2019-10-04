/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.Almacen;

import GUI.Views.AbstractDetailView;
import GUI.Views.util.AbstractCrossReferenePanel;
import java.awt.Dialog;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractDialogController;
import restManager.controller.almacen.TransaccionDetailController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.UnExpectedErrorException;
import restManager.persistencia.Almacen;
import restManager.persistencia.Cocina;
import restManager.persistencia.Insumo;
import restManager.persistencia.Transaccion;
import restManager.persistencia.TransaccionEntrada;
import restManager.persistencia.TransaccionEntradaPK;
import restManager.persistencia.TransaccionPK;
import restManager.resources.R;
import restManager.util.RestManagerAbstractTableModel;
import restManager.util.RestManagerComboBoxModel;
import restManager.util.utils;

/**
 *
 * @author Jorge
 */
public class TransaccionDetailView extends AbstractDetailView<Transaccion> {

    private AbstractCrossReferenePanel panel;
    private Almacen a;
    private ArrayList transacciones = new ArrayList();

    public TransaccionDetailView(Almacen a, AbstractDialogController controller, Dialog owner) {
        super(null, DialogType.FULL_SCREEN, controller, owner);
        this.a = a;
        initComponents();
        fetchComponentData();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelInfo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jFormattedTextFieldFecha = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextFieldHora = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxTipo = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxDestino = new javax.swing.JComboBox<>();
        jPanelControles = new javax.swing.JPanel();
        jButtonCrear = new javax.swing.JButton();
        jPanelDetalles = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelInfo.setBackground(new java.awt.Color(204, 204, 204));
        jPanelInfo.setBorder(new org.pushingpixels.lafwidget.utils.ShadowPopupBorder());
        jPanelInfo.setForeground(new java.awt.Color(204, 204, 204));
        jPanelInfo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 5));

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jLabel1.setText(bundle.getString("label_fecha")); // NOI18N
        jPanelInfo.add(jLabel1);

        jFormattedTextFieldFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        jFormattedTextFieldFecha.setEnabled(false);
        jFormattedTextFieldFecha.setPreferredSize(new java.awt.Dimension(80, 26));
        jPanelInfo.add(jFormattedTextFieldFecha);

        jLabel2.setText(bundle.getString("label_hora")); // NOI18N
        jPanelInfo.add(jLabel2);

        jFormattedTextFieldHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("hh:mm a"))));
        jFormattedTextFieldHora.setEnabled(false);
        jFormattedTextFieldHora.setPreferredSize(new java.awt.Dimension(70, 26));
        jPanelInfo.add(jFormattedTextFieldHora);

        jLabel3.setText(bundle.getString("label_tipo")); // NOI18N
        jPanelInfo.add(jLabel3);

        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entrada", "Salida" }));
        jComboBoxTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxTipoItemStateChanged(evt);
            }
        });
        jPanelInfo.add(jComboBoxTipo);

        jPanel1.setOpaque(false);

        jLabel4.setText(bundle.getString("label_destino")); // NOI18N
        jPanel1.add(jLabel4);

        jComboBoxDestino.setPreferredSize(new java.awt.Dimension(100, 27));
        jPanel1.add(jComboBoxDestino);

        jPanelInfo.add(jPanel1);

        getContentPane().add(jPanelInfo, java.awt.BorderLayout.PAGE_START);

        jPanelControles.setBackground(new java.awt.Color(153, 153, 153));
        jPanelControles.setBorder(new org.pushingpixels.lafwidget.utils.ShadowPopupBorder());

        jButtonCrear.setText(bundle.getString("label_crear")); // NOI18N
        jButtonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearActionPerformed(evt);
            }
        });
        jPanelControles.add(jButtonCrear);

        getContentPane().add(jPanelControles, java.awt.BorderLayout.PAGE_END);

        jPanelDetalles.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanelDetalles, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxTipoItemStateChanged
        jPanel1.setVisible(jComboBoxTipo.getSelectedIndex() == 1);
        updateView();
    }//GEN-LAST:event_jComboBoxTipoItemStateChanged

    private void jButtonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearActionPerformed
        if (jComboBoxTipo.getSelectedIndex() == 0) {
        //    getController().createNewTransaccionEntrada(transacciones);
        } else {
           // getController().createNewTransaccionSalida(transacciones);
        }
        dispose();
    }//GEN-LAST:event_jButtonCrearActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JComboBox<Cocina> jComboBoxDestino;
    private javax.swing.JComboBox<String> jComboBoxTipo;
    private javax.swing.JFormattedTextField jFormattedTextFieldFecha;
    private javax.swing.JFormattedTextField jFormattedTextFieldHora;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelControles;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JPanel jPanelInfo;
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

    @Override
    public void fetchComponentData() {
        jComboBoxDestino.setModel(new RestManagerComboBoxModel<>(getController().getCocinaList()));
        jPanel1.setVisible(false);
        jFormattedTextFieldFecha.setText(R.DATE_FORMAT.format(new Date()));
        jFormattedTextFieldHora.setText(R.TIME_FORMAT.format(new Date()));
    }

    @Override
    public void updateView() {
        transacciones = new ArrayList();
        if (panel != null) {
            jPanelDetalles.remove(panel);
        }
        if (jComboBoxTipo.getSelectedIndex() == 0) {
            panel = new AbstractCrossReferenePanel<TransaccionEntrada, Insumo>("Insumos", getController().getInsumoList()) {
                @Override
                public RestManagerAbstractTableModel<TransaccionEntrada> getTableModel() {
                    return new RestManagerAbstractTableModel<TransaccionEntrada>(transacciones, getjTableCrossReference()) {
                        @Override
                        public int getColumnCount() {
                            return 3;
                        }

                        @Override
                        public Object getValueAt(int rowIndex, int columnIndex) {
                            switch (columnIndex) {
                                case 0:
                                    return items.get(rowIndex).getTransaccion().getInsumo();
                                case 1:
                                    return items.get(rowIndex).getTransaccion().getCantidad();
                                case 2:
                                    return items.get(rowIndex).getValorTotal();
                                default:
                                    return null;
                            }
                        }

                        @Override
                        public String getColumnName(int column) {
                            switch (column) {
                                case 0:
                                    return "Insumo";
                                case 1:
                                    return "Cantidad";
                                case 2:
                                    return "Valor Total";
                                default:
                                    return null;
                            }
                        }
                    };
                }

                @Override
                public TransaccionEntrada transformK_T(Insumo selected) {
                    try {
                        return getController().addTransaccionEntrada(selected,
                                R.DATE_FORMAT.parse(jFormattedTextFieldFecha.getText()),
                                R.TIME_FORMAT.parse(jFormattedTextFieldHora.getText()),
                                a,0,0);
                    } catch (ParseException ex) {
                        throw new UnExpectedErrorException(this, ex.getMessage());
                    }
                }
            };
        } else {
            panel = new AbstractCrossReferenePanel<Transaccion, Insumo>("Insumos", getController().getInsumoList()) {
                @Override
                public RestManagerAbstractTableModel<Transaccion> getTableModel() {
                    return new RestManagerAbstractTableModel<Transaccion>(transacciones, getjTableCrossReference()) {
                        @Override
                        public int getColumnCount() {
                            return 3;
                        }

                        @Override
                        public Object getValueAt(int rowIndex, int columnIndex) {
                            switch (columnIndex) {
                                case 0:
                                    return items.get(rowIndex).getInsumo();
                                case 1:
                                    return items.get(rowIndex).getCantidad();
                                case 2:
                                    return utils.setDosLugaresDecimales(items.get(rowIndex).getInsumo().getCostoPorUnidad() * items.get(rowIndex).getCantidad());
                                default:
                                    return null;
                            }
                        }

                        @Override
                        public String getColumnName(int column) {
                            switch (column) {
                                case 0:
                                    return "Insumo";
                                case 1:
                                    return "Cantidad";
                                case 2:
                                    return "Valor Total";
                                default:
                                    return null;
                            }
                        }
                    };
                }

                @Override
                public Transaccion transformK_T(Insumo selected) {
                    try {
                        return getController().addTransaccionSalida(selected,
                                R.DATE_FORMAT.parse(jFormattedTextFieldFecha.getText()),
                                R.TIME_FORMAT.parse(jFormattedTextFieldHora.getText()),
                                a, (Cocina) jComboBoxDestino.getSelectedItem(),0);
                    } catch (ParseException ex) {
                        throw new UnExpectedErrorException(this, ex.getMessage());
                    }
                }
            };

        }
        jPanelDetalles.add(panel);
        jPanelDetalles.revalidate();

    }

    @Override
    public TransaccionDetailController getController() {
        return (TransaccionDetailController) super.getController(); //To change body of generated methods, choose Tools | Templates.
    }

}

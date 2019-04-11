/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.venta;

import GUI.Views.AbstractDetailView;
import GUI.Views.util.StateCellRender;
import GUI.Views.util.TableColumnAdjuster;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import restManager.controller.AbstractDialogController;
import restManager.controller.venta.VentaDetailController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.NoSelectedException;
import restManager.persistencia.Cocina;
import restManager.persistencia.Control.VentaDAO1;
import restManager.persistencia.Orden;
import restManager.persistencia.Personal;
import restManager.persistencia.Venta;
import restManager.persistencia.models.CocinaDAO;
import restManager.resources.R;
import restManager.util.RestManagerAbstractTableModel;
import restManager.util.comun;

/**
 *
 * @author Jorge
 */
public class VentasCreateEditView extends AbstractDetailView<Venta> {

    RestManagerAbstractTableModel<Orden> modelOrd;
    Date fechaFin;

    public VentasCreateEditView(Venta instance, AbstractDialogController controller) {
        super(instance, DialogType.FULL_SCREEN, controller);
        initComponents();
        init();
    }

    public VentasCreateEditView(Venta instance, AbstractDialogController controller, Frame owner) {
        super(instance, DialogType.FULL_SCREEN, controller, owner);
        initComponents();
        init();
    }

    public VentasCreateEditView(Venta instance, AbstractDialogController controller, Dialog owner) {
        super(instance, DialogType.FULL_SCREEN, controller, owner);
        initComponents();
        init();
    }

    public VentasCreateEditView(Venta instance, AbstractDialogController controller, Dialog owner, Date fechaFin) {
        super(instance, DialogType.FULL_SCREEN, controller, owner);
        this.fechaFin = fechaFin;
        initComponents();
        init();
    }

    public JPanel getjPanelDetailOrdenes() {
        return jPanelDetailOrdenes;
    }

    public void setjPanelDetailOrdenes(JPanel jPanelDetailOrdenes) {
        this.jPanelDetailOrdenes = jPanelDetailOrdenes;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelResumenDetallado = new javax.swing.JPanel();
        jTabbedPaneResumen = new javax.swing.JTabbedPane();
        jPanelRoot = new javax.swing.JPanel();
        jPanelOptions = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jideButton1 = new com.jidesoft.swing.JideButton();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButtonTerminarVentas = new javax.swing.JButton();
        jLabelFecha = new javax.swing.JLabel();
        jPanelData = new javax.swing.JPanel();
        jTabbedPaneData = new javax.swing.JTabbedPane();
        jPanelResumen = new javax.swing.JPanel();
        jPanelResumenVentas = new javax.swing.JPanel();
        jPanelNumero = new javax.swing.JPanel();
        jLabelTotalVentas = new javax.swing.JLabel();
        jButtonImprimirZ = new javax.swing.JButton();
        jPanelVentasCamareras = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableVentasDependientes = new javax.swing.JTable();
        jButtonImprimirDptes = new javax.swing.JButton();
        jPanelVentasCocinas = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableVentasPorCocina = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jPanelGastos = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonImprimirZ1 = new javax.swing.JButton();
        jLabelTotalGastos = new javax.swing.JLabel();
        jPanelVentas = new javax.swing.JPanel();
        jPanelDetailOrdenes = new javax.swing.JPanel();
        jPanelOrdenesActivas = new javax.swing.JPanel();
        jideLabel1 = new com.jidesoft.swing.JideLabel();
        jXPanelOrdenControl = new org.jdesktop.swingx.JXPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButtonEnviarCerrarCrearNueva = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jXTableOrdActivas = new org.jdesktop.swingx.JXTable();

        jPanelResumenDetallado.setLayout(new java.awt.BorderLayout());
        jPanelResumenDetallado.add(jTabbedPaneResumen, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        com.jidesoft.swing.JideBorderLayout jideBorderLayout1 = new com.jidesoft.swing.JideBorderLayout();
        jideBorderLayout1.setHgap(10);
        jideBorderLayout1.setVgap(10);
        getContentPane().setLayout(jideBorderLayout1);

        jPanelRoot.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanelRoot.setLayout(new java.awt.BorderLayout());

        jPanelOptions.setBackground(new java.awt.Color(204, 204, 204));
        jPanelOptions.setBorder(new org.pushingpixels.lafwidget.utils.ShadowPopupBorder());
        jPanelOptions.setLayout(new java.awt.BorderLayout());

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/logout40.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanelOptions.add(jButton2, java.awt.BorderLayout.WEST);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jideButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/refresh.png"))); // NOI18N
        jideButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jideButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jideButton1);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jButton1.setText(bundle.getString("label_imprimir_resumen")); // NOI18N
        jButton1.setEnabled(false);
        jPanel1.add(jButton1);

        jButtonTerminarVentas.setText(bundle.getString("label_terminar_ventas")); // NOI18N
        jButtonTerminarVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTerminarVentasActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonTerminarVentas);

        jLabelFecha.setText("Del 15/03/19 al 25/03/19");
        jLabelFecha.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha"));
        jPanel1.add(jLabelFecha);

        jPanel3.add(jPanel1);

        jPanelOptions.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanelRoot.add(jPanelOptions, java.awt.BorderLayout.NORTH);

        jPanelData.setLayout(new java.awt.BorderLayout());

        jTabbedPaneData.setToolTipText("");
        jTabbedPaneData.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jPanelResumen.setLayout(new javax.swing.BoxLayout(jPanelResumen, javax.swing.BoxLayout.PAGE_AXIS));

        jPanelResumenVentas.setBackground(new java.awt.Color(153, 255, 153));
        jPanelResumenVentas.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 102, 51), null, null), javax.swing.BorderFactory.createTitledBorder(null, "Ventas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 36)))); // NOI18N
        jPanelResumenVentas.setLayout(new javax.swing.BoxLayout(jPanelResumenVentas, javax.swing.BoxLayout.PAGE_AXIS));

        jPanelNumero.setOpaque(false);
        jPanelNumero.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabelTotalVentas.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabelTotalVentas.setText("1503.52 CUC");
        jLabelTotalVentas.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("label_total"))); // NOI18N
        jPanelNumero.add(jLabelTotalVentas);

        jButtonImprimirZ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImprimirZ.setText("Imprimir Z");
        jButtonImprimirZ.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButtonImprimirZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirZActionPerformed(evt);
            }
        });
        jPanelNumero.add(jButtonImprimirZ);

        jPanelResumenVentas.add(jPanelNumero);

        jPanelVentasCamareras.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen Ventas Dependientes"));
        jPanelVentasCamareras.setOpaque(false);
        jPanelVentasCamareras.setLayout(new java.awt.BorderLayout());

        jTableVentasDependientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuario", "Monto", "Ordenes Atendidas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
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
        jScrollPane3.setViewportView(jTableVentasDependientes);

        jPanelVentasCamareras.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jButtonImprimirDptes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImprimirDptes.setText(bundle.getString("label_imprimir")); // NOI18N
        jButtonImprimirDptes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirDptesActionPerformed(evt);
            }
        });
        jPanelVentasCamareras.add(jButtonImprimirDptes, java.awt.BorderLayout.PAGE_END);

        jPanelResumenVentas.add(jPanelVentasCamareras);

        jPanelVentasCocinas.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen de Ventas por Cocina"));
        jPanelVentasCocinas.setOpaque(false);
        jPanelVentasCocinas.setLayout(new java.awt.BorderLayout());

        jTableVentasPorCocina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Monto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class
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
        jScrollPane5.setViewportView(jTableVentasPorCocina);

        jPanelVentasCocinas.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButton8.setText(bundle.getString("label_imprimir")); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanelVentasCocinas.add(jButton8, java.awt.BorderLayout.PAGE_END);

        jPanelResumenVentas.add(jPanelVentasCocinas);

        jPanelResumen.add(jPanelResumenVentas);

        jPanelGastos.setBackground(new java.awt.Color(255, 102, 102));
        jPanelGastos.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 0, 0), null, null), javax.swing.BorderFactory.createTitledBorder(null, "Gastos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 36)))); // NOI18N
        jPanelGastos.setLayout(new javax.swing.BoxLayout(jPanelGastos, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.BorderLayout());

        jButtonImprimirZ1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImprimirZ1.setText(bundle.getString("imprimir_gastos_casa")); // NOI18N
        jButtonImprimirZ1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButtonImprimirZ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirZ1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonImprimirZ1, java.awt.BorderLayout.LINE_START);

        jLabelTotalGastos.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabelTotalGastos.setText("850.23 CUC");
        jLabelTotalGastos.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("label_total"))); // NOI18N
        jPanel2.add(jLabelTotalGastos, java.awt.BorderLayout.EAST);

        jPanelGastos.add(jPanel2);

        jPanelResumen.add(jPanelGastos);

        jTabbedPaneData.addTab("Resumen", jPanelResumen);

        jPanelVentas.setLayout(new java.awt.BorderLayout(20, 20));

        jPanelDetailOrdenes.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles de orden"));
        jPanelDetailOrdenes.setLayout(new java.awt.BorderLayout());
        jPanelVentas.add(jPanelDetailOrdenes, java.awt.BorderLayout.CENTER);

        jPanelOrdenesActivas.setPreferredSize(new java.awt.Dimension(400, 438));
        jPanelOrdenesActivas.setLayout(new java.awt.BorderLayout());

        jideLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jideLabel1.setText(bundle.getString("label_ordenes_activas")); // NOI18N
        jPanelOrdenesActivas.add(jideLabel1, java.awt.BorderLayout.PAGE_START);

        jXPanelOrdenControl.setBackground(new java.awt.Color(204, 204, 204));
        jXPanelOrdenControl.setBorder(new org.pushingpixels.lafwidget.utils.ShadowPopupBorder());
        jXPanelOrdenControl.setLayout(new java.awt.GridLayout(2, 0));

        jButton4.setText(bundle.getString("label_agregar")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jXPanelOrdenControl.add(jButton4);

        jButton5.setText(bundle.getString("label_eliminar")); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jXPanelOrdenControl.add(jButton5);

        jButton6.setText(bundle.getString("label_calcular_cambio")); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jXPanelOrdenControl.add(jButton6);

        jButtonEnviarCerrarCrearNueva.setMnemonic('r');
        jButtonEnviarCerrarCrearNueva.setText("Cerrado Rapido");
        jButtonEnviarCerrarCrearNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarCerrarCrearNuevaActionPerformed(evt);
            }
        });
        jXPanelOrdenControl.add(jButtonEnviarCerrarCrearNueva);

        jPanelOrdenesActivas.add(jXPanelOrdenControl, java.awt.BorderLayout.PAGE_END);

        jXTableOrdActivas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jXTableOrdActivas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane2.setViewportView(jXTableOrdActivas);

        jPanelOrdenesActivas.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanelVentas.add(jPanelOrdenesActivas, java.awt.BorderLayout.WEST);

        jTabbedPaneData.addTab("Ventas", jPanelVentas);

        jPanelData.add(jTabbedPaneData, java.awt.BorderLayout.CENTER);

        jPanelRoot.add(jPanelData, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelRoot);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        getController().createNewOrden();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        getController().removeOrden(modelOrd.getObjectAtSelectedRow());
        modelOrd.fireTableDataChanged();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jideButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jideButton1ActionPerformed
        getController().fetchNewDataFromServer();
    }//GEN-LAST:event_jideButton1ActionPerformed

    private void jButtonTerminarVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTerminarVentasActionPerformed
        getController().terminarVentas();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonTerminarVentasActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        getController().calcularCambio(getModelOrd().getObjectAtSelectedRow());
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButtonImprimirDptesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirDptesActionPerformed
        int row = jTableVentasDependientes.getSelectedRow();
        if (row == -1) {
            throw new NoSelectedException(jTableVentasDependientes);
        }
        getController().printPersonalResumenRow((String) jTableVentasDependientes.getValueAt(row, 0));        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonImprimirDptesActionPerformed

    private void jButtonImprimirZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirZActionPerformed
        getController().printZ();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonImprimirZActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int row = jTableVentasPorCocina.getSelectedRow();
        if (row == -1) {
            throw new NoSelectedException(jTableVentasDependientes);
        }
        getController().printCocinaResumen((String) jTableVentasPorCocina.getValueAt(row, 0));
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButtonImprimirZ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirZ1ActionPerformed
        getController().printGastosCasa();       // TODO add your handling code here:
    }//GEN-LAST:event_jButtonImprimirZ1ActionPerformed

    private void jButtonEnviarCerrarCrearNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarCerrarCrearNuevaActionPerformed
        enviarCerrarCrear();
    }//GEN-LAST:event_jButtonEnviarCerrarCrearNuevaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
    public void updateView() {
        if (fechaFin != null) {
            jLabelFecha.setText("Del " + R.DATE_FORMAT.format(instance.getFecha()) + " Al "
                    + R.DATE_FORMAT.format(fechaFin));
            jTabbedPaneData.removeTabAt(1);
            jButtonTerminarVentas.setVisible(false);

        } else {
            jLabelFecha.setText(R.DATE_FORMAT.format(instance.getFecha()));

            modelOrd = new RestManagerAbstractTableModel<Orden>(getController().getOrdenesActivas(), jXTableOrdActivas) {
                @Override
                public int getColumnCount() {
                    return 4;
                }

                @Override
                public Object getValueAt(int rowIndex, int columnIndex) {
                    switch (columnIndex) {
                        case 0:
                            return items.get(rowIndex).getCodOrden();
                        case 1:
                            return items.get(rowIndex).getMesacodMesa().getCodMesa();
                        case 2:
                            return items.get(rowIndex).getOrdenvalorMonetario() + R.COIN_SUFFIX;
                        case 3:
                            return items.get(rowIndex);
                        default:
                            return null;
                    }
                }

                @Override
                public String getColumnName(int column) {
                    switch (column) {
                        case 0:
                            return "Codigo Orden";
                        case 1:
                            return "Mesa";
                        case 2:
                            return "Valor Total";
                        case 3:
                            return "Estados";
                        default:
                            return null;

                    }
                }
            };
            jXTableOrdActivas.setModel(modelOrd);
            jXTableOrdActivas.getColumn(3).setCellRenderer(new StateCellRender<Orden>() {
                @Override
                public void processData(Orden object, Container root) {
                    if (object.getDeLaCasa()) {
                        root.add(getjState1());
                    }
                    if (object.getHoraTerminada() != null) {
                        root.add(getjState2());
                    }
                    if (object.getPorciento() != 0) {
                        root.add(getJstate3());
                    }
                }
            });
            jXTableOrdActivas.setRowHeight(40);

        }
        updateTableResumenDependientes();
        updateTableResumenCocinas();
        updateTableResumenDetallado();
        jLabelTotalVentas.setText(getController().getTotalVendido());
        jLabelTotalGastos.setText(getController().getTotalGastado());

    }

    @Override
    public VentaDetailController getController() {
        return (VentaDetailController) super.getController(); //To change body of generated methods, choose Tools | Templates.
    }

    public RestManagerAbstractTableModel<Orden> getModelOrd() {
        return modelOrd;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButtonEnviarCerrarCrearNueva;
    private javax.swing.JButton jButtonImprimirDptes;
    private javax.swing.JButton jButtonImprimirZ;
    private javax.swing.JButton jButtonImprimirZ1;
    private javax.swing.JButton jButtonTerminarVentas;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelTotalGastos;
    private javax.swing.JLabel jLabelTotalVentas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelData;
    private javax.swing.JPanel jPanelDetailOrdenes;
    private javax.swing.JPanel jPanelGastos;
    private javax.swing.JPanel jPanelNumero;
    private javax.swing.JPanel jPanelOptions;
    private javax.swing.JPanel jPanelOrdenesActivas;
    private javax.swing.JPanel jPanelResumen;
    private javax.swing.JPanel jPanelResumenDetallado;
    private javax.swing.JPanel jPanelResumenVentas;
    private javax.swing.JPanel jPanelRoot;
    private javax.swing.JPanel jPanelVentas;
    private javax.swing.JPanel jPanelVentasCamareras;
    private javax.swing.JPanel jPanelVentasCocinas;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPaneData;
    private javax.swing.JTabbedPane jTabbedPaneResumen;
    private javax.swing.JTable jTableVentasDependientes;
    private javax.swing.JTable jTableVentasPorCocina;
    private org.jdesktop.swingx.JXPanel jXPanelOrdenControl;
    private org.jdesktop.swingx.JXTable jXTableOrdActivas;
    private com.jidesoft.swing.JideButton jideButton1;
    private com.jidesoft.swing.JideLabel jideLabel1;
    // End of variables declaration//GEN-END:variables

    private void init() {
        TableColumnAdjuster adj = new TableColumnAdjuster(jXTableOrdActivas);
        adj.setDynamicAdjustment(true);
        jXTableOrdActivas.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting() && jXTableOrdActivas.getSelectedRow() != -1) {
                getController().updateOrdenDialog(modelOrd.getObjectAtSelectedRow());
            }
        });
        adj.adjustColumns();
        if (R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() < 3) {
            jTabbedPaneData.remove(0);
        }
    }

    private void updateTableResumenCocinas() {
        List<Personal> p = getController().getPersonalList();
        comun.limpiarTabla(jTableVentasDependientes);
        p.forEach((x) -> {
            VentaDAO1.getResumenVentasCamareroOnTable(jTableVentasDependientes, getInstance(), x);
        });
    }

    private void updateTableResumenDependientes() {
        List<Cocina> c = getController().getCocinaList();
        comun.limpiarTabla(jTableVentasPorCocina);
        c.forEach((x) -> {
            VentaDAO1.getResumenVentasCocinaOnTable(jTableVentasPorCocina, getInstance(), x);
        });

    }

    private void enviarCerrarCrear() {
        getController().cerrarOrdenRapido();
    }

    private void updateTableResumenDetallado() {
        if (fechaFin != null) {
            jTabbedPaneData.addTab("Resumen Detallado", jPanelResumenDetallado);
            crearFrame();
        }
    }

    private void crearFrame() {
        String hVentas,
                hGastos,
                cDate,
                nombreMenu = R.REST_NAME;

        if (getController().getInstance().getFecha().getDate() == fechaFin.getDate()
                && getController().getInstance().getFecha().getMonth() == fechaFin.getMonth()) {
            cDate = R.DATE_FORMAT.format(getController().getInstance().getFecha())
                    + "(" + nombreMenu + ")";
            hVentas = ("Ventas del dia " + cDate);
            hGastos = ("Gastos por productos del dia " + cDate);

        } else {
            cDate = R.DATE_FORMAT.format(getController().getInstance().getFecha())
                    + " al " + R.DATE_FORMAT.format(fechaFin) + "(" + nombreMenu + ")";
            hVentas = ("Resumen de ventas del " + cDate);
            hGastos = ("Resumen de gastos del " + cDate);
        }

        jTabbedPaneResumen.addTab("Resumen Total ", new Resumenes(getController().getInstance(), fechaFin, hVentas, hGastos));
        List<Cocina> cocinas = CocinaDAO.getInstance().findAll();
        for (int i = 0; i < cocinas.size(); i++) {
            jTabbedPaneResumen.addTab(cocinas.get(i).getNombreCocina(),
                    new Resumenes(getController().getInstance(), cocinas.get(i), fechaFin,
                            "Ventas de " + cocinas.get(i).getNombreCocina() + " " + cDate,
                            "Gastos por producto " + cocinas.get(i).getNombreCocina() + " " + cDate));
        }
    }

}

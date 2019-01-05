/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.venta;

import GUI.Views.AbstractDetailView;
import GUI.Views.util.StateCellRender;
import GUI.Views.util.TableColumnAdjuster;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import javax.swing.AbstractCellEditor;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.text.html.CSS;
import org.jdesktop.swingx.JXLabel;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractDialogController;
import restManager.controller.venta.OrdenController;
import restManager.controller.venta.VentaDetailController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Orden;
import restManager.persistencia.Venta;
import restManager.resources.R;
import restManager.util.RestManagerAbstractTableModel;

/**
 *
 * @author Jorge
 */
public class VentasCreateEditView extends AbstractDetailView<Venta> {

    RestManagerAbstractTableModel<Orden> modelOrd;

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

    public JPanel getjPanelDetailOrdenes() {
        return jPanelDetailOrdenes;
    }

    public void setjPanelDetailOrdenes(JPanel jPanelDetailOrdenes) {
        this.jPanelDetailOrdenes = jPanelDetailOrdenes;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        substanceDatePickerUI1 = new org.pushingpixels.substance.swingx.SubstanceDatePickerUI();
        jPanelRoot = new javax.swing.JPanel();
        jPanelOptions = new javax.swing.JPanel();
        jideButton1 = new com.jidesoft.swing.JideButton();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanelData = new javax.swing.JPanel();
        jTabbedPaneData = new javax.swing.JTabbedPane();
        jPanelVentas = new javax.swing.JPanel();
        jPanelDetailOrdenes = new javax.swing.JPanel();
        jPanelOrdenesActivas = new javax.swing.JPanel();
        jideLabel1 = new com.jidesoft.swing.JideLabel();
        jXPanelOrdenControl = new org.jdesktop.swingx.JXPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jXTableOrdActivas = new org.jdesktop.swingx.JXTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        com.jidesoft.swing.JideBorderLayout jideBorderLayout1 = new com.jidesoft.swing.JideBorderLayout();
        jideBorderLayout1.setHgap(10);
        jideBorderLayout1.setVgap(10);
        getContentPane().setLayout(jideBorderLayout1);

        jPanelRoot.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanelRoot.setLayout(new java.awt.BorderLayout());

        jPanelOptions.setBackground(new java.awt.Color(204, 204, 204));
        jPanelOptions.setBorder(new org.pushingpixels.lafwidget.utils.ShadowPopupBorder());
        jPanelOptions.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jideButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/refresh.png"))); // NOI18N
        jideButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jideButton1ActionPerformed(evt);
            }
        });
        jPanelOptions.add(jideButton1);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jButton1.setText(bundle.getString("label_imprimir_resumen")); // NOI18N
        jButton1.setEnabled(false);
        jPanel1.add(jButton1);

        jButton2.setText(bundle.getString("label_terminar_ventas")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        jDateChooser1.setEnabled(false);
        jPanel1.add(jDateChooser1);

        jPanelOptions.add(jPanel1);

        jPanelRoot.add(jPanelOptions, java.awt.BorderLayout.NORTH);

        jPanelData.setLayout(new java.awt.BorderLayout());

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        getController().terminarVentas();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        getController().calcularCambio(getModelOrd().getObjectAtSelectedRow());
    }//GEN-LAST:event_jButton6ActionPerformed

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
        jDateChooser1.setDate(instance.getFecha());
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
                        return items.get(rowIndex).getOrdenvalorMonetario() + R.coinSuffix;
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
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelData;
    private javax.swing.JPanel jPanelDetailOrdenes;
    private javax.swing.JPanel jPanelOptions;
    private javax.swing.JPanel jPanelOrdenesActivas;
    private javax.swing.JPanel jPanelRoot;
    private javax.swing.JPanel jPanelVentas;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPaneData;
    private org.jdesktop.swingx.JXPanel jXPanelOrdenControl;
    private org.jdesktop.swingx.JXTable jXTableOrdActivas;
    private com.jidesoft.swing.JideButton jideButton1;
    private com.jidesoft.swing.JideLabel jideLabel1;
    private org.pushingpixels.substance.swingx.SubstanceDatePickerUI substanceDatePickerUI1;
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
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.venta;

import GUI.Views.AbstractDetailView;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import javax.swing.AbstractCellEditor;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.JXLabel;
import restManager.controller.AbstractDialogController;
import restManager.controller.venta.OrdenController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Orden;
import restManager.persistencia.Venta;
import restManager.util.RestManagerAbstractTableModel;

/**
 *
 * @author Jorge
 */
public class VentasCreateEditView extends AbstractDetailView<Venta> {

    RestManagerAbstractTableModel<Orden> modelOrd;
    
    public VentasCreateEditView(Venta instance, AbstractDialogController controller) {
        super(instance, DialogType.NORMAL, controller);
        initComponents();
    }

    public VentasCreateEditView(Venta instance, AbstractDialogController controller, Frame owner) {
        super(instance, DialogType.NORMAL, controller, owner);
        initComponents();
    }

    public VentasCreateEditView(Venta instance, AbstractDialogController controller, Dialog owner) {
        super(instance, DialogType.NORMAL, controller, owner);
        initComponents();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTableOrdActivas = new org.jdesktop.swingx.JXTable();
        jXPanelOrdenControl = new org.jdesktop.swingx.JXPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        com.jidesoft.swing.JideBorderLayout jideBorderLayout1 = new com.jidesoft.swing.JideBorderLayout();
        jideBorderLayout1.setHgap(10);
        jideBorderLayout1.setVgap(10);
        getContentPane().setLayout(jideBorderLayout1);

        jPanelRoot.setLayout(new java.awt.BorderLayout());

        jPanelOptions.setLayout(new java.awt.BorderLayout(10, 10));

        jideButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/refresh.png"))); // NOI18N
        jideButton1.setPreferredSize(new java.awt.Dimension(50, 50));
        jPanelOptions.add(jideButton1, java.awt.BorderLayout.WEST);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jButton1.setText(bundle.getString("label_imprimir_resumen")); // NOI18N
        jPanel1.add(jButton1);

        jButton2.setText(bundle.getString("label_terminar_ventas")); // NOI18N
        jPanel1.add(jButton2);
        jPanel1.add(jDateChooser1);

        jPanelOptions.add(jPanel1, java.awt.BorderLayout.EAST);

        jPanelRoot.add(jPanelOptions, java.awt.BorderLayout.NORTH);

        jPanelData.setLayout(new java.awt.BorderLayout());

        jPanelVentas.setLayout(new java.awt.BorderLayout(20, 20));

        jPanelDetailOrdenes.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles de orden"));
        jPanelDetailOrdenes.setLayout(new java.awt.BorderLayout());
        jPanelVentas.add(jPanelDetailOrdenes, java.awt.BorderLayout.CENTER);

        jPanelOrdenesActivas.setLayout(new java.awt.BorderLayout());

        jideLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jideLabel1.setText(bundle.getString("label_ordenes_activas")); // NOI18N
        jPanelOrdenesActivas.add(jideLabel1, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setColumnHeaderView(null);

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
        jXTableOrdActivas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jXTableOrdActivasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jXTableOrdActivas);

        jPanelOrdenesActivas.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jXPanelOrdenControl.setLayout(new java.awt.GridLayout(2, 0));

        jButton4.setText(bundle.getString("label_agregar")); // NOI18N
        jXPanelOrdenControl.add(jButton4);

        jButton5.setText(bundle.getString("label_eliminar")); // NOI18N
        jXPanelOrdenControl.add(jButton5);

        jButton6.setText(bundle.getString("label_calcular_cambio")); // NOI18N
        jXPanelOrdenControl.add(jButton6);

        jButton7.setText(bundle.getString("label_cerrar_orden")); // NOI18N
        jXPanelOrdenControl.add(jButton7);

        jPanelOrdenesActivas.add(jXPanelOrdenControl, java.awt.BorderLayout.PAGE_END);

        jPanelVentas.add(jPanelOrdenesActivas, java.awt.BorderLayout.WEST);

        jTabbedPaneData.addTab("Ventas", jPanelVentas);

        jPanelData.add(jTabbedPaneData, java.awt.BorderLayout.CENTER);

        jPanelRoot.add(jPanelData, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelRoot);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jXTableOrdActivasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXTableOrdActivasMouseClicked
      
        
    }//GEN-LAST:event_jXTableOrdActivasMouseClicked

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
        modelOrd = new RestManagerAbstractTableModel<Orden>(instance.getOrdenList(), jXTableOrdActivas) {
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
                        return items.get(rowIndex).getOrdenvalorMonetario();
                    case 3:
                        return new JPanelCellEditor(items.get(rowIndex));
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
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelData;
    private javax.swing.JPanel jPanelDetailOrdenes;
    private javax.swing.JPanel jPanelOptions;
    private javax.swing.JPanel jPanelOrdenesActivas;
    private javax.swing.JPanel jPanelRoot;
    private javax.swing.JPanel jPanelVentas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPaneData;
    private org.jdesktop.swingx.JXPanel jXPanelOrdenControl;
    private org.jdesktop.swingx.JXTable jXTableOrdActivas;
    private com.jidesoft.swing.JideButton jideButton1;
    private com.jidesoft.swing.JideLabel jideLabel1;
    private org.pushingpixels.substance.swingx.SubstanceDatePickerUI substanceDatePickerUI1;
    // End of variables declaration//GEN-END:variables

    public class JPanelCellEditor extends AbstractCellEditor implements TableCellEditor {

        private JPanel panel;

        public JPanelCellEditor(Orden o) {
            panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            if (o.getDeLaCasa()) {
                panel.add(new JXLabel(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/exitoso.png"))));
            }

        }

        @Override
        public Object getCellEditorValue() {
            return panel;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return panel;
        }
    }

}

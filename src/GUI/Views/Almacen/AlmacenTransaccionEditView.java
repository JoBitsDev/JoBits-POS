/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.Almacen;

import GUI.AbstractView;
import java.awt.Dialog;
import java.awt.Frame;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import restManager.controller.AbstractController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.NoSelectedException;
import restManager.persistencia.Insumo;
import restManager.persistencia.InsumoTransaccion;
import restManager.persistencia.InsumoTransaccionPK;
import restManager.persistencia.Transaccion;
import restManager.persistencia.models.TransaccionDAO;
import restManager.resources.R;
import restManager.util.RestManagerAbstractTableModel;
import restManager.util.RestManagerListModel;

/**
 *
 * @author Jorge
 */
public class AlmacenTransaccionEditView extends AbstractView {

    Transaccion trans;
    List<Insumo> items;
    RestManagerAbstractTableModel<InsumoTransaccion> model;

    public AlmacenTransaccionEditView(Transaccion f, List<Insumo> items, AbstractController controller, Frame owner, boolean modal) {
        super(DialogType.NORMAL, controller, owner, modal);
        this.trans = f;
        this.items = items;
        initComponents();
        initTableModel();
        jListInsumos.setModel(new RestManagerListModel<>(items));

    }

    public AlmacenTransaccionEditView(Transaccion f, List<Insumo> items, AbstractController controller, Dialog owner, boolean modal) {
        super(DialogType.NORMAL, controller, owner, modal);
        this.trans = f;
        this.items = items;
        initComponents();
        initTableModel();
        jListInsumos.setModel(new RestManagerListModel<>(items));

    }

    public void updateView(Transaccion f) {
        this.jXDatePicker.setDate(f.getFechaTransaccion());
        jXLabelVALORTRANS.setText(String.format("+%.2f", f.getValorTotalTransacciones()) + R.coinSuffix);
        jXLabelVALORMERMA.setText(String.format("-%.2f", f.getValorMerma()) + R.coinSuffix);

    }

    public void updateTransValue() {
        float merma = 0, total = 0;

        for (InsumoTransaccion x : model.getItems()) {
            switch (x.getTipoTransaccion()) {
                case "ENTRADA":
                case "SALIDA":
                    total += x.getValorTotalMonetario();
                    break;
                case "MERMA":
                    merma += x.getValorTotalMonetario();
                    break;
            }
        }
        trans.setValorMerma(merma);
        trans.setValorTotalTransacciones(total);
        updateView(trans);
    }

    public void initTableModel() {
        JComboBox<TransaccionDAO.TIpoModelo> tipoTrans = new JComboBox<>(TransaccionDAO.TIpoModelo.values());
        model = new RestManagerAbstractTableModel<InsumoTransaccion>(trans.getInsumoTransaccionList(), jXTable1) {

            @Override
            public int getColumnCount() {
                return 6;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return trans.getInsumoTransaccionList().get(rowIndex).getInsumo().getNombre();
                    case 1:
                        return TransaccionDAO.TIpoModelo.valueOf(trans.getInsumoTransaccionList().get(rowIndex).getTipoTransaccion());
                    case 2:
                        return trans.getInsumoTransaccionList().get(rowIndex).getCantidadTransferida();
                    case 3:
                        switch (trans.getInsumoTransaccionList().get(rowIndex).getTipoTransaccion()) {
                            case "MERMA":
                            case "SALIDA":
                                trans.getInsumoTransaccionList().get(rowIndex).setValorTotalMonetario(
                                        trans.getInsumoTransaccionList().get(rowIndex).getCantidadTransferida()
                                        * trans.getInsumoTransaccionList().get(rowIndex).getInsumo().getCostoPorUnidad());
                            case "ENTRADA":
                                updateTransValue();
                                return trans.getInsumoTransaccionList().get(rowIndex).getValorTotalMonetario();
                            default:
                                return 0;
                        }

                    case 4:
                        return trans.getInsumoTransaccionList().get(rowIndex).getDescripcionTransaccion();
                    case 5:
                        return trans.getInsumoTransaccionList().get(rowIndex).getDestino();
                    default:
                        return null;
                }

            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Nombre";
                    case 1:
                        return "Tipo Trans.";
                    case 2:
                        return "Cantidad";
                    case 3:
                        return "Valor";
                    case 4:
                        return "Descripcion";
                    case 5:
                        return "Destino";
                    default:
                        return null;
                }
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 1
                        || columnIndex == 2
                        || columnIndex == 4
                        || columnIndex == 5
                        || (columnIndex == 3
                        && getValueAt(rowIndex, 1).toString().equals("ENTRADA"));

            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 1:
                        trans.getInsumoTransaccionList().get(rowIndex).setTipoTransaccion(aValue.toString());
                        break;
                    case 2:
                        trans.getInsumoTransaccionList().get(rowIndex).setCantidadTransferida((Float.parseFloat((String) aValue)));
                        break;
                    case 3:
                        trans.getInsumoTransaccionList().get(rowIndex).setValorTotalMonetario((Float.parseFloat((String) aValue)));
                        break;
                    case 4:
                        trans.getInsumoTransaccionList().get(rowIndex).setDescripcionTransaccion((String) aValue);
                        break;
                    case 5:
                        trans.getInsumoTransaccionList().get(rowIndex).setDestino((String) aValue);
                        break;
                }
                fireTableRowsUpdated(rowIndex, rowIndex);
            }

        };
        jXTable1.setModel(model);

        TableColumn UmColumn = jXTable1.getColumnModel().getColumn(1);
        UmColumn.setCellEditor(new DefaultCellEditor(tipoTrans));

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
        jPanelNorte = new javax.swing.JPanel();
        jXDatePicker = new org.jdesktop.swingx.JXDatePicker();
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();
        jPanelListaInsumos = new javax.swing.JPanel();
        jPanelBuscar = new javax.swing.JPanel();
        jTextFieldBuscar = new javax.swing.JTextField();
        jXLabelBuscar = new org.jdesktop.swingx.JXLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListInsumos = new javax.swing.JList<>();
        jPanelTotales = new javax.swing.JPanel();
        jXLabel2 = new org.jdesktop.swingx.JXLabel();
        jXLabelVALORTRANS = new org.jdesktop.swingx.JXLabel();
        jXLabel3 = new org.jdesktop.swingx.JXLabel();
        jXLabelVALORMERMA = new org.jdesktop.swingx.JXLabel();
        jPanelControles = new javax.swing.JPanel();
        jButtonConfirmarTrans = new javax.swing.JButton();
        jButtonAgregar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.BorderLayout(0, 10));

        jPanelNorte.setLayout(new java.awt.BorderLayout());

        jXDatePicker.setEditable(false);
        jPanelNorte.add(jXDatePicker, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanelNorte, java.awt.BorderLayout.PAGE_START);

        jPanelTabla.setLayout(new java.awt.BorderLayout(Integer.parseInt(java.util.ResourceBundle.getBundle("Integers").getString("borderlayout_space")), 0));

        jXTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Tipo Trans", "Cantidad", "Valor", "Descripcion", "Destino"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jXTable1);

        jPanelTabla.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanelListaInsumos.setLayout(new java.awt.BorderLayout());

        jPanelBuscar.setLayout(new java.awt.BorderLayout(Integer.parseInt(java.util.ResourceBundle.getBundle("Integers").getString("flowlayout_space")), 0));

        jTextFieldBuscar.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldBuscar.setMinimumSize(new java.awt.Dimension(80, 26));
        jTextFieldBuscar.setPreferredSize(new java.awt.Dimension(80, 26));
        jPanelBuscar.add(jTextFieldBuscar, java.awt.BorderLayout.PAGE_END);

        jXLabelBuscar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jXLabelBuscar.setText(bundle.getString("label_buscar")); // NOI18N
        jPanelBuscar.add(jXLabelBuscar, java.awt.BorderLayout.CENTER);

        jPanelListaInsumos.add(jPanelBuscar, java.awt.BorderLayout.PAGE_START);

        jListInsumos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListInsumos.setFixedCellWidth(150);
        jListInsumos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListInsumosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jListInsumos);

        jPanelListaInsumos.add(jScrollPane2, java.awt.BorderLayout.LINE_START);

        jPanelTabla.add(jPanelListaInsumos, java.awt.BorderLayout.WEST);

        jXLabel2.setText(bundle.getString("label_total_transaccion")); // NOI18N

        jXLabelVALORTRANS.setForeground(new java.awt.Color(0, 153, 0));
        jXLabelVALORTRANS.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jXLabelVALORTRANS.setText("0000.00");
        jXLabelVALORTRANS.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N

        jXLabel3.setText(bundle.getString("label_total_merma")); // NOI18N

        jXLabelVALORMERMA.setForeground(new java.awt.Color(204, 0, 0));
        jXLabelVALORMERMA.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jXLabelVALORMERMA.setText("000.00");
        jXLabelVALORMERMA.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanelTotalesLayout = new javax.swing.GroupLayout(jPanelTotales);
        jPanelTotales.setLayout(jPanelTotalesLayout);
        jPanelTotalesLayout.setHorizontalGroup(
            jPanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTotalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTotalesLayout.createSequentialGroup()
                        .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jXLabelVALORTRANS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelTotalesLayout.createSequentialGroup()
                        .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jXLabelVALORMERMA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelTotalesLayout.setVerticalGroup(
            jPanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTotalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabelVALORTRANS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabelVALORMERMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanelTabla.add(jPanelTotales, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanelTabla, java.awt.BorderLayout.CENTER);

        jPanelControles.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, Integer.parseInt(java.util.ResourceBundle.getBundle("Integers").getString("flowlayout_short_space")), 5));

        jButtonConfirmarTrans.setText(bundle.getString("label_confirmar_transaccion")); // NOI18N
        jButtonConfirmarTrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarTransActionPerformed(evt);
            }
        });
        jPanelControles.add(jButtonConfirmarTrans);

        jButtonAgregar.setText(bundle.getString("label_agregar")); // NOI18N
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });
        jPanelControles.add(jButtonAgregar);

        jButtonEliminar.setText(bundle.getString("label_eliminar")); // NOI18N
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
        jPanelControles.add(jButtonEliminar);

        jButtonCancelar.setText(bundle.getString("label_cancelar")); // NOI18N
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jPanelControles.add(jButtonCancelar);

        getContentPane().add(jPanelControles, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jListInsumosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListInsumosMouseClicked
        if (evt.getClickCount() == 2) {
            addToTable();
        }
    }//GEN-LAST:event_jListInsumosMouseClicked

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        addToTable();
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        deleteFromTable();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonConfirmarTransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarTransActionPerformed
            getController().createInstance(trans);
    }//GEN-LAST:event_jButtonConfirmarTransActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConfirmarTrans;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JList<Insumo> jListInsumos;
    private javax.swing.JPanel jPanelBuscar;
    private javax.swing.JPanel jPanelControles;
    private javax.swing.JPanel jPanelListaInsumos;
    private javax.swing.JPanel jPanelNorte;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JPanel jPanelTotales;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldBuscar;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker;
    private org.jdesktop.swingx.JXLabel jXLabel2;
    private org.jdesktop.swingx.JXLabel jXLabel3;
    private org.jdesktop.swingx.JXLabel jXLabelBuscar;
    private org.jdesktop.swingx.JXLabel jXLabelVALORMERMA;
    private org.jdesktop.swingx.JXLabel jXLabelVALORTRANS;
    private org.jdesktop.swingx.JXTable jXTable1;
    // End of variables declaration//GEN-END:variables

    private void addToTable() {
        Insumo i = jListInsumos.getSelectedValue();
        if (i == null) {
            throw new NoSelectedException();
        }
        InsumoTransaccionPK insumoPK = new InsumoTransaccionPK(i.getCodInsumo(), trans.getFechaTransaccion());

        InsumoTransaccion insumoT = new InsumoTransaccion(insumoPK);
        insumoT.setCantidadTransferida((float) 0);
        insumoT.setValorTotalMonetario((float) 0);
        insumoT.setDescripcionTransaccion("");
        insumoT.setInsumo(i);
        insumoT.setTransaccion(trans);
        insumoT.setTipoTransaccion(TransaccionDAO.TIpoModelo.ENTRADA.toString());
        insumoT.setHoraTransaccion(insumoPK.getTransaccionfechaTransaccion());

        model.addObject(insumoT);
    }

    private void deleteFromTable() {
        model.removeObject(model.getObjectAtSelectedRow());

    }

}

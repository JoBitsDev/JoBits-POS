/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen;

import com.jobits.pos.ui.AbstractDetailView;
import com.jobits.pos.ui.OldAbstractView;
import com.jobits.pos.ui.utils.OldAbstractCrossReferenePanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.xml.ws.handler.MessageContext;
import mdlaf.components.tabbedpane.MaterialTabbedPaneUI;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.controller.Controller;
import com.jobits.pos.controller.almacen.AlmacenManageController;
import com.jobits.pos.controller.almacen.AlmacenManageController.CheckBoxType;
import com.jobits.pos.controller.almacen.TransaccionDetailController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.exceptions.ValidatingException;
import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.InsumoAlmacen;
import com.jobits.pos.domain.models.InsumoAlmacenPK;
import com.jobits.pos.domain.models.TransaccionTransformacion;
import com.jobits.pos.adapters.repo.InsumoAlmacenDAO;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.utils.RestManagerAbstractTableModel;
import com.jobits.pos.ui.utils.RestManagerComboBoxModel;
import com.jobits.pos.ui.utils.utils;

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
    OldAbstractCrossReferenePanel<InsumoAlmacen, Insumo> model;
    OldAbstractCrossReferenePanel<TransaccionTransformacion, Insumo> modelTransformacion;
    final Color elaboracionColor = new Color(255, 255, 204);
    final String labelInsumoSleccionado = "<Seleccione un insumo en la tabla>";

    public AlmacenEditView(AbstractDetailController<Almacen> controller, OldAbstractView owner, Almacen instance) {
        super(instance, DialogType.FULL_SCREEN, controller, owner);
        initComponents();
        buttonGroup1.add(jRadioButtonSalida);
        buttonGroup1.add(jRadioButtonEntrada);
        buttonGroup1.add(jRadioButtonRebaja);
        buttonGroup1.add(jRadioButtonTraspaso);
        jComboBoxPuntoElab.setModel(new RestManagerComboBoxModel<>(getController().getCocinaList()));
        jComboBoxAlmacen.setModel(new RestManagerComboBoxModel<>(getController().getItems()));
        jComboBoxAlDestTransformacion.setModel(new RestManagerComboBoxModel<>(getController().getItems()));
        jComboBoxPuntoElab.setSelectedIndex(0);
        jComboBoxAlmacen.setSelectedIndex(0);

    }

    @Override
    public void updateView() {
        if (model != null) {
            model.getHandler().getTableModel().setItems(getController().getInsumoAlmacenList(getInstance()));
        } else {
            model = new OldAbstractCrossReferenePanel<InsumoAlmacen, Insumo>("Insumos", getController().getInsumoList()) {
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
                    getHandler().getTableModel().fireTableDataChanged();
                }

            };

        }
        modelTransformacion = new OldAbstractCrossReferenePanel<TransaccionTransformacion, Insumo>("Insumos", getController().getInsumoList()) {
            @Override
            public RestManagerAbstractTableModel<TransaccionTransformacion> getTableModel() {
                return new RestManagerAbstractTableModel<TransaccionTransformacion>(new ArrayList<TransaccionTransformacion>(), getjTableCrossReference()) {
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
                                return items.get(rowIndex).getCantidadCreada();
                            case 2:
                                return items.get(rowIndex).getCantidadUsada();
                            default:
                                return null;

                        }
                    }

                    @Override
                    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                        if (columnIndex == 1) {
                            getItems().get(rowIndex).setCantidadCreada((Float) aValue);
                            fireTableCellUpdated(rowIndex, rowIndex);
                        }
                        if (columnIndex == 2) {
                            getItems().get(rowIndex).setCantidadUsada((Float) aValue);
                            fireTableCellUpdated(rowIndex, rowIndex);
                        }
                    }

                    @Override
                    public String getColumnName(int column) {
                        switch (column) {
                            case 0:
                                return "Insumo";
                            case 1:
                                return "Cantidad creada";
                            case 2:
                                return "Cantidad usada";
                            default:
                                return null;
                        }
                    }

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnIndex > 0;
                    }

                    @Override
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnIndex > 0 ? Float.class : super.getColumnClass(columnIndex);
                    }
                };
            }

            @Override
            public TransaccionTransformacion transformK_T(Insumo selected) {
                TransaccionTransformacion nueva = new TransaccionTransformacion();
                nueva.setCantidadCreada((float) 0);
                nueva.setCantidadUsada((float) 0);
                nueva.setDireccionInversa(false);
                nueva.setInsumo(selected);
                return nueva;
            }
        };

        jXLabelValorTotal.setText(utils.setDosLugaresDecimales(getController().getInstance().getValorMonetario()));
        jTabbedPane1.setUI(new MaterialTabbedPaneUI());
    }

    @Override
    public void fetchComponentData() {
        jPanelTabla.add(model);
        jPanelTransformarEn.add(modelTransformacion);
        jLabelNombreAlmacen.setText(getInstance().getNombre());
        if (getInstance().getCentroElaboracion()) {
            jLabelNombreAlmacen.setForeground(elaboracionColor);
            jXLabelTotalAlmacen.setForeground(elaboracionColor);
            jXLabelValorTotal.setForeground(elaboracionColor);
        }

        model.getjTableCrossReference().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    getController().modificarStock(model.getHandler().getTableModel().getObjectAtSelectedRow().getInsumo());
                }
            }
        });
        model.setFinderVisible(true);
        model.getjTableCrossReference().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (model.getjTableCrossReference().getSelectedRow() != -1) {
                        jLabelInsumoSeleccionado.setText(model.getHandler().getTableModel().getObjectAtSelectedRow().getInsumo().toString());
                        jLabelInsumoSeleccionado.setForeground(Color.BLUE);
                        jLabelUMTransformacion.setText(model.getHandler().getTableModel().getObjectAtSelectedRow().getInsumo().getUm());
                    } else {
                        jLabelInsumoSeleccionado.setText(labelInsumoSleccionado);
                        jLabelInsumoSeleccionado.setForeground(Color.RED);
                        jLabelUMTransformacion.setText("U/M");
                    }
                }

            }
        });

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
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelTransaccion = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jButtonConfirmar = new javax.swing.JButton();
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
        jPanel13 = new javax.swing.JPanel();
        jSpinnerTransformar = new javax.swing.JSpinner();
        jLabelUMTransformacion = new javax.swing.JLabel();
        jPanelTransformarEn = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jComboBoxAlDestTransformacion = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jButtonConfirmarTransformacion = new javax.swing.JButton();
        jLabelInsumoSeleccionado = new org.jdesktop.swingx.JXLabel();
        jPanelTabla = new javax.swing.JPanel();
        jXPanelControles = new org.jdesktop.swingx.JXPanel();
        jPanel1 = new javax.swing.JPanel();
        jXLabelTotalAlmacen = new org.jdesktop.swingx.JXLabel();
        jXLabelValorTotal = new org.jdesktop.swingx.JXLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonVerFichasEntrada1 = new javax.swing.JButton();
        jButtonDarReporte = new javax.swing.JButton();
        jButtonModificarStock = new javax.swing.JButton();
        jButtonVerFichasEntrada = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFont(getFont());
        setMinimumSize(getMinimumSize());
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(new org.edisoncor.gui.util.DropShadowBorder());
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabelNombreAlmacen.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabelNombreAlmacen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNombreAlmacen.setText("<Nombre Almacen>");
        jPanel3.add(jLabelNombreAlmacen, java.awt.BorderLayout.CENTER);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/logout40.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, java.awt.BorderLayout.WEST);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Operaciones con", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(0, 153, 153))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(300, 463));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 204));
        jTabbedPane1.setForeground(new java.awt.Color(0, 153, 153));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(250, 443));
        jTabbedPane1.setOpaque(true);
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(250, 435));

        jPanelTransaccion.setBackground(new java.awt.Color(204, 255, 255));
        jPanelTransaccion.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel9.setOpaque(false);
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
        flowLayout1.setAlignOnBaseline(true);
        jPanel9.setLayout(flowLayout1);

        jButtonConfirmar.setForeground(new java.awt.Color(0, 153, 153));
        jButtonConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/confirmar.png"))); // NOI18N
        jButtonConfirmar.setText("Confirmar");
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
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

        jPanelTransaccion.add(jPanel9, java.awt.BorderLayout.SOUTH);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Cantidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(0, 153, 153))); // NOI18N
        jPanel7.setOpaque(false);
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jSpinnerCantidad.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(1000000.0f), Float.valueOf(1.0f)));
        jSpinnerCantidad.setToolTipText("Cantidad");
        jSpinnerCantidad.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanel7.add(jSpinnerCantidad);

        jPanelTransaccion.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Operaciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(0, 153, 153))); // NOI18N
        jPanel8.setOpaque(false);
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.PAGE_AXIS));

        jPanelEntrada.setOpaque(false);
        jPanelEntrada.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jRadioButtonEntrada.setSelected(true);
        jRadioButtonEntrada.setText(bundle.getString("label_entrada")); // NOI18N
        jRadioButtonEntrada.setToolTipText("");
        jRadioButtonEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonEntradaActionPerformed(evt);
            }
        });
        jPanelEntrada.add(jRadioButtonEntrada);

        jPanel10.setOpaque(false);

        jSpinnerMonto.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(1000000.0f), Float.valueOf(1.0f)));
        jSpinnerMonto.setToolTipText("Monto");
        jSpinnerMonto.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanel10.add(jSpinnerMonto);

        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText(R.COIN_SUFFIX);
        jPanel10.add(jLabel1);

        jPanelEntrada.add(jPanel10);

        jPanel8.add(jPanelEntrada);

        jPanelTraspaso.setOpaque(false);
        jPanelTraspaso.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jRadioButtonTraspaso.setText(bundle.getString("label_traspaso")); // NOI18N
        jRadioButtonTraspaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTraspasoActionPerformed(evt);
            }
        });
        jPanelTraspaso.add(jRadioButtonTraspaso);

        jComboBoxAlmacen.setEnabled(false);
        jComboBoxAlmacen.setMinimumSize(new java.awt.Dimension(150, 27));
        jComboBoxAlmacen.setPreferredSize(new java.awt.Dimension(150, 27));
        jPanelTraspaso.add(jComboBoxAlmacen);

        jPanel8.add(jPanelTraspaso);

        jPaneldestino.setMinimumSize(new java.awt.Dimension(200, 61));
        jPaneldestino.setOpaque(false);
        jPaneldestino.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jRadioButtonSalida.setText(bundle.getString("label_salida")); // NOI18N
        jRadioButtonSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSalidaActionPerformed(evt);
            }
        });
        jPaneldestino.add(jRadioButtonSalida);

        jComboBoxPuntoElab.setEnabled(false);
        jComboBoxPuntoElab.setMinimumSize(new java.awt.Dimension(150, 27));
        jComboBoxPuntoElab.setPreferredSize(new java.awt.Dimension(150, 27));
        jPaneldestino.add(jComboBoxPuntoElab);

        jPanel8.add(jPaneldestino);

        jPanelRazon.setOpaque(false);
        jPanelRazon.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jRadioButtonRebaja.setText(bundle.getString("label_rebaja")); // NOI18N
        jRadioButtonRebaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonRebajaActionPerformed(evt);
            }
        });
        jPanelRazon.add(jRadioButtonRebaja);

        jTextFieldRebaja.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jTextFieldRebaja.setToolTipText("Razon de rebaja");
        jTextFieldRebaja.setEnabled(false);
        jTextFieldRebaja.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanelRazon.add(jTextFieldRebaja);

        jPanel8.add(jPanelRazon);

        jPanelTransaccion.add(jPanel8, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Transaccion", jPanelTransaccion);

        jPanelTransformacion.setBackground(new java.awt.Color(204, 255, 255));
        jPanelTransformacion.setLayout(new java.awt.BorderLayout());

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Transformar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(0, 153, 153))); // NOI18N
        jPanel13.setOpaque(false);
        jPanel13.setPreferredSize(new java.awt.Dimension(300, 70));
        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jSpinnerTransformar.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 1.0f));
        jSpinnerTransformar.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanel13.add(jSpinnerTransformar);

        jLabelUMTransformacion.setForeground(new java.awt.Color(0, 153, 153));
        jLabelUMTransformacion.setText("<U/M>");
        jPanel13.add(jLabelUMTransformacion);

        jPanelTransformacion.add(jPanel13, java.awt.BorderLayout.PAGE_START);

        jPanelTransformarEn.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "En...", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(0, 153, 153))); // NOI18N
        jPanelTransformarEn.setOpaque(false);
        jPanelTransformarEn.setLayout(new java.awt.BorderLayout());

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Hacia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(0, 153, 153))); // NOI18N
        jPanel12.setOpaque(false);

        jComboBoxAlDestTransformacion.setMinimumSize(new java.awt.Dimension(150, 27));
        jComboBoxAlDestTransformacion.setPreferredSize(new java.awt.Dimension(150, 27));
        jPanel12.add(jComboBoxAlDestTransformacion);

        jPanelTransformarEn.add(jPanel12, java.awt.BorderLayout.PAGE_END);

        jPanelTransformacion.add(jPanelTransformarEn, java.awt.BorderLayout.CENTER);

        jPanel11.setOpaque(false);
        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout();
        flowLayout2.setAlignOnBaseline(true);
        jPanel11.setLayout(flowLayout2);

        jButtonConfirmarTransformacion.setForeground(new java.awt.Color(0, 153, 153));
        jButtonConfirmarTransformacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/confirmar.png"))); // NOI18N
        jButtonConfirmarTransformacion.setText("Confirmar");
        jButtonConfirmarTransformacion.setToolTipText(bundle.getString("label_confirmar")); // NOI18N
        jButtonConfirmarTransformacion.setBorderPainted(false);
        jButtonConfirmarTransformacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonConfirmarTransformacion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonConfirmarTransformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarTransformacionActionPerformed(evt);
            }
        });
        jPanel11.add(jButtonConfirmarTransformacion);

        jPanelTransformacion.add(jPanel11, java.awt.BorderLayout.SOUTH);

        jTabbedPane1.addTab("Transformacion", jPanelTransformacion);

        jPanel6.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jLabelInsumoSeleccionado.setForeground(new java.awt.Color(255, 0, 0));
        jLabelInsumoSeleccionado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelInsumoSeleccionado.setText("<Seleccione un insumo en la tabla>");
        jLabelInsumoSeleccionado.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabelInsumoSeleccionado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelInsumoSeleccionado.setLineWrap(true);
        jPanel6.add(jLabelInsumoSeleccionado, java.awt.BorderLayout.PAGE_START);

        jPanel5.add(jPanel6, java.awt.BorderLayout.EAST);

        jPanelTabla.setBackground(new java.awt.Color(204, 204, 204));
        jPanelTabla.setLayout(new java.awt.BorderLayout());
        jPanel5.add(jPanelTabla, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        jXPanelControles.setBackground(new java.awt.Color(204, 204, 204));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowBottomShadow(false);
        dropShadowBorder1.setShowTopShadow(true);
        jXPanelControles.setBorder(dropShadowBorder1);
        jXPanelControles.setLayout(new javax.swing.BoxLayout(jXPanelControles, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(406, 40));
        jPanel1.setLayout(new java.awt.BorderLayout(5, 5));

        jXLabelTotalAlmacen.setText(bundle.getString("label_valor_almacen")); // NOI18N
        jXLabelTotalAlmacen.setFont(new java.awt.Font("Apple Braille", 0, 24)); // NOI18N
        jPanel1.add(jXLabelTotalAlmacen, java.awt.BorderLayout.WEST);

        jXLabelValorTotal.setText("0.00 CUC");
        jXLabelValorTotal.setFont(new java.awt.Font("Apple Braille", 0, 24)); // NOI18N
        jPanel1.add(jXLabelValorTotal, java.awt.BorderLayout.EAST);

        jXPanelControles.add(jPanel1);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        jButtonVerFichasEntrada1.setText(bundle.getString("label_nuevaFactura")); // NOI18N
        jButtonVerFichasEntrada1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerFichasEntrada1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonVerFichasEntrada1);

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
        getController().modificarStock(model.getHandler().getTableModel().getObjectAtSelectedRow().getInsumo());
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

    private void jRadioButtonTraspasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTraspasoActionPerformed
        onCheckedCheckBox(CheckBoxType.TRASPASO);
    }//GEN-LAST:event_jRadioButtonTraspasoActionPerformed

    private void jButtonConfirmarTransformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarTransformacionActionPerformed
        getController().crearTransformacion(model.getHandler().getTableModel().getObjectAtSelectedRow()
                ,(float)jSpinnerTransformar.getValue()
                ,modelTransformacion.getHandler().getTableModel().getItems()
                ,(Almacen)jComboBoxAlDestTransformacion.getSelectedItem());
    }//GEN-LAST:event_jButtonConfirmarTransformacionActionPerformed

    private void jButtonVerFichasEntrada1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerFichasEntrada1ActionPerformed
       OperacionView view =  new OperacionView( new AlmacenManageController(getInstance()), this, true);
    }//GEN-LAST:event_jButtonVerFichasEntrada1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JButton jButtonConfirmarTransformacion;
    private javax.swing.JButton jButtonDarReporte;
    private javax.swing.JButton jButtonModificarStock;
    private javax.swing.JButton jButtonVerFichasEntrada;
    private javax.swing.JButton jButtonVerFichasEntrada1;
    private javax.swing.JComboBox<Almacen> jComboBoxAlDestTransformacion;
    private javax.swing.JComboBox<Almacen> jComboBoxAlmacen;
    private javax.swing.JComboBox<Cocina> jComboBoxPuntoElab;
    private javax.swing.JLabel jLabel1;
    private org.jdesktop.swingx.JXLabel jLabelInsumoSeleccionado;
    private javax.swing.JLabel jLabelNombreAlmacen;
    private javax.swing.JLabel jLabelUMTransformacion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
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
    private javax.swing.JPanel jPanelTransformarEn;
    private javax.swing.JPanel jPanelTraspaso;
    private javax.swing.JPanel jPaneldestino;
    private javax.swing.JRadioButton jRadioButtonEntrada;
    private javax.swing.JRadioButton jRadioButtonRebaja;
    private javax.swing.JRadioButton jRadioButtonSalida;
    private javax.swing.JRadioButton jRadioButtonTraspaso;
    private javax.swing.JSpinner jSpinnerCantidad;
    private javax.swing.JSpinner jSpinnerMonto;
    private javax.swing.JSpinner jSpinnerTransformar;
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
            throw new ValidatingException(this, "La cantidad no puede ser 0");
        }
        if (jRadioButtonEntrada.isSelected()) {
            getController().crearTransaccion(null,ins, 0, null, null, cantidad, monto, causa,true);
        }
        if (jRadioButtonSalida.isSelected()) {
            getController().crearTransaccion(null,ins, 1, (Cocina) jComboBoxPuntoElab.getSelectedItem(), null, cantidad, monto, causa,true);
        }
        if (jRadioButtonRebaja.isSelected()) {
            if (causa == null || causa.isEmpty()) {
                throw new ValidatingException(this, "La causa de la rebaja no puede estar vacía");
            }
            getController().crearTransaccion(null,ins, 2, null, null, cantidad, monto, causa,true);
        }
        if (jRadioButtonTraspaso.isSelected()) {
            if (getInstance().equals(jComboBoxAlmacen.getSelectedItem())) {
                throw new ValidatingException(this, "El almacen destino para el traspaso debe ser diferente al abierto");
            }
            getController().crearTransaccion(null,ins, 3, null, (Almacen) jComboBoxAlmacen.getSelectedItem(), cantidad, monto, causa,true);
        }

//        model.getHandler().getTableModel().fireTableDataChanged();
    }

    private void onCheckedCheckBox(CheckBoxType tipo) {
        jTextFieldRebaja.setEnabled(tipo == CheckBoxType.REBAJA);
        jComboBoxAlmacen.setEnabled(tipo == CheckBoxType.TRASPASO);
        jComboBoxPuntoElab.setEnabled(tipo == CheckBoxType.SALIDA);
        jSpinnerMonto.setEnabled(tipo == CheckBoxType.ENTRADA);
    }

  

}

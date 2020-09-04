/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen;

import com.jobits.pos.ui.AbstractDetailView;
import com.jobits.pos.ui.utils.OldAbstractCrossReferenePanel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.util.ArrayList;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.almacen.AlmacenManageController;
import com.jobits.pos.controller.almacen.AlmacenManageController.CheckBoxType;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.exceptions.ValidatingException;
import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.InsumoAlmacen;
import com.jobits.pos.domain.models.InsumoAlmacenPK;
import com.jobits.pos.domain.models.TransaccionTransformacion;
import com.jobits.pos.adapters.repo.impl.InsumoAlmacenDAO;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.utils.RestManagerAbstractTableModel;
import com.jobits.pos.ui.utils.RestManagerComboBoxModel;
import com.jobits.pos.ui.utils.utils;
import java.awt.Frame;

import com.jobits.ui.components.MaterialComponentsFactory;

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

    public AlmacenEditView(AbstractDetailController<Almacen> controller, Frame owner, Almacen instance) {
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
        jTabbedPane1.setUI(MaterialComponentsFactory.UI.getTabbedPaneUI());
    }

    @Override
    public void fetchComponentData() {
        jPanelTabla.add(model);
        jPanelTransformarEn.add(modelTransformacion);
        jLabelNombreAlmacen.setText(getInstance().getNombre());
        if (getInstance().getCentroElaboracion()) {
            jLabelNombreAlmacen.setForeground(elaboracionColor);
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
        jButton1 = MaterialComponentsFactory.Buttons.getCloseButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabelInsumoSeleccionado = new org.jdesktop.swingx.JXLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelTransaccion = new javax.swing.JPanel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        jPanelCantidad = new javax.swing.JPanel();
        jSpinnerCantidad = new javax.swing.JSpinner();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        jPanelOperaciones = new javax.swing.JPanel();
        jPanelEntrada = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jRadioButtonEntrada = new javax.swing.JRadioButton();
        jSpinnerMonto = new javax.swing.JSpinner();
        jPanelTraspaso = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jRadioButtonTraspaso = new javax.swing.JRadioButton();
        jComboBoxAlmacen = new javax.swing.JComboBox<>();
        jPaneldestino = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jRadioButtonSalida = new javax.swing.JRadioButton();
        jComboBoxPuntoElab = new javax.swing.JComboBox<>();
        jPanelRazon = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jRadioButtonRebaja = new javax.swing.JRadioButton();
        jTextFieldRebaja = MaterialComponentsFactory.Input.getTextField("", "Razon de rebaja");
        jPanelConfirmar = new javax.swing.JPanel();
        jButtonConfirmar = MaterialComponentsFactory.Buttons.getAcceptButton();
        jPanelTransformacion = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanelTransformar = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jSpinnerTransformar = new javax.swing.JSpinner();
        jLabelUMTransformacion = new javax.swing.JLabel();
        jPanelHacia = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jComboBoxAlDestTransformacion = new javax.swing.JComboBox<>();
        jPanelTransformarEn = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jButtonConfirmarTransformacion = MaterialComponentsFactory.Buttons.getAcceptButton();
        jPanelTabla = new javax.swing.JPanel();
        jXPanelControles = new org.jdesktop.swingx.JXPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jXLabelValorTotal = new org.jdesktop.swingx.JXLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonNuevaFactura = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jButtonModificarStock = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jButtonTransacciones = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jButtonDarReporte = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jButtonResumen = MaterialComponentsFactory.Buttons.getOutlinedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFont(getFont());
        setMinimumSize(getMaximumSize());
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(new org.edisoncor.gui.util.DropShadowBorder());
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabelNombreAlmacen.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabelNombreAlmacen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNombreAlmacen.setText("<Nombre Almacen>");
        jPanel3.add(jLabelNombreAlmacen, java.awt.BorderLayout.CENTER);

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
        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 20));
        jPanel6.setPreferredSize(new java.awt.Dimension(400, 463));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Operaciones con: ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N
        jPanel14.setLayout(new java.awt.BorderLayout());

        jLabelInsumoSeleccionado.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 10, 20, 10));
        jLabelInsumoSeleccionado.setForeground(new java.awt.Color(255, 0, 0));
        jLabelInsumoSeleccionado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelInsumoSeleccionado.setText("<Seleccione un insumo en la tabla>");
        jLabelInsumoSeleccionado.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabelInsumoSeleccionado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabelInsumoSeleccionado.setLineWrap(true);
        jPanel14.add(jLabelInsumoSeleccionado, java.awt.BorderLayout.NORTH);

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 204));
        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(250, 443));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(250, 435));

        jPanelTransaccion.setLayout(new java.awt.BorderLayout(0, 5));
        jPanelTransaccion.add(filler6, java.awt.BorderLayout.PAGE_END);

        jPanelCantidad.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(20, 1, 20, 1), "Cantidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N
        jPanelCantidad.setOpaque(false);
        jPanelCantidad.setLayout(new java.awt.BorderLayout());

        jSpinnerCantidad.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(1000000.0f), Float.valueOf(1.0f)));
        jSpinnerCantidad.setToolTipText("Cantidad");
        jSpinnerCantidad.setAutoscrolls(true);
        jSpinnerCantidad.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanelCantidad.add(jSpinnerCantidad, java.awt.BorderLayout.CENTER);
        jPanelCantidad.add(filler1, java.awt.BorderLayout.LINE_START);
        jPanelCantidad.add(filler2, java.awt.BorderLayout.LINE_END);
        jPanelCantidad.add(filler7, java.awt.BorderLayout.PAGE_START);

        jPanelTransaccion.add(jPanelCantidad, java.awt.BorderLayout.PAGE_START);

        jPanelOperaciones.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Operaciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N
        jPanelOperaciones.setOpaque(false);
        jPanelOperaciones.setLayout(new java.awt.GridLayout(4, 1));

        jPanelEntrada.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 1, 20), "Entrada (" + R.COIN_SUFFIX + ")"));
        jPanelEntrada.setMaximumSize(new java.awt.Dimension(2147483647, 80));
        jPanelEntrada.setOpaque(false);
        jPanelEntrada.setLayout(new java.awt.BorderLayout(5, 5));

        jRadioButtonEntrada.setSelected(true);
        jRadioButtonEntrada.setToolTipText("");
        jRadioButtonEntrada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jRadioButtonEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonEntradaActionPerformed(evt);
            }
        });
        jPanelEntrada.add(jRadioButtonEntrada, java.awt.BorderLayout.WEST);

        jSpinnerMonto.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(1000000.0f), Float.valueOf(1.0f)));
        jSpinnerMonto.setToolTipText("Monto");
        jSpinnerMonto.setMaximumSize(new java.awt.Dimension(150, 26));
        jSpinnerMonto.setMinimumSize(new java.awt.Dimension(150, 26));
        jSpinnerMonto.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanelEntrada.add(jSpinnerMonto, java.awt.BorderLayout.CENTER);

        jPanelOperaciones.add(jPanelEntrada);

        jPanelTraspaso.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 20), "Traspaso"));
        jPanelTraspaso.setMaximumSize(new java.awt.Dimension(32767, 80));
        jPanelTraspaso.setOpaque(false);
        jPanelTraspaso.setLayout(new java.awt.BorderLayout(5, 5));

        jRadioButtonTraspaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTraspasoActionPerformed(evt);
            }
        });
        jPanelTraspaso.add(jRadioButtonTraspaso, java.awt.BorderLayout.WEST);

        jComboBoxAlmacen.setEnabled(false);
        jComboBoxAlmacen.setMaximumSize(new java.awt.Dimension(150, 26));
        jComboBoxAlmacen.setMinimumSize(new java.awt.Dimension(150, 26));
        jComboBoxAlmacen.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanelTraspaso.add(jComboBoxAlmacen, java.awt.BorderLayout.CENTER);

        jPanelOperaciones.add(jPanelTraspaso);

        jPaneldestino.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 20), "Salida"));
        jPaneldestino.setMaximumSize(new java.awt.Dimension(2147483647, 80));
        jPaneldestino.setMinimumSize(new java.awt.Dimension(200, 61));
        jPaneldestino.setOpaque(false);
        jPaneldestino.setLayout(new java.awt.BorderLayout(5, 5));

        jRadioButtonSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSalidaActionPerformed(evt);
            }
        });
        jPaneldestino.add(jRadioButtonSalida, java.awt.BorderLayout.WEST);

        jComboBoxPuntoElab.setEnabled(false);
        jComboBoxPuntoElab.setMaximumSize(new java.awt.Dimension(150, 26));
        jComboBoxPuntoElab.setMinimumSize(new java.awt.Dimension(150, 26));
        jComboBoxPuntoElab.setPreferredSize(new java.awt.Dimension(150, 26));
        jPaneldestino.add(jComboBoxPuntoElab, java.awt.BorderLayout.CENTER);

        jPanelOperaciones.add(jPaneldestino);

        jPanelRazon.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 20), "Rebaja"));
        jPanelRazon.setMaximumSize(new java.awt.Dimension(2147483647, 80));
        jPanelRazon.setOpaque(false);
        jPanelRazon.setLayout(new java.awt.BorderLayout(5, 5));

        jRadioButtonRebaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonRebajaActionPerformed(evt);
            }
        });
        jPanelRazon.add(jRadioButtonRebaja, java.awt.BorderLayout.WEST);

        jTextFieldRebaja.setEnabled(false);
        jTextFieldRebaja.setMaximumSize(new java.awt.Dimension(150, 26));
        jTextFieldRebaja.setMinimumSize(new java.awt.Dimension(150, 26));
        jTextFieldRebaja.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanelRazon.add(jTextFieldRebaja, java.awt.BorderLayout.CENTER);

        jPanelOperaciones.add(jPanelRazon);

        jPanelTransaccion.add(jPanelOperaciones, java.awt.BorderLayout.CENTER);

        jPanelConfirmar.setOpaque(false);
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
        flowLayout1.setAlignOnBaseline(true);
        jPanelConfirmar.setLayout(flowLayout1);

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
        jPanelConfirmar.add(jButtonConfirmar);

        jPanelTransaccion.add(jPanelConfirmar, java.awt.BorderLayout.PAGE_END);

        jTabbedPane1.addTab("Transaccion", jPanelTransaccion);

        jPanelTransformacion.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.GridLayout());

        jPanelTransformar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Transformar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanelTransformar.setMinimumSize(new java.awt.Dimension(0, 60));
        jPanelTransformar.setOpaque(false);
        jPanelTransformar.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanelTransformar.setLayout(new java.awt.BorderLayout());

        jSpinnerTransformar.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 1.0f));
        jSpinnerTransformar.setMaximumSize(new java.awt.Dimension(150, 26));
        jSpinnerTransformar.setMinimumSize(new java.awt.Dimension(50, 26));
        jSpinnerTransformar.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanelTransformar.add(jSpinnerTransformar, java.awt.BorderLayout.CENTER);

        jLabelUMTransformacion.setText("<U/M>");
        jPanelTransformar.add(jLabelUMTransformacion, java.awt.BorderLayout.EAST);

        jPanel8.add(jPanelTransformar);

        jPanelHacia.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Hacia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanelHacia.setOpaque(false);
        jPanelHacia.setPreferredSize(new java.awt.Dimension(170, 60));

        jComboBoxAlDestTransformacion.setMaximumSize(new java.awt.Dimension(150, 26));
        jComboBoxAlDestTransformacion.setMinimumSize(new java.awt.Dimension(150, 26));
        jComboBoxAlDestTransformacion.setPreferredSize(new java.awt.Dimension(150, 27));
        jPanelHacia.add(jComboBoxAlDestTransformacion);

        jPanel8.add(jPanelHacia);

        jPanelTransformacion.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanelTransformarEn.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "En...", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanelTransformarEn.setOpaque(false);
        jPanelTransformarEn.setLayout(new java.awt.BorderLayout());
        jPanelTransformacion.add(jPanelTransformarEn, java.awt.BorderLayout.CENTER);

        jPanel11.setOpaque(false);
        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout();
        flowLayout2.setAlignOnBaseline(true);
        jPanel11.setLayout(flowLayout2);

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

        jPanel14.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel14, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel6, java.awt.BorderLayout.EAST);

        jPanelTabla.setBackground(new java.awt.Color(204, 204, 204));
        jPanelTabla.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 10));
        jPanelTabla.setLayout(new java.awt.BorderLayout());
        jPanel5.add(jPanelTabla, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        jXPanelControles.setBackground(new java.awt.Color(204, 204, 204));
        jXPanelControles.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 20, 20));
        jXPanelControles.setLayout(new javax.swing.BoxLayout(jXPanelControles, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor total en almacen", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N
        jPanel7.setMinimumSize(new java.awt.Dimension(690, 90));
        jPanel7.setPreferredSize(new java.awt.Dimension(690, 100));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(406, 40));
        jPanel1.setLayout(new java.awt.BorderLayout(5, 5));

        jXLabelValorTotal.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        jXLabelValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jXLabelValorTotal.setText("0.00 CUC");
        jXLabelValorTotal.setFocusable(false);
        jXLabelValorTotal.setFont(new java.awt.Font("Apple Braille", 0, 24)); // NOI18N
        jPanel1.add(jXLabelValorTotal, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel1, java.awt.BorderLayout.EAST);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        jButtonNuevaFactura.setText("Nueva Factura");
        jButtonNuevaFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevaFacturaActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonNuevaFactura);

        jButtonModificarStock.setText("Modificar Stock");
        jButtonModificarStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarStockActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonModificarStock);

        jButtonTransacciones.setText("Transacciones");
        jButtonTransacciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTransaccionesActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonTransacciones);

        jButtonDarReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonDarReporte.setText("Reporte");
        jButtonDarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDarReporteActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonDarReporte);

        jButtonResumen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonResumen.setText("Resumen");
        jButtonResumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResumenActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonResumen);

        jPanel7.add(jPanel2, java.awt.BorderLayout.CENTER);

        jXPanelControles.add(jPanel7);

        getContentPane().add(jXPanelControles, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void jRadioButtonRebajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonRebajaActionPerformed
        onCheckedCheckBox(CheckBoxType.REBAJA);
    }//GEN-LAST:event_jRadioButtonRebajaActionPerformed

    private void jRadioButtonTraspasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTraspasoActionPerformed
        onCheckedCheckBox(CheckBoxType.TRASPASO);
    }//GEN-LAST:event_jRadioButtonTraspasoActionPerformed

    private void jButtonConfirmarTransformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarTransformacionActionPerformed
        getController().crearTransformacion(model.getHandler().getTableModel().getObjectAtSelectedRow(),
                (float) jSpinnerTransformar.getValue(),
                modelTransformacion.getHandler().getTableModel().getItems(),
                (Almacen) jComboBoxAlDestTransformacion.getSelectedItem());
    }//GEN-LAST:event_jButtonConfirmarTransformacionActionPerformed

    private void jButtonNuevaFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevaFacturaActionPerformed
        OperacionView view = new OperacionView(new AlmacenManageController(getInstance()), this, true);
    }//GEN-LAST:event_jButtonNuevaFacturaActionPerformed

    private void jButtonModificarStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarStockActionPerformed
        getController().modificarStock(model.getHandler().getTableModel().getObjectAtSelectedRow().getInsumo());
    }//GEN-LAST:event_jButtonModificarStockActionPerformed

    private void jButtonTransaccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTransaccionesActionPerformed
        getController().verTransacciones(getInstance());
    }//GEN-LAST:event_jButtonTransaccionesActionPerformed

    private void jButtonDarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDarReporteActionPerformed
        getController().imprimirReporteParaCompras(getInstance());

    }//GEN-LAST:event_jButtonDarReporteActionPerformed

    private void jButtonResumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResumenActionPerformed
        getController().imprimirResumenAlmacen(getInstance());
    }//GEN-LAST:event_jButtonResumenActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JButton jButtonConfirmarTransformacion;
    private javax.swing.JButton jButtonDarReporte;
    private javax.swing.JButton jButtonModificarStock;
    private javax.swing.JButton jButtonNuevaFactura;
    private javax.swing.JButton jButtonResumen;
    private javax.swing.JButton jButtonTransacciones;
    private javax.swing.JComboBox<Almacen> jComboBoxAlDestTransformacion;
    private javax.swing.JComboBox<Almacen> jComboBoxAlmacen;
    private javax.swing.JComboBox<Cocina> jComboBoxPuntoElab;
    private org.jdesktop.swingx.JXLabel jLabelInsumoSeleccionado;
    private javax.swing.JLabel jLabelNombreAlmacen;
    private javax.swing.JLabel jLabelUMTransformacion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelCantidad;
    private javax.swing.JPanel jPanelConfirmar;
    private javax.swing.JPanel jPanelEntrada;
    private javax.swing.JPanel jPanelHacia;
    private javax.swing.JPanel jPanelOperaciones;
    private javax.swing.JPanel jPanelRazon;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JPanel jPanelTransaccion;
    private javax.swing.JPanel jPanelTransformacion;
    private javax.swing.JPanel jPanelTransformar;
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
            getController().crearTransaccion(null, ins, 0, null, null, cantidad, monto, causa, true);
        }
        if (jRadioButtonSalida.isSelected()) {
            getController().crearTransaccion(null, ins, 1, (Cocina) jComboBoxPuntoElab.getSelectedItem(), null, cantidad, monto, causa, true);
        }
        if (jRadioButtonRebaja.isSelected()) {
            if (causa == null || causa.isEmpty()) {
                throw new ValidatingException(this, "La causa de la rebaja no puede estar vacía");
            }
            getController().crearTransaccion(null, ins, 2, null, null, cantidad, monto, causa, true);
        }
        if (jRadioButtonTraspaso.isSelected()) {
            if (getInstance().equals(jComboBoxAlmacen.getSelectedItem())) {
                throw new ValidatingException(this, "El almacen destino para el traspaso debe ser diferente al abierto");
            }
            getController().crearTransaccion(null, ins, 3, null, (Almacen) jComboBoxAlmacen.getSelectedItem(), cantidad, monto, causa, true);
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

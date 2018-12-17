/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.Insumo;

import GUI.Components.JSpinner;
import GUI.Views.AbstractDetailView;
import GUI.Views.util.TableWithComboBoxAutoComplete;
import com.jidesoft.dialog.JideOptionPane;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteComboBoxEditor;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

import restManager.controller.AbstractDialogController;
import restManager.controller.insumo.InsumoCreateEditController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.NoSelectedException;
import restManager.persistencia.Almacen;
import restManager.persistencia.Insumo;
import restManager.persistencia.InsumoElaborado;
import restManager.persistencia.InsumoElaboradoPK;
import restManager.persistencia.ProductoInsumo;
import restManager.persistencia.ProductoInsumoPK;
import restManager.persistencia.ProductoVenta;
import restManager.resources.R;
import restManager.resources.RegularExpressions;
import restManager.util.RestManagerAbstractTableModel;
import restManager.util.RestManagerComboBoxListener;
import restManager.util.RestManagerComboBoxModel;

/**
 *
 * @author Jorge
 */
public class InsumoCreateEditView extends AbstractDetailView<Insumo> {

    TableWithComboBoxAutoComplete<Insumo, InsumoElaborado> tableIngElab;

    public InsumoCreateEditView(AbstractDialogController controller, Frame owner, boolean modal, Insumo ins) {
        super(ins, DialogType.DEFINED, controller, owner, modal);
        initComponents();
        hideCrossReferenceDialog();
        setElaboradoTable();

    }

    public InsumoCreateEditView(AbstractDialogController controller, Dialog owner, boolean modal, Insumo ins) {
        super(ins, DialogType.DEFINED, controller, owner, modal);
        initComponents();
        hideCrossReferenceDialog();
        setElaboradoTable();
    }

    /**
     * TODO: arreglar este mojon
     */
    @Override
    public void updateView() {
        updateTable(getInstance().getInsumoElaboradoList());
        updateComboBoxes(getController().getAlmacenList());
        updatePanelInputs();

    }

    @Override
    public InsumoCreateEditController getController() {
        return (InsumoCreateEditController) super.getController(); //To change body of generated methods, choose Tools | Templates.
    }

    private void updateTable(List<InsumoElaborado> items) {
        tableIngElab = new TableWithComboBoxAutoComplete<Insumo, InsumoElaborado>(jTableInsElab,
                jButtonAgregarIns, jButtonDeleteIns,
                jComboBoxAutoCompleteIns,
                new RestManagerAbstractTableModel<InsumoElaborado>(items, jTableInsElab) {

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 3:
                        items.get(rowIndex).setCantidad((float) aValue);
                        break;
                    case 4:
                        items.get(rowIndex).setCosto((float) aValue);
                        updateLabelCost();
                        break;
                }
                fireTableCellUpdated(rowIndex, columnIndex);

            }

            @Override
            public int getColumnCount() {
                return 5;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return items.get(rowIndex).getInsumo1().getCodInsumo();
                    case 1:
                        return items.get(rowIndex).getInsumo1().getNombre();
                    case 2:
                        return items.get(rowIndex).getInsumo1().getUm();
                    case 3:
                        return items.get(rowIndex).getCantidad();
                    case 4:
                        return items.get(rowIndex).getCosto();
                    default:
                        return null;

                }
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Codigo";
                    case 1:
                        return "Nombre";
                    case 2:
                        return "UM";
                    case 3:
                        return "Cantidad";
                    case 4:
                        return "Costo";
                    default:
                        return null;
                }
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return Float.class;
                    case 4:
                        return Float.class;
                    default:
                        return null;
                }
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 3 || columnIndex == 4;
            }

        },
                new RestManagerComboBoxModel<Insumo>(getController().getItems())) {
            @Override
            public InsumoElaborado transformK_to_T(Insumo selected) {
                InsumoElaborado newInsumo = new InsumoElaborado();
                InsumoElaboradoPK pk = new InsumoElaboradoPK(instance.getCodInsumo(), selected.getCodInsumo());
                newInsumo.setInsumoElaboradoPK(pk);
                newInsumo.setInsumo(instance);
                newInsumo.setInsumo1(selected);
                newInsumo.setCantidad(0);
                newInsumo.setCosto(0);
                return newInsumo;
            }

        };

        new TableWithComboBoxAutoComplete<ProductoVenta, ProductoInsumo>(jTableCrossReference, jButtonAgregarProd,
                jButtonDeleteProd, jComboBoxAutoComplete,
                new RestManagerAbstractTableModel<ProductoInsumo>(instance.getProductoInsumoList(), jTableCrossReference) {
            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return items.get(rowIndex).getProductoVenta().getPCod();
                    case 1:
                        return items.get(rowIndex).getProductoVenta().getNombre();
                    case 2:
                        return items.get(rowIndex).getInsumo().getUm();
                    case 3:
                        return items.get(rowIndex).getCantidad();
                    default:
                        return null;
                }
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Código";
                    case 1:
                        return "Nombre";
                    case 2:
                        return "U/M";
                    case 3:
                        return "Cantidad";
                    default:
                        return null;
                }
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 3;
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 3:
                        items.get(rowIndex).setCantidad(Float.parseFloat((String) aValue));
                        fireTableCellUpdated(rowIndex, columnIndex);
                        break;

                }

            }
        },
                new RestManagerComboBoxModel<ProductoVenta>(getController().getProductoList())) {
            @Override
            public ProductoInsumo transformK_to_T(ProductoVenta selected) {
                ProductoInsumo ret = new ProductoInsumo();
                ProductoInsumoPK pInsPK = new ProductoInsumoPK(selected.getPCod(), instance.getCodInsumo());
                ret.setProductoInsumoPK(pInsPK);
                ret.setProductoVenta(selected);
                ret.setInsumo(instance);
                ret.setCantidad(1);
                ret.setCosto(0);
                return ret;
            }
        };

    }

    private void updatePanelInputs() {

        jTextFieldNombre.setText(instance.getNombre());
        jSpinnerCosto.setValue(instance.getCostoPorUnidad());
        jSpinnerCantidadExistente.setValue(instance.getCantidadExistente());
        jSpinnerEstimacionStock.setValue(instance.getStockEstimation());
        jComboBoxUM.setSelectedItem(R.UM.valueOf(instance.getUm()));
        jComboBoxAlmacen.setSelectedItem(instance.getAlmacencodAlmacen());
        if (instance.getElaborado()) {
            jCheckBoxElaborado.setSelected(true);
            jTableInsElab.setEnabled(true);
            jSpinnerCantidadCreada.setValue(instance.getCantidadCreada());
        }

    }

    private void updateComboBoxes(List<Almacen> items) {
        items.forEach((item) -> {
            jComboBoxAlmacen.addItem(item);
        });
        for (R.UM x : R.UM.values()) {
            jComboBoxUM.addItem(x);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXPanelInputs = new org.jdesktop.swingx.JXPanel();
        jXLabelNombre = new org.jdesktop.swingx.JXLabel();
        jTextFieldNombre = new GUI.Components.JTextField();
        jXLabelUM = new org.jdesktop.swingx.JXLabel();
        jComboBoxUM = new javax.swing.JComboBox<>();
        jXLabelNombreAlmacen = new org.jdesktop.swingx.JXLabel();
        jComboBoxAlmacen = new javax.swing.JComboBox<>();
        jXLabelNombreCantExist = new org.jdesktop.swingx.JXLabel();
        jSpinnerCantidadExistente = new JSpinner();
        jXLabelNombre4 = new org.jdesktop.swingx.JXLabel();
        jSpinnerEstimacionStock = new JSpinner();
        jXLabelCostoU = new org.jdesktop.swingx.JXLabel();
        jSpinnerCosto = new JSpinner();
        jXPanelTabla = new org.jdesktop.swingx.JXPanel();
        jPanel1 = new javax.swing.JPanel();
        jCheckBoxElaborado = new javax.swing.JCheckBox();
        jXLabelNombreCantCreada = new org.jdesktop.swingx.JXLabel();
        jSpinnerCantidadCreada = new JSpinner();
        jXLabelNombreCosto = new org.jdesktop.swingx.JXLabel();
        jXLabelNombreValorCosto = new org.jdesktop.swingx.JXLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanelCrossReference = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxAutoComplete = new com.jidesoft.swing.AutoCompletionComboBox();
        jButtonAgregarProd = new javax.swing.JButton();
        jScrollPaneCrossReference = new javax.swing.JScrollPane();
        jTableCrossReference = new javax.swing.JTable();
        jButtonDeleteProd = new javax.swing.JButton();
        jPanelIngElab = new javax.swing.JPanel();
        jScrollPaneInsElab = new javax.swing.JScrollPane();
        jTableInsElab = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxAutoCompleteIns = new com.jidesoft.swing.AutoCompletionComboBox();
        jButtonAgregarIns = new javax.swing.JButton();
        jButtonDeleteIns = new javax.swing.JButton();
        jXPanelControles = new org.jdesktop.swingx.JXPanel();
        jButtonAdd = new javax.swing.JButton();
        jToggleButtonCrossReference = new javax.swing.JToggleButton();
        jButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        setTitle(bundle.getString("label_crear_insumo")); // NOI18N
        setMinimumSize(new java.awt.Dimension(590, 242));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(590, 242));

        jXPanelInputs.setBackground(new java.awt.Color(204, 204, 0));
        jXPanelInputs.setBorder(new org.pushingpixels.substance.internal.utils.border.SubstanceBorder());
        jXPanelInputs.setForeground(new java.awt.Color(204, 204, 0));

        jXLabelNombre.setText(bundle.getString("label_nombre")); // NOI18N

        jTextFieldNombre.setBorder(null);
        jTextFieldNombre.setMaximumSize(new java.awt.Dimension(120, 16));
        jTextFieldNombre.setMinimumSize(new java.awt.Dimension(120, 16));

        jXLabelUM.setText(bundle.getString("label_um")); // NOI18N

        jXLabelNombreAlmacen.setText(bundle.getString("label_almacen")); // NOI18N

        jXLabelNombreCantExist.setText(bundle.getString("label_cantidad_existente")); // NOI18N

        jSpinnerCantidadExistente.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 5.0f));

        jXLabelNombre4.setText(bundle.getString("label_est_stock")); // NOI18N

        jSpinnerEstimacionStock.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 5.0f));

        jXLabelCostoU.setText(bundle.getString("label_costo_unidad")); // NOI18N

        jSpinnerCosto.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 5.0f));

        javax.swing.GroupLayout jXPanelInputsLayout = new javax.swing.GroupLayout(jXPanelInputs);
        jXPanelInputs.setLayout(jXPanelInputsLayout);
        jXPanelInputsLayout.setHorizontalGroup(
            jXPanelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanelInputsLayout.createSequentialGroup()
                .addGroup(jXPanelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jXLabelUM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jXPanelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jXPanelInputsLayout.createSequentialGroup()
                            .addGap(85, 85, 85)
                            .addComponent(jXLabelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanelInputsLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jXLabelCostoU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jXPanelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSpinnerCosto, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jComboBoxUM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(jXPanelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXLabelNombreAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabelNombre4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabelNombreCantExist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jXPanelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinnerEstimacionStock, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerCantidadExistente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jXPanelInputsLayout.setVerticalGroup(
            jXPanelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanelInputsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXPanelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabelNombreAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jXPanelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabelNombreCantExist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerCantidadExistente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxUM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabelUM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jXPanelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabelCostoU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabelNombre4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSpinnerEstimacionStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        getContentPane().add(jXPanelInputs, java.awt.BorderLayout.PAGE_START);

        jXPanelTabla.setLayout(new java.awt.BorderLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(454, 0));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

        jCheckBoxElaborado.setText(bundle.getString("label_elaborado")); // NOI18N
        jCheckBoxElaborado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxElaboradoStateChanged(evt);
            }
        });
        jCheckBoxElaborado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxElaboradoActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBoxElaborado);

        jXLabelNombreCantCreada.setText(bundle.getString("label_cantidad_creada")); // NOI18N
        jPanel1.add(jXLabelNombreCantCreada);

        jSpinnerCantidadCreada.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 5.0f));
        jSpinnerCantidadCreada.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanel1.add(jSpinnerCantidadCreada);

        jXLabelNombreCosto.setText(bundle.getString("label_costo")); // NOI18N
        jPanel1.add(jXLabelNombreCosto);

        jXLabelNombreValorCosto.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jXLabelNombreValorCosto.setText(bundle.getString("label_lista_ingredientes")); // NOI18N
        jPanel1.add(jXLabelNombreValorCosto);

        jXPanelTabla.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(454, 404));
        jLayeredPane1.setLayout(new javax.swing.OverlayLayout(jLayeredPane1));

        jPanelCrossReference.setMinimumSize(new java.awt.Dimension(454, 0));
        jPanelCrossReference.setPreferredSize(new java.awt.Dimension(454, 40));
        jPanelCrossReference.setLayout(new java.awt.BorderLayout());

        jLabel1.setText(bundle.getString("label_producto_venta")); // NOI18N
        jPanel2.add(jLabel1);

        jComboBoxAutoComplete.setMaximumRowCount(5);
        jComboBoxAutoComplete.setPreferredSize(new java.awt.Dimension(250, 26));
        jPanel2.add(jComboBoxAutoComplete);

        jButtonAgregarProd.setText(bundle.getString("label_agregar")); // NOI18N
        jButtonAgregarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarProdActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonAgregarProd);

        jPanelCrossReference.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jScrollPaneCrossReference.setMinimumSize(new java.awt.Dimension(454, 0));

        jTableCrossReference.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPaneCrossReference.setViewportView(jTableCrossReference);

        jPanelCrossReference.add(jScrollPaneCrossReference, java.awt.BorderLayout.CENTER);

        jButtonDeleteProd.setText(bundle.getString("label_eliminar")); // NOI18N
        jButtonDeleteProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteProdActionPerformed(evt);
            }
        });
        jPanelCrossReference.add(jButtonDeleteProd, java.awt.BorderLayout.PAGE_END);

        jLayeredPane1.add(jPanelCrossReference);

        jPanelIngElab.setLayout(new java.awt.BorderLayout());

        jTableInsElab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableInsElab.setColumnSelectionAllowed(true);
        jScrollPaneInsElab.setViewportView(jTableInsElab);

        jPanelIngElab.add(jScrollPaneInsElab, java.awt.BorderLayout.CENTER);

        jLabel2.setText(bundle.getString("label_insumo")); // NOI18N
        jPanel4.add(jLabel2);

        jComboBoxAutoCompleteIns.setMaximumRowCount(5);
        jComboBoxAutoCompleteIns.setPreferredSize(new java.awt.Dimension(250, 26));
        jPanel4.add(jComboBoxAutoCompleteIns);

        jButtonAgregarIns.setText(bundle.getString("label_agregar")); // NOI18N
        jButtonAgregarIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarInsActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonAgregarIns);

        jPanelIngElab.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jButtonDeleteIns.setText(bundle.getString("label_eliminar")); // NOI18N
        jButtonDeleteIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteInsActionPerformed(evt);
            }
        });
        jPanelIngElab.add(jButtonDeleteIns, java.awt.BorderLayout.PAGE_END);

        jLayeredPane1.add(jPanelIngElab);

        jXPanelTabla.add(jLayeredPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jXPanelTabla, java.awt.BorderLayout.CENTER);

        jButtonAdd.setText(bundle.getString("label_crear")); // NOI18N
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        jXPanelControles.add(jButtonAdd);

        jToggleButtonCrossReference.setText(bundle.getString("label_ver_uso_de_insumo_en_platos")); // NOI18N
        jToggleButtonCrossReference.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jToggleButtonCrossReferenceStateChanged(evt);
            }
        });
        jToggleButtonCrossReference.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonCrossReferenceActionPerformed(evt);
            }
        });
        jXPanelControles.add(jToggleButtonCrossReference);

        jButtonCancelar.setText(bundle.getString("label_cancelar")); // NOI18N
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jXPanelControles.add(jButtonCancelar);

        getContentPane().add(jXPanelControles, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        getController().createUpdateInstance();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jCheckBoxElaboradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxElaboradoActionPerformed

    }//GEN-LAST:event_jCheckBoxElaboradoActionPerformed

    private void jCheckBoxElaboradoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxElaboradoStateChanged
        instance.setElaborado(jCheckBoxElaborado.isSelected());
        setElaboradoTable();
    }//GEN-LAST:event_jCheckBoxElaboradoStateChanged

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jToggleButtonCrossReferenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonCrossReferenceActionPerformed

    }//GEN-LAST:event_jToggleButtonCrossReferenceActionPerformed

    private void jToggleButtonCrossReferenceStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jToggleButtonCrossReferenceStateChanged
        if (jToggleButtonCrossReference.isSelected()) {
            showCrossReferenceDialog(instance.getProductoInsumoList());
        } else {
            hideCrossReferenceDialog();
        }
    }//GEN-LAST:event_jToggleButtonCrossReferenceStateChanged

    private void jButtonAgregarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarProdActionPerformed

    }//GEN-LAST:event_jButtonAgregarProdActionPerformed

    private void jButtonDeleteProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteProdActionPerformed
    }//GEN-LAST:event_jButtonDeleteProdActionPerformed

    private void jButtonAgregarInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarInsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAgregarInsActionPerformed

    private void jButtonDeleteInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteInsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonDeleteInsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAgregarIns;
    private javax.swing.JButton jButtonAgregarProd;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonDeleteIns;
    private javax.swing.JButton jButtonDeleteProd;
    private javax.swing.JCheckBox jCheckBoxElaborado;
    private javax.swing.JComboBox<Almacen> jComboBoxAlmacen;
    private com.jidesoft.swing.AutoCompletionComboBox jComboBoxAutoComplete;
    private com.jidesoft.swing.AutoCompletionComboBox jComboBoxAutoCompleteIns;
    private javax.swing.JComboBox<R.UM> jComboBoxUM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelCrossReference;
    private javax.swing.JPanel jPanelIngElab;
    private javax.swing.JScrollPane jScrollPaneCrossReference;
    private javax.swing.JScrollPane jScrollPaneInsElab;
    private javax.swing.JSpinner jSpinnerCantidadCreada;
    private javax.swing.JSpinner jSpinnerCantidadExistente;
    private javax.swing.JSpinner jSpinnerCosto;
    private javax.swing.JSpinner jSpinnerEstimacionStock;
    private javax.swing.JTable jTableCrossReference;
    private javax.swing.JTable jTableInsElab;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JToggleButton jToggleButtonCrossReference;
    private org.jdesktop.swingx.JXLabel jXLabelCostoU;
    private org.jdesktop.swingx.JXLabel jXLabelNombre;
    private org.jdesktop.swingx.JXLabel jXLabelNombre4;
    private org.jdesktop.swingx.JXLabel jXLabelNombreAlmacen;
    private org.jdesktop.swingx.JXLabel jXLabelNombreCantCreada;
    private org.jdesktop.swingx.JXLabel jXLabelNombreCantExist;
    private org.jdesktop.swingx.JXLabel jXLabelNombreCosto;
    private org.jdesktop.swingx.JXLabel jXLabelNombreValorCosto;
    private org.jdesktop.swingx.JXLabel jXLabelUM;
    private org.jdesktop.swingx.JXPanel jXPanelControles;
    private org.jdesktop.swingx.JXPanel jXPanelInputs;
    private org.jdesktop.swingx.JXPanel jXPanelTabla;
    // End of variables declaration//GEN-END:variables

    public void showCrossReferenceDialog(List<ProductoInsumo> insList) {
        jPanelCrossReference.setVisible(true);
        jLayeredPane1.moveToFront(jPanelCrossReference);
        setSize(getWidth(), 610);
    }

    public void hideCrossReferenceDialog() {
        jPanelCrossReference.setVisible(false);
        jLayeredPane1.moveToBack(jPanelCrossReference);
        if (!jCheckBoxElaborado.isSelected()) {
            setSize(getMinimumSize());
        }

    }

    public void setElaboradoTable() {
        if (instance.getElaborado()) {
            jCheckBoxElaborado.setSelected(true);
        }

        jPanelIngElab.setVisible(jCheckBoxElaborado.isSelected());
        if (jCheckBoxElaborado.isSelected()) {
            setSize(getWidth(), 610);
            jLayeredPane1.moveToFront(jPanelIngElab);
        } else {
            if (!jToggleButtonCrossReference.isSelected()) {
                setSize(getMinimumSize());
            }
            jLayeredPane1.moveToBack(jPanelIngElab);
        }
        jSpinnerCosto.setEnabled(!jCheckBoxElaborado.isSelected());

    }

    @Override
    public void setEditingMode() {
        jButtonAdd.setText(R.RESOURCE_BUNDLE.getString("label_editar"));
    }

    @Override
    public void setCreatingMode() {
        jButtonAdd.setText(R.RESOURCE_BUNDLE.getString("label_crear"));
    }

    @Override
    public boolean validateData() {
        if (jComboBoxAlmacen.getSelectedIndex() == -1 || !jTextFieldNombre.getInputVerifier().verify(jTextFieldNombre)) {
            return false;
        }
        instance.setAlmacencodAlmacen((Almacen) jComboBoxAlmacen.getSelectedItem());
        instance.setCantidadCreada((Float) jSpinnerCantidadCreada.getValue());
        instance.setCantidadExistente((Float) jSpinnerCantidadExistente.getValue());
        instance.setCostoPorUnidad((Float) jSpinnerCosto.getValue());
        instance.setElaborado(jCheckBoxElaborado.isSelected());
        instance.setNombre(jTextFieldNombre.getText());
        instance.setUm(jComboBoxUM.getSelectedItem().toString());
        instance.setProductoInsumoList(((RestManagerAbstractTableModel<ProductoInsumo>) jTableCrossReference.getModel()).getItems());
        instance.setInsumoElaboradoList1(instance.getInsumoElaboradoList1());
        instance.setInsumoTransaccionList(instance.getInsumoTransaccionList());
        instance.setIpvList(instance.getIpvList());
        instance.setStockEstimation((Float) jSpinnerEstimacionStock.getValue());

        if (instance.getElaborado()) {
            instance.setInsumoElaboradoList(((RestManagerAbstractTableModel<InsumoElaborado>) jTableInsElab.getModel()).getItems());
        } else {
            instance.setInsumoElaboradoList(new ArrayList<>());
        }
        return true;
    }

    public void updateLabelCost() {
        float total = 0;
        for (int i = 0; i < jTableInsElab.getRowCount(); i++) {
            total += (float) jTableInsElab.getValueAt(i, 4);
        }
        jXLabelNombreValorCosto.setText(String.format("%.2f" + " " + R.coinSuffix, total));
    }
}

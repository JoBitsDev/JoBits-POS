/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.gastos.pagos;

import com.jobits.pos.ui.OldAbstractView;
import com.jobits.pos.ui.utils.RestManagerCellRender;
import com.jobits.pos.ui.utils.RestManagerInputVerifier;
import com.jobits.pos.ui.utils.RestaurantManagerListIntelliHint;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import mdlaf.components.tabbedpane.MaterialTabbedPaneUI;
import mdlaf.components.table.MaterialTableUI;
import org.pushingpixels.substance.internal.fonts.Fonts;
import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.controller.gastos.pagos.CuentaController;
import com.jobits.pos.controller.gastos.pagos.FacturaController;
import com.jobits.pos.controller.gastos.pagos.PagoController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.domain.models.ContabilidadCuenta;
import com.jobits.pos.domain.models.Factura;
import com.jobits.pos.domain.models.Pago;
import com.jobits.pos.recursos.R;
import com.jobits.pos.recursos.valores.Colors;
import com.jobits.pos.ui.utils.RestManagerAbstractTableModel;
import com.jobits.pos.ui.utils.RestManagerComboBoxModel;
import com.jobits.pos.ui.utils.utils;

/**
 *
 * @author Jorge
 */
public class Gastos_Pagos_Dashboard extends OldAbstractView {

    //Controllers
    CuentaController cuentaController = new CuentaController();
    FacturaController facturaController = new FacturaController();
    PagoController pagoController = new PagoController();

    //Tablas
    RestManagerAbstractTableModel<ContabilidadCuenta> modelCuentas;
    RestManagerAbstractTableModel<Factura> modelFactura;
    RestManagerAbstractTableModel<Pago> modelPago;
    RestManagerAbstractTableModel<CuentaController.BalanceComprobacionItem> modelBalanceComprobacion;

    //Auxiliares
    ArrayList<CuentaController.BalanceComprobacionItem> listaBalanceCompleta = null;
    ArrayList<Factura> listaFacturaCompleta = null;

    //Listas
    RestaurantManagerListIntelliHint<ContabilidadCuenta> listaFacturaAsociada;
    RestaurantManagerListIntelliHint<ContabilidadCuenta> listaFacturaAfectada;

    public Gastos_Pagos_Dashboard(AbstractDialogController controller) {
        super(DialogType.FULL_SCREEN, controller);
        initComponents();
        fetchComponentData();
    }

    @Override
    public void fetchComponentData() {

        //
        // Generales
        //
        jTabbedPane1.setUI(new MaterialTabbedPaneUI());
        jComboBoxCuenta.setModel(new RestManagerComboBoxModel<>(cuentaController.getCuentasList()));
        jComboBoxCuenta.addItem(null);
        //
        // Cuentas
        //
        cuentaController.setView(this);
        modelCuentas = new RestManagerAbstractTableModel<ContabilidadCuenta>(cuentaController.getCuentasList(), jTableCuentas) {
            @Override
            public int getColumnCount() {
                return 2;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return items.get(rowIndex);
                    case 1:
                        return items.get(rowIndex).getTipoCuenta();
                    default:
                        return null;
                }
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Cuenta";
                    case 1:
                        return "Tipo";
                    default:
                        return null;
                }
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }

        };
        jComboBoxCuentaPadre.setModel(new RestManagerComboBoxModel<>(cuentaController.getCuentasList()));
        jComboBoxCuentaEnlazada.setModel(new RestManagerComboBoxModel<>(cuentaController.getCuentasList()));
        jComboBoxCuentaPadre.addItem(null);
        jComboBoxCuentaEnlazada.addItem(null);
        jTableCuentas.setUI(new MaterialTableUI());
        jTableCuentas.setDefaultRenderer(String.class, new RestManagerCellRender());
        jTableCuentas.setModel(modelCuentas);
        jTableCuentas.setRowSorter(modelCuentas.getSorter());

        //
        //Facturas
        //
        facturaController.setView(this);
        facturaController.setDismissOnAction(false);
        modelFactura = new RestManagerAbstractTableModel<Factura>(facturaController.getItems(), jTableFacturas) {
            @Override
            public int getColumnCount() {
                return 6;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return items.get(rowIndex).getIdCuentaDeudora();
                    case 1:
                        return items.get(rowIndex).getNoSerieFactura();
                    case 2:
                        return items.get(rowIndex).getNombre();
                    case 3:
                        return items.get(rowIndex).getMontoAPagar();
                    case 4:
                        return items.get(rowIndex).getMontoPagado();
                    case 5:
                        return R.DATE_FORMAT.format(items.get(rowIndex).getFecha());
                    default:
                        return null;
                }
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Cuenta";
                    case 1:
                        return "No. Serie";
                    case 2:
                        return "Nombre";
                    case 3:
                        return "Monto";
                    case 4:
                        return "Pagado";
                    case 5:
                        return "Fecha";
                    default:
                        return null;
                }
            }
        };
        listaFacturaAsociada = new RestaurantManagerListIntelliHint<>(jTextFieldlFacturaCuentaAsociada, facturaController.getCuentaList());
        listaFacturaAfectada = new RestaurantManagerListIntelliHint<>(jTextFieldlFacturaCuentaAfectada, facturaController.getCuentaList());
        jTextFieldFacturaNombreFactura.setInputVerifier(new RestManagerInputVerifier());
        jTableFacturas.setUI(new MaterialTableUI());
        jTableFacturas.setModel(modelFactura);
        jTableFacturas.setRowSorter(modelFactura.getSorter());

        //
        //Pagos
        //
        pagoController.setView(this);
        pagoController.setDismissOnAction(false);
        modelPago = new RestManagerAbstractTableModel<Pago>(pagoController.getItems(), jTablePagos) {
            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return R.DATE_FORMAT.format(items.get(rowIndex).getFecha());
                    case 1:
                        Pago p = items.get(rowIndex);
                        if (p.getACuenta() != null) {
                            if (p.getACuenta()) {
                                return "Estado de cuenta";
                            }
                        }
                        if (p.getNoCheque() != null) {
                            return "Cheque: " + p.getNoCheque();
                        }
                        if (p.getNoRecibo() != null) {
                            return "Efectivo: " + p.getNoRecibo();
                        }
                        return null;
                    case 2:
                        return items.get(rowIndex).getFactura().toString();
                    case 3:
                        return items.get(rowIndex).getMontoPagado();
                    default:
                        return null;
                }
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Fecha";
                    case 1:
                        return "Pago/Cobro a";
                    case 2:
                        return "Factura";
                    case 3:
                        return "Importe";
                    default:
                        return null;
                }
            }
        };
        jTablePagos.setUI(new MaterialTableUI());
        jTablePagos.setModel(modelPago);
        jTablePagos.setRowSorter(modelPago.getSorter());

        //
        // Balance Comprobacion
        //
        jTableBalanceComprobacion.setUI(new MaterialTableUI());
        jTableBalanceComprobacion.setDefaultRenderer(String.class, new RestManagerCellRender());
        // jTableBalanceComprobacion.setModel(modelBalanceComprobacion);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelROOT = new javax.swing.JPanel();
        jPanelBottom = new javax.swing.JPanel();
        jPanelTop = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jPanelData = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelResumen = new javax.swing.JPanel();
        jPanelFecha = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jDateChooserDel = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jDateChooserAl = new com.toedter.calendar.JDateChooser();
        jButtonVisualizar = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableBalanceComprobacion = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jXLabel4 = new org.jdesktop.swingx.JXLabel();
        jTextFieldBusquedaResumen = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jCheckBoxOcultarCuentas0 = new javax.swing.JCheckBox();
        jPanel22 = new javax.swing.JPanel();
        jCheckBoxExclusivo = new javax.swing.JCheckBox();
        jComboBoxElemento = new javax.swing.JComboBox<>();
        jComboBoxCuenta = new javax.swing.JComboBox<>();
        jPanel19 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabelDebitos = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabelCreditos = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jPanelCuentas = new javax.swing.JPanel();
        jPanelNuevaCuenta = new javax.swing.JPanel();
        jPanelINFO = new javax.swing.JPanel();
        jPanelNoCuenta = new javax.swing.JPanel();
        jLabelCuentaPrefijoPadre = new javax.swing.JLabel();
        jSpinnerNoCuenta = new javax.swing.JSpinner();
        jPanel6 = new javax.swing.JPanel();
        jComboBoxTipoCuenta = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jTextFieldNombreCuenta = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jComboBoxCuentaPadre = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jComboBoxCuentaEnlazada = new javax.swing.JComboBox<>();
        jPanelButtons = new javax.swing.JPanel();
        jButtonCuentaCrear = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCuentas = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jXLabel2 = new org.jdesktop.swingx.JXLabel();
        jTextFieldBusquedaCuenta = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanelFacturas = new javax.swing.JPanel();
        jPanelFactura = new javax.swing.JPanel();
        jPanelNuevaFactura = new javax.swing.JPanel();
        jPanelINFO1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jTextFieldlFacturaCuentaAsociada = new javax.swing.JTextField();
        jPanelNoCuenta1 = new javax.swing.JPanel();
        jTextFieldFacturaNoSerie = new javax.swing.JTextField();
        jPanelNoCuenta2 = new javax.swing.JPanel();
        jTextFieldFacturaNombreFactura = new javax.swing.JTextField();
        jPanelNoCuenta3 = new javax.swing.JPanel();
        jSpinnerFacturaMonto = new javax.swing.JSpinner();
        jPanel10 = new javax.swing.JPanel();
        jTextFieldlFacturaCuentaAfectada = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaFacturaDesc = new javax.swing.JTextArea();
        jPanelButtons1 = new javax.swing.JPanel();
        jButtonCrearFactura = new javax.swing.JButton();
        jPanelListaFacturas = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableFacturas = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jTextFieldBusquedaFactura = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jCheckBoxMostrarFacturasPorPagar = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanelPagos = new javax.swing.JPanel();
        jPanelPagoRoot = new javax.swing.JPanel();
        jPanelListaFacturas1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTablePagos = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jXLabel3 = new org.jdesktop.swingx.JXLabel();
        jTextFieldBusquedaPago = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanelROOT.setBorder(new javax.swing.border.LineBorder(Colors.WINDOW_BORDER_COLOR, 5, true));
        jPanelROOT.setLayout(new java.awt.BorderLayout());

        jPanelBottom.setBackground(new java.awt.Color(204, 204, 204));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanelBottom.setBorder(dropShadowBorder1);
        jPanelBottom.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        jPanelROOT.add(jPanelBottom, java.awt.BorderLayout.PAGE_END);

        jPanelTop.setBackground(new java.awt.Color(204, 204, 204));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanelTop.setBorder(dropShadowBorder2);
        jPanelTop.setLayout(new java.awt.BorderLayout());

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/logout40.png"))); // NOI18N
        jButton6.setBorderPainted(false);
        jButton6.setFocusable(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanelTop.add(jButton6, java.awt.BorderLayout.WEST);

        jPanelROOT.add(jPanelTop, java.awt.BorderLayout.PAGE_START);

        jPanelData.setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setBackground(Colors.WINDOW_BORDER_COLOR);
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanelResumen.setLayout(new java.awt.BorderLayout());

        jPanelFecha.setOpaque(false);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jLabel1.setText(bundle.getString("label_del")); // NOI18N
        jPanelFecha.add(jLabel1);

        jDateChooserDel.setOpaque(false);
        jDateChooserDel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserDelPropertyChange(evt);
            }
        });
        jPanelFecha.add(jDateChooserDel);

        jLabel2.setText(bundle.getString("label_al")); // NOI18N
        jPanelFecha.add(jLabel2);

        jDateChooserAl.setOpaque(false);
        jPanelFecha.add(jDateChooserAl);

        jButtonVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/visualizar.PNG"))); // NOI18N
        jButtonVisualizar.setToolTipText("Visualizar");
        jButtonVisualizar.setBorderPainted(false);
        jButtonVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVisualizarActionPerformed(evt);
            }
        });
        jPanelFecha.add(jButtonVisualizar);

        jPanelResumen.add(jPanelFecha, java.awt.BorderLayout.PAGE_START);

        jPanel15.setLayout(new java.awt.BorderLayout());

        jTableBalanceComprobacion.setAutoCreateRowSorter(true);
        jTableBalanceComprobacion.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jTableBalanceComprobacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Cuenta", "Debitos", "Creditos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Float.class
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
        jScrollPane4.setViewportView(jTableBalanceComprobacion);
        if (jTableBalanceComprobacion.getColumnModel().getColumnCount() > 0) {
            jTableBalanceComprobacion.getColumnModel().getColumn(0).setMinWidth(150);
            jTableBalanceComprobacion.getColumnModel().getColumn(0).setPreferredWidth(150);
        }

        jPanel15.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jPanel18.setOpaque(false);
        jPanel18.setLayout(new java.awt.BorderLayout());

        jXLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jXLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/buscar.png"))); // NOI18N
        jXLabel4.setText(bundle.getString("label_buscar")); // NOI18N
        jXLabel4.setToolTipText("Buscar");
        jPanel21.add(jXLabel4);

        jTextFieldBusquedaResumen.setPreferredSize(new java.awt.Dimension(200, 26));
        jTextFieldBusquedaResumen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBusquedaResumenKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBusquedaResumenKeyReleased(evt);
            }
        });
        jPanel21.add(jTextFieldBusquedaResumen);

        jPanel18.add(jPanel21, java.awt.BorderLayout.EAST);

        jCheckBoxOcultarCuentas0.setText("Ocultar cuentas en 0");
        jCheckBoxOcultarCuentas0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxOcultarCuentas0ActionPerformed(evt);
            }
        });
        jPanel20.add(jCheckBoxOcultarCuentas0);

        jPanel18.add(jPanel20, java.awt.BorderLayout.WEST);

        jCheckBoxExclusivo.setText("!");
        jCheckBoxExclusivo.setToolTipText("Exclusivo");
        jCheckBoxExclusivo.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jCheckBoxExclusivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxExclusivoActionPerformed(evt);
            }
        });
        jPanel22.add(jCheckBoxExclusivo);

        jComboBoxElemento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cuenta", "Sub-Cuenta", "Analisis", "Detalle", "Elemento" }));
        jComboBoxElemento.setSelectedIndex(4);
        jComboBoxElemento.setToolTipText("Nivel de detalle");
        jPanel22.add(jComboBoxElemento);

        jComboBoxCuenta.setToolTipText("especificar cuenta");
        jComboBoxCuenta.setPreferredSize(new java.awt.Dimension(200, 27));
        jPanel22.add(jComboBoxCuenta);

        jPanel18.add(jPanel22, java.awt.BorderLayout.CENTER);

        jPanel15.add(jPanel18, java.awt.BorderLayout.PAGE_START);

        jPanel19.setLayout(new java.awt.BorderLayout());

        jPanel25.setLayout(new java.awt.GridLayout(1, 3));

        jLabel3.setText("Total Debitos/Creditos");
        jPanel29.add(jLabel3);

        jPanel25.add(jPanel29);

        jLabelDebitos.setText("<debitos>");
        jPanel27.add(jLabelDebitos);

        jPanel25.add(jPanel27);

        jLabelCreditos.setText("<creditos>");
        jPanel28.add(jLabelCreditos);

        jPanel25.add(jPanel28);

        jPanel19.add(jPanel25, java.awt.BorderLayout.PAGE_START);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButton4.setText("Imprimir Balance");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel26.add(jButton4);

        jPanel19.add(jPanel26, java.awt.BorderLayout.LINE_END);

        jPanel15.add(jPanel19, java.awt.BorderLayout.PAGE_END);

        jPanelResumen.add(jPanel15, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Resumen", jPanelResumen);

        jPanelCuentas.setMaximumSize(new java.awt.Dimension(300, 2147483647));
        jPanelCuentas.setMinimumSize(new java.awt.Dimension(150, 95));
        jPanelCuentas.setPreferredSize(new java.awt.Dimension(200, 469));
        jPanelCuentas.setLayout(new java.awt.BorderLayout());

        jPanelNuevaCuenta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Nueva Cuenta"));
        jPanelNuevaCuenta.setPreferredSize(new java.awt.Dimension(200, 469));
        jPanelNuevaCuenta.setLayout(new java.awt.BorderLayout());

        jPanelINFO.setOpaque(false);
        jPanelINFO.setPreferredSize(new java.awt.Dimension(200, 253));
        jPanelINFO.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 15));

        jPanelNoCuenta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "No.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), Colors.LABEL_FORM_COLOR)); // NOI18N
        jPanelNoCuenta.setOpaque(false);
        jPanelNoCuenta.add(jLabelCuentaPrefijoPadre);

        jSpinnerNoCuenta.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10000, 1));
        jSpinnerNoCuenta.setPreferredSize(new java.awt.Dimension(100, 26));
        jPanelNoCuenta.add(jSpinnerNoCuenta);

        jPanelINFO.add(jPanelNoCuenta);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Tipo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), Colors.LABEL_FORM_COLOR)); // NOI18N
        jPanel6.setOpaque(false);

        jComboBoxTipoCuenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DEBITO", "CREDITO" }));
        jComboBoxTipoCuenta.setPreferredSize(new java.awt.Dimension(150, 27));
        jPanel6.add(jComboBoxTipoCuenta);

        jPanelINFO.add(jPanel6);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Nombre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), Colors.LABEL_FORM_COLOR)); // NOI18N
        jPanel4.setOpaque(false);

        jTextFieldNombreCuenta.setBorder(null);
        jTextFieldNombreCuenta.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanel4.add(jTextFieldNombreCuenta);

        jPanelINFO.add(jPanel4);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Cuenta Padre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), Colors.LABEL_FORM_COLOR)); // NOI18N
        jPanel5.setOpaque(false);

        jComboBoxCuentaPadre.setPreferredSize(new java.awt.Dimension(150, 27));
        jComboBoxCuentaPadre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxCuentaPadreItemStateChanged(evt);
            }
        });
        jPanel5.add(jComboBoxCuentaPadre);

        jPanelINFO.add(jPanel5);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Enlace", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), Colors.LABEL_FORM_COLOR)); // NOI18N
        jPanel12.setOpaque(false);

        jComboBoxCuentaEnlazada.setPreferredSize(new java.awt.Dimension(150, 27));
        jComboBoxCuentaEnlazada.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxCuentaEnlazadaItemStateChanged(evt);
            }
        });
        jPanel12.add(jComboBoxCuentaEnlazada);

        jPanelINFO.add(jPanel12);

        jPanelNuevaCuenta.add(jPanelINFO, java.awt.BorderLayout.CENTER);

        jButtonCuentaCrear.setText(bundle.getString("label_crear")); // NOI18N
        jButtonCuentaCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCuentaCrearActionPerformed(evt);
            }
        });
        jPanelButtons.add(jButtonCuentaCrear);

        jPanelNuevaCuenta.add(jPanelButtons, java.awt.BorderLayout.PAGE_END);

        jPanelCuentas.add(jPanelNuevaCuenta, java.awt.BorderLayout.WEST);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jTableCuentas.setAutoCreateRowSorter(true);
        jTableCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Cuenta", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableCuentas);
        if (jTableCuentas.getColumnModel().getColumnCount() > 0) {
            jTableCuentas.getColumnModel().getColumn(0).setMinWidth(150);
            jTableCuentas.getColumnModel().getColumn(0).setPreferredWidth(150);
            jTableCuentas.getColumnModel().getColumn(1).setMinWidth(100);
            jTableCuentas.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableCuentas.getColumnModel().getColumn(1).setMaxWidth(100);
            jTableCuentas.getColumnModel().getColumn(1).setHeaderValue("Tipo");
        }

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jXLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jXLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/buscar.png"))); // NOI18N
        jXLabel2.setText(bundle.getString("label_buscar")); // NOI18N
        jXLabel2.setToolTipText("Buscar");
        jPanel3.add(jXLabel2);

        jTextFieldBusquedaCuenta.setPreferredSize(new java.awt.Dimension(200, 26));
        jTextFieldBusquedaCuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBusquedaCuentaKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBusquedaCuentaKeyReleased(evt);
            }
        });
        jPanel3.add(jTextFieldBusquedaCuenta);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/borrar16.png"))); // NOI18N
        jButton1.setText("Eliminar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton1);

        jPanel2.add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jPanelCuentas.add(jPanel2, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Cuentas", jPanelCuentas);

        jPanelFacturas.setLayout(new java.awt.BorderLayout());

        jPanelFactura.setMaximumSize(new java.awt.Dimension(300, 2147483647));
        jPanelFactura.setMinimumSize(new java.awt.Dimension(150, 95));
        jPanelFactura.setPreferredSize(new java.awt.Dimension(200, 469));
        jPanelFactura.setLayout(new java.awt.BorderLayout());

        jPanelNuevaFactura.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Nueva Factura/Recepcion"));
        jPanelNuevaFactura.setPreferredSize(new java.awt.Dimension(200, 469));
        jPanelNuevaFactura.setLayout(new java.awt.BorderLayout());

        jPanelINFO1.setOpaque(false);
        jPanelINFO1.setPreferredSize(new java.awt.Dimension(200, 253));
        jPanelINFO1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 15));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Cargado a", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), Colors.LABEL_FORM_COLOR)); // NOI18N
        jPanel9.setOpaque(false);

        jTextFieldlFacturaCuentaAsociada.setToolTipText("");
        jTextFieldlFacturaCuentaAsociada.setPreferredSize(new java.awt.Dimension(150, 26));
        jTextFieldlFacturaCuentaAsociada.setSize(new java.awt.Dimension(150, 26));
        jPanel9.add(jTextFieldlFacturaCuentaAsociada);

        jPanelINFO1.add(jPanel9);

        jPanelNoCuenta1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "No.Serie", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), Colors.LABEL_FORM_COLOR)); // NOI18N
        jPanelNoCuenta1.setOpaque(false);

        jTextFieldFacturaNoSerie.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanelNoCuenta1.add(jTextFieldFacturaNoSerie);

        jPanelINFO1.add(jPanelNoCuenta1);

        jPanelNoCuenta2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Nombre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), Colors.LABEL_FORM_COLOR)); // NOI18N
        jPanelNoCuenta2.setOpaque(false);

        jTextFieldFacturaNombreFactura.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanelNoCuenta2.add(jTextFieldFacturaNombreFactura);

        jPanelINFO1.add(jPanelNoCuenta2);

        jPanelNoCuenta3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Monto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), Colors.LABEL_FORM_COLOR)); // NOI18N
        jPanelNoCuenta3.setOpaque(false);

        jSpinnerFacturaMonto.setModel(new javax.swing.SpinnerNumberModel(1.0f, 0.0f, null, 1.0f));
        jSpinnerFacturaMonto.setPreferredSize(new java.awt.Dimension(150, 26));
        jPanelNoCuenta3.add(jSpinnerFacturaMonto);

        jPanelINFO1.add(jPanelNoCuenta3);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Abonado a", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), Colors.LABEL_FORM_COLOR)); // NOI18N
        jPanel10.setOpaque(false);

        jTextFieldlFacturaCuentaAfectada.setToolTipText("");
        jTextFieldlFacturaCuentaAfectada.setPreferredSize(new java.awt.Dimension(150, 26));
        jTextFieldlFacturaCuentaAfectada.setSize(new java.awt.Dimension(150, 26));
        jPanel10.add(jTextFieldlFacturaCuentaAfectada);

        jPanelINFO1.add(jPanel10);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Descripcion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), Colors.LABEL_FORM_COLOR)); // NOI18N
        jPanel11.setOpaque(false);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane3.setPreferredSize(new java.awt.Dimension(150, 60));

        jTextAreaFacturaDesc.setColumns(20);
        jTextAreaFacturaDesc.setLineWrap(true);
        jTextAreaFacturaDesc.setRows(5);
        jTextAreaFacturaDesc.setWrapStyleWord(true);
        jTextAreaFacturaDesc.setPreferredSize(new java.awt.Dimension(150, 60));
        jScrollPane3.setViewportView(jTextAreaFacturaDesc);

        jPanel11.add(jScrollPane3);

        jPanelINFO1.add(jPanel11);

        jPanelNuevaFactura.add(jPanelINFO1, java.awt.BorderLayout.CENTER);

        jButtonCrearFactura.setText(bundle.getString("label_crear")); // NOI18N
        jButtonCrearFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearFacturaActionPerformed(evt);
            }
        });
        jPanelButtons1.add(jButtonCrearFactura);

        jPanelNuevaFactura.add(jPanelButtons1, java.awt.BorderLayout.PAGE_END);

        jPanelFactura.add(jPanelNuevaFactura, java.awt.BorderLayout.WEST);

        jPanelListaFacturas.setLayout(new java.awt.BorderLayout(5, 5));

        jTableFacturas.setAutoCreateRowSorter(true);
        jTableFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Cuenta", "No. Serie", "Nombre", "A pagar", "Pagado", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableFacturas);
        if (jTableFacturas.getColumnModel().getColumnCount() > 0) {
            jTableFacturas.getColumnModel().getColumn(0).setMinWidth(150);
            jTableFacturas.getColumnModel().getColumn(0).setPreferredWidth(150);
            jTableFacturas.getColumnModel().getColumn(0).setHeaderValue("Cuenta");
            jTableFacturas.getColumnModel().getColumn(1).setMinWidth(100);
            jTableFacturas.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableFacturas.getColumnModel().getColumn(1).setMaxWidth(100);
            jTableFacturas.getColumnModel().getColumn(1).setHeaderValue("No. Serie");
            jTableFacturas.getColumnModel().getColumn(2).setHeaderValue("Nombre");
            jTableFacturas.getColumnModel().getColumn(3).setHeaderValue("A pagar");
            jTableFacturas.getColumnModel().getColumn(4).setHeaderValue("Pagado");
            jTableFacturas.getColumnModel().getColumn(5).setHeaderValue("Fecha");
        }

        jPanelListaFacturas.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jXLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jXLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/buscar.png"))); // NOI18N
        jXLabel1.setText(bundle.getString("label_buscar")); // NOI18N
        jXLabel1.setToolTipText("Buscar");
        jPanel14.add(jXLabel1);

        jTextFieldBusquedaFactura.setPreferredSize(new java.awt.Dimension(200, 26));
        jTextFieldBusquedaFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBusquedaFacturaKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBusquedaFacturaKeyReleased(evt);
            }
        });
        jPanel14.add(jTextFieldBusquedaFactura);

        jPanel1.add(jPanel14, java.awt.BorderLayout.CENTER);

        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jCheckBoxMostrarFacturasPorPagar.setText("Mostrar Facturas por pagar");
        jCheckBoxMostrarFacturasPorPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMostrarFacturasPorPagarActionPerformed(evt);
            }
        });
        jPanel13.add(jCheckBoxMostrarFacturasPorPagar);

        jPanel1.add(jPanel13, java.awt.BorderLayout.SOUTH);

        jPanelListaFacturas.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jButton12.setForeground(new java.awt.Color(0, 204, 51));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/income16.png"))); // NOI18N
        jButton12.setText("Cobrar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel23.add(jButton12);

        jButton13.setForeground(new java.awt.Color(255, 0, 0));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/outcomemoney16.png"))); // NOI18N
        jButton13.setText("Pagar");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel23.add(jButton13);

        jPanel8.add(jPanel23, java.awt.BorderLayout.WEST);

        jPanel24.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/borrar16.png"))); // NOI18N
        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel24.add(jButton2);

        jPanel8.add(jPanel24, java.awt.BorderLayout.EAST);

        jPanelListaFacturas.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        jPanelFactura.add(jPanelListaFacturas, java.awt.BorderLayout.CENTER);

        jPanelFacturas.add(jPanelFactura, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Facturas/Recepciones", jPanelFacturas);

        jPanelPagos.setLayout(new java.awt.BorderLayout());

        jPanelPagoRoot.setMaximumSize(new java.awt.Dimension(300, 2147483647));
        jPanelPagoRoot.setMinimumSize(new java.awt.Dimension(150, 95));
        jPanelPagoRoot.setPreferredSize(new java.awt.Dimension(200, 469));
        jPanelPagoRoot.setLayout(new java.awt.BorderLayout());
        jPanelPagos.add(jPanelPagoRoot, java.awt.BorderLayout.CENTER);

        jPanelListaFacturas1.setLayout(new java.awt.BorderLayout(5, 5));

        jTablePagos.setAutoCreateRowSorter(true);
        jTablePagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Fecha", "Pago/Cobro por", "Factura", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTablePagos);

        jPanelListaFacturas1.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jPanel16.setOpaque(false);
        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jXLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jXLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/buscar.png"))); // NOI18N
        jXLabel3.setText(bundle.getString("label_buscar")); // NOI18N
        jXLabel3.setToolTipText("Buscar");
        jPanel16.add(jXLabel3);

        jTextFieldBusquedaPago.setPreferredSize(new java.awt.Dimension(200, 26));
        jTextFieldBusquedaPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBusquedaPagoKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBusquedaPagoKeyReleased(evt);
            }
        });
        jPanel16.add(jTextFieldBusquedaPago);

        jPanelListaFacturas1.add(jPanel16, java.awt.BorderLayout.PAGE_START);

        jPanel17.setLayout(new java.awt.BorderLayout());

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/borrar16.png"))); // NOI18N
        jButton9.setText("Eliminar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton9, java.awt.BorderLayout.EAST);

        jPanelListaFacturas1.add(jPanel17, java.awt.BorderLayout.PAGE_END);

        jPanelPagos.add(jPanelListaFacturas1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Cobros/Pagos", jPanelPagos);

        jPanelData.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jPanelROOT.add(jPanelData, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelROOT, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCuentaCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCuentaCrearActionPerformed
        crearCuenta();
    }//GEN-LAST:event_jButtonCuentaCrearActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButtonCrearFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearFacturaActionPerformed
        crearFactura();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCrearFacturaActionPerformed

    private void jTextFieldBusquedaFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaFacturaKeyTyped

    }//GEN-LAST:event_jTextFieldBusquedaFacturaKeyTyped

    private void jTextFieldBusquedaFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaFacturaKeyReleased
        modelFactura.filterByString(jTextFieldBusquedaFactura.getText());

    }//GEN-LAST:event_jTextFieldBusquedaFacturaKeyReleased

    private void jTextFieldBusquedaCuentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaCuentaKeyTyped

    }//GEN-LAST:event_jTextFieldBusquedaCuentaKeyTyped

    private void jTextFieldBusquedaCuentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaCuentaKeyReleased
        modelCuentas.filterByString(jTextFieldBusquedaCuenta.getText());
    }//GEN-LAST:event_jTextFieldBusquedaCuentaKeyReleased

    private void jComboBoxCuentaPadreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxCuentaPadreItemStateChanged
        if (jComboBoxCuentaPadre.getSelectedItem() != null) {
            jLabelCuentaPrefijoPadre.setText(jComboBoxCuentaPadre.getItemAt(jComboBoxCuentaPadre.getSelectedIndex()).getNumeroCuenta() + "-");
        } else {
            jLabelCuentaPrefijoPadre.setText("");
        }
    }//GEN-LAST:event_jComboBoxCuentaPadreItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cuentaController.destroy(modelCuentas.getObjectAtSelectedRow());
        onCreateCuentaUpdates();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        facturaController.destroy(modelFactura.getObjectAtSelectedRow());
        onCreateFacturaUpdate();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBoxCuentaEnlazadaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxCuentaEnlazadaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxCuentaEnlazadaItemStateChanged

    private void jTextFieldBusquedaPagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaPagoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBusquedaPagoKeyTyped

    private void jTextFieldBusquedaPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaPagoKeyReleased
        modelPago.filterByString(jTextFieldBusquedaPago.getText());
    }//GEN-LAST:event_jTextFieldBusquedaPagoKeyReleased

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextFieldBusquedaResumenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaResumenKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBusquedaResumenKeyTyped

    private void jTextFieldBusquedaResumenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBusquedaResumenKeyReleased
        modelBalanceComprobacion.filterByString(jTextFieldBusquedaResumen.getText());
    }//GEN-LAST:event_jTextFieldBusquedaResumenKeyReleased

    private void jDateChooserDelPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserDelPropertyChange
        if (evt.getPropertyName().equals("date")) {
            jDateChooserAl.setDate((Date) evt.getNewValue());
        }
    }//GEN-LAST:event_jDateChooserDelPropertyChange

    private void jButtonVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVisualizarActionPerformed
        createBalanceComprobacion(jDateChooserDel.getCalendar(), jDateChooserAl.getCalendar());

        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonVisualizarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        imprimirBalance();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jCheckBoxOcultarCuentas0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxOcultarCuentas0ActionPerformed
        ocultarMostrarCuentas();
    }//GEN-LAST:event_jCheckBoxOcultarCuentas0ActionPerformed

    private void jCheckBoxMostrarFacturasPorPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMostrarFacturasPorPagarActionPerformed
        mostrarFacturasPorPagar(jCheckBoxMostrarFacturasPorPagar.isSelected());
    }//GEN-LAST:event_jCheckBoxMostrarFacturasPorPagarActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        crearObligacionCobro();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        crearObligacionPago();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jCheckBoxExclusivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxExclusivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxExclusivoActionPerformed

    @Override
    public void updateView() {

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonCrearFactura;
    private javax.swing.JButton jButtonCuentaCrear;
    private javax.swing.JButton jButtonVisualizar;
    private javax.swing.JCheckBox jCheckBoxExclusivo;
    private javax.swing.JCheckBox jCheckBoxMostrarFacturasPorPagar;
    private javax.swing.JCheckBox jCheckBoxOcultarCuentas0;
    private javax.swing.JComboBox<ContabilidadCuenta> jComboBoxCuenta;
    private javax.swing.JComboBox<ContabilidadCuenta> jComboBoxCuentaEnlazada;
    private javax.swing.JComboBox<ContabilidadCuenta> jComboBoxCuentaPadre;
    private javax.swing.JComboBox<String> jComboBoxElemento;
    private javax.swing.JComboBox<String> jComboBoxTipoCuenta;
    private com.toedter.calendar.JDateChooser jDateChooserAl;
    private com.toedter.calendar.JDateChooser jDateChooserDel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelCreditos;
    private javax.swing.JLabel jLabelCuentaPrefijoPadre;
    private javax.swing.JLabel jLabelDebitos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelBottom;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JPanel jPanelButtons1;
    private javax.swing.JPanel jPanelCuentas;
    private javax.swing.JPanel jPanelData;
    private javax.swing.JPanel jPanelFactura;
    private javax.swing.JPanel jPanelFacturas;
    private javax.swing.JPanel jPanelFecha;
    private javax.swing.JPanel jPanelINFO;
    private javax.swing.JPanel jPanelINFO1;
    private javax.swing.JPanel jPanelListaFacturas;
    private javax.swing.JPanel jPanelListaFacturas1;
    private javax.swing.JPanel jPanelNoCuenta;
    private javax.swing.JPanel jPanelNoCuenta1;
    private javax.swing.JPanel jPanelNoCuenta2;
    private javax.swing.JPanel jPanelNoCuenta3;
    private javax.swing.JPanel jPanelNuevaCuenta;
    private javax.swing.JPanel jPanelNuevaFactura;
    private javax.swing.JPanel jPanelPagoRoot;
    private javax.swing.JPanel jPanelPagos;
    private javax.swing.JPanel jPanelROOT;
    private javax.swing.JPanel jPanelResumen;
    private javax.swing.JPanel jPanelTop;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSpinner jSpinnerFacturaMonto;
    private javax.swing.JSpinner jSpinnerNoCuenta;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableBalanceComprobacion;
    private javax.swing.JTable jTableCuentas;
    private javax.swing.JTable jTableFacturas;
    private javax.swing.JTable jTablePagos;
    private javax.swing.JTextArea jTextAreaFacturaDesc;
    private javax.swing.JTextField jTextFieldBusquedaCuenta;
    private javax.swing.JTextField jTextFieldBusquedaFactura;
    private javax.swing.JTextField jTextFieldBusquedaPago;
    private javax.swing.JTextField jTextFieldBusquedaResumen;
    private javax.swing.JTextField jTextFieldFacturaNoSerie;
    private javax.swing.JTextField jTextFieldFacturaNombreFactura;
    private javax.swing.JTextField jTextFieldNombreCuenta;
    private javax.swing.JTextField jTextFieldlFacturaCuentaAfectada;
    private javax.swing.JTextField jTextFieldlFacturaCuentaAsociada;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXLabel jXLabel2;
    private org.jdesktop.swingx.JXLabel jXLabel3;
    private org.jdesktop.swingx.JXLabel jXLabel4;
    // End of variables declaration//GEN-END:variables

    private void onCreateCuentaUpdates() {
        jTextFieldNombreCuenta.setText("");
        jSpinnerNoCuenta.setValue(0);
        jComboBoxCuentaPadre.setSelectedItem(null);
        jComboBoxTipoCuenta.setSelectedIndex(0);
        modelCuentas.setItems(cuentaController.getCuentasList());
        jComboBoxCuentaPadre.setModel(new RestManagerComboBoxModel<>(cuentaController.getCuentasList()));
        listaFacturaAfectada.setCompletionList(facturaController.getCuentaList());
        listaFacturaAsociada.setCompletionList(facturaController.getCuentaList());
    }

    private void onCreateFacturaUpdate() {
        String empty = "";
        modelFactura.setItems(facturaController.getItems());
        jTextAreaFacturaDesc.setText(empty);
        jSpinnerFacturaMonto.setValue(0);
        jTextFieldFacturaNoSerie.setText(empty);
        jTextFieldFacturaNombreFactura.setText(empty);
        jTextFieldlFacturaCuentaAfectada.setText(empty);
        jTextFieldlFacturaCuentaAsociada.setText(empty);
    }
    
    private void onCreatePagoUpdate(){
        modelPago.setItems(pagoController.getItems());
    }

    private void crearCuenta() {

        ContabilidadCuenta cuenta = new ContabilidadCuenta();
        cuenta.setNumeroCuenta(jLabelCuentaPrefijoPadre.getText() + jSpinnerNoCuenta.getValue().toString());
        cuenta.setIdCuentaPadre((ContabilidadCuenta) jComboBoxCuentaPadre.getSelectedItem());
        cuenta.setNombre(jTextFieldNombreCuenta.getText());
        cuenta.setTipoCuenta((String) jComboBoxTipoCuenta.getSelectedItem());
        cuenta.setCuentaEnlazada(jComboBoxCuentaEnlazada.getItemAt(jComboBoxCuentaEnlazada.getSelectedIndex()));
        cuentaController.create(cuenta);
        onCreateCuentaUpdates();

    }

    private void crearFactura() {
        Factura f = new Factura();
        f.setNoSerieFactura(jTextFieldFacturaNoSerie.getText());
        f.setDescripcion(jTextAreaFacturaDesc.getText());
        f.setIdCuentaAcreedora(listaFacturaAfectada.getSelectedHint());
        f.setEsGasto(false);
        f.setIdCuentaDeudora(listaFacturaAsociada.getSelectedHint());
        f.setMontoAPagar(utils.setDosLugaresDecimalesFloat((Float) jSpinnerFacturaMonto.getValue()));
        f.setMontoPagado((float) 0);
        f.setNoSerieFactura(jTextFieldFacturaNoSerie.getText());
        f.setNombre(jTextFieldFacturaNombreFactura.getText());
        f.setPagoList(new ArrayList<>());
        facturaController.create(f);
        onCreateFacturaUpdate();
    }

    private void createBalanceComprobacion(Calendar del, Calendar al) {
        modelBalanceComprobacion = new RestManagerAbstractTableModel<CuentaController.BalanceComprobacionItem>(
                cuentaController.getBalanceComprobacion(del, al,
                        jComboBoxCuenta.getItemAt(jComboBoxCuenta.getSelectedIndex()),
                        jComboBoxElemento.getSelectedIndex(),jCheckBoxExclusivo.isSelected()),
                jTableBalanceComprobacion) {
            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return items.get(rowIndex).getCuenta();
                    case 1:
                        return items.get(rowIndex).getBalanceDebito();
                    case 2:
                        return items.get(rowIndex).getBalanceCredito() == 0 ? null : items.get(rowIndex).getBalanceCredito();
                    default:
                        return null;
                }
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Cuenta";
                    case 1:
                        return "Debito";
                    case 2:
                        return "Credito";
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
                    case 2:
                        return Float.class;
                    default:
                        return Object.class;
                }
            }

        };
        jTableBalanceComprobacion.setModel(modelBalanceComprobacion);
        jCheckBoxOcultarCuentas0.setSelected(false);
        jTableBalanceComprobacion.setRowSorter(modelBalanceComprobacion.getSorter());
        jLabelDebitos.setText(""+utils.setDosLugaresDecimalesFloat(cuentaController.getSumaDebitosActuales()));
        jLabelCreditos.setText(""+utils.setDosLugaresDecimalesFloat(cuentaController.getSumaCreditosActuales()));

    }

    private void imprimirBalance() {
        MessageFormat header, footer;
        header = new MessageFormat("" + R.REST_NAME);
        footer = new MessageFormat("-{0}-");
        try {
            /* print the table */
            jTableBalanceComprobacion.setFont(Fonts.WINDOWS_VISTA_96DPI_LARGE);
            boolean complete = jTableBalanceComprobacion.print(JTable.PrintMode.FIT_WIDTH, header, footer,
                    true, null,
                    true, null);

            /* if printing completes */
            if (complete) {
                /* show a success message */
                JOptionPane.showMessageDialog(this,
                        "Printing Complete",
                        "Printing Result",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                /* show a message indicating that printing was cancelled */
                JOptionPane.showMessageDialog(this,
                        "Printing Cancelled",
                        "Printing Result",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException pe) {
            /* Printing failed, report to the user */
            JOptionPane.showMessageDialog(this,
                    "Printing Failed: " + pe.getMessage(),
                    "Printing Result",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ocultarMostrarCuentas() {
        if (jCheckBoxOcultarCuentas0.isSelected()) {
            listaBalanceCompleta = new ArrayList<>(modelBalanceComprobacion.getItems());
            for (int i = 0; i < listaBalanceCompleta.size(); i++) {
                CuentaController.BalanceComprobacionItem item = listaBalanceCompleta.get(i);
                if (item.getBalanceCredito() == 0 && item.getBalanceDebito() == 0) {
                    modelBalanceComprobacion.removeObject(item);
                }
            }
        } else {
            modelBalanceComprobacion.setItems(listaBalanceCompleta);
        }
    }

    private void mostrarFacturasPorPagar(boolean selected) {
        if (selected) {
            listaFacturaCompleta = new ArrayList<>(modelFactura.getItems());
            for (int i = 0; i < listaFacturaCompleta.size(); i++) {
                Factura item = listaFacturaCompleta.get(i);
                if (item.getMontoAPagar() <= item.getMontoPagado()) {
                    modelFactura.removeObject(item);
                }
            }
        } else {
            modelFactura.setItems(listaFacturaCompleta);
        }
    }

    private void crearObligacionPago() {
        Factura f = modelFactura.getObjectAtSelectedRow();
        int index = jTableFacturas.getSelectedRow();
        ObligacionDePago obligacion = new ObligacionDePago(this, f, cuentaController.getCuentasList(), false);
        obligacion.setVisible(true);
        Pago p = obligacion.getPago();
        if (p != null) {
            facturaController.createObligacionPago(f, p);
            modelFactura.fireTableRowsUpdated(index, index);
            onCreatePagoUpdate();
        }
    }

    private void crearObligacionCobro() {
        Factura f = modelFactura.getObjectAtSelectedRow();
        int index = jTableFacturas.getSelectedRow();
        ObligacionDePago obligacion = new ObligacionDePago(this, f, cuentaController.getCuentasList(), true);
        obligacion.setVisible(true);
        Pago p = obligacion.getPago();
        if (p != null) {
            facturaController.createObligacionCobro(f, p);
            modelFactura.fireTableRowsUpdated(index, index);
            onCreatePagoUpdate();
        }
    }
}

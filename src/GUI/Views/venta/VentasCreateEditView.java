/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.venta;

import GUI.Views.AbstractDetailView;
import GUI.Views.util.StateCellRender;
import GUI.Views.util.TableColumnAdjuster;
import com.jidesoft.hints.ListDataIntelliHints;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import restManager.controller.AbstractDialogController;
import restManager.controller.gasto.GastoController;
import restManager.controller.gasto.GastoOperacionController;
import restManager.controller.trabajadores.AsistenciaPersonalController;
import restManager.controller.venta.VentaDetailController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.NoSelectedException;
import restManager.persistencia.Area;
import restManager.persistencia.Cocina;
import restManager.persistencia.Control.VentaDAO1;
import restManager.persistencia.Orden;
import restManager.persistencia.Personal;
import restManager.persistencia.Venta;
import restManager.persistencia.models.CocinaDAO;
import restManager.printservice.Impresion;
import restManager.resources.R;
import restManager.util.RestManagerAbstractTableModel;
import restManager.util.RestManagerComboBoxModel;
import restManager.util.comun;

/**
 *
 * @author Jorge
 */
public class VentasCreateEditView extends AbstractDetailView<Venta> {

    RestManagerAbstractTableModel<Orden> modelOrd;
    GastoOperacionController gastoController = new GastoOperacionController();
    AsistenciaPersonalController personalController = new AsistenciaPersonalController();

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
        jPanelFooter = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jButtonTerminarVentas = new javax.swing.JButton();
        jPanelOptions = new javax.swing.JPanel();
        jideButton2 = new com.jidesoft.swing.JideButton();
        jPanel3 = new javax.swing.JPanel();
        jideButton1 = new com.jidesoft.swing.JideButton();
        jPanel1 = new javax.swing.JPanel();
        jPanelTurnosTrabajo = new javax.swing.JPanel();
        jButtonCambiarTurno = new javax.swing.JButton();
        jComboBoxSeleccionarVentaPorTurno = new javax.swing.JComboBox<>();
        jLabelFecha = new javax.swing.JLabel();
        jPanelData = new javax.swing.JPanel();
        jTabbedPaneData = new javax.swing.JTabbedPane();
        jPanelResumen = new javax.swing.JPanel();
        jPanelResumenVentas = new javax.swing.JPanel();
        jPanelNumero = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabelTotalVentas = new javax.swing.JLabel();
        jLabelTotalVentasNeta = new javax.swing.JLabel();
        jButtonImprimirZ = new javax.swing.JButton();
        jPanelVentasCamareras = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableVentasDependientes = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButtonImprimirDptes = new javax.swing.JButton();
        jButtonImpPagoVentas = new javax.swing.JButton();
        jPanelCocinaArea = new javax.swing.JPanel();
        jPanelVentasCocinas = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableVentasPorCocina = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jPanelVentasArea = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableVentasPorArea = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jPanelGastos = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabelTotalGastosAutorizo = new javax.swing.JLabel();
        jButtonImprimirZ1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabelTotalGastosInsumo = new javax.swing.JLabel();
        jLabelTotalGastos = new javax.swing.JLabel();
        jLabelTotalGastosPagoTrab = new javax.swing.JLabel();
        jPanelVentas = new javax.swing.JPanel();
        jPanelDetailOrdenes = new javax.swing.JPanel();
        jPanelOrdenesActivas = new javax.swing.JPanel();
        jideLabel1 = new com.jidesoft.swing.JideLabel();
        jXPanelOrdenControl = new org.jdesktop.swingx.JXPanel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButtonEnviarCerrarCrearNueva = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jXTableOrdActivas = new org.jdesktop.swingx.JXTable();
        jPanelOperaciones = new javax.swing.JPanel();
        jPanelExtracciones = new javax.swing.JPanel();
        jPanelPagoTrabajadores = new javax.swing.JPanel();

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

        jPanelFooter.setBackground(new java.awt.Color(204, 204, 204));
        jPanelFooter.setBorder(new org.pushingpixels.lafwidget.utils.ShadowPopupBorder());
        jPanelFooter.setLayout(new java.awt.BorderLayout());

        jPanel8.setOpaque(false);
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jButtonTerminarVentas.setText(bundle.getString("label_terminar_ventas")); // NOI18N
        jButtonTerminarVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTerminarVentasActionPerformed(evt);
            }
        });
        jPanel8.add(jButtonTerminarVentas);

        jPanelFooter.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanelRoot.add(jPanelFooter, java.awt.BorderLayout.SOUTH);

        jPanelOptions.setBackground(new java.awt.Color(204, 204, 204));
        jPanelOptions.setBorder(new org.pushingpixels.lafwidget.utils.ShadowPopupBorder());
        jPanelOptions.setLayout(new java.awt.BorderLayout());

        jideButton2.setForeground(new java.awt.Color(204, 204, 204));
        jideButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/logout40.png"))); // NOI18N
        jideButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jideButton2ActionPerformed(evt);
            }
        });
        jPanelOptions.add(jideButton2, java.awt.BorderLayout.WEST);

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

        jPanelTurnosTrabajo.setBorder(javax.swing.BorderFactory.createTitledBorder("Turnos de Trabajo"));
        jPanelTurnosTrabajo.setOpaque(false);

        jButtonCambiarTurno.setText(bundle.getString("label_cambiar_turno")); // NOI18N
        jButtonCambiarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCambiarTurnoActionPerformed(evt);
            }
        });
        jPanelTurnosTrabajo.add(jButtonCambiarTurno);

        jComboBoxSeleccionarVentaPorTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todo", "Turno 1", "Turno 2" }));
        jComboBoxSeleccionarVentaPorTurno.setToolTipText("Seleccione el turno a visualizar");
        jComboBoxSeleccionarVentaPorTurno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxSeleccionarVentaPorTurnoItemStateChanged(evt);
            }
        });
        jPanelTurnosTrabajo.add(jComboBoxSeleccionarVentaPorTurno);

        jPanel1.add(jPanelTurnosTrabajo);

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

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204), 3), "Propina"));
        jPanel10.setOpaque(false);

        jSpinner1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 5.0f));
        jSpinner1.setToolTipText("Propina general de la venta");
        jSpinner1.setMinimumSize(new java.awt.Dimension(50, 50));
        jSpinner1.setPreferredSize(new java.awt.Dimension(100, 20));
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });
        jPanel10.add(jSpinner1);

        jLabel1.setText(R.COIN_SUFFIX);
        jPanel10.add(jLabel1);

        jPanelNumero.add(jPanel10);

        jLabelTotalVentas.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabelTotalVentas.setText(bundle.getString("label_numeros_moneda")); // NOI18N
        jLabelTotalVentas.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 51), 3, true), bundle.getString("label_total"))); // NOI18N
        jPanelNumero.add(jLabelTotalVentas);

        jLabelTotalVentasNeta.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabelTotalVentasNeta.setText(bundle.getString("label_numeros_moneda")); // NOI18N
        jLabelTotalVentasNeta.setToolTipText("Este recuadro muestra la venta sin porciento por servicio");
        jLabelTotalVentasNeta.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 3, true), "Venta Neta"));
        jPanelNumero.add(jLabelTotalVentasNeta);

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

        jTableVentasDependientes.setAutoCreateRowSorter(true);
        jTableVentasDependientes.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jTableVentasDependientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuario", "Monto", "Ordenes Atendidas", "Pago por venta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
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
        jTableVentasDependientes.setRowHeight(25);
        jTableVentasDependientes.setRowMargin(5);
        jScrollPane3.setViewportView(jTableVentasDependientes);

        jPanelVentasCamareras.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.BorderLayout());

        jButtonImprimirDptes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImprimirDptes.setText(bundle.getString("label_imprimir_resumen_ventas")); // NOI18N
        jButtonImprimirDptes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirDptesActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonImprimirDptes, java.awt.BorderLayout.WEST);

        jButtonImpPagoVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImpPagoVentas.setText(bundle.getString("label_imprimir_pago_por_venta")); // NOI18N
        jButtonImpPagoVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImpPagoVentasActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonImpPagoVentas, java.awt.BorderLayout.EAST);

        jPanelVentasCamareras.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jPanelResumenVentas.add(jPanelVentasCamareras);

        jPanelCocinaArea.setOpaque(false);
        jPanelCocinaArea.setLayout(new java.awt.GridLayout(1, 0));

        jPanelVentasCocinas.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen de ventas por cocina"));
        jPanelVentasCocinas.setOpaque(false);
        jPanelVentasCocinas.setLayout(new java.awt.BorderLayout());

        jTableVentasPorCocina.setAutoCreateRowSorter(true);
        jTableVentasPorCocina.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
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
        jTableVentasPorCocina.setRowHeight(25);
        jTableVentasPorCocina.setRowMargin(5);
        jScrollPane5.setViewportView(jTableVentasPorCocina);

        jPanelVentasCocinas.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButton8.setText(bundle.getString("label_imprimir_resumen_ventas")); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton8);

        jPanelVentasCocinas.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jPanelCocinaArea.add(jPanelVentasCocinas);

        jPanelVentasArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen de ventas por area"));
        jPanelVentasArea.setOpaque(false);
        jPanelVentasArea.setLayout(new java.awt.BorderLayout());

        jTableVentasPorArea.setAutoCreateRowSorter(true);
        jTableVentasPorArea.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jTableVentasPorArea.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableVentasPorArea.setRowHeight(25);
        jTableVentasPorArea.setRowMargin(5);
        jScrollPane6.setViewportView(jTableVentasPorArea);

        jPanelVentasArea.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        jPanel7.setOpaque(false);
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButton9.setText(bundle.getString("label_imprimir_resumen_ventas")); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton9);

        jPanelVentasArea.add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jPanelCocinaArea.add(jPanelVentasArea);

        jPanelResumenVentas.add(jPanelCocinaArea);

        jPanelResumen.add(jPanelResumenVentas);

        jPanelGastos.setBackground(new java.awt.Color(255, 102, 102));
        jPanelGastos.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 0, 0), null, null), javax.swing.BorderFactory.createTitledBorder(null, "Gastos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 36)))); // NOI18N
        jPanelGastos.setLayout(new javax.swing.BoxLayout(jPanelGastos, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel9.setOpaque(false);
        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabelTotalGastosAutorizo.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabelTotalGastosAutorizo.setText(bundle.getString("label_numeros_moneda")); // NOI18N
        jLabelTotalGastosAutorizo.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 0, 102), 3, true), bundle.getString("label_autorizo"))); // NOI18N
        jPanel9.add(jLabelTotalGastosAutorizo, java.awt.BorderLayout.WEST);

        jButtonImprimirZ1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImprimirZ1.setText(bundle.getString("imprimir_gastos_casa")); // NOI18N
        jButtonImprimirZ1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButtonImprimirZ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirZ1ActionPerformed(evt);
            }
        });
        jPanel9.add(jButtonImprimirZ1, java.awt.BorderLayout.EAST);

        jPanel2.add(jPanel9, java.awt.BorderLayout.WEST);

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabelTotalGastosInsumo.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabelTotalGastosInsumo.setText(bundle.getString("label_numeros_moneda")); // NOI18N
        jLabelTotalGastosInsumo.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 0), 3, true), bundle.getString("label_insumo"))); // NOI18N
        jPanel6.add(jLabelTotalGastosInsumo);

        jLabelTotalGastos.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabelTotalGastos.setText(bundle.getString("label_numeros_moneda")); // NOI18N
        jLabelTotalGastos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 3, true), bundle.getString("label_gastos"))); // NOI18N
        jPanel6.add(jLabelTotalGastos);

        jLabelTotalGastosPagoTrab.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabelTotalGastosPagoTrab.setText(bundle.getString("label_numeros_moneda")); // NOI18N
        jLabelTotalGastosPagoTrab.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 204), 3, true), bundle.getString("label_pago_salario"))); // NOI18N
        jPanel6.add(jLabelTotalGastosPagoTrab);

        jPanel2.add(jPanel6, java.awt.BorderLayout.CENTER);

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
        jXTableOrdActivas.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        jXTableOrdActivas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jXTableOrdActivasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jXTableOrdActivas);

        jPanelOrdenesActivas.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanelVentas.add(jPanelOrdenesActivas, java.awt.BorderLayout.WEST);

        jTabbedPaneData.addTab("Ventas", jPanelVentas);

        jPanelOperaciones.setLayout(new java.awt.GridLayout(1, 0));

        jPanelExtracciones.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("label_extracciones"))); // NOI18N
        jPanelOperaciones.add(jPanelExtracciones);

        jPanelPagoTrabajadores.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("label_pago_trabajadores"))); // NOI18N
        jPanelOperaciones.add(jPanelPagoTrabajadores);

        jTabbedPaneData.addTab("Operaciones", jPanelOperaciones);

        jPanelData.add(jTabbedPaneData, java.awt.BorderLayout.CENTER);

        jPanelRoot.add(jPanelData, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelRoot);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        getController().createNewOrden();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jideButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jideButton1ActionPerformed
        getController().fetchNewDataFromServer(jComboBoxSeleccionarVentaPorTurno.getSelectedIndex());
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

    private void jideButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jideButton2ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jideButton2ActionPerformed

    private void jButtonCambiarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCambiarTurnoActionPerformed
        getController().cambiarTurno();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCambiarTurnoActionPerformed

    private void jButtonImpPagoVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImpPagoVentasActionPerformed
        int row = jTableVentasDependientes.getSelectedRow();
        if (row == -1) {
            throw new NoSelectedException(jTableVentasDependientes);
        }
        getController().printPagoPorVentaPersonal(jTableVentasDependientes.getValueAt(row, 0).toString());
    }//GEN-LAST:event_jButtonImpPagoVentasActionPerformed

    private void jComboBoxSeleccionarVentaPorTurnoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxSeleccionarVentaPorTurnoItemStateChanged
        getController().mostrarVenta(jComboBoxSeleccionarVentaPorTurno.getSelectedIndex());
    }//GEN-LAST:event_jComboBoxSeleccionarVentaPorTurnoItemStateChanged

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int row = jTableVentasPorArea.getSelectedRow();
        if (row == -1) {
            throw new NoSelectedException(jTableVentasPorArea);
        }
        getController().printAreaResumen((String) jTableVentasPorArea.getValueAt(row, 0));
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jXTableOrdActivasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXTableOrdActivasMouseClicked
    }//GEN-LAST:event_jXTableOrdActivasMouseClicked

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
       getController().setPropina((float) jSpinner1.getValue());
       updateTablePagoTrabajadores();
    }//GEN-LAST:event_jSpinner1StateChanged

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
        updateTableResumenPuntosElaboracion();
        updateTableResumenDptes();
        updateTableResumenDetallado();
        updateTableResumenGastos();
        updateTablePagoTrabajadores();
        updateTableResumenAreaVenta();
        jSpinner1.setValue(getController().getInstance().getVentapropina() != null ? getController().getInstance().getVentapropina() : (float)0);
        jLabelTotalVentas.setText(getController().getTotalVendido());
        jLabelTotalVentasNeta.setText(getController().getTotalVendidoNeto());
        jLabelTotalGastosInsumo.setText(getController().getTotalGastadoInsumos());
        jLabelTotalGastosPagoTrab.setText(getController().getTotalPagoTrabajadores());
        jLabelTotalGastosAutorizo.setText(getController().getTotalAutorizos());

    }

    @Override
    public VentaDetailController getController() {
        return (VentaDetailController) super.getController(); //To change body of generated methods, choose Tools | Templates.
    }

    public RestManagerAbstractTableModel<Orden> getModelOrd() {
        return modelOrd;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonCambiarTurno;
    private javax.swing.JButton jButtonEnviarCerrarCrearNueva;
    private javax.swing.JButton jButtonImpPagoVentas;
    private javax.swing.JButton jButtonImprimirDptes;
    private javax.swing.JButton jButtonImprimirZ;
    private javax.swing.JButton jButtonImprimirZ1;
    private javax.swing.JButton jButtonTerminarVentas;
    private javax.swing.JComboBox<String> jComboBoxSeleccionarVentaPorTurno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelTotalGastos;
    private javax.swing.JLabel jLabelTotalGastosAutorizo;
    private javax.swing.JLabel jLabelTotalGastosInsumo;
    private javax.swing.JLabel jLabelTotalGastosPagoTrab;
    private javax.swing.JLabel jLabelTotalVentas;
    private javax.swing.JLabel jLabelTotalVentasNeta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelCocinaArea;
    private javax.swing.JPanel jPanelData;
    private javax.swing.JPanel jPanelDetailOrdenes;
    private javax.swing.JPanel jPanelExtracciones;
    private javax.swing.JPanel jPanelFooter;
    private javax.swing.JPanel jPanelGastos;
    private javax.swing.JPanel jPanelNumero;
    private javax.swing.JPanel jPanelOperaciones;
    private javax.swing.JPanel jPanelOptions;
    private javax.swing.JPanel jPanelOrdenesActivas;
    private javax.swing.JPanel jPanelPagoTrabajadores;
    private javax.swing.JPanel jPanelResumen;
    private javax.swing.JPanel jPanelResumenDetallado;
    private javax.swing.JPanel jPanelResumenVentas;
    private javax.swing.JPanel jPanelRoot;
    private javax.swing.JPanel jPanelTurnosTrabajo;
    private javax.swing.JPanel jPanelVentas;
    private javax.swing.JPanel jPanelVentasArea;
    private javax.swing.JPanel jPanelVentasCamareras;
    private javax.swing.JPanel jPanelVentasCocinas;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPaneData;
    private javax.swing.JTabbedPane jTabbedPaneResumen;
    private javax.swing.JTable jTableVentasDependientes;
    private javax.swing.JTable jTableVentasPorArea;
    private javax.swing.JTable jTableVentasPorCocina;
    private org.jdesktop.swingx.JXPanel jXPanelOrdenControl;
    private org.jdesktop.swingx.JXTable jXTableOrdActivas;
    private com.jidesoft.swing.JideButton jideButton1;
    private com.jidesoft.swing.JideButton jideButton2;
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
        if (R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() < 3 && !R.CAJERO_PERMISOS_ESPECIALES) {
            jTabbedPaneData.remove(0);
        }
        jPanelTurnosTrabajo.setVisible(R.VARIOS_TURNOS);
        jComboBoxSeleccionarVentaPorTurno.setEnabled(R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() > 2);
        jButtonCambiarTurno.setEnabled(getController().getInstance().getCambioTurno1() == null);
        if (R.VARIOS_TURNOS) {
            jButtonTerminarVentas.setEnabled(getController().getInstance().getCambioTurno1() != null);
        }
    }

    private void updateTableResumenDptes() {
        List<Personal> p = getController().getPersonalList();
        comun.limpiarTabla(jTableVentasDependientes);
        p.forEach((x) -> {
            VentaDAO1.getResumenVentasCamareroOnTable(jTableVentasDependientes, getInstance(), x);
        });
    }

    private void updateTableResumenPuntosElaboracion() {
        List<Cocina> c = getController().getCocinaList();
        comun.limpiarTabla(jTableVentasPorCocina);
        c.forEach((x) -> {
            VentaDAO1.getResumenVentasCocinaOnTable(jTableVentasPorCocina, getInstance(), x);
        });

    }

    private void updateTableResumenAreaVenta() {
        List<Area> a = getController().getAreaList();
        comun.limpiarTabla(jTableVentasPorArea);
        a.forEach((x) -> {
            VentaDAO1.getResumenVentaPorAreaOnTable(jTableVentasPorArea, getInstance(), x);
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

    private void updateTableResumenGastos() {
        gastoController.setParent(jPanelExtracciones);
        gastoController.setDiaVenta(getController().getInstance());
        jLabelTotalGastos.setText(comun.setDosLugaresDecimales(gastoController.getValorTotalGastos()));
        gastoController.constructView(jPanelExtracciones);
    }

    private void updateTablePagoTrabajadores() {
        personalController.setParent(jPanelPagoTrabajadores);
        personalController.setDiaVenta(getController().getInstance());
        personalController.constructView(jPanelPagoTrabajadores);
    }
}

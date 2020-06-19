/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;
import com.jobits.pos.ui.utils.StateCellRender;
import com.jobits.pos.ui.utils.TableColumnAdjuster;
import java.awt.Color;
import java.awt.Container;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import mdlaf.components.tabbedpane.MaterialTabbedPaneUI;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.PieStyler;
import org.knowm.xchart.style.Styler;
import com.jobits.pos.controller.gasto.GastoOperacionController;
import com.jobits.pos.controller.trabajadores.AsistenciaPersonalController;
import com.jobits.pos.controller.venta.VentaDetailController;
import com.jobits.pos.exceptions.NoSelectedException;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.VentaDAO1;
import com.jobits.pos.domain.models.Orden;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.adapters.repo.impl.CocinaDAO;
import com.jobits.pos.adapters.repo.autenticacion.PersonalDAO;
import com.jobits.pos.controller.login.MainMenuController;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.main.PresenterFacade;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.AbstractViewPanel;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.BindableTableModel;
import com.jobits.pos.ui.utils.RestManagerAbstractTableModel;
import com.jobits.pos.ui.utils.utils;
import com.jobits.pos.ui.venta.presenter.ResumenVentaAreaTablaModel;
import com.jobits.pos.ui.venta.presenter.ResumenVentaPtoElabTablaModel;
import com.jobits.pos.ui.venta.presenter.ResumenVentaUsuarioTablaModel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import static com.jobits.pos.ui.venta.presenter.VentaResumenViewModel.*;
import com.jobits.pos.ui.venta.presenter.VentaResumenViewPresenter;
import static com.jobits.pos.ui.venta.presenter.VentaResumenViewPresenter.*;
import com.jobits.ui.components.MaterialComponentsFactory;
import java.awt.BorderLayout;
import java.awt.SplashScreen;
import java.awt.event.ActionEvent;

/**
 *
 * @author Jorge
 */
public class VentaDetailView extends AbstractViewPanel {

    public static final String VIEW_NAME = "Comenzar Turno";

    GastoOperacionController gastoController = new GastoOperacionController();
    AsistenciaPersonalController personalController = new AsistenciaPersonalController();
    Date fechaFin;
    MesaListView mesaView;
    OrdenDetailFragmentView ordenDetailView;
    private JFileChooser fileChooser;

    public VentaDetailView(AbstractViewPresenter presenter) {
        super(presenter);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelResumenDetallado = new javax.swing.JPanel();
        jTabbedPaneResumen = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanelDetailOrdenes = new javax.swing.JPanel();
        jPanelOrdenesActivas = new javax.swing.JPanel();
        jideLabel1 = new com.jidesoft.swing.JideLabel();
        jXPanelOrdenControl = new org.jdesktop.swingx.JXPanel();
        jButtonNuevaOrden = new javax.swing.JButton();
        jButtonCalcCAmbio = new javax.swing.JButton();
        jButtonEnviarCerrarCrearNueva = new javax.swing.JButton();
        jScrollPane2 = MaterialComponentsFactory.Containers.getScrollPane();
        jXTableOrdActivas = new org.jdesktop.swingx.JXTable();
        jPanelRoot = new javax.swing.JPanel();
        jPanelFooter = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButtonRefrescar = new com.jidesoft.swing.JideButton();
        jPanel1 = new javax.swing.JPanel();
        jLabelFecha = new javax.swing.JLabel();
        jPanelTurnosTrabajo = new javax.swing.JPanel();
        jButtonCambiarTurno = new javax.swing.JButton();
        jComboBoxSeleccionarVentaPorTurno = new javax.swing.JComboBox<>();
        jButtonReabrirVentas = new javax.swing.JButton();
        jButtonTerminarVentas1 = new javax.swing.JButton();
        jButtonTerminarYExportar = new javax.swing.JButton();
        jPanelData = new javax.swing.JPanel();
        jTabbedPaneData = new javax.swing.JTabbedPane();
        jPanelResumen = new javax.swing.JPanel();
        jTabbedPaneResumenD = new javax.swing.JTabbedPane();
        jPanelGeneral = new javax.swing.JPanel();
        jPanelGastos = new javax.swing.JPanel();
        jLabelTotalGastosInsumo = new javax.swing.JLabel();
        jLabelTotalGastosPagoTrab = new javax.swing.JLabel();
        jLabelTotalGastos = new javax.swing.JLabel();
        jPanelAutorizos = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jButtonImprimirAutorizos = new javax.swing.JButton();
        jLabelTotalGastosAutorizo = new javax.swing.JLabel();
        jPanelNumero = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabelTotalVentasNeta = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jButtonImprimirZ = new javax.swing.JButton();
        jLabelTotalVentas = new javax.swing.JLabel();
        jTextFieldPropina = MaterialComponentsFactory.Input.getTextFielPrecioVenta("0.0", "Propina", R.COIN_SUFFIX);
        jPanel13 = new javax.swing.JPanel();
        jPanelGraficaPieGenerales = new javax.swing.JPanel();
        jPanelAreas = new javax.swing.JPanel();
        jPanelVentasArea = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableVentasPorArea = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jButtonImprimirResumenArea = new javax.swing.JButton();
        jPanelDependientes = new javax.swing.JPanel();
        jPanelVentasCamareras = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableVentasDependientes = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButtonImprimirDptes = new javax.swing.JButton();
        jButtonImpPagoVentas = new javax.swing.JButton();
        jPanelPtoElab = new javax.swing.JPanel();
        jPanelCocinaArea = new javax.swing.JPanel();
        jPanelVentasCocinas = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableVentasPorCocina = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButtonImprimirResumenPto = new javax.swing.JButton();
        jPanelVentas = new javax.swing.JPanel();
        jPanelOperaciones = new javax.swing.JPanel();
        jPanelExtracciones = new javax.swing.JPanel();
        jPanelPagoTrabajadores = new javax.swing.JPanel();

        jPanelResumenDetallado.setLayout(new java.awt.BorderLayout());
        jPanelResumenDetallado.add(jTabbedPaneResumen, java.awt.BorderLayout.CENTER);

        jPanelDetailOrdenes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Detalles de orden"));
        jPanelDetailOrdenes.setLayout(new java.awt.BorderLayout());
        jPanel2.add(jPanelDetailOrdenes);

        jPanelOrdenesActivas.setPreferredSize(new java.awt.Dimension(400, 438));
        jPanelOrdenesActivas.setLayout(new java.awt.BorderLayout());

        jideLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jideLabel1.setText(bundle.getString("label_ordenes_activas")); // NOI18N
        jPanelOrdenesActivas.add(jideLabel1, java.awt.BorderLayout.PAGE_START);

        jXPanelOrdenControl.setBackground(new java.awt.Color(204, 204, 204));
        jXPanelOrdenControl.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        jXPanelOrdenControl.setLayout(new java.awt.GridLayout(2, 0, 4, 4));

        jButtonNuevaOrden.setText(bundle.getString("label_agregar")); // NOI18N
        jXPanelOrdenControl.add(jButtonNuevaOrden);

        jButtonCalcCAmbio.setText(bundle.getString("label_calcular_cambio")); // NOI18N
        jXPanelOrdenControl.add(jButtonCalcCAmbio);

        jButtonEnviarCerrarCrearNueva.setMnemonic('r');
        jButtonEnviarCerrarCrearNueva.setText("Cerrado Rapido");
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
        jScrollPane2.setViewportView(jXTableOrdActivas);

        jPanelOrdenesActivas.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanelOrdenesActivas);

        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new java.awt.BorderLayout());

        jPanelRoot.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanelRoot.setLayout(new java.awt.BorderLayout());

        jPanelFooter.setBackground(new java.awt.Color(204, 204, 204));
        jPanelFooter.setLayout(new java.awt.BorderLayout());

        jPanel8.setOpaque(false);
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButtonRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/refresh.png"))); // NOI18N
        jPanel3.add(jButtonRefrescar);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabelFecha.setText("Del 15/03/19 al 25/03/19");
        jLabelFecha.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha"));
        jPanel1.add(jLabelFecha);

        jPanel3.add(jPanel1);

        jPanel8.add(jPanel3);

        jPanelTurnosTrabajo.setBorder(javax.swing.BorderFactory.createTitledBorder("Turnos de Trabajo"));
        jPanelTurnosTrabajo.setOpaque(false);

        jButtonCambiarTurno.setText(bundle.getString("label_cambiar_turno")); // NOI18N
        jPanelTurnosTrabajo.add(jButtonCambiarTurno);

        jComboBoxSeleccionarVentaPorTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todo", "Turno 1", "Turno 2" }));
        jComboBoxSeleccionarVentaPorTurno.setToolTipText("Seleccione el turno a visualizar");
        jPanelTurnosTrabajo.add(jComboBoxSeleccionarVentaPorTurno);

        jPanel8.add(jPanelTurnosTrabajo);

        jButtonReabrirVentas.setText(bundle.getString("label_reabrir_ventas")); // NOI18N
        jButtonReabrirVentas.setEnabled(false);
        jPanel8.add(jButtonReabrirVentas);

        jButtonTerminarVentas1.setText(bundle.getString("label_terminar_ventas")); // NOI18N
        jPanel8.add(jButtonTerminarVentas1);

        jButtonTerminarYExportar.setText(bundle.getString("label_terminar_y_exportar")); // NOI18N
        jButtonTerminarYExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTerminarYExportarActionPerformed(evt);
            }
        });
        jPanel8.add(jButtonTerminarYExportar);

        jPanelFooter.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanelRoot.add(jPanelFooter, java.awt.BorderLayout.SOUTH);

        jPanelData.setLayout(new java.awt.BorderLayout());

        jTabbedPaneData.setToolTipText("");
        jTabbedPaneData.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jPanelResumen.setLayout(new java.awt.BorderLayout());

        jTabbedPaneResumenD.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPaneResumenD.setToolTipText("");
        jTabbedPaneResumenD.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jPanelGeneral.setLayout(new java.awt.BorderLayout());

        jPanelGastos.setBackground(new java.awt.Color(255, 102, 102));
        jPanelGastos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Gastos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font(".SF NS Text", 1, 24))); // NOI18N
        jPanelGastos.setMaximumSize(new java.awt.Dimension(250, 136));
        jPanelGastos.setMinimumSize(new java.awt.Dimension(150, 111));
        jPanelGastos.setPreferredSize(new java.awt.Dimension(190, 111));
        jPanelGastos.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 15));

        jLabelTotalGastosInsumo.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabelTotalGastosInsumo.setText(bundle.getString("label_numeros_moneda")); // NOI18N
        jLabelTotalGastosInsumo.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 0), 3, true), bundle.getString("label_insumo"))); // NOI18N
        jLabelTotalGastosInsumo.setMaximumSize(new java.awt.Dimension(324234234, 80));
        jLabelTotalGastosInsumo.setPreferredSize(new java.awt.Dimension(160, 47));
        jPanelGastos.add(jLabelTotalGastosInsumo);

        jLabelTotalGastosPagoTrab.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabelTotalGastosPagoTrab.setText(bundle.getString("label_numeros_moneda")); // NOI18N
        jLabelTotalGastosPagoTrab.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 204), 3, true), bundle.getString("label_pago_salario"))); // NOI18N
        jLabelTotalGastosPagoTrab.setMaximumSize(new java.awt.Dimension(324234234, 80));
        jLabelTotalGastosPagoTrab.setPreferredSize(new java.awt.Dimension(160, 47));
        jPanelGastos.add(jLabelTotalGastosPagoTrab);

        jLabelTotalGastos.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabelTotalGastos.setText(bundle.getString("label_numeros_moneda")); // NOI18N
        jLabelTotalGastos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 3, true), bundle.getString("label_otros"))); // NOI18N
        jLabelTotalGastos.setMaximumSize(new java.awt.Dimension(324234234, 80));
        jLabelTotalGastos.setPreferredSize(new java.awt.Dimension(160, 47));
        jPanelGastos.add(jLabelTotalGastos);

        jPanelAutorizos.setMaximumSize(new java.awt.Dimension(2147483647, 80));
        jPanelAutorizos.setOpaque(false);
        jPanelAutorizos.setLayout(new java.awt.BorderLayout());

        jPanel12.setOpaque(false);
        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonImprimirAutorizos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImprimirAutorizos.setToolTipText(bundle.getString("imprimir_gastos_casa")); // NOI18N
        jButtonImprimirAutorizos.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel12.add(jButtonImprimirAutorizos);

        jPanelAutorizos.add(jPanel12, java.awt.BorderLayout.PAGE_END);

        jLabelTotalGastosAutorizo.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabelTotalGastosAutorizo.setText(bundle.getString("label_numeros_moneda")); // NOI18N
        jLabelTotalGastosAutorizo.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 0, 102), 3, true), bundle.getString("label_autorizo"))); // NOI18N
        jLabelTotalGastosAutorizo.setPreferredSize(new java.awt.Dimension(140, 47));
        jPanelAutorizos.add(jLabelTotalGastosAutorizo, java.awt.BorderLayout.CENTER);

        jPanelGastos.add(jPanelAutorizos);

        jPanelGeneral.add(jPanelGastos, java.awt.BorderLayout.EAST);

        jPanelNumero.setBackground(new java.awt.Color(153, 255, 153));
        jPanelNumero.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Ventas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font(".SF NS Text", 1, 36))); // NOI18N
        jPanelNumero.setMaximumSize(new java.awt.Dimension(250, 32767));
        jPanelNumero.setMinimumSize(new java.awt.Dimension(150, 146));
        jPanelNumero.setPreferredSize(new java.awt.Dimension(190, 121));
        jPanelNumero.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 15));

        jPanel9.setOpaque(false);
        jPanel9.setSize(new java.awt.Dimension(0, 80));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabelTotalVentasNeta.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabelTotalVentasNeta.setText(bundle.getString("label_numeros_moneda")); // NOI18N
        jLabelTotalVentasNeta.setToolTipText("Este recuadro muestra la venta sin porciento por servicio");
        jLabelTotalVentasNeta.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 3, true), "Venta Neta"));
        jLabelTotalVentasNeta.setPreferredSize(new java.awt.Dimension(170, 80));
        jPanel9.add(jLabelTotalVentasNeta, java.awt.BorderLayout.CENTER);

        jPanel11.setOpaque(false);
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonImprimirZ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImprimirZ.setToolTipText("Imprimir Z");
        jButtonImprimirZ.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel11.add(jButtonImprimirZ);

        jPanel9.add(jPanel11, java.awt.BorderLayout.PAGE_START);

        jPanelNumero.add(jPanel9);

        jLabelTotalVentas.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabelTotalVentas.setText(bundle.getString("label_numeros_moneda")); // NOI18N
        jLabelTotalVentas.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 51), 3, true), bundle.getString("label_total"))); // NOI18N
        jLabelTotalVentas.setMaximumSize(new java.awt.Dimension(180, 80));
        jLabelTotalVentas.setMinimumSize(new java.awt.Dimension(0, 80));
        jLabelTotalVentas.setPreferredSize(new java.awt.Dimension(170, 80));
        jLabelTotalVentas.setSize(new java.awt.Dimension(0, 80));
        jPanelNumero.add(jLabelTotalVentas);

        jTextFieldPropina.setMaximumSize(new java.awt.Dimension(180, 80));
        jTextFieldPropina.setMinimumSize(new java.awt.Dimension(0, 80));
        jTextFieldPropina.setPreferredSize(new java.awt.Dimension(170, 80));
        jTextFieldPropina.setSize(new java.awt.Dimension(0, 80));
        jPanelNumero.add(jTextFieldPropina);

        jPanelGeneral.add(jPanelNumero, java.awt.BorderLayout.WEST);

        jPanel13.setLayout(new java.awt.BorderLayout());

        jPanelGraficaPieGenerales.setLayout(new java.awt.BorderLayout());
        jPanel13.add(jPanelGraficaPieGenerales, java.awt.BorderLayout.CENTER);

        jPanelGeneral.add(jPanel13, java.awt.BorderLayout.CENTER);

        jTabbedPaneResumenD.addTab("General", jPanelGeneral);

        jPanelAreas.setLayout(new javax.swing.BoxLayout(jPanelAreas, javax.swing.BoxLayout.PAGE_AXIS));

        jPanelVentasArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen de ventas por area"));
        jPanelVentasArea.setOpaque(false);
        jPanelVentasArea.setLayout(new java.awt.BorderLayout());

        jTableVentasPorArea.setAutoCreateRowSorter(true);
        jTableVentasPorArea.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jTableVentasPorArea.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Neta", "Real"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
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
        jTableVentasPorArea.setRowHeight(25);
        jTableVentasPorArea.setRowMargin(5);
        jScrollPane6.setViewportView(jTableVentasPorArea);

        jPanelVentasArea.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        jPanel7.setOpaque(false);
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButtonImprimirResumenArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImprimirResumenArea.setText(bundle.getString("label_imprimir_resumen_ventas")); // NOI18N
        jPanel7.add(jButtonImprimirResumenArea);

        jPanelVentasArea.add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jPanelAreas.add(jPanelVentasArea);

        jTabbedPaneResumenD.addTab("Areas", jPanelAreas);

        jPanelDependientes.setLayout(new java.awt.BorderLayout());

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
        jPanel4.add(jButtonImprimirDptes, java.awt.BorderLayout.WEST);

        jButtonImpPagoVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImpPagoVentas.setText("Imprimir pago por comision");
        jPanel4.add(jButtonImpPagoVentas, java.awt.BorderLayout.EAST);

        jPanelVentasCamareras.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jPanelDependientes.add(jPanelVentasCamareras, java.awt.BorderLayout.CENTER);

        jTabbedPaneResumenD.addTab("Dependientes", jPanelDependientes);

        jPanelPtoElab.setLayout(new java.awt.BorderLayout());

        jPanelCocinaArea.setOpaque(false);
        jPanelCocinaArea.setLayout(new java.awt.GridLayout(1, 0));

        jPanelVentasCocinas.setBorder(javax.swing.BorderFactory.createTitledBorder("Ventas por punto elaboración"));
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

        jButtonImprimirResumenPto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jButtonImprimirResumenPto.setText(bundle.getString("label_imprimir_resumen_ventas")); // NOI18N
        jPanel5.add(jButtonImprimirResumenPto);

        jPanelVentasCocinas.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jPanelCocinaArea.add(jPanelVentasCocinas);

        jPanelPtoElab.add(jPanelCocinaArea, java.awt.BorderLayout.CENTER);

        jTabbedPaneResumenD.addTab("Ptos Elaboracion", jPanelPtoElab);

        jPanelResumen.add(jTabbedPaneResumenD, java.awt.BorderLayout.CENTER);

        jTabbedPaneData.addTab("Resumen", jPanelResumen);

        jPanelVentas.setLayout(new java.awt.BorderLayout(20, 20));
        jTabbedPaneData.addTab("Ventas", jPanelVentas);

        jPanelOperaciones.setLayout(new java.awt.GridLayout(1, 0));

        jPanelExtracciones.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("label_extracciones"))); // NOI18N
        jPanelExtracciones.setLayout(new java.awt.BorderLayout());
        jPanelOperaciones.add(jPanelExtracciones);

        jPanelPagoTrabajadores.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("label_pago_trabajadores"))); // NOI18N
        jPanelPagoTrabajadores.setLayout(new java.awt.BorderLayout());
        jPanelOperaciones.add(jPanelPagoTrabajadores);

        jTabbedPaneData.addTab("Operaciones", jPanelOperaciones);

        jPanelData.add(jTabbedPaneData, java.awt.BorderLayout.CENTER);

        jPanelRoot.add(jPanelData, java.awt.BorderLayout.CENTER);

        add(jPanelRoot, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTerminarYExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTerminarYExportarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonTerminarYExportarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCalcCAmbio;
    private javax.swing.JButton jButtonCambiarTurno;
    private javax.swing.JButton jButtonEnviarCerrarCrearNueva;
    private javax.swing.JButton jButtonImpPagoVentas;
    private javax.swing.JButton jButtonImprimirAutorizos;
    private javax.swing.JButton jButtonImprimirDptes;
    private javax.swing.JButton jButtonImprimirResumenArea;
    private javax.swing.JButton jButtonImprimirResumenPto;
    private javax.swing.JButton jButtonImprimirZ;
    private javax.swing.JButton jButtonNuevaOrden;
    private javax.swing.JButton jButtonReabrirVentas;
    private com.jidesoft.swing.JideButton jButtonRefrescar;
    private javax.swing.JButton jButtonTerminarVentas1;
    private javax.swing.JButton jButtonTerminarYExportar;
    private javax.swing.JComboBox<String> jComboBoxSeleccionarVentaPorTurno;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelTotalGastos;
    private javax.swing.JLabel jLabelTotalGastosAutorizo;
    private javax.swing.JLabel jLabelTotalGastosInsumo;
    private javax.swing.JLabel jLabelTotalGastosPagoTrab;
    private javax.swing.JLabel jLabelTotalVentas;
    private javax.swing.JLabel jLabelTotalVentasNeta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelAreas;
    private javax.swing.JPanel jPanelAutorizos;
    private javax.swing.JPanel jPanelCocinaArea;
    private javax.swing.JPanel jPanelData;
    private javax.swing.JPanel jPanelDependientes;
    private javax.swing.JPanel jPanelDetailOrdenes;
    private javax.swing.JPanel jPanelExtracciones;
    private javax.swing.JPanel jPanelFooter;
    private javax.swing.JPanel jPanelGastos;
    private javax.swing.JPanel jPanelGeneral;
    private javax.swing.JPanel jPanelGraficaPieGenerales;
    private javax.swing.JPanel jPanelNumero;
    private javax.swing.JPanel jPanelOperaciones;
    private javax.swing.JPanel jPanelOrdenesActivas;
    private javax.swing.JPanel jPanelPagoTrabajadores;
    private javax.swing.JPanel jPanelPtoElab;
    private javax.swing.JPanel jPanelResumen;
    private javax.swing.JPanel jPanelResumenDetallado;
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
    private javax.swing.JTabbedPane jTabbedPaneData;
    private javax.swing.JTabbedPane jTabbedPaneResumen;
    private javax.swing.JTabbedPane jTabbedPaneResumenD;
    private javax.swing.JTable jTableVentasDependientes;
    private javax.swing.JTable jTableVentasPorArea;
    private javax.swing.JTable jTableVentasPorCocina;
    private javax.swing.JTextField jTextFieldPropina;
    private org.jdesktop.swingx.JXPanel jXPanelOrdenControl;
    private org.jdesktop.swingx.JXTable jXTableOrdActivas;
    private com.jidesoft.swing.JideLabel jideLabel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void wireUp() {
        Bindings.bind(jLabelTotalVentasNeta, getPresenter().getModel(PROP_VENTA_NETA));
        Bindings.bind(jLabelTotalVentas, getPresenter().getModel(PROP_VENTA_TOTAL));
        Bindings.bind(jLabelTotalGastos, getPresenter().getModel(PROP_TOTAL_GASTO_OTROS));
        Bindings.bind(jLabelTotalGastosInsumo, getPresenter().getModel(PROP_TOTAL_GASTO_INSUMOS));
        Bindings.bind(jLabelTotalGastosPagoTrab, getPresenter().getModel(PROP_TOTAL_GASTO_SALARIO));
        Bindings.bind(jLabelTotalGastosAutorizo, getPresenter().getModel(PROP_TOTAL_AUTORIZOS));
        Bindings.bind(jTextFieldPropina, getPresenter().getModel(PROP_PROPINA_TOTAL));//TODO: manejar enabled
        Bindings.bind(fileChooser, "selectedFile", getPresenter().getModel(PROP_FILE_FOR_EXPORT));
        Bindings.bind(jLabelFecha, getPresenter().getModel(PROP_FECHA));

        jButtonImprimirZ.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_Z));
        jButtonImprimirAutorizos.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_AUTORIZOS));
        jButtonTerminarVentas1.addActionListener(getPresenter().getOperation(ACTION_TERMINAR_VENTAS));
        jButtonTerminarYExportar.addActionListener((ActionEvent e) -> {
            int result = fileChooser.showSaveDialog(jPanelRoot);
            if (result == JFileChooser.APPROVE_OPTION) {
                getPresenter().getOperation(ACTION_TERMINAR_EXPORTAR).doAction();
            }
        });
        Bindings.bind(jButtonReabrirVentas, "enabled", getPresenter().getModel(PROP_REABRIR_VENTAS_ENABLED));
        jButtonReabrirVentas.addActionListener(getPresenter().getOperation(ACTION_REABRIR_VENTA));

        //Areas
        Bindings.bind(jTableVentasPorArea,
                new SelectionInList<ResumenVentaAreaTablaModel>(
                        getPresenter().getModel(PROP_LISTA_RESUMEN_AREA_VENTA),
                        getPresenter().getModel(PROP_RESUMEM_AREA_SELECCIONADA)));
        jButtonImprimirResumenArea.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_RESUMEN_AREA));

        //Dpte
        Bindings.bind(jTableVentasDependientes,
                new SelectionInList<ResumenVentaUsuarioTablaModel>(
                        getPresenter().getModel(PROP_LISTA_RESUMEN_PERSONAL_VENTA),
                        getPresenter().getModel(PROP_RESUMEN_USUARIO_SELECCIONADO)));
        jButtonImprimirDptes.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_RESUMEN_USUARIO));
        jButtonImpPagoVentas.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_RESUMEN_USUARIO_COMISION));

        //Pto elab
        Bindings.bind(jTableVentasPorCocina,
                new SelectionInList<ResumenVentaUsuarioTablaModel>(
                        getPresenter().getModel(PROP_LISTA_RESUMEN_PTO_VENTA),
                        getPresenter().getModel(PROP_RESUMEN_PTO_SELECCIONADO)));
        jButtonImprimirResumenPto.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_RESUMEN_PTO));

        //Ventas
        Bindings.bind(jXTableOrdActivas,
                new SelectionInList<Orden>(
                        getPresenter().getModel(PROP_LISTA_ORDEN),
                        getPresenter().getModel(PROP_ORDEN_SELECCIONADA)));
        jXTableOrdActivas.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting() && jXTableOrdActivas.getSelectedRow() != -1) {
                getPresenter().getOperation(ACTION_ABRIR_ORDEN).doAction();
            }
        });
        jButtonNuevaOrden.addActionListener(getPresenter().getOperation(ACTION_ABRIR_ORDEN));
        Bindings.bind(jButtonCambiarTurno, "enabled", getPresenter().getModel(PROP_CAMBIAR_TURNO_ENABLED));
        updateGraficasResumenGeneralVentas();

    }

    @Override
    public void uiInit() {
        initComponents();
        jTabbedPaneResumenD.setUI(new ui.componentsui.tabbedpane.MaterialTabbedPaneUI());
        jTabbedPaneData.setUI(new ui.componentsui.tabbedpane.MaterialTabbedPaneUI());
        initAreaTableUI();
        initUsuarioTablaUI();
        initPtoElaboracionUI();
        initOrdenTableUI();
        if (R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() < 3 && !R.CAJERO_PERMISOS_ESPECIALES) {
            jTabbedPaneData.remove(0);
        }//TODO autorizacion en el view
        jPanelTurnosTrabajo.setVisible(R.VARIOS_TURNOS);//TODO:otro mojon
        jComboBoxSeleccionarVentaPorTurno.setEnabled(R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() > 2);//TODO: otro mas
        fileChooser = new JFileChooser();
        mesaView = new MesaListView(PresenterFacade.getPresenterFor(MesaListView.VIEW_NAME));
        jPanelVentas.add(mesaView);
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

    private void initPtoElaboracionUI() {
        BindableTableModel<ResumenVentaPtoElabTablaModel> ptoElabModel
                = new BindableTableModel<ResumenVentaPtoElabTablaModel>(jTableVentasPorCocina) {
            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Codigo";
                    case 1:
                        return "Nombre";
                    case 2:
                        return "Monto";
                    default:
                        return null;
                }
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return getRow(rowIndex).getCodigoPto();
                    case 1:
                        return getRow(rowIndex).getNombrePto();
                    case 2:
                        return getRow(rowIndex).getMontoRecaudado();
                    default:
                        return null;
                }
            }
        };
        jTableVentasPorCocina.setModel(ptoElabModel);
    }

    private void initUsuarioTablaUI() {
        BindableTableModel<ResumenVentaUsuarioTablaModel> ventasDpteModel
                = new BindableTableModel<ResumenVentaUsuarioTablaModel>(jTableVentasDependientes) {
            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Usuario";
                    case 1:
                        return "Monto";
                    case 2:
                        return "Ordenes Atendidas";
                    case 3:
                        return "Comision por ventas";
                    default:
                        return null;
                }
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return getRow(rowIndex).getUsuario();
                    case 1:
                        return getRow(rowIndex).getMontoRecaudado();
                    case 2:
                        return getRow(rowIndex).getOrdenesAtendidas();
                    case 3:
                        return getRow(rowIndex).getPagoPorVenta();
                    default:
                        return null;
                }
            }
        };
        jTableVentasDependientes.setModel(ventasDpteModel);
    }

    private void initAreaTableUI() {
        BindableTableModel<ResumenVentaAreaTablaModel> areaTablaModel
                = new BindableTableModel<ResumenVentaAreaTablaModel>(jTableVentasPorArea) {
            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Codigo";
                    case 1:
                        return "Nombre";
                    case 2:
                        return "V. Neta";
                    case 3:
                        return "V. Real";
                    default:
                        return null;
                }
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return getRow(rowIndex).getCodArea();
                    case 1:
                        return getRow(rowIndex).getNombreArea();
                    case 2:
                        return getRow(rowIndex).getTotalReacuadadoNeta();
                    case 3:
                        return getRow(rowIndex).getTotalRecaudadoReal();
                    default:
                        return null;
                }
            }
        };
        jTableVentasPorArea.setModel(areaTablaModel);
    }

    private void initOrdenTableUI() {
        TableColumnAdjuster adj = new TableColumnAdjuster(jXTableOrdActivas);
        adj.setDynamicAdjustment(true);

        if (fechaFin != null) {// viene faltando cuando es un resumen detallado
//            jLabelFecha.setText("Del " + R.DATE_FORMAT.format(instance.getFecha()) + " Al "
//                    + R.DATE_FORMAT.format(fechaFin));
//            jTabbedPaneData.removeTabAt(1);
//            jButtonReabrirVentas.setVisible(false);
//            jButtonRefrescar.setVisible(false);

        } else {
            BindableTableModel<Orden> modelOrd
                    = new BindableTableModel<Orden>(jXTableOrdActivas) {
                @Override
                public int getColumnCount() {
                    return 4;
                }

                @Override
                public Object getValueAt(int rowIndex, int columnIndex) {
                    switch (columnIndex) {
                        case 0:
                            return getRow(rowIndex).getCodOrden();
                        case 1:
                            return getRow(rowIndex).getMesacodMesa().getCodMesa();
                        case 2:
                            return getRow(rowIndex).getOrdenvalorMonetario() + R.COIN_SUFFIX;
                        case 3:
                            return getRow(rowIndex);
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

            adj.adjustColumns();
        }
    }

    //TODO: agregar listener al bean para nada mas cambien los datos se actualize la tabla p mejor agregar los listener a los jtextfield
    private void updateGraficasResumenGeneralVentas() {
        PieChart chartPie = new PieChartBuilder().theme(Styler.ChartTheme.XChart).title("Ventas/Gastos ").build();
        chartPie.getStyler().setAnnotationType(PieStyler.AnnotationType.Percentage);
        chartPie.getStyler().setChartTitleBoxVisible(true);
        chartPie.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
        chartPie.getStyler().setLegendBackgroundColor(new Color(255, 255, 255, 0));

        float insumo = Float.parseFloat(jLabelTotalGastosInsumo.getText().split(" ")[0]);
        float otros = Float.parseFloat(jLabelTotalGastos.getText().split(" ")[0]);
        float trabajadores = Float.parseFloat(jLabelTotalGastosPagoTrab.getText().split(" ")[0]);
        float gananciaRelativa = Float.parseFloat(jLabelTotalVentas.getText().split(" ")[0]) - insumo - otros - trabajadores;

        chartPie.addSeries("Gastos de Insumo " + utils.setDosLugaresDecimales(insumo), insumo);
        chartPie.addSeries("Pago a trabajadores " + utils.setDosLugaresDecimales(trabajadores), trabajadores);
        chartPie.addSeries("Otros gastos " + utils.setDosLugaresDecimales(otros), otros);
        chartPie.addSeries("Resto de efectivo " + utils.setDosLugaresDecimales(gananciaRelativa), gananciaRelativa).setFillColor(Color.GREEN);

        XChartPanel wrapperPie = new XChartPanel(chartPie);
        jPanelGraficaPieGenerales.add(wrapperPie);
    }

    private void crearFrame() {
//        String hVentas,
//                hGastos,
//                cDate,
//                nombreMenu = R.REST_NAME;
//
//        if (getController().getInstance().getFecha().getDate() == fechaFin.getDate()
//                && getController().getInstance().getFecha().getMonth() == fechaFin.getMonth()) {
//            cDate = R.DATE_FORMAT.format(getController().getInstance().getFecha())
//                    + "(" + nombreMenu + ")";
//            hVentas = ("Ventas del dia " + cDate);
//            hGastos = ("Gastos por productos del dia " + cDate);
//
//        } else {
//            cDate = R.DATE_FORMAT.format(getController().getInstance().getFecha())
//                    + " al " + R.DATE_FORMAT.format(fechaFin) + "(" + nombreMenu + ")";
//            hVentas = ("Resumen de ventas del " + cDate);
//            hGastos = ("Resumen de gastos del " + cDate);
//        }
//
//        jTabbedPaneResumen.addTab("Resumen Total ", new Resumenes(getController().getInstance(), fechaFin, hVentas, hGastos));
//        List<Cocina> cocinas = CocinaDAO.getInstance().findAll();
//        for (int i = 0; i < cocinas.size(); i++) {
//            jTabbedPaneResumen.addTab(cocinas.get(i).getNombreCocina(),
//                    new Resumenes(getController().getInstance(), cocinas.get(i), fechaFin,
//                            "Ventas de " + cocinas.get(i).getNombreCocina() + " " + cDate,
//                            "Gastos por producto " + cocinas.get(i).getNombreCocina() + " " + cDate));
//        }
    }

    private void updateTableResumenDetallado() {
        if (fechaFin != null) {
            jTabbedPaneData.addTab("Resumen Detallado", jPanelResumenDetallado);
            crearFrame();
        }
    }

    public void addOrdenView(OrdenDetailFragmentView view) {
        if (jPanelDetailOrdenes.getComponentCount() == 0) {
            jPanelDetailOrdenes.add(view, BorderLayout.CENTER);
        }
        // repaint();
    }

}

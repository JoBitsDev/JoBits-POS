/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.list.SelectionInList;
import com.jobits.pos.ui.utils.VentaCellRender;
import javax.swing.JFileChooser;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.core.domain.models.temporal.ResumenVentaEstadisticas;
import com.jobits.pos.ui.AbstractViewPanel;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.swing.utils.BindableTableModel;
import com.jobits.pos.ui.venta.presenter.VentaCalendarViewModel;
import com.jobits.pos.ui.venta.presenter.VentaCalendarViewPresenter;
import com.jobits.ui.components.MaterialComponentsFactory;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author Jorge
 */
public class VentaCalendarView extends AbstractViewPanel {

    public static final String VIEW_NAME = "Ventas";

    private JFileChooser fileChooser;

    private BindableTableModel<DayReviewWrapper<ResumenVentaEstadisticas>> model;

    public VentaCalendarView(AbstractViewPresenter presenter) {
        super(presenter);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanel4 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanelSeleccion = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jPanel3 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanel1 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jLabel9 = new javax.swing.JLabel();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jPanel2 = MaterialComponentsFactory.Containers.getTransparentPanel();
        jLabel8 = new javax.swing.JLabel();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jPanelDetalles = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jPanelCalendario = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanelHeader = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = MaterialComponentsFactory.Containers.getScrollPane();
        jTableCalendar = new javax.swing.JTable();
        jPanelEstadisticas = MaterialComponentsFactory.Containers.getTransparentPanel();
        jPanelVentas = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanelV = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jLabelTotalVendido = new javax.swing.JLabel();
        jLabelPromedioVendido = new javax.swing.JLabel();
        jLabelHoraPico = new javax.swing.JLabel();
        jPanelGastos = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanelG = MaterialComponentsFactory.Containers.getSecondaryPanel();
        jLabelInsumo = new javax.swing.JLabel();
        jLabelTrabajadores = new javax.swing.JLabel();
        jLabelOtros = new javax.swing.JLabel();
        jPanelControles = MaterialComponentsFactory.Containers.getPrimaryPanel();
        jButtonEliminar = MaterialComponentsFactory.Buttons.getLinedButton();
        jButtonEditar = MaterialComponentsFactory.Buttons.getLinedButton();
        jButtonY = MaterialComponentsFactory.Buttons.getLinedButton();
        jButtonImportarVentas = MaterialComponentsFactory.Buttons.getLinedButton();

        jScrollPane2.setViewportView(jTree1);

        setOpaque(false);
        setLayout(new java.awt.BorderLayout(5, 5));

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanelSeleccion.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 10, 0));
        jPanelSeleccion.setMinimumSize(new java.awt.Dimension(325, 60));
        jPanelSeleccion.setPreferredSize(new java.awt.Dimension(325, 60));
        jPanelSeleccion.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(400, 100));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(150, 100));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 2, 18)); // NOI18N
        jLabel9.setText("Año");
        jPanel1.add(jLabel9);

        jYearChooser1.setOpaque(false);
        jYearChooser1.setPreferredSize(new java.awt.Dimension(80, 35));
        jPanel1.add(jYearChooser1);

        jPanel3.add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setPreferredSize(new java.awt.Dimension(200, 100));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 2, 18)); // NOI18N
        jLabel8.setText("Mes");
        jPanel2.add(jLabel8);

        jMonthChooser1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jMonthChooser1.setMinimumSize(new java.awt.Dimension(176, 40));
        jMonthChooser1.setOpaque(false);
        jMonthChooser1.setPreferredSize(new java.awt.Dimension(176, 35));
        jPanel2.add(jMonthChooser1);

        jPanel3.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanelSeleccion.add(jPanel3, java.awt.BorderLayout.EAST);

        jPanel4.add(jPanelSeleccion, java.awt.BorderLayout.PAGE_START);

        jPanelDetalles.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 15, 15));
        jPanelDetalles.setLayout(new java.awt.BorderLayout());

        jPanelCalendario.setLayout(new java.awt.BorderLayout());

        jPanelHeader.setPreferredSize(new java.awt.Dimension(595, 50));
        jPanelHeader.setLayout(new java.awt.GridLayout(1, 7));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Lunes");
        jPanelHeader.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Martes");
        jPanelHeader.add(jLabel3);

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Miercoles");
        jPanelHeader.add(jLabel4);

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Jueves");
        jPanelHeader.add(jLabel5);

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Viernes");
        jPanelHeader.add(jLabel6);

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Sabado");
        jPanelHeader.add(jLabel7);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Domingo");
        jPanelHeader.add(jLabel1);

        jPanelCalendario.add(jPanelHeader, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        jScrollPane1.setOpaque(false);

        jTableCalendar.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jTableCalendar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableCalendar.setColumnSelectionAllowed(true);
        jTableCalendar.setOpaque(false);
        jTableCalendar.setRowHeight(100);
        jTableCalendar.setShowGrid(false);
        jTableCalendar.setTableHeader(null);
        jScrollPane1.setViewportView(jTableCalendar);

        jPanelCalendario.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanelDetalles.add(jPanelCalendario, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanelDetalles, java.awt.BorderLayout.CENTER);

        jPanelEstadisticas.setPreferredSize(new java.awt.Dimension(200, 527));
        jPanelEstadisticas.setLayout(new java.awt.GridLayout(2, 0, 0, 20));

        jPanelVentas.setLayout(new java.awt.BorderLayout());

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Ventas");
        jPanelVentas.add(jLabel10, java.awt.BorderLayout.PAGE_START);

        jPanelV.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanelV.setLayout(new java.awt.GridLayout(3, 0));

        jLabelTotalVendido.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabelTotalVendido.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTotalVendido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/pulgar-arriba.png"))); // NOI18N
        jLabelTotalVendido.setText("XXXXXX.XX < >");
        jLabelTotalVendido.setToolTipText("Total");
        jLabelTotalVendido.setBorder(javax.swing.BorderFactory.createTitledBorder("Total"));
        jPanelV.add(jLabelTotalVendido);

        jLabelPromedioVendido.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabelPromedioVendido.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelPromedioVendido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/promedio.png"))); // NOI18N
        jLabelPromedioVendido.setText("XXXXXX.XX < >");
        jLabelPromedioVendido.setToolTipText("Promedio");
        jLabelPromedioVendido.setBorder(javax.swing.BorderFactory.createTitledBorder("Promedio"));
        jPanelV.add(jLabelPromedioVendido);

        jLabelHoraPico.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabelHoraPico.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelHoraPico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/hora_pico.png"))); // NOI18N
        jLabelHoraPico.setText("XX PM/AM");
        jLabelHoraPico.setToolTipText("Hora Pico");
        jLabelHoraPico.setBorder(javax.swing.BorderFactory.createTitledBorder("Hora Pico"));
        jPanelV.add(jLabelHoraPico);

        jPanelVentas.add(jPanelV, java.awt.BorderLayout.CENTER);

        jPanelEstadisticas.add(jPanelVentas);

        jPanelGastos.setLayout(new java.awt.BorderLayout());

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Gastos");
        jPanelGastos.add(jLabel11, java.awt.BorderLayout.PAGE_START);

        jPanelG.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanelG.setLayout(new java.awt.GridLayout(3, 0));

        jLabelInsumo.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabelInsumo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Strings"); // NOI18N
        jLabelInsumo.setText(bundle.getString("label_numeros_moneda")); // NOI18N
        jLabelInsumo.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 0), 3, true), "Insumo"));
        jPanelG.add(jLabelInsumo);

        jLabelTrabajadores.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabelTrabajadores.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTrabajadores.setText(bundle.getString("label_numeros_moneda")); // NOI18N
        jLabelTrabajadores.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 204), 3, true), "Trabajadores"));
        jPanelG.add(jLabelTrabajadores);

        jLabelOtros.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabelOtros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelOtros.setText(bundle.getString("label_numeros_moneda")); // NOI18N
        jLabelOtros.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 3, true), "Otros"));
        jPanelG.add(jLabelOtros);

        jPanelGastos.add(jPanelG, java.awt.BorderLayout.CENTER);

        jPanelEstadisticas.add(jPanelGastos);

        jPanel4.add(jPanelEstadisticas, java.awt.BorderLayout.EAST);

        jPanelControles.setPreferredSize(new java.awt.Dimension(328, 60));
        jPanelControles.setLayout(new java.awt.GridBagLayout());

        jButtonEliminar.setText(bundle.getString("label_eliminar")); // NOI18N
        jButtonEliminar.setPreferredSize(new java.awt.Dimension(120, 50));
        jPanelControles.add(jButtonEliminar, new java.awt.GridBagConstraints());

        jButtonEditar.setText(bundle.getString("label_editar")); // NOI18N
        jButtonEditar.setPreferredSize(new java.awt.Dimension(120, 50));
        jPanelControles.add(jButtonEditar, new java.awt.GridBagConstraints());

        jButtonY.setText("Y");
        jButtonY.setPreferredSize(new java.awt.Dimension(120, 50));
        jPanelControles.add(jButtonY, new java.awt.GridBagConstraints());

        jButtonImportarVentas.setText("Importar Ventas");
        jButtonImportarVentas.setPreferredSize(new java.awt.Dimension(160, 50));
        jPanelControles.add(jButtonImportarVentas, new java.awt.GridBagConstraints());

        jPanel4.add(jPanelControles, java.awt.BorderLayout.PAGE_END);

        add(jPanel4, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonImportarVentas;
    private javax.swing.JButton jButtonY;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelHoraPico;
    private javax.swing.JLabel jLabelInsumo;
    private javax.swing.JLabel jLabelOtros;
    private javax.swing.JLabel jLabelPromedioVendido;
    private javax.swing.JLabel jLabelTotalVendido;
    private javax.swing.JLabel jLabelTrabajadores;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelCalendario;
    private javax.swing.JPanel jPanelControles;
    private javax.swing.JPanel jPanelDetalles;
    private javax.swing.JPanel jPanelEstadisticas;
    private javax.swing.JPanel jPanelG;
    private javax.swing.JPanel jPanelGastos;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelSeleccion;
    private javax.swing.JPanel jPanelV;
    private javax.swing.JPanel jPanelVentas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableCalendar;
    private javax.swing.JTree jTree1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void wireUp() {
        Bindings.bind(jYearChooser1, "year", getPresenter().getModel(VentaCalendarViewModel.PROP_YEAR_SELECCIONADO));
        Bindings.bind(jMonthChooser1, "month", getPresenter().getModel(VentaCalendarViewModel.PROP_MES_SELECCIONADO));
        Bindings.bind(jLabelHoraPico, getPresenter().getModel(VentaCalendarViewModel.PROP_HORA_PICO_INTERVALOS));
        Bindings.bind(jLabelInsumo, getPresenter().getModel(VentaCalendarViewModel.PROP_GASTO_INSUMO_INTERVALO));
        Bindings.bind(jLabelOtros, getPresenter().getModel(VentaCalendarViewModel.PROP_GASTO_OTROS_INTERVALO));
        Bindings.bind(jLabelPromedioVendido, getPresenter().getModel(VentaCalendarViewModel.PROP_PROMEDIO_VENTAS_INTERVALO));
        Bindings.bind(jLabelTotalVendido, getPresenter().getModel(VentaCalendarViewModel.PROP_TOTAL_VENTAS_INTERVALO));
        Bindings.bind(jLabelTrabajadores, getPresenter().getModel(VentaCalendarViewModel.PROP_GASTO_TRABAJADORES_INTERVALO));
        Bindings.bind(fileChooser, "selectedFile", getPresenter().getModel(VentaCalendarViewModel.PROP_ARCHIVO_A_IMPORTAR));
        Bindings.bind(jButtonY, "visible", getPresenter().getModel(VentaCalendarViewModel.PROP_Y_VISIBLE));
        jButtonEditar.addActionListener(getPresenter().getOperation(VentaCalendarViewPresenter.ACTION_EDITAR));
        jButtonEliminar.addActionListener(getPresenter().getOperation(VentaCalendarViewPresenter.ACTION_ELIMINAR));
        jButtonImportarVentas.addActionListener((e) -> {
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                getPresenter().getOperation(VentaCalendarViewPresenter.ACTION_IMPORTAR_VENTA).doAction();
            }
        });
        Bindings.bind(jTableCalendar, new SelectionInList<List<Venta>>(
                getPresenter().getModel(VentaCalendarViewModel.PROP_LISTA_ELEMENTOS),
                getPresenter().getModel(VentaCalendarViewModel.PROP_ELEMENTO_SELECCIONADO)));
        jButtonY.addActionListener(getPresenter().getOperation(VentaCalendarViewPresenter.ACTION_Y));
        jTableCalendar.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            getPresenter().getModel(VentaCalendarViewModel.PROP_DIA_SELECCIONADO).setValue(getObjectAtSelectedCell());
        });
        jTableCalendar.getColumnModel().getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            getPresenter().getModel(VentaCalendarViewModel.PROP_DIA_SELECCIONADO).setValue(getObjectAtSelectedCell());
        });
        jTableCalendar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2 && evt.getButton() == 1) {
                    getPresenter().getOperation(VentaCalendarViewPresenter.ACTION_EDITAR).doAction();
                }
            }

        });
    }

    @Override

    public void uiInit() {
        initComponents();
        jMonthChooser1.addMouseWheelListener((MouseWheelEvent e) -> {
            int pos = e.getWheelRotation();
            if (pos < 0) {
                jMonthChooser1.setMonth(jMonthChooser1.getMonth() - 1);
            } else if (pos > 0) {
                jMonthChooser1.setMonth(jMonthChooser1.getMonth() + 1);
            }
        });
        jYearChooser1.addMouseWheelListener((MouseWheelEvent e) -> {
            int pos = e.getWheelRotation();
            if (pos < 0) {
                jYearChooser1.setYear(jYearChooser1.getYear() - 1);
            } else if (pos > 0) {
                jYearChooser1.setYear(jYearChooser1.getYear() + 1);
            }
        });

        jScrollPane1.getViewport().setOpaque(false);
        fileChooser = new JFileChooser();
        model = new BindableTableModel<DayReviewWrapper<ResumenVentaEstadisticas>>(jTableCalendar) {//findVentas(month, year)
            @Override
            public int getColumnCount() {
                return 7;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return List.class;
            }

            @Override
            public DayReviewWrapper<ResumenVentaEstadisticas> getValueAt(int rowIndex, int columnIndex) {
                int linearPos = (rowIndex * (getColumnCount() - 1) + columnIndex) + rowIndex + 1;
                int relativePos = linearPos - getPresenter().getModel(VentaCalendarViewModel.PROP_MONTH_OFFSET).intValue();

                if (relativePos > 0) {
                    return getObjectAt(relativePos);
                }
                return null;
            }

            @Override
            public DayReviewWrapper<ResumenVentaEstadisticas> getObjectAt(int dayOfMonth) {
                for (int i = 0; i < getListModel().getSize(); i++) {
                    if (super.getRow(i) != null) {
                        if (!super.getRow(i).getLista_contenida().isEmpty()) {
                            if (super.getRow(i).getFecha().getDayOfMonth()== dayOfMonth) {
                                return super.getRow(i);
                            }
                        }
                    }
                }
                return null;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Lunes";
                    case 1:
                        return "Martes";
                    case 2:
                        return "Miercoles";
                    case 3:
                        return "Jueves";
                    case 4:
                        return "Viernes";
                    case 5:
                        return "Sabado";
                    case 6:
                        return "Domingo";
                    default:
                        return null;
                }
            }

        };
        jTableCalendar.setModel(model);
        jTableCalendar.setDefaultRenderer(List.class, new VentaCellRender());
        jTableCalendar.setCellSelectionEnabled(true);
        jTableCalendar.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        jPanelResumen.setVisible(false);

    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

    private DayReviewWrapper<Venta> getObjectAtSelectedCell() {
        if (jTableCalendar.getSelectedRow() == -1 || jTableCalendar.getSelectedColumn() == -1) {
            return null;
        }
        return (DayReviewWrapper<Venta>) model.getValueAt(jTableCalendar.getSelectedRow(), jTableCalendar.getSelectedColumn());
    }

}

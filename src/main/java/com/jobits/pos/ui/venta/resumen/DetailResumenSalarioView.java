/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen;

import com.jobits.pos.core.domain.models.AsistenciaPersonal;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.ui.utils.BindableTableModel;
import com.jobits.pos.ui.venta.resumen.presenter.DetailResumenSalarioViewPresenter;
import com.jobits.pos.utils.utils;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Home
 */
public class DetailResumenSalarioView extends AbstractListResumenViewPanel<DayReviewWrapper, AsistenciaPersonal> {

    public static final String VIEW_NAME = "Resumen Salario View";

    /**
     * Creates new form DetailResumenAutorizoView
     *
     * @param presenter
     */
    public DetailResumenSalarioView(DetailResumenSalarioViewPresenter presenter) {
        super(presenter);
        jTableMain.getColumnModel().getColumn(1).setCellRenderer(utils.numberColumCellRender());
        jTableDetail.getColumnModel().getColumn(2).setCellRenderer(utils.numberColumCellRender());
    }

    @Override
    public BindableTableModel<DayReviewWrapper> generateMainTableModel() {
        return new BindableTableModel<DayReviewWrapper>(jTableMain) {
            @Override
            public int getColumnCount() {
                return 2;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Fecha";
                    case 1:
                        return "Total";
                }
                return null;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return getRow(rowIndex)
                                .getFecha().format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
                    case 1:
                        return utils.setDosLugaresDecimalesFloat(getRow(rowIndex).getTotal());
                }
                return null;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return LocalDate.class;
                    case 1:
                        return Float.class;
                    default:
                        return String.class;
                }
            }
        };
    }

    @Override
    public BindableTableModel<AsistenciaPersonal> generateDetailTableModel() {
        return new BindableTableModel<AsistenciaPersonal>(jTableDetail) {
            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Dia de Trabajo";
                    case 1:
                        return "Personal";
                    case 2:
                        return "Salario";
                }
                return null;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                AsistenciaPersonal p = getRow(rowIndex);
                switch (columnIndex) {
                    case 0:
                        Date d = p.getVenta().getFecha();
                        return Instant.ofEpochMilli(d.getTime()).atZone(ZoneId.systemDefault()).toLocalDate()
                                .format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
                    case 1:
                        return p.getPersonal().getUsuario();
                    case 2:
                        if (p.getPago() != null) {
                            return utils.setDosLugaresDecimalesFloat(p.getPago());
                        }
                }
                return null;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return LocalDate.class;
                    case 2:
                        return Float.class;
                    default:
                        return String.class;
                }
            }

        };
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

}

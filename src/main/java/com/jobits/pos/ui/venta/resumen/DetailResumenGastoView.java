/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen;

import com.jobits.pos.core.domain.models.GastoVenta;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.ui.AbstractListResumenViewPanel;
import com.jobits.pos.ui.filter.FilterMainView;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.BindableTableModel;
import com.jobits.pos.ui.venta.resumen.presenter.DetailResumenGastoViewPresenter;
import com.jobits.pos.utils.utils;
import java.awt.BorderLayout;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Home
 */
public class DetailResumenGastoView extends AbstractListResumenViewPanel<DayReviewWrapper, GastoVenta> {

    public static final String VIEW_NAME = "Resumen Autorizo View";

    /**
     * Creates new form DetailResumenAutorizoView
     *
     * @param presenter
     */
    public DetailResumenGastoView(DetailResumenGastoViewPresenter presenter) {
        super(presenter);
        jTableMain.getColumnModel().getColumn(1).setCellRenderer(utils.numberColumCellRender());
        jTableDetail.getColumnModel().getColumn(3).setCellRenderer(utils.numberColumCellRender());
        jPanelDetailPanel.add(new FilterMainView(getPresenter().getBean().getFilter_presenter()), BorderLayout.EAST);

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
    public BindableTableModel<GastoVenta> generateDetailTableModel() {
        return new BindableTableModel<GastoVenta>(jTableDetail) {
            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Fecha";
                    case 1:
                        return "Categoria";
                    case 2:
                        return "Tipo";
                    case 3:
                        return "Monto";
                }
                return null;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                GastoVenta g = getRow(rowIndex);
                switch (columnIndex) {
                    case 0:
                        Date d = g.getVenta().getFecha();
                        return Instant.ofEpochMilli(d.getTime()).atZone(ZoneId.systemDefault()).toLocalDate()
                                .format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
                    case 1:
                        return g.getGasto().getTipoGastoidGasto().getNombre();
                    case 2:
                        return g.getGasto().getNombre();
                    case 3:
                        if (g.getImporte() != null) {
                            return utils.setDosLugaresDecimalesFloat(g.getImporte());
                        }
                }
                return null;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return LocalDate.class;
                    case 3:
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

    @Override
    public DetailResumenGastoViewPresenter getPresenter() {
        return (DetailResumenGastoViewPresenter) super.getPresenter(); //To change body of generated methods, choose Tools | Templates.
    }

}

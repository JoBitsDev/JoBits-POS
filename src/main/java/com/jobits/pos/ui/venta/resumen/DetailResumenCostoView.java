/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen;

import com.jobits.pos.core.domain.models.ProductoInsumo;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.utils.BindableTableModel;
import com.jobits.pos.ui.venta.resumen.presenter.DetailResumenCostoViewPresenter;
import com.jobits.pos.utils.utils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Home
 */
public class DetailResumenCostoView extends AbstractListResumenViewPanel<DayReviewWrapper, ProductoInsumo> {

    public static final String VIEW_NAME = "Resumen Costo View";

    /**
     * Creates new form DetailResumenAutorizoView
     *
     * @param presenter
     */
    public DetailResumenCostoView(DetailResumenCostoViewPresenter presenter) {
        super(presenter);
        jTableMain.getColumnModel().getColumn(0).setPreferredWidth(200);
        jTableMain.getColumnModel().getColumn(1).setCellRenderer(utils.numberColumCellRender());
        jTableDetail.getColumnModel().getColumn(2).setCellRenderer(utils.numberColumCellRender());
        jTableDetail.getColumnModel().getColumn(3).setCellRenderer(utils.numberColumCellRender());
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
                        return utils.setDosLugaresDecimalesFloat(
                                getRow(rowIndex).getTotal());
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
    public BindableTableModel<ProductoInsumo> generateDetailTableModel() {
        return new BindableTableModel<ProductoInsumo>(jTableDetail) {
            @Override
            public int getColumnCount() {
                return 4;

            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Insumo";
                    case 1:
                        return "U/M";
                    case 2:
                        return "Cantidad";
                    case 3:
                        return "Costo (" + R.COIN_SUFFIX.substring(1) + ")";
                }
                return null;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                ProductoInsumo i = getRow(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return i.getInsumo().getNombre();
                    case 1:
                        return i.getInsumo().getUm();
                    case 2:
                        return utils.setDosLugaresDecimalesFloat(i.getCantidad());
                    case 3:
                        return utils.setDosLugaresDecimalesFloat(i.getCosto());
                }
                return null;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                    case 1:
                        return String.class;
                    default:
                        return Float.class;
                }
            }
        };
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

}

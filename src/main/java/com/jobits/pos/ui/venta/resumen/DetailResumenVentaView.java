/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.resumen;

import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.domain.models.temporal.DayReviewWrapper;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.AbstractListResumenViewPanel;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.BindableTableModel;
import com.jobits.pos.utils.utils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Home
 */
public class DetailResumenVentaView extends AbstractListResumenViewPanel<DayReviewWrapper, ProductovOrden> {

    public static final String VIEW_NAME = "Resumen Venta View";

    public DetailResumenVentaView(AbstractViewPresenter presenter) {
        super(presenter);
        jTableMain.getColumnModel().getColumn(1).setCellRenderer(utils.numberColumCellRender());
        jTableDetail.getColumnModel().getColumn(1).setCellRenderer(utils.numberColumCellRender());
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
    public BindableTableModel<ProductovOrden> generateDetailTableModel() {
        return new BindableTableModel<ProductovOrden>(jTableDetail) {
            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Producto Venta";
                    case 1:
                        return "Precio (" + R.COIN_SUFFIX.substring(1) + ")";
                    case 2:
                        return "Cantidad";
                    case 3:
                        return "Recaudado (" + R.COIN_SUFFIX.substring(1) + ")";
                }
                return null;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                ProductovOrden pv = getRow(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return pv.getProductoVenta().getNombre();
                    case 1:
                        return pv.getPrecioVendido();
                    case 2:
                        return utils.setDosLugaresDecimalesFloat(pv.getCantidad());
                    case 3:
                        return utils.setDosLugaresDecimalesFloat(pv.getPrecioVendido() * pv.getCantidad());
                }
                return null;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 1:
                    case 2:
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

}

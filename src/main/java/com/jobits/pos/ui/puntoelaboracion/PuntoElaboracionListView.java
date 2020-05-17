/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.puntoelaboracion;

import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.ui.AbstractListViewPanel;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.utils.BindableTableModel;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PuntoElaboracionListView extends AbstractListViewPanel<Cocina> {

    public static final String VIEW_NAME = "Puntos elaboración";

    public PuntoElaboracionListView(AbstractListViewPresenter presenter) {
        super(presenter);
    }

    @Override
    public BindableTableModel<Cocina> generateTableModel() {
        return new BindableTableModel<Cocina>(jTableList) {
            @Override
            public int getColumnCount() {
                return 5;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return getRow(rowIndex).getCodCocina();
                    case 1:
                        return getRow(rowIndex).getNombreCocina();
                    case 2:
                        return getRow(rowIndex).getProductoVentaList().size();
                    case 3:
                        return getRow(rowIndex).getRecibirNotificacion();
                    case 4:
                        return getRow(rowIndex).getLimitarVentaInsumoAgotado();
                    default:
                        return null;
                }
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 3 || columnIndex == 4;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3 || columnIndex == 4) {
                    return Boolean.class;
                } else {
                    return super.getColumnClass(columnIndex);

                }
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                if (columnIndex == 3) {
                    getRow(rowIndex).setRecibirNotificacion((Boolean) aValue);
                    //getController().setSelected(items.get(rowIndex));
                    //getController().update();//TODO: activar comportamiento
                }
                if (columnIndex == 4) {
                    getRow(rowIndex).setLimitarVentaInsumoAgotado((Boolean) aValue);
                    // getController().setSelected(items.get(rowIndex));
                    //getController().update();
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
                        return "Productos";
                    case 3:
                        return "Notificaciones";
                    case 4:
                        return "Restringir Ventas";
                    default:
                        return null;
                }
            }
        };

    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }
}

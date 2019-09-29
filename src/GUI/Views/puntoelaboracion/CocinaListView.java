/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.puntoelaboracion;

import GUI.Views.AbstractListView;
import java.awt.Dialog;
import java.awt.Frame;
import java.util.List;
import restManager.controller.AbstractListController;
import restManager.persistencia.Cocina;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class CocinaListView extends AbstractListView<Cocina> {

    public CocinaListView(AbstractListController<Cocina> controller, Frame owner, boolean modal) {
        super(controller, owner, modal);
    }

    public CocinaListView(AbstractListController<Cocina> controller, Dialog owner, boolean modal) {
        super(controller, owner, modal);
    }

    @Override
    public MyJTableModel<Cocina> generateTableModel(List<Cocina> items) {
        return new MyJTableModel<Cocina>(items) {
            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return items.get(rowIndex).getCodCocina();
                    case 1:
                        return items.get(rowIndex).getNombreCocina();
                    case 2:
                        return items.get(rowIndex).getProductoVentaList().size();
                    case 3:
                        return items.get(rowIndex).getRecibirNotificacion();
                    default:
                        return null;
                }
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 3;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) {
                    return Boolean.class;
                } else {
                    return super.getColumnClass(columnIndex);

                }
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                if (columnIndex == 3) {
                    items.get(rowIndex).setRecibirNotificacion((Boolean) aValue);
                    getController().setSelected(items.get(rowIndex));
                    getController().update();
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
                        return "Cantidad de productos";
                    case 3:
                        return "Recibir Notificaciones";
                    default:
                        return null;
                }
            }
        };

    }
}

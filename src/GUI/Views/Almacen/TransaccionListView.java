/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.Almacen;

import GUI.Views.AbstractListView;
import GUI.Views.AbstractView;
import java.awt.Frame;
import java.util.List;
import restManager.controller.AbstractListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Transaccion;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class TransaccionListView extends AbstractListView<Transaccion> {

    public TransaccionListView(AbstractListController<Transaccion> controller, AbstractView parent, boolean modal) {
        super(controller, parent, modal);
    }

    @Override
    public MyJTableModel<Transaccion> generateTableModel(List<Transaccion> items) {
        return new MyJTableModel<Transaccion>(items) {
            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return items.get(rowIndex).getInsumo();
                    case 1:
                        return R.DATE_FORMAT.format(items.get(rowIndex).getTransaccionPK().getFecha());
                    case 2:
                        return R.TIME_FORMAT.format(items.get(rowIndex).getTransaccionPK().getHora());
                    case 3:
                        return items.get(rowIndex).getCantidad();
                    default:
                        return null;
                }
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Insumo";
                    case 1:
                        return "Fecha";
                    case 2:
                        return "Hora";
                    case 3:
                        return "Cantidad";
                    default:
                        return null;
                }
            }
        };
    }

    @Override
    public void updateView() {
        super.updateView(); //To change body of generated methods, choose Tools | Templates.
        getjTableList().getRowSorter().toggleSortOrder(1);
    }

}

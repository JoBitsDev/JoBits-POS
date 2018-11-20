/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.cocina;

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
                return 3;
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
                    default:
                        return null;
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
                        return "Cantidad de platos";
                    default:
                        return null;
                }
            }
        };

    }
}

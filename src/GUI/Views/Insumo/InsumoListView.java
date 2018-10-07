/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.Insumo;

import GUI.AbstractListView;
import java.awt.Dialog;
import java.awt.Frame;
import java.util.List;
import restManager.controller.AbstractController;
import restManager.persistencia.Insumo;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class InsumoListView extends AbstractListView<Insumo> {

    public InsumoListView(AbstractController controller, Frame owner, boolean modal) {
        super(controller, owner, modal);
    }

    public InsumoListView(AbstractController controller, Dialog owner, boolean modal) {
        super(controller, owner, modal);
    }

    @Override
    public MyJTableModel<Insumo> generateTableModel(List<Insumo> items) {
        return new MyJTableModel<Insumo>(items) {
            @Override
            public int getColumnCount() {
                return 5;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return items.get(rowIndex).getCodInsumo();
                    case 1:
                        return items.get(rowIndex).getNombre();
                    case 2:
                        return items.get(rowIndex).getUm();
                    case 3:
                        return items.get(rowIndex).getCantidadExistente();
                    case 4:
                        return items.get(rowIndex).getElaborado();
                    default:
                        return null;

                }
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Codigo";
                    case 1:
                        return "Nombre";
                    case 2:
                        return "UM";
                    case 3:
                        return "En Almacen";
                    case 4:
                        return "Elaborado";
                    default:
                        return null;

                }
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 4:
                        return Boolean.class;
                    default:
                        return super.getColumnClass(columnIndex);

                }
            }
        };
    }

}

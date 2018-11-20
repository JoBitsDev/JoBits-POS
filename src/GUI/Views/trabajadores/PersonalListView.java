/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.trabajadores;

import GUI.Views.AbstractListView;
import java.awt.Dialog;
import java.awt.Frame;
import java.util.List;
import restManager.controller.AbstractController;
import restManager.controller.AbstractListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Personal;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PersonalListView extends AbstractListView<Personal> {

    public PersonalListView(AbstractListController<Personal> controller, Frame owner, boolean modal) {
        super(controller, owner, modal);
    }

    public PersonalListView(AbstractListController<Personal> controller, Dialog owner, boolean modal) {
        super(controller, owner, modal);
    }

    @Override
    public MyJTableModel<Personal> generateTableModel(List<Personal> items) {
        return new MyJTableModel<Personal>(items) {
            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return items.get(rowIndex).getUsuario();
                    case 1:
                        return items.get(rowIndex).getDatosPersonales().getNombre();
                    case 2:
                        if (items.get(rowIndex).getPuestoTrabajoList() == null
                                || items.get(rowIndex).getPuestoTrabajoList().isEmpty()) {
                            return null;
                        }
                        return items.get(rowIndex).getPuestoTrabajoList().get(0);
                    default:
                        return null;
                }
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Usuario";
                    case 1:
                        return "Nombre";
                    case 2:
                        return "Puesto de Trabajo";
                    default:
                        return null;
                }
            }
        };
    }

}

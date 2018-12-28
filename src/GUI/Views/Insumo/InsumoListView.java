/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.Insumo;

import GUI.Views.AbstractListView;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JMenuItem;
import restManager.controller.AbstractDialogController;
import restManager.controller.AbstractListController;
import restManager.controller.insumo.InsumoListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Insumo;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class InsumoListView extends AbstractListView<Insumo> {

    public InsumoListView(AbstractListController<Insumo> controller, Frame owner, boolean modal) {
        super(controller, owner, modal);
    }

    public InsumoListView(AbstractListController<Insumo> controller, Dialog owner, boolean modal) {
        super(controller, owner, modal);
    }

    @Override
    protected void createPopUpMenu() {
        JMenuItem verUsos = new JMenuItem("Ver Usos En Productos");
        verUsos.addActionListener((ActionEvent e) -> {
           ((InsumoListController)getController()).crossReferenceInsumo(model.getObjectAtSelectedRow());
        });
        jPopupMenuClickDerecho.add(verUsos);
    }

    @Override
    public MyJTableModel<Insumo> generateTableModel(List<Insumo> items) {
        return new MyJTableModel<Insumo>(items) {
            @Override
            public int getColumnCount() {
                return 4;
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
                        return "Elaborado";
                    default:
                        return null;

                }
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 3:
                        return Boolean.class;
                    default:
                        return super.getColumnClass(columnIndex);

                }
            }
        };
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.insumo;

import com.jobits.pos.ui.OldAbstractListView;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JMenuItem;
import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.controller.AbstractListController;
import com.jobits.pos.controller.insumo.InsumoListController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.domain.models.Insumo;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class InsumoListView extends OldAbstractListView<Insumo> {

    public InsumoListView(AbstractListController<Insumo> controller, Frame owner, boolean modal) {
        super(controller, owner, modal);
    }

    public InsumoListView(AbstractListController<Insumo> controller, Dialog owner, boolean modal) {
        super(controller, owner, modal);
    }

    @Override
    public MyJTableModel<Insumo> generateTableModel(List<Insumo> items) {
        return new MyJTableModel<Insumo>(items) {
            @Override
            public int getColumnCount() {
                return 3;
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
                    default:
                        return null;

                }
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    default:
                        return super.getColumnClass(columnIndex);

                }
            }
        };
    }

}

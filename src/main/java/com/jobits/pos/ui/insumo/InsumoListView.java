/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.insumo;

import com.jobits.pos.controller.login.MainMenuController;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.ui.AbstractListViewPanel;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.utils.BindableTableModel;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class InsumoListView extends AbstractListViewPanel<Insumo> {

    public static final String VIEW_NAME = "Insumos";

    public InsumoListView(AbstractListViewPresenter presenter) {
        super(presenter);
    }

    @Override
    public BindableTableModel<Insumo> generateTableModel() {
        return new BindableTableModel<Insumo>(jTableList) {
            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return ((Insumo) getListModel().getElementAt(rowIndex)).getCodInsumo();
                    case 1:
                        return ((Insumo) getListModel().getElementAt(rowIndex)).getNombre();
                    case 2:
                        return ((Insumo) getListModel().getElementAt(rowIndex)).getUm();
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

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

}

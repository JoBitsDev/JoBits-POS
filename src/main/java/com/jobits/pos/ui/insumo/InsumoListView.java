/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.insumo;

import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.ui.AbstractListViewPanel;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.swing.utils.BindableTableModel;

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
                return 5;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return getRow(rowIndex).getCodInsumo();
                    case 1:
                        return getRow(rowIndex).getNombre();
                    case 2:
                        return getRow(rowIndex).getUm();
                    case 3:
                        return getRow(rowIndex).getCostoPorUnidad();
                    case 4:
                        return getRow(rowIndex).getIdentificador();
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
                        return "Costo por unidad";
                    case 4:
                        return "Identificador";
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

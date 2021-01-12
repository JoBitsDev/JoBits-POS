/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores;

import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.ui.AbstractListViewPanel;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.utils.BindableTableModel;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PersonalListView extends AbstractListViewPanel<Personal> {

    public static final String VIEW_NAME = "Trabajadores";

    public PersonalListView(AbstractListViewPresenter presenter) {
        super(presenter);
    }

    @Override
    public BindableTableModel<Personal> generateTableModel() {
        return new BindableTableModel<Personal>(jTableList) {
            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return getRow(rowIndex).getUsuario();
                    case 1:
                        return getRow(rowIndex).getDatosPersonales().getNombre();
                    case 2:
                        return getRow(rowIndex).getPuestoTrabajonombrePuesto();
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

    @Override

    public String getViewName() {
        return VIEW_NAME;
    }

}

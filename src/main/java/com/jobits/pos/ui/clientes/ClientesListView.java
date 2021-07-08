/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes;

import com.jobits.pos.cliente.core.domain.ClienteDomain;
import com.jobits.pos.ui.trabajadores.*;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.ui.AbstractListViewPanel;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.swing.utils.BindableTableModel;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ClientesListView extends AbstractListViewPanel<ClienteDomain> {

    public static final String VIEW_NAME = "Clientes";

    public ClientesListView(AbstractListViewPresenter presenter) {
        super(presenter);
    }

    @Override
    public BindableTableModel<ClienteDomain> generateTableModel() {
        return new BindableTableModel<ClienteDomain>(jTableList) {
            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return getRow(rowIndex).getNombre() + " " + getRow(rowIndex).getApellidos();
                    case 1:
                        return getRow(rowIndex).getTelefono();
                    case 2:
                        return getRow(rowIndex).getDireccionEnvioList().size();
                    default:
                        return null;
                }
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Cliente";
                    case 1:
                        return java.util.ResourceBundle.getBundle("Strings").getString("label_telefono");
                    case 2:
                        return "Cantidad de direcciones";
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

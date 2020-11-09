/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes;

import com.jobits.pos.domain.models.Cliente;
import com.jobits.pos.ui.trabajadores.*;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.ui.AbstractListViewPanel;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.utils.BindableTableModel;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ClientesListView extends AbstractListViewPanel<Cliente> {

    public static final String VIEW_NAME = "Clientes";

    public ClientesListView(AbstractListViewPresenter presenter) {
        super(presenter);
    }

    @Override
    public BindableTableModel<Cliente> generateTableModel() {
        return new BindableTableModel<Cliente>(jTableList) {
            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return getRow(rowIndex).getNombreCliente();
                    case 1:
                        return getRow(rowIndex).getTelefonoCliente();
                    case 2:
                        return getRow(rowIndex).getOrdenList().size();
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
                        return "Cantidad de Ordenes";
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

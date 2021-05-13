/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.clientes.reserva;

import com.jobits.pos.reserva.core.domain.Cliente;
import com.jobits.pos.ui.AbstractListViewPanel;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.swing.utils.BindableTableModel;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ClientesReservaListView extends AbstractListViewPanel<Cliente> {

    public static final String VIEW_NAME = "Clientes Reserva";

    public ClientesReservaListView(AbstractListViewPresenter presenter) {
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
                Cliente c = getRow(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return c.getNombrecliente() + " " + c.getApellidocliente();
                    case 1:
                        return c.getTelefonocliente();
                    case 2:
                        return c.getReservaCollection().size();
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
                        return "Cantidad de Reservas";
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

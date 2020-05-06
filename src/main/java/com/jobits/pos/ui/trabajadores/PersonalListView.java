/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores;

import com.jobits.pos.ui.AbstractListView;
import com.jidesoft.swing.JideButton;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.controller.AbstractListController;
import com.jobits.pos.controller.trabajadores.PersonalListController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.persistencia.Personal;
import com.jobits.pos.recursos.valores.Fonts;
import com.jobits.pos.ui.utils.utils;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PersonalListView extends AbstractListView<Personal> {

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
                        return items.get(rowIndex).getPuestoTrabajonombrePuesto();
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
    public PersonalListController getController() {
        return (PersonalListController) super.getController(); //To change body of generated methods, choose Tools | Templates.
    }

}

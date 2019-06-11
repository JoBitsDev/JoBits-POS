/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.trabajadores;

import GUI.Views.AbstractListView;
import com.jidesoft.swing.JideButton;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import restManager.controller.AbstractDialogController;
import restManager.controller.AbstractListController;
import restManager.controller.trabajadores.PersonalListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Personal;
import restManager.resources.values.Fonts;
import restManager.util.comun;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PersonalListView extends AbstractListView<Personal> {

    public PersonalListView(AbstractListController<Personal> controller, Dialog owner, boolean modal) {
        super(controller, owner, modal);
        JButton jideButton1 = new JButton();
        jideButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/banknote.png"))); // NOI18N
        jideButton1.setToolTipText("Pagar");
        jideButton1.setFont(Fonts.BODY);
        jideButton1.setText("Pagar");
        jideButton1.setMinimumSize(new java.awt.Dimension(100, 40));
        jideButton1.addActionListener((ActionEvent e) -> {
            getController().pagar(model.getObjectAtSelectedRow());
        });
        getjXPanelControles().add(jideButton1);
    }

    @Override
    public MyJTableModel<Personal> generateTableModel(List<Personal> items) {
        return new MyJTableModel<Personal>(items) {
            @Override
            public int getColumnCount() {
                return 4;
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
                    case 3:
                        return comun.setDosLugaresDecimales(items.get(rowIndex).getPagoPendiente() != null ? items.get(rowIndex).getPagoPendiente() : 0);
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
                    case 3:
                        return "Por Pagar";
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

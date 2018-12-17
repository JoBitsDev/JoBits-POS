/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.venta;

import GUI.Views.AbstractListView;
import java.awt.Frame;
import java.util.List;
import javax.swing.JButton;
import restManager.controller.AbstractListController;
import restManager.persistencia.Venta;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class VentaSinArchivarListView extends AbstractListView<Venta> {

    JButton jButtonArchivarDias,jButtonMostrarResumenVentas,jButtonEliminar,jButtonSalir;
    
    public VentaSinArchivarListView(AbstractListController<Venta> controller, Frame parent, boolean modal) {
        super(controller, parent, modal);
        getjXPanelControles().removeAll();
        
    }

    @Override
    public MyJTableModel<Venta> generateTableModel(List<Venta> items) {
        return new MyJTableModel<Venta>(items) {
            @Override
            public int getColumnCount() {
                return 2;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                if (columnIndex == 0) {
                    return items.get(rowIndex).getFecha();
                } else {
                    return items.get(rowIndex).getVentaTotal();
                }
            }

            @Override
            public String getColumnName(int column) {
                if (column == 0) {
                    return "Día(Fecha)";
                } else {
                    return "Monto Recaudado";
                }
            }
        };
    }

}

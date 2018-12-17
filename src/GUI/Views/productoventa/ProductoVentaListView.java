/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.productoventa;

import GUI.Views.AbstractListView;
import com.jidesoft.swing.JideButton;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import restManager.controller.AbstractListController;
import restManager.controller.productoventa.ProductoVentaListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.ProductoVenta;
import restManager.printservice.ComponentPrinter;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ProductoVentaListView extends AbstractListView<ProductoVenta> {

    public ProductoVentaListView(AbstractListController<ProductoVenta> controller, Frame owner, boolean modal) {
        super(controller, owner, modal);

        JideButton jideButton1 = new com.jidesoft.swing.JideButton();
        jideButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/imp.png"))); // NOI18N
        jideButton1.setToolTipText("Imprimir");
        jideButton1.addActionListener((ActionEvent e) -> {
            getController().printProductoVenta(model.getObjectAtSelectedRow());
        });
        super.getjXPanelControles().add(jideButton1);

    }

    @Override
    public MyJTableModel<ProductoVenta> generateTableModel(List<ProductoVenta> items) {
        return new MyJTableModel<ProductoVenta>(items) {
            @Override
            public int getColumnCount() {
                return 6;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return items.get(rowIndex).getPCod();
                    case 1:
                        return items.get(rowIndex).getNombre();
                    case 2:
                        return items.get(rowIndex).getPrecioVenta() + R.coinSuffix;
                    case 3:
                        return items.get(rowIndex).getSeccionnombreSeccion().getNombreSeccion();
                    case 4:
                        return items.get(rowIndex).getCocinacodCocina().getNombreCocina();
                    case 5:
                        return items.get(rowIndex).getVisible();
                    default:
                        return null;
                }
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Código";
                    case 1:
                        return "Nombre";
                    case 2:
                        return "Precio";
                    case 3:
                        return "Seccion";
                    case 4:
                        return "Cocina";
                    case 5:
                        return "Visible";
                    default:
                        return null;
                }
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 5;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 5) {
                    return Boolean.class;
                } else {
                    return super.getColumnClass(columnIndex);

                }
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                if (columnIndex == 5) {
                    items.get(rowIndex).setVisible((Boolean) aValue);
                    getController().setSelected(items.get(rowIndex));
                    getController().update();
                }
            }
        };
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(700, 600);
    }

    @Override
    public ProductoVentaListController getController() {
        return (ProductoVentaListController) super.getController(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}

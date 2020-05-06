/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.productos;

import com.jobits.pos.ui.AbstractListView;
import com.jidesoft.swing.JideButton;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import com.jobits.pos.controller.AbstractListController;
import com.jobits.pos.controller.productos.ProductoVentaListController;
import com.jobits.pos.domain.models.Carta;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.recursos.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ProductoVentaListView extends AbstractListView<ProductoVenta> {

    public ProductoVentaListView(AbstractListController<ProductoVenta> controller, Dialog owner, boolean modal) {
        super(controller, owner, modal);

//        JideButton jideButton1 = new com.jidesoft.swing.JideButton();
//        jideButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
//        jideButton1.setToolTipText("Imprimir");
//        jideButton1.addActionListener((ActionEvent e) -> {
//            getController().printProductoVenta(model.getObjectAtSelectedRow());
//        });
        JComboBox<Carta> cartas = new JComboBox<>(getController().getCartaList());
        cartas.addItem(null);
        cartas.addItemListener((ItemEvent e) -> {
            getController().setSelectedCarta((Carta) cartas.getSelectedItem());
        });
        JLabel label = new JLabel("Menu: ");

//        super.getjXPanelControles().add(jideButton1);
        super.getjPanelExtra().add(label);
        super.getjPanelExtra().add(cartas);
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
                        return items.get(rowIndex).getCodigoProducto();
                    case 1:
                        return items.get(rowIndex).getNombre();
                    case 2:
                        return items.get(rowIndex).getPrecioVenta() + R.COIN_SUFFIX;
                    case 3:
                        return items.get(rowIndex).getSeccionnombreSeccion();
                    case 4:
                        return items.get(rowIndex).getCocinacodCocina();
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
                if (columnIndex == 5 && getController().canSetVisible(items.get(rowIndex))) {
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

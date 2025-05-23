/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.productos;

import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.AbstractListViewPanel;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.productos.presenter.ProductoVentaListViewPresenter;
import com.jobits.pos.ui.swing.utils.BindableTableModel;
import com.jobits.pos.utils.utils;
import java.util.Comparator;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ProductoVentaListView extends AbstractListViewPanel<ProductoVenta> {

    public static final String VIEW_NAME = "Productos";

    public ProductoVentaListView(AbstractListViewPresenter presenter) {
        super(presenter);
    }

    @Override
    public void uiInit() {
        super.uiInit(); //To change body of generated methods, choose Tools | Templates.
//        JComboBox<Carta> cartas = MaterialComponentsFactory.Displayers.getComboBox();
//        cartas.addItem(null);
//        cartas.addItemListener((ItemEvent e) -> {
//            getController().setSelectedCarta((Carta) cartas.getSelectedItem());
//        });
//        JLabel label = new JLabel("Menu: ");
//
//        super.getjPanelExtra().add(label);
//        super.getjPanelExtra().add(cartas);
//TODO implementar el comboBox de los menu
        jTableList.getColumnModel().getColumn(2).setCellRenderer(utils.numberColumCellRender());
        jTableList.getColumnModel().getColumn(3).setCellRenderer(utils.numberColumCellRender());
    }

    @Override
    public BindableTableModel<ProductoVenta> generateTableModel() {
        return new BindableTableModel<ProductoVenta>(jTableList) {
            @Override
            public int getColumnCount() {
                return 7;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return ((ProductoVenta) getListModel().getElementAt(rowIndex)).getCodigoProducto();
                    case 1:
                        return ((ProductoVenta) getListModel().getElementAt(rowIndex)).getNombre();
                    case 2:
                        return ((ProductoVenta) getListModel().getElementAt(rowIndex)).getGasto();
                    case 3:
                        return ((ProductoVenta) getListModel().getElementAt(rowIndex)).getPrecioVenta();
                    case 4:
                        return ((ProductoVenta) getListModel().getElementAt(rowIndex)).getSeccionnombreSeccion();
                    case 5:
                        return ((ProductoVenta) getListModel().getElementAt(rowIndex)).getCocinacodCocina();
                    case 6:
                        return ((ProductoVenta) getListModel().getElementAt(rowIndex)).getVisible();
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
                        return "Precio Costo (" + R.COIN_SUFFIX + ")";
                    case 3:
                        return "Precio Venta (" + R.COIN_SUFFIX + ")";
                    case 4:
                        return "Seccion";
                    case 5:
                        return "Cocina";
                    case 6:
                        return "Visible";
                    default:
                        return null;
                }
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 6;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 2,3:
                        return Float.class;
                    case 6:
                        return Boolean.class;
                    default:
                        return super.getColumnClass(columnIndex);
                }
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                if (columnIndex == 6) {
                    getPresenter().getOperation(ProductoVentaListViewPresenter.ACTION_CHANGE_VISIBLE).doAction();
                }
            }
        };
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

}

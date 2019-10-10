/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views.Almacen;

import GUI.Views.AbstractListView;
import GUI.Views.AbstractView;
import com.jidesoft.swing.JideButton;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.List;
import restManager.controller.AbstractListController;
import restManager.controller.almacen.TransaccionesListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Transaccion;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class TransaccionListView extends AbstractListView<Transaccion> {

    public TransaccionListView(AbstractListController<Transaccion> controller, AbstractView parent, boolean modal) {
        super(controller, parent, modal);
        JideButton jideButton1 = new com.jidesoft.swing.JideButton();
        jideButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/impresora.png"))); // NOI18N
        jideButton1.setToolTipText("Imprimir Recibo");
        jideButton1.addActionListener((ActionEvent e) -> {
            getController().imprimirTransaccionesSeleccionadas(model.getSelectedsObjects());
        });
        getjXPanelControles().add(jideButton1);
    }

    @Override
    public MyJTableModel<Transaccion> generateTableModel(List<Transaccion> items) {
        return new MyJTableModel<Transaccion>(items) {
            @Override
            public int getColumnCount() {
                return 5;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return items.get(rowIndex).getInsumocodInsumo();
                    case 1:
                        return R.DATE_FORMAT.format(items.get(rowIndex).getFecha());
                    case 2:
                        return R.TIME_FORMAT.format(items.get(rowIndex).getHora());
                    case 3:
                        return items.get(rowIndex).getCantidad();
                    case 4:
                        Transaccion t = items.get(rowIndex);
                        if (t.getTransaccionEntrada() != null) {
                            return "ENTRADA (Total: " + t.getTransaccionEntrada().getValorTotal() + R.COIN_SUFFIX + ")";
                        }
                        if (t.getTransaccionMerma() != null) {
                            return t.getTransaccionMerma().getRazon().toUpperCase();
                        }
                        if (t.getTransaccionSalida() != null) {
                            return "SALIDA: " + t.getTransaccionSalida().getCocinacodCocina();
                        }
                        if (t.getTransaccionTraspaso() != null) {
                            return "TRASPASO: ";
                        }
                        if (t.getTransaccionTransformacionList() != null) {
                           if(!t.getTransaccionTransformacionList().isEmpty()){
                            return "TRANSFORMACION: ";
                           }
                        }
                        
                    default:
                        return null;
                }
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Insumo";
                    case 1:
                        return "Fecha";
                    case 2:
                        return "Hora";
                    case 3:
                        return "Cantidad";
                    case 4:
                        return "Tipo";
                    default:
                        return null;
                }
            }
        };
    }

    @Override

    public void updateView() {
        super.updateView(); //To change body of generated methods, choose Tools | Templates.
        getjTableList().getRowSorter().toggleSortOrder(1);
    }

    @Override
    public TransaccionesListController getController() {
        return (TransaccionesListController) super.getController(); //To change body of generated methods, choose Tools | Templates.
    }

}

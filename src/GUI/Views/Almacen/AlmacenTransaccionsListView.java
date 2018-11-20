/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI.Views.Almacen;

import GUI.Views.AbstractListView;
import java.awt.Dialog;
import java.awt.Frame;
import java.text.SimpleDateFormat;
import java.util.List;
import restManager.controller.AbstractController;
import restManager.controller.AbstractListController;
import restManager.persistencia.Transaccion;
import restManager.resources.R;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class AlmacenTransaccionsListView extends AbstractListView<Transaccion>{
    
    public AlmacenTransaccionsListView(AbstractListController<Transaccion> controller, Frame owner, boolean modal) {
        super(controller, owner, modal);
    }

    public AlmacenTransaccionsListView(AbstractListController<Transaccion> controller, Dialog owner, boolean modal) {
        super(controller, owner, modal);
    }

    @Override
    public MyJTableModel<Transaccion> generateTableModel(List<Transaccion> items) {
    return new MyJTableModel<Transaccion>(items) {
        @Override
        public int getColumnCount() {
            return 3;
        }
        
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch(columnIndex){
                case 0: return R.DATE_FORMAT.format(items.get(rowIndex).getFechaTransaccion());
                case 1: return items.get(rowIndex).getValorTotalTransacciones();
                case 2: return items.get(rowIndex).getValorMerma();
                default: return null;
            }

        }
        
        @Override
        public String getColumnName(int column) {
            switch(column){
                case 0: return "Fecha";
                case 1: return "Valor Total Trans.";
                case 2: return "Valor Merma";
                default: return null;
            }

        }
    };
    }

}

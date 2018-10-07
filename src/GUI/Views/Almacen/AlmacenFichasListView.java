/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI.Views.Almacen;

import GUI.AbstractListView;
import java.awt.Dialog;
import java.awt.Frame;
import java.util.List;
import restManager.controller.AbstractController;
import restManager.persistencia.Ficha;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class AlmacenFichasListView extends AbstractListView<Ficha>{

    public AlmacenFichasListView(AbstractController controller, Frame owner, boolean modal) {
        super(controller, owner, modal);
    }

    public AlmacenFichasListView(AbstractController controller, Dialog owner, boolean modal) {
        super(controller, owner, modal);
    }

    @Override
    public MyJTableModel<Ficha> generateTableModel(List<Ficha> items) {
    return new MyJTableModel<Ficha>(items) {
        @Override
        public int getColumnCount() {
            return 3;
        }
        
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch(columnIndex){
                case 0: return items.get(rowIndex).getFechaCreacion();
                case 1: return items.get(rowIndex).getTipo();
                case 2: return items.get(rowIndex).getValorMonetario();
                default: return null;
            }

        }
        
        @Override
        public String getColumnName(int column) {
            switch(column){
                case 0: return "Fecha";
                case 1: return "Tipo";
                case 2: return "Valor Monetario";
                default: return null;
            }

        }
    };
    }

}

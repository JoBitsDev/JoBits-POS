/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.ui.almacen;

import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.ui.AbstractListViewPanel;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.utils.BindableTableModel;

/**
 * FirstDream
 * @author Jorge
 *
 */
public class AlmacenListView extends AbstractListViewPanel<Almacen>{

    
    public static final String VIEW_NAME = "Almacenes";
    
    public AlmacenListView(AbstractListViewPresenter presenter) {
        super(presenter);
    }

    @Override
    public BindableTableModel<Almacen> generateTableModel() {
        return new BindableTableModel<Almacen>(jTableList) {
            @Override
            public int getColumnCount() {
                return 2;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch(columnIndex){
                    case 0: return getRow(rowIndex);
                    case 1: return getRow(rowIndex).getValorMonetario();
                    default: return null;
                }
            }
            
            @Override
            public String getColumnName(int column) {
                switch(column){
                    case 0: return "Nombre";
                    case 1: return "Valor Monetario";
                    default: return null;
                }
            }
        };
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

}

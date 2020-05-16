/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.ui.trabajadores;

import com.jobits.pos.domain.models.PuestoTrabajo;
import com.jobits.pos.ui.AbstractListViewPanel;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.utils.BindableTableModel;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class PuestoTrabajoListView extends AbstractListViewPanel<PuestoTrabajo>{

    public static final String VIEW_NAME = "Puestos de trabajos";
    
    public PuestoTrabajoListView(AbstractListViewPresenter presenter) {
        super(presenter);
    }

    @Override
    public BindableTableModel<PuestoTrabajo> generateTableModel() {
      model = new BindableTableModel<PuestoTrabajo>() {
           @Override
           public int getColumnCount() {
               return 3;
           }
           
           @Override
           public Object getValueAt(int rowIndex, int columnIndex) {
               switch(columnIndex){
                   case 0: return getRow(rowIndex).getNombrePuesto();
                   case 1: return getRow(rowIndex).getNivelAcceso();
                   case 2: return getRow(rowIndex).getPuestosDisponibles();
                   default:return null;
               }
           }
           
           @Override
           public String getColumnName(int column) {
               switch(column){
                   case 0: return "Nombre Puesto";
                   case 1: return "Nivel de Acceso";
                   case 2: return "Puestos Disponibles";
                   default:return null;
               }
           }
       };
       return model;
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }
    
    

}

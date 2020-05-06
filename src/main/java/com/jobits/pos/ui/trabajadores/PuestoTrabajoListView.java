/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.ui.trabajadores;

import com.jobits.pos.ui.AbstractListView;
import java.awt.Dialog;
import java.awt.Frame;
import java.util.List;
import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.controller.AbstractListController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.persistencia.PuestoTrabajo;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class PuestoTrabajoListView extends AbstractListView<PuestoTrabajo>{

    public PuestoTrabajoListView(AbstractListController<PuestoTrabajo> controller, Frame owner, boolean modal) {
        super(controller, owner, modal);
    }

    public PuestoTrabajoListView(AbstractListController<PuestoTrabajo> controller, Dialog owner, boolean modal) {
        super(controller, owner, modal);
    }
    

    @Override
    public MyJTableModel<PuestoTrabajo> generateTableModel(List<PuestoTrabajo> items) {
      model = new MyJTableModel<PuestoTrabajo>(items) {
           @Override
           public int getColumnCount() {
               return 3;
           }
           
           @Override
           public Object getValueAt(int rowIndex, int columnIndex) {
               switch(columnIndex){
                   case 0: return items.get(rowIndex).getNombrePuesto();
                   case 1: return items.get(rowIndex).getNivelAcceso();
                   case 2: return items.get(rowIndex).getPuestosDisponibles();
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
    
    

}

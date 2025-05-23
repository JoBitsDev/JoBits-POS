/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores;

import com.jobits.pos.core.domain.models.AsistenciaPersonal;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.ui.AbstractViewPanel;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.swing.utils.BindableTableModel;
import com.jobits.pos.ui.utils.AddFromPanel;
import com.jobits.pos.utils.utils;
import com.jobits.ui.components.MaterialComponentsFactory;
import com.root101.clean.core.app.services.UserResolver;
import com.root101.swing.material.standards.MaterialIcons;

import java.awt.*;

import static com.jobits.pos.ui.trabajadores.presenter.AsistenciaPersonalPresenter.*;
import static com.jobits.pos.ui.trabajadores.presenter.AsistenciaPersonalViewModel.*;

/**
 * @author Home
 */
public class AsistenciaPersonalView extends AbstractViewPanel {

    public static final String VIEW_NAME = "Asistencia Personal";
    private AddFromPanel<AsistenciaPersonal, Personal> tablePagoTrabajadores;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonAMayores;

    /**
     * Creates new form AsistenciaPersonalView
     *
     * @param presenter
     */
    public AsistenciaPersonalView(AbstractViewPresenter presenter) {
        super(presenter);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());

        jButtonImprimir = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jButtonImprimir.setIcon(MaterialIcons.PRINT);
        jButtonImprimir.setText("Asistencia");
        jButtonImprimir.setPreferredSize(new java.awt.Dimension(130, 50));

        jButtonAMayores = MaterialComponentsFactory.Buttons.getOutlinedButton();
        jButtonAMayores.setIcon(MaterialIcons.MONETIZATION_ON);
        jButtonAMayores.setText("A Mayores");
        jButtonAMayores.setPreferredSize(new java.awt.Dimension(130, 50));

    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void wireUp() {
        jButtonImprimir.addActionListener(getPresenter().getOperation(ACTION_IMPRIMIR_ASISTENCIA));
        jButtonAMayores.addActionListener(getPresenter().getOperation(ACTION_A_MAYORES));

    }

    @Override
    public void uiInit() {
        initComponents();
        AddFromPanel.AddFromPanelBuilder<AsistenciaPersonal, Personal> builder = AddFromPanel.builder();

        builder.addAction(getPresenter().getOperation(ACTION_AGREGAR_PERSONAL));
        builder.removeAction(getPresenter().getOperation(ACTION_ELIMINAR_PERSONAL));
        builder.autoCompletitionData(getPresenter().getModel(PROP_LISTA_PERSONAL_DISPONIBLE));
        builder.autoCOmpletitionDataSelection(getPresenter().getModel(PROP_PERSONAL_DISPONIBLE_SELECCIONADO));
        builder.jTextFieldDataName("Trabajadores");
        builder.tableDataHolder(getPresenter().getModel(PROP_LISTA_PERSONAL_CONTENIDO));
        builder.tableSelectionDataHolder(getPresenter().getModel(PROP_PERSONAL_CONTENIDO_SELECIONADO));

        BindableTableModel<AsistenciaPersonal> tableModel
                = new BindableTableModel<AsistenciaPersonal>() {
            @Override
            public int getColumnCount() {
                return UserResolver.resolveUser(Personal.class).getPuestoTrabajonombrePuesto().getNivelAcceso() > 2 ? 5 : 2;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return getRow(rowIndex).getAsistenciaPersonalPK().getVentaid();
                    case 1:
                        return getRow(rowIndex).getAsistenciaPersonalPK().getPersonalusuario();
                    case 2:
                        return getRow(rowIndex).getPago();
                    case 3:
                        return utils.setDosLugaresDecimalesFloat(getRow(rowIndex).getPropina());
                    case 4:
                        return getRow(rowIndex).getAMayores();
                    default:
                        return null;
                }
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Dia de Trabajo";
                    case 1:
                        return "Usuario trabajando";
                    case 2:
                        return "Estimado de pago";
                    case 3:
                        return "Propina ";
                    case 4:
                        return "A Mayores";
                    default:
                        return null;
                }
            }
        };
        builder.tableModel(tableModel);

        tablePagoTrabajadores = builder.build();

        tablePagoTrabajadores.getjPanelOpciones().add(jButtonImprimir, 0);
        tablePagoTrabajadores.getjPanelOpciones().add(jButtonAMayores, 0);

        add(tablePagoTrabajadores, BorderLayout.CENTER);
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }
    // End of variables declaration//GEN-END:variables
}

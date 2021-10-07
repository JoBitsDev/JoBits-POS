/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.configuracion.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.servicios.impresion.Impresora;
import com.jobits.pos.servicios.impresion.ImpresoraService;
import com.jobits.pos.ui.configuracion.Impresoras;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.root101.clean.core.domain.services.ResourceHandler;
import java.awt.Container;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class ImpresorasViewPresenter extends AbstractListViewPresenter<ImpresorasViewModel> {

    public static String ACTION_CHANGE_DEFAULT;
    public static final String ACTION_SET_PANEL_VISIBLE = "Panel Visible";

    ImpresoraService service;

    public ImpresorasViewPresenter(ImpresoraService service) {
        super(new ImpresorasViewModel(), Impresoras.VIEW_NAME);
        this.service = service;
        setListToBean();
    }

    @Override
    protected void registerOperations() {
        super.registerOperations(); //To change body of generated methods, choose Tools | Templates.
        registerOperation(new AbstractViewAction(ACTION_CHANGE_DEFAULT) {
            @Override
            public Optional doAction() {
                Impresora impresora = getBean().getImpresora_seleccionada();//TODO: logica en presenter
                impresora.setPorDefecto(!impresora.isPorDefecto());
                service.updateImpresora(impresora);
                getBean().getLista_impresoras().clear();
                getBean().getLista_impresoras().addAll(new ArrayListModel<>(service.findAll()));
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SET_PANEL_VISIBLE) {
            @Override
            public Optional doAction() {
                onSetPanelVisibile();
                return Optional.empty();
            }
        });
    }

    @Override
    protected void onAgregarClick() {
        if (getBean().getNombre_impresora_actual() != null
                && getBean().getImpresora_sistema_seleccionada() != null
                && getBean().getArea_seleccionado() != null) {

            if (showConfirmDialog(Application.getInstance().getMainWindow())) {
                Impresora newImpresora = new Impresora(
                        getBean().getNombre_impresora_actual(),
                        getBean().getImpresora_sistema_seleccionada(),
                        getBean().getArea_seleccionado(), false);

                if (service.agregarImpresora(newImpresora) != null) {
                    showSuccessDialog(Application.getInstance().getMainWindow(), "Se ha agregado la impresora con éxito");

                    getBean().getLista_impresoras().clear();
                    getBean().getLista_impresoras().addAll(new ArrayListModel<>(service.findAll()));

                    getBean().setNombre_impresora_actual(null);
                    getBean().setImpresora_sistema_seleccionada(null);
                    getBean().setArea_seleccionado(null);
                }
            }

        } else {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Rellene todos los campos");
        }

    }

    @Override
    protected void onEditarClick() {
        //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onEliminarClick() {
        Impresora impresoraToDelete = getBean().getImpresora_seleccionada();
        if (showConfirmDialog(Application.getInstance().getMainWindow())) {
            if (service.deleteImpresora(impresoraToDelete) != null) {
                getBean().getLista_impresoras().clear();
                getBean().getLista_impresoras().addAll(new ArrayListModel<>(service.findAll()));
                showSuccessDialog(Application.getInstance().getMainWindow(), "Se ha eliminado la impresora con éxito");
            }
        }
    }

    @Override
    protected void setListToBean() {
        getBean().setLista_impresoras(new ArrayListModel<>(service.findAll()));
        getBean().setLista_impresoras_sistema(new ArrayListModel<>(service.getNombreImpresorasSistema()));
        getBean().setLista_area(new ArrayListModel<>(service.getNombreGrupos()));
    }

    public void showSuccessDialog(Container view, String text) {

        JOptionPane.showMessageDialog(view, text,
                ResourceHandler.getString("label_informacion"), JOptionPane.INFORMATION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/exitoso.png")));

    }

    protected boolean showConfirmDialog(Container view) {

        return true ? JOptionPane.showConfirmDialog(view, ResourceHandler.getString("desea_aplicar_cambios"),
                ResourceHandler.getString("label_confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/pregunta.png")))
                == JOptionPane.YES_OPTION : true;

    }

    private void onSetPanelVisibile() {
        getBean().setPanel_visible(!getBean().isPanel_visible());
    }
}

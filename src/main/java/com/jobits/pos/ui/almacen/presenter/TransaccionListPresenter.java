/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen.presenter;

import com.jobits.pos.inventario.core.almacen.domain.Almacen;
import com.jobits.pos.inventario.core.almacen.usecase.TransaccionListService;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.almacen.TransaccionListView;
import static com.jobits.pos.ui.almacen.presenter.TransaccionListModel.PROP_SHOW_MERMAS;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import java.beans.PropertyChangeEvent;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class TransaccionListPresenter extends AbstractListViewPresenter<TransaccionListModel> {

    TransaccionListService service;
    public static final String ACTION_IMPRIMIR_TRANSACCIONES = "";
    public static final String ACTION_MOSTRAR_MERMAS = "Mostrar Mermas";
    public static final String ACTION_CERRAR_POPUP = "Cerrar";

    public TransaccionListPresenter(TransaccionListService service, Almacen almacen) {
        super(new TransaccionListModel(), TransaccionListView.VIEW_NAME);
        this.service = service;
        getBean().setAlmacen(almacen);
        setListToBean();
        registerExtraOperations();
        registerListeners();
    }

    protected void registerExtraOperations() {
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_TRANSACCIONES) {
            @Override
            public Optional doAction() {
                JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Operacion por desarrollar");
//                List a = new ArrayList<>();
//                for (Transaccion lista_elemento : getBean().getLista_elementos()) {
//                    //TODO:selecionar solo transaciones seleccionadas
//                }
//                service.imprimirTransaccionesSeleccionadas(a);
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CERRAR_POPUP) {
            @Override
            public Optional doAction() {
                Application.getInstance().getNavigator().navigateUp();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_MOSTRAR_MERMAS) {
            @Override
            public Optional doAction() {
                onShowMermasClick();
                return Optional.empty();
            }
        }
        );
    }

    @Override
    protected void onAgregarClick() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onEditarClick() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onEliminarClick() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void setListToBean() {
        getBean().getLista_elementos().clear();
        getBean().getLista_elementos().addAll(service.findAllByAlmacen(getBean().getAlmacen()));
    }

    private void onShowMermasClick() {
        getBean().setShow_mermas(!getBean().isShow_mermas());
    }

    private void registerListeners() {
        getBean().addPropertyChangeListener(PROP_SHOW_MERMAS, (PropertyChangeEvent evt) -> {
            getBean().getLista_elementos().clear();
            if ((boolean) evt.getNewValue()) {
                getBean().getLista_elementos().addAll(service.findMermasByAlmacen(getBean().getAlmacen()));
            } else {
                getBean().getLista_elementos().addAll(service.findAllByAlmacen(getBean().getAlmacen()));
            }
        });
    }

}

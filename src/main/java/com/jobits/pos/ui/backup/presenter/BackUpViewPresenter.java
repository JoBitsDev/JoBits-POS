/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.backup.presenter;

import com.jobits.pos.controller.backup.BackUpService;
import com.jobits.pos.controller.login.UbicacionConexionController;
import com.jobits.pos.domain.UbicacionConexionModel;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.beans.PropertyChangeEvent;
import java.util.Arrays;
import java.util.Optional;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class BackUpViewPresenter extends AbstractViewPresenter<BackUpViewModel> {

    public static final String ACTION_REALIZAR_COPIA_SEG = "Realizar copia de seguridad";

    private UbicacionConexionController ubicacionController;

    public BackUpViewPresenter(UbicacionConexionController ubicacionController) {
        super(new BackUpViewModel());
        this.ubicacionController = ubicacionController;
        updateBeanData();

    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_REALIZAR_COPIA_SEG) {
            @Override
            public Optional doAction() {
                onRealizarCopiaDeSeguridad();
                return Optional.empty();
            }
        });
    }

    private void onRealizarCopiaDeSeguridad() {
        UbicacionConexionModel model = getBean().getUbicacion_seleccionada();
        if (R.CURRENT_CONNECTION.equals(model) || model.getTipoUbicacion() == UbicacionConexionModel.TipoUbicacion.MASTER) {
            Application.getInstance().getNotificationService().
                    showDialog("Las copias de seguridad no pueden realizarse " //TODO hardcoded String
                            + "en el mismo servidor que se origina el pedido "
                            + "ni en un servidor principal",
                            TipoNotificacion.ERROR);
        } else {

            boolean resp = (boolean) Application.getInstance().getNotificationService().
                    showDialog(R.RESOURCE_BUNDLE.getString("confirmar_copia_seg"), TipoNotificacion.DIALOG_CONFIRM).orElse(false);

            if (resp) {

                BackUpService bu = new BackUpService(getBean().getUbicacion_seleccionada());

                if (getBean().isCheckbox_personal()) {
                    bu.setTipoBackUp(BackUpService.TipoBackUp.PERSONAL);

                }
                if (getBean().isCheckbox_productos()) {
                    bu.setTipoBackUp(BackUpService.TipoBackUp.PRODUCTOS);

                }
                if (getBean().isCheckbox_ventas()) {
                    bu.setTipoBackUp(BackUpService.TipoBackUp.VENTA);
                }
                if (getBean().isCheckbox_todo()) {
                    bu.setTipoBackUp(BackUpService.TipoBackUp.All);
                }
                if (getBean().isCheckbox_limpieza_servidor()) {
//                    LogInController controller = new LogInController();
//                    if (controller.constructoAuthorizationViewForConfirm()) {
                    bu.setTipoBackUp(BackUpService.TipoBackUp.LIMPIEZA);
//                    }
                }

                if (bu.getTipoBackUp() != BackUpService.TipoBackUp.NULO) {
                    bu.addPropertyChangeListener(BackUpService.PROPERTY_PROGRESS_UPDATE, (PropertyChangeEvent evt) -> {
                        int valor = ((Float) evt.getNewValue()).intValue();
                        getBean().setProgress_indicator(valor);
                    });
                    Application.getInstance().getBackgroundWorker().processInBackground(bu);

                }
            }

        }
    }

    private void updateBeanData() {
        getBean().setLista_ubicaciones(Arrays.asList(ubicacionController.getUbicaciones().getUbicaciones()));
    }

}

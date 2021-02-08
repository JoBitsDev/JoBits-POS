/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.backup.presenter;

import com.jobits.pos.controller.backup.impl.BackUpController;
import com.jobits.pos.controller.backup.BackUpService;
import org.jobits.app.repo.UbicacionConexionModel;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.beans.PropertyChangeEvent;
import java.util.Arrays;
import java.util.Optional;
import org.jobits.app.repo.UbicacionConexionService;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class BackUpViewPresenter extends AbstractViewPresenter<BackUpViewModel> {

    public static final String ACTION_REALIZAR_COPIA_SEG = "Realizar copia de seguridad";
    public static final String ACTION_CERRAR = "Cerrar";

    private UbicacionConexionService ubicacionController = PosDesktopUiModule.getInstance().getImplementation(UbicacionConexionService.class);

    public BackUpViewPresenter() {
        super(new BackUpViewModel());
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
        registerOperation(new AbstractViewAction(ACTION_CERRAR) {
            @Override
            public Optional doAction() {
                Application.getInstance().getNavigator().navigateUp();
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

                BackUpService bu = new BackUpController(getBean().getUbicacion_seleccionada());//TODO: inyectar despues

                if (getBean().isCheckbox_personal()) {
                    bu.setTipoBackUp(BackUpController.TipoBackUp.PERSONAL);

                }
                if (getBean().isCheckbox_productos()) {
                    bu.setTipoBackUp(BackUpController.TipoBackUp.PRODUCTOS);

                }
                if (getBean().isCheckbox_ventas()) {
                    bu.setTipoBackUp(BackUpController.TipoBackUp.VENTA);
                }
                if (getBean().isCheckbox_todo()) {
                    bu.setTipoBackUp(BackUpController.TipoBackUp.All);
                }
                if (getBean().isCheckbox_limpieza_servidor()) {
//                    LogInController controller = new LogInController();
//                    if (controller.constructoAuthorizationViewForConfirm()) {
                    bu.setTipoBackUp(BackUpController.TipoBackUp.LIMPIEZA);
//                    }
                }

                if (bu.getTipoBackUp() != BackUpController.TipoBackUp.NULO) {
                    bu.addPropertyChangeListener(BackUpController.PROPERTY_PROGRESS_UPDATE, (PropertyChangeEvent evt) -> {
                        int valor = ((Float) evt.getNewValue()).intValue();
                        getBean().setProgress_indicator(valor);
                    });
                    Application.getInstance().getBackgroundWorker().processInBackground(bu::execute);
                }
            }

        }

    }

    private void updateBeanData() {
        getBean().setLista_ubicaciones(Arrays.asList(ubicacionController.getUbicaciones().getUbicaciones()));
    }

}

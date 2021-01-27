/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.login.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jhw.swing.material.standars.MaterialIcons;
import com.jhw.swing.util.icons.DerivableIcon;
import com.jobits.pos.core.repo.autenticacion.PersonalDAO;
import com.jobits.pos.controller.login.MainMenuController;
import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.controller.login.LogInService;
import com.jobits.pos.controller.login.UbicacionConexionController;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.UbicacionConexionModel;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.ui.mainmenu.presenter.MainMenuPresenter;
import com.jobits.pos.ui.mainmenu.MainMenuView;
import com.jobits.pos.ui.RootView;
import com.jobits.pos.ui.login.UbicacionView;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.LongProcessActionServiceImpl;
import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class LoginViewPresenter extends AbstractViewPresenter<LoginViewModel> {

    public static final String ACTION_AUTENTICAR = "Autenticar";

    public static final String ACTION_EDITAR_UBICACION = "Editar ubicacion";

    LogInService service;
    UbicacionConexionController ubicacionController;

    public LoginViewPresenter(LogInService service) {
        super(new LoginViewModel());
        this.service = service;
        ubicacionController = new UbicacionConexionController();
        getBean().setListaUbicaciones(new ArrayListModel<>(
                Arrays.asList(ubicacionController.getUbicaciones().getUbicaciones())));
    }

    public void setUbicacionSeleccionada() {
        getBean().setUbicacionSeleccionada(ubicacionController.getUbicaciones().getUbicacionActiva());
    }

    private void onAutenticarClick() {
        String password = getBean().getContrasena();
        getBean().setContrasena("");
        try {
            if (service.autenticar(getBean().getNombreUsuario(), password.toCharArray())) {
                Application.getInstance().setLoggedUser(service.getUsuarioConectado());
                Application.getInstance().getNotificationService().notify("Bienvenido", TipoNotificacion.SUCCESS);
                NavigationService.getInstance().navigateTo(MainMenuView.VIEW_NAME,
                        new MainMenuPresenter(new MainMenuController(PersonalDAO.getInstance().find(getBean().getNombreUsuario())))); //TODO revisar eso codigo que no le pertenece a esta clse
                RootView.getInstance().getDashboard().getTaskPane().setShrinked(true);
            }
        } catch (IllegalArgumentException ex) {
            Application.getInstance().getNotificationService().notify(ex.getMessage(), TipoNotificacion.ERROR);//PENDING jtext fields pierden focus cuando sale la notificacion
        }
        RootView.getInstance().getStatusBar().refreshView();
    }

    private void onUbicacionSeleccionadaChanged() {
        new LongProcessActionServiceImpl("Conectando a BD") {//TODO: internacionalizar
            @Override
            protected void longProcessMethod() {
                try {
                    ubicacionController.setSelectedUbicacion(getBean().getUbicacionSeleccionada());
                    service.connect(ubicacionController.getUbicaciones().getUbicacionActiva());
                    getBean().setEstadoConexion(getBean().getUbicacionSeleccionada().toString());
                    actualizarLabelConexionYBotonAutenticar(service.isConnected());

                } catch (IOException ex) {
                    Logger.getLogger(LoginViewPresenter.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(LoginViewPresenter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.performAction(null);
    }

    private void onEditarUbicacionClick() {
        JComboBox<UbicacionConexionModel> jComboBox1 = new JComboBox<>();
        jComboBox1.setModel(new DefaultComboBoxModel<>(ubicacionController.getUbicaciones().getUbicaciones()));
        jComboBox1.setSelectedItem(getBean().getUbicacionSeleccionada());
        Object[] options = {"Seleccionar", "Editar", "Cancelar"};
        //                     yes        no       cancel
        int confirm = JOptionPane.showOptionDialog(
                null,
                jComboBox1,
                "Ubicaciones",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.YES_NO_CANCEL_OPTION,
                MaterialIcons.LOCATION_ON,
                options,
                options[0]);
        switch (confirm) {
            case JOptionPane.YES_OPTION:
                getBean().setUbicacionSeleccionada((UbicacionConexionModel) jComboBox1.getSelectedItem());
                break;
            case JOptionPane.NO_OPTION:
                NavigationService.getInstance().navigateTo(UbicacionView.VIEW_NAME,
                        new UbicacionViewPresenter(ubicacionController), DisplayType.POPUP);//TODO codigo de ubicaciones
                break;
            case JOptionPane.CANCEL_OPTION:
                break;
            default:
                break;
        }

//        JOptionPane.showMessageDialog(null, jComboBox1, "Ubicaciones", 0, MaterialIcons.LOCATION_ON);
    }

    private void actualizarLabelConexionYBotonAutenticar(boolean conn) {
        if (conn) {
            getBean().setColorLabelConexion(Color.green);
        } else {
            getBean().setColorLabelConexion(Color.red);
        }
        getBean().setBotonAutenticarHabilitado(conn);
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_AUTENTICAR) {
            @Override
            public Optional doAction() {
                onAutenticarClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_EDITAR_UBICACION, false) {
            @Override
            public Optional doAction() {
                onEditarUbicacionClick();
                return Optional.empty();
            }
        });
        getBean().addPropertyChangeListener(LoginViewModel.PROP_UBICACIONSELECCIONADA, (evt) -> {
            onUbicacionSeleccionadaChanged();
        });
    }

}

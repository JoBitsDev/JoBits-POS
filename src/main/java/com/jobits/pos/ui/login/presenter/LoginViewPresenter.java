/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.login.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.client.webconnection.exception.ServerErrorException;
import com.jobits.pos.client.webconnection.login.LoginService;
import com.jobits.pos.client.webconnection.login.model.UbicacionModel;
import com.jobits.pos.client.webconnection.login.model.UbicacionService;
import com.root101.swing.material.standards.MaterialIcons;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.ui.mainmenu.presenter.MainMenuPresenter;
import com.jobits.pos.ui.mainmenu.MainMenuView;
import com.jobits.pos.ui.RootView;
import com.jobits.pos.ui.login.UbicacionView;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.LongProcessActionServiceImpl;
import com.jobits.pos.utils.utils;
import com.jobits.ui.swing.LongProcessActionService;
import java.awt.Color;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static final String PROP_READY_TO_LOGIN = "Listo para logearse";

    LoginService service = PosDesktopUiModule.getInstance().getImplementation(LoginService.class);
    UbicacionService ubicacionController = PosDesktopUiModule.getInstance().getImplementation(UbicacionService.class);

    public LoginViewPresenter() {
        super(new LoginViewModel());
        refreshState();
    }

    public void setUbicacionSeleccionada() {
        getBean().setUbicacionSeleccionada(ubicacionController.loadLocations().getActiveUbicacion());
        firePropertyChange(PROP_READY_TO_LOGIN, null, null);
    }

    private void onAutenticarClick() {
        String password = utils.getSHA256(getBean().getContrasena());
        String passwordPlain = (getBean().getContrasena());
        getBean().setContrasena("");

        new LongProcessActionServiceImpl("Autenticando") {//TODO: internacionalizar
            @Override
            protected void longProcessMethod() {
                var configuracionService = PosDesktopUiModule.getInstance().getImplementation(ConfiguracionService.class);
                boolean ret = false;
                try {
                    ret = service.autenticar(getBean().getNombreUsuario(), password.toCharArray());
                } catch (IllegalArgumentException ex) {
                    if ((boolean) configuracionService.getConfiguracion(R.SettingID.GENERAL_AUTENTICACION_PLANA)) {
                        ret = service.autenticar(getBean().getNombreUsuario(), passwordPlain.toCharArray());
                    }
                }
                if (ret) {
                    Application.getInstance().setLoggedUser(service.getUsuarioConectado());
                    Application.getInstance().getNotificationService().notify("Bienvenido", TipoNotificacion.SUCCESS);
                    NavigationService.getInstance().navigateTo(MainMenuView.VIEW_NAME,
                            new MainMenuPresenter(new MainMenuController(PersonalDAO.getInstance().find(getBean().getNombreUsuario())))); //TODO revisar eso codigo que no le pertenece a esta clse
                    RootView.getInstance().getDashboard().getTaskPane().setShrinked(true);
                }
            }
        }.performAction(null);

        RootView.getInstance().getStatusBar().refreshView();
     
    }

    private void onUbicacionSeleccionadaChanged() {
        new LongProcessActionServiceImpl("Conectando a BD") {//TODO: internacionalizar
            @Override
            protected void longProcessMethod() {
                try {
                    ubicacionController.setSelectedLocation(getBean().getUbicacionSeleccionada());
                    service.connect(ubicacionController.loadLocations().getActiveUbicacion());//TODO:REST API
                    getBean().setEstadoConexion(getBean().getUbicacionSeleccionada().toString());
                    actualizarLabelConexionYBotonAutenticar(service.isConnected());

                }  catch (Exception ex) {
                    Logger.getLogger(LoginViewPresenter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.performAction(null);
    }

    private void onEditarUbicacionClick() {
        JComboBox<UbicacionModel> jComboBox1 = new JComboBox<>();
        var wrapper = ubicacionController.loadLocations();
        UbicacionModel[] arr = new UbicacionModel[wrapper.getUbicaciones().size()];
        jComboBox1.setModel(new DefaultComboBoxModel<>(wrapper.getUbicaciones().toArray(arr)));
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
                getBean().setUbicacionSeleccionada((UbicacionModel) jComboBox1.getSelectedItem());
                break;
            case JOptionPane.NO_OPTION:
                ubicacionController.setSelectedLocation((UbicacionModel) jComboBox1.getSelectedItem());
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
    protected Optional refreshState() {
        getBean().setListaUbicaciones(new ArrayListModel<>(ubicacionController.loadLocations().getUbicaciones()));
        setUbicacionSeleccionada();
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
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

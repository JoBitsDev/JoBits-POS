/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.login.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.clientes.ClientesDetailService;
import com.root101.swing.material.standards.MaterialIcons;
import com.jobits.pos.controller.configuracion.ConfiguracionService;
import com.jobits.pos.core.repo.autenticacion.PersonalDAO;
import com.jobits.pos.controller.login.impl.MainMenuController;
import com.jobits.pos.controller.login.LogInService;
import com.jobits.pos.controller.mesa.MesaService;
import com.jobits.pos.controller.reservas.ReservaOrdenListener;
import com.jobits.pos.controller.reservas.UbicacionMesaListener;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.controller.venta.VentaListService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.hook.escandallo.ProductoEscandalloHook;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.reserva.core.module.ReservaCoreModule;
import com.jobits.pos.reserva.core.usecase.ReservaUseCase;
import com.jobits.pos.reserva.core.usecase.UbicacionUseCase;
import com.jobits.pos.ui.mainmenu.presenter.MainMenuPresenter;
import com.jobits.pos.ui.mainmenu.MainMenuView;
import com.jobits.pos.ui.RootView;
import com.jobits.pos.ui.login.UbicacionView;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.LongProcessActionServiceImpl;
import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.jobits.db.core.domain.UbicacionConexionModel;
import org.jobits.db.core.usecase.UbicacionConexionService;

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

    LogInService service;
    UbicacionConexionService ubicacionController = PosDesktopUiModule.getInstance().getImplementation(UbicacionConexionService.class);

    public LoginViewPresenter(LogInService service) {
        super(new LoginViewModel());
        this.service = service;
        refreshState();
    }

    public void setUbicacionSeleccionada() {
        getBean().setUbicacionSeleccionada(ubicacionController.getUbicaciones().getUbicacionActiva());
        firePropertyChange(PROP_READY_TO_LOGIN, null, null);
    }

    private void onAutenticarClick() {
        String password = getBean().getContrasena();
        getBean().setContrasena("");
        try {
            new LongProcessActionServiceImpl("Autenticando") {//TODO: internacionalizar
                @Override
                protected void longProcessMethod() {
                    if (service.autenticar(getBean().getNombreUsuario(), password.toCharArray())) {
                        Application.getInstance().setLoggedUser(service.getUsuarioConectado());
                        Application.getInstance().getNotificationService().notify("Bienvenido", TipoNotificacion.SUCCESS);
                        NavigationService.getInstance().navigateTo(MainMenuView.VIEW_NAME,
                                new MainMenuPresenter(new MainMenuController(PersonalDAO.getInstance().find(getBean().getNombreUsuario())))); //TODO revisar eso codigo que no le pertenece a esta clse
                        RootView.getInstance().getDashboard().getTaskPane().setShrinked(true);
                    }
                }
            }.performAction(null);
        } catch (IllegalArgumentException ex) {
            Application.getInstance().getNotificationService().notify(ex.getMessage(), TipoNotificacion.ERROR);//PENDING jtext fields pierden focus cuando sale la notificacion
        }
        RootView.getInstance().getStatusBar().refreshView();
        //TODO: poner todos los listeners en los controllers del core
        if (ReservaOrdenListener.getInstance() == null) {
            ReservaOrdenListener.initInstance(
                    PosDesktopUiModule.getInstance().getImplementation(OrdenService.class),
                    ReservaCoreModule.getInstance().getImplementation(ReservaUseCase.class),
                    PosDesktopUiModule.getInstance().getImplementation(VentaListService.class),
                    PosDesktopUiModule.getInstance().getImplementation(MesaService.class),
                    PosDesktopUiModule.getInstance().getImplementation(ClientesDetailService.class));
        }
        if (UbicacionMesaListener.getInstance() == null) {
            UbicacionMesaListener.initInstance(
                    ReservaCoreModule.getInstance().getImplementation(UbicacionUseCase.class),
                    PosDesktopUiModule.getInstance().getImplementation(MesaService.class),
                    PosDesktopUiModule.getInstance().getImplementation(ConfiguracionService.class));
        }
        ReservaOrdenListener.getInstance();
        UbicacionMesaListener.getInstance();
        ProductoEscandalloHook.getInstance();
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
                ubicacionController.setSelectedUbicacion((UbicacionConexionModel) jComboBox1.getSelectedItem());
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
        getBean().setListaUbicaciones(new ArrayListModel<>(
                Arrays.asList(ubicacionController.getUbicaciones().getUbicaciones())));
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

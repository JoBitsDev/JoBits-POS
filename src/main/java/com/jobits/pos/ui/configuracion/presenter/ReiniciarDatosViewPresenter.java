/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.configuracion.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.root101.swing.material.standards.MaterialIcons;
import com.jobits.pos.controller.almacen.AlmacenManageService;
import com.jobits.pos.controller.almacen.IPVService;
import com.jobits.pos.controller.login.LogInService;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionService;
import com.jobits.pos.utils.utils;

/**
 *
 * @author Home
 */
public class ReiniciarDatosViewPresenter extends AbstractViewPresenter<ReiniciarDatosViewModel> {

    public static final String ACTION_REINICIAR_ALMACEN = "Reiniciar Almacen";
    public static final String ACTION_REINICIAR_IPV = "Reiniciar IPV";

    AlmacenManageService almacenService = PosDesktopUiModule.getInstance().getImplementation(AlmacenManageService.class);
    LogInService loginService = PosDesktopUiModule.getInstance().getImplementation(LogInService.class);
    IPVService ipvService = PosDesktopUiModule.getInstance().getImplementation(IPVService.class);
    PuntoElaboracionService cocinaService = PosDesktopUiModule.getInstance().getImplementation(PuntoElaboracionService.class);
    VentaDetailService ventaService = PosDesktopUiModule.getInstance().getImplementation(VentaDetailService.class);

    public ReiniciarDatosViewPresenter() {
        super(new ReiniciarDatosViewModel());
        refreshState();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_REINICIAR_ALMACEN) {
            @Override
            public Optional doAction() {
                reiniciarAlmacen();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_REINICIAR_IPV) {
            @Override
            public Optional doAction() {
                reinciarIPV();
                return Optional.empty();
            }
        });
    }

    @Override
    protected Optional refreshState() {
        getBean().setLista_almacen(new ArrayListModel<>(almacenService.findAll()));
        if (!getBean().getLista_almacen().isEmpty()) {
            getBean().setAlmacen_seleccionado(getBean().getLista_almacen().get(0));
        }
        getBean().setLista_cocinas(new ArrayListModel<>(cocinaService.findAll()));
        if (!getBean().getLista_cocinas().isEmpty()) {
            getBean().setCocina_seleccionada(getBean().getLista_cocinas().get(0));
        }
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }

    private void reiniciarAlmacen() {
        if (loginService.autorizarCurrentUser(R.NivelAcceso.ADMINISTRADOR)) {
            if ((boolean) Application.getInstance().getNotificationService().
                    showDialog("Esta seguro que desea reiniciar los datos del almacen seleccionado?",
                            TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
                Application.getInstance().getBackgroundWorker().processInBackground("Reiniciando Almacen...", () -> {
                    almacenService.resetAlmacen(getBean().getAlmacen_seleccionado().getCodAlmacen());
                });
                Application.getInstance().getNotificationService().showDialog(
                        "Se han reiniciado los datos de " + getBean().getAlmacen_seleccionado(),
                        TipoNotificacion.INFO);
            }
        }
    }

    private void reinciarIPV() {
        if (loginService.autorizarCurrentUser(R.NivelAcceso.ADMINISTRADOR)) {
            if ((boolean) Application.getInstance().getNotificationService().
                    showDialog("Esta seguro que desea reiniciar los datos del IPV seleccionado?",
                            TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
                Venta venta = selectFecha(ventaService.getVentasDeFecha(utils.dateToLocalDate(getBean().getFecha_venta_seleccionada())));
                if (venta != null) {
                    Application.getInstance().getBackgroundWorker().processInBackground("Reiniciando IPVs...", () -> {
                        ipvService.reiniciarIPV(getBean().getCocina_seleccionada(), venta);
                    });
                    Application.getInstance().getNotificationService().showDialog(
                            "Se han reiniciado los IPV del "
                            + new SimpleDateFormat("dd/MM/yyyy").format(getBean().getFecha_venta_seleccionada())
                            + " en " + getBean().getCocina_seleccionada().getNombreCocina(),
                            TipoNotificacion.INFO);
                }
            }
        }
    }

    private Venta selectFecha(List<Venta> ventas) {
        if (ventas.isEmpty()) {
            Application.getInstance().getNotificationService().showDialog("No hay ventas registradas", TipoNotificacion.ERROR);
        } else if (ventas.size() == 1) {
            return ventas.get(0);
        } else {
            JComboBox<Venta> jComboBox1 = new JComboBox<>();
            jComboBox1.setModel(new DefaultComboBoxModel<>(ventas.toArray(new Venta[0])));
            jComboBox1.setSelectedItem(ventas.get(0));
            Object[] options = {"Seleccionar", "Cancelar"};
            //                     yes           cancel
            int confirm = JOptionPane.showOptionDialog(
                    null,
                    jComboBox1,
                    "Ventas",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.YES_NO_OPTION,
                    MaterialIcons.MONETIZATION_ON,
                    options,
                    options[0]);
            switch (confirm) {
                case JOptionPane.YES_OPTION:
                    return (Venta) jComboBox1.getSelectedItem();
                case JOptionPane.NO_OPTION:
                    break;
                default:
                    break;
            }
        }
        return null;
    }

}

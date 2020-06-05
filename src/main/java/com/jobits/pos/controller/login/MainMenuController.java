/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.login;

import com.jobits.pos.controller.backup.SincronizacionController;
import com.jobits.pos.controller.AbstractController;
import com.jobits.pos.controller.licencia.Licence;
import com.jobits.pos.controller.licencia.LicenceController;
import com.jobits.pos.controller.configuracion.ConfiguracionController;
import com.jobits.pos.controller.venta.OrdenController;
import com.jobits.pos.controller.venta.VentaDetailController;
import com.jobits.pos.exceptions.UnauthorizedAccessException;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.NotificationService;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class MainMenuController {

    private SincronizacionController sincronizacion = new SincronizacionController();

    public MainMenuController() {
        if (Application.getInstance().getLoggedUser() == null) {
            throw new IllegalStateException("Usuario no autenticado");
        }
        new ConfiguracionController().cargarConfiguracion();
        sincronizacion.terminarSincronizacion();
    }

    public boolean validate(Personal loggedUser, MenuButtons menuButtons) throws UnauthorizedAccessException {
        if (!(loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() >= menuButtons.getNivelMinimoAcceso())) {
            Application.getInstance().getNotificationService().notify("El usuario no tiene permisos", TipoNotificacion.ERROR);//TODO: Cambiar por autorization
            return false;
        }
        return true;
    }

    public Licence getEstadoLicencia() {
        LicenceController licence = new LicenceController(Licence.TipoLicencia.APLICACION);
        return licence.getLicence();
    }

    public VentaDetailController comenzarVentas(Date date) throws IllegalArgumentException {
        int nivel = R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso();
        if (nivel > R.NivelAcceso.ECONOMICO.getNivel()) {
            if (date == null) {
                return new VentaDetailController();
            } else {
                return new VentaDetailController(new OrdenController(), date);
            }
        } else if (nivel >= R.NivelAcceso.CAJERO.getNivel()) {
            return new VentaDetailController();
        } else if (nivel >= R.NivelAcceso.DEPENDIENTE.getNivel()) {
            if (showConfirmDialog("Desea comenzar el dia de trabajo en el dia " + R.DATE_FORMAT.format(new Date()))) {
                VentaDetailController controller = new VentaDetailController(new OrdenController(), new Date());
                controller.initIPV(controller.getInstance());
                Application.getInstance().getNotificationService().
                        notify("El dia de trabajo esta iniciado en la fecha: "
                                + R.DATE_FORMAT.format(controller.getInstance().getFecha()),
                                TipoNotificacion.SUCCESS);
                return controller;
            }
        }
        return null;
    }

    private boolean showConfirmDialog(String string) {
        return JOptionPane.showConfirmDialog(Application.getInstance().getMainWindow(), string,
                R.RESOURCE_BUNDLE.getString("label_confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/pregunta.png")))
                == JOptionPane.YES_OPTION;
    }

    public enum MenuButtons {

        //
        //Productos de Venta
        //
        MENU(1, "Productos"),
        INSUMO(3, "Insumos"),
        COCINA(3, "Puntos elaboración"),
        SECCION(3, "Menú"),
        SALON(3, "Areas de Venta"),
        //
        // Almacen
        //
        ALMACEN(2, "Almacenes"),
        ACTIVOS(2),
        IPV(2, "IPV"),
        //
        //Contabilidad
        //
        VENTAS(3, "Ventas"),
        CUENTAS_CONTABLES(4),
        PRESUPUESTO(4),
        COMENZAR_VENTAS(0, "Comenzar Turno"),
        //
        //TRABAJADORES
        //

        TRABAJADORES(4, "Trabajadores"),
        PUESTOS_TRABAJO(4, "Puestos de trabajos"),
        NOMINAS(3, "Nóminas"),
        //
        //CONFIGURACION
        //

        COPIA_SEG(4, "Copias de seguridad"),
        LICENCIA(0, "Licencia"),
        CONFIGURACION(5, "Configuración");

        private final int nivelMinimoAcceso;
        private final String nombreVisible;

        private MenuButtons(int nivelMinimoAcceso) {
            this.nivelMinimoAcceso = nivelMinimoAcceso;
            this.nombreVisible = name();
        }

        private MenuButtons(int nivelMinimoAcceso, String nombreVisible) {
            this.nivelMinimoAcceso = nivelMinimoAcceso;
            this.nombreVisible = nombreVisible;
        }

        public int getNivelMinimoAcceso() {
            return nivelMinimoAcceso;
        }

        @Override
        public String toString() {
            return nombreVisible;
        }

    }

}

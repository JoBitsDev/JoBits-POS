/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.login;

import com.jobits.pos.ui.login.MainView;
import com.jobits.pos.ui.copiaSegView;
import java.awt.Container;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.jobits.pos.backup.SincronizacionController;
import com.jobits.pos.controller.AbstractController;
import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.controller.licencia.Licence;
import com.jobits.pos.controller.licencia.LicenceController;
import com.jobits.pos.controller.almacen.ActivoFijoController;
import com.jobits.pos.controller.almacen.AlmacenListController;
import com.jobits.pos.controller.almacen.IPVController;
import com.jobits.pos.controller.areaventa.AreaVentaController;
import com.jobits.pos.controller.configuracion.ConfiguracionController;
import com.jobits.pos.controller.gastos.pagos.CuentaController;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionListController;
import com.jobits.pos.controller.insumo.InsumoListController;
import com.jobits.pos.controller.productos.ProductoVentaListController;
import com.jobits.pos.controller.seccion.CartaListController;
import com.jobits.pos.controller.trabajadores.NominasController;
import com.jobits.pos.controller.trabajadores.PersonalListController;
import com.jobits.pos.controller.trabajadores.PuestoTrabajoListController;
import com.jobits.pos.controller.venta.VentaDetailController;
import com.jobits.pos.controller.venta.VentaListController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.exceptions.ExceptionHandler;
import com.jobits.pos.exceptions.UnauthorizedAccessException;
import com.jobits.pos.persistencia.Personal;
import com.jobits.pos.persistencia.Venta;
import com.jobits.pos.persistencia.modelos.PersonalDAO;
import com.jobits.pos.persistencia.modelos.VentaDAO;
import com.jobits.pos.recursos.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class MainController extends AbstractDialogController<Personal> {

    private SincronizacionController sincronizacion = new SincronizacionController();

    public MainController(Personal loggedUser) {
        super(PersonalDAO.getInstance());
        R.loggedUser = loggedUser;
        new ConfiguracionController().cargarConfiguracion();
    }

    public MainController(Personal loggedUser, JFrame parentView) {
        this(loggedUser);
        constructView(parentView);
    }

    @Override
    public void constructView(Container parent) {
        setView(new MainView(this, (JFrame) parent, true));
        getView().updateView();
        getView().setVisible(true);
        sincronizacion.terminarSincronizacion();
    }

    public void actionButton(MenuButtons menuButtons) {
        AbstractController controller = null;
        getView().setEnabled(false);
        try {
            validate(R.loggedUser, menuButtons);
            //LoadingWindow.show(getView());
            switch (menuButtons) {
                case MENU:
                    controller = new ProductoVentaListController(getView());
                    break;
                case INSUMO:
                    controller = new InsumoListController(getView());
                    break;
                case COCINA:
                    controller = new PuntoElaboracionListController(getView());
                    break;
                case SECCION:
                    controller = new CartaListController(getView());
                    break;
                case SALON:
                    controller = new AreaVentaController(getView());
                    break;
                case ALMACEN:
                    controller = new AlmacenListController(getView());
                    break;
                case VENTAS:
                    controller = new VentaListController(getView());
                    break;
                case TRABAJADORES:
                    controller = new PersonalListController(getView());
                    break;
                case PUESTOS_TRABAJO:
                    controller = new PuestoTrabajoListController(getView());
                    break;
                case IPV:
                    controller = new IPVController(getView());
                    break;
                case ACTIVOS:
                    controller = new ActivoFijoController(getView());
                    break;
                case COMENZAR_VENTAS:
                    controller = comenzarVentas();
                    break;
                case COPIA_SEG:
                    copiaSegView seg = new copiaSegView(getView(), true);
                    break;
                case LICENCIA:
                    controller = new LicenceController(getView(), Licence.TipoLicencia.APLICACION);
                    break;
                case NOMINAS:
                    controller = new NominasController(getView());
                    break;
                case CONFIGURACION:
                    controller = new ConfiguracionController(getView());
                    break;
                case CUENTAS_CONTABLES:
                    controller = new CuentaController(getView());
                    break;
                default:
                    getView().setEnabled(true);
                    throw new DevelopingOperationException(getView());

            }
        } catch (UnauthorizedAccessException e) {
        } catch (Exception e) {
            if (controller != null) {
                controller.getView().dispose();
                showErrorDialog((Container) controller.getView(), e.getMessage());
            } else {
                showErrorDialog(getView(), e.getMessage());
            }
        }
        getView().setEnabled(true);
    }

    private boolean validate(Personal loggedUser, MenuButtons menuButtons) throws UnauthorizedAccessException {
        if (!(loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() >= menuButtons.getNivelMinimoAcceso())) {
            throw new UnauthorizedAccessException(getView());
        }
        return true;
    }

    public Licence getEstadoLicencia() {
        LicenceController licence = new LicenceController(Licence.TipoLicencia.APLICACION);
        return licence.getLicence();
    }

    @Override
    public MainView getView() {
        return (MainView) super.getView(); //To change body of generated methods, choose Tools | Templates.
    }

    private AbstractController comenzarVentas() throws IllegalArgumentException {
        int nivel = R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso();
        if (nivel > R.NivelAcceso.ECONOMICO.getNivel()) {
            String date = JOptionPane.showInputDialog(getView(), "Introduzca el dia a trabajar en el formato dd/mm/aa \n "
                    + "o deje la casilla en blanco para comenzar en el ultimo dia sin cerrar ", "Entrada", JOptionPane.QUESTION_MESSAGE);
            if (date == null) {
                getView().setEnabled(true);
                return null;
            }
            if (date.isEmpty()) {
                return new VentaDetailController(getView());
            } else {
                Date fechaVenta;
                try {
                    fechaVenta = R.DATE_FORMAT.parse(date);
                } catch (ParseException ex) {
                    throw new IllegalArgumentException("La fecha introducida es incorrecta");
                }
                return new VentaDetailController(getView(), fechaVenta);
            }
        } else if (nivel >= R.NivelAcceso.CAJERO.getNivel()) {
            return new VentaDetailController(getView());
        } else if (nivel >= R.NivelAcceso.DEPENDIENTE.getNivel()) {
            if (showConfirmDialog(getView(), "Desea comenzar el dia de trabajo en el dia " + R.DATE_FORMAT.format(new Date()))) {
                VentaDetailController controller = new VentaDetailController(new Date());
                controller.initIPV(controller.getInstance());
                showSuccessDialog(getView(), "El dia de trabajo esta iniciado en la fecha: " + R.DATE_FORMAT.format(controller.getInstance().getFecha())
                        + " \npresione aceptar");
                return controller;
            }
        }
        return null;
    }

    public enum MenuButtons {

        //
        //Productos de Venta
        //
        MENU(1),
        INSUMO(3),
        COCINA(3),
        SECCION(3),
        SALON(3),
        //
        // Almacen
        //
        ALMACEN(2),
        ACTIVOS(2),
        IPV(2),
        //
        //Contabilidad
        //
        VENTAS(3),
        CUENTAS_CONTABLES(4),
        PRESUPUESTO(4),
        COMENZAR_VENTAS(0),
        //
        //TRABAJADORES
        //

        TRABAJADORES(4),
        PUESTOS_TRABAJO(4),
        NOMINAS(3),
        //
        //CONFIGURACION
        //

        COPIA_SEG(4),
        LICENCIA(0),
        CONFIGURACION(5);

        private final int nivelMinimoAcceso;

        private MenuButtons(int nivelMinimoAcceso) {
            this.nivelMinimoAcceso = nivelMinimoAcceso;
        }

        public int getNivelMinimoAcceso() {
            return nivelMinimoAcceso;
        }

    }

}

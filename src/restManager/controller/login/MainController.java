/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.login;

import GUI.Views.login.MainView;
import GUI.copiaSegView;
import java.awt.Container;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import restManager.controller.AbstractController;
import restManager.controller.AbstractDialogController;
import restManager.controller.Licence.Licence;
import restManager.controller.Licence.LicenceController;
import restManager.controller.almacen.ActivoFijoController;
import restManager.controller.almacen.AlmacenListController;
import restManager.controller.almacen.IPVController;
import restManager.controller.areaventa.AreaVentaController;
import restManager.controller.configuracion.ConfiguracionController;
import restManager.controller.gastos_pagos.CuentaController;
import restManager.controller.puntoelaboracion.PuntoElaboracionListController;
import restManager.controller.insumo.InsumoListController;
import restManager.controller.productoventa.ProductoVentaListController;
import restManager.controller.seccion.CartaListController;
import restManager.controller.trabajadores.NominasController;
import restManager.controller.trabajadores.PersonalListController;
import restManager.controller.trabajadores.PuestoTrabajoListController;
import restManager.controller.venta.VentaDetailController;
import restManager.controller.venta.VentaListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.ExceptionHandler;
import restManager.exceptions.UnauthorizedAccessException;
import restManager.persistencia.Personal;
import restManager.persistencia.Venta;
import restManager.persistencia.models.PersonalDAO;
import restManager.persistencia.models.VentaDAO;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class MainController extends AbstractDialogController<Personal> {

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

    private AbstractController comenzarVentas() throws IllegalArgumentException{
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.login;

import GUI.Views.AbstractView;
import GUI.Views.login.MainView;
import GUI.Views.seccion.CartaSeccionView;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.HeadlessException;
import java.text.ParseException;
import javax.swing.JOptionPane;
import restManager.controller.AbstractController;
import restManager.controller.AbstractDialogController;
import restManager.controller.almacen.AlmacenListController;
import restManager.controller.almacen.IPVController;
import restManager.controller.areaventa.AreaVentaController;
import restManager.controller.puntoelaboracion.PuntoElaboracionListController;
import restManager.controller.insumo.InsumoListController;
import restManager.controller.productoventa.ProductoVentaListController;
import restManager.controller.seccion.CartaListController;
import restManager.controller.seccion.SeccionListController;
import restManager.controller.trabajadores.PersonalListController;
import restManager.controller.trabajadores.PuestoTrabajoListController;
import restManager.controller.venta.VentaDetailController;
import restManager.controller.venta.VentaListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.ExceptionHandler;
import restManager.exceptions.UnauthorizedAccessException;
import restManager.persistencia.Carta;
import restManager.persistencia.Negocio;
import restManager.persistencia.Personal;
import restManager.persistencia.Venta;
import restManager.persistencia.models.CartaDAO;
import restManager.persistencia.models.NegocioDAO;
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
        Negocio model = NegocioDAO.getInstance().find(1);
        R.REST_NAME = model.getNombre();
        R.MAIN_COIN = model.getMonedaPrincipal();
        R.COIN_SUFFIX = " " + model.getMonedaPrincipal();
    }

    public MainController(Personal loggedUser, AbstractView parentView) {
        this(loggedUser);
        constructView(parentView);
    }

    @Override
    public void constructView(Container parent) {
        setView(new MainView(this, (Dialog) parent, true));
        getView().setVisible(true);
    }

    public void actionButton(MenuButtons menuButtons) {
        AbstractController controller;
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
                case COMENZAR_VENTAS:
                    if (R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() > 3) {
                        String date = JOptionPane.showInputDialog(getView(), "Introduzca el dia a trabajar en el formato dd/mm/aa \n "
                                + "o deje la casilla en blanco para comenzar en el ultimo dia sin cerrar ", "Entrada", JOptionPane.QUESTION_MESSAGE);
                        if (date == null) {
                            controller = new VentaDetailController(getView());
                        } else {
                            try {
                                Venta v = VentaDAO.getInstance().find(R.DATE_FORMAT.parse(date));
                                if (v == null) {
                                    controller = new VentaDetailController(getView(), R.DATE_FORMAT.parse(date));
                                } else {
                                    controller = new VentaDetailController(v, getView());
                                }
                            } catch (ParseException ex) {
                                showErrorDialog(getView(), ex.getMessage());
                            }
                        }
                    } else {
                        controller = new VentaDetailController(getView());
                    }
                    break;
                default:
                    getView().setEnabled(true);
                    throw new DevelopingOperationException(getView());

            }
        } catch (Exception e) {
            ExceptionHandler.showExceptionToUser(e, getView());
            e.printStackTrace();
        }
        getView().setEnabled(true);
    }

    private void validate(Personal loggedUser, MenuButtons menuButtons) {
        if (!(loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() >= menuButtons.getNivelMinimoAcceso())) {
            throw new UnauthorizedAccessException(getView());
        }
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
        ALMACEN(3),
        INVENTARIO(3),
        IPV(3),
        //
        //Contabilidad
        //
        VENTA_RAPIDA(4),
        VENTAS(4),
        ARCHIVOS(4),
        PRESUPUESTO(4),
        COMENZAR_VENTAS(1),
        //
        //TRABAJADORES
        //

        TRABAJADORES(4),
        PUESTOS_TRABAJO(4),
        //
        //CONFIGURACION
        //

        COPIA_SEG(4);
        private final int nivelMinimoAcceso;

        private MenuButtons(int nivelMinimoAcceso) {
            this.nivelMinimoAcceso = nivelMinimoAcceso;
        }

        public int getNivelMinimoAcceso() {
            return nivelMinimoAcceso;
        }

    }

}

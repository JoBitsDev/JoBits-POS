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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import restManager.controller.AbstractController;
import restManager.controller.AbstractDialogController;
import restManager.controller.Licence.Licence;
import restManager.controller.Licence.LicenceController;
import restManager.controller.almacen.AlmacenListController;
import restManager.controller.almacen.IPVController;
import restManager.controller.areaventa.AreaVentaController;
import restManager.controller.puntoelaboracion.PuntoElaboracionListController;
import restManager.controller.insumo.InsumoListController;
import restManager.controller.productoventa.ProductoVentaListController;
import restManager.controller.seccion.CartaListController;
import restManager.controller.trabajadores.PersonalListController;
import restManager.controller.trabajadores.PuestoTrabajoListController;
import restManager.controller.venta.VentaDetailController;
import restManager.controller.venta.VentaListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.UnauthorizedAccessException;
import restManager.persistencia.Negocio;
import restManager.persistencia.Personal;
import restManager.persistencia.Venta;
import restManager.persistencia.models.ConfiguracionDAO;
import restManager.persistencia.models.NegocioDAO;
import restManager.persistencia.models.PersonalDAO;
import restManager.persistencia.models.VentaDAO;
import restManager.printservice.Impresion;
import restManager.printservice.Ticket;
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
        cargarConfiguracion();
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
                    if (R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() > R.NivelAcceso.ECONOMICO.getNivel()) {
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
                case COPIA_SEG:
                    copiaSegView seg = new copiaSegView(getView(), true);
                    break;
                case LICENCIA:
                    controller = new LicenceController(getView());
                    break;
                default:
                    getView().setEnabled(true);
                    throw new DevelopingOperationException(getView());

            }
        } catch (UnauthorizedAccessException | DevelopingOperationException e) {
            //  ExceptionHandler.showExceptionToUser(e, getView());
        }
        getView().setEnabled(true);
    }

    private boolean validate(Personal loggedUser, MenuButtons menuButtons) {
        if (!(loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() >= menuButtons.getNivelMinimoAcceso())) {
            throw new UnauthorizedAccessException(getView());
        }
        return true;
    }

    public Licence getEstadoLicencia() {
        LicenceController licence = new LicenceController();
        return licence.getLicence();
    }

    @Override
    public MainView getView() {
        return (MainView) super.getView(); //To change body of generated methods, choose Tools | Templates.
    }

    private void cargarConfiguracion() {
        Negocio model = NegocioDAO.getInstance().find(1);
        ConfiguracionDAO c = ConfiguracionDAO.getInstance();
        R.REST_NAME = model.getNombre();
        R.MAIN_COIN = model.getMonedaPrincipal();
        R.COIN_SUFFIX = " " + model.getMonedaPrincipal();
        R.COINCHANGE = c.find(R.SettingID.GENERAL_CAMBIO_MONEDA).getValor();
        R.VARIOS_TURNOS = c.find(R.SettingID.GENERAL_TURNOS_VARIOS).getValor() == 1;
        R.CAJERO_PERMISOS_ESPECIALES = c.find(R.SettingID.GENERAL_CAJERO_PERMISOS_ESP).getValor() == 1;
        R.CONSUMO_DE_LA_CASA_EN_ESTADISTICAS = c.find(R.SettingID.GENERAL_CONSUMO_CASA_ESTADISTICAL).getValor() == 1;
        Ticket.PAPER_LENGHT = c.find(R.SettingID.IMPRESION_TICKET_TAMANO_PAPEL).getValor();
        Ticket.LINE_CHAR = c.find(R.SettingID.IMPRESION_TICKET_CARACTER_SEPARADOR).getValorString().charAt(0);
        Impresion.SHOW_HEADER = c.find(R.SettingID.IMPRESION_TICKET_ENCABEZADO_RESTAURANTE).getValor() == 1;
        Impresion.PRINT_IN_CENTRAL_KITCHEN = c.find(R.SettingID.IMPRESION_IMPRIMIR_COCINA_CENTRAL).getValor() == 1;
        Impresion.PRINT_GASTOS_EN_AUTORIZOS = c.find(R.SettingID.IMPRESION_IMPRIMIR_GASTOS_AUTORIZOS).getValor() == 1;
        Impresion.IMPRIMIR_TICKET_COCINA = c.find(R.SettingID.IMPRESION_IMPRIMIR_TICKET_EN_COCINA).getValor() == 1;
        Impresion.cantidadCopias = c.find(R.SettingID.IMPRESION_CANTIDAD_COPIAS).getValor();
        Impresion.REDONDEO_POR_EXCESO = c.find(R.SettingID.IMPRESION_REDONDEO_EXCESO).getValor() == 1;
        Impresion.SHOW_SUBTOTAL = c.find(R.SettingID.IMPRESION_TICKET_SUBTOTAL).getValor() == 1;
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
        INVENTARIO(2),
        IPV(2),
        //
        //Contabilidad
        //
        VENTAS(3),
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

        COPIA_SEG(4),
        LICENCIA(0);

        private final int nivelMinimoAcceso;

        private MenuButtons(int nivelMinimoAcceso) {
            this.nivelMinimoAcceso = nivelMinimoAcceso;
        }

        public int getNivelMinimoAcceso() {
            return nivelMinimoAcceso;
        }

    }

}

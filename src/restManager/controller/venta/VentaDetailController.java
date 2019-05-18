/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.venta;

import GUI.Views.util.CalcularCambioView;
import GUI.Views.venta.VentasCreateEditView;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import restManager.controller.AbstractDetailController;
import restManager.controller.almacen.IPVController;
import restManager.controller.login.LogInController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Cocina;
import restManager.persistencia.Control.VentaDAO1;
import restManager.persistencia.GastoVenta;
import restManager.persistencia.IpvRegistro;
import restManager.persistencia.Orden;
import restManager.persistencia.Personal;
import restManager.persistencia.ProductoInsumo;
import restManager.persistencia.ProductovOrden;
import restManager.persistencia.Venta;
import restManager.persistencia.models.CocinaDAO;
import restManager.persistencia.models.OrdenDAO;
import restManager.persistencia.models.PersonalDAO;
import restManager.persistencia.models.VentaDAO;
import restManager.printservice.Impresion;
import restManager.resources.R;
import restManager.util.comun;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class VentaDetailController extends AbstractDetailController<Venta> {

    Date fechaFin = null;
    OrdenController ordController;
    private VentasCreateEditView vi;
    int turnoActivo = 0;

    public VentaDetailController() {
        super(VentaDAO.getInstance());
        OrdenDAO.getInstance().addPropertyChangeListener(this);
    }

    public VentaDetailController(Venta instance) {
        super(instance, VentaDAO.getInstance());
        OrdenDAO.getInstance().addPropertyChangeListener(this);
    }

    public VentaDetailController(Window parent) {
        super(parent, VentaDAO.getInstance());
        OrdenDAO.getInstance().addPropertyChangeListener(this);
    }

    public VentaDetailController(Window parent, Date diaVentas) {
        super(VentaDAO.getInstance());
        this.parent = parent;
        OrdenDAO.getInstance().addPropertyChangeListener(this);
        instance = getDiaDeVenta(diaVentas);
        state = State.CREATING;
        constructView(parent);
    }

    public VentaDetailController(Date diaVentas) {
        super(VentaDAO.getInstance());
        OrdenDAO.getInstance().addPropertyChangeListener(this);
        instance = getDiaDeVenta(diaVentas);
        state = State.CREATING;
    }

    public VentaDetailController(Venta instance, Window parent) {
        super(instance, parent, VentaDAO.getInstance());
        OrdenDAO.getInstance().addPropertyChangeListener(this);
    }

    public VentaDetailController(Venta instance, Window parent, Date fechafin) {
        super(VentaDAO.getInstance());
        setParent(parent);
        setInstance(instance);
        this.fechaFin = fechafin;
        OrdenDAO.getInstance().addPropertyChangeListener(this);
        constructView(parent);
    }

    @Override
    public Venta createNewInstance() {
        return getDiaDeVenta(null);
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        vi = new VentasCreateEditView(getInstance(), this, (JDialog) parent, fechaFin);
        setView(vi);
        getView().updateView();
        getView().setVisible(true);
    }

    public void updateOrdenDialog(Orden objectAtSelectedRow) {
        if (ordController == null) {
            ordController = new OrdenController(objectAtSelectedRow, vi.getjPanelDetailOrdenes());

        } else {
            ordController.setInstance(objectAtSelectedRow);
        }
    }

    public void createNewOrden() {
        boolean nil = ordController == null;
        Orden newOrden;
        if (nil) {
            ordController = new OrdenController(getInstance());
            newOrden = ordController.getInstance();
        } else {
            newOrden = ordController.createNewInstance();
        }
        super.getInstance().getOrdenList().add(newOrden);
        ordController.create(newOrden, true);
        if (nil) {
            ordController = new OrdenController(newOrden, vi.getjPanelDetailOrdenes());
        } else {
            ordController.setInstance(newOrden);
        }
    }

    public List<Orden> getOrdenesActivas() {
        if (R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() <= 2) {
            return VentaDAO1.getOrdenesActivas(getInstance());
        } else {
            return getInstance().getOrdenList();
        }
    }

    public void fetchNewDataFromServer(int turnoTrabajo) {
        turnoActivo = turnoTrabajo;
        getModel().getEntityManager().refresh(getInstance());
        vi.updateView();
    }

    public void terminarVentas() {

        if (showConfirmDialog(vi, "¿Desea terminar el día de trabajo?")) {
            float ventaTotal = 0,
                    ventasGastosEnInsumos = 0,
                    ventasGastosGastos = 0,
                    ventasPagoTrabajadores = 0;

            getModel().getEntityManager().refresh(super.getInstance());
            for (Orden x : super.getInstance().getOrdenList()) {
                if (x.getHoraTerminada() == null) {
                    showErrorDialog(vi, "Existen tickets sin cerrar. Cierre los tickets antes de terminar la venta");
                    return;
                }
                if (!x.getDeLaCasa()) {
                    ventaTotal += x.getOrdenvalorMonetario();
                }
                ventasGastosEnInsumos += x.getOrdengastoEninsumos();

            }
            for (GastoVenta x : super.getInstance().getGastoVentaList()) {
                ventasGastosGastos += x.getImporte();
            }
            super.getInstance().setVentaTotal((double) ventaTotal);
            super.getInstance().setVentagastosEninsumos((double) ventasGastosEnInsumos);
            super.getInstance().setVentagastosGastos(ventasGastosGastos);
            update(super.getInstance());

        }
    }

    public void calcularCambio(Orden objectAtSelectedRow) {
        CalcularCambioView cc = new CalcularCambioView(getView(), true, objectAtSelectedRow);
    }

    public Venta getDiaDeVenta(Date fecha) {
        Venta ret;
        if (fecha == null) {
            //revisar si hay un dia sin cerrar
            List<Venta> ventas = getItems();
            for (int i = ventas.size() - 1; i >= 0; i--) {
                if (ventas.get(i).getVentaTotal() == null) {
                    return ventas.get(i);
                }
            }

            //revisar si ya el dia esta creado pero no terminado
            ret = VentaDAO.getInstance().find(new Date());
            if (ret != null) {
                new IPVController().inicializarIpvs(ret.getFecha());
                return ret;
            }

            // crear el dia nuevo
            ret = new Venta();
            ret.setVentagastosEninsumos(0.0);
            ret.setOrdenList(new ArrayList<>());
            ret.setFecha(new Date());
            create(ret, true);
            new IPVController().inicializarIpvs(ret.getFecha());
            return ret;
        } else {
            //revisar si la fecha donde se quiere crear el dia ya esta creada
            ret = VentaDAO.getInstance().find(fecha);
            if (ret != null) {
                return ret;
            }
            // crear el dia con la fecha pasada por parametros
            ret = new Venta();
            ret.setFecha(fecha);
            ret.setVentagastosEninsumos(0.0);
            ret.setOrdenList(new ArrayList<>());
            create(ret, true);
            new IPVController().inicializarIpvs(ret.getFecha());
            return ret;
        }
    }

    public String getTotalVendido() {
        return comun.setDosLugaresDecimales(VentaDAO1.getValorTotalVentas(getInstance()));
    }

    public String getTotalGastadoInsumos() {
        return comun.setDosLugaresDecimales(VentaDAO1.getValorTotalGastosInsumo(getInstance()));
    }

    public List<Personal> getPersonalList() {
        return PersonalDAO.getInstance().findAll();
    }

    public List<Cocina> getCocinaList() {
        return CocinaDAO.getInstance().findAll();
    }

    public void printPersonalResumenRow(String user) {
        Personal p = PersonalDAO.getInstance().find(user);
        List<ProductovOrden> aux = VentaDAO1.getResumenVentasCamarero(getInstance(), p);
        Collections.sort(aux, (o1, o2) -> {
            return o1.getProductoVenta().getNombre().compareTo(o2.getProductoVenta().getNombre());
        });
        getImpresionInstance().printPersonalResumen(aux, p, getInstance().getFecha());

    }

    public void printZ() {
        Impresion.getDefaultInstance().printZ(getInstance());
    }

    public void printCocinaResumen(String codCocina) {
        Cocina c = CocinaDAO.getInstance().find(codCocina);
        List<ProductovOrden> aux = VentaDAO1.getResumenVentasCocina(getInstance(), c);
        Collections.sort(aux, (o1, o2) -> {
            return o1.getProductoVenta().getNombre().compareTo(o2.getProductoVenta().getNombre());
        });
        getImpresionInstance().printResumenPuntoElab(aux, c, getInstance().getFecha());
    }

    public Float getGastoTotalDeInsumo(IpvRegistro i) {
        float total = 0;
        for (Orden x : getInstance().getOrdenList()) {
            if (x.getHoraTerminada() != null) {
                for (ProductovOrden p : x.getProductovOrdenList()) {
                    if (p.getProductoVenta().getCocinacodCocina().equals(i.getIpv().getCocina())) {
                        for (ProductoInsumo in : p.getProductoVenta().getProductoInsumoList()) {
                            if (in.getInsumo().equals(i.getIpv().getInsumo())) {
                                total += p.getCantidad() * in.getCantidad();
                            }
                        }
                    }
                }
            }
        }
        return total;
    }

    public void printGastosCasa() {
        Impresion.getDefaultInstance().printResumenCasa(VentaDAO1.getResumenVentasCasa(getInstance()), getInstance().getFecha());
    }

    public void cerrarOrdenRapido() {
        if (ordController != null) {
            if (showConfirmDialog(getView(), "Desea enviar a cocina, cerrar y crear una nueva orden")) {
                ordController.enviarACocina();
                ordController.despachar();
                createNewOrden();
            }
        }

    }

    public String getTotalVendidoNeto() {
        return comun.setDosLugaresDecimales(VentaDAO1.getValorTotalVentasNeta(getInstance()));
    }

    @Override
    public Venta getInstance() {
        if (R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() >= 3) {
            return super.getInstance();
        } else {
            if (super.getInstance().getCambioTurno1() == null) {
                return super.getInstance();
            }else{
                turnoActivo = 2;
            }

        }

        ArrayList<Orden> ord = new ArrayList<>(super.getInstance().getOrdenList());
        Collections.sort(ord);
        Venta vent = null;
        String cod_orden_low;
        String cod_orden_high;
        switch (turnoActivo) {
            case 1:
                cod_orden_low = ord.get(0).getCodOrden();
                cod_orden_high = super.getInstance().getCambioTurno1();
                break;
            case 2:
                cod_orden_low = super.getInstance().getCambioTurno1();
                cod_orden_high = ord.get(ord.size() - 1).getCodOrden();
                break;
            default:
                cod_orden_low = ord.get(0).getCodOrden();
                cod_orden_high = ord.get(ord.size() - 1).getCodOrden();
                break;
        }
        int cod_orden_low_pos = 0,
                cod_orden_high_pos = ord.size();

        for (int i = 0; i < ord.size(); i++) {
            if (ord.get(i).getCodOrden().equals(cod_orden_low)) {
                cod_orden_low_pos = turnoActivo == 2 ? i + 1 : i;
            }
            if (ord.get(i).getCodOrden().equals(cod_orden_high)) {
                cod_orden_high_pos = i+1;
            }
        }
        vent = VentaDAO.getInstance().find(super.getInstance().getFecha());
        if (vent != null) {
            vent.setOrdenList(ord.subList(cod_orden_low_pos, cod_orden_high_pos));
        }
        return vent == null ? super.getInstance() : vent;
    }

    public void cambiarTurno() {
        //getModel().getEntityManager().refresh(super.getInstance());
        if (showConfirmDialog(getView(), "Seguro desea cambiar el turno. Esta accion no se puede deshacer")) {

            ArrayList<Orden> ord = new ArrayList<>(super.getInstance().getOrdenList());
            Collections.sort(ord);
            for (Orden x : ord) {
                if (x.getHoraTerminada() == null) {
                    showErrorDialog(getView(), "No se puede cambiar turno con ordenes abiertas");
                    return;
                }
            }

            super.getInstance().setCambioTurno1(ord.get(ord.size() - 1).getCodOrden());
            update(super.getInstance(), true);
            showSuccessDialog(getView(), "Turno cambiado exitosamente");
            fetchNewDataFromServer(2);
        }

    }

    private Impresion getImpresionInstance() {
        Impresion i = Impresion.getDefaultInstance();
        i.setSHOW_PRICES(showConfirmDialog(
                getView(), "Presione SI para imprimir los valores,\nNo para imprimir solo las cantidades"));
        return i;
    }

    public void printPagoPorVentaPersonal(String user) {
        Impresion i = getImpresionInstance();
        i.prinPagoPorVenta(getInstance(), user);
    }

    public void mostrarVenta(int turnoVenta) {
        switch (turnoVenta) {
            case 0:
            case 1:
            case 2:
                fetchNewDataFromServer(turnoVenta);
                break;
            default:
                fetchNewDataFromServer(0);
                break;
        }
    }

}

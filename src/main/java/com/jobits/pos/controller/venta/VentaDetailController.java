/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.venta;

import com.jobits.pos.adapters.repo.VentaDAO;
import com.jobits.pos.adapters.repo.AreaDAO;
import com.jobits.pos.adapters.repo.CocinaDAO;
import com.jobits.pos.adapters.repo.OrdenDAO;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.AsistenciaPersonal;
import com.jobits.pos.domain.models.GastoVenta;
import com.jobits.pos.domain.models.Orden;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.IpvRegistro;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.PuestoTrabajo;
import com.jobits.pos.domain.models.ProductovOrden;
import com.jobits.pos.ui.utils.CalcularCambioView;
import com.jobits.pos.ui.utils.LongProcessAction;
import com.jobits.pos.ui.venta.VentasCreateEditView;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobits.pos.adapters.repo.autenticacion.PersonalDAO;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.licencia.Licence;
import com.jobits.pos.controller.licencia.LicenceController;
import com.jobits.pos.controller.almacen.IPVController;
import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.controller.trabajadores.AsistenciaPersonalController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.exceptions.UnauthorizedAccessException;
import com.jobits.pos.domain.VentaDAO1;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.utils.utils;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class VentaDetailController extends AbstractDetailController<Venta> {

    Date fechaFin = null;
    private OrdenController ordController;
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
        super(VentaDAO.getInstance());
        this.parent = parent;
        OrdenDAO.getInstance().addPropertyChangeListener(this);
        new LongProcessAction() {
            @Override
            protected void longProcessMethod() {
                instance = initDiaVentas(null);
            }
        }.performAction(parent);
        state = State.CREATING;
        constructView(parent);
    }

    public VentaDetailController(Window parent, Date diaVentas) {
        super(VentaDAO.getInstance());
        this.parent = parent;
        OrdenDAO.getInstance().addPropertyChangeListener(this);
        new LongProcessAction() {
            @Override
            protected void longProcessMethod() {
                instance = initDiaVentas(diaVentas);
            }
        }.performAction(parent);
        state = State.CREATING;
        constructView(parent);
    }

    public VentaDetailController(Venta instance, Window parent) {
        super(instance, parent, VentaDAO.getInstance());
        OrdenDAO.getInstance().addPropertyChangeListener(this);
    }

    public VentaDetailController(Date diaVentas) {
        super(VentaDAO.getInstance());
        OrdenDAO.getInstance().addPropertyChangeListener(this);
        instance = initDiaVentas(diaVentas);
        state = State.CREATING;
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
        return initDiaVentas(null);
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        vi = new VentasCreateEditView(getInstance(), this, (JDialog) parent, fechaFin);
        if (getInstance().getCambioTurno1() != null) {
            turnoActivo = 2;
        }
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
        getView().updateView();
        ordController.create(newOrden, true);
        if (nil) {
            ordController = new OrdenController(newOrden, vi.getjPanelDetailOrdenes());
        } else {
            ordController.setInstance(newOrden);
        }
    }

    public List<Orden> getOrdenesActivas() {
        ArrayList<Orden> ret;
        if (R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() <= 2) {
            ret = new ArrayList<>(VentaDAO1.getOrdenesActivas(getInstance()));
        } else {
            ret = new ArrayList<>(getInstance().getOrdenList());
        }
        Collections.sort(ret);
        return ret;
    }

    public void fetchNewDataFromServer(int turnoTrabajo) {
        turnoActivo = turnoTrabajo;
        getModel().getEntityManager().refresh(getModel().find(getInstance().getFecha()));
        if (ordController != null) {
            if (ordController.getInstance() != null) {
                int index = getOrdenesActivas().indexOf(ordController.getInstance());
                ordController.setInstance(getOrdenesActivas().get(index));
            }
        }
        vi.updateView();
    }

    public void terminarVentas() {

        if (showConfirmDialog(vi, "¿Desea terminar el día de trabajo?")) {
            float ventaTotal = 0,
                    ventasGastosEnInsumos = 0,
                    ventasGastosGastos = 0,
                    ventasPagoTrabajadores = 0;
            Venta v = getModel().getEntityManager().find(Venta.class, super.getInstance().getFecha());
            getModel().getEntityManager().refresh(v);
            for (Orden x : super.getInstance().getOrdenList()) {
                if (x.getHoraTerminada() == null) {
                    showErrorDialog(vi, "Existen tickets sin cerrar (" + x + "). Cierre los tickets antes de terminar la venta");
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
            for (AsistenciaPersonal x : super.getInstance().getAsistenciaPersonalList()) {
                ventasPagoTrabajadores += x.getPago();
            }
            super.getInstance().setVentaTotal((double) ventaTotal);
            super.getInstance().setVentagastosEninsumos((double) ventasGastosEnInsumos);
            super.getInstance().setVentagastosGastos(ventasGastosGastos);
            super.getInstance().setVentagastosPagotrabajadores(ventasPagoTrabajadores);
            update(super.getInstance());

        }
    }

    public void calcularCambio(Orden objectAtSelectedRow) {
        CalcularCambioView cc = new CalcularCambioView(getView(), true, objectAtSelectedRow);
    }

    public Venta initDiaVentas(Date fecha) {
        Venta v = getDiaDeVenta(fecha);
        return v;
    }

    public void initIPV(Venta v) {
        new LongProcessAction("Creando IPVs.........") {
            @Override
            protected void longProcessMethod() {
                new IPVController().inicializarExistencias(v.getFecha());
                new IPVController().inicializarIpvs(v.getFecha());
            }
        }.performAction(getView());
    }

    public Venta getDiaDeVenta(Date fecha) {
        Venta ret;
        LicenceController licence = new LicenceController(Licence.TipoLicencia.APLICACION);
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
                return ret;
            }

            // crear el dia nuevo
            ret = new Venta();
            ret.setVentagastosEninsumos(0.0);
            ret.setVentapropina((float) 0);
            ret.setOrdenList(new ArrayList<>());
            ret.setFecha(new Date());
            create(ret, true);
            return ret;
        } else {

            //revisar si la fecha donde se quiere crear el dia excede el tiempo de licencia
            if (licence.getLicence().getFechaFinMilis() < fecha.toInstant().toEpochMilli()) {
                throw new UnauthorizedAccessException(getView(), "No se pueden crear ventas mas alla de la fecha fin de la licencia");
            }
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
            return ret;
        }
    }

    public String getTotalVendido() {
        return utils.setDosLugaresDecimales(VentaDAO1.getValorTotalVentas(getInstance()));
    }

    public String getTotalGastadoInsumos() {
        return utils.setDosLugaresDecimales(VentaDAO1.getValorTotalGastosInsumo(getInstance()));
    }

    public String getTotalPagoTrabajadores() {
        return utils.setDosLugaresDecimales(VentaDAO1.getValorTotalPagoTrabajadores(getInstance()));
    }

    public List<Personal> getPersonalList() {
        return PersonalDAO.getInstance().findAll();
    }

    public List<Cocina> getCocinaList() {
        return CocinaDAO.getInstance().findAll();
    }

    public void printPersonalResumenRow(Personal p) {
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

    public float getVentaTotalDelProducto(ProductoVenta productoVenta) {
        float total = 0;
        for (Orden x : getInstance().getOrdenList()) {
            if (x.getHoraTerminada() != null && !x.getDeLaCasa()) {
                for (ProductovOrden p : x.getProductovOrdenList()) {
                    if (productoVenta.equals(p.getProductoVenta())) {
                        total += p.getCantidad();
                    }
                }
            }
        }
        return total;
    }

    public float getAutorizosTotalDelProducto(ProductoVenta productoVenta) {
        float total = 0;
        for (Orden x : getInstance().getOrdenList()) {
            if (x.getDeLaCasa() && x.getHoraTerminada() != null) {
                for (ProductovOrden p : x.getProductovOrdenList()) {
                    if (productoVenta.equals(p.getProductoVenta())) {
                        total += p.getCantidad();
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
        return utils.setDosLugaresDecimales(VentaDAO1.getValorTotalVentasNeta(getInstance()));
    }

    @Override
    public Venta getInstance() {
        Venta vent = super.getInstance();
        //si es admin ve la venta general
        if (R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() >= 3 && turnoActivo == 0) {
            return vent;
        } else {
            //sino a cambiado de turno ve la venta general
            if (vent.getCambioTurno1() == null) {
                if (R.loggedUser.getPuestoTrabajonombrePuesto().getAreacodArea() != null) {
                    for (int i = 0; i < vent.getOrdenList().size();) {
                        if (!vent.getOrdenList().get(i).getMesacodMesa().getAreacodArea().
                                equals(R.loggedUser.getPuestoTrabajonombrePuesto().getAreacodArea())) {
                            vent.getOrdenList().remove(i);
                        } else {
                            i++;
                        }
                    }
                }
                return vent;
            } else {
                if (turnoActivo == 0) {
                    turnoActivo = 2;
                }
            }

        }

        //ordenamos la venta por numero de orden
        getModel().getEntityManager().refresh(vent);
        ArrayList<Orden> ord = new ArrayList<>(vent.getOrdenList());
        Collections.sort(ord);
        String cod_orden_low;
        String cod_orden_high;
        //depende el turno activo fraccionamos la venta segun el turno.
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

        //escogemos la posicion final del turno
        for (int i = 0; i < ord.size(); i++) {
            if (ord.get(i).getCodOrden().equals(cod_orden_low)) {
                cod_orden_low_pos = turnoActivo == 2 ? i + 1 : i;
            }
            if (ord.get(i).getCodOrden().equals(cod_orden_high)) {
                cod_orden_high_pos = i + 1;
            }
        }
        // creamos una venta virtual para separar los turnos de trabajo
        vent = VentaDAO.getInstance().find(super.getInstance().getFecha());
        if (vent != null) {
            vent.setOrdenList(ord.subList(cod_orden_low_pos, cod_orden_high_pos));
            if (R.loggedUser.getPuestoTrabajonombrePuesto().getAreacodArea() != null) {
                for (int i = 0; i < vent.getOrdenList().size();) {
                    if (!vent.getOrdenList().get(i).getMesacodMesa().getAreacodArea().
                            equals(R.loggedUser.getPuestoTrabajonombrePuesto().getAreacodArea())
                            && R.loggedUser.getPuestoTrabajonombrePuesto().getAreacodArea() != null) {
                        vent.getOrdenList().remove(i);
                    } else {
                        i++;
                    }
                }

            }
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

    public void printPagoPorVentaPersonal(Personal user) {
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

    public Float getPagoTrabajador(Personal personal) {
        float pago = 0;
        PuestoTrabajo p = personal.getPuestoTrabajonombrePuesto();
        Venta v = getInstance();
        float totalVentas;
        if (p.getAreaPago() != null) {
            totalVentas = VentaDAO1.getValorVentasCocina(v, CocinaDAO.getInstance().find(p.getAreaPago()));
        } else {
            totalVentas = VentaDAO1.getValorTotalVentas(v);
        }

        pago += p.getSalarioFijo();
        if (totalVentas > p.getAPartirDe()) {
            pago += (totalVentas - p.getAPartirDe()) * (p.getSalarioPorcientoDeArea() / 100);
            totalVentas -= p.getAPartirDe();
        }
        pago += (p.getSalarioPorcientoVentaTotal() * totalVentas) / 100;

        if (personal.getPuestoTrabajonombrePuesto().getPagoPorVentas() != null) {
            if (personal.getPuestoTrabajonombrePuesto().getPagoPorVentas()) {
                int personalTrabajandoPorVentas = 0;
                for (AsistenciaPersonal x : new AsistenciaPersonalController().getPersonalTrabajando(getInstance())) {
                    if (x.getPersonal().getPuestoTrabajonombrePuesto().getPagoPorVentas()) {
                        personalTrabajandoPorVentas++;
                    }
                }
                pago += VentaDAO1.getValorPagoPorVentas(getInstance()) / personalTrabajandoPorVentas;
            }
        }

        return utils.setDosLugaresDecimalesFloat(pago);

    }

    public String getTotalAutorizos() {
        float ret = 0;
        for (ProductovOrden x : VentaDAO1.getResumenVentasCasa(getInstance())) {
            ret += x.getCantidad() * x.getProductoVenta().getPrecioVenta();
        }
        return utils.setDosLugaresDecimales(ret);
    }

    public List<Area> getAreaList() {
        return AreaDAO.getInstance().findAll();
    }

    public void printAreaResumen(String string) {
        Area a = AreaDAO.getInstance().find(string);
        Impresion.getDefaultInstance().printResumenVentaArea(VentaDAO1.getResumenVentaPorArea(getInstance(), a), getInstance().getFecha());
    }

    public void setPropina(float value) {
        super.getInstance().setVentapropina(value);
        update(super.getInstance(), true);
    }

    public Float getPropinaTrabajador(Personal personal) {
        float ret = 0;
        Venta v = getInstance();
        if (personal.getPuestoTrabajonombrePuesto().getPropina()) {
            int personalTrabajandoPropina = 0;
            for (AsistenciaPersonal x : new AsistenciaPersonalController().getPersonalTrabajando(v)) {
                if (x.getPersonal().getPuestoTrabajonombrePuesto().getPropina()) {
                    personalTrabajandoPropina++;
                }
            }

            if (personal.getPuestoTrabajonombrePuesto().getPropina()) {
                if (v.getVentapropina() != null) {
                    ret += v.getVentapropina() / personalTrabajandoPropina;
                }
            }

        }
        return utils.setDosLugaresDecimalesFloat(ret);
    }

    public void reabrirVentas() {
        if (new LogInController().constructoAuthorizationView(getView(), R.NivelAcceso.ECONOMICO)) {
            Calendar limitTime = Calendar.getInstance();
            limitTime.add(Calendar.DAY_OF_YEAR, -1);
            limitTime.set(Calendar.HOUR_OF_DAY, 0);
            limitTime.set(Calendar.MINUTE, 0);
            limitTime.set(Calendar.SECOND, 0);
            limitTime.set(Calendar.MILLISECOND, 0);
            if (super.getInstance().getFecha().getTime() < limitTime.getTimeInMillis()) {
                showErrorDialog(getView(), "La venta solo se puede reabrir en el margen de 1 dia laboral. No antes");
                return;
            }
            Venta v = super.getInstance();
            v.setVentaTotal(null);
            update(v, true);
            showSuccessDialog(getView());

        }
    }

    public boolean terminarYExportar(File file) {
        terminarVentas();
        try {
            new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValue(file, getInstance());
            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            showErrorDialog(getView(), "Ocurrio un error exportando la venta.");
            return false;
        }
    }

    public boolean importarVenta(File file) {

        try {
            Venta ret = new ObjectMapper().readValue(file, Venta.class);
            getModel().startTransaction();
            if (getModel().find(ret.getFecha()) == null) {
                create(ret);
            } else {
                update(ret);
            }
            getModel().commitTransaction();
            IPVController ipvController = new IPVController();
            ipvController.recalcularExistencias(ret.getFecha());
            ipvController.recalcularIpvRegistros(ret);
            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            showErrorDialog(getView(), "Ocurrio un error importando la venta.");
            return false;
        }
    }

}

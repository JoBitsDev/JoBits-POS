/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.venta;

import com.jobits.pos.adapters.repo.impl.VentaDAO;
import com.jobits.pos.adapters.repo.impl.AreaDAO;
import com.jobits.pos.adapters.repo.impl.CocinaDAO;
import com.jobits.pos.adapters.repo.impl.OrdenDAO;
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
import com.jobits.pos.ui.utils.LongProcessActionServiceImpl;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobits.pos.adapters.repo.autenticacion.PersonalDAO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.licencia.Licence;
import com.jobits.pos.controller.licencia.LicenceController;
import com.jobits.pos.controller.almacen.IPVController;
import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.controller.trabajadores.AsistenciaPersonalController;
import com.jobits.pos.exceptions.UnauthorizedAccessException;
import com.jobits.pos.domain.VentaDAO1;
import com.jobits.pos.main.Application;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.servicios.impresion.formatter.CasaFormatter;
import com.jobits.pos.servicios.impresion.formatter.PagoPorVentaFormatter;
import com.jobits.pos.servicios.impresion.formatter.PersonalResumenFormatter;
import com.jobits.pos.servicios.impresion.formatter.PuntoElaboracionFormatter;
import com.jobits.pos.servicios.impresion.formatter.ResumenVentaAreaFormatter;
import com.jobits.pos.servicios.impresion.formatter.VentaZFormatter;
import com.jobits.pos.ui.utils.utils;
import com.jobits.pos.ui.venta.presenter.ResumenVentaAreaTablaModel;
import com.jobits.pos.ui.venta.presenter.ResumenVentaPtoElabTablaModel;
import com.jobits.pos.ui.venta.presenter.ResumenVentaUsuarioTablaModel;
import javax.swing.JOptionPane;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class VentaDetailController extends AbstractDetailController<Venta>
        implements VentaDetailService {

    Date fechaFin = null;
    private OrdenService ordController;
    int turnoActivo = 0;

    public VentaDetailController() {
        super(VentaDAO.getInstance());
        Application.getInstance().getBackgroundWorker().processInBackground(() -> {
            instance = initDiaVentas(null);
        });
        OrdenDAO.getInstance().addPropertyChangeListener(this);
        this.ordController = new OrdenController();

    }

    public VentaDetailController(Venta instance) {//TODO aqui se pudiera crear el constructor del orden controller sin pasarlo por parametro
        super(instance, VentaDAO.getInstance());
        this.ordController = new OrdenController();
        OrdenDAO.getInstance().addPropertyChangeListener(this);
        if (getInstance().getCambioTurno1() != null) {
            turnoActivo = 2;
        }

    }

    public VentaDetailController(Date diaVentas) {
        super(VentaDAO.getInstance());
        OrdenDAO.getInstance().addPropertyChangeListener(this);
        Application.getInstance().getBackgroundWorker().processInBackground(() -> {
            instance = initDiaVentas(diaVentas);

        });
        this.ordController = new OrdenController();
        if (getInstance().getCambioTurno1() != null) {
            turnoActivo = 2;
        }

    }

    public VentaDetailController(Venta instance, Date fechafin) {
        super(VentaDAO.getInstance());
        setInstance(instance);
        this.fechaFin = fechafin;
        OrdenDAO.getInstance().addPropertyChangeListener(this);
        if (getInstance().getCambioTurno1() != null) {
            turnoActivo = 2;
        }

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
    }

    //TODO: borrar despues de verificar
//    public void updateOrdenDialog(Orden objectAtSelectedRow) {
//        if (ordController == null) {
//            //  ordController = new OrdenController(objectAtSelectedRow, vi.getjPanelDetailOrdenes());
//
//        } else {
//          //  ordController.setInstance(objectAtSelectedRow);
//        }
//    }
    @Override
    public Orden createNewOrden() {
        boolean nil = ordController == null;
        Orden newOrden;
        if (nil) {
            ordController = new OrdenController();
        }
        //TODO: aqui se sigue sin manejar el numero de la mesa en los controllers y no en las vistas
        newOrden = ordController.createNewInstance(null, R.DATE_FORMAT.format(getInstance().getFecha()));//TODO: ver que parametros hacen falta para la nueva orden
        super.getInstance().getOrdenList().add(newOrden);
        return newOrden;
    }

    @Override
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
//        if (ordController != null) { //TODO: borrar luego de verificar
//            if (ordController.getInstance() != null) {
//                int index = getOrdenesActivas().indexOf(ordController.getInstance());
//                //   ordController.setInstance(getOrdenesActivas().get(index));TODO: borrar despues de verificar
//            }
//        }
        //vi.updateView();
    }

    public boolean terminarVentas() {

        if (showConfirmDialog(null, "¿Desea terminar el día de trabajo?")) {
            float ventaTotal = 0,
                    ventasGastosEnInsumos = 0,
                    ventasGastosGastos = 0,
                    ventasPagoTrabajadores = 0;
            Venta v = getModel().getEntityManager().find(Venta.class, super.getInstance().getFecha());
            getModel().getEntityManager().refresh(v);
            for (Orden x : super.getInstance().getOrdenList()) {
                if (x.getHoraTerminada() == null) {
                    showErrorDialog(null, "Existen tickets sin cerrar (" + x + "). Cierre los tickets antes de terminar la venta");
                    return false;
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
            if (!super.getInstance().getOrdenList().isEmpty()) {
                super.getInstance().setCambioTurno1(super.getInstance().getOrdenList().get(super.getInstance().getOrdenList().size() - 1).getCodOrden());
            }
            super.getInstance().setVentaTotal((double) ventaTotal);
            super.getInstance().setVentagastosEninsumos((double) ventasGastosEnInsumos);
            super.getInstance().setVentagastosGastos(ventasGastosGastos);
            super.getInstance().setVentagastosPagotrabajadores(ventasPagoTrabajadores);
            update(super.getInstance());

            return true;
        }
        return false;
    }

    public void calcularCambio(Orden objectAtSelectedRow) {
        CalcularCambioView cc = new CalcularCambioView(getView(), true, objectAtSelectedRow);
    }

    public Venta initDiaVentas(Date fecha) {
        Venta v = getDiaDeVenta(fecha);
        return v;
    }

    public void initIPV(Venta v) {
        new LongProcessActionServiceImpl("Creando IPVs.........") {
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

    /**
     *
     * @param p
     */
    @Override
    public void printPersonalResumenRow(Personal p) {
        List<ProductovOrden> aux = VentaDAO1.getResumenVentasCamarero(getInstance(), p);
        Collections.sort(aux, (o1, o2) -> {
            return o1.getProductoVenta().getNombre().compareTo(o2.getProductoVenta().getNombre());
        });
        getImpresionInstance().print(new PersonalResumenFormatter(aux, p, getInstance().getFecha()), null);

    }

    @Override
    public void printZ() {
        Impresion.getDefaultInstance().print(new VentaZFormatter(getInstance()), null);
    }

    public void printCocinaResumen(String codCocina) {
        Cocina c = CocinaDAO.getInstance().find(codCocina);
        List<ProductovOrden> aux = VentaDAO1.getResumenVentasCocina(getInstance(), c);
        Collections.sort(aux, (o1, o2) -> {
            return o1.getProductoVenta().getNombre().compareTo(o2.getProductoVenta().getNombre());
        });
        getImpresionInstance().print(new PuntoElaboracionFormatter(aux, c, getInstance().getFecha()), c.getNombreCocina());
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
        Impresion.getDefaultInstance().print(new CasaFormatter(VentaDAO1.getResumenVentasCasa(getInstance()), getInstance().getFecha()), null);
    }

    public void cerrarOrdenRapido(String codOrden) {
        if (ordController != null) {
            if (showConfirmDialog(getView(), "Desea enviar a cocina, cerrar y crear una nueva orden")) {
                ordController.enviarACocina();
                ordController.cerrarOrden(codOrden);
                createNewOrden();
            }
        }

    }

    @Override
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

        int value = JOptionPane.showConfirmDialog(getView(), "Presione SI para imprimir los valores,\nNo para imprimir solo las cantidades",
                R.RESOURCE_BUNDLE.getString("label_confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/pregunta.png")));

        switch (value) {
            case JOptionPane.YES_OPTION:
                i.setSHOW_PRICES(true);
                break;
            case JOptionPane.NO_OPTION:
                i.setSHOW_PRICES(false);
                break;
            case JOptionPane.CLOSED_OPTION:
                throw new RuntimeException();
            default:
                break;
        }
        return i;
    }

    /**
     *
     * @param user
     */
    @Override
    public void printPagoPorVentaPersonal(Personal user) {
        Impresion i = getImpresionInstance();
        i.print(new PagoPorVentaFormatter(getInstance(), user.getUsuario()), null);
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

    @Override
    public void printAreaResumen(Area a) {
        Impresion.getDefaultInstance().print(new ResumenVentaAreaFormatter(
                VentaDAO1.getResumenVentaPorAreaOld(getInstance(), a), getInstance().getFecha()), null);
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

    @Override
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

    public List<ResumenVentaAreaTablaModel> getResumenPorAreaVenta() {
        List<ResumenVentaAreaTablaModel> ret = new ArrayList<>();
        for (Area a : getAreaList()) {
            ResumenVentaAreaTablaModel newResumen = VentaDAO1.getResumenVentaPorArea(getInstance(), a);
            if (newResumen != null) {
                ret.add(newResumen);
            }
        }
        return ret;
    }

    public List<ResumenVentaPtoElabTablaModel> getResumenPorPtoVenta() {
        List<ResumenVentaPtoElabTablaModel> ret = new ArrayList<>();
        for (Cocina c : getCocinaList()) {
            ResumenVentaPtoElabTablaModel newResumen = VentaDAO1.getResumenVentasCocinaOnTable(getInstance(), c);
            if (newResumen != null) {
                ret.add(newResumen);
            }
        }
        return ret;
    }

    public List<ResumenVentaUsuarioTablaModel> getResumenPorUsuarioVenta() {
        List<ResumenVentaUsuarioTablaModel> ret = new ArrayList<>();
        for (Personal p : getPersonalList()) {
            ResumenVentaUsuarioTablaModel newResumen = VentaDAO1.getResumenVentasCamareroB(getInstance(), p);
            if (newResumen != null) {
                ret.add(newResumen);
            }
        }
        return ret;
    }

    public boolean canReabrirVenta() {
        return getInstance().getCambioTurno1() != null;

    }

    public String getTotalGastos() {
        float total = 0;
        for (GastoVenta g : getInstance().getGastoVentaList()) {
            total += g.getImporte();
        }
        return utils.setDosLugaresDecimales(total);
    }

    public boolean canCambiarTurno() {
        return getInstance().getCambioTurno1() == null;

    }

    public float getTotalPropina() {
        if (getInstance().getVentapropina() != null) {
            return getInstance().getVentapropina();
        } else {
            return 0;
        }
    }

}

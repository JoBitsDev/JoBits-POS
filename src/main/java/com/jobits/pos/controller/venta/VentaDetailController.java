/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.venta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobits.pos.adapters.repo.autenticacion.PersonalDAO;
import com.jobits.pos.adapters.repo.impl.AreaDAO;
import com.jobits.pos.adapters.repo.impl.CocinaDAO;
import com.jobits.pos.adapters.repo.impl.ConfiguracionDAO;
import com.jobits.pos.adapters.repo.impl.OrdenDAO;
import com.jobits.pos.adapters.repo.impl.VentaDAO;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.almacen.IPVController;
import com.jobits.pos.controller.licencia.Licence;
import com.jobits.pos.controller.licencia.LicenceController;
import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.controller.reservas.ReservaListController;
import com.jobits.pos.controller.trabajadores.AsistenciaPersonalController;
import com.jobits.pos.domain.VentaDAO1;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.AsistenciaPersonal;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.GastoVenta;
import com.jobits.pos.domain.models.IpvRegistro;
import com.jobits.pos.domain.models.Orden;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.ProductovOrden;
import com.jobits.pos.domain.models.PuestoTrabajo;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.servicios.impresion.formatter.CasaFormatter;
import com.jobits.pos.servicios.impresion.formatter.PagoPorVentaFormatter;
import com.jobits.pos.servicios.impresion.formatter.PersonalResumenFormatter;
import com.jobits.pos.servicios.impresion.formatter.PuntoElaboracionFormatter;
import com.jobits.pos.servicios.impresion.formatter.ResumenVentaAreaFormatter;
import com.jobits.pos.servicios.impresion.formatter.VentaZFormatter;
import com.jobits.pos.ui.utils.LongProcessActionServiceImpl;
import com.jobits.pos.utils.utils;
import com.jobits.pos.domain.venta.ResumenVentaAreaTablaModel;
import com.jobits.pos.domain.venta.ResumenVentaPtoElabTablaModel;
import com.jobits.pos.domain.venta.ResumenVentaUsuarioTablaModel;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class VentaDetailController extends AbstractDetailController<Venta>
        implements VentaDetailService {

    private OrdenService ordController;
    Date fechaFin = null;

    public VentaDetailController() {
        super(VentaDAO.getInstance());
        OrdenDAO.getInstance().addPropertyChangeListener(this);

    }

    private VentaDetailController(Venta instance, Date fechafin) {
        super(VentaDAO.getInstance());
        setInstance(instance);
        this.fechaFin = fechafin;
        OrdenDAO.getInstance().addPropertyChangeListener(this);

    }

    @Deprecated
    @Override
    public void cambiarTurno(int codVenta) {
        throw new DevelopingOperationException();
//        if (showConfirmDialog(getView(), "Seguro desea cambiar el turno. Esta accion no se puede deshacer")) {
//
//            ArrayList<Orden> ord = new ArrayList<>(getInstance(codVenta).getOrdenList());
//            Collections.sort(ord);
//            for (Orden x : ord) {
//                if (x.getHoraTerminada() == null) {
//                    showErrorDialog(getView(), "No se puede cambiar turno con ordenes abiertas");
//                    return;
//                }
//            }
//
//            getInstance(codVenta).setCambioTurno1(ord.get(ord.size() - 1).getCodOrden());
//            update(getInstance(codVenta), true);
//            showSuccessDialog(getView(), "Turno cambiado exitosamente");
//            fetchNewDataFromServer(2);
//        }

    }

    @Override
    public Venta cambiarTurno(Venta fecha) {
        Venta v = null;
        terminarVentas(fecha.getId());
        v = inicializarVentas(fecha.getFecha(), true).get(0);

        return v;
    }

    @Override
    public boolean canOpenNuevoTurno(Date fecha) {
        return VentaDAO.getInstance().find(fecha).size() < ConfiguracionDAO.getInstance().
                find(R.SettingID.GENERAL_CANTIDAD_TURNOS).getValor();
    }

    @Override
    public boolean canReabrirVenta(int codVenta) {
        return getInstance(codVenta).getVentaTotal() != null;

    }

    @Override
    public void cerrarOrdenRapido(String codOrden, int codVenta) {
        if (ordController != null) {
            ordController.enviarACocina(codOrden);
            ordController.cerrarOrden(codOrden, true);
            createNewOrden(codVenta);
        }

    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
    }

    @Override
    public Venta createNewInstance() {
        return inicializarVentas(null, false).get(0);
    }

    @Override
    public Orden createNewOrden(int codVenta) {
        boolean nil = ordController == null;
        Orden newOrden;
        if (nil) {
            ordController = new OrdenController();
        }
        //TODO: aqui se sigue sin manejar el numero de la mesa en los controllers y no en las vistas
        newOrden = ordController.createNewInstance(null, R.DATE_FORMAT.format(getInstance(codVenta).getFecha()), getInstance(codVenta).getId());//TODO: ver que parametros hacen falta para la nueva orden
        getInstance(codVenta).getOrdenList().add(newOrden);
        return newOrden;
    }

    @Override
    public void fetchNewDataFromServer(int codVenta) {
        getModel().getEntityManager().refresh(getModel().find(codVenta));
    }

    @Override
    public List<Area> getAreaList() {
        return AreaDAO.getInstance().findAll();
    }

    @Override
    public float getAutorizosTotalDelProducto(ProductoVenta productoVenta, int codVenta) {
        float total = 0;
        for (Orden x : getInstance(codVenta).getOrdenList()) {
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

    @Override
    public List<Cocina> getCocinaList() {
        return CocinaDAO.getInstance().findAll();
    }

    @Override
    public Float getGastoTotalDeInsumo(IpvRegistro i, int codVenta) {
        float total = 0;
        for (Orden x : getInstance(codVenta).getOrdenList()) {
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

    @Override
    public Venta getInstance(int codVenta) {
        Venta vent = VentaDAO.getInstance().find(codVenta);
        //si es admin ve la venta general
        if (R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() >= 3) {
            return vent;
        } else {
            //sino a cambiado de turno ve la venta general
            if (R.loggedUser.getPuestoTrabajonombrePuesto().getAreacodArea() != null) {
                for (int i = 0; i < vent.getOrdenList().size();) {
                    if (vent.getOrdenList().get(i).getMesacodMesa() != null) {
                        if (!vent.getOrdenList().get(i).getMesacodMesa().getAreacodArea().
                                equals(R.loggedUser.getPuestoTrabajonombrePuesto().getAreacodArea())) {
                            vent.getOrdenList().remove(i);
                        }
                    } else {
                        i++;
                    }
                }
            }
            return vent;
        }
    }

    @Override
    public Venta getInstance() {
        throw new IllegalStateException("Bad Call a get instance");
    }

    @Override
    public List<Orden> getOrdenesActivas(int codVenta) {
        ArrayList<Orden> ret;
        if (R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() <= 2) {
            ret = new ArrayList<>(VentaDAO1.getOrdenesActivas(getInstance(codVenta)));
        } else {
            ret = new ArrayList<>(getInstance(codVenta).getOrdenList());
        }
        Collections.sort(ret);
        return ret;
    }

    @Override
    public Float getPagoTrabajador(Personal personal, int codVenta) {
        float pago = 0;
        PuestoTrabajo p = personal.getPuestoTrabajonombrePuesto();
        Venta v = getInstance(codVenta);
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
                for (AsistenciaPersonal x : new AsistenciaPersonalController().getPersonalTrabajando(getInstance(codVenta))) {
                    if (x.getPersonal().getPuestoTrabajonombrePuesto().getPagoPorVentas()) {
                        personalTrabajandoPorVentas++;
                    }
                }
                pago += VentaDAO1.getValorPagoPorVentas(getInstance(codVenta)) / personalTrabajandoPorVentas;
            }
        }
        return utils.setDosLugaresDecimalesFloat(pago);
    }

    @Override
    public List<Personal> getPersonalList() {
        return PersonalDAO.getInstance().findAll();
    }

    @Override
    public Float getPropinaTrabajador(Personal personal, int codVenta) {
        float ret = 0;
        Venta v = getInstance(codVenta);
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
    public List<ResumenVentaAreaTablaModel> getResumenPorAreaVenta(int codVenta) {
        List<ResumenVentaAreaTablaModel> ret = new ArrayList<>();
        for (Area a : getAreaList()) {
            ResumenVentaAreaTablaModel newResumen = VentaDAO1.getResumenVentaPorArea(getInstance(codVenta), a);
            if (newResumen != null) {
                ret.add(newResumen);
            }
        }
        return ret;
    }

    @Override
    public List<ResumenVentaPtoElabTablaModel> getResumenPorPtoVenta(int codVenta) {
        List<ResumenVentaPtoElabTablaModel> ret = new ArrayList<>();
        for (Cocina c : getCocinaList()) {
            ResumenVentaPtoElabTablaModel newResumen = VentaDAO1.getResumenVentasCocinaOnTable(getInstance(codVenta), c);
            if (newResumen != null) {
                ret.add(newResumen);
            }
        }
        return ret;
    }

    @Override
    public List<ResumenVentaUsuarioTablaModel> getResumenPorUsuarioVenta(int codVenta) {
        List<ResumenVentaUsuarioTablaModel> ret = new ArrayList<>();
        for (Personal p : getPersonalList()) {
            ResumenVentaUsuarioTablaModel newResumen = VentaDAO1.getResumenVentasCamareroB(getInstance(codVenta), p);
            if (newResumen != null) {
                ret.add(newResumen);
            }
        }
        return ret;
    }

    @Override
    public String getTotalAutorizos(int codVenta) {
        float ret = 0;
        for (ProductovOrden x : VentaDAO1.getResumenVentasCasa(getInstance(codVenta))) {
            ret += x.getCantidad() * x.getProductoVenta().getPrecioVenta();
        }
        return utils.setDosLugaresDecimales(ret);
    }

    @Override
    public String getTotalGastadoInsumos(int codVenta) {
        return utils.setDosLugaresDecimales(VentaDAO1.getValorTotalGastosInsumo(getInstance(codVenta)));
    }

    @Override
    public String getTotalGastos(int codVenta) {
        float total = 0;
        for (GastoVenta g : getInstance(codVenta).getGastoVentaList()) {
            total += g.getImporte();
        }
        return utils.setDosLugaresDecimales(total);
    }

    @Override
    public String getTotalPagoTrabajadores(int codVenta) {
        return utils.setDosLugaresDecimales(VentaDAO1.getValorTotalPagoTrabajadores(getInstance(codVenta)));
    }

    @Override
    public float getTotalPropina(int codVenta) {
        if (getInstance(codVenta).getVentapropina() != null) {
            return getInstance(codVenta).getVentapropina();
        } else {
            return 0;
        }
    }

    @Override
    public String getTotalVendido(int codVenta) {
        return utils.setDosLugaresDecimales(VentaDAO1.getValorTotalVentas(getInstance(codVenta)));
    }

    @Override
    public String getTotalVendidoNeto(int codVenta) {
        return utils.setDosLugaresDecimales(VentaDAO1.getValorTotalVentasNeta(getInstance(codVenta)));
    }

    @Override
    public float getVentaTotalDelProducto(ProductoVenta productoVenta, int codVenta) {
        float total = 0;
        for (Orden x : getInstance(codVenta).getOrdenList()) {
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

    @Override
    public void importarVenta(File file) {
        if (file == null) {
            throw new IllegalArgumentException("Seleccione un archivo a importar");
        }
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
            ipvController.recalcularExistencias(ret.getFecha(), ret.getId());
            ipvController.recalcularIpvRegistros(ret.getId());//TODO: arreglar esto
        } catch (IOException ex) {
            throw new IllegalStateException("Ocurrio un error importando la venta.");
        }
    }

    @Override
    public List<Venta> inicializarVentas(Date fecha, boolean nuevaVenta) {
        List<Venta> ret;
        if (nuevaVenta) {
            ret = crearVenta(fecha);
        } else {
            if (fecha == null) {
                ret = getDiaSinCerrar();
                if (ret.isEmpty()) {
                    List<Venta> listVentas = getVentasDeFecha(new Date());
                    if (listVentas.size() < R.SettingID.GENERAL_CANTIDAD_TURNOS.getIntegerValue()) {
                        ret = crearVenta(new Date());
                    } else {
                        ret = listVentas;
                    }
                }
            } else {
                //revisar si la fecha donde se quiere crear el dia ya esta creada
                ret = VentaDAO.getInstance().find(fecha);
                if (ret.isEmpty()) {
                    ret = crearVenta(fecha);
                }
            }
        }
        Collections.sort(ret, (Venta o1, Venta o2) -> {
            return o1.getId().compareTo(o2.getId());
        });
        switch (Application.getInstance().getLoggedUser().getPuestoTrabajonombrePuesto().getNivelAcceso()) {
            case 0:
            case 1:
            case 2:
                Venta v = ret.get(ret.size() - 1);
                ret.clear();
                ret.add(v);
                break;
            default:
                break;
        }
        return ret;
    }

    @Override
    public void initIPV(Venta v) {
        if (v != null) {
            new LongProcessActionServiceImpl("Creando IPVs.........") {
                @Override
                protected void longProcessMethod() {
                    new IPVController().inicializarExistencias(v.getId());
                    new IPVController().inicializarIpvs(v.getId());
                }
            }.performAction(getView());
        }
    }

    @Override
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

    @Override
    public void printAreaResumen(Area a, int codVenta) {
        if (a == null) {
            throw new IllegalArgumentException("El Area es nula");
        }
        Impresion.getDefaultInstance().print(new ResumenVentaAreaFormatter(
                VentaDAO1.getResumenVentaPorAreaOld(getInstance(codVenta), a), getInstance(codVenta).getFecha()), null);
    }

    @Override
    public void printCocinaResumen(String codCocina, int codVenta, boolean printValores) {
        Cocina c = CocinaDAO.getInstance().find(codCocina);
        List<ProductovOrden> aux = VentaDAO1.getResumenVentasCocina(getInstance(codVenta), c);
        Collections.sort(aux, (o1, o2) -> {
            return o1.getNombreProductoVendido().compareTo(o2.getNombreProductoVendido());
        });
        getImpresionInstance(printValores).print(new PuntoElaboracionFormatter(aux, c, getInstance(codVenta).getFecha()), c.getNombreCocina());
    }

    @Override
    public void printGastosCasa(int codVenta) {
        Impresion.getDefaultInstance().print(new CasaFormatter(VentaDAO1.getResumenVentasCasa(getInstance(codVenta)), getInstance(codVenta).getFecha()), null);
    }

    @Override
    public void printPagoPorVentaPersonal(Personal user, int codVenta, boolean printValores) {
        if (user == null) {
            throw new IllegalArgumentException("Personal nulo");
        }
        Impresion i = getImpresionInstance(printValores);
        i.print(new PagoPorVentaFormatter(getInstance(codVenta), user.getUsuario()), null);
    }

    @Override
    public void printPersonalResumenRow(Personal p, int codVenta, boolean printValores) {
        if (p == null) {
            throw new IllegalArgumentException("Personal nulo");
        }
        List<ProductovOrden> aux = VentaDAO1.getResumenVentasCamarero(getInstance(codVenta), p);
        Collections.sort(aux, (o1, o2) -> {
            return o1.getNombreProductoVendido().compareTo(o2.getNombreProductoVendido());
        });
        getImpresionInstance(printValores).print(new PersonalResumenFormatter(aux, p, getInstance(codVenta).getFecha()), null);

    }

    @Override
    public void printZ(int codVenta) {
        Impresion.getDefaultInstance().print(new VentaZFormatter(getInstance(codVenta)), null);
    }

    @Override
    public void reabrirVentas(int codVenta) {
        if (new LogInController().constructoAuthorizationView(R.NivelAcceso.ECONOMICO)) {
            Calendar limitTime = Calendar.getInstance();
            limitTime.add(Calendar.DAY_OF_YEAR, -1);
            limitTime.set(Calendar.HOUR_OF_DAY, 0);
            limitTime.set(Calendar.MINUTE, 0);
            limitTime.set(Calendar.SECOND, 0);
            limitTime.set(Calendar.MILLISECOND, 0);
            if (getInstance(codVenta).getFecha().getTime() < limitTime.getTimeInMillis()) {
                throw new IllegalStateException("La venta solo se puede reabrir en el margen de 1 dia laboral. No antes");
            }
            Venta v = getInstance(codVenta);
            v.setVentaTotal(null);
            update(v, true);
        }
    }

    @Override
    public void setPropina(float value, int codVenta) {
        getInstance(codVenta).setVentapropina(value);
        update(getInstance(codVenta), true);
    }

    @Override
    public void terminarVentas(int codVenta) {
        float ventaTotal = 0,
                ventasGastosEnInsumos = 0,
                ventasGastosGastos = 0,
                ventasPagoTrabajadores = 0;
        Venta v = getModel().getEntityManager().find(Venta.class, codVenta);
        getModel().getEntityManager().refresh(v);
        for (Orden x : v.getOrdenList()) {
            if (x.getHoraTerminada() == null) {
                throw new IllegalStateException("Existen tickets sin cerrar (" + x + "). Cierre los tickets antes de terminar la venta");
            }
            if (!x.getDeLaCasa()) {
                ventaTotal += x.getOrdenvalorMonetario();
            }
            ventasGastosEnInsumos += x.getOrdengastoEninsumos();
        }
        for (GastoVenta x : v.getGastoVentaList()) {
            ventasGastosGastos += x.getImporte();
        }
        for (AsistenciaPersonal x : v.getAsistenciaPersonalList()) {
            ventasPagoTrabajadores += x.getPago();
        }
        v.setVentaTotal((double) ventaTotal);
        v.setVentagastosEninsumos((double) ventasGastosEnInsumos);
        v.setVentagastosGastos(ventasGastosGastos);
        v.setVentagastosPagotrabajadores(ventasPagoTrabajadores);
        update(v);
    }

    @Override
    public void terminarYExportar(File file, int codVenta) {
        terminarVentas(codVenta);
        try {
            new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValue(file, getInstance(codVenta));
        } catch (IOException ex) {
            throw new IllegalAccessError("Ocurrio un error exportando la venta.");
        }
    }

    private List<Venta> crearVenta(Date fecha) {
        LicenceController licence = new LicenceController(Licence.TipoLicencia.APLICACION);
        //revisar si la fecha donde se quiere crear el dia excede el tiempo de licencia
        if (licence.getLicence().getFechaFinMilis() < fecha.toInstant().toEpochMilli()) {
            throw new IllegalStateException("No se pueden crear ventas mas alla de la fecha fin de la licencia");
        }
        Venta aux = new Venta();
        aux.setVentagastosEninsumos(0.0);
        aux.setVentapropina((float) 0);
        aux.setOrdenList(new ArrayList<>());
        aux.setFecha(fecha);
        create(aux, true);
        List<Venta> ret = getVentasDeFecha(fecha);
        ret.add(aux);

        //Crear IPvs
        initIPV(aux);
        return ret;
    }

    private List<Venta> getDiaSinCerrar() {
        //revisar si hay un dia sin cerrar
        List<Venta> ventas = getItems();
        for (int i = ventas.size() - 1; i >= 0; i--) {
            if (ventas.get(i).getVentaTotal() == null) {
                return getVentasDeFecha(ventas.get(i).getFecha());
            }
        }
        return new ArrayList<>();
    }

    private Impresion getImpresionInstance(boolean printValores) {
        Impresion i = Impresion.getDefaultInstance();
        i.setSHOW_PRICES(printValores);
        return i;
    }

    private List<Venta> getVentasDeFecha(Date date) {
        return VentaDAO.getInstance().find(date);
    }

    @Override
    public List<Orden> findReservas(int codVenta) {
        List<Orden> listaReserva = new ArrayList<>();
        Date currentDate = VentaDAO.getInstance().find(codVenta).getFecha();
        for (Orden reserva : (List<Orden>) new ReservaListController().getListaReservas()) {
            Date d = reserva.getVentafecha();
            if (currentDate.getDate() == d.getDate()
                    && currentDate.getMonth() == d.getMonth()
                    && currentDate.getYear() == d.getYear()) {
                listaReserva.add(reserva);
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy");
        if (listaReserva.isEmpty()) {
            throw new IllegalStateException("No hay reservaciones para el " + sdf.format(currentDate));
        }
        return listaReserva;
    }

    @Override
    public Orden abrirReserva(Orden orden, int codVenta) {
        orden.setVentaid(VentaDAO.getInstance().find(codVenta));
        getModel().startTransaction();
        OrdenDAO.getInstance().edit(orden);
        getModel().commitTransaction();
        getInstance(codVenta).getOrdenList().add(orden);
        return null;
    }

}

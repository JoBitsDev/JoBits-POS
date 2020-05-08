/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.almacen;

import com.jobits.pos.adapters.repo.VentaDAO;
import com.jobits.pos.adapters.repo.InsumoDAO;
import com.jobits.pos.adapters.repo.IpvRegistroDAO;
import com.jobits.pos.adapters.repo.IpvDAO;
import com.jobits.pos.adapters.repo.CocinaDAO;
import com.jobits.pos.adapters.repo.ProductoVentaDAO;
import com.jobits.pos.adapters.repo.IpvRegistroVentaDAO;
import com.jobits.pos.domain.models.IpvVentaRegistro;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.Ipv;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.domain.models.IpvRegistro;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.IpvVentaRegistroPK;
import com.jobits.pos.domain.models.IpvPK;
import com.jobits.pos.domain.models.ProductovOrden;
import com.jobits.pos.domain.models.IpvRegistroPK;
import com.jobits.pos.ui.OldAbstractView;
import com.jobits.pos.ui.almacen.IpvGestionView;

import java.awt.Container;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.controller.venta.VentaDetailController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.exceptions.UnExpectedErrorException;
import com.jobits.pos.exceptions.ValidatingException;

import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.utils.utils;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class IPVController extends AbstractDialogController<Ipv> {

    //
    // Constructores
    //
    public IPVController() {
        super(IpvDAO.getInstance());
    }

    public IPVController(Container parent) {
        super(IpvDAO.getInstance());
        IpvRegistroDAO.getInstance().addPropertyChangeListener(this);
        constructView(parent);
    }

    //
    // Overrides
    //
    @Override
    public void constructView(Container parent) {
        setView(new IpvGestionView((OldAbstractView) parent, this));
        getView().updateView();
        getView().setVisible(true);
        IpvRegistroDAO.getInstance().removePropertyChangeListener(this);

    }

    @Override
    public void create(Ipv selected, boolean quietMode) {
        super.create(selected, quietMode); //To change body of generated methods, choose Tools | Templates.
        inicializarExistencias(new VentaDetailController().getDiaDeVenta(null).getFecha());
    }

    @Override
    public void destroy(Ipv selected, boolean quietMode) {
        boolean previousValue = showDialogs;
        setShowDialogs(!quietMode);
        getModel().startTransaction();
        for (IpvRegistro x : selected.getIpvRegistroList()) {
            getModel().getEntityManager().remove(x);
        }

        getModel().getEntityManager().remove(selected);
        getModel().commitTransaction();
        setShowDialogs(previousValue);
    }

    //
    // Getters
    //
    public List<String> getExistenciaRegistroList(Cocina cocina) {
        List<Date> ret = IpvRegistroDAO.getInstance().getIpvRegistroList(cocina);
        List<String> r = new ArrayList<>();
        ret.forEach((x) -> {
            r.add(R.DATE_FORMAT.format(x));
        });
        return r;
    }

    public List<IpvRegistro> getIpvRegistroList(Cocina cocina, Date fecha) {
        return IpvRegistroDAO.getInstance().getIpvRegistroList(cocina, fecha);
    }

    public List<IpvVentaRegistro> getIpvRegistroVentaList(Cocina cocina, Date fecha) {
        List<IpvVentaRegistro> list = IpvRegistroVentaDAO.getInstance().getIpvVentaRegistroList(fecha);
        List<IpvVentaRegistro> ret = new ArrayList<>();
        for (IpvVentaRegistro x : list) {
            if (x.getProductoVenta().getCocinacodCocina().equals(cocina)) {
                ret.add(x);
            }
        }
        Collections.sort(ret, (IpvVentaRegistro o1, IpvVentaRegistro o2) -> o1.getProductoVenta().getNombre().compareToIgnoreCase(o2.getProductoVenta().getNombre()));
        return ret;
    }

    public List<Cocina> getCocinaList() {
        return CocinaDAO.getInstance().findAll();
    }

    public List<Insumo> getInsumoList() {
        return InsumoDAO.getInstance().findAll();
    }

    public Insumo getInsumo(String ipvinsumocodInsumo) {
        return InsumoDAO.getInstance().find(ipvinsumocodInsumo);
    }

    private float getEntradaDiaAnterior(IpvRegistro reg) {
        Date d = reg.getIpvRegistroPK().getFecha();

        Calendar c = Calendar.getInstance();
        c.set(d.getYear() + 1900, d.getMonth(), d.getDate());

        byte i = 0;
        IpvRegistro founded = null;
        do {
            i++;
            c.add(Calendar.DAY_OF_MONTH, -1);
            d = Date.from(c.toInstant());
            IpvRegistroPK newReg
                    = new IpvRegistroPK(reg.getIpvRegistroPK().getIpvinsumocodInsumo(),
                            reg.getIpvRegistroPK().getIpvcocinacodCocina(), d);
            founded = IpvRegistroDAO.getInstance().find(newReg);
        } while (founded == null && i < 7);

        if (founded != null) {
//            VentaDetailController controller = new VentaDetailController(founded.getIpvRegistroPK().getFecha());
//            founded.setConsumo(calcular_existencia_del_dia(controller, founded));
//            updateInstance(founded);
            if (founded.getConsumoReal() != null) {
                if (founded.getConsumoReal() > 0) {
                    return founded.getFinalAjustado();
                } else {
                    return founded.getFinalCalculado();
                }
            } else {
                return founded.getFinalCalculado();
            }
        }
        return 0;

    }

    private float getEntradaDiaAnterior(IpvVentaRegistro reg) {
        Date d = reg.getIpvVentaRegistroPK().getVentafecha();

        Calendar c = Calendar.getInstance();
        c.set(d.getYear() + 1900, d.getMonth(), d.getDate());

        byte i = 0;
        IpvVentaRegistro founded = null;
        do {
            i++;
            c.add(Calendar.DAY_OF_MONTH, -1);
            d = Date.from(c.toInstant());
            IpvVentaRegistroPK newReg
                    = new IpvVentaRegistroPK(d,
                            reg.getIpvVentaRegistroPK().getProductoVentapCod());
            founded = IpvRegistroVentaDAO.getInstance().find(newReg);
        } while (founded == null && i < 7);

        if (founded != null) {
//            VentaDetailController controller = new VentaDetailController(founded.getFechaVenta().getFecha());
//            founded.setVendidos(controller.getVentaTotalDelProducto(founded.getProductoVenta()));
//            updateInstance(founded);
            return founded.getFinal1();
        }
        return 0;

    }

    //
    // Logica
    //
    public void ajustarConsumo(IpvRegistro instance) {
        float cantidad;
        try {
            cantidad = Float.parseFloat(showInputDialog(getView(), "Introduzca la cantidad a ajustar"));
        } catch (NumberFormatException e) {
            showErrorDialog(getView(), "El valor introducido no es correcto");
            return;
        }
        if (showConfirmDialog(getView(), "Desea ajustar el consumo de " + instance.getIpv().getInsumo() + " a " + cantidad)) {
            getModel().getEntityManager().refresh(instance);
            instance.setConsumoReal(cantidad);
            updateInstance(instance);
            getModel().getEntityManager().refresh(instance);
        }

    }

    /**
     * este metodo debe invocarse una sola vez para inicializar los ipvs del dia
     * de trabajo
     *
     * @param fecha
     * @return
     */
    public List<IpvRegistro> inicializarExistencias(Date fecha) {
        ArrayList<IpvRegistro> ret = new ArrayList<>();
        actualizarIPVs();
        IpvDAO.getInstance().findAll().forEach((x) -> {
            IpvRegistroPK pk = new IpvRegistroPK(x.getInsumo().getCodInsumo(), x.getCocina().getCodCocina(), fecha);
            IpvRegistro reg = IpvRegistroDAO.getInstance().find(pk);
            if (reg == null) {
                reg = new IpvRegistro(pk);
                reg.setIpv(x);
                IpvRegistroDAO.getInstance().startTransaction();
                IpvRegistroDAO.getInstance().create(reg);
                IpvRegistroDAO.getInstance().commitTransaction();
                reg.setInicio(getEntradaDiaAnterior(reg));
                reg.setEntrada((float) 0);
                reg.setConsumo((float) 0);
                reg.setConsumoReal((float) 0);
                updateInstance(reg);
            }
            ret.add(reg);
        });

        return ret;
    }

    /**
     * este metodo debe invocarse una sola vez para inicializar los ipvs del dia
     * de trabajo
     *
     * @param fecha
     * @return
     */
    public List<IpvVentaRegistro> inicializarIpvs(Date fecha) {
        ArrayList<IpvVentaRegistro> ret = new ArrayList<>();
        for (ProductoVenta x : ProductoVentaDAO.getInstance().findAll()) {
            IpvVentaRegistroPK pk = new IpvVentaRegistroPK(fecha, x.getCodigoProducto());
            IpvVentaRegistro reg = IpvRegistroVentaDAO.getInstance().find(pk);
            if (reg == null) {
                reg = new IpvVentaRegistro(pk);
                IpvRegistroVentaDAO.getInstance().startTransaction();
                IpvRegistroVentaDAO.getInstance().create(reg);
                IpvRegistroVentaDAO.getInstance().commitTransaction();
                reg.setInicio(getEntradaDiaAnterior(reg));
                reg.setEntrada((float) 0);
                reg.setVendidos((float) 0);
                reg.setAutorizos((float) 0);
                reg.setFechaVenta(VentaDAO.getInstance().find(fecha));
                reg.setProductoVenta(x);
                updateInstance(reg);
            }
            ret.add(reg);
        }

        return ret;
    }

    public void darEntradaExistencia(Insumo insumo, Cocina cocina, Date fecha, Float cantidad) {
        try {
            IpvRegistro reg = IpvRegistroDAO.getInstance().getIpvRegistro(cocina, fecha, insumo);
            if (reg != null) {
                reg.setEntrada(reg.getEntrada() + cantidad);
                updateInstance(reg);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ValidatingException(getView(),
                    "El insumo en el ipv a dar entrada no existe o no hay un ipv inizializado en el dia actual");

        }
    }

    public boolean hayDisponibilidad(ProductoVenta selected, Date fecha, float cantidad) {
        for (ProductoInsumo insumo : selected.getProductoInsumoList()) {
            try {
                IpvRegistro ipv = IpvRegistroDAO.getInstance().getIpvRegistro(selected.getCocinacodCocina(), fecha, insumo.getInsumo());
                float f = ipv.getConsumo() + insumo.getCantidad() * cantidad;
                if (f > ipv.getDisponible()) {
                    selected.setVisible(false);
                    getModel().getEntityManager().getTransaction().begin();
                    getModel().getEntityManager().merge(selected);
                    getModel().getEntityManager().getTransaction().commit();
                    return false;
                }
            } catch (NoResultException e) {
                return true;
            } catch (PersistenceException e) {
                throw new UnExpectedErrorException(e.getMessage());

            }

        }
        return true;

    }

    public void darEntradaExistencia(IpvRegistro instance) {
        float cantidad;
        try {
            cantidad = Float.parseFloat(showInputDialog(getView(), "Introduzca la cantidad a dar entrada"));
        } catch (NumberFormatException e) {
            showErrorDialog(getView(), "El valor introducido no es correcto");
            return;
        }
        if (showConfirmDialog(getView(), "Desea dar entrada a " + cantidad + " de " + instance.getIpv().getInsumo())) {
            if (cantidad < 0) {
                if (!new LogInController().constructoAuthorizationView(getView(), R.NivelAcceso.ADMINISTRADOR)) {
                    return;
                }
            }
            getModel().getEntityManager().refresh(instance);
            instance.setEntrada(instance.getEntrada() + cantidad);
            updateInstance(instance);
            getModel().getEntityManager().refresh(instance);
        }
    }

    public void darEntradaIPV(IpvVentaRegistro instance) {
        float cantidad;
        try {
            cantidad = Float.parseFloat(showInputDialog(getView(), "Introduzca la cantidad a dar entrada"));
        } catch (NumberFormatException e) {
            showErrorDialog(getView(), "El valor introducido no es correcto");
            return;
        }
        if (showConfirmDialog(getView(), "Desea dar entrada a " + cantidad + " de " + instance.getProductoVenta())) {
            if (cantidad < 0) {
                if (!new LogInController().constructoAuthorizationView(getView(), R.NivelAcceso.ADMINISTRADOR)) {
                    return;
                }
            }
            getModel().getEntityManager().refresh(instance);
            instance.setEntrada(instance.getEntrada() + cantidad);
            updateInstance(instance);
            getModel().getEntityManager().refresh(instance);
        }
    }

    public void darEntradaIPV(IpvVentaRegistro instance, float cantidad) {
        if (showConfirmDialog(getView(), "Desea dar entrada a " + cantidad + " de " + instance.getProductoVenta())) {
            if (cantidad < 0) {
                if (!new LogInController().constructoAuthorizationView(getView(), R.NivelAcceso.ADMINISTRADOR)) {
                    return;
                }
            }
            instance.setEntrada(instance.getEntrada() + cantidad);
            updateInstance(instance);
        }
    }

    public void consumir(ProductovOrden productoVenta, float cantidad) {
        consumirIpvRegistro(productoVenta, cantidad);
        IpvVentaRegistroPK pk = new IpvVentaRegistroPK(productoVenta.getOrden().getVentafecha().getFecha(), productoVenta.getProductoVenta().getCodigoProducto());
        IpvVentaRegistro ipvVenta = IpvRegistroVentaDAO.getInstance().find(pk);
        if (ipvVenta != null) {
            ipvVenta.setVendidos(ipvVenta.getVendidos() + cantidad);
            updateInstance(ipvVenta);
        }
    }

    public void consumirPorLaCasa(ProductovOrden productoVenta, float cantidad) {
        consumirIpvRegistro(productoVenta, cantidad);
        IpvVentaRegistroPK pk = new IpvVentaRegistroPK(productoVenta.getOrden().getVentafecha().getFecha(), productoVenta.getProductoVenta().getCodigoProducto());
        IpvVentaRegistro ipvVenta = IpvRegistroVentaDAO.getInstance().find(pk);
        if (ipvVenta != null) {
            ipvVenta.setAutorizos(ipvVenta.getAutorizos() + cantidad);
            updateInstance(ipvVenta);
        }
    }

    //esto solo pincha cuando ponen el de la casa al final
    public void consumirPorLaCasa(List<ProductovOrden> listaProductos) {
        for (ProductovOrden x : listaProductos) {
            IpvVentaRegistroPK pk = new IpvVentaRegistroPK(x.getOrden().getVentafecha().getFecha(), x.getProductoVenta().getCodigoProducto());
            IpvVentaRegistro ipvVenta = IpvRegistroVentaDAO.getInstance().find(pk);
            if (ipvVenta != null) {
                ipvVenta.setVendidos(ipvVenta.getVendidos() - x.getCantidad());
                ipvVenta.setAutorizos(ipvVenta.getAutorizos() + x.getCantidad());
                updateInstance(ipvVenta);
            }
        }

    }

    //esto solo pincha cuando ponen el de la casa al final
    public void devolverPorLaCasa(List<ProductovOrden> listaProductos) {
        for (ProductovOrden x : listaProductos) {
            IpvVentaRegistroPK pk = new IpvVentaRegistroPK(x.getOrden().getVentafecha().getFecha(), x.getProductoVenta().getCodigoProducto());
            IpvVentaRegistro ipvVenta = IpvRegistroVentaDAO.getInstance().find(pk);
            if (ipvVenta != null) {
                ipvVenta.setAutorizos(ipvVenta.getAutorizos() - x.getCantidad());
                ipvVenta.setVendidos(ipvVenta.getVendidos() + x.getCantidad());
                updateInstance(ipvVenta);
            }
        }
    }

    public void devolverPorLaCasa(ProductovOrden productoVenta, float diferencia) {
        devolverIpvRegistro(productoVenta, diferencia);
        IpvVentaRegistroPK pk = new IpvVentaRegistroPK(productoVenta.getOrden().getVentafecha().getFecha(), productoVenta.getProductoVenta().getCodigoProducto());
        IpvVentaRegistro ipvVenta = IpvRegistroVentaDAO.getInstance().find(pk);
        if (ipvVenta != null) {
            ipvVenta.setAutorizos(ipvVenta.getAutorizos() - diferencia);
            updateInstance(ipvVenta);
        }
    }

    public void devolver(ProductovOrden productoVenta, float cantidad) {
        devolverIpvRegistro(productoVenta, cantidad);
        IpvVentaRegistroPK pk = new IpvVentaRegistroPK(productoVenta.getOrden().getVentafecha().getFecha(), productoVenta.getProductoVenta().getCodigoProducto());
        IpvVentaRegistro ipvVenta = IpvRegistroVentaDAO.getInstance().find(pk);
        if (ipvVenta != null) {
            ipvVenta.setVendidos(ipvVenta.getVendidos() - cantidad);
            updateInstance(ipvVenta);
        }
    }

    /**
     * Vuelve a calcular de manera manual todos los consumos del dia en la fecha
     * seleccionada
     *
     * @param fecha
     */
    public void recalcularExistencias(Date fecha) {
        VentaDetailController ventaController = new VentaDetailController(fecha);
        for (IpvRegistro x : IpvRegistroDAO.getInstance().getIpvRegistroList(fecha)) {
            x.setConsumo(ventaController.getGastoTotalDeInsumo(x));
            getModel().startTransaction();
            IpvRegistroDAO.getInstance().edit(x);
            getModel().commitTransaction();
        }
    }

    public void recalcularIpvRegistros(Venta ret) {
        VentaDetailController ventaController = new VentaDetailController(ret);
        for (IpvVentaRegistro x : IpvRegistroVentaDAO.getInstance().getIpvVentaRegistroList(ret.getFecha())) {
            x.setVendidos(ventaController.getVentaTotalDelProducto(x.getProductoVenta()));
            x.setAutorizos(ventaController.getAutorizosTotalDelProducto(x.getProductoVenta()));
            getModel().startTransaction();
            IpvRegistroVentaDAO.getInstance().edit(x);
            getModel().commitTransaction();
        }

    }

    //
    // Metodos Privados
    //
    private void consumirIpvRegistro(ProductovOrden productoVenta, float cantidad) {
        List<IpvRegistro> updateList = new ArrayList<>();
        for (ProductoInsumo productoInsumo : productoVenta.getProductoVenta().getProductoInsumoList()) {
            IpvRegistro registro = IpvRegistroDAO.getInstance().
                    getIpvRegistro(productoVenta.getProductoVenta().getCocinacodCocina(),
                            productoVenta.getOrden().getVentafecha().getFecha(),
                            productoInsumo.getInsumo());
            if (registro != null) {
                float cantidadaRebajar = productoInsumo.getCantidad() * cantidad;
                registro.setConsumo(registro.getConsumo() + cantidadaRebajar);
                updateList.add(registro);
            }
        }
        for (IpvRegistro registro : updateList) {
            updateInstance(registro);
        }
    }

    private void devolverIpvRegistro(ProductovOrden productoVenta, float cantidad) {
        List<IpvRegistro> updateList = new ArrayList<>();
        for (ProductoInsumo productoInsumo : productoVenta.getProductoVenta().getProductoInsumoList()) {
            IpvRegistro registro = IpvRegistroDAO.getInstance().
                    getIpvRegistro(productoVenta.getProductoVenta().getCocinacodCocina(),
                            productoVenta.getOrden().getVentafecha().getFecha(),
                            productoInsumo.getInsumo());
            if (registro != null) {
                float cantidadaRebajar = productoInsumo.getCantidad() * cantidad;
                registro.setConsumo(registro.getConsumo() - cantidadaRebajar);
                updateList.add(registro);
            }
        }
        for (IpvRegistro registro : updateList) {
            updateInstance(registro);
        }
    }

    private void updateInstance(IpvRegistro instance) {
        if (instance.getEntrada() == null) {
            instance.setEntrada((float) 0);
        }
        if (instance.getInicio() == null) {
            instance.setInicio((float) 0);
        }
        if (instance.getConsumo() == null) {
            instance.setConsumo((float) 0);
        }
        if (instance.getConsumoReal() == null) {
            instance.setConsumoReal((float) 0);
        }
        if (instance.getFinalCalculado() == null) {
            instance.setFinalCalculado((float) 0);
        }
        if (instance.getFinalAjustado() == null) {
            instance.setFinalAjustado((float) 0);
        }
        if (instance.getDisponible() == null) {
            instance.setDisponible((float) 0);
        }
        IpvRegistroDAO.getInstance().startTransaction();
        IpvRegistroDAO.getInstance().edit(instance);
        IpvRegistroDAO.getInstance().commitTransaction();
    }

    private void updateInstance(IpvVentaRegistro instance) {
        if (instance.getEntrada() == null) {
            instance.setEntrada((float) 0);
        }
        if (instance.getInicio() == null) {
            instance.setInicio((float) 0);
        }
        if (instance.getVendidos() == null) {
            instance.setVendidos((float) 0);
        }
        if (instance.getFinal1() == null) {
            instance.setFinal1((float) 0);
        }
        if (instance.getDisponible() == null) {
            instance.setDisponible((float) 0);
        }
        if (instance.getAutorizos() == null) {
            instance.setAutorizos((float) 0);
        }
        IpvRegistroVentaDAO.getInstance().startTransaction();
        IpvRegistroVentaDAO.getInstance().edit(instance);
        IpvRegistroVentaDAO.getInstance().commitTransaction();
    }

    private void actualizarIPVs() {
        for (Insumo i : InsumoDAO.getInstance().findAll()) {
            for (ProductoInsumo pv : i.getProductoInsumoList()) {
                IpvPK pk = new IpvPK(i.getCodInsumo(), pv.getProductoVenta().getCocinacodCocina().getCodCocina());
                Ipv ipv = IpvDAO.getInstance().find(pk);
                if (ipv == null) {
                    ipv = new Ipv(pk);
                    ipv.setCocina(pv.getProductoVenta().getCocinacodCocina());
                    ipv.setInsumo(i);
                    ipv.setIpvRegistroList(new ArrayList<>());
                    IpvDAO.getInstance().startTransaction();
                    IpvDAO.getInstance().create(ipv);
                    IpvDAO.getInstance().commitTransaction();
                }
            }
        }
    }

}

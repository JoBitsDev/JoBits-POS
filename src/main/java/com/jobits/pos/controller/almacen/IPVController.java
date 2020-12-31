/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.almacen;

import com.jobits.pos.adapters.repo.impl.CocinaDAO;
import com.jobits.pos.adapters.repo.impl.InsumoDAO;
import com.jobits.pos.adapters.repo.impl.IpvDAO;
import com.jobits.pos.adapters.repo.impl.IpvRegistroDAO;
import com.jobits.pos.adapters.repo.impl.IpvRegistroVentaDAO;
import com.jobits.pos.adapters.repo.impl.ProductoVentaDAO;
import com.jobits.pos.adapters.repo.impl.VentaDAO;
import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.controller.venta.VentaDetailController;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.Ipv;
import com.jobits.pos.domain.models.IpvPK;
import com.jobits.pos.domain.models.IpvRegistro;
import com.jobits.pos.domain.models.IpvRegistroPK;
import com.jobits.pos.domain.models.IpvVentaRegistro;
import com.jobits.pos.domain.models.IpvVentaRegistroPK;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.ProductovOrden;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.exceptions.UnExpectedErrorException;
import com.jobits.pos.exceptions.ValidatingException;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.utils.NumberPad;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class IPVController extends AbstractDialogController<Ipv> implements IPVService {

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
    // Logica
    //
    public void ajustarConsumo(IpvRegistro instance) {
        float cantidad;
        try {
//            cantidad = Float.parseFloat(showInputDialog(getView(), "Introduzca la cantidad a ajustar"));
            cantidad = new NumberPad(null).showView();
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

    //
    // Overrides
    //
    @Override
    public void constructView(Container parent) {

    }

    @Override
    public void create(Ipv selected, boolean quietMode) {
        super.create(selected, quietMode); //To change body of generated methods, choose Tools | Templates.
        // inicializarExistencias(new VentaDetailController().inicializarVentas(null).get(0));
    }

    public void darEntradaExistencia(Insumo insumo, Cocina cocina, int idVenta, Float cantidad) {
        try {
            IpvRegistro reg = IpvRegistroDAO.getInstance().getIpvRegistro(cocina, idVenta, insumo);
            if (reg != null) {
                reg.setEntrada(reg.getEntrada() + cantidad);
                updateInstance(reg);
            }
        } catch (PersistenceException e) {
            if (getModel().getEntityManager().getTransaction().isActive()) {
                getModel().getEntityManager().getTransaction().rollback();
            }
            System.out.println(e.getMessage());
            throw new ValidatingException(getView(),
                    "El insumo en el ipv a dar entrada no existe o no hay un ipv inizializado en el dia actual");

        }
    }

    public void darEntradaExistencia(IpvRegistro instance) {
        if (instance != null) {
            float cantidad;
            try {
//            cantidad = Float.parseFloat(showInputDialog(getView(), "Introduzca la cantidad a dar entrada"));
                cantidad = new NumberPad(null).showView();
            } catch (NumberFormatException e) {
                showErrorDialog(getView(), "El valor introducido no es correcto");
                return;
            }
            if (showConfirmDialog(getView(), "Desea dar entrada a " + cantidad + " de " + instance.getIpv().getInsumo())) {
                if (cantidad < 0) {
                    if (!new LogInController().constructoAuthorizationView(R.NivelAcceso.ADMINISTRADOR.getNivel())) {
                        return;
                    }
                }
                getModel().getEntityManager().refresh(instance);
                instance.setEntrada(instance.getEntrada() + cantidad);
                updateInstance(instance);
                getModel().getEntityManager().refresh(instance);
            }
        } else {
            showErrorDialog(getView(), "Seleccione un IPV primero");
        }
    }

    public void darEntradaIPV(IpvVentaRegistro instance) {
        if (instance != null) {
            float cantidad;
            try {
//            cantidad = Float.parseFloat(showInputDialog(getView(), "Introduzca la cantidad a dar entrada"));
                cantidad = new NumberPad(null).showView();
            } catch (NumberFormatException e) {
                showErrorDialog(getView(), "El valor introducido no es correcto");
                return;
            }
            if (showConfirmDialog(getView(), "Desea dar entrada a " + cantidad + " de " + instance.getProductoVenta())) {
                if (cantidad < 0) {
                    if (!new LogInController().constructoAuthorizationView(R.NivelAcceso.ADMINISTRADOR.getNivel())) {
                        return;
                    }
                }
                getModel().getEntityManager().refresh(instance);
                instance.setEntrada(instance.getEntrada() + cantidad);
                updateInstance(instance);
                getModel().getEntityManager().refresh(instance);
            }
        } else {
            showErrorDialog(getView(), "Seleccione un IPV primero");
        }
    }

    public void darEntradaIPV(IpvVentaRegistro instance, float cantidad) {
        if (showConfirmDialog(getView(), "Desea dar entrada a " + cantidad + " de " + instance.getProductoVenta())) {
            if (cantidad < 0) {
                if (!new LogInController().constructoAuthorizationView(R.NivelAcceso.ADMINISTRADOR.getNivel())) {
                    return;
                }
            }
            instance.setEntrada(instance.getEntrada() + cantidad);
            updateInstance(instance);
        }
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

    public List<Cocina> getCocinaList() {
        return CocinaDAO.getInstance().findAll();
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

    public Insumo getInsumo(String ipvinsumocodInsumo) {
        return InsumoDAO.getInstance().find(ipvinsumocodInsumo);
    }

    public List<Insumo> getInsumoList() {
        return InsumoDAO.getInstance().findAll();
    }

    public List<IpvRegistro> getIpvRegistroList(Cocina cocina, int ventaCod) {

        List<IpvRegistro> lista = IpvRegistroDAO.getInstance().getIpvRegistroList(cocina, ventaCod);
        VentaDetailController controller = new VentaDetailController();
        for (IpvRegistro i : lista) {
            i.setConsumo(controller.getGastoTotalDeInsumo(i, ventaCod));
            updateInstance(i);
        }
        return lista;
    }

    public List<IpvVentaRegistro> getIpvRegistroVentaList(Cocina cocina, int codVenta) {
        VentaDetailController controller = new VentaDetailController();
        List<IpvVentaRegistro> list = IpvRegistroVentaDAO.getInstance().getIpvVentaRegistroList(codVenta);
        List<IpvVentaRegistro> ret = new ArrayList<>();
        for (IpvVentaRegistro x : list) {
            if (x.getProductoVenta() != null) {
                if (x.getProductoVenta().getCocinacodCocina() != null) {
                    if (x.getProductoVenta().getCocinacodCocina().equals(cocina)) {
                        x.setVenta(controller.getVentaTotalDelProducto(x.getProductoVenta(), codVenta));
                        x.setAutorizos(controller.getAutorizosTotalDelProducto(x.getProductoVenta(), codVenta));
                        updateInstance(x);
                        ret.add(x);
                    }
                }
            }
        }
        Collections.sort(ret, (IpvVentaRegistro o1, IpvVentaRegistro o2) -> o1.getProductoVenta().getNombre().compareToIgnoreCase(o2.getProductoVenta().getNombre()));
        return ret;
    }

    public List<Venta> getVentasInRange(Date fecha) {
        return VentaDAO.getInstance().find(fecha);
    }

    public boolean hayDisponibilidad(ProductoVenta selected, int idVenta, float cantidad) {
        for (ProductoInsumo insumo : selected.getProductoInsumoList()) {
            try {
                IpvRegistro ipv = IpvRegistroDAO.getInstance().getIpvRegistro(selected.getCocinacodCocina(), idVenta, insumo.getInsumo());
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

    /**
     * este metodo debe invocarse una sola vez para inicializar los ipvs del dia
     * de trabajo
     *
     * @param fecha
     * @return
     */
    public List<IpvRegistro> inicializarExistencias(int idVenta) {
        ArrayList<IpvRegistro> ret = new ArrayList<>();
        actualizarIPVs();
        IpvDAO.getInstance().findAll().forEach((x) -> {
            IpvRegistroPK pk = new IpvRegistroPK(x.getInsumo().getCodInsumo(), x.getCocina().getCodCocina(), idVenta);
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
    public List<IpvVentaRegistro> inicializarIpvs(int idVenta) {
        ArrayList<IpvVentaRegistro> ret = new ArrayList<>();
        for (ProductoVenta x : ProductoVentaDAO.getInstance().findAll()) {
            IpvVentaRegistroPK pk = new IpvVentaRegistroPK(x.getCodigoProducto(), idVenta);
            IpvVentaRegistro reg = IpvRegistroVentaDAO.getInstance().find(pk);
            if (reg == null) {
                reg = new IpvVentaRegistro(pk);
                IpvRegistroVentaDAO.getInstance().startTransaction();
                IpvRegistroVentaDAO.getInstance().create(reg);
                IpvRegistroVentaDAO.getInstance().commitTransaction();
                reg.setInicio(getEntradaDiaAnterior(reg));
                reg.setEntrada((float) 0);
                reg.setVenta((float) 0);
                reg.setAutorizos((float) 0);
                reg.setDiaVenta(VentaDAO.getInstance().find(idVenta));
                reg.setProductoVenta(x);
                updateInstance(reg);
            }
            ret.add(reg);
        }

        return ret;
    }

    /**
     * Vuelve a calcular de manera manual todos los consumos del dia en la fecha
     * seleccionada
     *
     * @param fecha
     */
    public void recalcularExistencias(Date fecha, int idVenta) {
        VentaDetailController ventaController = new VentaDetailController();
        for (IpvRegistro x : IpvRegistroDAO.getInstance().getIpvRegistroList(idVenta)) {
            x.setConsumo(ventaController.getGastoTotalDeInsumo(x, idVenta));
            getModel().startTransaction();
            IpvRegistroDAO.getInstance().edit(x);
            getModel().commitTransaction();
        }

    }

    public void recalcularIpvRegistros(int codVenta) {
        VentaDetailController ventaController = new VentaDetailController();
        for (IpvVentaRegistro x : IpvRegistroVentaDAO.getInstance().getIpvVentaRegistroList(codVenta)) {
            x.setVenta(ventaController.getVentaTotalDelProducto(x.getProductoVenta(), codVenta));
            x.setAutorizos(ventaController.getAutorizosTotalDelProducto(x.getProductoVenta(), codVenta));
            getModel().startTransaction();
            IpvRegistroVentaDAO.getInstance().edit(x);
            getModel().commitTransaction();
        }

    }

    private void actualizarIPVs() {
        for (Insumo i : InsumoDAO.getInstance().findAll()) {
            for (ProductoInsumo pv : i.getProductoInsumoList()) {
                if (pv.getProductoVenta().getCocinacodCocina() == null) {
                    continue;
                }
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

    //
    // Metodos Privados
    //
    private void consumirIpvRegistro(ProductovOrden productoVenta, float cantidad) {
        List<IpvRegistro> updateList = new ArrayList<>();
        for (ProductoInsumo productoInsumo : productoVenta.getProductoVenta().getProductoInsumoList()) {
            IpvRegistro registro = IpvRegistroDAO.getInstance().
                    getIpvRegistro(productoVenta.getProductoVenta().getCocinacodCocina(),
                            productoVenta.getOrden().getVentaid().getId(),
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
                            productoVenta.getOrden().getVentaid().getId(),
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

    private float getEntradaDiaAnterior(IpvRegistro reg) {
        int id = reg.getIpvRegistroPK().getVentaId();

        byte i = 0;
        IpvRegistro founded = null;
        do {
            i++;
            id--;
            IpvRegistroPK newReg
                    = new IpvRegistroPK(reg.getIpvRegistroPK().getIpvinsumocodInsumo(),
                            reg.getIpvRegistroPK().getIpvcocinacodCocina(), id);
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
        int d = reg.getIpvVentaRegistroPK().getVentaid();

        byte i = 0;
        IpvVentaRegistro founded = null;
        do {
            i++;
            d--;
            IpvVentaRegistroPK newReg
                    = new IpvVentaRegistroPK(
                            reg.getIpvVentaRegistroPK().getProductoVentapCod(), d);
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

    private void updateInstance(IpvRegistro instance) {
        if (instance.getEntrada() == null) {
            instance.setEntrada((float) 0);
        }
        if (instance.getInicio() == null) {
            instance.setInicio((float) 0);
        }
        if (instance.getDisponible() == null) {
            instance.setDisponible((float) 0);
        } else {
            instance.setDisponible(instance.getInicio() + instance.getEntrada());
        }
        if (instance.getConsumo() == null) {
            instance.setConsumo((float) 0);
        }
        if (instance.getConsumoReal() == null) {
            instance.setConsumoReal((float) 0);
        }
        if (instance.getFinalCalculado() == null) {
            instance.setFinalCalculado((float) 0);
        } else {
            instance.setFinalCalculado(instance.getDisponible() - instance.getConsumo());
        }
        if (instance.getFinalAjustado() == null) {
            instance.setFinalAjustado((float) 0);
        } else {
            instance.setFinalAjustado(instance.getDisponible() - instance.getConsumoReal());
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
        if (instance.getVenta() == null) {
            instance.setVenta((float) 0);
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

    @Override
    public void transferirIPVRegistro(IpvRegistro ipv_registro_seleciconado) {
        if (ipv_registro_seleciconado != null) {

            List<Cocina> cocinas = getCocinaList();
            Cocina cocina;
            float cantidad;
            if (!cocinas.isEmpty()) {
                JComboBox<Cocina> jComboBox1 = new JComboBox<>();
                jComboBox1.setModel(new DefaultComboBoxModel<>(cocinas.toArray(new Cocina[0])));
                jComboBox1.setSelectedItem(cocinas.get(0));
                Object[] options = {"Seleccionar", "Cancelar"};
                //                     yes        no       cancel
                int confirm = JOptionPane.showOptionDialog(
                        null,
                        jComboBox1,
                        "Puntos de Elaboracion",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.YES_NO_OPTION,
                        new ImageIcon(getClass().getResource("/restManager/resources/icons pack/olla_indigo.png")),
                        options,
                        options[0]);
                switch (confirm) {
                    case JOptionPane.YES_OPTION:
                        cocina = (Cocina) jComboBox1.getSelectedItem();
                        if (cocina != null) {
                            IpvRegistro IPVregistroDestino = IpvRegistroDAO.getInstance().
                                    getIpvRegistro(cocina, ipv_registro_seleciconado.getVenta().getId(), ipv_registro_seleciconado.getIpv().getInsumo());
                            if (IPVregistroDestino != null) {
                                cantidad = new NumberPad(null).showView();
                                if (ipv_registro_seleciconado.getDisponible() >= cantidad) {

                                    getModel().getEntityManager().refresh(ipv_registro_seleciconado);
                                    ipv_registro_seleciconado.setEntrada(ipv_registro_seleciconado.getDisponible() - cantidad);
                                    updateInstance(ipv_registro_seleciconado);
                                    getModel().getEntityManager().refresh(ipv_registro_seleciconado);

                                    getModel().getEntityManager().refresh(IPVregistroDestino);
                                    IPVregistroDestino.setEntrada(IPVregistroDestino.getDisponible() + cantidad);
                                    updateInstance(IPVregistroDestino);
                                    getModel().getEntityManager().refresh(IPVregistroDestino);
                                } else {
                                    showErrorDialog(null, "No hay suficiente "
                                            + ipv_registro_seleciconado.getIpv().getInsumo().getNombre()
                                            + " disponible para transferir a "
                                            + cocina.getNombreCocina());
                                }
                            } else {
                                showErrorDialog(null, ipv_registro_seleciconado.getIpv().getInsumo().getNombre()
                                        + " no esta registrado en " + cocina.getNombreCocina());
                            }
                        }
                    case JOptionPane.NO_OPTION:
                    default:
                        break;
                }
            } else {
                showErrorDialog(null, "No hay Puntos de Elaboracion registrados en el sistema");
            }
        } else {
            showErrorDialog(null, "Seleccione un IPV primero");
        }
    }

}

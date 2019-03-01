/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.venta;

import GUI.Views.AbstractView;
import GUI.Views.util.CalcularCambioView;
import GUI.Views.venta.VentasCreateEditView;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import restManager.controller.AbstractDetailController;
import restManager.controller.almacen.IPVController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.UnauthorizedAccessException;
import restManager.persistencia.Cocina;
import restManager.persistencia.Control.VentaDAO1;
import restManager.persistencia.IpvRegistro;
import restManager.persistencia.Orden;
import restManager.persistencia.Personal;
import restManager.persistencia.ProductoInsumo;
import restManager.persistencia.ProductovOrden;
import restManager.persistencia.Venta;
import restManager.persistencia.jpa.staticContent;
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

    OrdenController ordController;
    private VentasCreateEditView vi;

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

    public VentaDetailController(AbstractView parent, Date diaVentas, boolean b) {
        super(VentaDAO.getInstance());
        this.parent = parent;
        OrdenDAO.getInstance().addPropertyChangeListener(this);
        instance = getDiaDeVenta(diaVentas);
        state = State.CREATING;
        constructFastView(parent);
    }

    public VentaDetailController(AbstractView parent, boolean b) {
        super(VentaDAO.getInstance());
        this.parent = parent;
        OrdenDAO.getInstance().addPropertyChangeListener(this);
        instance = getDiaDeVenta(null);
        state = State.CREATING;
        constructFastView(parent);
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
        vi = new VentasCreateEditView(getInstance(), this, (JDialog) parent);
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
        if (nil) {
            ordController = new OrdenController(getInstance());
        }
        Orden newOrden = ordController.createNewInstance();
        getInstance().getOrdenList().add(newOrden);
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

    public void removeOrden(Orden objectAtSelectedRow) {
        if (R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() < 3) {
            throw new UnauthorizedAccessException(getView());
        }
        ordController.destroy(objectAtSelectedRow, false);
        fetchNewDataFromServer();

    }

    public void fetchNewDataFromServer() {
        getModel().getEntityManager().refresh(getInstance());
        vi.updateView();
    }

    public void terminarVentas() {
        if (showConfirmDialog(vi, "¿Desea terminar el día de trabajo?")) {
            float ventaTotal = 0,
                    ventasGastosEnInsumos = 0;
            for (Orden x : getInstance().getOrdenList()) {
                if (x.getHoraTerminada() == null) {
                    showErrorDialog(vi, "Existen tickets sin cerrar. Cierre los tickets antes de terminar la venta");
                    return;
                }
                if (!x.getDeLaCasa()) {
                    ventaTotal += x.getOrdenvalorMonetario();
                }
                ventasGastosEnInsumos += x.getOrdengastoEninsumos();

            }
            getInstance().setVentaTotal((double) ventaTotal);
            getInstance().setVentagastosEninsumos((double) ventasGastosEnInsumos);
            update(getInstance());

        }
    }

    public void calcularCambio(Orden objectAtSelectedRow) {
        CalcularCambioView cc = new CalcularCambioView(getView(), true, objectAtSelectedRow);
    }

    private Venta getDiaDeVenta(Date fecha) {
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

    public String getTotalGastado() {
        return comun.setDosLugaresDecimales(VentaDAO1.getValorTotalGastos(getInstance()));
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
        Impresion.getDefaultInstance().printPersonalResumen(aux, p, getInstance().getFecha());

    }

    public void printZ() {
        Impresion.getDefaultInstance().printZ(getInstance());
    }

    public void printCocinaResumen(String string) {
        Cocina c = CocinaDAO.getInstance().find(string);
        List<ProductovOrden> aux = VentaDAO1.getResumenVentasCocina(getInstance(), c);
        Collections.sort(aux, (o1, o2) -> {
            return o1.getProductoVenta().getNombre().compareTo(o2.getProductoVenta().getNombre());
        });
        Impresion.getDefaultInstance().printResumenPuntoElab(aux, c, getInstance().getFecha());
    }

    private void constructFastView(AbstractView parent) {

    }

    public Float getGastoTotalDeInsumo(IpvRegistro i) {
        float total = 0;
        for (Orden x : getInstance().getOrdenList()) {
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
        return total;
    }

}

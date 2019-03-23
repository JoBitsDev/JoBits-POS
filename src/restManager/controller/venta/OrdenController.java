package restManager.controller.venta;

import GUI.Views.View;
import GUI.Views.venta.OrdenDetailFragmentView;
import java.awt.Container;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import restManager.controller.AbstractFragmentController;
import restManager.controller.almacen.IPVController;
import restManager.controller.login.LogInController;
import restManager.exceptions.DevelopingOperationException;
import restManager.logs.RestManagerHandler;
import restManager.persistencia.Configuracion;
import restManager.persistencia.IpvRegistro;
import restManager.persistencia.IpvRegistroPK;

import restManager.persistencia.Mesa;
import restManager.persistencia.Nota;
import restManager.persistencia.NotaPK;
import restManager.persistencia.Orden;
import restManager.persistencia.ProductoInsumo;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.ProductovOrden;
import restManager.persistencia.Venta;
import restManager.persistencia.models.ConfigDAO;
import restManager.persistencia.models.IpvRegistroDAO;
import restManager.persistencia.models.MesaDAO;
import restManager.persistencia.models.NotaDAO;
import restManager.persistencia.models.OrdenDAO;
import restManager.persistencia.models.ProductoVentaDAO;
import restManager.persistencia.models.ProductovOrdenDAO;
import restManager.printservice.Impresion;

import restManager.resources.R;
import restManager.util.comun;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class OrdenController extends AbstractFragmentController<Orden> {

    Venta fechaOrden;
    private static final Logger LOGGER = Logger.getLogger(Venta.class.getSimpleName());
    private View v;
    private OrdenDetailFragmentView view;

    public OrdenController(Venta fecha) {
        super(OrdenDAO.getInstance());
        this.fechaOrden = fecha;
        setDismissOnAction(false);
        setShowDialogs(false);
        instance = createNewInstance();
    }

    public OrdenController(Orden instance) {
        super(instance, OrdenDAO.getInstance());
        fechaOrden = instance.getVentafecha();
        setDismissOnAction(false);
        setShowDialogs(false);
    }

    public OrdenController(Container parent, Venta fecha) {
        super(parent, OrdenDAO.getInstance());
        this.fechaOrden = fecha;
        setDismissOnAction(false);
        setShowDialogs(false);
        instance = createNewInstance();
        view = new OrdenDetailFragmentView(instance, this, parent);
        constructView(parent);
    }

    public OrdenController(Orden instance, Container parent) {
        super(instance, parent, OrdenDAO.getInstance());
        fechaOrden = instance.getVentafecha();
        setDismissOnAction(false);
        setShowDialogs(false);
        view = new OrdenDetailFragmentView(getInstance(), this, parent);
        constructView(parent);
    }

    @Override
    public Orden createNewInstance() {
        Orden ret = new Orden();
        ret.setCodOrden(getOrdenCod());
        ret.setPersonalusuario(R.loggedUser);
        ret.setDeLaCasa(false);
        ret.setGananciaXporciento(R.PERCENTAGE);
        ret.setMesacodMesa(MesaDAO.getInstance().find(R.NO_MESA));
        ret.setHoraComenzada(new Date());
        ret.setOrdengastoEninsumos((float) 0);
        ret.setOrdenvalorMonetario((float) 0);
        ret.setPorciento(R.PERCENTAGE);
        ret.setProductovOrdenList(new ArrayList<>());
        ret.setVentafecha(fechaOrden);
        return ret;

    }

    @Override
    public void constructView(java.awt.Container parent) {
        setView(view);
        getView().updateView();
        getView().setVisible(true);
    }

    public void enviarACocina() {
        Impresion i = new Impresion();
        instance = i.printKitchen(instance);
        RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.ENVIAR_COCINA, Level.FINEST, instance);
        update(instance);
        showSuccessDialog(getView(), "Productos enviados a cocina exitosamente");
    }

    public void imprimirPreTicket() {
        Impresion i = new Impresion();
        i.print(instance, true);
        RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.IMPRIMIR_TICKET_PARCIAL, Level.FINER, instance);
    }

    public void addNota(ProductovOrden prod) {
        Nota nota = prod.getNota();
        if (nota == null) {
            String nuevanota = showInputDialog(getView(), "Introduzca la nota a adjuntar");
            NotaPK pk = new NotaPK(
                    prod.getProductovOrdenPK().getProductoVentapCod(), prod.getProductovOrdenPK().getOrdencodOrden());
            Nota n = new Nota(pk);
            n.setDescripcion(nuevanota);
            n.setProductovOrden(prod);

            prod.setNota(n);
            NotaDAO.getInstance().create(n);
            RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.SET_NOTA, Level.FINER, instance.getCodOrden(), n.getDescripcion());
            ProductovOrdenDAO.getInstance().edit(prod);

        } else {
            String notaAntigua = nota.getDescripcion();
            String nuevaNota = showInputDialog(getView(), "Edite la nota anterior", notaAntigua);
            nota.setDescripcion(nuevaNota);
            NotaDAO.getInstance().edit(nota);
            RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.SET_NOTA, Level.FINER, instance.getCodOrden(), nota.getDescripcion());
        }

    }

    public void despachar() {
        Impresion i = new Impresion();
        setShowDialogs(true);
        if (showConfirmDialog(getView(), "Desea cerrar la orden " + instance.getCodOrden())) {
            setShowDialogs(false);
            boolean enviar = true;
            for (ProductovOrden x : instance.getProductovOrdenList()) {
                if (x.getCantidad() != x.getEnviadosacocina()) {
                    showErrorDialog(getView(), "Existen productos que no han sido enviados a cocina. Envie a cocina antes de cerrar la orden");
                    return;
                }
            }
            if (enviar) {
                if (showConfirmDialog(getView(), "Desea imprimir un ticket de la orden")) {

                    i.print(instance, false);
                    RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.IMPRIMIRTICKET, Level.FINE, instance);
                }
                instance.setHoraTerminada(new Date());
                instance.setOrdengastoEninsumos(getGastosInsumos(instance));
                RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.CERRAR, Level.FINE, instance);
                Mesa m = instance.getMesacodMesa();
                m.setEstado("vacia");
                instance.setMesacodMesa(m);
                MesaDAO.getInstance().edit(m);
                setDismissOnAction(true);
                update(instance, true);
            }
        }
        setShowDialogs(false);
        getView().setVisible(false);
    }

    public void updatePorciento(float f) {
        instance.setPorciento(f);
        view.updateValorTotal();
        update(instance);
    }

    public void removeProduct(ProductovOrden objectAtSelectedRow) {

        ProductovOrdenDAO.getInstance().remove(objectAtSelectedRow);
        instance.getProductovOrdenList().remove(objectAtSelectedRow);

        update(instance);
        view.updateValorTotal();
    }

    public float getGastosInsumos(Orden instance) {
        float ret = 0;
        for (ProductovOrden x : instance.getProductovOrdenList()) {
            ret += x.getProductoVenta().getGasto() * x.getCantidad();
        }
        return ret;
    }

    public float getValorTotal() {
        float total = 0;
        for (ProductovOrden x : getInstance().getProductovOrdenList()) {
            total += x.getCantidad() * x.getProductoVenta().getPrecioVenta();

        }
        instance.setOrdenvalorMonetario(comun.redondeoPorExcesoFloat(total * (1 + instance.getPorciento() / 100)));
        update(instance, true);
        return instance.getOrdenvalorMonetario();
    }

    @Override
    public void setInstance(Orden instance) {
        if (instance == null) {
            this.instance = createNewInstance();
        } else {
            this.instance = instance;

        }
        view.setInstance(this.instance);
    }

    @Override
    public void setParent(Container parent) {
        super.setParent(parent); //To change body of generated methods, choose Tools | Templates.
        constructView(parent);
    }

    @Override
    public Orden getInstance() {
        if (instance == null) {
            instance = createNewInstance();
        }
        return instance;
    }

    public List<ProductoVenta> getPDVList() {
        return ProductoVentaDAO.getInstance().findAllVisible();
    }

    public void addProduct(ProductoVenta selected) {
        boolean found = false;
        ProductovOrden founded = null;
        float cantidadAgregada = 1;
        for (ProductovOrden x : new ArrayList<>(getInstance().getProductovOrdenList())) {
            if (x.getProductoVenta().getPCod().equals(selected.getPCod())) {
                founded = x;
                found = true;
                break;
            }
        }
        if (found) {
            founded.setCantidad(founded.getCantidad() + 1);
            ProductovOrdenDAO.getInstance().edit(founded);

        } else {
            founded = new ProductovOrden(selected.getPCod(), getInstance().getCodOrden());
            founded.setOrden(getInstance());
            founded.setProductoVenta(selected);
            founded.setCantidad(Float.parseFloat(showInputDialog(getView(), "Introduzca la cantidad")));
            founded.setEnviadosacocina(0);
            founded.setNumeroComensal(0);
            ProductovOrdenDAO.getInstance().create(founded);
            getInstance().getProductovOrdenList().add(founded);
        }
        cantidadAgregada = founded.getCantidad();
        update(instance);
        view.updateValorTotal();

    }

    private String getOrdenCod() {

        ConfigDAO conf = new ConfigDAO();
        Configuracion c = conf.find("O");
        int orden = c.getValor();
        c.setValor(orden + 1);
        conf.startTransaction();
        conf.edit(c);
        conf.commitTransaction();
        return "O-" + orden;
    }

    public enum UpdateIpvAction {
        AGREGAR, REMOVER
    }
}

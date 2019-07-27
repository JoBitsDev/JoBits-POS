package restManager.controller.venta;

import GUI.Views.View;
import GUI.Views.util.CalcularCambioView;
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
import restManager.persistencia.NotificacionEnvioCocina;
import restManager.persistencia.Orden;
import restManager.persistencia.ProductoInsumo;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.ProductovOrden;
import restManager.persistencia.Venta;
import restManager.persistencia.models.ConfigDAO;
import restManager.persistencia.models.ConfiguracionDAO;
import restManager.persistencia.models.IpvRegistroDAO;
import restManager.persistencia.models.MesaDAO;
import restManager.persistencia.models.NotaDAO;
import restManager.persistencia.models.OrdenDAO;
import restManager.persistencia.models.ProductoVentaDAO;
import restManager.persistencia.models.ProductovOrdenDAO;
import restManager.printservice.Impresion;

import restManager.resources.R;
import restManager.util.utils;

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
        Mesa m;
        if (ConfiguracionDAO.getInstance().find(R.SettingID.GENERAL_MESA_FIJA_CAJERO.getValue()).getValor() == 0) {
            ArrayList<Mesa> mesas = (ArrayList<Mesa>) MesaDAO.getInstance().findAll();
            for (int i = 0; i < mesas.size();) {
                if (!mesas.get(i).getEstado().equals("vacia")) {
                    mesas.remove(i);
                } else {
                    i++;
                }
            }
            if (R.loggedUser.getPuestoTrabajonombrePuesto().getAreacodArea() != null) {
                for (int i = 0; i < mesas.size();) {
                    if (!mesas.get(i).getAreacodArea().equals(R.loggedUser.getPuestoTrabajonombrePuesto().getAreacodArea())) {
                        mesas.remove(i);
                    } else {
                        i++;
                    }
                }
            }
            m = (Mesa) showInputDialog(null, "Seleccione la mesa", "Mesas disponibles", mesas.toArray(), MesaDAO.getInstance().find(R.NO_MESA_CAJA));
        } else {
            m = MesaDAO.getInstance().find(R.NO_MESA_CAJA);
        }
        ret.setMesacodMesa(m);
        ret.setCodOrden(getOrdenCod());
        m.setEstado(ret.getCodOrden() + " " + R.loggedUser.getUsuario());
        ret.setPersonalusuario(R.loggedUser);
        ret.setDeLaCasa(false);
        ret.setPorciento(ret.getMesacodMesa().getAreacodArea().getPorcientoPorServicio().floatValue());
        ret.setHoraComenzada(new Date());
        ret.setOrdengastoEninsumos((float) 0);
        ret.setOrdenvalorMonetario((float) 0);
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
        if (autorize()) {
            Impresion i = new Impresion();
            instance = i.printKitchen(instance);
            RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.ENVIAR_COCINA, Level.FINEST, instance);
            update(instance);
            showSuccessDialog(getView(), "Productos enviados a cocina exitosamente");
        }
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
        if (autorize()) {
            Impresion i = new Impresion();
            setShowDialogs(true);
            if (showConfirmDialog(getView(), "Desea cerrar la orden " + instance.getCodOrden())) {
                setShowDialogs(false);
                boolean enviar = true;
                for (ProductovOrden x : instance.getProductovOrdenList()) {
                    if (x.getCantidad() != x.getEnviadosacocina()) {
                        showErrorDialog(getView(), "Existen productos que no han sido enviados a elaborar. Envie a elaborar antes de cerrar la orden");
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
            CalcularCambioView cambio = new CalcularCambioView(null, true, getInstance());
        }
    }

    public void updatePorciento(float f) {
        instance.setPorciento(f);
        view.updateValorTotal();
        update(instance);
    }

    public void removeProduct(ProductovOrden objectAtSelectedRow) {
        if (autorize()) {
            int index = instance.getProductovOrdenList().indexOf(objectAtSelectedRow);
            instance.getProductovOrdenList().get(index).setCantidad(0);
            Impresion i = new Impresion();
            i.printCancelationTicket(instance);
            getModel().startTransaction();
            for (NotificacionEnvioCocina x : instance.getProductovOrdenList().get(index).getNotificacionEnvioCocinaList()) {
                getModel().getEntityManager().remove(x);
            }
            getModel().commitTransaction();
            ProductovOrdenDAO.getInstance().remove(objectAtSelectedRow);
            instance.getProductovOrdenList().remove(objectAtSelectedRow);

            update(instance);
            view.updateValorTotal();
        }
    }

    public float getGastosInsumos(Orden instance) {
        float ret = 0;
        for (ProductovOrden x : instance.getProductovOrdenList()) {
            float check = 0;
            for (ProductoInsumo pi : x.getProductoVenta().getProductoInsumoList()) {
                check += pi.getCosto();
            }
            if (check != x.getProductoVenta().getGasto()) {
                x.getProductoVenta().setGasto(check);
                getModel().startTransaction();
                ProductoVentaDAO.getInstance().edit(x.getProductoVenta());
                getModel().commitTransaction();
            }
            ret += x.getProductoVenta().getGasto() * x.getCantidad();
        }
        return ret;
    }

    public float getValorTotal() {
        float total = 0;
        for (ProductovOrden x : getInstance().getProductovOrdenList()) {
            total += x.getCantidad() * x.getProductoVenta().getPrecioVenta();

        }
        instance.setOrdenvalorMonetario(utils.redondeoPorExcesoFloat(total * (1 + instance.getPorciento() / 100)));
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
        return ProductoVentaDAO.getInstance().findAllVisible(getInstance().getMesacodMesa());
    }

    public void addProduct(ProductoVenta selected) {
        if (autorize()) {

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
                founded.setCantidad(founded.getCantidad() + Float.parseFloat(showInputDialog(getView(), "Introduzca la cantidad de "+ founded.getProductoVenta())));
                ProductovOrdenDAO.getInstance().edit(founded);

            } else {
                founded = new ProductovOrden(selected.getPCod(), getInstance().getCodOrden());
                founded.setOrden(getInstance());
                founded.setProductoVenta(selected);
                founded.setCantidad(Float.parseFloat(showInputDialog(getView(), "Introduzca la cantidad de " + founded.getProductoVenta())));
                founded.setEnviadosacocina(0);
                founded.setNumeroComensal(0);
                ProductovOrdenDAO.getInstance().create(founded);
                getInstance().getProductovOrdenList().add(founded);
            }
            cantidadAgregada = founded.getCantidad();
            update(instance);
            view.updateValorTotal();
        }

    }

    private String getOrdenCod() {

        ConfigDAO conf = new ConfigDAO();
        Configuracion c = conf.find("O");
        getModel().getEntityManager().refresh(c);
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

    public boolean autorize() {
        return new LogInController().constructoAuthorizationView(null, instance.getPersonalusuario().getUsuario());
    }
}

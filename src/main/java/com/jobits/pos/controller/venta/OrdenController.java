package com.jobits.pos.controller.venta;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.adapters.repo.impl.ConfigDAO;
import com.jobits.pos.adapters.repo.impl.ConfiguracionDAO;
import com.jobits.pos.adapters.repo.impl.MesaDAO;
import com.jobits.pos.adapters.repo.impl.NotaDAO;
import com.jobits.pos.adapters.repo.impl.OrdenDAO;
import com.jobits.pos.adapters.repo.impl.ProductoVentaDAO;
import com.jobits.pos.adapters.repo.impl.ProductovOrdenDAO;
import com.jobits.pos.adapters.repo.impl.SeccionDAO;
import com.jobits.pos.adapters.repo.impl.VentaDAO;
import com.jobits.pos.controller.AbstractFragmentController;
import com.jobits.pos.controller.almacen.IPVController;
import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Configuracion;
import com.jobits.pos.domain.models.Mesa;
import com.jobits.pos.domain.models.Nota;
import com.jobits.pos.domain.models.NotaPK;
import com.jobits.pos.domain.models.NotificacionEnvioCocina;
import com.jobits.pos.domain.models.Orden;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.ProductovOrden;
import com.jobits.pos.domain.models.Seccion;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.exceptions.ValidatingException;
import com.jobits.pos.logs.RestManagerHandler;
import com.jobits.pos.recursos.R;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.servicios.impresion.formatter.AbstractTicketFormatter;
import com.jobits.pos.servicios.impresion.formatter.CancelacionCocinaFormatter;
import com.jobits.pos.servicios.impresion.formatter.CocinaFormatter;
import com.jobits.pos.servicios.impresion.formatter.OrdenFormatter;
import com.jobits.pos.ui.utils.CalcularCambioView;
import com.jobits.pos.ui.utils.utils;
import java.awt.Container;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class OrdenController extends AbstractFragmentController<Orden>
        implements OrdenService {

    private static final Logger LOGGER = Logger.getLogger(Venta.class.getSimpleName());
    private IPVController ipvController = new IPVController();
    protected final String SYNC = "Sale con: ";

    public OrdenController() {
        super(OrdenDAO.getInstance());
        init();
    }

    @Override
    public void addNota(String codOrden, ProductovOrden prod) {
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
            RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.SET_NOTA, Level.FINER, codOrden, n.getDescripcion());
            ProductovOrdenDAO.getInstance().edit(prod);

        } else {
            String notaAntigua = nota.getDescripcion();
            String nuevaNota = showInputDialog(getView(), "Edite la nota anterior", notaAntigua);
            nota.setDescripcion(nuevaNota);
            NotaDAO.getInstance().edit(nota);
            RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.SET_NOTA, Level.FINER, codOrden, nota.getDescripcion());
        }

    }

    //TODO:quitar los popups de aqui
    @Override
    public void addProduct(String codOrden, ProductoVenta selected) {
        if (autorize()) {
            Orden o = getInstance(codOrden);
            boolean found = false;
            ProductovOrden founded = null;
            float cantidadAgregada = 0;
            for (ProductovOrden x : new ArrayList<>(o.getProductovOrdenList())) {
                if (x.getProductoVenta().getCodigoProducto().equals(selected.getCodigoProducto())) {
                    founded = x;
                    found = true;
                    break;
                }
            }
            if (found) {
                cantidadAgregada = founded.getCantidad();
                float cantidad = Float.parseFloat(showInputDialog(getView(), "Introduzca la cantidad de " + founded.getProductoVenta()));
                if (!esDespachable(selected, o, cantidad)) {
                    throw new ValidatingException(getView(), "No hay existencias de" + selected + " para elaborar");
                }
                founded.setCantidad(founded.getCantidad() + cantidad);

            } else {
                founded = new ProductovOrden(selected.getCodigoProducto(), o.getCodOrden());
                founded.setOrden(o);
                founded.setProductoVenta(selected);
                founded.setCantidad(Float.parseFloat(showInputDialog(getView(), "Introduzca la cantidad de " + founded.getProductoVenta())));
                founded.setEnviadosacocina((float) 0);
                founded.setNumeroComensal(0);
                if (!esDespachable(selected, o, founded.getCantidad())) {
                    throw new ValidatingException(getView(), "No hay existencias de" + selected + " para elaborar. "
                            + "\n el producto se cambiará a no visible");
                }
                ProductovOrdenDAO.getInstance().create(founded);
            }
            if (getInstance(codOrden).getDeLaCasa()) {
                ipvController.consumirPorLaCasa(founded, founded.getCantidad());
            } else {
                ipvController.consumir(founded, founded.getCantidad());
            }
            if (found) {
                ProductovOrdenDAO.getInstance().edit(founded);
            } else {
                o.getProductovOrdenList().add(founded);
            }
            cantidadAgregada = founded.getCantidad() - cantidadAgregada;
            fireWarningOnAdding(founded, cantidadAgregada);
            update(getInstance(codOrden));
        }

    }

//    private void addProduct(String codOrden, ProductovOrden selected, float cantidad) {
//        if (autorize()) {
//            if (!esDespachable(selected.getProductoVenta(), selected.getOrden(), cantidad)) {
//                throw new ValidatingException(getView(), "No hay existencias de " + selected + " para elaborar");
//            }
//            selected.setCantidad(selected.getCantidad() + cantidad);
//            if (getInstance(codOrden).getDeLaCasa()) {
//                ipvController.consumirPorLaCasa(selected, cantidad);
//            } else {
//                ipvController.consumir(selected, cantidad);
//            }
//            ProductovOrdenDAO.getInstance().edit(selected);
//            fireWarningOnAdding(selected, cantidad);
//            update(instance);
//        }
//    }
//    
    public void addRapidoProducto(String codOrden, String idProducto) {
        ProductoVenta productoABuscar = ProductoVentaDAO.getInstance().find(idProducto);
        if (productoABuscar != null) {
            addProduct(codOrden, productoABuscar);
        }
    }

    @Override
    public void cerrarOrden(String codOrden) {
        if (autorize()) {
            Orden o = getInstance(codOrden);
            Impresion i = new Impresion();
            setShowDialogs(true);
            if (showConfirmDialog(getView(), "Desea cerrar la orden " + o.getCodOrden())) {
                setShowDialogs(false);
                boolean enviar = true;
                for (ProductovOrden x : o.getProductovOrdenList()) {
                    if (x.getCantidad() != x.getEnviadosacocina()) {
                        showErrorDialog(getView(), "Existen productos que no han sido enviados a elaborar. Envie a elaborar antes de cerrar la orden");
                        return;
                    }
                }
                if (enviar) {
                    if (showConfirmDialog(getView(), "Desea imprimir un ticket de la orden")) {
                        i.print(new OrdenFormatter(o, false), o.getMesacodMesa().getAreacodArea().getNombre());
                        RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.IMPRIMIRTICKET, Level.FINE, o);
                    }
                    o.setHoraTerminada(new Date());
                    o.setOrdengastoEninsumos(getGastosInsumos(o));
                    RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.CERRAR, Level.FINE, o);
                    Mesa m = getInstance().getMesacodMesa();
                    m.setEstado("vacia");
                    o.setMesacodMesa(m);
                    MesaDAO.getInstance().edit(m);
                    setDismissOnAction(true);
                    update(o, true);
                }
            }
            setShowDialogs(false);
            CalcularCambioView cambio = new CalcularCambioView(null, true, o);
        }
    }

    @Override
    public void constructView(java.awt.Container parent) {
        throw new IllegalStateException("Bad call");
    }

    @Override
    public Orden createNewInstance() {
        throw new IllegalStateException("Depracated");
    }

    @Override
    public Orden createNewInstance(String codMesa, String fechaPedido) {
        Orden ret = new Orden();
        Mesa m;
        if (ConfiguracionDAO.getInstance().find(R.SettingID.GENERAL_MESA_FIJA_CAJERO.getValue()).getValor() == 0) {
            ArrayList<Mesa> mesas = (ArrayList<Mesa>) MesaDAO.getInstance().findAll();
            for (int i = 0; i < mesas.size();) {
                getModel().getEntityManager().refresh(mesas.get(i));
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
            Collections.sort(mesas, (Mesa o1, Mesa o2) -> Integer.compare(Integer.parseInt(o1.getCodMesa().split("-")[1]), Integer.parseInt(o2.getCodMesa().split("-")[1])));
            m = (Mesa) showInputDialog(null, "Seleccione la mesa", "Mesas disponibles", mesas.toArray(), mesas.get(0));
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
        try {
            ret.setVentafecha(VentaDAO.getInstance().find(R.DATE_FORMAT.parse(fechaPedido)));
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Fecha de pedido en formato incorrecto " + fechaPedido + " formato correcto (dd/mm/yy)");
        }
        RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.CREADO, Level.FINE, ret);
        create(ret);
        return ret;
    }

    @Override
    public void enviarACocina(String codOrden) {
        if (autorize()) {
            Orden o = getInstance(codOrden);
            o = printKitchen(o);
            RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.ENVIAR_COCINA, Level.FINE, o);
            update(o);
            showSuccessDialog(getView(), "Productos enviados a cocina exitosamente");
        }
    }

    @Override
    public void setDeLaCasa(boolean selected) {
        getInstance().setDeLaCasa(selected);
        if (selected) {
            ipvController.consumirPorLaCasa(getInstance().getProductovOrdenList());
        } else {
            ipvController.devolverPorLaCasa(getInstance().getProductovOrdenList());
        }
    }

    public float getGastosInsumos(Orden instance) {
        float ret = 0;
        for (ProductovOrden x : getInstance().getProductovOrdenList()) {
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

    @Override
    public Orden getInstance(String codOrden) {
        Orden ret = OrdenDAO.getInstance().find(codOrden);
        if (ret == null) {
            throw new IllegalArgumentException("La orden " + codOrden + " no existe en el sistema");
        }
        return ret;
    }

    @Override
    public Orden getInstance() {
        throw new IllegalStateException("Depracated");
//        if (instance == null) {//TODO: borrar
//            instance = createNewInstance();
//        }
//        return instance;
    }

    @Override
    public void setInstance(Orden instance) {
        throw new IllegalStateException("Depracated");
    }

    public ArrayListModel<Mesa> getListaMesas(Area area_seleccionada) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Seccion> getListaSecciones() {
        List<Seccion> secciones = SeccionDAO.getInstance().findVisibleSecciones(getInstance().getMesacodMesa());
        Collections.sort(secciones, new Comparator<Seccion>() {
            @Override
            public int compare(Seccion o1, Seccion o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        return secciones;
    }

    @Override
    public List<ProductoVenta> getPDVList() {
        return ProductoVentaDAO.getInstance().findAllVisible(getInstance().getMesacodMesa());
    }

    @Override
    public void setParent(Container parent) {
        throw new IllegalStateException("Bad call");
    }

    @Override
    public void setPorciento(String codOrden, float f) {
        getInstance().setPorciento(f);
        RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.PORCIENTO_ACTUALIZADO, Level.WARNING, instance, f);
        //view.updateValorTotal();
        update(instance);
    }

    @Override
    public float getValorTotal(String codOrden) {
        float total = 0;
        Orden o = getInstance(codOrden);
        for (ProductovOrden x : o.getProductovOrdenList()) {
            total += x.getCantidad() * x.getProductoVenta().getPrecioVenta();

        }
        o.setOrdenvalorMonetario(AbstractTicketFormatter.REDONDEO_POR_EXCESO
                ? utils.redondeoPorExcesoFloat(total * (1 + (o.getPorciento() / 100)))
                : utils.setDosLugaresDecimalesFloat(total * (1 + (o.getPorciento() / 100))));
        update(o, true);
        return o.getOrdenvalorMonetario();
    }

    @Override
    public void imprimirPreTicket() {
        Impresion i = new Impresion();
        i.print(new OrdenFormatter(instance, true), getInstance().getMesacodMesa().getAreacodArea().getNombre());
        RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.IMPRIMIR_TICKET_PARCIAL, Level.FINE, instance);
    }

    @Override
    public void removeProduct(String codProducto, ProductovOrden selected, float diferencia) {
        if (autorize()) {
            int index = getInstance().getProductovOrdenList().indexOf(selected);
            float difer = getInstance().getProductovOrdenList().get(index).getCantidad();
            if (difer - diferencia == 0) {
                removeProduct(codProducto, selected);
                return;
            }
            getInstance().getProductovOrdenList().get(index).setCantidad(difer - diferencia);
            //Impresion i = new Impresion();
            printCancelationTicket(instance);
            if (getInstance().getDeLaCasa()) {
                ipvController.devolverPorLaCasa(selected, diferencia);
            } else {
                ipvController.devolver(selected, diferencia);
            }
            getModel().startTransaction();
            for (NotificacionEnvioCocina x : getInstance().getProductovOrdenList().get(index).getNotificacionEnvioCocinaList()) {
                float dif = x.getCantidad() - diferencia;
                if (dif <= 0) {
                    getModel().getEntityManager().remove(x);
                } else {
                    x.setCantidad(x.getCantidad());
                    getModel().getEntityManager().merge(x);
                }
            }
            getModel().commitTransaction();

            ProductovOrdenDAO.getInstance().edit(selected);
            fireWarningOnDeleting(selected, diferencia);

            update(instance);
        }
    }

    //
    //Metodos privados
    //
    private boolean autorize() {
        return new LogInController().constructoAuthorizationView(null, getInstance().getPersonalusuario().getUsuario());
    }

    private boolean esDespachable(ProductoVenta selected, Orden ordenVenta, float cantidad) {
        return selected.getCocinacodCocina().getLimitarVentaInsumoAgotado()
                ? new IPVController().hayDisponibilidad(selected, ordenVenta.getVentafecha().getFecha(), cantidad)
                : true;

    }

    private void fireWarningOnAdding(ProductovOrden producto, float cantidad) {
        //   Level l = getInstance().getHoraTerminada() != null ? Level.SEVERE : Level.WARNING;
        RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.AGREGAR, Level.FINE, producto, cantidad);
    }

    private void fireWarningOnDeleting(ProductovOrden producto, float cantidad) {
        Level l = getInstance().getHoraTerminada() != null ? Level.SEVERE : Level.WARNING;
        RestManagerHandler.Log(LOGGER, RestManagerHandler.Action.BORRAR, l, producto, cantidad);

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

    private void init() {
        setDismissOnAction(false);
        setShowDialogs(false);
        if (LOGGER.getHandlers().length == 0) {
            LOGGER.addHandler(new RestManagerHandler(Venta.class));
            LOGGER.setLevel(Level.FINE);
        }
    }

    private Orden printCancelationTicket(Orden o) {

        List<Cocina> cocinasExistentesEnLaOrden = new ArrayList<>();
        for (ProductovOrden x : o.getProductovOrdenList()) {
            if (!cocinasExistentesEnLaOrden.contains(x.getProductoVenta().getCocinacodCocina())) {
                cocinasExistentesEnLaOrden.add(x.getProductoVenta().getCocinacodCocina());
            }
        }
        if (cocinasExistentesEnLaOrden.size() > 1) {
            for (int i = 0; i < cocinasExistentesEnLaOrden.size(); i++) {
                String sync = SYNC;
                for (int j = 0; j < cocinasExistentesEnLaOrden.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    sync += cocinasExistentesEnLaOrden.get(j).getNombreCocina() + " ";
                }
                Impresion impresion = new Impresion();
                impresion.print(new CancelacionCocinaFormatter(o, cocinasExistentesEnLaOrden.get(i)), cocinasExistentesEnLaOrden.get(i).getNombreCocina());

                //printCancelationKitchen(o, cocinasExistentesEnLaOrden.get(i));
            }
        } else {
            if (cocinasExistentesEnLaOrden.size() > 0) {
                Impresion impresion = new Impresion();
                impresion.print(new CancelacionCocinaFormatter(o, cocinasExistentesEnLaOrden.get(0)), cocinasExistentesEnLaOrden.get(0).getNombreCocina());

                //printCancelationKitchen(o, cocinasExistentesEnLaOrden.get(0));
            }

        }

        return o;
    }

    private Orden printKitchen(Orden o) {
        printCancelationTicket(o);

        List<Cocina> cocinasExistentesEnLaOrden = new ArrayList<>();
        for (ProductovOrden x : o.getProductovOrdenList()) {
            if (!cocinasExistentesEnLaOrden.contains(x.getProductoVenta().getCocinacodCocina())) {
                cocinasExistentesEnLaOrden.add(x.getProductoVenta().getCocinacodCocina());
            }
        }
        if (cocinasExistentesEnLaOrden.size() > 1) {
            for (int i = 0; i < cocinasExistentesEnLaOrden.size(); i++) {
                String sync = SYNC;
                for (int j = 0; j < cocinasExistentesEnLaOrden.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    sync += cocinasExistentesEnLaOrden.get(j).getNombreCocina() + " ";
                }
                Impresion impresion = new Impresion();
                impresion.print(new CocinaFormatter(o, cocinasExistentesEnLaOrden.get(i), sync), cocinasExistentesEnLaOrden.get(i).getNombreCocina());
                //printKitchen(o, cocinasExistentesEnLaOrden.get(i), sync);
            }
        } else {
            if (cocinasExistentesEnLaOrden.size() > 0) {
                Impresion impresion = new Impresion();
                impresion.print(new CocinaFormatter(o, cocinasExistentesEnLaOrden.get(0), ""), cocinasExistentesEnLaOrden.get(0).getNombreCocina());
                //printKitchen(o, cocinasExistentesEnLaOrden.get(0), "");
            }
        }

        return o;

    }

    private void removeProduct(String codOrden, ProductovOrden objectAtSelectedRow) {
        Orden o = getInstance(codOrden);
        if (autorize()) {
            int index = o.getProductovOrdenList().indexOf(objectAtSelectedRow);
            float cantidadBorrada = getInstance().getProductovOrdenList().get(index).getCantidad();
            o.getProductovOrdenList().get(index).setCantidad(0);
            //Impresion i = new Impresion();
            printCancelationTicket(o);
            if (o.getDeLaCasa()) {
                ipvController.devolverPorLaCasa(objectAtSelectedRow, cantidadBorrada);
            } else {
                ipvController.devolver(objectAtSelectedRow, cantidadBorrada);
            }
            getModel().startTransaction();
            for (NotificacionEnvioCocina x : o.getProductovOrdenList().get(index).getNotificacionEnvioCocinaList()) {
                getModel().getEntityManager().remove(x);
            }
            getModel().commitTransaction();
            ProductovOrdenDAO.getInstance().remove(objectAtSelectedRow);
            o.getProductovOrdenList().remove(objectAtSelectedRow);

            fireWarningOnDeleting(objectAtSelectedRow, cantidadBorrada);
            update(o);
        }
    }

}

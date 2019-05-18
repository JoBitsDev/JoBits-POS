/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.almacen;

import java.awt.Container;
import java.awt.Window;

import java.util.Date;
import java.util.List;

import restManager.controller.AbstractDetailController;
import restManager.exceptions.ValidatingException;

import restManager.persistencia.Almacen;
import restManager.persistencia.Cocina;
import restManager.persistencia.Insumo;
import restManager.persistencia.Transaccion;
import restManager.persistencia.TransaccionEntrada;
import restManager.persistencia.TransaccionEntradaPK;
import restManager.persistencia.TransaccionMerma;
import restManager.persistencia.TransaccionMermaPK;
import restManager.persistencia.TransaccionPK;
import restManager.persistencia.models.CocinaDAO;
import restManager.persistencia.models.InsumoDAO;
import restManager.persistencia.models.TransaccionDAO;
import restManager.persistencia.models.TransaccionEntradaDAO;
import restManager.persistencia.models.TransaccionMermaDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class TransaccionDetailController extends AbstractDetailController<Transaccion> {

    Almacen a;

    public TransaccionDetailController() {
        super(TransaccionDAO.getInstance());
    }

    public TransaccionDetailController(Transaccion instance) {
        super(instance, TransaccionDAO.getInstance());
    }

    public TransaccionDetailController(Window parent, Almacen a) {
        super(TransaccionDAO.getInstance());
        this.parent = parent;
        this.a = a;
        constructView(parent);
    }

    public TransaccionDetailController(Transaccion instance, Window parent) {
        super(instance, parent, TransaccionDAO.getInstance());
    }

    @Override
    public Transaccion createNewInstance() {
        return null;
    }

    @Override
    public void constructView(Container parent) {
//        setView(new TransaccionDetailView(a, this, (AbstractView) parent));
//        getView().updateView();
//        getView().setVisible(true);
    }

    public List<Insumo> getInsumoList() {
        return InsumoDAO.getInstance().findAll();
    }

    public TransaccionEntrada addTransaccionEntrada(Insumo selected, Date fecha, Date hora, Almacen a) {
        try {
            TransaccionPK transPK = new TransaccionPK(selected.getCodInsumo(),
                    a.getCodAlmacen(), fecha, hora);
            Transaccion t = new Transaccion(transPK);
            t.setCantidad(Float.parseFloat(showInputDialog(getView(),
                    "Introduzca la cantidad de " + selected + " en " + selected.getUm() + " a dar entrada",
                    (float) 1)));
            t.setInsumo(selected);
            t.setAlmacen(a);
            TransaccionEntradaPK retPK
                    = new TransaccionEntradaPK(selected.getCodInsumo(),
                            t.getTransaccionPK().getFecha(),
                            t.getTransaccionPK().getHora(), a.getCodAlmacen());
            TransaccionEntrada ret = new TransaccionEntrada(retPK);
            ret.setJustificado(false);
            ret.setTransaccion(t);
            ret.setValorTotal(Float.parseFloat(showInputDialog(getView(), "Introduzca el Importe de la entrada"
                    + "\n(el importe mostrado es con el coste actual del insumo)", t.getCantidad() * selected.getCostoPorUnidad())));
            ret.setPrecioPorUnidad(ret.getValorTotal() / ret.getTransaccion().getCantidad());
            t.setTransaccionEntrada(ret);
            a.getTransaccionList().add(t);
            createNewTransaccionEntrada(ret);
            return ret;
        } catch (NumberFormatException ex) {
            throw new ValidatingException(getView());
        }
    }

    public Transaccion addTransaccionSalida(Insumo selected, Date fecha, Date hora, Almacen a, Cocina cocina) {
        try {
            TransaccionPK transPK = new TransaccionPK(selected.getCodInsumo(),
                    a.getCodAlmacen(), fecha, hora);
            Transaccion t = new Transaccion(transPK);
            t.setCantidad(Float.parseFloat(showInputDialog(getView(),
                    "Introduzca la cantidad de " + selected + " a dar Salida",
                    (float) 1)));
            t.setInsumo(selected);
            t.setAlmacen(a);
            t.setCocina(cocina);
            createNewTransaccionSalida(t);
            return t;
        } catch (NumberFormatException ex) {
            throw new ValidatingException(getView());
        }
    }

    void addTransaccionMerma(Insumo insumo, Date fecha, Date hora, Almacen a) {
        TransaccionPK transPK = new TransaccionPK(insumo.getCodInsumo(),
                a.getCodAlmacen(), fecha, hora);
        Transaccion t = new Transaccion(transPK);
        t.setCantidad(Float.parseFloat(showInputDialog(getView(),
                "Introduzca la cantidad de " + insumo + " en " + insumo.getUm() + " a rebajar",
                (float) 1)));
        t.setInsumo(insumo);
        t.setAlmacen(a);

        TransaccionMermaPK pk = new TransaccionMermaPK();
        pk.setTransaccionalmacencodAlmacen(a.getCodAlmacen());
        pk.setTransaccionfecha(fecha);
        pk.setTransaccionhora(hora);
        pk.setTransaccioninsumocodInsumo(insumo.getCodInsumo());
        TransaccionMerma rebaja = new TransaccionMerma(pk);
        rebaja.setTransaccion(t);
        rebaja.setRazon(showInputDialog(getView(), "Introduzca la causa de la Rebaja"));
        a.getTransaccionList().add(t);
        createNewTransaccionMerma(rebaja);
    }

    public void createNewTransaccionEntrada(TransaccionEntrada transaccion) {
        TransaccionEntradaDAO.getInstance().startTransaction();
        TransaccionEntradaDAO.getInstance().create(transaccion);
        TransaccionEntradaDAO.getInstance().commitTransaction();
        AlmacenManageController controller = new AlmacenManageController(transaccion.getTransaccion().getAlmacen());
        controller.setView(getView());
        controller.darEntradaAInsumo(transaccion.getTransaccion().getInsumo(), transaccion.getTransaccion().getCantidad(), transaccion.getValorTotal());
    }

    public void createNewTransaccionSalida(Transaccion transaccion) {
        TransaccionDAO.getInstance().startTransaction();
        AlmacenManageController almacenController = new AlmacenManageController(transaccion.getAlmacen());
        almacenController.setView(getView());
        almacenController.darSalidaAInsumo(transaccion);
        TransaccionDAO.getInstance().create(transaccion);
        getModel().commitTransaction();

    }

    public void createNewTransaccionMerma(TransaccionMerma transaccion) {
        TransaccionMermaDAO.getInstance().startTransaction();
        AlmacenManageController almacenController = new AlmacenManageController(transaccion.getTransaccion().getAlmacen());
        almacenController.setView(getView());
        almacenController.darMermaInsumo(transaccion.getTransaccion());
        TransaccionMermaDAO.getInstance().create(transaccion);
        getModel().commitTransaction();

    }

    public List<Cocina> getCocinaList() {
        return CocinaDAO.getInstance().findAll();
    }

}

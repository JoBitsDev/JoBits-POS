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
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.ValidatingException;

import restManager.persistencia.Almacen;
import restManager.persistencia.Cocina;
import restManager.persistencia.Insumo;
import restManager.persistencia.InsumoAlmacen;
import restManager.persistencia.Operacion;
import restManager.persistencia.Transaccion;
import restManager.persistencia.TransaccionEntrada;
import restManager.persistencia.TransaccionMerma;
import restManager.persistencia.TransaccionSalida;
import restManager.persistencia.TransaccionTransformacion;
import restManager.persistencia.TransaccionTransformacionPK;
import restManager.persistencia.TransaccionTraspaso;
import restManager.persistencia.models.CocinaDAO;
import restManager.persistencia.models.InsumoDAO;
import restManager.persistencia.models.TransaccionDAO;
import restManager.persistencia.models.TransaccionEntradaDAO;
import restManager.persistencia.models.TransaccionMermaDAO;
import restManager.persistencia.models.TransaccionSalidaDAO;
import restManager.persistencia.models.TransaccionTraspasoDAO;

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

    TransaccionEntrada addTransaccionEntrada(Operacion o, Insumo insumo, Date fecha, Date hora, Almacen a, float cantidad, float importe) {
        Transaccion t = nuevaTransaccion(o,insumo, fecha, hora, a, cantidad);
        TransaccionEntrada ret = new TransaccionEntrada(t.getNoTransaccion());
        ret.setJustificado(false);
        ret.setTransaccion(t);
        ret.setValorTotal(importe);
        ret.setPrecioPorUnidad(ret.getValorTotal() / ret.getTransaccion().getCantidad());
        t.setTransaccionEntrada(ret);
        createNewTransaccionEntrada(ret,a);
        return ret;

    }

    public Transaccion addTransaccionSalida(Operacion o, Insumo insumo, Date fecha, Date hora, Almacen a, Cocina cocina, float cantidad) {
        Transaccion t = nuevaTransaccion(o,insumo, fecha, hora, a, cantidad);

        TransaccionSalida salida = new TransaccionSalida(t.getNoTransaccion());
        salida.setTransaccion(t);
        salida.setCocinacodCocina(cocina);
        createNewTransaccionSalida(salida,a);
        return t;

    }

    public void addTransaccionRebaja(Operacion o, Insumo insumo, Date fecha, Date hora, Almacen a, float cantidad, String causaRebaja) {
        Transaccion t = nuevaTransaccion(o,insumo, fecha, hora, a, cantidad);
        TransaccionMerma rebaja = new TransaccionMerma(t.getNoTransaccion());
        rebaja.setTransaccion(t);
        rebaja.setRazon(causaRebaja);
        createNewTransaccionRebaja(rebaja,a);
    }

    public void addTransaccionTraspaso(Operacion o, Insumo insumo, Date fecha, Date hora, Almacen a, Almacen destinoTraspaso, float cantidad) {
        Transaccion t = nuevaTransaccion(o,insumo, fecha, hora, a, cantidad);
        TransaccionTraspaso traspaso = new TransaccionTraspaso(t.getNoTransaccion());
        traspaso.setTransaccion(t);
        traspaso.setAlmacenDestino(destinoTraspaso);
        createNewTransaccionTraspaso(traspaso,a);
    }

    public void addTransaccionTransformacion(InsumoAlmacen selected, Date fecha, Date hora, List<TransaccionTransformacion> items, float cantidad, float merma, Almacen destino) {
        Transaccion t = nuevaTransaccion(null,selected.getInsumo(), fecha, hora, selected.getAlmacen(), cantidad);
        float precioMedioNuevo = (cantidad * selected.getInsumo().getCostoPorUnidad()) / cantidad - merma;
        for (TransaccionTransformacion i : items) {
            TransaccionTransformacionPK pk = new TransaccionTransformacionPK(t.getNoTransaccion(), i.getInsumo().getCodInsumo());
            i.setTransaccion(t);
            i.setTransaccionTransformacionPK(pk);
            i.setCostoUnitario(precioMedioNuevo);
        }
        t.setTransaccionTransformacionList(items);
        createNewTransaccionTransformacion(t, destino,selected.getAlmacen());
    }

    public void createNewTransaccionEntrada(TransaccionEntrada transaccion, Almacen a) {
        getModel().startTransaction();
        AlmacenManageController controller = new AlmacenManageController(a);
        controller.setView(getView());
        controller.darEntradaAInsumo(transaccion);
        TransaccionEntradaDAO.getInstance().create(transaccion);
        TransaccionEntradaDAO.getInstance().commitTransaction();
    }

    public void createNewTransaccionSalida(TransaccionSalida transaccion,Almacen a) {
        getModel().startTransaction();
        AlmacenManageController almacenController = new AlmacenManageController(a);
        almacenController.setView(getView());
        almacenController.darSalidaAInsumo(transaccion);
        TransaccionSalidaDAO.getInstance().create(transaccion);
        getModel().commitTransaction();

    }

    public void createNewTransaccionRebaja(TransaccionMerma transaccion,Almacen a) {
        getModel().startTransaction();
        AlmacenManageController almacenController = new AlmacenManageController(a);
        almacenController.setView(getView());
        almacenController.darMermaInsumo(transaccion);
        TransaccionMermaDAO.getInstance().create(transaccion);
        getModel().commitTransaction();

    }

    private void createNewTransaccionTraspaso(TransaccionTraspaso transaccion,Almacen a) {
        getModel().startTransaction();
        AlmacenManageController almacenController = new AlmacenManageController(a);
        almacenController.setView(getView());
        almacenController.darTraspasoInsumo(transaccion);
        TransaccionTraspasoDAO.getInstance().create(transaccion);
        getModel().commitTransaction();
    }

    private void createNewTransaccionTransformacion(Transaccion t, Almacen a,Almacen origen) {
        TransaccionDAO.getInstance().startTransaction();
        AlmacenManageController almacenController = new AlmacenManageController(origen);
        almacenController.setView(getView());
        almacenController.darTransformacionAInsumo(t, a);
        almacenController.getCocinaList();
    }

    public List<Cocina> getCocinaList() {
        return CocinaDAO.getInstance().findAll();
    }

    private Transaccion nuevaTransaccion(Operacion o,Insumo insumo, Date fecha, Date hora, Almacen a, float cantidad) {
        Transaccion t = new Transaccion();
        if (o != null) {
            t.setOperacionnoOperacion(o);
        }
        t.setCantidad(cantidad);
        t.setInsumocodInsumo(insumo);
        t.setFecha(fecha);
        t.setHora(hora);
        TransaccionDAO.getInstance().create(t);
        return t;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.almacen;

import java.awt.Container;
import java.awt.Window;

import java.util.Date;
import java.util.List;

import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.exceptions.ValidatingException;

import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.InsumoAlmacen;
import com.jobits.pos.domain.models.Operacion;
import com.jobits.pos.domain.models.Transaccion;
import com.jobits.pos.domain.models.TransaccionEntrada;
import com.jobits.pos.domain.models.TransaccionMerma;
import com.jobits.pos.domain.models.TransaccionSalida;
import com.jobits.pos.domain.models.TransaccionTransformacion;
import com.jobits.pos.domain.models.TransaccionTransformacionPK;
import com.jobits.pos.domain.models.TransaccionTraspaso;
import com.jobits.pos.adapters.repo.impl.CocinaDAO;
import com.jobits.pos.adapters.repo.impl.InsumoDAO;
import com.jobits.pos.adapters.repo.impl.TransaccionDAO;
import com.jobits.pos.adapters.repo.impl.TransaccionEntradaDAO;
import com.jobits.pos.adapters.repo.impl.TransaccionMermaDAO;
import com.jobits.pos.adapters.repo.impl.TransaccionSalidaDAO;
import com.jobits.pos.adapters.repo.impl.TransaccionTraspasoDAO;
import com.jobits.pos.recursos.R;

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
        instance = createNewInstance();
    }

    public TransaccionDetailController(Transaccion instance) {
        super(instance, TransaccionDAO.getInstance());
    }

    public TransaccionDetailController(Window parent, Almacen a) {
        this();
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
//        setView(new TransaccionDetailView(a, this, (OldAbstractView) parent));
//        getView().updateView();
//        getView().setVisible(true);
    }

    public List<Insumo> getInsumoList() {
        return InsumoDAO.getInstance().findAll();
    }

    TransaccionEntrada addTransaccionEntrada(Operacion o, Insumo insumo, Date fecha, Date hora, Almacen a, float cantidad, float importe) {
        Transaccion t = nuevaTransaccion(o, insumo, fecha, hora, a, cantidad);
        TransaccionEntrada ret = new TransaccionEntrada(t.getNoTransaccion());
        ret.setJustificado(false);
        ret.setTransaccion(t);
        ret.setValorTotal(importe);
        ret.setPrecioPorUnidad(ret.getValorTotal() / ret.getTransaccion().getCantidad());
        t.setTransaccionEntrada(ret);
        createNewTransaccionEntrada(ret, a);
        return ret;

    }

    public Transaccion addTransaccionSalida(Operacion o, Insumo insumo, Date fecha, Date hora, Almacen a, Cocina cocina, float cantidad) {
        Transaccion t = nuevaTransaccion(o, insumo, fecha, hora, a, cantidad);

        TransaccionSalida salida = new TransaccionSalida(t.getNoTransaccion());
        salida.setTransaccion(t);
        salida.setCocinacodCocina(cocina);
        createNewTransaccionSalida(salida, a);
        return t;

    }

    public void addTransaccionRebaja(Operacion o, Insumo insumo, Date fecha, Date hora, Almacen a, float cantidad, String causaRebaja) {
        Transaccion t = nuevaTransaccion(o, insumo, fecha, hora, a, cantidad);
        TransaccionMerma rebaja = new TransaccionMerma(t.getNoTransaccion());
        rebaja.setTransaccion(t);
        rebaja.setRazon(causaRebaja);
        createNewTransaccionRebaja(rebaja, a);
    }

    public void addTransaccionTraspaso(Operacion o, Insumo insumo, Date fecha, Date hora, Almacen a, Almacen destinoTraspaso, float cantidad) {
        Transaccion t = nuevaTransaccion(o, insumo, fecha, hora, a, cantidad);
        TransaccionTraspaso traspaso = new TransaccionTraspaso(t.getNoTransaccion());
        traspaso.setTransaccion(t);
        traspaso.setAlmacenDestino(destinoTraspaso);
        createNewTransaccionTraspaso(traspaso, a);
    }

    public void addTransaccionTransformacion(InsumoAlmacen selected, Date fecha, Date hora, List<TransaccionTransformacion> items, float cantidad, float merma, Almacen destino) {
        Transaccion t = nuevaTransaccion(new Operacion(), selected.getInsumo(), fecha, hora, selected.getAlmacen(), cantidad);
        float precioMedioNuevo = (cantidad * selected.getInsumo().getCostoPorUnidad()) / cantidad - merma;
        for (TransaccionTransformacion i : items) {
            TransaccionTransformacionPK pk = new TransaccionTransformacionPK(t.getNoTransaccion(), i.getInsumo().getCodInsumo());
            i.setTransaccion(t);
            i.setTransaccionTransformacionPK(pk);
            i.setCostoUnitario(precioMedioNuevo);
        }
        t.setTransaccionTransformacionList(items);
        createNewTransaccionTransformacion(t, destino, selected.getAlmacen());
    }

    public void createNewTransaccionEntrada(TransaccionEntrada transaccion, Almacen a) {
        getModel().startTransaction();
        AlmacenManageController controller = new AlmacenManageController(a);
        controller.setView(getView());
        controller.darEntradaAInsumo(transaccion);
        TransaccionEntradaDAO.getInstance().create(transaccion);
        TransaccionEntradaDAO.getInstance().commitTransaction();
    }

    public void createNewTransaccionSalida(TransaccionSalida transaccion, Almacen a) {
        getModel().startTransaction();
        AlmacenManageController almacenController = new AlmacenManageController(a);
        almacenController.setView(getView());
        almacenController.darSalidaAInsumo(transaccion);
        TransaccionSalidaDAO.getInstance().create(transaccion);
        getModel().commitTransaction();

    }

    public void createNewTransaccionRebaja(TransaccionMerma transaccion, Almacen a) {
        getModel().startTransaction();
        AlmacenManageController almacenController = new AlmacenManageController(a);
        almacenController.setView(getView());
        almacenController.darMermaInsumo(transaccion);
        TransaccionMermaDAO.getInstance().create(transaccion);
        getModel().commitTransaction();

    }

    private void createNewTransaccionTraspaso(TransaccionTraspaso transaccion, Almacen a) {
        getModel().startTransaction();
        AlmacenManageController almacenController = new AlmacenManageController(a);
        almacenController.setView(getView());
        almacenController.darTraspasoInsumo(transaccion);
        TransaccionTraspasoDAO.getInstance().create(transaccion);
        getModel().commitTransaction();
    }

    private void createNewTransaccionTransformacion(Transaccion t, Almacen a, Almacen origen) {
        TransaccionDAO.getInstance().startTransaction();
        AlmacenManageController almacenController = new AlmacenManageController(origen);
        almacenController.setView(getView());
        almacenController.darTransformacionAInsumo(t, a);
        almacenController.getCocinaList();
    }

    public List<Cocina> getCocinaList() {
        return CocinaDAO.getInstance().findAll();
    }

    private Transaccion nuevaTransaccion(Operacion o, Insumo insumo, Date fecha, Date hora, Almacen a, float cantidad) {
        Transaccion t = new Transaccion();
//        if (o != null) {
        o.setAlmacen(a);
        t.setOperacionnoOperacion(o);
//        }
        t.setDescripcion(R.loggedUser.getUsuario());
        t.setCantidad(cantidad);
        t.setInsumocodInsumo(insumo);
        t.setFecha(fecha);
        t.setHora(hora);
        TransaccionDAO.getInstance().create(t);
        return t;
    }

}

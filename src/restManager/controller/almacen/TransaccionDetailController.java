/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.almacen;

import GUI.Views.AbstractView;
import GUI.Views.Almacen.TransaccionDetailView;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Window;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractDialogController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.ValidatingException;
import restManager.persistencia.Almacen;
import restManager.persistencia.Insumo;
import restManager.persistencia.Transaccion;
import restManager.persistencia.TransaccionEntrada;
import restManager.persistencia.TransaccionEntradaPK;
import restManager.persistencia.TransaccionPK;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.InsumoDAO;
import restManager.persistencia.models.TransaccionDAO;
import restManager.persistencia.models.TransaccionEntradaDAO;

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
        setView(new TransaccionDetailView(a,this, (AbstractView) parent));
        getView().updateView();
        getView().setVisible(true);
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
                    "Introduzca la cantidad de " + selected + " a dar entrada",
                    (float) 1)));
            t.setInsumo(selected);
            t.setAlmacen(a);
            t.setCocinaList(new ArrayList<>());
            TransaccionEntradaPK retPK
                    = new TransaccionEntradaPK(selected.getCodInsumo(),
                            t.getTransaccionPK().getFecha(),
                            t.getTransaccionPK().getHora(), a.getCodAlmacen());
            TransaccionEntrada ret = new TransaccionEntrada(retPK);
            ret.setConsumido(false);
            ret.setTransaccion(t);
            ret.setValorTotal(Float.parseFloat(showInputDialog(getView(), "Introduzca el costo de la entrada")));
            ret.setPrecioPorUnidad(ret.getValorTotal()/ret.getTransaccion().getCantidad());
            t.setTransaccionEntrada(ret);

            return ret;
        } catch (NumberFormatException ex) {
            throw new ValidatingException(getView());
        }
    }

    public void createNewTransaccionEntrada(ArrayList<TransaccionEntrada> transacciones) {
        for (TransaccionEntrada x : transacciones) {
            TransaccionEntradaDAO.getInstance().startTransaction();
            TransaccionEntradaDAO.getInstance().create(x);
            TransaccionEntradaDAO.getInstance().commitTransaction();
            
        }
    }

}

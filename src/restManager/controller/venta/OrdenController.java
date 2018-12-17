package restManager.controller.venta;

import GUI.Views.AbstractFragmentView;
import GUI.Views.View;
import GUI.Views.venta.OrdenDetailFragmentView;

import java.awt.Window;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDialog;
import restManager.controller.AbstractDetailController;
import restManager.exceptions.DevelopingOperationException;
import restManager.logs.RestManagerHandler;

import restManager.persistencia.Mesa;
import restManager.persistencia.Nota;
import restManager.persistencia.NotaPK;
import restManager.persistencia.Orden;
import restManager.persistencia.ProductovOrden;
import restManager.persistencia.Venta;
import restManager.persistencia.models.MesaDAO;
import restManager.persistencia.models.NotaDAO;
import restManager.persistencia.models.OrdenDAO;
import restManager.persistencia.models.ProductovOrdenDAO;
import restManager.printservice.Impresion;

import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class OrdenController extends AbstractDetailController<Orden> {

    Venta fechaOrden;
    private static final Logger LOGGER = Logger.getLogger(Venta.class.getSimpleName());
    private View v;

    public OrdenController(Venta fecha) {
        super(OrdenDAO.getInstance());
        this.fechaOrden = fecha;
        setDismissOnAction(false);
        setAutoShowDialogs(false);
    }

    public OrdenController(Orden instance) {
        super(instance, OrdenDAO.getInstance());
        setDismissOnAction(false);
        setAutoShowDialogs(false);
    }

    public OrdenController(Window parent, Venta fecha) {
        super(parent, OrdenDAO.getInstance());
        this.fechaOrden = fecha;
        setDismissOnAction(false);
        setAutoShowDialogs(false);
    }

    public OrdenController(Orden instance, Window parent) {
        super(instance, parent, OrdenDAO.getInstance());
        setDismissOnAction(false);
        setAutoShowDialogs(false);
    }

    @Override
    public Orden createNewInstance() {
        Orden ret = new Orden();
        ret.setCodOrden(getModel().generateStringCode("O-"));
        ret.setPersonalusuario(R.loggedUser);
        ret.setDeLaCasa(false);
        ret.setGananciaXporciento(Float.valueOf("0"));
        ret.setHoraComenzada(new Date());
        ret.setOrdengastoEninsumos((float) 0);
        ret.setOrdenvalorMonetario((float) 0);
        ret.setPorciento(R.PERCENTAGE);
        ret.setProductovOrdenList(new ArrayList<>());
        ret.setVentafecha(fechaOrden);
        return ret;

    }

    @Override
    public void constructView(Window parent) {
        setV(new OrdenDetailFragmentView(instance, this, (JDialog) parent));
        getV().updateView();
        getV().setVisible(true);
    }

    public AbstractFragmentView<Orden> getV() {
        return (AbstractFragmentView<Orden>) v;
    }

    public void setV(View v) {
        this.v = v;
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
        if (showConfirmDialog(getView(), "Desea cerrar la orden " + instance.getCodOrden())) {
            boolean enviar = true;
            for (ProductovOrden x : instance.getProductovOrdenList()) {
                if (x.getCantidad() != x.getEnviadosacocina()) {
                    showErrorDialog(getView(), "Existen productos que no han sido enviados a cocina. Envie a cocina antes de cerrar la orden");
                    return;
                }
            }
            if (enviar) {
                if (showConfirmDialog(getView(), "Desea imprimir un ticket de la orden")) {
                    Impresion i = new Impresion();
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
                setAutoShowDialogs(true);
                update(instance);

            }
        }
    }

    public void updatePorciento(float f) {
        instance.setPorciento(f);
        instance.setOrdenvalorMonetario(instance.getOrdenvalorMonetario() * (1 + f));
        update(instance);
    }

    public void removeProduct(ProductovOrden objectAtSelectedRow) {
        ProductovOrdenDAO.getInstance().remove(objectAtSelectedRow);
        instance.getProductovOrdenList().remove(objectAtSelectedRow);
        update(instance);
    }

    private float getGastosInsumos(Orden instance) {
        float ret = 0;
        for (ProductovOrden x : instance.getProductovOrdenList()) {
            ret += x.getProductoVenta().getGasto();
        }
        return ret;
    }

    public void setValorTotal(float total) {
        instance.setOrdenvalorMonetario(total);
        update(instance);
    }

}

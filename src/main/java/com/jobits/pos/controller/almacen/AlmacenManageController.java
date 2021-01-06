/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.almacen;

import java.awt.Window;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;

import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.insumo.InsumoDetailController;
import com.jobits.pos.controller.login.LogInController;
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
import com.jobits.pos.domain.models.TransaccionTraspaso;
import com.jobits.pos.adapters.repo.impl.AlmacenDAO;
import com.jobits.pos.adapters.repo.impl.CocinaDAO;
import com.jobits.pos.adapters.repo.impl.InsumoAlmacenDAO;
import com.jobits.pos.adapters.repo.impl.InsumoDAO;
import com.jobits.pos.adapters.repo.impl.OperacionDAO;
import com.jobits.pos.adapters.repo.impl.TransaccionDAO;
import com.jobits.pos.adapters.repo.impl.TransaccionEntradaDAO;
import com.jobits.pos.adapters.repo.impl.TransaccionMermaDAO;
import com.jobits.pos.domain.TransaccionSimple;
import com.jobits.pos.domain.models.InsumoElaborado;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.servicios.impresion.formatter.AlmacenFormatter;
import com.jobits.pos.servicios.impresion.formatter.StockBalanceFormatter;
import com.jobits.pos.utils.utils;
import java.util.Collections;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class AlmacenManageController extends AbstractDetailController<Almacen> implements AlmacenManageService {

    public AlmacenManageController(Almacen a) {
        super(a, AlmacenDAO.getInstance());
    }

    public AlmacenManageController(Window parent, Almacen a) {
        super(a, parent, AlmacenDAO.getInstance());
//        getModel().getEntityManager().refresh(getInstance());
        TransaccionDAO.getInstance().addPropertyChangeListener(this);
        InsumoAlmacenDAO.getInstance().addPropertyChangeListener(this);
        TransaccionEntradaDAO.getInstance().addPropertyChangeListener(this);
        TransaccionMermaDAO.getInstance().addPropertyChangeListener(this);
        // constructView(parent);
    }

    //
    // Operaciones del controlador
    //
    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
//        setView(new OldAlmacenEditView(this, (Frame) parent, getInstance()));
        getView().updateView();
        getView().fetchComponentData();
        getView().setVisible(true);
    }

    @Override
    public void imprimirReporteParaCompras(Almacen a, int selection) {
        switch (selection) {
            case 0:
                contructTableForPrintingAndPrint(a);
                break;//impresion normal
            case 1:
                contructTicketAndPrint(a);
                break;//impresion ticket
            default:
                break;//cancelado
        }

    }

    @Override
    public void modificarStock(Insumo i) {
        //InsumoDetailController insumoController = new InsumoDetailController(i, getView());
        // getView().updateView();
    }

    @Override
    public void imprimirResumenAlmacen(Almacen a) {
        Impresion i = new Impresion();
        i.print(new AlmacenFormatter(a), null);

    }

    @Override
    public List<InsumoAlmacen> getInsumoAlmacenList(Almacen a) {
        List<InsumoAlmacen> retSorted = a.getInsumoAlmacenList();
        Collections.sort(retSorted);
        return a.getInsumoAlmacenList();
    }

    @Override
    public List<Insumo> getInsumoList() {
        return InsumoDAO.getInstance().findAll();
    }

    @Override
    public void verTransacciones(Almacen a) {
//        TransaccionesListController controller = new TransaccionesListController(getView(), a);
    }

    @Override
    public Almacen createNewInstance() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cocina> getCocinaList() {
        return CocinaDAO.getInstance().findAll();
    }

    @Override
    public void darTraspasoInsumo(TransaccionTraspaso x) throws ValidatingException {
        InsumoAlmacen desde = AlmacenDAO.getInstance().findInsumo(getInstance().getCodAlmacen(), x.getTransaccion().getInsumocodInsumo().getCodInsumo());
        InsumoAlmacen hasta;
        try {
            hasta = AlmacenDAO.getInstance().findInsumo(x.getAlmacenDestino().getCodAlmacen(), x.getTransaccion().getInsumocodInsumo().getCodInsumo());
        } catch (NoResultException ex) {
            throw new IllegalAccessError(
                    "NO existe " + x.getTransaccion().getInsumocodInsumo() + " en " + x.getAlmacenDestino());
        }
        float precioMedio = utils.redondeoPorExcesoFloat(desde.getValorMonetario() / desde.getCantidad());
        desde.setCantidad(desde.getCantidad() - x.getTransaccion().getCantidad());
        desde.setValorMonetario(desde.getValorMonetario() - x.getTransaccion().getCantidad() * precioMedio);

        hasta.setCantidad(hasta.getCantidad() + x.getTransaccion().getCantidad());
        hasta.setValorMonetario(hasta.getValorMonetario() + x.getTransaccion().getCantidad() * precioMedio);
        getModel().startTransaction();
        InsumoAlmacenDAO.getInstance().edit(desde);
        InsumoAlmacenDAO.getInstance().edit(hasta);
        getModel().commitTransaction();
        updateValorTotalAlmacen(instance);
        updateValorTotalAlmacen(x.getAlmacenDestino());

    }

    @Override
    public void createInsumo(InsumoAlmacen newInsumo) {
        InsumoAlmacenDAO.getInstance().create(newInsumo);
    }

    @Override
    public void removeInsumoFromStorage(InsumoAlmacen objectAtSelectedRow) {
        if (showConfirmDialog(getView(), "Desea eliminar las existencias de " + objectAtSelectedRow.getInsumo() + " del almacen")) {
            getModel().startTransaction();
            InsumoAlmacenDAO.getInstance().remove(objectAtSelectedRow);
            getInstance().getInsumoAlmacenList().remove(objectAtSelectedRow);
            getModel().commitTransaction();
        }
    }

    @Override
    public void setCentroElaboracion(boolean selected) {
        instance.setCentroElaboracion(selected);
        update(instance, true);
    }

    @Override
    public void crearTransformacion(InsumoAlmacen selected, float cantidad, List<TransaccionTransformacion> items, Almacen destino) {
        // Validaciones
        if (selected.getCantidad() < cantidad || cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a transformar no puede ser mayor que la cantidad existente en almacen"
                    + "\n Ni la cantidad a transformar ser igual o menor que cero ");
        }

        if (items.isEmpty()) {
            throw new IllegalArgumentException("La lista de insumos transformados esta vacia");
        }
        float sumaTransformacion = 0;
        for (TransaccionTransformacion i : items) {
            sumaTransformacion += i.getCantidadUsada();
            boolean flag = true;
            for (InsumoElaborado x : selected.getInsumo().getInsumoDerivadoList()) {
                if (x.getInsumo_derivado_nombre().equals(i.getInsumo())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                throw new IllegalArgumentException("El insumo " + i.getInsumo() + " no es un insumo derivado de " + selected.getInsumo()
                        + "\n y no es posible transformarlo");
            }
            if (AlmacenDAO.getInstance().findInsumo(destino.getCodAlmacen(), i.getInsumo().getCodInsumo()) == null) {
                throw new IllegalArgumentException("El insumo " + i.getInsumo() + " no se encuentra en el almacen destino (" + destino + ")");
            }
            if (i.getCantidadCreada() <= 0) {
                throw new IllegalArgumentException("Las cantidades creadas deben ser mayor que cero");
            }
        }
        if (sumaTransformacion > cantidad) {
            throw new IllegalArgumentException("La cantidad total transformada en insumos no puede ser mayor que la cantidad a transformar");
        }

        float merma = utils.setDosLugaresDecimalesFloat(sumaTransformacion - cantidad);
        if (sumaTransformacion < cantidad) {
            if (showConfirmDialog(getView(), selected.getInsumo() + " mermara " + merma + ". Desea continuar?")) {
                if (!new LogInController().constructoAuthorizationView(R.NivelAcceso.ECONOMICO)) {
                    return;
                }
            } else {
                return;
            }
        }
        TransaccionDetailService controller = new TransaccionDetailController();
        controller.addTransaccionTransformacion(selected, new Date(), new Date(), items, cantidad, merma, destino);
    }

    @Override
    public InsumoAlmacen findInsumo(Insumo ins) {
        for (InsumoAlmacen i : getInsumoAlmacenList(getInstance())) {
            if (i.getInsumo().getCodInsumo().equals(ins.getCodInsumo())) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void agregarInsumoAlmacen(Insumo i) {
        if (findInsumo(i) == null) {
            InsumoAlmacen insumoAlmacen = new InsumoAlmacen(
                    i.getCodInsumo(), getInstance().getCodAlmacen());
            insumoAlmacen.setInsumo(i);
            insumoAlmacen.setCantidad(0f);
            insumoAlmacen.setValorMonetario(0f);
            getModel().startTransaction();
            getInsumoAlmacenList(getInstance()).add(insumoAlmacen);
            getModel().commitTransaction();
        } else {
            throw new IllegalStateException("El Insumo ya se encuentra registrado en " + getInstance().getNombre());
        }
    }

    //
    //Accesibles por otros controladores
    //
    @Override
    public void darEntradaAInsumo(TransaccionEntrada x) {
        darEntradaAInsumo(x.getTransaccion().getInsumocodInsumo(), x.getTransaccion().getCantidad(), x.getValorTotal());
    }

    private void darEntradaAInsumo(Insumo i, float cantidad, float montoFactura) {
        InsumoAlmacen ins = AlmacenDAO.getInstance().findInsumo(getInstance().getCodAlmacen(), i.getCodInsumo());
        ins.setCantidad(ins.getCantidad() + cantidad);
        ins.setValorMonetario(ins.getValorMonetario() + montoFactura);
        updateInsumo(ins);
        if (ins.getValorMonetario() / ins.getCantidad() != i.getCostoPorUnidad()) {
            if (showConfirmDialog(getView(), "Actualizar el costo del insumo " + i + " \n de "
                    + i.getCostoPorUnidad() + R.COIN_SUFFIX + " a " + utils.setDosLugaresDecimales(ins.getValorMonetario() / ins.getCantidad()))) {
                i.setCostoPorUnidad(utils.setDosLugaresDecimalesFloat(ins.getValorMonetario() / ins.getCantidad()));
                InsumoDetailController controller = new InsumoDetailController();
                controller.setView(getView());
                controller.setShowDialogs(false);
                controller.update(i, true);
                controller.updateProductoOnInsumo(i);
            }
        }
        updateValorTotalAlmacen(instance);
    }

    @Override
    public void darSalidaAInsumo(TransaccionSalida x, int idVenta) throws IllegalArgumentException {
        IPVController controller = new IPVController();
        controller.setView(getView());
        InsumoAlmacen insumoADarSalida = AlmacenDAO.getInstance().findInsumo(getInstance().getCodAlmacen(), x.getTransaccion().getInsumocodInsumo().getCodInsumo());

        if (insumoADarSalida.getCantidad() < x.getTransaccion().getCantidad()) {
            if (getModel().getEntityManager().getTransaction().isActive()) {
                getModel().getEntityManager().getTransaction().rollback();
            }
            throw new IllegalArgumentException("No hay suficiente cantidad de " + x.getTransaccion().getInsumocodInsumo() + " para extraer del almacen");
        }
        controller.darEntradaExistencia(x.getTransaccion().getInsumocodInsumo(), x.getCocinacodCocina(), idVenta, x.getTransaccion().getCantidad());
        float precioMedio = insumoADarSalida.getValorMonetario() / insumoADarSalida.getCantidad();
        insumoADarSalida.setCantidad(insumoADarSalida.getCantidad() - x.getTransaccion().getCantidad());
        insumoADarSalida.setValorMonetario(insumoADarSalida.getValorMonetario() - x.getTransaccion().getCantidad() * precioMedio);
        updateInsumo(insumoADarSalida);
        updateValorTotalAlmacen(instance);
    }

    @Override
    public void darMermaInsumo(TransaccionMerma x) throws IllegalArgumentException {
        darMermaInsumo(x.getTransaccion().getInsumocodInsumo(), x.getTransaccion().getCantidad());
    }

    private void darMermaInsumo(Insumo i, float cantidad) throws ValidatingException {
        InsumoAlmacen insumoaRebajar = AlmacenDAO.getInstance().findInsumo(getInstance().getCodAlmacen(), i.getCodInsumo());

        if (insumoaRebajar.getCantidad() < cantidad) {
            if (getModel().getEntityManager().getTransaction().isActive()) {
                getModel().getEntityManager().getTransaction().rollback();
            }
            throw new IllegalArgumentException("No hay suficiente cantidad de " + i + " para extraer del almacen");
        }
        float precioMedio = insumoaRebajar.getValorMonetario() / insumoaRebajar.getCantidad();
        insumoaRebajar.setCantidad(insumoaRebajar.getCantidad() - cantidad);
        insumoaRebajar.setValorMonetario(insumoaRebajar.getValorMonetario() - cantidad * precioMedio);
        getModel().startTransaction();
        InsumoAlmacenDAO.getInstance().edit(insumoaRebajar);
        getModel().commitTransaction();
        updateValorTotalAlmacen(instance);
    }

    @Override
    public void darTransformacionAInsumo(Transaccion t, Almacen a) {
        darMermaInsumo(t.getInsumocodInsumo(), t.getCantidad());
        InsumoAlmacen ins = AlmacenDAO.getInstance().findInsumo(a.getCodAlmacen(), t.getInsumocodInsumo().getCodInsumo());
        float precioMedio = ins.getValorMonetario() / ins.getCantidad();
        for (TransaccionTransformacion x : t.getTransaccionTransformacionList()) {
            darEntradaAInsumo(x.getInsumo(), x.getCantidadCreada(), precioMedio * x.getCantidadCreada());
        }
    }

    //
    // Metodos Privados
    //
    private void updateInsumo(InsumoAlmacen ins) {
        InsumoAlmacenDAO.getInstance().startTransaction();
        InsumoAlmacenDAO.getInstance().edit(ins);
        InsumoAlmacenDAO.getInstance().commitTransaction();
    }

    private void contructTableForPrintingAndPrint(Almacen a) {
        throw new UnsupportedOperationException();
    }

//    private boolean printOverStockedInsumos() {
//        return JOptionPane.showConfirmDialog(getView(),
//                R.RESOURCE_BUNDLE.getString("dialog_imprimir_insumos_sobrantes")) == JOptionPane.YES_OPTION;
//    }
    private void contructTicketAndPrint(Almacen a) {
        Impresion.getDefaultInstance().print(new StockBalanceFormatter(a), null);
    }

    @Override
    public void updateValorTotalAlmacen(Almacen instance) {
        double total = 0;
        instance = getModel().find(instance.getCodAlmacen());
        for (InsumoAlmacen x : instance.getInsumoAlmacenList()) {
            if (x.getValorMonetario() != null) {
                total += utils.setDosLugaresDecimalesFloat(x.getValorMonetario());
            }
        }
        instance.setValorMonetario(utils.setDosLugaresDecimalesDouble(total));
        update(instance, true);
        getModel().getEntityManager().getEntityManagerFactory().getCache().evict(Almacen.class
        );
    }

    private Operacion createOperacion(String recibo, Date fechaFactura) {
        Operacion o = new Operacion();
        o.setAlmacen(getInstance());
        o.setFecha(fechaFactura);
        o.setHora(new Date());
        o.setNoRecibo(recibo);
        getModel().startTransaction();
        OperacionDAO.getInstance().create(o);
        getModel().commitTransaction();
        return o;
    }

    @Override
    public void crearOperacionEntrada(ArrayList<TransaccionSimple> transacciones, String recibo, Date fechaFactura) {
        Operacion o = createOperacion(recibo, fechaFactura);
        TransaccionDetailService controller = new TransaccionDetailController();
        for (TransaccionSimple t : transacciones) {
            getModel().startTransaction();
//            if (showConfirmDialog(getView(), "Desea dar entrada a " + cantidad + ins.getInsumo().getUm() + "\n de " + ins.getInsumo() + " por " + importe + R.COIN_SUFFIX)) {
            controller.addTransaccionEntrada(o, t.getInsumo().getInsumo(), R.TODAYS_DATE, new Date(), getInstance(), t.getCantidad(), t.getMonto());
//            }
            getModel().commitTransaction();
        }
        updateValorTotalAlmacen(getInstance());
    }

    @Override
    public void crearOperacionRebaja(ArrayList<TransaccionSimple> transacciones, String recibo, Date fechaFactura) {
        Operacion o = createOperacion(recibo, fechaFactura);
        TransaccionDetailService controller = new TransaccionDetailController();
        for (TransaccionSimple t : transacciones) {
            getModel().startTransaction();
//            if (showConfirmDialog(getView(), "Desea rebajar  " + cantidad + ins.getInsumo().getUm() + "\n de " + ins.getInsumo() + " debido a " + causaRebaja)) {
            controller.addTransaccionRebaja(o, t.getInsumo().getInsumo(), R.TODAYS_DATE, new Date(), getInstance(), t.getCantidad(), t.getCausa());
//            }
            getModel().commitTransaction();
        }
        updateValorTotalAlmacen(getInstance());
    }

    @Override
    public void crearOperacionSalida(ArrayList<TransaccionSimple> transacciones, String recibo, Date fechaFactura, Integer codVenta) {
        Operacion o = createOperacion(recibo, fechaFactura);
        TransaccionDetailService controller = new TransaccionDetailController();
        for (TransaccionSimple t : transacciones) {
            getModel().startTransaction();
            if (codVenta != null) {
//                if (showConfirmDialog(getView(), "Desea dar salida a " + cantidad + ins.getInsumo().getUm() + "\n de " + ins.getInsumo() + " hacia " + destino)) {
                controller.addTransaccionSalida(o, t.getInsumo().getInsumo(), R.TODAYS_DATE, new Date(), getInstance(), t.getcDestino(), t.getCantidad(), codVenta);
//                }
            } else {
                throw new IllegalArgumentException("No existe venta registrada en la fecha de factura");
            }
            getModel().commitTransaction();
        }
        updateValorTotalAlmacen(getInstance());
    }

    @Override
    public void crearOperacionTraspaso(ArrayList<TransaccionSimple> transacciones, String recibo, Date fechaFactura) {
        Operacion o = createOperacion(recibo, fechaFactura);
        TransaccionDetailService controller = new TransaccionDetailController();
        for (TransaccionSimple t : transacciones) {
            getModel().startTransaction();
            if (t.getInsumo().getCantidad() < t.getCantidad()) {
                throw new IllegalArgumentException("La cantidad a transferir tiene que ser mayor a la cantidad existente");
            }
//            if (showConfirmDialog(getView(), "Desea traspasar " + cantidad + ins.getInsumo().getUm() + "\n de " + ins.getInsumo() + " hacia " + destinoTraspaso)) {
            controller.addTransaccionTraspaso(o, t.getInsumo().getInsumo(), R.TODAYS_DATE, new Date(), getInstance(), t.getaDestino(), t.getCantidad());
//            }
            getModel().commitTransaction();
        }
        updateValorTotalAlmacen(getInstance());
    }

    public enum OperationType {
        ENTRADA(0),
        REBAJA(2),
        SALIDA(1),
        TRASPASO(3),
        TRANSFORMAR(4);

        final int numero;

        OperationType(int numero) {
            this.numero = numero;
        }

        public int getNumero() {
            return numero;
        }

    }
}

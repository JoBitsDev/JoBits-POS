/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.almacen;

import GUI.Views.AbstractView;
import GUI.Views.Almacen.AlmacenEditView;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;

import restManager.controller.AbstractDetailController;
import restManager.controller.insumo.InsumoCreateEditController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.NoSelectedException;
import restManager.exceptions.UnExpectedErrorException;
import restManager.exceptions.ValidatingException;
import restManager.persistencia.Almacen;
import restManager.persistencia.Cocina;
import restManager.persistencia.Insumo;
import restManager.persistencia.InsumoAlmacen;
import restManager.persistencia.Transaccion;
import restManager.persistencia.TransaccionEntrada;
import restManager.persistencia.TransaccionMerma;
import restManager.persistencia.TransaccionSalida;
import restManager.persistencia.TransaccionTraspaso;
import restManager.persistencia.models.AlmacenDAO;
import restManager.persistencia.models.CocinaDAO;
import restManager.persistencia.models.InsumoAlmacenDAO;
import restManager.persistencia.models.InsumoDAO;
import restManager.persistencia.models.TransaccionDAO;
import restManager.persistencia.models.TransaccionEntradaDAO;
import restManager.persistencia.models.TransaccionMermaDAO;
import restManager.printservice.Impresion;
import restManager.resources.R;
import restManager.util.utils;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class AlmacenManageController extends AbstractDetailController<Almacen> {

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

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        setView(new AlmacenEditView(this, (AbstractView) parent, getInstance()));
        getView().updateView();
        getView().fetchComponentData();
        getView().setVisible(true);
    }

    public void imprimirReporteParaCompras(Almacen a) {
        String[] options = {"Impresora Regular", "Impresora Ticket", "Cancelar"};
        int selection = JOptionPane.showOptionDialog(getView(),
                R.RESOURCE_BUNDLE.getString("dialog_seleccionar_manera_imprimir"),
                R.RESOURCE_BUNDLE.getString("label_impresion"), JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
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

    public void modificarStock(Insumo i) {
        InsumoCreateEditController insumoController = new InsumoCreateEditController(i, getView());
        getView().updateView();
    }

    public void imprimirResumenAlmacen(Almacen a) {
        Impresion i = new Impresion();
        i.printResumenAlmacen(a);

    }

    //
    // Metodos Privados
    //
    private void contructTableForPrintingAndPrint(Almacen a) {
        throw new restManager.exceptions.DevelopingOperationException();
    }

    private void contructTicketAndPrint(Almacen a) {
        Impresion.getDefaultInstance().printStockBalance(a);
    }

    private boolean printOverStockedInsumos() {
        return JOptionPane.showConfirmDialog(getView(),
                R.RESOURCE_BUNDLE.getString("dialog_imprimir_insumos_sobrantes")) == JOptionPane.YES_OPTION;
    }

    public List<InsumoAlmacen> getInsumoAlmacenList(Almacen a) {
        return a.getInsumoAlmacenList();
    }

    public List<Insumo> getInsumoList() {
        return InsumoDAO.getInstance().findAll();
    }

    public void verTransacciones(Almacen a) {
        TransaccionesListController controller = new TransaccionesListController(getView(), a);
    }

    @Override
    public Almacen createNewInstance() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    void darEntradaAInsumo(TransaccionEntrada x) {
        InsumoAlmacen ins = AlmacenDAO.getInstance().findInsumo(getInstance().getCodAlmacen(), x.getTransaccion().getInsumocodInsumo().getCodInsumo());
        ins.setCantidad(ins.getCantidad() + x.getTransaccion().getCantidad());
        ins.setValorMonetario(ins.getValorMonetario() + x.getValorTotal());
        updateInsumo(ins);
        if (ins.getValorMonetario() / ins.getCantidad() != x.getTransaccion().getInsumocodInsumo().getCostoPorUnidad()) {
            if (showConfirmDialog(getView(), "Actualizar el costo del insumo " + x.getTransaccion().getInsumocodInsumo()+ " \n de "
                    + x.getTransaccion().getInsumocodInsumo().getCostoPorUnidad() + R.COIN_SUFFIX + " a " + utils.setDosLugaresDecimales(ins.getValorMonetario() / ins.getCantidad()))) {
                x.getTransaccion().getInsumocodInsumo().setCostoPorUnidad(utils.setDosLugaresDecimalesFloat(ins.getValorMonetario() / ins.getCantidad()));
                InsumoCreateEditController controller = new InsumoCreateEditController();
                controller.setView(getView());
                controller.setShowDialogs(false);
                controller.update(x.getTransaccion().getInsumocodInsumo(), true);
                controller.updateInsumoOnFichas(x.getTransaccion().getInsumocodInsumo());
            }
        }
        updateValorTotalAlmacen(instance);
    }

    private void updateInsumo(InsumoAlmacen ins) {
        InsumoAlmacenDAO.getInstance().startTransaction();
        InsumoAlmacenDAO.getInstance().edit(ins);
        InsumoAlmacenDAO.getInstance().commitTransaction();
    }

    void darSalidaAInsumo(TransaccionSalida x) throws ValidatingException{
        IPVController controller = new IPVController();
        controller.setView(getView());
        InsumoAlmacen insumoADarSalida = AlmacenDAO.getInstance().findInsumo(getInstance().getCodAlmacen(), x.getTransaccion().getInsumocodInsumo().getCodInsumo());

        if (insumoADarSalida.getCantidad() < x.getTransaccion().getCantidad()) {
            if (getModel().getEntityManager().getTransaction().isActive()) {
                getModel().getEntityManager().getTransaction().rollback();
            }
            throw new restManager.exceptions.ValidatingException(getView(), "No hay suficiente cantidad de " + x.getTransaccion().getInsumocodInsumo()+ " para extraer del almacen");
        }
        controller.darEntrada(x.getTransaccion().getInsumocodInsumo(), x.getCocinacodCocina(), x.getTransaccion().getFecha(), x.getTransaccion().getCantidad());
        float precioMedio = insumoADarSalida.getValorMonetario() / insumoADarSalida.getCantidad();
        insumoADarSalida.setCantidad(insumoADarSalida.getCantidad() - x.getTransaccion().getCantidad());
        insumoADarSalida.setValorMonetario(insumoADarSalida.getValorMonetario() - x.getTransaccion().getCantidad() * precioMedio);
        getModel().startTransaction();
        InsumoAlmacenDAO.getInstance().edit(insumoADarSalida);
        getModel().commitTransaction();
        updateValorTotalAlmacen(instance);
    }

    void darMermaInsumo(TransaccionMerma x) throws ValidatingException{
        InsumoAlmacen insumoaRebajar = AlmacenDAO.getInstance().findInsumo(getInstance().getCodAlmacen(), x.getTransaccion().getInsumocodInsumo().getCodInsumo());

        if (insumoaRebajar.getCantidad() < x.getTransaccion().getCantidad()) {
            if (getModel().getEntityManager().getTransaction().isActive()) {
                getModel().getEntityManager().getTransaction().rollback();
            }
            throw new restManager.exceptions.ValidatingException(getView(), "No hay suficiente cantidad de " + x.getTransaccion().getInsumocodInsumo()+ " para extraer del almacen");
        }
        float precioMedio = insumoaRebajar.getValorMonetario() / insumoaRebajar.getCantidad();
        insumoaRebajar.setCantidad(insumoaRebajar.getCantidad() - x.getTransaccion().getCantidad());
        insumoaRebajar.setValorMonetario(insumoaRebajar.getValorMonetario() - x.getTransaccion().getCantidad() * precioMedio);
        getModel().startTransaction();
        InsumoAlmacenDAO.getInstance().edit(insumoaRebajar);
        getModel().commitTransaction();
        updateValorTotalAlmacen(instance);
    }

    public List<Cocina> getCocinaList() {
        return CocinaDAO.getInstance().findAll();
    }

    void darTraspasoInsumo(TransaccionTraspaso x) throws ValidatingException{
        InsumoAlmacen desde = AlmacenDAO.getInstance().findInsumo(getInstance().getCodAlmacen(), x.getTransaccion().getInsumocodInsumo().getCodInsumo());
        InsumoAlmacen hasta;
        try {
            hasta = AlmacenDAO.getInstance().findInsumo(x.getAlmacenDestino().getCodAlmacen(), x.getTransaccion().getInsumocodInsumo().getCodInsumo());
        } catch (NoResultException ex) {
            throw new ValidatingException(getView(), "NO existe " + x.getTransaccion().getInsumocodInsumo()+ " en " + x.getAlmacenDestino());
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

    /**
     *
     * @param ins
     * @param tipo 0-entrada, 1- salida, 2-merma, 3 traspaso
     * @param destino sino es de tipo destino este parametro es nulo
     * @param destinoTraspaso
     * @param cantidad
     * @param importe
     * @param causaRebaja
     */
    public void crearTransaccion(InsumoAlmacen ins, int tipo, Cocina destino, Almacen destinoTraspaso, float cantidad, float importe, String causaRebaja) {
        TransaccionDetailController controller = new TransaccionDetailController();
        controller.setView(getView());
        getModel().startTransaction();
        switch (tipo) {
            case 0:
                if (showConfirmDialog(getView(), "Desea dar entrada a " + cantidad + ins.getInsumo().getUm() + "\n de " + ins.getInsumo() + " por " + importe + R.COIN_SUFFIX)) {
                    controller.addTransaccionEntrada(ins.getInsumo(), R.TODAYS_DATE, new Date(), getInstance(), cantidad, importe);
                }
                break;
            case 1:
                if (showConfirmDialog(getView(), "Desea dar salida a " + cantidad + ins.getInsumo().getUm() + "\n de " + ins.getInsumo() + " hacia " + destino)) {
                    controller.addTransaccionSalida(ins.getInsumo(), R.TODAYS_DATE, new Date(), getInstance(), destino, cantidad);
                }
                break;
            case 2:
                if (showConfirmDialog(getView(), "Desea rebajar  " + cantidad + ins.getInsumo().getUm() + "\n de " + ins.getInsumo() + " debido a " + causaRebaja)) {
                    controller.addTransaccionRebaja(ins.getInsumo(), R.TODAYS_DATE, new Date(), getInstance(), cantidad, causaRebaja);
                }
                break;
            case 3:
                if (ins.getCantidad() < cantidad) {
                    throw new ValidatingException(getView(), "La cantidad a transferir tiene que ser mayor a la cantidad existente");
                }
                if (showConfirmDialog(getView(), "Desea traspasar " + cantidad + ins.getInsumo().getUm() + "\n de " + ins.getInsumo() + " hacia " + destinoTraspaso)) {
                    controller.addTransaccionTraspaso(ins.getInsumo(), R.TODAYS_DATE, new Date(), getInstance(), destinoTraspaso, cantidad);
                }
                break;
            default:
                throw new UnExpectedErrorException(getView());
        }
        getModel().commitTransaction();
        updateValorTotalAlmacen(getInstance());
        getView().updateView();
        showSuccessDialog(getView());
    }

    private void updateValorTotalAlmacen(Almacen instance) {
        float total = 0;
        for (InsumoAlmacen x : instance.getInsumoAlmacenList()) {
            total += x.getValorMonetario();
        }
        instance.setValorMonetario(total);
        update(instance, true);
        getModel().getEntityManager().getEntityManagerFactory().getCache().evict(Almacen.class);
    }

    public void createInsumo(InsumoAlmacen newInsumo) {
        InsumoAlmacenDAO.getInstance().create(newInsumo);
    }

    public void removeInsumoFromStorage(InsumoAlmacen objectAtSelectedRow) {
        if (showConfirmDialog(getView(), "Desea eliminar las existencias de " + objectAtSelectedRow.getInsumo() + " del almacen")) {
            getModel().startTransaction();
            InsumoAlmacenDAO.getInstance().remove(objectAtSelectedRow);
            getInstance().getInsumoAlmacenList().remove(objectAtSelectedRow);
            getModel().commitTransaction();
        }
    }

    public void setCentroElaboracion(boolean selected) {
        instance.setCentroElaboracion(selected);
        update(instance, true);
    }

}

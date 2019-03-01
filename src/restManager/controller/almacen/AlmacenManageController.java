/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.almacen;

import GUI.Views.AbstractView;
import GUI.Views.Almacen.AlmacenEditView;
import java.awt.Window;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

import restManager.controller.AbstractDetailController;
import restManager.controller.insumo.InsumoCreateEditController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Almacen;
import restManager.persistencia.Cocina;
import restManager.persistencia.Insumo;
import restManager.persistencia.InsumoAlmacen;
import restManager.persistencia.Transaccion;
import restManager.persistencia.models.AlmacenDAO;
import restManager.persistencia.models.CocinaDAO;
import restManager.persistencia.models.InsumoAlmacenDAO;
import restManager.persistencia.models.InsumoDAO;
import restManager.persistencia.models.TransaccionDAO;
import restManager.persistencia.models.TransaccionEntradaDAO;
import restManager.persistencia.models.TransaccionMermaDAO;
import restManager.printservice.Impresion;
import restManager.resources.R;
import restManager.util.comun;

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
        throw new DevelopingOperationException();
//        Impresion i = new Impresion();
//        ArrayList<Insumo> list = new ArrayList<>(a.getInsumoAlmacenList());
//        list.sort((Insumo o1, Insumo o2) -> o1.getNombre().compareTo(o2.getNombre()));
//        i.printStockBalance(list, printOverStockedInsumos());
    }

    private boolean printOverStockedInsumos() {
        return JOptionPane.showConfirmDialog(getView(),
                R.RESOURCE_BUNDLE.getString("dialog_imprimir_insumos_sobrantes")) == JOptionPane.YES_OPTION;
    }

    public List<InsumoAlmacen> getInsumoAlmacenList(Almacen a) {
        return InsumoAlmacenDAO.getInstance().getInsumoAlmacenList(a);
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

    void darEntradaAInsumo(Insumo insumo, Float cantidad, Float valorTotal) {
        InsumoAlmacen ins = AlmacenDAO.getInstance().findInsumo(getInstance().getCodAlmacen(), insumo.getCodInsumo());
        ins.setCantidad(ins.getCantidad() + cantidad);
        ins.setValorMonetario(ins.getValorMonetario() + valorTotal);
        updateInsumo(ins);
        if (ins.getValorMonetario() / ins.getCantidad() != insumo.getCostoPorUnidad()) {
            if (showConfirmDialog(getView(), "Actualizar el costo del insumo " + insumo + " \n de "
                    + insumo.getCostoPorUnidad() + R.coinSuffix + " a " + comun.setDosLugaresDecimales(ins.getValorMonetario() / ins.getCantidad()))) {
                insumo.setCostoPorUnidad(ins.getValorMonetario() / ins.getCantidad());
                InsumoCreateEditController controller = new InsumoCreateEditController();
                controller.setView(getView());
                controller.setShowDialogs(false);
                controller.update(insumo, true);
                controller.updateInsumoOnFichas(insumo);
            }
        }
        updateValorTotalAlmacen(instance);
    }

    private void updateInsumo(InsumoAlmacen ins) {
        InsumoAlmacenDAO.getInstance().startTransaction();
        InsumoAlmacenDAO.getInstance().edit(ins);
        InsumoAlmacenDAO.getInstance().commitTransaction();
    }

    void darSalidaAInsumo(Transaccion x) {
        IPVController controller = new IPVController();
        controller.setView(getView());
        InsumoAlmacen insumoADarSalida = AlmacenDAO.getInstance().findInsumo(getInstance().getCodAlmacen(), x.getInsumo().getCodInsumo());

        if (insumoADarSalida.getCantidad() < x.getCantidad()) {
            if (getModel().getEntityManager().getTransaction().isActive()) {
                getModel().getEntityManager().getTransaction().rollback();
            }
            throw new restManager.exceptions.ValidatingException(getView(), "No hay suficiente cantidad de " + x.getInsumo() + " para extraer del almacen");
        }
        controller.darEntrada(x.getInsumo(), x.getCocina(), x.getTransaccionPK().getFecha(), x.getCantidad());
        float precioMedio = insumoADarSalida.getValorMonetario() / insumoADarSalida.getCantidad();
        insumoADarSalida.setCantidad(insumoADarSalida.getCantidad() - x.getCantidad());
        insumoADarSalida.setValorMonetario(insumoADarSalida.getValorMonetario() - x.getCantidad() * precioMedio);
        getModel().startTransaction();
        InsumoAlmacenDAO.getInstance().edit(insumoADarSalida);
        getModel().commitTransaction();
        updateValorTotalAlmacen(instance);
    }

    void darMermaInsumo(Transaccion x) {
        InsumoAlmacen insumoaRebajar = AlmacenDAO.getInstance().findInsumo(getInstance().getCodAlmacen(), x.getInsumo().getCodInsumo());

        if (insumoaRebajar.getCantidad() < x.getCantidad()) {
            if (getModel().getEntityManager().getTransaction().isActive()) {
                getModel().getEntityManager().getTransaction().rollback();
            }
            throw new restManager.exceptions.ValidatingException(getView(), "No hay suficiente cantidad de " + x.getInsumo() + " para extraer del almacen");
        }
        float precioMedio = insumoaRebajar.getValorMonetario() / insumoaRebajar.getCantidad();
        insumoaRebajar.setCantidad(insumoaRebajar.getCantidad() - x.getCantidad());
        insumoaRebajar.setValorMonetario(insumoaRebajar.getValorMonetario() - x.getCantidad() * precioMedio);
        getModel().startTransaction();
        InsumoAlmacenDAO.getInstance().edit(insumoaRebajar);
        getModel().commitTransaction();
        updateValorTotalAlmacen(instance);
    }

    public List<Cocina> getCocinaList() {
        return CocinaDAO.getInstance().findAll();
    }

    /**
     *
     * @param ins
     * @param tipo 0-entrada, 1- salida, 2-merma
     * @param destino sino es de tipo destino este parametro es nulo
     */
    public void crearTransaccion(InsumoAlmacen ins, int tipo, Cocina destino) {
        TransaccionDetailController controller = new TransaccionDetailController();
        controller.setView(getView());
        getModel().startTransaction();
        switch (tipo) {
            case 0:
                controller.addTransaccionEntrada(ins.getInsumo(), R.TODAYS_DATE, new Date(), getInstance());
                break;
            case 1:
                controller.addTransaccionSalida(ins.getInsumo(), R.TODAYS_DATE, new Date(), getInstance(), destino);
                break;
            case 2:
                controller.addTransaccionMerma(ins.getInsumo(), R.TODAYS_DATE, new Date(), getInstance());
        }
        getModel().commitTransaction();
        updateValorTotalAlmacen(getInstance());
        getView().updateView();

    }

    private void updateValorTotalAlmacen(Almacen instance) {
        float total = 0;
        for (InsumoAlmacen x : instance.getInsumoAlmacenList()) {
            total += x.getValorMonetario();
        }
        instance.setValorMonetario(total);
        update(instance, true);
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

}

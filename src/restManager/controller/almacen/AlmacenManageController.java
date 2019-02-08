/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.almacen;

import GUI.Views.AbstractView;
import GUI.Views.Almacen.AlmacenEditView;
import java.awt.Window;
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
import restManager.resources.R;

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
    }

    private void updateInsumo(InsumoAlmacen ins) {
        InsumoAlmacenDAO.getInstance().startTransaction();
        InsumoAlmacenDAO.getInstance().edit(ins);
        InsumoAlmacenDAO.getInstance().commitTransaction();
    }

    void darSalidaAInsumo(Transaccion x) {
        IPVController controller = new IPVController();
        InsumoAlmacen insumoADarSalida = AlmacenDAO.getInstance().findInsumo(getInstance().getCodAlmacen(), x.getInsumo().getCodInsumo());

        if (insumoADarSalida.getCantidad() < x.getCantidad()) {
            throw new restManager.exceptions.ValidatingException(getView(),"No hay suficiente cantidad de " + x.getInsumo() + " para dar salida al punto de elaboración");
        }
        insumoADarSalida.setCantidad(insumoADarSalida.getCantidad() - x.getCantidad());
        InsumoAlmacenDAO.getInstance().edit(insumoADarSalida);
        controller.darEntrada(x.getInsumo(), x.getCocina(), x.getTransaccionPK().getFecha(), x.getCantidad());

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
        switch (tipo) {
            case 0:
                controller.addTransaccionEntrada(ins.getInsumo(), R.TODAYS_DATE, R.TODAYS_DATE, getInstance());
                break;
            case 1:
                controller.addTransaccionSalida(ins.getInsumo(), R.TODAYS_DATE, R.TODAYS_DATE, getInstance(), destino);
                break;
            case 2:
                throw new DevelopingOperationException();

        }
        updateValorTotalAlmacen(getInstance());
        

    }

    private void updateValorTotalAlmacen(Almacen instance) {
        float total = 0;
        for (InsumoAlmacen x : instance.getInsumoAlmacenList()) {
            total += x.getValorMonetario();
        }
        instance.setValorMonetario(total);
        update(instance, true);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.almacen;

import GUI.Views.AbstractView;
import GUI.Views.Almacen.AlmacenEditView;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import restManager.controller.AbstractDialogController;
import restManager.controller.insumo.InsumoCreateEditController;
import restManager.controller.insumo.InsumoListController;
import restManager.persistencia.Almacen;
import restManager.persistencia.Insumo;
import restManager.persistencia.models.AlmacenDAO;
import restManager.printservice.Impresion;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class AlmacenManageController extends AbstractDialogController<Almacen> {

    Almacen a;

    public AlmacenManageController(Almacen a) {
        super(new AlmacenDAO());
        this.a = a;
    }

    public AlmacenManageController(Window parent, Almacen a) {
        this(a);
        constructView(parent);
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        setView(new AlmacenEditView(this, (Dialog) parent, true));
        getView().updateView();
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
    
    public void verTransaccionsArchivadas(){
        AlmacenTransaccionListController fichasController = new AlmacenTransaccionListController(getView(),a);
    }
    //
    // Metodos Privados
    //

    private void contructTableForPrintingAndPrint(Almacen a) {
        throw new restManager.exceptions.DevelopingOperationException();
    }

    private void contructTicketAndPrint(Almacen a) {
        Impresion i = new Impresion();
        ArrayList<Insumo> list = new ArrayList<>(a.getInsumoList());
        list.sort((Insumo o1, Insumo o2) -> o1.getNombre().compareTo(o2.getNombre()));
        i.printStockBalance(list, printOverStockedInsumos());
    }

    private boolean printOverStockedInsumos() {
        return JOptionPane.showConfirmDialog(getView(),
                R.RESOURCE_BUNDLE.getString("dialog_imprimir_insumos_sobrantes")) == JOptionPane.YES_OPTION;
    }

        @Override
    public AbstractView getView() {
       return getView();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.almacen;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.OldAbstractListController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.exceptions.NoSelectedException;
import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.adapters.repo.impl.AlmacenDAO;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.recursos.RegularExpressions;
import java.util.Collections;
import java.util.List;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class AlmacenListController extends OldAbstractListController<Almacen> implements AlmacenListService {

    private final String PREFIX_FOR_ID = "A-";

    public AlmacenListController() {
        super(AlmacenDAO.getInstance());
    }

 
    @Override
    public void destroy(Almacen selected) {
        setSelected(selected);
        deleteSelectedStorage();
    }

    @Override
    public void update(Almacen selected) {
        setSelected(selected);
        openSelectedStorage();
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
    }

    public void createNewStorage() {

        String storageName = JOptionPane.showInputDialog(R.RESOURCE_BUNDLE.getString("dialogo_agregar_almacen"));
        if (storageName != null) {
            if (storageName.matches(RegularExpressions.ONLY_WORDS_SEPARATED_WITH_SPACES)) {
                selected = new Almacen();
                selected.setInsumoAlmacenList(new ArrayList<>());
                selected.setCantidadInsumos(0);
                selected.setValorMonetario(0.0);
                selected.setCodAlmacen(super.getModel().generateStringCode(PREFIX_FOR_ID));
                selected.setNombre(storageName);

                create();
                showSuccessDialog(Application.getInstance().getMainWindow());
            } else {
                JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Nombre no permitido");
                //TODO: implementar exepciones
            }
        }
    }

    public void deleteSelectedStorage() {
        if (selected == null) {
            throw new NoSelectedException();
        } else {
            int resp = JOptionPane.showConfirmDialog(getView(), R.RESOURCE_BUNDLE.getString("dialogo_borrar_almacen") + " " + selected.getNombre());
            if (resp == JOptionPane.YES_OPTION) {
                destroy();
                showSuccessDialog(Application.getInstance().getMainWindow());
            }
        }
    }

    public void openSelectedStorage() {
        if (selected == null) {
            throw new NoSelectedException();
        } else {
            AlmacenManageController manageController = new AlmacenManageController(Application.getInstance().getMainWindow(), selected);
        }

    }

    @Override
    public AbstractDetailController<Almacen> getDetailControllerForNew() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractDetailController<Almacen> getDetailControllerForEdit(Almacen selected) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Almacen> getItems() {
        List<Almacen> ret = super.getItems();
        Collections.sort(ret);
        return ret;
    }

}

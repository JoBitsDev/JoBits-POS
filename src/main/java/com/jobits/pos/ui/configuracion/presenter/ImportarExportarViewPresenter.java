/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.configuracion.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.productos.ProductoVentaListController;
import com.jobits.pos.controller.productos.ProductoVentaListService;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.configuracion.ImportarExportar;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import java.awt.Container;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class ImportarExportarViewPresenter extends AbstractListViewPresenter<ImportarExportarViewModel> {

    ProductoVentaListController service;

    public static final String ACTION_IMPORTAR_EXPORTAR = "Importar Exportar";

    public ImportarExportarViewPresenter(ProductoVentaListController service) {
        super(new ImportarExportarViewModel(), ImportarExportar.VIEW_NAME);
        this.service = service;
        addListeners();
        setListToBean();
    }

    @Override
    protected void registerOperations() {
        super.registerOperations(); //To change body of generated methods, choose Tools | Templates.
        registerOperation(new AbstractViewAction(ACTION_IMPORTAR_EXPORTAR) {
            @Override
            public Optional doAction() {
                importarExportar();
                return Optional.empty();
            }

        });
    }

    @Override
    protected void onAgregarClick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onEditarClick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onEliminarClick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void setListToBean() {
        getBean().setImportar_exportar_opciones(new ArrayListModel(Arrays.asList("Importar", "Exportar")));
        getBean().setLista_tipo_datos(new ArrayListModel(Arrays.asList("Ficha de Costo")));
    }

    private void addListeners() {
        getBean().addPropertyChangeListener(ImportarExportarViewModel.PROP_IMPORTAR_EXPORTAR_OPCION_SELECCIONADA, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null) {
                if (evt.getNewValue().equals("Importar")) {
                    getBean().setButton_text("Abrir");
                } else if (evt.getNewValue().equals("Exportar")) {
                    getBean().setButton_text("Guardar");
                }
                getBean().setTipo_dato_enabled(true);
            } else if (evt.getNewValue() == null) {
                getBean().setTipo_dato_enabled(false);
                getBean().setTipo_dato_seleccionado(null);
                getBean().setButton_text("---");
            }
        });
        getBean().addPropertyChangeListener(ImportarExportarViewModel.PROP_TIPO_DATO_SELECCIONADO, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null) {
                getBean().setButton_enabled(true);
            } else if (evt.getNewValue() == null) {
                getBean().setButton_enabled(false);
            }
        });
    }

    private void importarExportar() {
        try {
            if (getBean().getTipo_dato_seleccionado().equals("Ficha de Costo")) {
                if (getBean().getImportar_exportar_opcion_seleccionada().equals("Importar")) {
                    JFileChooser list = new JFileChooser();
                    int response = list.showOpenDialog(Application.getInstance().getMainWindow());
                    if (response == JFileChooser.APPROVE_OPTION) {
                        service.importarFichadeCostoFromJson(list.getSelectedFile());
                        showSuccessDialog(Application.getInstance().getMainWindow(), "El archivo se ha exportado con éxito");
                    }
                } else if (getBean().getImportar_exportar_opcion_seleccionada().equals("Exportar")) {
                    JFileChooser list = new JFileChooser();
                    int response = list.showSaveDialog(Application.getInstance().getMainWindow());
                    if (response == JFileChooser.APPROVE_OPTION) {
                        service.exportarToJson(list.getSelectedFile(), service.getItems());
                        showSuccessDialog(Application.getInstance().getMainWindow(), "El archivo se ha importado con éxito");
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ImportarExportarViewPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showSuccessDialog(Container view, String text) {
        JOptionPane.showMessageDialog(view, text,
                R.RESOURCE_BUNDLE.getString("label_informacion"), JOptionPane.INFORMATION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/exitoso.png")));

    }
}

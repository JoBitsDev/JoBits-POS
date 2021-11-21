/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.configuracion.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.almacen.AlmacenManageService;
import com.jobits.pos.controller.insumo.InsumoDetailService;
import com.jobits.pos.controller.productos.ProductoInsumoListService;
import com.jobits.pos.controller.productos.ProductoVentaService;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.io.DataHeader;
import com.jobits.pos.io.IOTemplate;
import com.jobits.pos.io.impl.AbstractRawDataConverter;
import com.jobits.pos.io.impl.CsvIOTemplateImpl;
import com.jobits.pos.io.impl.InsumoAlmacenRawDataConverterImpl;
import com.jobits.pos.io.impl.InsumoConverterRawDataConverterImpl;
import com.jobits.pos.io.impl.ProductoInsumoRawDataConverterImpl;
import com.jobits.pos.io.impl.ProductoVentaRawDataConverterImpl;
import com.jobits.pos.ui.configuracion.DataHeaderWrapper;
import com.jobits.pos.ui.configuracion.OpcionIO;
import com.jobits.pos.ui.configuracion.TipoDato;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Home
 */
public class ImportarExportarViewPresenter extends AbstractViewPresenter<ImportarExportarViewModel> {

    public static final String ACTION_OPEN_SAVE_FILE = "Open/Save File";
    public static final String ACTION_FILL_HEADER_VALUES = "Fill Header Value";
    public static final String ACTION_MIX_HEADER_VALUES = "Mix Header Value";
    public static final String ACTION_REMOVE_MIXED_HEADER_VALUES = "Remove Mixed Header Values";
    public static final String ACTION_LOAD_DATA_FROM_SOURCE = "Load Data From Source";
    public static final String ACTION_EXECUTE_ACTION = "Execute Action";
    
    
    ProductoVentaService service = PosDesktopUiModule.getInstance().getImplementation(ProductoVentaService.class);
    ProductoInsumoListService iService = PosDesktopUiModule.getInstance().getImplementation(ProductoInsumoListService.class);
    IOTemplate<Insumo> template = new CsvIOTemplateImpl<>();
    AbstractRawDataConverter converter;

    public List dataList = new ArrayList();

    public ImportarExportarViewPresenter() {
        super(new ImportarExportarViewModel());
        addListeners();
        setListToBean();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_OPEN_SAVE_FILE) {
            @Override
            public Optional doAction() {
                openSaveFile();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_FILL_HEADER_VALUES) {
            @Override
            public Optional doAction() {
                fillHeaderValues();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_MIX_HEADER_VALUES) {
            @Override
            public Optional doAction() {
                mixHeaderValues();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_REMOVE_MIXED_HEADER_VALUES) {
            @Override
            public Optional doAction() {
                removeDataHeaderWrapper();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_LOAD_DATA_FROM_SOURCE) {
            @Override
            public Optional doAction() {
                loadDataFromSource();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_EXECUTE_ACTION) {
            @Override
            public Optional doAction() {
                executeAction();
                return Optional.empty();
            }
        });
    }

    protected void setListToBean() {
        getBean().setLista_opciones(new ArrayListModel(Arrays.asList(OpcionIO.values())));
        getBean().setOpcion_seleccionada(OpcionIO.IMPORTAR);
        getBean().setLista_tipo_dato(new ArrayListModel(Arrays.asList(TipoDato.values())));
    }

    private void addListeners() {
        getBean().addPropertyChangeListener(ImportarExportarViewModel.PROP_OPCION_SELECCIONADA, (PropertyChangeEvent evt) -> {
            OpcionIO value = (OpcionIO) evt.getNewValue();
            if (value.equals(OpcionIO.IMPORTAR)) {
                getBean().setEnable_select_column_panel(true);
                getBean().setOpen_file_text_button("Seleccionar Archivo");
                getBean().setNombre_archivo_seleccionado("Ningun Archivo Seleccionado");
            } else if (value.equals(OpcionIO.EXPORTAR)) {
                getBean().setEnable_select_column_panel(false);
                getBean().setOpen_file_text_button("Seleccionar Ruta");
                getBean().setNombre_archivo_seleccionado("Ninguna Ruta Seleccionada");
            }
            getBean().setOpcion_text(value.getStringValue());
        });
        getBean().addPropertyChangeListener(ImportarExportarViewModel.PROP_TIPO_DATO_SELECCIONADO, (PropertyChangeEvent evt) -> {
            getBean().setData_type_text(((TipoDato) evt.getNewValue()).getStringValue());
        });
    }

    private void openSaveFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
        int response;
        File file;
        switch (getBean().getOpcion_seleccionada()) {
            case IMPORTAR:
                response = fileChooser.showOpenDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    getBean().setArchivo_seleccionado(file);
                    getBean().setNombre_archivo_seleccionado(file.getName());
                    getBean().setEnable_button_to_sel_columns(true);
                }
                break;
            case EXPORTAR:
                response = fileChooser.showSaveDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    if (!FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("csv")) {
                        file = new File(file.getPath() + ".csv");
//                        file = new File(file.getParentFile(), FilenameUtils.getBaseName(file.getName()) + ".xml"); // ALTERNATIVELY: remove the extension (if any) and replace it with ".xml"
                    }
                    getBean().setArchivo_seleccionado(file);
                    getBean().setNombre_archivo_seleccionado(file.getName());
                    getBean().setEnable_button_to_sel_columns(true);
                }
                break;
        }
    }

    private void fillHeaderValues() {
        switch (getBean().getTipo_dato_seleccionado()) {
            case INSUMO:
                converter = new InsumoConverterRawDataConverterImpl();
                break;
            case FICHA_DE_COSTO:
                converter = new ProductoInsumoRawDataConverterImpl();
                break;
            case INSUMO_ALMACEN:
                converter = new InsumoAlmacenRawDataConverterImpl();
                break;
            case PRODUCTO_VENTA:
                converter = new ProductoVentaRawDataConverterImpl();
                break;
        }
        switch (getBean().getOpcion_seleccionada()) {
            case IMPORTAR:
                ArrayListModel<DataHeader> dataHeaders;
                ArrayListModel<String> rawHeaders;
                dataHeaders = new ArrayListModel<>(converter.getDataHeaders());
                rawHeaders = new ArrayListModel<>(template.readHeaders(getBean().getArchivo_seleccionado().getPath()));
                if (!dataHeaders.isEmpty()) {
                    getBean().setLista_data_header(dataHeaders);
                    getBean().setData_header_seleccionado(dataHeaders.get(0));
                }
                if (!rawHeaders.isEmpty()) {
                    getBean().setLista_raw_header(rawHeaders);
                    getBean().setRaw_header_seleccionado(rawHeaders.get(0));
                }
                firePropertyChange("To Column Select", null, null);
                break;
            case EXPORTAR:
                switch (getBean().getTipo_dato_seleccionado()) {
                    case INSUMO:
                        InsumoDetailService useCaseInsumo
                                = PosDesktopUiModule.getInstance().getImplementation(InsumoDetailService.class);
                        dataList = useCaseInsumo.findAll();
                        break;
                    case FICHA_DE_COSTO:
                        ProductoInsumoListService useCaseProductoInsumo
                                = PosDesktopUiModule.getInstance().getImplementation(ProductoInsumoListService.class);
                        dataList = useCaseProductoInsumo.findAll();
                        break;
                    case INSUMO_ALMACEN:
                        AlmacenManageService useCaseInsumoAlmacen
                                = PosDesktopUiModule.getInstance().getImplementation(AlmacenManageService.class);
                        dataList = useCaseInsumoAlmacen.findAll();
                        break;
                    case PRODUCTO_VENTA:
                        dataList = service.findAll();
                        break;
                }
                firePropertyChange("To Ready", null, null);
                getBean().setCantidad_datos(String.valueOf(dataList.size()));
                break;
        }

    }

    private void mixHeaderValues() {
        DataHeader dataHeader = getBean().getData_header_seleccionado();
        String rawHeader = getBean().getRaw_header_seleccionado();
        if (rawHeader != null && dataHeader != null) {
            getBean().getLista_data_header().remove(dataHeader);
            getBean().getLista_raw_header().remove(rawHeader);
            getBean().getLista_data_header_wrapper().add(new DataHeaderWrapper(dataHeader, rawHeader));
            if (!getBean().getLista_data_header().isEmpty()) {
                getBean().setData_header_seleccionado(getBean().getLista_data_header().get(0));
            } else {
                getBean().setData_header_seleccionado(null);
            }
            if (!getBean().getLista_raw_header().isEmpty()) {
                getBean().setRaw_header_seleccionado(getBean().getLista_raw_header().get(0));
            } else {
                getBean().setRaw_header_seleccionado(null);
            }
            validateGoToNextView();
        }
    }

    private void removeDataHeaderWrapper() {
        DataHeaderWrapper dataHeaderWrapper = getBean().getData_header_wrapper_seleccionado();
        if (dataHeaderWrapper != null) {
            getBean().getLista_data_header().add(dataHeaderWrapper.getDataHeader());
            getBean().getLista_raw_header().add(dataHeaderWrapper.getRawHeader());
            getBean().getLista_data_header_wrapper().remove(dataHeaderWrapper);
            validateGoToNextView();
        }
    }

    private void loadDataFromSource() {
        ArrayListModel<DataHeaderWrapper> lista = getBean().getLista_data_header_wrapper();
        lista.forEach(x -> {
            converter.mapDataToRawHeader(x.getDataHeader(), x.getRawHeader());
        });
        try {
            dataList = template.importDataFrom(getBean().getArchivo_seleccionado().getPath(), converter);
            getBean().setCantidad_datos(String.valueOf(dataList.size()));
        } catch (Exception ex) {
            getBean().setEnable_button_do_action(false);
            getBean().setError_text_mesage("No se pudieron cargar los datos del archivo seleccionado");
            getBean().setError_text_description(ex.getMessage());
            firePropertyChange("Error During Load", null, null);
        }
    }

    private void executeAction() {
        switch (getBean().getOpcion_seleccionada()) {
            case IMPORTAR:
                switch (getBean().getTipo_dato_seleccionado()) {
                    case INSUMO:
                        InsumoDetailService useCaseInsumo
                                = PosDesktopUiModule.getInstance().getImplementation(InsumoDetailService.class);
                        useCaseInsumo.bulkImport(dataList);
                        break;
                    case FICHA_DE_COSTO:
                        iService.bulkImportProductoInsumo(dataList);
                        break;
                    case INSUMO_ALMACEN:
                        AlmacenManageService useCaseInsumoAlmacen
                                = PosDesktopUiModule.getInstance().getImplementation(AlmacenManageService.class);
                        useCaseInsumoAlmacen.bulkImport(dataList);
                        break;
                    case PRODUCTO_VENTA:
                        service.bulkImport(dataList);
                        break;
                }
                break;
            case EXPORTAR:
                boolean bool = template.exportToFile(getBean().getArchivo_seleccionado().getPath(), dataList, converter);
                if (!bool) {
                    getBean().setEnable_button_do_action(false);
                    getBean().setError_text_mesage("No se pudo completar la exportacion");
                    firePropertyChange("Error During Load", null, null);
                }
                break;
        }
        getBean().setEnable_button_do_action(false);
        firePropertyChange("Success", null, null);
    }

    private void validateGoToNextView() {
        ArrayListModel<DataHeader> list = getBean().getLista_data_header();
        if (!list.isEmpty()) {
            for (DataHeader x : list) {
                if (x.isRequired()) {
                    getBean().setEnable_button_to_ready(false);
                    break;
                } else {
                    getBean().setEnable_button_to_ready(true);
                }
            }
        } else {
            getBean().setEnable_button_to_ready(true);
        }
    }
}

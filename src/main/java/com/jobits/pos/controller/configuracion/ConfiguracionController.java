/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.configuracion;

import com.jobits.pos.ui.configuracion.ConfiguracionView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Container;
import java.awt.Dialog;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.jobits.pos.algoritmo.ParametrosConfiguracion;
import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.exceptions.ValidatingException;
import com.jobits.pos.domain.models.Configuracion;
import com.jobits.pos.domain.models.Negocio;
import com.jobits.pos.domain.models.Seccion;
import com.jobits.pos.adapters.repo.ConfiguracionDAO;
import com.jobits.pos.adapters.repo.NegocioDAO;
import com.jobits.pos.adapters.repo.SeccionDAO;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.servicios.impresion.Ticket;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.OldView;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ConfiguracionController extends AbstractDialogController<Configuracion> {

    private ParametrosConfiguracion configuracionY;

    public ConfiguracionController() {
        super(ConfiguracionDAO.getInstance());
    }

    public ConfiguracionController(OldView parentView) {
        super(ConfiguracionDAO.getInstance());
        constructView(parentView.getContainer());
    }

    public void cargarConfiguracion() {
        Negocio model = NegocioDAO.getInstance().find(1);
        ConfiguracionDAO c = ConfiguracionDAO.getInstance();
        R.REST_NAME = model.getNombre();
        R.MAIN_COIN = model.getMonedaPrincipal();
        R.COIN_SUFFIX = " " + model.getMonedaPrincipal();
        R.COINCHANGE = c.find(R.SettingID.GENERAL_CAMBIO_MONEDA).getValor();
        R.VARIOS_TURNOS = c.find(R.SettingID.GENERAL_TURNOS_VARIOS).getValor() == 1;
        R.CAJERO_PERMISOS_ESPECIALES = c.find(R.SettingID.GENERAL_CAJERO_PERMISOS_ESP).getValor() == 1;
        R.CONSUMO_DE_LA_CASA_EN_ESTADISTICAS = c.find(R.SettingID.GENERAL_CONSUMO_CASA_ESTADISTICAS).getValor() == 1;
        Ticket.PAPER_LENGHT = c.find(R.SettingID.IMPRESION_TICKET_TAMANO_PAPEL).getValor();
        Ticket.LINE_CHAR = c.find(R.SettingID.IMPRESION_TICKET_CARACTER_SEPARADOR).getValorString().charAt(0);
        Impresion.SHOW_HEADER = c.find(R.SettingID.IMPRESION_TICKET_ENCABEZADO_RESTAURANTE).getValor() == 1;
        Impresion.PRINT_IN_CENTRAL_KITCHEN = c.find(R.SettingID.IMPRESION_IMPRIMIR_COCINA_CENTRAL).getValor() == 1;
        Impresion.PRINT_GASTOS_EN_AUTORIZOS = c.find(R.SettingID.IMPRESION_IMPRIMIR_GASTOS_AUTORIZOS).getValor() == 1;
        Impresion.IMPRIMIR_TICKET_COCINA = c.find(R.SettingID.IMPRESION_IMPRIMIR_TICKET_EN_COCINA).getValor() == 1;
        Impresion.cantidadCopias = c.find(R.SettingID.IMPRESION_CANTIDAD_COPIAS).getValor();
        Impresion.REDONDEO_POR_EXCESO = c.find(R.SettingID.IMPRESION_REDONDEO_EXCESO).getValor() == 1;
        Impresion.SHOW_SUBTOTAL = c.find(R.SettingID.IMPRESION_TICKET_SUBTOTAL).getValor() == 1;
        Impresion.CABECERA = c.find(R.SettingID.IMPRESION_TICKET_VALOR_ENCABEZADO).getValorString();
        Impresion.PRINT_SECOND_COIN = c.find(R.SettingID.IMPRESION_IMPRIMIR_MONEDA_SECUNDARIA).getValor() == 1;
        Impresion.BUZZER_ON = c.find(R.SettingID.IMPRESION_BUZZER_ON).getValor() == 1;
    }

    public ParametrosConfiguracion cargarConfiguracionY() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            configuracionY = mapper.readValue(new File(R.CONFIG_FILE_PATH), ParametrosConfiguracion.class);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            configuracionY = new ParametrosConfiguracion(new ArrayList<>(), new ArrayList<>(), (byte) 40, (byte) 30);
        } catch (IOException ex) {
            ex.printStackTrace();
            configuracionY = new ParametrosConfiguracion(new ArrayList<>(), new ArrayList<>(), (byte) 40, (byte) 30);

        }
        return configuracionY;
    }

    public void updateConfiguracionY(ParametrosConfiguracion conf) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(R.CONFIG_FILE_PATH), conf);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void constructView(Container parent) {
        setView(new ConfiguracionView(this, (Dialog) parent));
        getView().updateView();
        getView().setVisible(true);
    }

    public Object getConfiguracion(R.SettingID settingID) {
        ConfiguracionDAO model = (ConfiguracionDAO) getModel();
        Configuracion c = model.find(settingID);
        switch (settingID) {
            case GENERAL_TURNOS_VARIOS:
            case GENERAL_CAJERO_PERMISOS_ESP:
            case GENERAL_CONSUMO_CASA_ESTADISTICAS:
            case IMPRESION_TICKET_ENCABEZADO_RESTAURANTE:
            case IMPRESION_IMPRIMIR_TICKET_EN_COCINA:
            case IMPRESION_REDONDEO_EXCESO:
            case GENERAL_MESA_FIJA_CAJERO:
            case IMPRESION_IMPRIMIR_MONEDA_SECUNDARIA:
            case IMPRESION_BUZZER_ON:
            case SINCRONIZACION_HABILITAR:
                return c.getValor() == 1;
            case GENERAL_CAMBIO_MONEDA:
            case IMPRESION_CANTIDAD_COPIAS:
            case IMPRESION_TICKET_TAMANO_PAPEL:
            case SINCRONIZACION_UBICACION:
            case SINCRONIZACION_TIEMPO_LOOP:
                return c.getValor().toString();
            case IMPRESION_TICKET_CARACTER_SEPARADOR:
            case IMPRESION_TICKET_VALOR_ENCABEZADO:
                return c.getValorString();
            default:
                throw new ValidatingException("Falta configurar para cargar " + settingID);
        }
    }

    public void updateConfiguracion(R.SettingID settingID, Object update) {
        ConfiguracionDAO model = (ConfiguracionDAO) getModel();
        Configuracion c = model.find(settingID);
        switch (settingID) {
            case GENERAL_TURNOS_VARIOS:
            case GENERAL_CAJERO_PERMISOS_ESP:
            case GENERAL_CONSUMO_CASA_ESTADISTICAS:
            case IMPRESION_TICKET_ENCABEZADO_RESTAURANTE:
            case IMPRESION_IMPRIMIR_TICKET_EN_COCINA:
            case IMPRESION_REDONDEO_EXCESO:
            case GENERAL_MESA_FIJA_CAJERO:
            case IMPRESION_IMPRIMIR_MONEDA_SECUNDARIA:
            case IMPRESION_BUZZER_ON:
            case SINCRONIZACION_HABILITAR:
                c.setValor((boolean) update == true ? 1 : 0);
                break;
            case GENERAL_CAMBIO_MONEDA:
            case IMPRESION_CANTIDAD_COPIAS:
            case IMPRESION_TICKET_TAMANO_PAPEL:
            case SINCRONIZACION_UBICACION:
            case SINCRONIZACION_TIEMPO_LOOP:
                c.setValor(Integer.parseInt(update.toString()));
                break;
            case IMPRESION_TICKET_CARACTER_SEPARADOR:
            case IMPRESION_TICKET_VALOR_ENCABEZADO:
            
                c.setValorString(update.toString());
                break;
            default:
                throw new ValidatingException("Falta configurar para actualizar " + settingID);

        }
        update(c, true);
    }

    public List<Seccion> getSeccionList() {
        return SeccionDAO.getInstance().findAll();
    }

    public ParametrosConfiguracion getConfiguracionY() {
        return configuracionY;
    }

    public void setConfiguracionY(ParametrosConfiguracion configuracionY) {
        this.configuracionY = configuracionY;
    }

}

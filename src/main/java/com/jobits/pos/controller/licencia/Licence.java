/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.licencia;

import java.util.Date;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.adapters.repo.VentaDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class Licence {

    private static final Licence INSTANCE = new Licence();
    private String licence;
    private TipoLicencia tipoLicencia = TipoLicencia.APLICACION;
    private long fechaFinMilis;
    public boolean LICENCIA_VALIDA = false;
    public boolean LICENCIA_ACTIVA = false;
    public long DIAS_RESTANTES = 0;

    private Licence() {
    }

    public static Licence getInstance() {
        return INSTANCE;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
        descifrarLicenciaCalcularRestante();
        validarLicencia();
    }

    public long getFechaFinMilis() {
        return fechaFinMilis;
    }

    public void setFechaFinMilis(long fechaFinMilis) {
        this.fechaFinMilis = fechaFinMilis;
    }

    public TipoLicencia getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(TipoLicencia tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }
    
    private void descifrarLicenciaCalcularRestante() {
        try {
            Cipher c = new Cipher(tipoLicencia + LicenceController.stringFormatter(SerialNumber.getUID()));
            fechaFinMilis = Long.parseLong(c.decrypt(licence));
            long milisLastSale = new Date().toInstant().toEpochMilli();
            for (Venta v : VentaDAO.getInstance().findAll()) {
                if (v.getFecha().toInstant().toEpochMilli() > milisLastSale) {
                    milisLastSale = v.getFecha().toInstant().toEpochMilli();
                }
            }
            if (milisLastSale > fechaFinMilis) {
                DIAS_RESTANTES = 0;
                LICENCIA_ACTIVA = false;
            } else {
                long milisToday = new Date().toInstant().toEpochMilli();
                if (milisLastSale > milisToday) {
                    milisToday = milisLastSale;
                }
                DIAS_RESTANTES = (fechaFinMilis - milisToday) / 86400000L;
                LICENCIA_ACTIVA = DIAS_RESTANTES >= 0;
            }
        } catch (Exception ex) {
            LICENCIA_VALIDA = false;
            LICENCIA_ACTIVA = false;
            ex.printStackTrace();
        }

    }

    private void validarLicencia() {
        LICENCIA_VALIDA = true;
    }

    public void reset() {
        LICENCIA_ACTIVA = false;
        LICENCIA_VALIDA = false;
    }

    public enum TipoLicencia {
        APLICACION("R-", "lic.key"),
        SECUNDARIA("B-","y.key");

        private final String prefijo,path;

        private TipoLicencia(String prefijo,String path) {
            this.prefijo = prefijo;
            this.path = path;
        }

        public String getPrefijo() {
            return prefijo;
        }

        public String getPath() {
            return path;
        }

        @Override
        public String toString() {
            return prefijo;
        }

        
        
    }

}

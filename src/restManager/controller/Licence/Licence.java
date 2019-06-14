/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.Licence;

import java.util.Date;
import restManager.persistencia.Venta;
import restManager.persistencia.models.VentaDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class Licence {

    private static final Licence INSTANCE = new Licence();
    private String licence;
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

    private void descifrarLicenciaCalcularRestante() {
        try {
            Cipher c = new Cipher("R-" + LicenceController.stringFormatter(SerialNumber.getUID()));
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
                if(milisLastSale > milisToday){
                    milisToday = milisLastSale;
                }
                DIAS_RESTANTES = (fechaFinMilis - milisToday ) / 86400000L;
                LICENCIA_ACTIVA = DIAS_RESTANTES >= 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void validarLicencia() {
        LICENCIA_VALIDA = true;
    }

}

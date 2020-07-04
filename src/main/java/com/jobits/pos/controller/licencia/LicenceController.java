/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.licencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class LicenceController implements LicenceService {

    public static final String LICENCIA_INVALIDA = "Licencia invalida";
    public static final String ERROR_LECTURA_LICENCIA = "Error de lectura de la licencia";
    public static final String LICENCIA_NO_ENCONTRADA = "Archivo de licencia no encontrado o ilegible";
    public static final String ERROR_ESCRITURA = "Error de escritura";

    private final Licence licence = Licence.getInstance();
    private Licence.TipoLicencia tipoLic = Licence.TipoLicencia.APLICACION;
    private String estadoLic = LICENCIA_INVALIDA;

    public LicenceController() {
    }
    
    public LicenceController(Licence.TipoLicencia tipoLic) {
        this.tipoLic = tipoLic;
        estadoLic = getEstadoLicencia(this.tipoLic);
    }


    public String getEstadoLicencia(Licence.TipoLicencia tipo) {
        licence.reset();
        File f = new File(tipo.getPath());
        if (!f.exists() || !f.canRead()) {
            return LICENCIA_NO_ENCONTRADA;
        }
        FileInputStream in;
        String key;
        try {
            in = new FileInputStream(f);
            byte[] b = new byte[32];
            in.read(b);
            key = new String(b);
        } catch (FileNotFoundException ex) {
            return ERROR_LECTURA_LICENCIA;
        } catch (IOException ex) {
            return ERROR_LECTURA_LICENCIA;
        }
        Licence.getInstance().setTipoLicencia(tipo);
        Licence.getInstance().setLicence(key.replaceAll("-", ""));
        if (licence.LICENCIA_VALIDA && licence.LICENCIA_ACTIVA) {
            return "Dias restantes " + licence.DIAS_RESTANTES;
        } else {
            return LICENCIA_INVALIDA;
        }
    }

    public void validateAndSafe(String key) {
        if (validateLicence(key)) {
            saveLicence(key);
            // showSuccessDialog(getView(), "Licencia activa por " + licence.DIAS_RESTANTES + " Dia(s)");
            //getView().dispose();
        } else {
//            showErrorDialog(getView(), LICENCIA_INVALIDA);
        }
    }

    private boolean validateLicence(String key) {
        licence.setLicence(key);
        return licence.LICENCIA_ACTIVA && licence.LICENCIA_VALIDA;
    }

    private void saveLicence(String key) {
        File f = new File(tipoLic.getPath());
        try (FileOutputStream out = new FileOutputStream(f)) {
            out.write(key.getBytes());
            out.flush();
        } catch (FileNotFoundException ex) {
            // showErrorDialog(getView(), LICENCIA_NO_ENCONTRADA);
        } catch (IOException ex) {
            //showErrorDialog(getView(), ERROR_ESCRITURA);
        }
    }

    public Licence getLicence() {
        return licence;
    }

    public String getSoftwareUID() {
        return tipoLic + stringFormatter(SerialNumber.getUID());
    }

    public String getEstadoLic() {
        return estadoLic;
    }

    public static String stringFormatter(String key) {
        int contador = 0;
        String ret = "";
        while (contador < key.length()) {
            if (contador % 4 == 0) {
                ret += "-";
            }
            ret += key.charAt(contador);
            contador++;
        }
        return ret.substring(1);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.Licence;

import GUI.Views.AbstractView;
import GUI.Views.Licence.LicenceDialogView;
import GUI.Views.View;
import java.awt.Container;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import restManager.controller.AbstractController;
import restManager.controller.AbstractDialogController;
import restManager.controller.licencia.Licencia;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Configuracion;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.ConfiguracionDAO;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class LicenceController extends AbstractDialogController<Configuracion> {

    private final Licence licence;
    private Container parent;

    public LicenceController() {
        super(ConfiguracionDAO.getInstance());
        licence = Licence.getInstance();
        getEstadoLicencia();
    }

    public LicenceController(Container parent) {
        super(ConfiguracionDAO.getInstance());
        this.parent = parent;
        licence = Licence.getInstance();
        getEstadoLicencia();
        constructView(parent);
    }

    @Override
    public void constructView(Container parent) {
        setView(new LicenceDialogView(View.DialogType.DEFINED, this, (AbstractView) parent, true));
        getView().updateView();
        getView().setVisible(true);
    }

    public String getEstadoLicencia() {
        File f = new File(R.LICENCE_KEY_PATH);
        if (!f.exists() || !f.canRead()) {
            return "Archivo de licencia no encontrado o ilegible";
        }
        FileInputStream in;
        String key;
        try {
            in = new FileInputStream(f);
            byte[] b = new byte[32];
            in.read(b);
            key = new String(b);
        } catch (FileNotFoundException ex) {
            return "Error de lectura de la licencia";
        } catch (IOException ex) {
            return "Error de lectura de la licencia";
        }
        Licence.getInstance().setLicence(key.replaceAll("-", ""));
        if (licence.LICENCIA_VALIDA && licence.LICENCIA_ACTIVA) {
            return "Dias restantes " + licence.DIAS_RESTANTES;
        } else {
            return "Licencia invalida";
        }
    }

    public void validateAndSafe(String key) {
        if (validateLicence(key)) {
            safeLicence(key);
            showSuccessDialog(getView(), "Licencia activa por " + licence.DIAS_RESTANTES + " Dias");
            getView().dispose();
        } else {
            showErrorDialog(getView(), "La licencia no es valida");
        }
    }

    private boolean validateLicence(String key) {
        licence.setLicence(key);
        return licence.LICENCIA_ACTIVA && licence.LICENCIA_VALIDA;
    }

    private void safeLicence(String key) {
        File f = new File(R.LICENCE_KEY_PATH);
        try (FileOutputStream out = new FileOutputStream(f)) {
            out.write(key.getBytes());
            out.flush();
        } catch (FileNotFoundException ex) {
            showErrorDialog(getView(), "El archivo de licencia no se encontro");
        } catch (IOException ex) {
            showErrorDialog(getView(), "Error de escritura");
        }
    }

    public Licence getLicence() {
        return licence;
    }

    public String getSoftwareUID() {
        return "R-" + stringFormatter(SerialNumber.getUID());
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

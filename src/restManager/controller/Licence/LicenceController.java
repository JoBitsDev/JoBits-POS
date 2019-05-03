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
import restManager.controller.AbstractController;
import restManager.controller.AbstractDialogController;
import restManager.controller.licencia.Licencia;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Configuracion;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.ConfiguracionDAO;

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
        Configuracion c = getModel().find("Lic");
        if (c != null) {
            if (c.getValorString() != null) {
                licence.setLicence(c.getValorString().replaceAll("-", ""));
            }
        }
    }

    public LicenceController(Container parent) {
        super(ConfiguracionDAO.getInstance());
        this.parent = parent;
        licence = Licence.getInstance();
        Configuracion c = getModel().find("Lic");
        if (c != null) {
            if (c.getValorString() != null) {
                licence.setLicence(c.getValorString().replaceAll("-", ""));
            }
        }
        constructView(parent);
    }

    @Override
    public void constructView(Container parent) {
        setView(new LicenceDialogView(View.DialogType.DEFINED, this, (AbstractView) parent, true));
        getView().updateView();
        getView().setVisible(true);
    }

    public String getEstadoLicencia() {
        Configuracion c = getModel().find("Lic");
        if (c == null) {
            return "Error en la licencia";
        }
        if (c.getValorString() == null) {
            return "Licencia nula o faltante";
        }

        Licence.getInstance().setLicence(c.getValorString().replaceAll("-", ""));
        if (licence.LICENCIA_VALIDA && licence.LICENCIA_ACTIVA) {
            return "Dias restantes " + licence.DIAS_RESTANTES;
        } else {
            return "Licencia erronea";
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
        Configuracion c = ConfiguracionDAO.getInstance().find("Lic");
        getModel().startTransaction();
        if (c == null) {
            c = new Configuracion("Lic");
            c.setValorString(stringFormatter(key));
            getModel().create(c);
        } else {
            c.setValorString(stringFormatter(key));
            getModel().edit(c);
        }
        getModel().commitTransaction();
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

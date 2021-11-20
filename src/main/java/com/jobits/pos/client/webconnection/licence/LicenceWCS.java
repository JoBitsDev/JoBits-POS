/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.licence;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.controller.licencia.LicenceService;
import com.jobits.pos.controller.licencia.impl.Licence;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Capa: Services. Esta clase es la encargada del login de un usuario en el
 * sistema, en su creacion recive el usuario y la contrasenna y proporciona un
 * metodo para autenticarlos.
 *
 * @extends SimpleWebConnectionService ya que es un servicio.
 */
public class LicenceWCS extends BaseConnection implements LicenceService {

    LicenceWCI licenceService;

    /**
     * Constructor del servicio, recive el usuario y la contrasenna de los que
     * se van a logear.
     */
    public LicenceWCS() {
        super();
        licenceService = retrofit.create(LicenceWCI.class);
    }

    @Override
    public String getEstadoLicencia(Licence.TipoLicencia tipoLicencia) {
        try {
            var ret = handleResponse(licenceService.getLicence(TENNANT_TOKEN, TOKEN).execute());
        } catch (IOException ex) {
            Logger.getLogger(LicenceWCS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Licencia Activa";
    }

    @Override
    public String getEstadoLic() {
        return "Licencia Activa";
    }

    @Override
    public boolean estaActivaLaLicencia() {
        return true;

    }

    @Override
    public boolean validateAndSafe(String key) {
        return true;
    }

    @Override
    public String getSoftwareUID() {
        return "0000-0000-0000";
    }

}

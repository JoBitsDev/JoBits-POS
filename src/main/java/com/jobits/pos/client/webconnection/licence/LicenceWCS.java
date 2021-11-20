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
        var ret = handleCall(licenceService.getLicence(TENNANT_TOKEN, TOKEN, tipoLicencia.getPrefijo().substring(0, 1)));
        return ret.getEstado();
    }

    @Override
    public String getEstadoLic() {
        return getEstadoLicencia(Licence.TipoLicencia.APLICACION);
    }

    @Override
    public boolean estaActivaLaLicencia() {
        var ret = handleCall(licenceService.getLicence(TENNANT_TOKEN, TOKEN, null));
        return ret.LICENCIA_ACTIVA && ret.LICENCIA_VALIDA;
    }

    @Override
    public boolean validateAndSafe(String key) {
        var ret = handleCall(licenceService.renew(TENNANT_TOKEN, TOKEN, key));
        return ret.LICENCIA_ACTIVA && ret.LICENCIA_VALIDA;
    }

    @Override
    public String getSoftwareUID() {
        var ret = handleCall(licenceService.getUID(TENNANT_TOKEN, TOKEN));
        return ret.get("uid");

    }

}

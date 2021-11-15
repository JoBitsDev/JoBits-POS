/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.login;

import com.jobits.pos.client.webconnection.login.model.UbicacionModel;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.recursos.R;
import org.jobits.db.core.domain.ConexionPropertiesModel;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public interface LoginService {

    public boolean autenticar(String nombreUsuario, char[] toCharArray);

    public boolean autorizar(String usuario, char[] toCharArray);

    public boolean autorizarCurrentUser(R.NivelAcceso nivelMinimoAcceso);

    public void connect(UbicacionModel ubicacionActiva) throws Exception;

    public boolean isConnected();

    public Personal getUsuarioConectado();
}

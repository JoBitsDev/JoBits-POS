/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.login;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.client.webconnection.exception.ServerErrorException;
import com.jobits.pos.client.webconnection.login.model.CredentialsModel;
import com.jobits.pos.client.webconnection.login.model.UbicacionModel;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.recursos.R;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jobits.pos.client.webconnection.domain.Token;
import com.jobits.pos.main.Application;

import retrofit2.Response;

/**
 * Capa: Services. Esta clase es la encargada del login de un usuario en el
 * sistema, en su creacion recive el usuario y la contrasenna y proporciona un
 * metodo para autenticarlos.
 *
 * @extends SimpleWebConnectionService ya que es un servicio.
 */
public class LoginWCS extends BaseConnection implements LoginService {

    LoginWCI loginService;

    private CredentialsModel credentials;

    /**
     * Constructor del servicio, recive el usuario y la contrasenna de los que
     * se van a logear.
     */
    public LoginWCS() {
        super();
    }

    /**
     * Autentica al usuario.
     *
     * @return true si el usuario se auntentica correctamente, false de lo
     * contrario.
     * @throws ServerErrorException si hay error en el servidor.
     * @throws NoConnectionException si no hay coneccion con el servidor.
     */
    public boolean authenticate(String user, String pass) throws Exception {
        initService();
        Response<Map<String, Object>> ret = loginService.getToken(TENNANT_TOKEN,
                HTTP_HEADER_BASIC + new CredentialsModel(user, pass).getBase64BasicAuth()).execute();
        if (ret.isSuccessful()) {
            TOKEN = ret.body().get("Token").toString();
            var personal = oMapper.convertValue(ret.body().get("User"), Personal.class);
            Application.getInstance().setLoggedUser(personal);
            return true;
        } else {
            TOKEN = null;
            throw new ServerErrorException(converter.convert(ret.errorBody()));
        }
    }

    public String getToken() {
        return TOKEN;
    }

    public void setToken(String token) {
        TOKEN = token;
    }

    /**
     * Obtiene el token de la base de datos
     *
     * @param user
     * @param pass
     * @param idUser
     * @param idBaseDatos
     * @return
     * @throws ServerErrorException si hay error en el servidor.
     * @throws NoConnectionException si no hay coneccion con el servidor.
     */
    public boolean getTennantToken(String user, String pass, int idUser, int idBaseDatos)
            throws Exception {
        initService();
        Response<Token> res = loginService.getTennantToken(HTTP_HEADER_BASIC + new CredentialsModel(user, pass).getBase64BasicAuth(), idUser, idBaseDatos).execute();
        if (res.isSuccessful()) {
            TENNANT_TOKEN = res.body().getToken();
            return true;
        }
        TENNANT_TOKEN = null;
        TOKEN = null;
        throw new ServerErrorException(converter.convert(res.errorBody()));

    }

    @Override
    public boolean autenticar(String nombreUsuario, char[] toCharArray) {
        try {
            return authenticate(nombreUsuario, new String(toCharArray));
        } catch (Exception ex) {
            Logger.getLogger(LoginWCS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean autorizar(String usuario, char[] toCharArray) {
        try {
            return authenticate(usuario, new String(toCharArray));
        } catch (Exception ex) {
            Logger.getLogger(LoginWCS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean autorizarCurrentUser(R.NivelAcceso nivelMinimoAcceso) {
        return Application.getInstance().getLoggedUser().getPuestoTrabajonombrePuesto()
                .getNivelAcceso() >= nivelMinimoAcceso.getNivel();
    }

    @Override
    public void connect(UbicacionModel ubicacionActiva) throws Exception {
        getTennantToken(ubicacionActiva.getUsuario(),
                ubicacionActiva.getPassword(),
                ubicacionActiva.getUsuarioId(),
                ubicacionActiva.getBaseDatosId());
    }

    @Override
    public boolean isConnected() {
        return getBearerToken() != null;
    }

    @Override
    public Personal getUsuarioConectado() {
        return Application.getInstance().getLoggedUser();
    }

    private void initService() {
        if (loginService == null) {
            loginService = retrofit.create(LoginWCI.class);
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.login.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Base64;

public class CredentialsModel {

    private String username;
    private String password;

    private CredentialsModel() {
    }

    public CredentialsModel(String username, String password) {
        this(username, password, true);
    }

    public CredentialsModel(String username, String password, boolean plainPassword) {
        this.username = username;
        this.password = plainPassword ? getSHA256(password) : password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBase64BasicAuth() {
        return Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }

    private String getSHA256(String pass) {
        try {
            byte arr[] = MessageDigest.getInstance("SHA-256").digest(pass.getBytes());
            return String.format("%064x", new BigInteger(1, arr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "{ \"username\":\"" + username + "\""
                + ",\"password\":\"" + password + "\" }";
    }
}

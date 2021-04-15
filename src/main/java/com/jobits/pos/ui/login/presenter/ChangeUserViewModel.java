/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.login.presenter;

import com.jobits.pos.ui.viewmodel.AbstractViewModel;

/**
 *
 * @author Home
 */
public class ChangeUserViewModel extends AbstractViewModel {

    private String user;

    public static final String PROP_USER = "user";

    private String password;

    public static final String PROP_PASSWORD = "password";

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        String oldPassword = this.password;
        this.password = password;
        firePropertyChange(PROP_PASSWORD, oldPassword, password);
    }

    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public String getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(String user) {
        String oldUser = this.user;
        this.user = user;
        firePropertyChange(PROP_USER, oldUser, user);
    }

}

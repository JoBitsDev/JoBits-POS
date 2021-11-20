/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.jobits.pos.client.webconnection.licence.LicenceWCS;
import com.jobits.pos.client.webconnection.login.LoginService;
import com.jobits.pos.client.webconnection.login.LoginWCS;
import com.jobits.pos.client.webconnection.login.model.UbicacionService;
import com.jobits.pos.client.webconnection.login.model.UbicacionServiceImpl;
import com.jobits.pos.client.webconnection.venta.VentaWCS;
import com.jobits.pos.controller.licencia.LicenceService;
import com.jobits.pos.controller.venta.VentaDetailService;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
class PosDesktopUIInjectionConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(LoginService.class).to(LoginWCS.class).in(Scopes.SINGLETON);
        bind(UbicacionService.class).to(UbicacionServiceImpl.class).in(Scopes.SINGLETON);
        bind(VentaDetailService.class).to(VentaWCS.class).in(Scopes.SINGLETON);
        bind(LicenceService.class).to(LicenceWCS.class).in(Scopes.SINGLETON);

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.module;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jobits.pos.reserva.repo.util.ResourceServiceImpl;
import com.root101.clean.core.app.modules.AbstractModule;
import com.root101.clean.core.app.modules.DefaultAbstractModule;
import com.root101.clean.core.domain.services.ResourceHandler;
import com.root101.clean.core.exceptions.AlreadyInitModule;
import com.root101.clean.core.exceptions.NotInitModule;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class PosDesktopUiModule extends DefaultAbstractModule {

    private final Injector inj = Guice.createInjector(new PosDesktopUIInjectionConfig());

    private static PosDesktopUiModule INSTANCE;

    public static PosDesktopUiModule getInstance() {
        if (INSTANCE == null) {
            throw new NotInitModule(ResourceHandler.getString("com.jobits.pos.ui.desktop.name"));
        }
        return INSTANCE;
    }

    /**
     * Usar init() sin repo por parametro para usar el repo por defecto
     *
     * @param repoModule
     * @return
     * @Deprecated
     */
    public static PosDesktopUiModule init(AbstractModule... modules) {
        if (INSTANCE != null) {
            throw new AlreadyInitModule(ResourceHandler.getString("com.jobits.pos.ui.desktop.name"));
        }
        INSTANCE = new PosDesktopUiModule();
        for (AbstractModule m : modules) {
            INSTANCE.registerModule(m);
        }
        return getInstance();
    }

    private PosDesktopUiModule() {
        ResourceHandler.registerResourceService(new DesktopUIResourceServiceImpl());
    }

    @Override
    public String getModuleName() {
        return ResourceHandler.getString("com.jobits.pos.ui.desktop.name");
    }

    @Override
    protected <T> T getOwnImplementation(Class<T> type) {
        return inj.getInstance(type);
    }

}

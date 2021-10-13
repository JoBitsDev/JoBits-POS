/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.jobits.pos.cliente.core.usecase.ClienteUseCase;
import com.jobits.pos.ui.clientes.presenter.ClienteUseCaseRFImpl;

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
             //   bind(ClienteUseCase.class).to(ClienteUseCaseRFImpl.class).in(Scopes.SINGLETON);


    }

}

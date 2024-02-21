/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection;

import com.root101.clean.core.app.usecase.CRUDUseCase;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class CRUDBaseConnection<T> extends BaseConnection implements CRUDUseCase<T> {

    @Override
    public T create(T t) throws RuntimeException {
        throw new UnsupportedOperationException();
    }

    @Override
    public T edit(T t) throws RuntimeException {
        throw new UnsupportedOperationException();
    }

    @Override
    public T destroy(T t) throws RuntimeException {
        throw new UnsupportedOperationException();
    }

    @Override
    public T destroyById(Object o) throws RuntimeException {
        throw new UnsupportedOperationException();
    }

    @Override
    public T findBy(Object o) throws RuntimeException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> findAll() throws RuntimeException {
        throw new UnsupportedOperationException();
    }

}

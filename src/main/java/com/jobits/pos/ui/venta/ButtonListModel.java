/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class ButtonListModel<T> extends AbstractViewModel {

    private ArrayListModel<T> list;

    public ButtonListModel(ArrayListModel<T> list) {
        this.list = list;
    }

    public ArrayListModel<T> getList() {
        return list;
    }

    public void setList(ArrayListModel<T> list) {
        this.list = list;
    }

}

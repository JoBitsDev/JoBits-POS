/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.utils;

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ListModel;

/**
 * FirstDream
 *
 * @author Jorge
 * @param <T>
 *
 */
public class RestManagerListModel<T> extends AbstractListModel<T> implements ListModel<T> {

    private final List<T> elements;

    public RestManagerListModel(List<T> elements) {
        this.elements = elements;
    }

    @Override
    public int getSize() {
        return elements != null ? elements.size() : 0;
    }

    @Override
    public T getElementAt(int index) {
        return elements != null ? elements.get(index) : null;
    }

    public List<T> getElements() {
        return elements;
    }

}

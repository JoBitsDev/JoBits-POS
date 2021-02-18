/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.filter.presenter;

/**
 *
 * @author Home
 */
public enum InputType {

    TEXTFIELD("TextField"),
    COMBOBOX("ComboBox");

    /**
     * El recurso a buscar en los .properties
     */
    private final String filterType;

    private InputType(String filterType) {
        this.filterType = filterType;
    }

    public String getFilterType() {
        return filterType;
    }

}

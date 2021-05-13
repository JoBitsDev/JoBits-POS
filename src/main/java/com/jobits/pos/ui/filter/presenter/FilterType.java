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
public enum FilterType {

    COCINA("Cocina", InputType.COMBOBOX),
    INSUMO("Insumo", InputType.TEXTFIELD),
    SECCION("Seccion", InputType.TEXTFIELD),
    AREA("Area", InputType.COMBOBOX),
    PRODUCTO("Producto", InputType.TEXTFIELD),
    IPV("Ipv", InputType.COMBOBOX),
    COCINA_E("Cocina.", InputType.COMBOBOX),
    PRODUCTO_E("Producto.", InputType.TEXTFIELD),
    IPV_E("Ipv.", InputType.COMBOBOX);

    /**
     * El recurso a buscar en los .properties
     */
    private final String filterType;
    private final InputType inputType;

    private FilterType(String filterType, InputType inputType) {
        this.filterType = filterType;
        this.inputType = inputType;
    }

    public String getFilterType() {
        return filterType;
    }

    public InputType getInputType() {
        return inputType;
    }

}

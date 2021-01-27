/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.configuracion;

import com.jobits.pos.io.DataHeader;

/**
 *
 * @author Home
 */
public class DataHeaderWrapper {

    private final DataHeader dataHeader;

    private final String rawHeader;

    public DataHeaderWrapper(DataHeader dataHeader, String rawHeader) {
        this.dataHeader = dataHeader;
        this.rawHeader = rawHeader;
    }

    public DataHeader getDataHeader() {
        return dataHeader;
    }

    public String getRawHeader() {
        return rawHeader;
    }

}

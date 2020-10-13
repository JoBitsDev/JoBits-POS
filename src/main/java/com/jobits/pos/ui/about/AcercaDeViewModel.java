/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.about;

import com.jobits.pos.ui.viewmodel.AbstractViewModel;

/**
 *
 * @author Home
 */
public class AcercaDeViewModel extends AbstractViewModel {

    private String version_sistema;

    public static final String PROP_VERSION_SISTEMA = "version_sistema";

    private String anno_copyrigth;

    public static final String PROP_ANNO_COPYRIGTH = "anno_copyrigth";

    /**
     * Get the value of anno_copyrigth
     *
     * @return the value of anno_copyrigth
     */
    public String getAnno_copyrigth() {
        return anno_copyrigth;
    }

    /**
     * Set the value of anno_copyrigth
     *
     * @param anno_copyrigth new value of anno_copyrigth
     */
    public void setAnno_copyrigth(String anno_copyrigth) {
        String oldAnno_copyrigth = this.anno_copyrigth;
        this.anno_copyrigth = anno_copyrigth;
        firePropertyChange(PROP_ANNO_COPYRIGTH, oldAnno_copyrigth, anno_copyrigth);
    }

    /**
     * Get the value of version_sistema
     *
     * @return the value of version_sistema
     */
    public String getVersion_sistema() {
        return version_sistema;
    }

    /**
     * Set the value of version_sistema
     *
     * @param version_sistema new value of version_sistema
     */
    public void setVersion_sistema(String version_sistema) {
        String oldVersion_sistema = this.version_sistema;
        this.version_sistema = version_sistema;
        firePropertyChange(PROP_VERSION_SISTEMA, oldVersion_sistema, version_sistema);
    }

}

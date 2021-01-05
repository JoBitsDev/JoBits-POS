/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.backup;

import com.jobits.pos.controller.backup.BackUpController.TipoBackUp;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.ui.LongProcessMethod;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Home
 */
public interface BackUpService extends LongProcessMethod {

    String PROPERTY_PROGRESS_UPDATE = "progress";

    boolean EjecutarBackUpLineal(TipoBackUp tipo);

    boolean ExisteVentaEnLocal(Venta v);

    void addPropertyChangeListener(String property, PropertyChangeListener listener);

    //
    // Metodos Heredados SwingWorker
    //
    void execute();

    TipoBackUp getTipoBackUp();

    void incluirDiaAbierto(boolean b);

    boolean isBorradoRemoto();

    void removePropertyCHangeListener(String propertyName, PropertyChangeListener listener);

    void setBorradoRemoto(boolean borradoRemoto);

    //
    // Metodos Publicos
    //
    void setTipoBackUp(TipoBackUp tipoBackUp);
    
}

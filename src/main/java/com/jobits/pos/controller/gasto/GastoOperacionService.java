/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.gasto;

import com.jobits.pos.domain.models.Gasto;
import com.jobits.pos.domain.models.GastoVenta;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.recursos.R;
import java.awt.Container;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Home
 */
public interface GastoOperacionService {

    void constructView(Container parent);

    void createNewGasto(R.TipoGasto cat, String nombre, float monto, String descripcion);

    Gasto createNewInstanceAndAdd();

    Gasto editInstance(Gasto instance);

    List<GastoVenta> getGastos(Date dia);

    /**
     *
     * @return
     */
    List<GastoVenta> getLista();

    List<String> getNombres(String toString);

    float getValorTotalGastos();

    void removeGasto(GastoVenta objectAtSelectedRow);

    void setDiaVenta(Venta diaVenta);
    
}

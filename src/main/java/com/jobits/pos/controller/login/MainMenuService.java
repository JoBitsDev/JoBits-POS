/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.login;

import com.jobits.pos.controller.venta.VentaDetailController;
import com.jobits.pos.domain.models.Venta;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ERIK QUESADA
 */
public interface MainMenuService {

    public VentaDetailController comenzarVentasEconomico(Date parse);
    
    public VentaDetailController comenzarVentasDependiente();
    
    public VentaDetailController comenzarVentasCajero();

    public boolean estaActivaLaLicencia();

    public List<Venta> getDiaVentaSeleccionado();
}

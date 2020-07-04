/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.login;

import com.jobits.pos.controller.venta.VentaDetailController;
import java.util.Date;

/**
 *
 * @author ERIK QUESADA
 */
public interface MainMenuService {

    public VentaDetailController comenzarVentas(Date parse);
    
}

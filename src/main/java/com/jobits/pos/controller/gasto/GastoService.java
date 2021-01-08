/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.gasto;

import com.jobits.pos.adapters.repo.impl.GastoDAO;
import java.awt.Container;

/**
 *
 * @author Home
 */
public interface GastoService {

    void constructView(Container parent);

    GastoDAO getModel();
    
}

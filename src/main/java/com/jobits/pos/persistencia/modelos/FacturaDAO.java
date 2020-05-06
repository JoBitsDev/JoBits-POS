/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.persistencia.modelos;


import com.jobits.pos.persistencia.Factura;
import com.jobits.pos.persistencia.ContabilidadCuenta;
import com.jobits.pos.persistencia.modelos.AbstractModel;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class FacturaDAO extends AbstractModel<Factura> {

    private static FacturaDAO INSTANCE = null;

    private FacturaDAO() {
        super(Factura.class);
    }

    public static FacturaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FacturaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
       
}

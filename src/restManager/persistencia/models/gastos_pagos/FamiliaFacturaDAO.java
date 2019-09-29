/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models.gastos_pagos;


import restManager.persistencia.gastos_pagos.FamiliaFactura;
import restManager.persistencia.models.AbstractModel;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class FamiliaFacturaDAO extends AbstractModel<FamiliaFactura> {

    private static FamiliaFacturaDAO INSTANCE = null;

    private FamiliaFacturaDAO() {
        super(FamiliaFactura.class);
    }

    public static FamiliaFacturaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FamiliaFacturaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
       
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo;



import com.jobits.pos.domain.models.TipoGasto;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class TipoGastoDAO extends AbstractModel<TipoGasto> {

    private static TipoGastoDAO INSTANCE = null;

    private TipoGastoDAO() {
        super(TipoGasto.class);
    }

    public static TipoGastoDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TipoGastoDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
       
}

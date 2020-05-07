/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo;


import com.jobits.pos.domain.models.ContabilidadCuenta;
import com.jobits.pos.adapters.repo.AbstractRepository;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ContabilidadCuentaDAO extends AbstractRepository<ContabilidadCuenta> {

    private static ContabilidadCuentaDAO INSTANCE = null;

    private ContabilidadCuentaDAO() {
        super(ContabilidadCuenta.class);
    }

    public static ContabilidadCuentaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ContabilidadCuentaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
       
}

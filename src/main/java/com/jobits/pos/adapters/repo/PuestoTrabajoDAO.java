/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo;


import com.jobits.pos.domain.models.PuestoTrabajo;
import com.jobits.pos.domain.models.Seccion;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PuestoTrabajoDAO extends AbstractRepository<PuestoTrabajo> {

    private static PuestoTrabajoDAO INSTANCE = null;

    private PuestoTrabajoDAO() {
        super(PuestoTrabajo.class);
    }

    public static PuestoTrabajoDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PuestoTrabajoDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
       
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo.autenticacion;


import com.jobits.pos.adapters.repo.impl.AbstractRepository;
import com.jobits.pos.domain.models.Personal;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PersonalDAO extends AbstractRepository<Personal> {

    private static PersonalDAO INSTANCE = null;

    private PersonalDAO() {
        super(Personal.class);
    }

    public static PersonalDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PersonalDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

   
    
}

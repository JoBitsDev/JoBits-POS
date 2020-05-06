/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.persistencia.modelos;


import com.jobits.pos.persistencia.Personal;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PersonalDAO extends AbstractModel<Personal> {

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

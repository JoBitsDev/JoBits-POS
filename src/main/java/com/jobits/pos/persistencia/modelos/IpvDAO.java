/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.persistencia.modelos;

import com.jobits.pos.persistencia.Carta;
import com.jobits.pos.persistencia.Ipv;
import com.jobits.pos.persistencia.Mesa;
import com.jobits.pos.persistencia.Nota;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class IpvDAO extends AbstractModel<Ipv> {

    private static IpvDAO INSTANCE = null;

    private IpvDAO() {
        super(Ipv.class);
    }

    public static IpvDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IpvDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
}

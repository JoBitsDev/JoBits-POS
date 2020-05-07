/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo;

import com.jobits.pos.domain.models.Carta;
import com.jobits.pos.domain.models.Ipv;
import com.jobits.pos.domain.models.Mesa;
import com.jobits.pos.domain.models.Nota;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class IpvDAO extends AbstractRepository<Ipv> {

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

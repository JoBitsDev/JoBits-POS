/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.main;

import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.recursos.R;
import com.root101.clean.core.app.services.UserResolverService;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class UserResolverServiceImpl implements UserResolverService<Personal> {

    @Override
    public Personal resolveUser() {
        return R.loggedUser;
    }

}

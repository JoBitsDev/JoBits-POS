/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.adapters.repo.impl;

import com.jobits.pos.domain.models.Orden;
import com.jobits.pos.domain.models.ProductovOrden;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class OrdenDAO extends AbstractRepository<Orden>{

    private static OrdenDAO INSTANCE = null;
    
    private OrdenDAO() {
        super(Orden.class);
    }
    
    public static OrdenDAO getInstance(){
        if(INSTANCE == null){
            INSTANCE = new OrdenDAO();
            return INSTANCE;
        }else{
            return INSTANCE;
        }
    }

    @Override
    public void remove(Orden entity) {
//        startTransaction();
//        for (ProductovOrden o : entity.getProductovOrdenList()) {
//            ProductovOrdenDAO.getInstance().remove(o);
//        }
//        commitTransaction();
        super.remove(entity);
        
    }
    
    
    

}

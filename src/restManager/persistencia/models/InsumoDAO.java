/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;

import restManager.persistencia.Insumo;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class InsumoDAO extends AbstractModel<Insumo> {

    public InsumoDAO() {
        super(Insumo.class);
    }

    @Override
    public void edit(Insumo entity) {
        Insumo oldInsumo = find(entity.getCodInsumo());
        startTransaction();
        oldInsumo.getInsumoElaboradoList().forEach((x) -> {
            getEntityManager().remove(x);
        });
        commitTransaction();

        startTransaction();

        entity.getInsumoElaboradoList().forEach((x) -> {
            getEntityManager().persist(x);
        });
        
         super.edit(entity);
        commitTransaction();
        
       
    }

}

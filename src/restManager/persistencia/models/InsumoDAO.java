/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;

import restManager.persistencia.Insumo;
import restManager.persistencia.InsumoElaborado;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class InsumoDAO extends AbstractModel<Insumo> {

    private static InsumoDAO INSTANCE = null;

    private InsumoDAO() {
        super(Insumo.class);
    }

    public static InsumoDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InsumoDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    @Override
    public void edit(Insumo entity) {
        Insumo oldInsumo = find(entity.getCodInsumo());
        startTransaction();
        for (InsumoElaborado x : oldInsumo.getInsumoElaboradoList()) {
            getEntityManager().remove(x);
        }
        commitTransaction();

        startTransaction();

        for (InsumoElaborado x : entity.getInsumoElaboradoList()) {
            getEntityManager().persist(x);
        }

        super.edit(entity);
        commitTransaction();

    }

}

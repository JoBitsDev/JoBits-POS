/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;

import java.util.ArrayList;
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

    @Override
    public void create(Insumo insumo) {
        if (insumo.getProductoInsumoList() == null) {
            insumo.setProductoInsumoList(new ArrayList<>());
        }
        if (insumo.getIpvList() == null) {
            insumo.setIpvList(new ArrayList<>());
        }
        if (insumo.getInsumoElaboradoList() == null) {
            insumo.setInsumoElaboradoList(new ArrayList<>());
        }
        if (insumo.getInsumoElaboradoList1() == null) {
            insumo.setInsumoElaboradoList1(new ArrayList<>());
        }

        if (insumo.getInsumoAlmacenList() == null) {
            insumo.setInsumoAlmacenList(new ArrayList<>());
        }

        if (insumo.getTransaccionList() == null) {
            insumo.setTransaccionList(new ArrayList<>());
        }
        getEntityManager().persist(insumo);
    }

}

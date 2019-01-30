/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;

import java.util.List;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Almacen;
import restManager.persistencia.InsumoAlmacen;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class InsumoAlmacenDAO extends AbstractModel<InsumoAlmacen> {

    private static InsumoAlmacenDAO INSTANCE = null;

    private InsumoAlmacenDAO() {
        super(InsumoAlmacen.class);
    }

    public static InsumoAlmacenDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InsumoAlmacenDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    @Override
    public void create(InsumoAlmacen entity) {
        startTransaction();
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
        commitTransaction();
    }

    public List<InsumoAlmacen> getInsumoAlmacenList(Almacen a) {
                return getEntityManager().createNamedQuery("InsumoAlmacen.findByAlmacencodAlmacen")
                .setParameter("almacencodAlmacen", a.getCodAlmacen())
                .getResultList();
    }

}

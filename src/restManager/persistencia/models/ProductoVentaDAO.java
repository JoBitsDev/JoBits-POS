/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;

import restManager.persistencia.ProductoVenta;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ProductoVentaDAO extends AbstractModel<ProductoVenta> {

    private static ProductoVentaDAO INSTANCE = null;

    private ProductoVentaDAO() {
        super(ProductoVenta.class);
    }

    public static ProductoVentaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductoVentaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    @Override
    public void edit(ProductoVenta entity) {
        ProductoVenta oldProd = find(entity.getPCod());
        startTransaction();
        for (int i = 0; i < oldProd.getProductoInsumoList().size(); i++) {
            getEntityManager().remove(oldProd.getProductoInsumoList().get(i));
        }
        commitTransaction();
        startTransaction();
        for (int i = 0; i < entity.getProductoInsumoList().size(); i++) {
            getEntityManager().persist(entity.getProductoInsumoList().get(i));
        }
        super.edit(entity); //To change body of generated methods, choose Tools | Templates.

        commitTransaction();
    }

}

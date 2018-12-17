/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;

import restManager.persistencia.ProductovOrden;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ProductovOrdenDAO extends AbstractModel<ProductovOrden> {

    private static ProductovOrdenDAO INSTANCE = null;

    private ProductovOrdenDAO() {
        super(ProductovOrden.class);
    }

    public static ProductovOrdenDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductovOrdenDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
}

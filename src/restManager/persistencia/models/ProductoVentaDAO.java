/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Carta;
import restManager.persistencia.Mesa;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.Seccion;

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

    public List<ProductoVenta> findAllVisible() {
        List<ProductoVenta> ret = new ArrayList<>();
        findAll().stream().filter((x) -> (x.getVisible())).forEachOrdered((x) -> {
            ret.add(x);
        });
        Collections.sort(ret,(o1, o2) -> {
            return o1.getNombre().compareTo(o2.getNombre());
        });
        return ret;
    }

    public List<ProductoVenta> findAllVisible(Mesa mesacodMesa) {
        ArrayList<ProductoVenta> productosDisp = new ArrayList<>();
        for (Carta c : mesacodMesa.getAreacodArea().getCartaList()) {
            for (Seccion s : c.getSeccionList()) {
                productosDisp.addAll(new ArrayList<>(s.getProductoVentaList()));
            }
        }
          List<ProductoVenta> ret = new ArrayList<>();
        productosDisp.stream().filter((x) -> (x.getVisible())).forEachOrdered((x) -> {
            ret.add(x);
        });
        Collections.sort(ret,(o1, o2) -> {
            return o1.getNombre().compareTo(o2.getNombre());
        });
        return ret;
    }

}

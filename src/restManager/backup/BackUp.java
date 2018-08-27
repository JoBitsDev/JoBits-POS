/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.backup;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import restManager.persistencia.Area;
import restManager.persistencia.Cocina;
import restManager.persistencia.Insumo;
import restManager.persistencia.Mesa;
import restManager.persistencia.Orden;
import restManager.persistencia.ProductoInsumo;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.ProductovOrden;
import restManager.persistencia.Seccion;
import restManager.persistencia.Venta;

/**
 * FirstDream
 *
 * @author Jorge
 *
 *
 */
public class BackUp {

    private EntityManagerFactory emf;
    private final EntityManager em;

    
    //
    //Constructores
    //
    
    public BackUp() {

        emf = Persistence.createEntityManagerFactory("Restaurant_Manager_1.01PU_ext");
        em = emf.createEntityManager();
    }

    public BackUp(EntityManagerFactory emf) {
        this.emf = emf;
        em = emf.createEntityManager();
    }

    public BackUp(String persistenceUnitName) {
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        em = emf.createEntityManager();

    }
    
    //
    //Metodos Publicos
    //
    

    
    public boolean startBackupTransaction() {
        em.getTransaction().begin();
        return true;
    }

    public boolean commitBackupTransaction() {
        em.getTransaction().commit();
        return true;
    }

    public boolean backUpArea(List<Area> areas) {
        for (Area a : areas) {
            if (EntityExist(a, a.getCodArea())) {
                em.merge(a);
            } else {
                em.persist(a);
            }

            backUPMesa(a.getMesaList());

        }
        return true;
    }

    public boolean backUPMesa(List<Mesa> mesas) {
        for (Mesa m : mesas) {

            if (EntityExist(m, m.getCodMesa())) {
                em.merge(m);
            } else {
                em.persist(m);
            }

        }
        return true;
    }

    public boolean backUPCocina(List<Cocina> cocina) {
        for (Cocina c : cocina) {
            if (EntityExist(c, c.getCodCocina())) {
                em.merge(c);
            } else {
                em.persist(c);
            }
        }
        return true;
    }

    public boolean BackUpProd(List<ProductoVenta> prods) {
        for (ProductoVenta pv : prods) {
            pv.setProductovOrdenList(null);
            BackUpProdInsumo(pv.getProductoInsumoList());

            if (EntityExist(pv, pv.getPCod())) {
                em.merge(pv);
            } else {
                em.persist(pv);

            }

        }
        return true;
    }

    public boolean BackUPSecciones(List<Seccion> seccion) {
        for (Seccion s : seccion) {
            if (EntityExist(s, s.getNombreSeccion())) {
                em.merge(s);
            } else {
                em.persist(s);

            }
        }
        return true;
    }

    public boolean BackUpOrdenes(List<Orden> ords) {
        for (Orden o : ords) {
        BackUpProdvOrden(o.getProductovOrdenList());
        
            if (EntityExist(o, o.getCodOrden())) {
                em.merge(o);    
            } else {
                em.persist(o);
            }
        }

        return true;
    }
    
     public boolean BackUpProdvOrden(List<ProductovOrden> productovOrdenList) {
         for (ProductovOrden po : productovOrdenList) {
             if (EntityExist(po, po.getProductovOrdenPK())) {
                 em.merge(po);
             } else {
                 em.persist(po);
             }
         }
         
         return true;
     
     }
    
    public boolean BackUpVentas(List<Venta> ventas){
        
        for (Venta v : ventas) {
            
            BackUpOrdenes(v.getOrdenList());
            
            if (EntityExist(v, v.getFecha())) {
                em.merge(v);
            } else {
                em.persist(v);
            }
        }
        
        return true;
        
    }

    public boolean BackUpInsumos(List<Insumo> ins) {
        for (Insumo in : ins) {
            if (EntityExist(in, in.getCodInsumo())) {
                em.merge(in);

            } else {
                em.persist(in);
            }
        }
        return true;
    }

    public boolean BackUpProdInsumo(List<ProductoInsumo> pIns) {
        for (ProductoInsumo x : pIns) {
            if (EntityExist(x, x.getProductoInsumoPK())) {
                em.merge(x);
            } else {
                em.persist(x);

            }

        }
        return true;
    }
    
    //
    //Metodos Privados
    //

    private boolean EntityExist(Object entity, Object primaryKey) {

        return em.find(entity.getClass(), primaryKey) != null;

    }

   

}

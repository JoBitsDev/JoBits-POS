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
import restManager.persistencia.Cocina;
import restManager.persistencia.Mesa;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.Seccion;
import restManager.persistencia.jpa.staticContent;

/**
 * FirstDream
 *
 * @author Jorge
 *
 *
 */
public class BackUp {

    private EntityManagerFactory emf;
    private EntityManager em;

    public BackUp() {
        emf = Persistence.createEntityManagerFactory("Restaurant_Manager_1.01PU_ext");
        em = emf.createEntityManager();
    }
    
    public boolean startBackup(){
        em.getTransaction().begin();
        return true;
    }
    
    public boolean commitBackup(){
        em.getTransaction().commit();
        return true;
    }

    public boolean backUPMesa(List<Mesa> mesas) {
        for (Mesa m : mesas) {
            em.persist(m.getAreacodArea());
            em.persist(m);
        }
        return true;
    }
    
    public boolean backUPCocina(List<Cocina> cocina){
        for (Cocina c : cocina) {
            em.persist(c);
        }
        return true;
    }
    
    public boolean backUpProd(List<ProductoVenta> prods){
        for (ProductoVenta pv : prods) {
            pv.setProductovOrdenList(null);
            pv.setProductovOrdenArchivadoList(null);
            em.persist(pv);
            
        }
        return true;
    }

    public boolean backUPSecciones(List<Seccion> seccion) {
        for (Seccion s : seccion) {
            em.persist(s);
        }
        return true;
    }

}

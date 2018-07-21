/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.jpa;


import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;

/**
 *
 * @author Jorge
 */
public class staticContent {
    
    public static String host = "localhost",port = "5432",
            data_base_name = "Rest_Manager_DB",user = "Rest_Admin";
    
    public static char [] pass = {'a','d','m','i','n'};

    public static EntityManagerFactory EMF = 
            Persistence.createEntityManagerFactory("Restaurant_Manager_1.01PU");

    public static ProductoVentaJpaController productoJPA = 
            new ProductoVentaJpaController(EMF);
    
    public static CocinaJpaController cocinaJPA = 
            new CocinaJpaController(EMF);
    
    public static SeccionJpaController seccionJPA = 
            new SeccionJpaController(EMF);
    
    public static PersonalJpaController personalJPA= 
            new PersonalJpaController(EMF);

    public static PuestoTrabajoJpaController puestosJPA = 
            new PuestoTrabajoJpaController(EMF);

    public static DatosPersonalesJpaController datosPJPA = 
            new DatosPersonalesJpaController(EMF);
    
    public static VentaJpaController ventaJPA = 
            new VentaJpaController(EMF);
    
    public static CartaJpaController cartaJPA = 
            new CartaJpaController(EMF);
    
    public static MesaJpaController mesasJPA = 
            new MesaJpaController(EMF);
    
    public static AreaJpaController areaJPA = 
            new AreaJpaController(EMF);
    
    public static OrdenJpaController ordenJPA = 
            new OrdenJpaController(EMF);
    
    public static ProductovOrdenJpaController productovOrdenJpa = 
            new ProductovOrdenJpaController(EMF);
    
    public static ConfiguracionJpaController configJPA = 
            new ConfiguracionJpaController(EMF);
    
    public static InsumoJpaController insumoJPA = 
            new InsumoJpaController(EMF);
    
    public static ProductoInsumoJpaController productoInsumo = 
            new ProductoInsumoJpaController(EMF);
    
    public static IpvJpaController ipvJPA = new IpvJpaController(EMF);
    
    
    public static IpvRegistroJpaController ipvregJPA = 
            new IpvRegistroJpaController(EMF);
    
    public static NotaJpaController notaJPA = new NotaJpaController(EMF);
    
    
    /**
     * refresca el cache de la conexion haciendo posible la actualizacion en
     * tiempo real
     */
    public static void clearConnCache() {
        staticContent.EMF.getCache().evictAll();
        
        
    }
  

   
}


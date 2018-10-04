/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.jpa;

import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;
import restManager.resources.R;

/**
 *
 * @author Jorge
 */
public class staticContent {

    private static boolean CONECTADO;

    private static  String persistenceUnitName;

    private static EntityManagerFactory EMF;

    public static ProductoVentaJpaController productoJPA;

    public static CocinaJpaController cocinaJPA;

    public static SeccionJpaController seccionJPA;

    public static PersonalJpaController personalJPA;

    public static PuestoTrabajoJpaController puestosJPA;

    public static DatosPersonalesJpaController datosPJPA;

    public static VentaJpaController ventaJPA;

    public static CartaJpaController cartaJPA;

    public static MesaJpaController mesasJPA;

    public static AreaJpaController areaJPA;

    public static OrdenJpaController ordenJPA;

    public static ProductovOrdenJpaController productovOrdenJpa;

    public static ConfiguracionJpaController configJPA;

    public static InsumoJpaController insumoJPA;

    public static ProductoInsumoJpaController productoInsumo;

    public static IpvJpaController ipvJPA;

    public static IpvRegistroJpaController ipvregJPA;

    public static NotaJpaController notaJPA;

    private staticContent(String persistenceUnitName) {
        staticContent.persistenceUnitName = persistenceUnitName;
        EMF = Persistence.createEntityManagerFactory(persistenceUnitName);
        if (EMF != null) {
            initJPAConnections();
           
        } else {
            throw new NullPointerException(R.RESOURCE_BUNDLE.getString("null_pointer_EMF_not_Found"));
        }
    }

    /**
     * refresca el cache de la conexion haciendo posible la actualizacion en
     * tiempo real
     */
    public static void clearConnCache() {
        if (EMF != null) {
            EMF.getCache().evictAll();

        } else {
            throw new NullPointerException(R.RESOURCE_BUNDLE.getString("null_pointer_EMF_not_Found"));
        }
    }

    public static void clearCache(Class cls) {
        if (EMF != null) {
            EMF.getCache().evict(cls);
        } else {
            throw new NullPointerException(R.RESOURCE_BUNDLE.getString("null_pointer_EMF_not_Found"));
        }

    }

    public static EntityManagerFactory getEMF() {
        return EMF;
    }

    public static boolean isCONECTADO() {
        return CONECTADO;
    }
    
    private void initJPAConnections() {

        try {
            EMF.createEntityManager();
            
            productoJPA = new ProductoVentaJpaController(EMF);

            cocinaJPA = new CocinaJpaController(EMF);

            seccionJPA = new SeccionJpaController(EMF);

            personalJPA = new PersonalJpaController(EMF);

            puestosJPA = new PuestoTrabajoJpaController(EMF);

            datosPJPA = new DatosPersonalesJpaController(EMF);

            ventaJPA = new VentaJpaController(EMF);

            cartaJPA = new CartaJpaController(EMF);

            mesasJPA = new MesaJpaController(EMF);

            areaJPA = new AreaJpaController(EMF);

            ordenJPA = new OrdenJpaController(EMF);

            productovOrdenJpa = new ProductovOrdenJpaController(EMF);

            configJPA = new ConfiguracionJpaController(EMF);

            insumoJPA = new InsumoJpaController(EMF);

            productoInsumo = new ProductoInsumoJpaController(EMF);

            ipvJPA = new IpvJpaController(EMF);

            ipvregJPA = new IpvRegistroJpaController(EMF);

            notaJPA = new NotaJpaController(EMF);

            CONECTADO = true;

        } catch (Exception e) {
            CONECTADO = false;
            System.out.println(e.getMessage());
        }

    }

    public static staticContent init(String persistenceUnitName){
        return new staticContent(persistenceUnitName);
    }

    public static String getPersistenceUnitName() {
        return persistenceUnitName;
    }
    
    
    
    
}

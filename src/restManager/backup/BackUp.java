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
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.ExceptionHandler;
import restManager.persistencia.Area;
import restManager.persistencia.Carta;
import restManager.persistencia.Cocina;
import restManager.persistencia.Configuracion;
import restManager.persistencia.DatosPersonales;
import restManager.persistencia.GastoVenta;
import restManager.persistencia.Insumo;
import restManager.persistencia.Mesa;
import restManager.persistencia.Orden;
import restManager.persistencia.Personal;
import restManager.persistencia.ProductoInsumo;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.ProductovOrden;
import restManager.persistencia.PuestoTrabajo;
import restManager.persistencia.Seccion;
import restManager.persistencia.Venta;
import restManager.persistencia.models.AreaDAO;
import restManager.persistencia.models.CartaDAO;
import restManager.persistencia.models.CocinaDAO;
import restManager.persistencia.models.ConfiguracionDAO;
import restManager.persistencia.models.InsumoDAO;
import restManager.persistencia.models.MesaDAO;
import restManager.persistencia.models.PersonalDAO;
import restManager.persistencia.models.ProductoVentaDAO;
import restManager.persistencia.models.PuestoTrabajoDAO;
import restManager.persistencia.models.SeccionDAO;
import restManager.persistencia.models.VentaDAO;
import restManager.resources.R;
import restManager.util.LoadingWindow;
import restManager.util.comun;

/**
 * FirstDream
 *
 * @author Jorge
 *
 *
 */
public class BackUp extends SwingWorker<Boolean, Float> {

    private final EntityManagerFactory emf;
    private final EntityManager em;
    private JProgressBar barraDeProgreso;
    private TipoBackUp tipoBackUp;
    private boolean borradoRemoto = false;
    private float progress = 0;
    private float topeProceso = 100;

    //
    //Constructores
    //
    public BackUp(TipoBackUp tipoBackUp) {
        this.tipoBackUp = tipoBackUp;
        emf = Persistence.createEntityManagerFactory(R.RESOURCE_BUNDLE.getString("unidad_persistencia_local"));
        em = emf.createEntityManager();
    }

    public BackUp(String persistenceUnitName, JProgressBar barraProgreso, TipoBackUp tipoBackUp) {
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        em = emf.createEntityManager();
        this.barraDeProgreso = barraProgreso;
        this.tipoBackUp = tipoBackUp;
    }

    //
    // Metodos Publicos
    //
    public void setTipoBackUp(TipoBackUp tipoBackUp) {
        this.tipoBackUp = tipoBackUp;
    }

    public TipoBackUp getTipoBackUp() {
        return tipoBackUp;

    }

    public JProgressBar getBarraDeProgreso() {
        return barraDeProgreso;
    }

    public void setBarraDeProgreso(JProgressBar barraDeProgreso) {
        this.barraDeProgreso = barraDeProgreso;
    }

    public boolean isBorradoRemoto() {
        return borradoRemoto;
    }

    public void setBorradoRemoto(boolean borradoRemoto) {
        this.borradoRemoto = borradoRemoto;
    }

    //
    // Metodos Heredados SwingWorker
    //
    @Override
    protected Boolean doInBackground() throws Exception {

        EjecutarBackUp(tipoBackUp);
        if (borradoRemoto) {
            BorradoRemotoVentas(VentaDAO.getInstance().findAll());
        }
        return true;
    }

    @Override
    protected void done() {
        LoadingWindow.hide();
        super.done(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void process(List<Float> chunks) {
        for (Float chunk : chunks) {
            barraDeProgreso.setValue(chunk.intValue());
            barraDeProgreso.setString(comun.setDosLugaresDecimalesFloat(chunk) + "%");
        }
    }

    //
    //Metodos Privados
    //
    private boolean startBackupTransaction() {
        em.getTransaction().begin();
        return true;
    }

    private boolean commitBackupTransaction() {
        em.flush();
        em.getTransaction().commit();
        return true;
    }

    //
    //BackUps de entidades
    //
    private boolean BackUpCarta(List<Carta> findCartaEntities) {
        float sumaXCantidad = topeProceso / findCartaEntities.size();
        for (Carta x : findCartaEntities) {
            if (EntityExist(x, x.getCodCarta())) {
                em.merge(x);
            } else {
                em.persist(x);
            }

            BackUPSecciones(x.getSeccionList());

            incrementarProgreso(sumaXCantidad);
        }
        return true;

    }

    private boolean backUpArea(List<Area> areas) {
        float sumaXCantidad = topeProceso / areas.size();
        for (Area a : areas) {
            if (EntityExist(a, a.getCodArea())) {
                em.merge(a);
            } else {
                em.persist(a);
            }

            backUPMesa(a.getMesaList());
            incrementarProgreso(sumaXCantidad);
        }
        return true;
    }

    private boolean backUPMesa(List<Mesa> mesas) {
        for (Mesa m : mesas) {

            if (EntityExist(m, m.getCodMesa())) {
                em.merge(m);
            } else {
                em.persist(m);
            }

        }
        return true;
    }

    private boolean backUPCocina(List<Cocina> cocina) {
        float sumaXCantidad = topeProceso / cocina.size();
        for (Cocina c : cocina) {
            c.setIpvList(null);
            if (EntityExist(c, c.getCodCocina())) {
                em.merge(c);
            } else {
                em.persist(c);
            }
            incrementarProgreso(sumaXCantidad);
        }
        return true;
    }

    private boolean BackUpProd(List<ProductoVenta> prods) {
        float sumaXCantidad = topeProceso / prods.size();
        System.out.println("inicio productos venta");
        for (ProductoVenta pv : prods) {
            pv.setProductovOrdenList(null);
            System.out.println(pv);
            BackUpProdInsumo(pv.getProductoInsumoList());

            if (EntityExist(pv, pv.getPCod())) {
                em.merge(pv);
            } else {
                em.persist(pv);

            }
            incrementarProgreso(sumaXCantidad);

        }
        return true;
    }

    private boolean BackUPSecciones(List<Seccion> seccion) {
        for (Seccion s : seccion) {
            if (EntityExist(s, s.getNombreSeccion())) {
                em.merge(s);
            } else {
                em.persist(s);

            }
        }
        return true;
    }

    private boolean BackUpOrdenes(List<Orden> ords) {
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

    private boolean BackUpProdvOrden(List<ProductovOrden> productovOrdenList) {
        for (ProductovOrden po : productovOrdenList) {
            if (EntityExist(po, po.getProductovOrdenPK())) {
                em.merge(po);
            } else {
                em.persist(po);
            }
        }

        return true;

    }

    private boolean BackUpVentas(List<Venta> ventas) {
        float sumaXCantidad = topeProceso / ventas.size();
        for (Venta v : ventas) {
            BackUpOrdenes(v.getOrdenList());
            BackUpGastos(v.getGastoVentaList());
            if (EntityExist(v, v.getFecha())) {
                em.merge(v);
            } else {
                em.persist(v);
            }
            incrementarProgreso(sumaXCantidad);
        }

        return true;

    }

    private boolean BackUpInsumos(List<Insumo> ins) {
        float sumaXCantidad = topeProceso / ins.size();
        for (Insumo in : ins) {
            System.out.println(in.toString());
            in.setProductoInsumoList(null);
            in.setTransaccionList(null);
            in.setInsumoAlmacenList(null);
            if (EntityExist(in, in.getCodInsumo())) {
                System.out.println("Actualizando " + in);
                em.merge(in);

            } else {
                System.out.println("Creando " + in);
                em.persist(in);
            }
            incrementarProgreso(sumaXCantidad);
        }
        return true;
    }

    private boolean BackUpProdInsumo(List<ProductoInsumo> pIns) {
        for (ProductoInsumo x : pIns) {
            if (EntityExist(x, x.getProductoInsumoPK())) {
                em.merge(x);
            } else {
                em.persist(x);

            }

        }
        return true;
    }

    private boolean BackUpPersonal(List<Personal> p) {
        float sumaXCantidad = topeProceso / p.size();
        for (Personal x : p) {
            if (EntityExist(x, x.getUsuario())) {
                em.merge(x);
            } else {
                x.setOrdenList(null);
                em.persist(x);
            }
            incrementarProgreso(sumaXCantidad);
        }
        return true;
    }

    private boolean BackUpPuestoDeTrabajo(List<PuestoTrabajo> puestos) {
        float sumaXCantidad = topeProceso / puestos.size();
        for (PuestoTrabajo puesto : puestos) {
            if (EntityExist(puesto, puesto.getNombrePuesto())) {
                em.merge(puesto);
            } else {
                em.persist(puesto);
            }
            incrementarProgreso(sumaXCantidad);
        }

        return true;
    }

    private boolean BackUpDatosPersonales(List<DatosPersonales> datosP) {
        for (DatosPersonales x : datosP) {
            if (EntityExist(x, x.getPersonalusuario())) {
                em.merge(x);
            } else {
                em.persist(x);
            }
        }
        return true;
    }

    private boolean BackUpConfiguracion(List<Configuracion> configs) {
        for (Configuracion x : configs) {
            if (EntityExist(x, x.getNombre())) {
                em.merge(x);
            } else {
                em.persist(x);
            }
        }
        return true;
    }

    private boolean EntityExist(Object entity, Object primaryKey) {
        return em.find(entity.getClass(), primaryKey) != null;
    }

    private void incrementarProgreso(float i) {
        progress += i;
        publish(progress);
    }

    //
    // Borrados Remotos
    //
    //TODO: Mal hecho
    private boolean BorradoRemotoVentas(List<Venta> ventas) {
        for (Venta x : ventas) {
            VentaDAO.getInstance().remove(x);
        }
        return true;
    }

    //
    // scripts de backup
    //
    private boolean EjecutarBackUpPersonal() {
        startBackupTransaction();
        BackUpPuestoDeTrabajo(PuestoTrabajoDAO.getInstance().findAll());
        BackUpPersonal(PersonalDAO.getInstance().findAll());
//        BackUpDatosPersonales(Dato);
        commitBackupTransaction();
        return true;
    }

    private boolean EjecutarBackUpProductos() {
        startBackupTransaction();
        //backup area
        backUpArea(AreaDAO.getInstance().findAll());

//        commitBackupTransaction();
//        System.out.println("areas");
//        startBackupTransaction();
//
//        //backup carta
//        BackUpCarta(CartaDAO.getInstance().findAll());
//
//        commitBackupTransaction();
//        System.out.println("carta");
//        startBackupTransaction();
//
//        // backup cocinas
//        backUPCocina(CocinaDAO.getInstance().findAll());
//
//        commitBackupTransaction();
//        System.out.println("cocina");
//        startBackupTransaction();
        
        // backup platos
        BackUpProd(ProductoVentaDAO.getInstance().findAll());

        commitBackupTransaction();
        System.out.println("productos");
        startBackupTransaction();

        // backup ingredientes
        BackUpInsumos(InsumoDAO.getInstance().findAll());

        commitBackupTransaction();
        System.out.println("insumo");
        startBackupTransaction();


        commitBackupTransaction();

        return true;

    }

    private boolean EjecutarBackUpVentas() {
        startBackupTransaction();
        //backup ventas
        BackUpVentas(VentaDAO.getInstance().findAll());
        commitBackupTransaction();
        return true;
    }

    private boolean EjecutarBackUpAll() {
        startBackupTransaction();
        try {
            BackUpConfiguracion(ConfiguracionDAO.getInstance().findAll());
        } catch (Exception e) {
            ExceptionHandler.showExceptionToUser(e, "Error ejecutando copia de seguridad en Configuracion");
        }

        try {
            EjecutarBackUpPersonal();
        } catch (Exception e) {
            ExceptionHandler.showExceptionToUser(e, "Error ejecutando copia de seguridad en Personal");

        }
        try {
            EjecutarBackUpProductos();
        } catch (Exception e) {
            ExceptionHandler.showExceptionToUser(e, "Error ejecutando copia de seguridad en Productos");
        }
        try {
            EjecutarBackUpVentas();
        } catch (Exception e) {
            ExceptionHandler.showExceptionToUser(e, "Error ejecutando copia de seguridad en Ventas");
        }
        commitBackupTransaction();
        return true;

    }

    private boolean EjecutarBackUp(TipoBackUp tipo) {
        barraDeProgreso.setValue(0);
        switch (tipo) {
            case PERSONAL:
                topeProceso = 50;
                EjecutarBackUpPersonal();
                break;
            case PRODUCTOS:
                topeProceso = 20;
                EjecutarBackUpProductos();
                break;
            case VENTA:
                topeProceso = 100;
                EjecutarBackUpVentas();
                break;
            case All:
                topeProceso = 10;
                EjecutarBackUpAll();

        }

        return true;
    }

    private boolean BackUpGastos(List<GastoVenta> gastoVentaList) {
        for (GastoVenta o : gastoVentaList) {
            if (EntityExist(o.getGasto().getTipoGastoidGasto(), o.getGasto().getTipoGastoidGasto().getIdGasto())) {
                em.merge(o.getGasto().getTipoGastoidGasto());
            } else {
                em.persist(o.getGasto().getTipoGastoidGasto());
            }
            if (EntityExist(o.getGasto(), o.getGasto().getCodGasto())) {
                em.merge(o.getGasto());
            } else {
                em.persist(o.getGasto());
            }

            if (EntityExist(o, o.getGastoVentaPK())) {
                em.merge(o);
            } else {
                em.persist(o);
            }
        }

        return true;
    }

    //
    // Clase Interna
    //
    public enum TipoBackUp {
        PERSONAL,
        PRODUCTOS,
        VENTA,
        All;
    }

}

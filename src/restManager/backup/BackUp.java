/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.backup;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import restManager.persistencia.Area;
import restManager.persistencia.Carta;
import restManager.persistencia.Cocina;
import restManager.persistencia.Configuracion;
import restManager.persistencia.DatosPersonales;
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
import restManager.persistencia.jpa.exceptions.IllegalOrphanException;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.staticContent;
import restManager.persistencia.models.AreaDAO;
import restManager.persistencia.models.InsumoDAO;
import restManager.persistencia.models.PuestoTrabajoDAO;
import restManager.persistencia.models.VentaDAO;
import restManager.resources.R;
import restManager.util.LoadingWindow;

/**
 * FirstDream
 *
 * @author Jorge
 *
 *
 */
public class BackUp extends SwingWorker<Boolean, Float> {

    private EntityManagerFactory emf;
    private final EntityManager em;
    private JProgressBar barraDeProgreso;
    private TipoBackUp tipoBackUp;
    private boolean borradoRemoto = false;
    private float progress = 0;

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
            barraDeProgreso.setString(chunk + "%");
        }
    }

    //
    //Metodos Privados
    //
    private boolean startBackupTransaction() {
        em.getTransaction().begin();
        incrementarProgreso(1);
        return true;
    }

    private boolean commitBackupTransaction() {
        em.getTransaction().commit();
        incrementarProgreso(1);
        return true;
    }

    //
    //BackUps de entidades
    //
    private boolean BackUpCarta(List<Carta> findCartaEntities) {
        for (Carta x : findCartaEntities) {
            if (EntityExist(x, x.getCodCarta())) {
                em.merge(x);
            } else {
                em.persist(x);
            }
        }
        return true;

    }

    private boolean backUpArea(List<Area> areas) {
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
        for (Cocina c : cocina) {
            c.setIpvList(null);
            if (EntityExist(c, c.getCodCocina())) {
                em.merge(c);
            } else {
                em.persist(c);
            }
        }
        return true;
    }

    private boolean BackUpProd(List<ProductoVenta> prods) {
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

    private boolean BackUpInsumos(List<Insumo> ins) {
        for (Insumo in : ins) {
            //in.setAlmacencodAlmacen(null);
            if (EntityExist(in, in.getCodInsumo())) {
                em.merge(in);

            } else {
                em.persist(in);
            }
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
        for (Personal x : p) {
            if (EntityExist(x, x.getUsuario())) {

                em.merge(x);
            } else {
                x.setOrdenList(null);
                em.persist(x);
            }
        }
        return true;
    }

    private boolean BackUpPuestoDeTrabajo(List<PuestoTrabajo> puestos) {
        for (PuestoTrabajo puesto : puestos) {
            if (EntityExist(puesto, puesto.getNombrePuesto())) {
                em.merge(puesto);
            } else {
                em.persist(puesto);
            }
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
        BackUpPersonal(staticContent.personalJPA.findPersonalEntities());
        BackUpDatosPersonales(staticContent.datosPJPA.findDatosPersonalesEntities());
        commitBackupTransaction();
        return true;
    }

    private boolean EjecutarBackUpProductos() {

        startBackupTransaction();
        //backup area
        backUpArea(AreaDAO.getInstance().findAll());
        //backup carta
        BackUpCarta(staticContent.cartaJPA.findCartaEntities());
        // backup cocinas
        backUPCocina(staticContent.cocinaJPA.findCocinaEntities());
        // backup secciones
        BackUPSecciones(staticContent.seccionJPA.findSeccionEntities());
        // backup ingredientes
        BackUpInsumos(InsumoDAO.getInstance().findAll());
        // backup platos
        BackUpProd(staticContent.productoJPA.findProductoVentaEntities());
        // backup mesas
        backUPMesa(staticContent.mesasJPA.findMesaEntities());
        commitBackupTransaction();

        return true;

    }

    private boolean EjecutarBackUpVentas() {
        startBackupTransaction();
        //backup ventas
        BackUpVentas(staticContent.ventaJPA.findVentaEntities());
        commitBackupTransaction();
        return true;
    }

    private boolean EjecutarBackUpAll() {
        try {
            BackUpConfiguracion(staticContent.configJPA.findConfiguracionEntities());
        } catch (Exception e) {
            System.out.println("error en config");
        }

        try {
            EjecutarBackUpPersonal();
        } catch (Exception e) {
            System.out.println("error en personal");
            System.out.println();

        }
        try {
            EjecutarBackUpProductos();
        } catch (Exception e) {
            System.out.println("error en productos");
        }
        try {
            EjecutarBackUpVentas();
        } catch (Exception e) {
            System.out.println("Error en ventas");
        }
        return true;

    }

    private boolean EjecutarBackUp(TipoBackUp tipo) {
        switch (tipo) {
            case PERSONAL:
                EjecutarBackUpPersonal();
                break;
            case PRODUCTOS:
                EjecutarBackUpProductos();
                break;
            case VENTA:
                EjecutarBackUpVentas();
                break;
            case All:
                EjecutarBackUpAll();

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

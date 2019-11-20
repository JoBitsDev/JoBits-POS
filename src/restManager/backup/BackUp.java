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
import restManager.controller.venta.VentaListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.ExceptionHandler;
import restManager.persistencia.Area;
import restManager.persistencia.AsistenciaPersonal;
import restManager.persistencia.Carta;
import restManager.persistencia.Cocina;
import restManager.persistencia.Configuracion;
import restManager.persistencia.DatosPersonales;
import restManager.persistencia.Gasto;
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
import restManager.persistencia.TipoGasto;
import restManager.persistencia.Venta;
import restManager.persistencia.models.AreaDAO;
import restManager.persistencia.models.AsistenciaPersonalDAO;
import restManager.persistencia.models.CartaDAO;
import restManager.persistencia.models.CocinaDAO;
import restManager.persistencia.models.ConfiguracionDAO;
import restManager.persistencia.models.GastoVentaDAO;
import restManager.persistencia.models.InsumoDAO;
import restManager.persistencia.models.MesaDAO;
import restManager.persistencia.models.PersonalDAO;
import restManager.persistencia.models.ProductoVentaDAO;
import restManager.persistencia.models.PuestoTrabajoDAO;
import restManager.persistencia.models.SeccionDAO;
import restManager.persistencia.models.VentaDAO;
import restManager.resources.R;
import restManager.util.LoadingWindow;
import restManager.util.utils;

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

    public BackUp() {
        this.tipoBackUp = TipoBackUp.NULO;
        emf = Persistence.createEntityManagerFactory(R.RESOURCE_BUNDLE.getString("unidad_persistencia_local"));
        em = emf.createEntityManager();
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

    public boolean ExisteVentaEnLocal(Venta v) {
        if (tipoBackUp == TipoBackUp.NULO) {
            Venta venta = em.find(Venta.class, v.getFecha());
            return venta != null;
        } else {
            return false;
        }
    }

    //
    // Metodos Heredados SwingWorker
    //
    @Override
    protected Boolean doInBackground() throws Exception {

        EjecutarBackUp(tipoBackUp);
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
            barraDeProgreso.setString(utils.setDosLugaresDecimalesFloat(chunk) + "%");
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
            Carta c = em.find(Carta.class, x.getCodCarta());
            if (c != null) {
//TODO: mezclar secciones;
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
            Area area = em.find(Area.class, a.getCodArea());
            if (area != null) {
                a.setCartaList(area.getCartaList());
                a.setMesaList(area.getMesaList());
                a.setPuestoTrabajoList(area.getPuestoTrabajoList());
                em.merge(a);
            } else {
                a.setCartaList(null);
                a.setMesaList(null);
                a.setPuestoTrabajoList(null);
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
            Cocina coc = em.find(Cocina.class, c.getCodCocina());
            if (coc != null) {
                c.setImpresoraList(coc.getImpresoraList());
                c.setNotificacionEnvioCocinaList(coc.getNotificacionEnvioCocinaList());
                c.setProductoVentaList(coc.getProductoVentaList());
                c.setIpvList(coc.getIpvList());
                em.merge(c);
            } else {
                c.setImpresoraList(null);
                c.setNotificacionEnvioCocinaList(null);
                c.setProductoVentaList(null);
                c.setIpvList(null);
                em.persist(c);
            }
            incrementarProgreso(sumaXCantidad);
        }
        return true;
    }

    private boolean BackUpProd(List<ProductoVenta> prods) {
        float sumaXCantidad = topeProceso / prods.size();
        for (ProductoVenta pv : prods) {
            ProductoVenta p = em.find(ProductoVenta.class, pv.getPCod());
//            BackUpProdInsumo(pv.getProductoInsumoList());
            if (p != null) {
                pv.setProductovOrdenList(p.getProductovOrdenList());
                em.merge(pv);
            } else {
                pv.setProductovOrdenList(null);
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
            if (v.getVentaTotal() != null) {
                if (EntityExist(v, v.getFecha())) {
                } else {
                    em.persist(v);
                    BackUpOrdenes(v.getOrdenList());
                    BackUpGastos(v.getGastoVentaList());
                    backUpAsistenciaPersonal(v.getAsistenciaPersonalList());
                }
            }
            incrementarProgreso(sumaXCantidad);
        }

        return true;

    }

    private boolean BackUpInsumos(List<Insumo> ins) {
        float sumaXCantidad = topeProceso / ins.size();
        for (Insumo in : ins) {
            Insumo i = em.find(Insumo.class, in.getCodInsumo());
            if (i != null) {
                in.setInsumoAlmacenList(i.getInsumoAlmacenList());
                in.setInsumoDerivadoList(i.getInsumoDerivadoList());
                in.setIpvList(i.getIpvList());
                in.setTransaccionList(i.getTransaccionList());
                em.merge(in);

            } else {
                in.setInsumoAlmacenList(null);
                in.setInsumoDerivadoList(null);
                in.setIpvList(null);
                in.setTransaccionList(null);
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

    private boolean BackUpPersonal(List<Personal> per) {
        float sumaXCantidad = topeProceso / per.size();
        for (Personal x : per) {
            Personal p = em.find(x.getClass(), x.getUsuario());
            if (p != null) {
                x.setAsistenciaPersonalList(p.getAsistenciaPersonalList());
                x.setOrdenList(p.getOrdenList());
                em.merge(x);
            } else {
                x.setOrdenList(null);
                x.setAsistenciaPersonalList(null);
                em.persist(x);
            }
            incrementarProgreso(sumaXCantidad);
        }
        return true;
    }

    private boolean BackUpPuestoDeTrabajo(List<PuestoTrabajo> puestos) {
        float sumaXCantidad = topeProceso / puestos.size();
        for (PuestoTrabajo puesto : puestos) {
            PuestoTrabajo p = em.find(puesto.getClass(), puesto.getNombrePuesto());
            if (p != null) {
                puesto.setPersonalList(p.getPersonalList());
                em.merge(puesto);
            } else {
                puesto.setPersonalList(null);
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
    private boolean EjecutarLimpiezaVentas(List<Venta> ventas) {
        float sumaXCantidad = topeProceso / ventas.size();
        VentaListController controller = new VentaListController();
        controller.setShowDialogs(false);
        for (Venta x : ventas) {
            if (x.getVentaTotal() != null) {
                controller.destroy(x);
                incrementarProgreso(sumaXCantidad);
            }
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

        //backup carta
        BackUpCarta(CartaDAO.getInstance().findAll());

        // backup cocinas
        backUPCocina(CocinaDAO.getInstance().findAll());

        // backup platos
        BackUpProd(ProductoVentaDAO.getInstance().findAll());

        // backup ingredientes
        BackUpInsumos(InsumoDAO.getInstance().findAll());

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
                break;
            case LIMPIEZA:
                topeProceso = 100;
                EjecutarLimpiezaVentas(VentaDAO.getInstance().findAll());

        }

        return true;
    }

    private boolean BackUpGastos(List<GastoVenta> gastoVentaList) {
        for (GastoVenta o : gastoVentaList) {
            TipoGasto tipo = em.find(o.getGasto().getTipoGastoidGasto().getClass(), o.getGasto().getTipoGastoidGasto().getIdGasto());
            if (tipo != null) {
                o.getGasto().getTipoGastoidGasto().setGastoList(tipo.getGastoList());
                em.merge(o.getGasto().getTipoGastoidGasto());
            } else {
                o.getGasto().getTipoGastoidGasto().setGastoList(null);
                em.persist(o.getGasto().getTipoGastoidGasto());
            }

            Gasto g = em.find(Gasto.class, o.getGasto().getCodGasto());
            if (g != null) {
                o.getGasto().setGastoVentaList(g.getGastoVentaList());
                em.merge(o.getGasto());
            } else {
                o.getGasto().setGastoVentaList(null);
                em.persist(o.getGasto());
            }

            GastoVenta gv = em.find(GastoVenta.class, o.getGastoVentaPK());
            if (gv != null) {
                em.merge(o);
            } else {
                em.persist(o);
            }
        }

        return true;
    }

    private void backUpAsistenciaPersonal(List<AsistenciaPersonal> asistenciaPersonalList) {
        for (AsistenciaPersonal a : asistenciaPersonalList) {
            em.persist(a);
        }
    }

//
// Clase Interna
//
    public enum TipoBackUp {
        NULO,
        PERSONAL,
        PRODUCTOS,
        VENTA,
        All,
        LIMPIEZA;
    }

}

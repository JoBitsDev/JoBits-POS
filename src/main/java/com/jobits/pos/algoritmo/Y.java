/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.algoritmo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import javax.swing.SwingWorker;
import com.jobits.pos.controller.Controller;
import com.jobits.pos.controller.configuracion.ConfiguracionController;
import com.jobits.pos.controller.venta.VentaListController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.domain.models.Configuracion;
import com.jobits.pos.domain.VentaDAO1;
import com.jobits.pos.domain.models.Orden;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.ProductovOrden;
import com.jobits.pos.domain.models.Seccion;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.adapters.repo.impl.ConfiguracionDAO;
import com.jobits.pos.adapters.repo.impl.SeccionDAO;
import com.jobits.pos.adapters.repo.impl.VentaDAO;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.utils.LoadingWindow;
import com.jobits.pos.utils.utils;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class Y extends SwingWorker<List<Orden>, Integer> {

    private ProductoVentaFilter filter;

    private Venta ventaReal;

    private float monto_A_Ajustar;

    private List<Orden> ordCompletadas;

    private byte porciento_a_ajustar,
            porciento_cantidad_ordenes;

    private VentaListController controller;

    //
    //buscar otra forma
    //
    private float montoRecaudado;
    private int puntero,
            limiteOrdenes = -1;

    public Y(Venta ventaReal) {
        this.ventaReal = ventaReal;
        ConfiguracionController conf = new ConfiguracionController();
        ParametrosConfiguracion params = conf.cargarConfiguracionY();
        filter = new ProductoVentaFilter(params.bebidas, params.excluidos);
        this.porciento_a_ajustar = params.getM();
        this.porciento_cantidad_ordenes = params.getC();
    }

    public Y(Venta valueAt, VentaListController controller) {
        this(valueAt);
        this.controller = controller;
    }

    public Y(Venta ventaReal, byte porciento_a_ajustar, byte porciento_cantidad_ordenes, VentaListController controller) {
        this(ventaReal, controller);
        this.porciento_a_ajustar = porciento_a_ajustar;
        this.porciento_cantidad_ordenes = porciento_cantidad_ordenes;
    }

    public Venta getVentaReal() {
        return ventaReal;
    }

    public void setVentaReal(Venta ventaReal) {
        this.ventaReal = ventaReal;
    }

    public float getMonto_A_Ajustar() {
        return monto_A_Ajustar;
    }

    public void setMonto_A_Ajustar(float monto_A_Ajustar) {
        this.monto_A_Ajustar = monto_A_Ajustar;
    }

    public byte getPorciento_a_ajustar() {
        return porciento_a_ajustar;
    }

    public void setPorciento_a_ajustar(byte porciento_a_ajustar) {
        this.porciento_a_ajustar = porciento_a_ajustar;
    }

    public byte getPorciento_cantidad_ordenes() {
        return porciento_cantidad_ordenes;
    }

    public void setPorciento_cantidad_ordenes(byte porciento_cantidad_ordenes) {
        this.porciento_cantidad_ordenes = porciento_cantidad_ordenes;
    }

    public ProductoVentaFilter getFilter() {
        return filter;
    }

    public void setFilter(ProductoVentaFilter filter) {
        this.filter = filter;
    }

    @Override
    protected List<Orden> doInBackground() throws Exception {
        return ejecutarAlgoritmo();

    }

    @Override
    protected void done() {
        LoadingWindow.hide();
        super.done(); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    protected void process(List<Integer> chunks) {
        for (Integer x : chunks) {
            System.out.println(x);
        }
        super.process(chunks); //To change body of generated methods, choose Tools | Templates.
    }

    private void metodoA(float valorMaxOrden, List<Orden> lista) {
        float valorAgregado = agregarOrden(lista.get(puntero % lista.size()), valorMaxOrden);
        montoRecaudado += valorAgregado;
        // int porcientoCompletado = ((int) ((montoRecaudado / monto_A_Ajustar) * 100));
        if (valorAgregado != 0) {
            limiteOrdenes--;
            // publish(porcientoCompletado);
        }
        if (limiteOrdenes == 0) {
            limiteOrdenes++;
        }
    }

    private void ordenarOrdenes() {
        Collections.sort(ventaReal.getOrdenList(), (o1, o2) -> {
            return numOrden(o1.getCodOrden()) > numOrden(o2.getCodOrden()) ? 1 : -1; //To change body of generated lambdas, choose Tools | Templates.
        });
    }

    private int numOrden(String noOrden) {
        return Integer.parseInt(noOrden.substring(2));
    }

    private float agregarOrden(Orden get, float valorMaxOrden) {
        if (ordCompletadas.contains(get)) {
            return 0;
        }
        limpiarMaquillarOrden(get, valorMaxOrden);

        if (get.getOrdenvalorMonetario() == 0) {
            return 0;
        } else {
            ordCompletadas.add(get);
            if (get.getDeLaCasa()) {
                return 0;
            }
            return get.getOrdenvalorMonetario();
        }

    }

    private void limpiarMaquillarOrden(Orden get, float valorMaximo) {
        if (get.getOrdenvalorMonetario() < valorMaximo || get.getOrdenvalorMonetario() == 0) {
            return;
        }

        List<ProductovOrden> liquidos = filter.getLiquidos(get);
        List<ProductovOrden> comidas = filter.getComida(get);
        Collections.sort(comidas, (ProductovOrden o1, ProductovOrden o2) -> {
            return Float.compare(o1.getCantidad() * o1.getPrecioVendido(),
                    o2.getCantidad() * o2.getPrecioVendido());
        });
        Collections.sort(liquidos, (ProductovOrden o1, ProductovOrden o2) -> {
            return Float.compare(o1.getCantidad() * o1.getPrecioVendido(),
                    o2.getCantidad() * o2.getPrecioVendido());
        });

        float valorMaxAjustado = (float) (valorMaximo * (1.1));
        int i = 0,
                iteracionesMax = comidas.size() * liquidos.size();
        while (get.getOrdenvalorMonetario() > valorMaxAjustado && i < iteracionesMax) {
            if (comidas.size() >= liquidos.size()) {
                iterarEnComidas(liquidos, comidas, valorMaxAjustado, get);
            } else {
                iterarEnLiquidos(liquidos, comidas, valorMaxAjustado, get);
            }
        }
        get.setProductovOrdenList(comidas);
        get.getProductovOrdenList().addAll(liquidos);

    }

    private void iterarEnComidas(List<ProductovOrden> liquidos, List<ProductovOrden> comidas, float valorMaxAjustado, Orden get) {
        for (int i = 0; i < comidas.size() && get.getOrdenvalorMonetario() > valorMaxAjustado && comidas.size() >= liquidos.size();) {
            if (comidas.get(i).getCantidad() > 1) {
                comidas.get(i).setCantidad(comidas.get(i).getCantidad() - 1);
                i++;
            } else {
                comidas.remove(i);
            }
            actualizarValorMonetarioOrden(liquidos, comidas, get);
        }
    }

    private void iterarEnLiquidos(List<ProductovOrden> liquidos, List<ProductovOrden> comidas, float valorMaxAjustado, Orden get) {
        for (int i = 0; i < liquidos.size() && get.getOrdenvalorMonetario() > valorMaxAjustado && liquidos.size() >= comidas.size();) {
            if (liquidos.get(i).getCantidad() > 1) {
                liquidos.get(i).setCantidad(liquidos.get(i).getCantidad() - 1);
                i++;
            } else {
                liquidos.remove(i);
            }
            actualizarValorMonetarioOrden(liquidos, comidas, get);
        }
    }

    private void actualizarValorMonetarioOrden(List<ProductovOrden> liquidos, List<ProductovOrden> comidas, Orden get) {
        float totalVenta = 0;
        float totalGasto = 0;
        for (ProductovOrden x : liquidos) {
            totalVenta += x.getCantidad() * x.getPrecioVendido();
            totalGasto += x.getCantidad() * x.getProductoVenta().getGasto();
        }
        for (ProductovOrden x : comidas) {
            totalVenta += x.getCantidad() * x.getPrecioVendido();
            totalGasto += x.getCantidad() * x.getProductoVenta().getGasto();
        }

        get.setOrdenvalorMonetario(utils.redondeoPorExcesoFloat(totalVenta * (1 + (get.getPorciento() / 100))));
        get.setOrdengastoEninsumos(utils.redondeoPorExcesoFloat(totalGasto * (1 + (get.getPorciento() / 100))));
    }

    private String actualizarUltimoCodOrden() {
        ConfiguracionDAO conf = ConfiguracionDAO.getInstance();
        Configuracion c = conf.find(R.SettingID.GENERAL_ULTIMA_ORDEN_PRUEBA);
        ConfiguracionDAO.getCurrentConnection().refresh(c);
        int orden = c.getValor();
        c.setValor(orden + 1);
        conf.startTransaction();
        conf.edit(c);
        conf.commitTransaction();
        return "O-" + orden;
    }

    public List<Orden> ejecutarAlgoritmo() {

//ordenamos arreglo
        ordenarOrdenes();
        ordCompletadas = new ArrayList<>();

        //separar ventas en dos arreglo almuerzo y comida
        int cantidadOrdenes = ventaReal.getOrdenList().size() - 1;
        int punteroAlmuerzo = 0;
        int punteroComida = cantidadOrdenes / 2;

        List<Orden> listaHorarioAlmuerzo = ventaReal.getOrdenList().subList(punteroComida, cantidadOrdenes);
        List<Orden> listaHorarioComida = ventaReal.getOrdenList().subList(punteroAlmuerzo, punteroComida - 1);

        //inicializamos las variables
        Configuracion c = ConfiguracionDAO.getInstance().find(R.SettingID.GENERAL_ULTIMA_ORDEN_PRUEBA);
        int ultimoCodOrden = c.getValor();
        if (ultimoCodOrden == -1) {
            c.setValor(Integer.valueOf(ventaReal.getOrdenList().get(0).getCodOrden().substring(2)));
            ConfiguracionDAO.getInstance().startTransaction();
            ConfiguracionDAO.getInstance().edit(c);
            ConfiguracionDAO.getInstance().commitTransaction();
        }

        if (limiteOrdenes == -1) {
            limiteOrdenes = cantidadOrdenes * porciento_cantidad_ordenes / 100;
        }

        puntero = 0;
        monto_A_Ajustar = (float) (ventaReal.getVentaTotal() * porciento_a_ajustar / 100);

        int i = 0;

        //ejecutamos algoritmo
        while (monto_A_Ajustar > montoRecaudado && puntero < cantidadOrdenes) {
            float valorMaxOrden = (monto_A_Ajustar - montoRecaudado) / limiteOrdenes;
            if (puntero % 2 == 0) {
                metodoA(valorMaxOrden, listaHorarioAlmuerzo);
            } else {
                metodoA(valorMaxOrden, listaHorarioComida);
            }
            puntero++;
        }

        Collections.sort(ordCompletadas, (o1, o2) -> {
            return numOrden(o1.getCodOrden()) > numOrden(o2.getCodOrden()) ? 1 : -1; //To change body of generated lambdas, choose Tools | Templates.
        });

        for (Orden ord : ordCompletadas) {
            ord.setCodOrden(actualizarUltimoCodOrden());
            for (ProductovOrden pv : ord.getProductovOrdenList()) {
                pv.setOrden(ord);
            }
        }

        return ordCompletadas;
    }

    public class ProductoVentaFilter {

        private List<Seccion> seccionLiquidos;
        private List<Seccion> excluidos;
        private boolean modoPrueba = true;

        public ProductoVentaFilter() {
            if (cargarArchivos()) {
                modoPrueba = false;
            }
        }

        public ProductoVentaFilter(List<Seccion> seccionLiquidos, List<Seccion> excluidos) {
            this.seccionLiquidos = seccionLiquidos;
            this.excluidos = excluidos;
            modoPrueba = false;
        }

        public boolean isLiquido(ProductoVenta p) {
            if (modoPrueba) {
                return false;
            }
            for (Seccion s : seccionLiquidos) {
                if (p.getSeccionnombreSeccion().equals(s)) {
                    return true;
                }
            }
            return false;
        }

        public boolean isComida(ProductoVenta p) {
            return !isLiquido(p);
        }

        public boolean isExcluido(ProductoVenta p) {
            for (Seccion s : excluidos) {
                if (p.getSeccionnombreSeccion().equals(s)) {
                    return true;
                }
            }
            return false;
        }

        public List<ProductovOrden> getLiquidos(Orden o) {
            List<ProductovOrden> ret = new ArrayList<>();
            for (ProductovOrden pv : o.getProductovOrdenList()) {
                if (isLiquido(pv.getProductoVenta()) && !isExcluido(pv.getProductoVenta())) {
                    ret.add(pv);
                }
            }
            return ret;
        }

        public List<ProductovOrden> getComida(Orden o) {
            List<ProductovOrden> ret = new ArrayList<>();
            for (ProductovOrden pv : o.getProductovOrdenList()) {
                if (isComida(pv.getProductoVenta()) && !isExcluido(pv.getProductoVenta())) {
                    ret.add(pv);
                }
            }
            return ret;
        }

        private boolean cargarArchivos() {
            throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
        }

    }

}

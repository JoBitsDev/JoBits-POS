/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.algoritmo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import javax.swing.SwingWorker;
import restManager.controller.Controller;
import restManager.controller.venta.VentaListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Configuracion;
import restManager.persistencia.Control.VentaDAO1;
import restManager.persistencia.Orden;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.ProductovOrden;
import restManager.persistencia.Seccion;
import restManager.persistencia.Venta;
import restManager.persistencia.models.ConfiguracionDAO;
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
 */
public class Y extends SwingWorker<List<Orden>, Integer> {

    private ProductoVentaFilter filter;

    private float VALOR_MAXIMO_MONETARIO;

    private float VALOR_MIN_MONETARIO;

    private Venta ventaReal;

    private float monto_A_Ajustar;

    private List<Orden> ordCompletadas;

    private final byte porciento_a_ajustar = 30;

    private VentaListController controller;

    //
    //buscar otra forma
    //
    private float montoRecaudado;
    private int puntero, limite;

    public Y(Venta ventaReal) {
        this.ventaReal = ventaReal;
    }

    public Y(Venta valueAt, VentaListController controller) {
        this.ventaReal = valueAt;
        this.controller = controller;
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
            limite--;
            // publish(porcientoCompletado);
        }
        if (limite == 0) {
            limite++;
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

        //
        // quitar luego
        //
        List<Seccion> secciones = SeccionDAO.getInstance().findAll();
        List<Seccion> bebidas = new ArrayList<>();
        for (Seccion s : secciones) {

            switch (s.getNombreSeccion()) {

                case "Bebida":
                case "Cocteleria":
                    bebidas.add(s);
                    break;
                default:
                    break;
            }
        }
        filter = new ProductoVentaFilter(bebidas);

        //
        // hasta aqui
        //
        List<ProductovOrden> liquidos = filter.getLiquidos(get);
        List<ProductovOrden> comidas = filter.getComida(get);
        Collections.sort(comidas, (ProductovOrden o1, ProductovOrden o2) -> {
            return Float.compare(o1.getCantidad() * o1.getProductoVenta().getPrecioVenta(),
                    o2.getCantidad() * o2.getProductoVenta().getPrecioVenta());
        });
        Collections.sort(liquidos, (ProductovOrden o1, ProductovOrden o2) -> {
            return Float.compare(o1.getCantidad() * o1.getProductoVenta().getPrecioVenta(),
                    o2.getCantidad() * o2.getProductoVenta().getPrecioVenta());
        });

        float valorMaxAjustado = (float) (valorMaximo * (1.1));
        while (get.getOrdenvalorMonetario() > valorMaxAjustado) {
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
        float total = 0;
        for (ProductovOrden x : liquidos) {
            total += x.getCantidad() * x.getProductoVenta().getPrecioVenta();
        }
        for (ProductovOrden x : comidas) {
            total += x.getCantidad() * x.getProductoVenta().getPrecioVenta();
        }

        get.setOrdenvalorMonetario(utils.redondeoPorExcesoFloat(total * (1 + (get.getPorciento() / 100))));
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

        limite = cantidadOrdenes / 2;
        puntero = 0;
        monto_A_Ajustar = (float) (ventaReal.getVentaTotal() * porciento_a_ajustar / 100);

        int i = 0;
        while (monto_A_Ajustar > montoRecaudado && puntero < cantidadOrdenes) {
            float valorMaxOrden = (monto_A_Ajustar - montoRecaudado) / limite;
            metodoA(valorMaxOrden, listaHorarioAlmuerzo);
            valorMaxOrden = (monto_A_Ajustar - montoRecaudado) / limite;
            metodoA(valorMaxOrden, listaHorarioComida);
            puntero++;
        }

        Collections.sort(ordCompletadas, (o1, o2) -> {
            return numOrden(o1.getCodOrden()) > numOrden(o2.getCodOrden()) ? 1 : -1; //To change body of generated lambdas, choose Tools | Templates.
        });

        for (Orden ord : ordCompletadas) {
            ord.setCodOrden(actualizarUltimoCodOrden());
            for (ProductovOrden pv : ord.getProductovOrdenList()) {
                pv.getProductovOrdenPK().setOrdencodOrden(ord.getCodOrden());
                if (pv.getNota() != null) {
                    pv.getNota().getNotaPK().setProductovOrdenordencodOrden(ord.getCodOrden());
                }
            }
        }

        return ordCompletadas;
    }

    public class ProductoVentaFilter {

        private List<Seccion> seccionLiquidos;
        private boolean modoPrueba = true;

        public ProductoVentaFilter() {
            if (cargarArchivos()) {
                modoPrueba = false;
            }
        }

        public ProductoVentaFilter(List<Seccion> seccionLiquidos) {
            this.seccionLiquidos = seccionLiquidos;
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

        public List<ProductovOrden> getLiquidos(Orden o) {
            List<ProductovOrden> ret = new ArrayList<>();
            for (ProductovOrden pv : o.getProductovOrdenList()) {
                if (isLiquido(pv.getProductoVenta())) {
                    ret.add(pv);
                }
            }
            return ret;
        }

        public List<ProductovOrden> getComida(Orden o) {
            List<ProductovOrden> ret = new ArrayList<>();
            for (ProductovOrden pv : o.getProductovOrdenList()) {
                if (isComida(pv.getProductoVenta())) {
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.algoritmo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.SwingWorker;
import restManager.persistencia.Orden;
import restManager.persistencia.ProductovOrden;
import restManager.persistencia.Venta;
import restManager.util.LoadingWindow;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class VentaB extends SwingWorker<List<Orden>, Integer> {

    private final String SECCION_COCTELERIA = "Cocteleria";

    private final String SECCION_TRAGOS = "Tragos";

    private float VALOR_MAXIMO_MONETARIO = 15;

    private float VALOR_MIN_MONETARIO = 3;

    private Venta ventaReal;

    private float monto_A_Ajustar;

    private List<Orden> ordEncontradas;

    private final byte porciento_a_ajustar = 20;

    public VentaB(Venta ventaReal) {
        this.ventaReal = ventaReal;
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
        ordenarOrdenes();
        ordEncontradas = new ArrayList<>();

        float montoRecaudado = 0;
        int noOrdenDelDia = 1001;

        int cantidadOrdenes = ventaReal.getOrdenList().size() - 1;
        int punteroAlmuerzo = 0;
        int punteroComida = cantidadOrdenes / 2;

        List<Orden> horarioAlmuerzo = ventaReal.getOrdenList().subList(punteroComida, cantidadOrdenes);
        List<Orden> horarioComida = ventaReal.getOrdenList().subList(punteroAlmuerzo, punteroComida - 1);

        int pasoAlmuerzo = horarioAlmuerzo.size() % 2 == 0 ? 2 : 1;
        int pasoComida = horarioComida.size() % 2 == 0 ? 2 : 1;

        int limite = cantidadOrdenes;

        //monto_A_Ajustar =(float) (calcularMontoTotal()*porciento_a_ajustar/100);
        monto_A_Ajustar = 40;
        while (monto_A_Ajustar > montoRecaudado) {

            montoRecaudado += agregarOrden(horarioAlmuerzo.get(punteroAlmuerzo % horarioAlmuerzo.size()), punteroAlmuerzo);
            int porcientoCompletado = ((int) ((montoRecaudado / monto_A_Ajustar) * 100));
            publish(porcientoCompletado);

            if (porcientoCompletado > 100) {
                break;
            }

            montoRecaudado += agregarOrden(horarioComida.get(punteroComida % horarioComida.size()), punteroComida);
            porcientoCompletado = ((int) ((montoRecaudado / monto_A_Ajustar) * 100));
            publish(porcientoCompletado);

            if (porcientoCompletado > 100) {
                break;
            }

            punteroAlmuerzo += pasoAlmuerzo;
            punteroComida += pasoComida;
            limite--;
            if (limite == 0) {
                if (VALOR_MAXIMO_MONETARIO < 100) {
                    VALOR_MAXIMO_MONETARIO += 5;

                } else if (VALOR_MIN_MONETARIO > 0) {
                    VALOR_MIN_MONETARIO -= 1;

                }

                limite = cantidadOrdenes;
            }

        }

        Collections.sort(ordEncontradas, (o1, o2) -> {
            return numOrden(o1.getCodOrden()) > numOrden(o2.getCodOrden()) ? 1 : -1; //To change body of generated lambdas, choose Tools | Templates.
        });

        for (Orden x : ordEncontradas) {
            x.setCodOrden(crearNumOrden(noOrdenDelDia));
            noOrdenDelDia++;
        }

        
        return ordEncontradas;
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

    private void ordenarOrdenes() {
        Collections.sort(ventaReal.getOrdenList(), (o1, o2) -> {
            return numOrden(o1.getCodOrden()) > numOrden(o2.getCodOrden()) ? 1 : -1; //To change body of generated lambdas, choose Tools | Templates.
        });
    }

    private int numOrden(String noOrden) {
        return Integer.parseInt(noOrden.substring(3));
    }

    private String crearNumOrden(int noOrden) {
        return "O-" + noOrden;
    }

    private float agregarOrden(Orden get, int puntero) {
        puntero++;
        if (ordEncontradas.contains(get)) {
            return 0;
        }
        limpiarMaquillarOrden(get);

        if (get.getOrdenvalorMonetario() > VALOR_MAXIMO_MONETARIO) {
            return 0;
        }

        if (get.getOrdenvalorMonetario() < VALOR_MIN_MONETARIO) {
            return 0;
        }

        if (tieneLiquidosProhibidos(get) || get.getOrdenvalorMonetario() == 0) {
            return 0;
        } else {
            ordEncontradas.add(get);

            return get.getOrdenvalorMonetario();

        }

    }

    private boolean tieneLiquidosProhibidos(Orden get) {
        for (ProductovOrden x : get.getProductovOrdenList()) {
            if (x.getProductoVenta().getSeccionnombreSeccion().getNombreSeccion().equals(SECCION_TRAGOS)
                    || x.getProductoVenta().getSeccionnombreSeccion().getNombreSeccion().equals(SECCION_COCTELERIA)) {
                return true;
            }
            if (x.getProductoVenta().getNombre().contains("Pulpo")) {
                return true;
            }
            if (x.getProductoVenta().getNombre().contains("calamar")) {
                return true;
            }
        }
        return false;
    }

    private boolean limpiarMaquillarOrden(Orden get) {
        float total = 0;
        for (int i = 0; i < get.getProductovOrdenList().size();) {
            boolean remove = false;
            ProductovOrden x = get.getProductovOrdenList().get(i);

            if (x.getProductoVenta().getSeccionnombreSeccion().getNombreSeccion().equals(SECCION_TRAGOS)
                    || x.getProductoVenta().getSeccionnombreSeccion().getNombreSeccion().equals(SECCION_COCTELERIA)) {
                remove = true;
            }
            if (x.getProductoVenta().getNombre().contains("Pulpo")) {
                remove = true;
            }
            if (x.getProductoVenta().getNombre().contains("calamar")) {
                remove = true;
            }

            if (remove) {
                get.getProductovOrdenList().remove(i);

            } else {
                i++;
                x.setCantidad(1);
                total += x.getCantidad() * x.getProductoVenta().getPrecioVenta();
            }

        }
        get.setOrdenvalorMonetario(total * Float.parseFloat("1.1"));

        return true;
    }

    private float calcularMontoTotal() {
        if (ventaReal.getVentaTotal() != null) {
            return ventaReal.getVentaTotal().floatValue();
        }
        float total = 0;
        for (Orden x : ventaReal.getOrdenList()) {
            total += x.getOrdenvalorMonetario();
        }
        return total;
    }

}

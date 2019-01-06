package restManager.persistencia.Control;

import GUI.Views.login.Main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import restManager.exceptions.DevelopingOperationException;

import restManager.persistencia.Cocina;
import restManager.persistencia.Venta;
import restManager.persistencia.Orden;
import restManager.persistencia.Personal;
import restManager.persistencia.ProductoInsumo;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.ProductovOrden;
import restManager.resources.R;
import restManager.util.comun;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class VentaDAO1 {

    //
    //Métodos referentes a resumenes de las ventas
    //
    public static ArrayList<ProductovOrden> getResumenVentas(Venta v) {
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            if (!o.getDeLaCasa()) {
                joinListsProductovOrden(ret,
                        new ArrayList(o.getProductovOrdenList()));
            }
        }//nˆ3

        Collections.sort(ret, (o1, o2) -> {
            return o1.getProductoVenta().getNombre().compareTo(o2.getProductoVenta().getNombre());
        });

        return ret;
    }

    public static void getResumenVentasOnTable(JTable tabla, Venta v) {

        //inicializando los datos
        ArrayList[] rowData = comun.initArray(new ArrayList[5]);
        ArrayList<ProductovOrden> ret = getResumenVentas(v);

        //convirtiendo a rowData
        float total = 0;
        for (ProductovOrden x : ret) {
            ProductoVenta pv = x.getProductoVenta();
            rowData[0].add(pv.getPCod());
            rowData[1].add(pv.getNombre());
            rowData[2].add(pv.getPrecioVenta());
            rowData[3].add(x.getCantidad());
            rowData[4].add(x.getProductoVenta().getPrecioVenta() * x.getCantidad());
            total += pv.getPrecioVenta() * x.getCantidad();
        }

        //llenando la tabla
        try {
            comun.AddToTable(rowData, tabla);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void getResumenVentasDeLaCasaOnTable(JTable tabla, Venta v) {

        //inicializando los datos
        ArrayList[] rowData = comun.initArray(new ArrayList[5]);
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            if (o.getDeLaCasa()) {
                joinListsProductovOrden(ret,
                        new ArrayList(o.getProductovOrdenList()));
            }
        }//nˆ3

        //convirtiendo a rowData
        float total = 0;
        for (ProductovOrden x : ret) {
            ProductoVenta pv = x.getProductoVenta();
            rowData[0].add(pv.getPCod());
            rowData[1].add(pv.getNombre());
            rowData[2].add(pv.getPrecioVenta());
            rowData[3].add(x.getCantidad());
            rowData[4].add(x.getProductoVenta().getPrecioVenta() * x.getCantidad());
            total += pv.getPrecioVenta() * x.getCantidad();
        }

        //llenando la tabla
        try {
            comun.AddToTable(rowData, tabla);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void getResumenVentasDeLaCasaXCocinaOnTable(JTable tabla, Venta v, Cocina c) {
        //inicializando los datos
        ArrayList[] rowData = comun.initArray(new ArrayList[5]);
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            if (o.getDeLaCasa()) {
                joinListsProductovOrdenByCocina(ret,
                        new ArrayList(o.getProductovOrdenList()), c);
            }

        }//nˆ3

        //convirtiendo a rowData
        float total = 0;
        for (ProductovOrden x : ret) {
            ProductoVenta pv = x.getProductoVenta();
            rowData[0].add(pv.getPCod());
            rowData[1].add(pv.getNombre());
            rowData[2].add(pv.getPrecioVenta());
            rowData[3].add(x.getCantidad());
            rowData[4].add(x.getProductoVenta().getPrecioVenta() * x.getCantidad());
            total += pv.getPrecioVenta() * x.getCantidad();
        }

        //llenando la tabla
        try {
            comun.AddToTable(rowData, tabla);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void getResumenVentasCamareroOnTable(JTable tabla, Venta v, Personal p) {

        //inicializando los datos
        ArrayList[] rowData = comun.initArray(new ArrayList[2]);
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        float total = 0;
        //llenando l array
        for (Orden o : aux) {
            if (!o.getDeLaCasa() && o.getPersonalusuario().equals(p) && o.getHoraTerminada() != null) {
                total += o.getOrdenvalorMonetario();
            }
        }//n

        //convirtiendo a rowData
        if (total != 0) {
            rowData[0].add(p.getUsuario());
            rowData[1].add(comun.setDosLugaresDecimales(total));

            //llenando la tabla
            try {
                comun.AddToTable(rowData, tabla);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * crea un resumen del total que ha vendido cda cocina
     *
     * @param tabla
     * @param v
     * @param c
     */
    public static void getResumenVentasCocinaOnTable(JTable tabla, Venta v, Cocina c) {
        //inicializando los datos
        ArrayList[] rowData = comun.initArray(new ArrayList[3]);
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            joinListsProductovOrdenByCocina(ret,
                    new ArrayList(o.getProductovOrdenList()), c);

        }//nˆ3

        //convirtiendo a rowData
        float total = 0;
        for (ProductovOrden x : ret) {
            ProductoVenta pv = x.getProductoVenta();
            total += pv.getPrecioVenta() * x.getCantidad();
        }
        if (total != 0) {
            rowData[0].add(c.getCodCocina());
            rowData[1].add(c.getNombreCocina());
            rowData[2].add(comun.setDosLugaresDecimales(total));

            //llenando la tabla
            try {
                comun.AddToTable(rowData, tabla);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public static void getResumenDetalladoVentasCocinaOnTable(JTable tabla, Venta v, Cocina c) {
        //inicializando los datos
        ArrayList[] rowData = comun.initArray(new ArrayList[5]);
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            joinListsProductovOrdenByCocina(ret,
                    new ArrayList(o.getProductovOrdenList()), c);

        }//nˆ3

        //convirtiendo a rowData
        float total = 0;
        for (ProductovOrden x : ret) {
            rowData[0].add(x.getProductoVenta().getPCod());
            rowData[1].add(x.getProductoVenta().getNombre());
            rowData[2].add(x.getProductoVenta().getPrecioVenta());
            rowData[3].add(x.getCantidad());
            rowData[4].add(x.getProductoVenta().getPrecioVenta() * x.getCantidad());

            total += x.getProductoVenta().getPrecioVenta() * x.getCantidad();
        }

        //llenando la tabla
        try {
            comun.AddToTable(rowData, tabla);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static List<ProductovOrden> getResumenVentasCamarero(Venta v, Personal p) {

        //inicializando los datos
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            if (!o.getDeLaCasa() && o.getPersonalusuario().equals(p)) {
                joinListsProductovOrden(ret,
                        new ArrayList(o.getProductovOrdenList()));
            }
        }//nˆ3

        return ret;
    }

    public static List<ProductovOrden> getResumenVentasCocina(Venta v, Cocina c) {
        //inicializando los datos
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            joinListsProductovOrdenByCocina(ret,
                    new ArrayList<>(o.getProductovOrdenList()), c);

        }//nˆ3

        return ret;
    }

    public static List<ProductovOrden> getResumenVentasCasa(Venta v) {
        //inicializando los datos
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            if (o.getDeLaCasa()) {
                joinListsProductovOrden(ret,
                        new ArrayList<>(o.getProductovOrdenList()));
            }

        }//nˆ3

        return ret;
    }

    //
    //Métodos referentes a los resumenes del consumo de insumos
    //
    public static List<ProductoInsumo> getResumenGastosCocina(Cocina c, Venta v) {

        //inicializando los datos
        ArrayList<ProductoInsumo> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            for (ProductovOrden p : o.getProductovOrdenList()) {
                if (p.getProductoVenta().getCocinacodCocina().equals(c)) {
                    joinListsProductoInsumos(ret,
                            new ArrayList<>(p.getProductoVenta().
                                    getProductoInsumoList()), p.getCantidad());
                }
            }
        }
        return ret;
    }

    public static void getResumenGastosOnTable(JTable tabla, Venta v) {

        //inicializando los datos
        ArrayList[] rowData = comun.initArray(new ArrayList[5]);
        ArrayList<ProductoInsumo> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            for (ProductovOrden p : o.getProductovOrdenList()) {
                joinListsProductoInsumos(ret,
                        new ArrayList<>(p.getProductoVenta().
                                getProductoInsumoList()), p.getCantidad());
            }
        }

        //ordeneando los datos alfabeticamente 
        Collections.sort(ret, (ProductoInsumo o1, ProductoInsumo o2)
                -> o1.getInsumo().getNombre().compareTo(o2.getInsumo().getNombre()));

        //convirtiendo a rowData
        float total = 0;
        for (ProductoInsumo x : ret) {

            rowData[0].add(x.getInsumo().getCodInsumo());
            rowData[1].add(x.getInsumo().getNombre());
            rowData[2].add(x.getInsumo().getUm());
            rowData[3].add(x.getCantidad());
            rowData[4].add(x.getCosto());
            total += x.getCosto();
        }

        //llenando la tabla
        try {
            comun.AddToTable(rowData, tabla);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public static void getResumenGastosCocinaOnTable(JTable tabla, Venta v, Cocina c) {

        //inicializando los datos
        ArrayList[] rowData = comun.initArray(new ArrayList[5]);
        ArrayList<ProductoInsumo> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            for (ProductovOrden p : o.getProductovOrdenList()) {
                if (p.getProductoVenta().getCocinacodCocina().equals(c)) {
                    joinListsProductoInsumos(ret,
                            new ArrayList<>(p.getProductoVenta().
                                    getProductoInsumoList()), p.getCantidad());
                }
            }
        }

        //ordeneando los datos alfabeticamente 
        Collections.sort(ret, (ProductoInsumo o1, ProductoInsumo o2)
                -> o1.getInsumo().getNombre().compareTo(o2.getInsumo().getNombre()));

        //convirtiendo a rowData
        float total = 0;
        for (ProductoInsumo x : ret) {

            rowData[0].add(x.getInsumo().getCodInsumo());
            rowData[1].add(x.getInsumo().getNombre());
            rowData[2].add(x.getInsumo().getUm());
            rowData[3].add(x.getCantidad());
            rowData[4].add(x.getCosto());
            total += x.getCosto();
        }

        //llenando la tabla
        try {
            comun.AddToTable(rowData, tabla);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

    public static void getResumenGastosDeLaCasaOnTable(JTable tabla, Venta v) {

        //inicializando los datos
        ArrayList[] rowData = comun.initArray(new ArrayList[5]);
        ArrayList<ProductoInsumo> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            if (o.getDeLaCasa()) {
                for (ProductovOrden p : o.getProductovOrdenList()) {
                    joinListsProductoInsumos(ret,
                            new ArrayList<>(p.getProductoVenta().
                                    getProductoInsumoList()), p.getCantidad());
                }
            }
        }

        //convirtiendo a rowData
        float total = 0;
        for (ProductoInsumo x : ret) {

            rowData[0].add(x.getInsumo().getCodInsumo());
            rowData[1].add(x.getInsumo().getNombre());
            rowData[2].add(x.getInsumo().getUm());
            rowData[3].add(x.getCantidad());
            rowData[4].add(x.getCosto());
            total += x.getCosto();
        }

        //llenando la tabla
        try {
            comun.AddToTable(rowData, tabla);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public static void getResumenGastosDeLaCasaCocinaOnTable(JTable tabla, Venta v, Cocina c) {

        //inicializando los datos
        ArrayList[] rowData = comun.initArray(new ArrayList[5]);
        ArrayList<ProductoInsumo> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            if (o.getDeLaCasa()) {
                for (ProductovOrden p : o.getProductovOrdenList()) {
                    if (p.getProductoVenta().getCocinacodCocina().equals(c)) {
                        joinListsProductoInsumos(ret,
                                new ArrayList<>(p.getProductoVenta().
                                        getProductoInsumoList()), p.getCantidad());
                    }
                }
            }
        }

        //convirtiendo a rowData
        float total = 0;
        for (ProductoInsumo x : ret) {

            rowData[0].add(x.getInsumo().getCodInsumo());
            rowData[1].add(x.getInsumo().getNombre());
            rowData[2].add(x.getInsumo().getUm());
            rowData[3].add(x.getCantidad());
            rowData[4].add(x.getCosto());
            total += x.getCosto();
        }

        //llenando la tabla
        try {
            comun.AddToTable(rowData, tabla);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

    //
    //Métodos privados para el funcionamiento de la clase
    //
    /**
     * agrega a un arreglo de ProductosvOrdenes una nueva orden
     *
     * @param pivot - el arreglo al que agregar la nueva orden
     * @param b - la orden a agregar
     *
     */
    private static void joinListsProductovOrden(ArrayList<ProductovOrden> pivot,
            ArrayList<ProductovOrden> b) {

        while (!b.isEmpty()) {
            boolean founded = false;
            for (int j = 0; j < pivot.size() && !founded; j++) {
                if (b.get(0).getProductoVenta().getPCod().equals(
                        pivot.get(j).getProductoVenta().getPCod())) {
                    founded = true;
                    pivot.get(j).setCantidad(pivot.get(j).getCantidad() + b.get(0).getCantidad());
                }
            }
            if (!founded) {
                ProductovOrden po = new ProductovOrden(b.get(0).getProductovOrdenPK());
                po.setCantidad(b.get(0).getCantidad());
                po.setEnviadosacocina(b.get(0).getEnviadosacocina());
                po.setOrden(b.get(0).getOrden());
                po.setProductoVenta(b.get(0).getProductoVenta());
                pivot.add(po);
            }
            b.remove(0);

        }

    }

    private static void joinListsProductovOrdenByCocina(ArrayList<ProductovOrden> pivot,
            ArrayList<ProductovOrden> b, Cocina c) {

        while (!b.isEmpty()) {
            boolean founded = false;
            for (int j = 0; j < pivot.size() && !founded; j++) {
                if (b.get(0).getProductoVenta().getPCod().equals(
                        pivot.get(j).getProductoVenta().getPCod())) {
                    founded = true;
                    pivot.get(j).setCantidad(pivot.get(j).getCantidad() + b.get(0).getCantidad());
                }
            }
            if (!founded && b.get(0).getProductoVenta().getCocinacodCocina().equals(c)) {
                ProductovOrden po = new ProductovOrden(b.get(0).getProductovOrdenPK());
                po.setCantidad(b.get(0).getCantidad());
                po.setEnviadosacocina(b.get(0).getEnviadosacocina());
                po.setOrden(b.get(0).getOrden());
                po.setProductoVenta(b.get(0).getProductoVenta());
                pivot.add(po);
            }
            b.remove(0);

        }

    }

    /**
     * este metodo es para acumular la relacion producto insumo en un solo
     * arreglo
     *
     * @param pivot - el arreglo que se tiene para acumular
     * @param b - el nuevo productos que se va a incluir
     * @param cant - la cantidad de ese producto
     */
    private static void joinListsProductoInsumos(
            ArrayList<ProductoInsumo> pivot,
            ArrayList<ProductoInsumo> b, int cant) {

        for (ProductoInsumo x : b) {
            boolean founded = false;

            for (int j = 0; j < pivot.size() && !founded; j++) {
                if (x.getInsumo().getCodInsumo().
                        equals(pivot.get(j).getInsumo().getCodInsumo())) {
                    founded = true;
                    pivot.get(j).setCantidad(pivot.get(j).getCantidad() + (x.getCantidad() * cant));
                    pivot.get(j).setCosto(pivot.get(j).getCosto() + (x.getCosto() * cant));
                }
            }
            if (!founded) {
                ProductoInsumo pi = new ProductoInsumo(x.getProductoInsumoPK());
                pi.setCantidad(x.getCantidad() * cant);
                pi.setCosto(x.getCosto() * cant);
                pi.setInsumo(x.getInsumo());
                pi.setProductoVenta(x.getProductoVenta());
                pivot.add(pi);

            }
        }

    }

    public static float getValorTotalVentas(Venta v) {
        float total = 0;
        for (Orden x : v.getOrdenList()) {
            if (!x.getDeLaCasa() && x.getHoraTerminada() != null) {
                total += x.getOrdenvalorMonetario();
            }
        }
        return total;
    }

    public static float getValorTotalRedondeoAFavorDeLaCasa(Venta v) {
        int total = 0;
        for (Orden x : v.getOrdenList()) {
            total += comun.cantidadARedondearPorExceso((int) Math.ceil(x.getOrdenvalorMonetario() * 100));
        }
        return ((float) total) / 100;
    }

    public static List<Orden> getOrdenesActivas(Venta ventas) {

        ArrayList<Orden> ordenes = new ArrayList<>(ventas.getOrdenList());

        Collections.sort(ordenes, (Orden o1, Orden o2) -> {
            int idO1, idO2;
            idO1 = Integer.parseInt(o1.getCodOrden().substring(2));
            idO2 = Integer.parseInt(o2.getCodOrden().substring(2));
            return -1 * Integer.compare(idO1, idO2);
        });

        List<Orden> retOrd = new ArrayList<>();
        List<String> existingMesasName = new ArrayList<>();

        ordenes.forEach((o) -> {
            String codMesa = o.getMesacodMesa().getCodMesa();

            if (o.getHoraTerminada() == null) {
                retOrd.add(o);
                existingMesasName.add(codMesa);

            } else {
                if (!existingMesasName.contains(codMesa)) {
                    //if(o.getHoraTerminada().)
                    retOrd.add(o);
                    existingMesasName.add(codMesa);
                }
            }
        });
        return retOrd;
    }

    public static float getValorTotalGastos(Venta instance) {
         float total = 0;
        for (Orden x : instance.getOrdenList()) {
            if (!x.getDeLaCasa() && x.getHoraTerminada() != null) {
                total += x.getOrdengastoEninsumos();
            }
        }
        return total;
    }

}

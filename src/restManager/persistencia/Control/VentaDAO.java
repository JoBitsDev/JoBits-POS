/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.Control;

import GUI.Main;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.jdesktop.swingx.JXTable;

import restManager.persistencia.Cocina;
import restManager.persistencia.Venta;
import restManager.persistencia.Orden;
import restManager.persistencia.Personal;
import restManager.persistencia.ProductoInsumo;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.ProductovOrden;
import restManager.util.comun;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class VentaDAO {

    //
    //Métodos referentes a resumenes de las ventas
    //
    public static void getResumenVentasOnTable(JTable tabla, Venta v) {

        //inicializando los datos
        ArrayList[] rowData = comun.initArray(new ArrayList[5]);
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            if (!o.getDeLaCasa()) {
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

    public static void getResumenVentasCamareroOnTable(JTable tabla, Venta v, Personal p) {

        //inicializando los datos
        ArrayList[] rowData = comun.initArray(new ArrayList[3]);
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            if (!o.getDeLaCasa() && o.getPersonalusuario().equals(p)) {
                joinListsProductovOrden(ret,
                        new ArrayList(o.getProductovOrdenList()));
            }
        }//nˆ3

        //convirtiendo a rowData
        float total = 0;
        for (ProductovOrden x : ret) {
            ProductoVenta pv = x.getProductoVenta();
            total += pv.getPrecioVenta() * x.getCantidad();
        }
        if (total != 0) {
            rowData[0].add(p.getUsuario());
            rowData[1].add(p.getDatosPersonales().getNombre());
            rowData[2].add(total + Main.moneda);

            //llenando la tabla
            try {
                comun.AddToTable(rowData, tabla);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

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
            rowData[2].add(total + Main.moneda);

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

}

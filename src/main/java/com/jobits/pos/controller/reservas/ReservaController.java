/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.reservas;

import com.jobits.pos.adapters.repo.impl.ClienteDAO;
import com.jobits.pos.adapters.repo.impl.ConfigDAO;
import com.jobits.pos.adapters.repo.impl.MesaDAO;
import com.jobits.pos.adapters.repo.impl.OrdenDAO;
import com.jobits.pos.controller.AbstractController;
import com.jobits.pos.domain.models.Cliente;
import com.jobits.pos.domain.models.Configuracion;
import com.jobits.pos.domain.models.Mesa;
import com.jobits.pos.domain.models.Orden;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.ProductovOrden;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.reserva.presenter.ReservaDetailViewPresenter;
import java.awt.Container;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
public class ReservaController extends AbstractController<Orden>
        implements ReservaService {

    private Orden reserva;

    private boolean creatingMode = false;

    public ReservaController() {
        super(OrdenDAO.getInstance());
        reserva = generarNuevaReserva();
        creatingMode = true;
    }

    public ReservaController(Orden o) {
        super(OrdenDAO.getInstance());
        this.reserva = o;
    }

    @Override
    public Orden getReserva() {
        return reserva;
    }

    @Override
    public void constructView(Container parent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crearEditarReserva(Date fecha, Mesa mesa, Cliente cliente, List<ProductovOrden> lista) {
        if (mesa == null) {
            throw new IllegalArgumentException("Debe selecionar la mesa que desea reservar");
        }
        reserva.setVentafecha(fecha);
        reserva.setMesacodMesa(mesa);
        reserva.setClienteIdCliente(cliente);
        reserva.setProductovOrdenList(lista);
        reserva.setPorciento(reserva.getMesacodMesa().getAreacodArea().getPorcientoPorServicio().floatValue());
        if (creatingMode) {
            getModel().startTransaction();
            create(reserva);
            getModel().commitTransaction();
        } else {
            getModel().startTransaction();
            update(reserva);
            getModel().commitTransaction();
        }
    }

    @Override
    public List<Mesa> mesasDisponiblesParaReservar(Date fecha) {
        ArrayList<Mesa> mesas = (ArrayList<Mesa>) MesaDAO.getInstance().findAll();
        LocalDate fechaProgramada = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (fechaProgramada.isAfter(LocalDate.now().plusDays(1))) {
            Collections.sort(mesas, (Mesa o1, Mesa o2) -> Integer.compare(Integer.parseInt(o1.getCodMesa().split("-")[1]), Integer.parseInt(o2.getCodMesa().split("-")[1])));
            return mesas;
        } else {
            for (int i = 0; i < mesas.size();) {
                getModel().getEntityManager().refresh(mesas.get(i));
                if (!mesas.get(i).getEstado().equals("vacia")) {
                    mesas.remove(i);
                } else {
                    i++;
                }
            }
            if (R.loggedUser.getPuestoTrabajonombrePuesto().getAreacodArea() != null) {
                for (int i = 0; i < mesas.size();) {
                    if (!mesas.get(i).getAreacodArea().equals(R.loggedUser.getPuestoTrabajonombrePuesto().getAreacodArea())) {
                        mesas.remove(i);
                    } else {
                        i++;
                    }
                }
            }
            Collections.sort(mesas, (Mesa o1, Mesa o2) -> Integer.compare(Integer.parseInt(o1.getCodMesa().split("-")[1]), Integer.parseInt(o2.getCodMesa().split("-")[1])));
            return mesas;
        }
    }

    /**
     *
     * @param producto
     * @param cantidad
     * @return
     */
    @Override
    public ProductovOrden generarProductovOrden(ProductoVenta producto, float cantidad) {
        ProductovOrden ret = new ProductovOrden();

        ret.setProductoVenta(producto);
        ret.setOrden(reserva);
        ret.setPrecioVendido(producto.getPrecioVenta());
        ret.setNombreProductoVendido(producto.toString());
        ret.setCantidad(cantidad);

        ret.setEnviadosacocina(0f);
        ret.setNumeroComensal(0);
        ret.setAgregos(new ArrayList<>());
        ret.setAgregadoA(null);

        return ret;
    }

    private Orden generarNuevaReserva() {
        Orden ret = new Orden();
        ret.setCodOrden(generarCodOrden());
        ret.setPersonalusuario(R.loggedUser);
        ret.setDeLaCasa(false);
        ret.setHoraComenzada(new Date());
        ret.setOrdengastoEninsumos((float) 0);
        ret.setOrdenvalorMonetario((float) 0);
        ret.setProductovOrdenList(new ArrayList<>());
        ret.setVentafecha(new Date());
        return ret;
    }

    private String generarCodOrden() {
        ConfigDAO conf = new ConfigDAO();
        Configuracion c = conf.find("O");
        getModel().getEntityManager().refresh(c);
        int orden = c.getValor();
        c.setValor(orden + 1);
        conf.startTransaction();
        conf.edit(c);
        conf.commitTransaction();
        return "O-" + orden;
    }

    @Override
    public void agregarProductoAOrden(boolean esAgrego, ProductovOrden pvoAgregar, ProductovOrden pvoSeleccionado) {
        if (esAgrego) {
            if (pvoSeleccionado != null) {
                pvoAgregar.setAgregadoA(pvoSeleccionado);
                List<ProductovOrden> lista = pvoSeleccionado.getAgregos();
                lista.add(pvoAgregar);
                pvoSeleccionado.setAgregos(lista);
                lista = reserva.getProductovOrdenList();
                lista.remove(pvoSeleccionado);
                lista.add(pvoSeleccionado);
                reserva.setProductovOrdenList(lista);
            }
        } else {
            List<ProductovOrden> list = reserva.getProductovOrdenList();
            boolean found = false;
            ProductovOrden founded = null;
            for (ProductovOrden x : list) {
                if (x.getProductoVenta().getCodigoProducto().equals(pvoAgregar.getProductoVenta().getCodigoProducto())) {
                    founded = x;
                    found = true;
                    break;
                }
            }
            if (found && founded.getAgregos().isEmpty()) {
                founded.setCantidad(founded.getCantidad() + pvoAgregar.getCantidad());
            }
            if (found) {
                list.remove(founded);
                list.add(founded);
            } else {
                list.add(pvoAgregar);
            }
            reserva.setProductovOrdenList(list);
        }
    }

    @Override
    public void eliminarProDuctoDeOrden(ProductovOrden producto_seleccionado) {
        List<ProductovOrden> list = reserva.getProductovOrdenList();
        int index = list.indexOf(producto_seleccionado);
        list.remove(index);
        reserva.setProductovOrdenList(list);
    }

    @Override
    public List<Cliente> getListaClientes() {
        return ClienteDAO.getInstance().findAll();
    }

    @Override
    public Date formatDate(int hora, int minutos, String pm_am, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
        String input = sdf.format(date) + " " + hora + ":" + minutos + " " + pm_am;
        try {
            date = df.parse(input);
        } catch (ParseException ex) {
            Logger.getLogger(ReservaDetailViewPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.almacen;

import GUI.Views.AbstractView;
import GUI.Views.Almacen.IpvGestionView;
import com.sun.webkit.Timer;

import java.awt.Container;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import restManager.controller.AbstractDialogController;
import restManager.controller.login.LogInController;
import restManager.controller.venta.VentaDetailController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.ValidatingException;

import restManager.persistencia.Cocina;
import restManager.persistencia.Insumo;
import restManager.persistencia.Ipv;
import restManager.persistencia.IpvRegistro;
import restManager.persistencia.IpvRegistroPK;
import restManager.persistencia.models.CocinaDAO;
import restManager.persistencia.models.InsumoDAO;
import restManager.persistencia.models.IpvDAO;
import restManager.persistencia.models.IpvRegistroDAO;
import restManager.resources.R;
import restManager.util.comun;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class IPVController extends AbstractDialogController<Ipv> {

    public IPVController() {
        super(IpvDAO.getInstance());
    }

    public IPVController(Container parent) {
        super(IpvDAO.getInstance());
        IpvRegistroDAO.getInstance().addPropertyChangeListener(this);
        constructView(parent);
    }

    @Override
    public void constructView(Container parent) {
        setView(new IpvGestionView((AbstractView) parent, this));
        getView().updateView();
        getView().setVisible(true);
        IpvRegistroDAO.getInstance().removePropertyChangeListener(this);

    }

    public List<String> getIpvRegistroList(Cocina cocina) {
        List<Date> ret = IpvRegistroDAO.getInstance().getIpvRegistroList(cocina);
        List<String> r = new ArrayList<>();
        ret.forEach((x) -> {
            r.add(R.DATE_FORMAT.format(x));
        });
        return r;
    }

    public List<IpvRegistro> getIpvRegistroList(Cocina cocina, Date fecha) {
        return IpvRegistroDAO.getInstance().getIpvRegistroList(cocina, fecha);
    }

    public List<Cocina> getCocinaList() {
        return CocinaDAO.getInstance().findAll();
    }

    public List<Insumo> getInsumoList() {
        return InsumoDAO.getInstance().findAll();
    }

    public Insumo getInsumo(String ipvinsumocodInsumo) {
        return InsumoDAO.getInstance().find(ipvinsumocodInsumo);
    }

    public void darEntrada(IpvRegistro instance) {
        float cantidad;
        try {
            cantidad = Float.parseFloat(showInputDialog(getView(), "Introduzca la cantidad a dar entrada"));
        } catch (NumberFormatException e) {
            showErrorDialog(getView(), "El valor introducido no es correcto");
            return;
        }
        if (showConfirmDialog(getView(), "Desea dar entrada a " + cantidad + " de " + instance.getIpv().getInsumo())) {
            if (cantidad < 0) {
                if(!new LogInController().constructoAuthorizationView(getView(), R.NivelAcceso.ADMINISTRADOR.getNivel())){
                    return;
                }
            }
            instance.setEntrada(instance.getEntrada() + cantidad);
            updateInstance(instance);
        }
    }

    public void ajustarConsumo(IpvRegistro instance) {
        float cantidad;
        try {
            cantidad = Float.parseFloat(showInputDialog(getView(), "Introduzca la cantidad a ajustar"));
        } catch (NumberFormatException e) {
            showErrorDialog(getView(), "El valor introducido no es correcto");
            return;
        }
        if (showConfirmDialog(getView(), "Desea ajustar el consumo de " + instance.getIpv().getInsumo() + " a " + cantidad)) {
            instance.setConsumoReal(cantidad);
            updateInstance(instance);
        }

    }

    public void updateInstance(IpvRegistro instance) {
        instance.setDisponible(instance.getEntrada() + instance.getInicio());
        instance.setFinal1(comun.setDosLugaresDecimalesFloat(instance.getDisponible() - instance.getConsumo()));
        if (instance.getConsumoReal() != null) {
            if (instance.getConsumoReal() > 0) {
                instance.setFinal1(comun.setDosLugaresDecimalesFloat(instance.getDisponible() - instance.getConsumoReal()));
            }
        }
        IpvRegistroDAO.getInstance().startTransaction();
        IpvRegistroDAO.getInstance().edit(instance);
        IpvRegistroDAO.getInstance().commitTransaction();
    }

    public List<IpvRegistro> inicializarIpvs(Date fecha) {
        ArrayList<IpvRegistro> ret = new ArrayList<>();
        getItems().forEach((x) -> {
            IpvRegistroPK pk = new IpvRegistroPK(x.getInsumo().getCodInsumo(), x.getCocina().getCodCocina(), fecha);
            IpvRegistro reg = IpvRegistroDAO.getInstance().find(pk);
            if (reg == null) {
                reg = new IpvRegistro(pk);
                reg.setIpv(x);
                IpvRegistroDAO.getInstance().startTransaction();
                IpvRegistroDAO.getInstance().create(reg);
                IpvRegistroDAO.getInstance().commitTransaction();
                reg.setInicio(getEntradaDiaAnterior(reg));
                reg.setEntrada((float) 0);
                reg.setConsumo((float) 0);
                reg.setConsumoReal((float) 0);
                updateInstance(reg);
            }
            ret.add(reg);
        });

        return ret;
    }

    private float getEntradaDiaAnterior(IpvRegistro reg) {
        Date d = reg.getIpvRegistroPK().getFecha();

        Calendar c = Calendar.getInstance();
        c.set(d.getYear() + 1900, d.getMonth(), d.getDate());
        c.add(Calendar.DAY_OF_MONTH, -1);

        d = Date.from(c.toInstant());
        IpvRegistroPK newReg
                = new IpvRegistroPK(reg.getIpvRegistroPK().getIpvinsumocodInsumo(),
                        reg.getIpvRegistroPK().getIpvcocinacodCocina(), d);
        IpvRegistro founded = IpvRegistroDAO.getInstance().find(newReg);

        return founded != null ? founded.getFinal1() : 0;

    }

    public void darEntrada(Insumo insumo, Cocina cocina, Date fecha, Float cantidad) {
        try {
            IpvRegistro reg = IpvRegistroDAO.getInstance().getIpvRegistro(cocina, fecha, insumo);
            if (reg != null) {
                reg.setEntrada(reg.getEntrada() + cantidad);
                updateInstance(reg);
            }
        } catch (Exception e) {
            throw new ValidatingException(getView(),
                    "El insumo en el ipv a dar entrada no existe o no hay un ipv inizializado en el dia actual");

        }
    }

    public ArrayList<IpvRegistro> calculate_IPV_to_Currenr(ArrayList<IpvRegistro> listaRegistros) {
        VentaDetailController controller = new VentaDetailController(listaRegistros.get(0).getIpvRegistroPK().getFecha());
        for (IpvRegistro x : listaRegistros) {
            x.setConsumo(comun.setDosLugaresDecimalesFloat(controller.getGastoTotalDeInsumo(x)));
            updateInstance(x);
        }
        return listaRegistros;
    }

    @Override
    public void create(Ipv selected, boolean quietMode) {
        super.create(selected, quietMode); //To change body of generated methods, choose Tools | Templates.
        inicializarIpvs(new VentaDetailController().getDiaDeVenta(null).getFecha());
    }

    @Override
    public void destroy(Ipv selected, boolean quietMode) {
        boolean previousValue = showDialogs;
        setShowDialogs(!quietMode);
        getModel().startTransaction();
        for (IpvRegistro x : selected.getIpvRegistroList()) {
            getModel().getEntityManager().remove(x);
        }
        
        getModel().getEntityManager().remove(selected);
        getModel().commitTransaction();
        setShowDialogs(previousValue);
    }

}

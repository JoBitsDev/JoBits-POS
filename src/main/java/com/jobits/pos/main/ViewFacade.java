/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.main;

import com.jobits.pos.ui.about.AcercaDeView;
import com.jobits.pos.ui.mainmenu.MainMenuView;
import com.jobits.pos.ui.View;
import com.jobits.pos.ui.almacen.FacturaView;
import com.jobits.pos.ui.almacen.AlmacenMainView;
import com.jobits.pos.ui.almacen.TransaccionListView;
import com.jobits.pos.ui.almacen.ipv.IPVPedidoVentasView;
import com.jobits.pos.ui.almacen.ipv.IpvGestionView;
import com.jobits.pos.ui.areaventa.AreaDetailView;
import com.jobits.pos.ui.areaventa.AreaVentaListView;
import com.jobits.pos.ui.autorizo.AutorizoView;
import com.jobits.pos.ui.backup.BackUpView;
import com.jobits.pos.ui.clientes.ClientesDetailView;
import com.jobits.pos.ui.clientes.ClientesListView;
import com.jobits.pos.ui.configuracion.ConfiguracionView;
import com.jobits.pos.ui.dashboard.DashBoardView;
import com.jobits.pos.ui.imagemanager.ImageManagerView;
import com.jobits.pos.ui.insumo.InsumoDetailView;
import com.jobits.pos.ui.insumo.InsumoListView;
import com.jobits.pos.ui.licencia.LicenceDialogView;
import com.jobits.pos.ui.login.LogInView;
import com.jobits.pos.ui.login.UbicacionView;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.productos.ProductoVentaDetailView;
import com.jobits.pos.ui.productos.ProductoVentaListView;
import com.jobits.pos.ui.puntoelaboracion.PuntoElaboracionListView;
import com.jobits.pos.ui.cartas.CartasSeccionView;
import com.jobits.pos.ui.cartas.SeccionDetailView;
import com.jobits.pos.ui.reportes.ReportarBugView;
import com.jobits.pos.ui.reserva.ReservasDetailView;
import com.jobits.pos.ui.reserva.ReservasListView;
import com.jobits.pos.ui.trabajadores.NominasDetailView;
import com.jobits.pos.ui.trabajadores.PersonalDetailView;
import com.jobits.pos.ui.trabajadores.PersonalListView;
import com.jobits.pos.ui.trabajadores.PuestoTrabajoDetailView;
import com.jobits.pos.ui.trabajadores.PuestoTrabajoListView;
import com.jobits.pos.ui.venta.orden.OrdenDetailFragmentView;
import com.jobits.pos.ui.venta.VentaCalendarView;
import com.jobits.pos.ui.venta.VentaDetailView;
import com.jobits.pos.ui.venta.VentaResumenView;
import com.jobits.pos.ui.venta.VentaStatisticsView;
import com.jobits.pos.ui.venta.orden.CalcularCambioView;
import com.jobits.pos.ui.venta.orden.OrdenLogView;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class ViewFacade {

    public static View getRootView(AbstractViewPresenter presenter) {
        return LogInView.getInstance();
    }

    public static View getView(String viewUniqueName, AbstractViewPresenter presenter) {
        AbstractViewPresenter p;
        if (presenter == null) {
            p = PresenterFacade.getPresenterFor(viewUniqueName);
        } else {
            p = presenter;
        }
        switch (viewUniqueName) {
            case UbicacionView.VIEW_NAME:
                return new UbicacionView(p);
            case AcercaDeView.VIEW_NAME:
                return new AcercaDeView(p);
            case LogInView.VIEW_NAME:
                return getRootView(p);
            case MainMenuView.VIEW_NAME:
                return new MainMenuView(p);
            case DashBoardView.VIEW_NAME:
                return new DashBoardView(p);
            case ProductoVentaListView.VIEW_NAME:
                return new ProductoVentaListView((AbstractListViewPresenter) p);
            case ProductoVentaDetailView.VIEW_NAME:
                return new ProductoVentaDetailView(p);
            case InsumoListView.VIEW_NAME:
                return new InsumoListView((AbstractListViewPresenter) p);
            case InsumoDetailView.VIEW_NAME:
                return new InsumoDetailView(p);
            case PersonalListView.VIEW_NAME:
                return new PersonalListView((AbstractListViewPresenter) p);
            case PersonalDetailView.VIEW_NAME:
                return new PersonalDetailView(p);
            case PuestoTrabajoListView.VIEW_NAME:
                return new PuestoTrabajoListView((AbstractListViewPresenter) p);
            case PuestoTrabajoDetailView.VIEW_NAME:
                return new PuestoTrabajoDetailView(p);
            case NominasDetailView.VIEW_NAME:
                return new NominasDetailView(p);
            case PuntoElaboracionListView.VIEW_NAME:
                return new PuntoElaboracionListView((AbstractListViewPresenter) p);
            case CartasSeccionView.VIEW_NAME:
                return new CartasSeccionView(p);
            case SeccionDetailView.VIEW_NAME:
                return new SeccionDetailView(p);
            case AreaVentaListView.VIEW_NAME:
                return new AreaVentaListView(p);
            case AreaDetailView.VIEW_NAME:
                return new AreaDetailView(p);
            case ConfiguracionView.VIEW_NAME:
                return new ConfiguracionView(p);
            case IpvGestionView.VIEW_NAME:
                return new IpvGestionView(p);
            case VentaDetailView.VIEW_NAME:
                return new VentaDetailView(p);
            case OrdenDetailFragmentView.VIEW_NAME:
                return new OrdenDetailFragmentView(p);
            case BackUpView.VIEW_NAME:
                return new BackUpView(p);
            case VentaCalendarView.VIEW_NAME:
                return new VentaCalendarView(p);
            case VentaStatisticsView.VIEW_NAME:
                return new VentaStatisticsView(p);
            case AlmacenMainView.VIEW_NAME:
                return new AlmacenMainView(p);
            case FacturaView.VIEW_NAME:
                return new FacturaView(p);
            case ReportarBugView.VIEW_NAME:
                return new ReportarBugView(p);
            case ClientesDetailView.VIEW_NAME:
                return new ClientesDetailView(p);
            case ReservasDetailView.VIEW_NAME:
                return new ReservasDetailView(p);
            case ClientesListView.VIEW_NAME:
                return new ClientesListView((AbstractListViewPresenter) p);
            case OrdenLogView.VIEW_NAME:
                return new OrdenLogView(p);
            case ImageManagerView.VIEW_NAME:
                return new ImageManagerView(p);
            case CalcularCambioView.VIEW_NAME:
                return new CalcularCambioView(p);
            case VentaResumenView.VIEW_NAME:
                return new VentaResumenView(p);
            case AutorizoView.VIEW_NAME:
                return new AutorizoView(p);
            case IPVPedidoVentasView.VIEW_NAME:
                return new IPVPedidoVentasView(p);
            case ReservasListView.VIEW_NAME:
                return new ReservasListView((AbstractListViewPresenter) p);
            case TransaccionListView.VIEW_NAME:
                return new TransaccionListView((AbstractListViewPresenter) p);
            case LicenceDialogView.VIEW_NAME:
                return new LicenceDialogView(
                        Application.getInstance().getLicenceController());

            default:
                throw new IllegalArgumentException("Vista no registrada para UID " + viewUniqueName);
        }
    }
}

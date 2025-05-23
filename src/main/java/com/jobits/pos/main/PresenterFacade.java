/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.main;

import com.jobits.pos.client.webconnection.login.model.UbicacionService;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.controller.venta.VentaCalendarResumeUseCase;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Carta;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.Seccion;
import com.jobits.pos.inventario.core.almacen.domain.Almacen;
import com.jobits.pos.inventario.core.almacen.usecase.AlmacenManageService;
import com.jobits.pos.inventario.core.almacen.usecase.IPVService;
import com.jobits.pos.inventario.core.almacen.usecase.PedidoIpvVentasService;
import com.jobits.pos.inventario.core.almacen.usecase.TransaccionListService;
import com.jobits.pos.reserva.core.domain.Categoria;
import com.jobits.pos.reserva.core.domain.Reserva;
import com.jobits.pos.reserva.core.domain.Ubicacion;
import com.jobits.pos.ui.about.AcercaDeView;
import com.jobits.pos.ui.about.AcercaDeViewPresenter;
import com.jobits.pos.ui.almacen.AlmacenMainView;
import com.jobits.pos.ui.almacen.FacturaView;
import com.jobits.pos.ui.almacen.PendingOperationsListView;
import com.jobits.pos.ui.almacen.TransaccionListView;
import com.jobits.pos.ui.almacen.ipv.IPVPedidoVentasView;
import com.jobits.pos.ui.almacen.ipv.IpvGestionView;
import com.jobits.pos.ui.almacen.ipv.presenter.IPVPedidoVentasViewPresenter;
import com.jobits.pos.ui.almacen.ipv.presenter.IpvGestionViewPresenter;
import com.jobits.pos.ui.almacen.presenter.AlmacenViewPresenter;
import com.jobits.pos.ui.almacen.presenter.FacturaViewPresenter;
import com.jobits.pos.ui.almacen.presenter.OperacionesListPresenter;
import com.jobits.pos.ui.almacen.presenter.TransaccionListPresenter;
import com.jobits.pos.ui.areaventa.AreaDetailView;
import com.jobits.pos.ui.areaventa.AreaVentaListView;
import com.jobits.pos.ui.areaventa.MesaDetailView;
import com.jobits.pos.ui.areaventa.presenter.AreaDetailViewPresenter;
import com.jobits.pos.ui.areaventa.presenter.AreaVentaViewPresenter;
import com.jobits.pos.ui.areaventa.presenter.MesaDetailViewPresenter;
import com.jobits.pos.ui.autorizo.AutorizoView;
import com.jobits.pos.ui.autorizo.presenter.AutorizoViewPresenter;
import com.jobits.pos.ui.backup.BackUpView;
import com.jobits.pos.ui.backup.presenter.BackUpViewPresenter;
import com.jobits.pos.ui.cartas.CartasSeccionView;
import com.jobits.pos.ui.cartas.SeccionDetailView;
import com.jobits.pos.ui.cartas.presenter.CartasSeccionViewPresenter;
import com.jobits.pos.ui.cartas.presenter.SeccionDetailViewPresenter;
import com.jobits.pos.ui.clientes.ClientesDetailView;
import com.jobits.pos.ui.clientes.ClientesListView;
import com.jobits.pos.ui.clientes.DomicilioView;
import com.jobits.pos.ui.clientes.presenter.ClientesDetailViewPresenter;
import com.jobits.pos.ui.clientes.presenter.ClientesListViewPresenter;
import com.jobits.pos.ui.clientes.presenter.DomicilioViewPresenter;
import com.jobits.pos.ui.clientes.reserva.ClientesReservaDetailView;
import com.jobits.pos.ui.clientes.reserva.ClientesReservaListView;
import com.jobits.pos.ui.clientes.reserva.presenter.ClientesReservaDetailViewPresenter;
import com.jobits.pos.ui.clientes.reserva.presenter.ClientesReservaListViewPresenter;
import com.jobits.pos.ui.configuracion.ConfiguracionView;
import com.jobits.pos.ui.configuracion.presenter.ConfigurationViewPresenter;
import com.jobits.pos.ui.dashboard.DashBoardView;
import com.jobits.pos.ui.dashboard.presenter.DashboardViewPresenter;
import com.jobits.pos.ui.imagemanager.ImageManagerView;
import com.jobits.pos.ui.imagemanager.presenter.ImageManagerViewPresenter;
import com.jobits.pos.ui.insumo.InsumoDetailView;
import com.jobits.pos.ui.insumo.InsumoListView;
import com.jobits.pos.ui.insumo.presenter.InsumoDetailViewPresenter;
import com.jobits.pos.ui.insumo.presenter.InsumoListViewPresenter;
import com.jobits.pos.ui.licencia.LicenceDialogView;
import com.jobits.pos.ui.login.ChangeUserView;
import com.jobits.pos.ui.login.LogInView;
import com.jobits.pos.ui.login.UbicacionView;
import com.jobits.pos.ui.login.presenter.ChangeUserViewPresenter;
import com.jobits.pos.ui.login.presenter.LoginViewPresenter;
import com.jobits.pos.ui.login.presenter.UbicacionViewPresenter;
import com.jobits.pos.ui.mainmenu.MainMenuView;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.productos.ProductoVentaDetailView;
import com.jobits.pos.ui.productos.ProductoVentaListView;
import com.jobits.pos.ui.productos.presenter.ProductoVentaDetailPresenter;
import com.jobits.pos.ui.productos.presenter.ProductoVentaListViewPresenter;
import com.jobits.pos.ui.puntoelaboracion.PuntoElaboracionListView;
import com.jobits.pos.ui.puntoelaboracion.presenter.PuntoElaboracionListViewPresenter;
import com.jobits.pos.ui.reportes.ReportarBugView;
import com.jobits.pos.ui.reportes.presenter.ReportarBugViewPresenter;
import com.jobits.pos.ui.reserva.ReservaOrdenDetailView;
import com.jobits.pos.ui.reserva.ReservaSchedulerView;
import com.jobits.pos.ui.reserva.ReservasDetailView;
import com.jobits.pos.ui.reserva.presenter.ReservaDetailViewPresenter;
import com.jobits.pos.ui.reserva.presenter.ReservaOrdenDetailViewPresenter;
import com.jobits.pos.ui.reserva.presenter.ReservaSchedulerViewPresenter;
import com.jobits.pos.ui.reserva.ubicaciones.CategoriaDetailView;
import com.jobits.pos.ui.reserva.ubicaciones.UbicacionDetailView;
import com.jobits.pos.ui.reserva.ubicaciones.presenter.CategoriaDetailViewPresenter;
import com.jobits.pos.ui.reserva.ubicaciones.presenter.UbicacionDetailViewPresenter;
import com.jobits.pos.ui.trabajadores.NominasDetailView;
import com.jobits.pos.ui.trabajadores.PersonalDetailView;
import com.jobits.pos.ui.trabajadores.PersonalListView;
import com.jobits.pos.ui.trabajadores.PuestoTrabajoDetailView;
import com.jobits.pos.ui.trabajadores.PuestoTrabajoListView;
import com.jobits.pos.ui.trabajadores.presenter.NominasDetailPresenter;
import com.jobits.pos.ui.trabajadores.presenter.PersonalDetailViewPresenter;
import com.jobits.pos.ui.trabajadores.presenter.PersonalListViewPresenter;
import com.jobits.pos.ui.trabajadores.presenter.PuestoTrabajoDetailViewPresenter;
import com.jobits.pos.ui.trabajadores.presenter.PuestoTrabajoListViewPresenter;
import com.jobits.pos.ui.venta.VentaCalendarView;
import com.jobits.pos.ui.venta.VentaDetailView;
import com.jobits.pos.ui.venta.VentaStatisticsView;
import com.jobits.pos.ui.venta.mesas.MesaListView;
import com.jobits.pos.ui.venta.mesas.presenter.MesaListViewPresenter;
import com.jobits.pos.ui.venta.orden.AgregarProductoView;
import com.jobits.pos.ui.venta.orden.CalcularCambioView;
import com.jobits.pos.ui.venta.orden.OrdenLogView;
import com.jobits.pos.ui.venta.orden.ProductoEnCalienteView;
import com.jobits.pos.ui.venta.orden.presenter.AgregarProductoViewPresenter;
import com.jobits.pos.ui.venta.orden.presenter.CalcularCambioViewPresenter;
import com.jobits.pos.ui.venta.orden.presenter.OrdenLogViewPresenter;
import com.jobits.pos.ui.venta.orden.presenter.ProductoEnCalienteViewPresenter;
import com.jobits.pos.ui.venta.presenter.VentaCalendarViewPresenter;
import com.jobits.pos.ui.venta.presenter.VentaDetailViewPresenter;
import com.jobits.pos.ui.venta.presenter.VentaStatisticsViewPresenter;
import com.jobits.pos.ui.venta.resumen.ResumenMainview;
import com.jobits.pos.ui.venta.resumen.presenter.ResumenMainViewPresenter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class PresenterFacade {

    public static AbstractViewPresenter getPresenterFor(String viewUIDName) {
        switch (viewUIDName) {
            case LogInView.VIEW_NAME:
                return new LoginViewPresenter();
            case AcercaDeView.VIEW_NAME:
                return new AcercaDeViewPresenter();
            case ChangeUserView.VIEW_NAME:
                return new ChangeUserViewPresenter();
            case UbicacionView.VIEW_NAME:
                return new UbicacionViewPresenter(PosDesktopUiModule.getInstance().getImplementation(UbicacionService.class));
            case MainMenuView.VIEW_NAME:
                throw new IllegalStateException("Bad call on view: " + viewUIDName);
            case DashBoardView.VIEW_NAME:
                return new DashboardViewPresenter();
            case ProductoVentaListView.VIEW_NAME:
                return new ProductoVentaListViewPresenter();
            case ProductoVentaDetailView.VIEW_NAME:
                return new ProductoVentaDetailPresenter(null);
            case InsumoListView.VIEW_NAME:
                return new InsumoListViewPresenter();
            case InsumoDetailView.VIEW_NAME:
                return new InsumoDetailViewPresenter(null);
            case PersonalListView.VIEW_NAME:
                return new PersonalListViewPresenter();
            case PersonalDetailView.VIEW_NAME:
                return new PersonalDetailViewPresenter(null);
            case PuestoTrabajoListView.VIEW_NAME:
                return new PuestoTrabajoListViewPresenter();
            case PuestoTrabajoDetailView.VIEW_NAME:
                return new PuestoTrabajoDetailViewPresenter(null);
            case NominasDetailView.VIEW_NAME:
                return new NominasDetailPresenter();
            case PuntoElaboracionListView.VIEW_NAME:
                return new PuntoElaboracionListViewPresenter();
            case CartasSeccionView.VIEW_NAME:
                return new CartasSeccionViewPresenter();
            case SeccionDetailView.VIEW_NAME:
                return new SeccionDetailViewPresenter(new Seccion(), new Carta());
            case AreaVentaListView.VIEW_NAME:
                return new AreaVentaViewPresenter();
            case AreaDetailView.VIEW_NAME:
                return new AreaDetailViewPresenter(null, true);
            case ConfiguracionView.VIEW_NAME:
                return new ConfigurationViewPresenter();
            case IpvGestionView.VIEW_NAME:
                return new IpvGestionViewPresenter(PosDesktopUiModule.getInstance().getImplementation(IPVService.class));
            case VentaDetailView.VIEW_NAME:
                return new VentaDetailViewPresenter(
                        PosDesktopUiModule.getInstance().getImplementation(VentaDetailService.class),
                        PosDesktopUiModule.getInstance().getImplementation(OrdenService.class),
                        -1);
            case BackUpView.VIEW_NAME:
                return new BackUpViewPresenter();
            case AgregarProductoView.VIEW_NAME:
                return new AgregarProductoViewPresenter(null, null, 0);
            case ReservaOrdenDetailView.VIEW_NAME:
                return new ReservaOrdenDetailViewPresenter(null, PosDesktopUiModule.getInstance().getImplementation(OrdenService.class));
            case VentaCalendarView.VIEW_NAME:
                return new VentaCalendarViewPresenter(PosDesktopUiModule.getInstance().getImplementation(VentaCalendarResumeUseCase.class));
            case VentaStatisticsView.VIEW_NAME:
                return new VentaStatisticsViewPresenter(PosDesktopUiModule.getInstance().getImplementation(VentaCalendarResumeUseCase.class));
            case AlmacenMainView.VIEW_NAME:
                return new AlmacenViewPresenter(
                        PosDesktopUiModule.getInstance().getImplementation(AlmacenManageService.class));
            case FacturaView.VIEW_NAME:
                return new FacturaViewPresenter(PosDesktopUiModule.getInstance().getImplementation(AlmacenManageService.class), new Almacen());
            case ReportarBugView.VIEW_NAME:
                return new ReportarBugViewPresenter();
            case OrdenLogView.VIEW_NAME:
                return new OrdenLogViewPresenter(null);
            case ProductoEnCalienteView.VIEW_NAME:
                return new ProductoEnCalienteViewPresenter(null);
            case ReservasDetailView.VIEW_NAME:
                return new ReservaDetailViewPresenter(new Reserva(), true);
            case CategoriaDetailView.VIEW_NAME:
                return new CategoriaDetailViewPresenter(new Categoria(), true);
            case CalcularCambioView.VIEW_NAME:
                return new CalcularCambioViewPresenter(new Orden());
            case AutorizoView.VIEW_NAME:
                return new AutorizoViewPresenter(null);
            case ImageManagerView.VIEW_NAME:
                return new ImageManagerViewPresenter(null);
            case TransaccionListView.VIEW_NAME:
                return new TransaccionListPresenter(PosDesktopUiModule.getInstance().getImplementation(TransaccionListService.class), null);
            case PendingOperationsListView.VIEW_NAME:
                return new OperacionesListPresenter(null);
            case MesaListView.VIEW_NAME:
                return new MesaListViewPresenter();
            case ClientesListView.VIEW_NAME:
                return new ClientesListViewPresenter();
            case ClientesDetailView.VIEW_NAME:
                return new ClientesDetailViewPresenter(null);
            case ClientesReservaListView.VIEW_NAME:
                return new ClientesReservaListViewPresenter();
            case ClientesReservaDetailView.VIEW_NAME:
                return new ClientesReservaDetailViewPresenter(null);
            case ReservaSchedulerView.VIEW_NAME:
                return new ReservaSchedulerViewPresenter();
            case ResumenMainview.VIEW_NAME:
                return new ResumenMainViewPresenter();
            case DomicilioView.VIEW_NAME:
                return new DomicilioViewPresenter(null);
            case MesaDetailView.VIEW_NAME:
                return new MesaDetailViewPresenter(new Area(), new Mesa(), true);
            case UbicacionDetailView.VIEW_NAME:
                return new UbicacionDetailViewPresenter(new Ubicacion(), true);
            case IPVPedidoVentasView.VIEW_NAME:
                return new IPVPedidoVentasViewPresenter(
                        PosDesktopUiModule.getInstance().getImplementation(PedidoIpvVentasService.class),
                        null, null, new ArrayList<>());
            case LicenceDialogView.VIEW_NAME:
                Logger.getLogger(LicenceDialogView.class.getName()).log(Level.WARNING, "No presenter register for {0}", viewUIDName);
                return null;
            default:
                throw new IllegalArgumentException("No presenter register for " + viewUIDName);
        }

    }

}

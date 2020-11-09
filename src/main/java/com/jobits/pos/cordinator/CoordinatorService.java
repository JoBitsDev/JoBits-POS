/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.cordinator;

import com.jobits.pos.ui.about.AcercaDeView;
import com.jobits.pos.ui.login.LogInView;
import com.jobits.pos.ui.mainmenu.MainMenuView;
import com.jobits.pos.ui.almacen.OldAlmacenListView;
import com.jobits.pos.ui.almacen.FacturaView;
import com.jobits.pos.ui.almacen.AlmacenMainView;
import com.jobits.pos.ui.almacen.TransaccionListView;
import com.jobits.pos.ui.almacen.ipv.IpvGestionView;
import com.jobits.pos.ui.areaventa.AreaDetailView;
import com.jobits.pos.ui.areaventa.AreaVentaListView;
import com.jobits.pos.ui.backup.BackUpView;
import com.jobits.pos.ui.configuracion.ConfiguracionView;
import com.jobits.pos.ui.imagemanager.ImageManagerView;
import com.jobits.pos.ui.insumo.InsumoDetailView;
import com.jobits.pos.ui.insumo.InsumoListView;
import com.jobits.pos.ui.licencia.LicenceDialogView;
import com.jobits.pos.ui.login.UbicacionView;
import com.jobits.pos.ui.productos.ProductoVentaDetailView;
import com.jobits.pos.ui.productos.ProductoVentaListView;
import com.jobits.pos.ui.puntoelaboracion.PuntoElaboracionListView;
import com.jobits.pos.ui.menu.MenuSeccionView;
import com.jobits.pos.ui.reportes.ReportarBugView;
import com.jobits.pos.ui.trabajadores.NominasDetailView;
import com.jobits.pos.ui.trabajadores.PersonalDetailView;
import com.jobits.pos.ui.trabajadores.PersonalListView;
import com.jobits.pos.ui.trabajadores.PuestoTrabajoDetailView;
import com.jobits.pos.ui.trabajadores.PuestoTrabajoListView;
import com.jobits.pos.ui.venta.orden.OrdenDetailFragmentView;
import com.jobits.pos.ui.venta.VentaCalendarView;
import com.jobits.pos.ui.venta.VentaDetailView;
import com.jobits.pos.ui.venta.VentaStatisticsView;
import com.jobits.pos.ui.venta.orden.OrdenLogView;
import com.jobits.pos.ui.venta.orden.presenter.OrdenDetailViewModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class CoordinatorService implements Coordinator {

    public static final String AVAILABLE_EVERYWHERE = "Available everywhere";

    private Map<String, List<String>> navigationGraph;

    private static CoordinatorService instance;

    private CoordinatorService() {
        populateNavigationGraph();
    }

    public static CoordinatorService getInstance() {
        if (instance == null) {
            instance = new CoordinatorService();
        }
        return instance;
    }

    @Override
    public boolean canNavigateTo(String currentViewUID, String toViewUniqueName) {
        List<String> availablesViews = new ArrayList<>(navigationGraph.getOrDefault(currentViewUID, new ArrayList<>()));
        for (String x : navigationGraph.get(AVAILABLE_EVERYWHERE)) {
            availablesViews.add(x);
        }
        return availablesViews.stream().anyMatch((x) -> (x.equals(toViewUniqueName)));
    }

    private void populateNavigationGraph() {
        navigationGraph = new HashMap<>();
        navigationGraph.put(LogInView.VIEW_NAME, Arrays.asList(MainMenuView.VIEW_NAME));
        navigationGraph.put(ProductoVentaListView.VIEW_NAME, Arrays.asList(ProductoVentaDetailView.VIEW_NAME));
        navigationGraph.put(AreaVentaListView.VIEW_NAME, Arrays.asList(AreaDetailView.VIEW_NAME));
        navigationGraph.put(PersonalListView.VIEW_NAME, Arrays.asList(PersonalDetailView.VIEW_NAME));
        navigationGraph.put(PuestoTrabajoListView.VIEW_NAME, Arrays.asList(PuestoTrabajoDetailView.VIEW_NAME));
        navigationGraph.put(InsumoListView.VIEW_NAME, Arrays.asList(InsumoDetailView.VIEW_NAME));
        navigationGraph.put(AlmacenMainView.VIEW_NAME,
                Arrays.asList(InsumoDetailView.VIEW_NAME,
                        FacturaView.VIEW_NAME,
                        TransaccionListView.VIEW_NAME));
        navigationGraph.put(AVAILABLE_EVERYWHERE,
                Arrays.asList(ProductoVentaListView.VIEW_NAME,
                        UbicacionView.VIEW_NAME,
                        InsumoListView.VIEW_NAME,
                        PersonalListView.VIEW_NAME,
                        PuestoTrabajoListView.VIEW_NAME,
                        PuntoElaboracionListView.VIEW_NAME,
                        NominasDetailView.VIEW_NAME,
                        MenuSeccionView.VIEW_NAME,
                        AreaVentaListView.VIEW_NAME,
                        ConfiguracionView.VIEW_NAME,
                        IpvGestionView.VIEW_NAME,
                        VentaDetailView.VIEW_NAME,
                        BackUpView.VIEW_NAME,
                        VentaCalendarView.VIEW_NAME,
                        AlmacenMainView.VIEW_NAME,
                        VentaStatisticsView.VIEW_NAME,
                        AcercaDeView.VIEW_NAME,
                        LogInView.VIEW_NAME,
                        OrdenLogView.VIEW_NAME,
                        ReportarBugView.VIEW_NAME,
                        ImageManagerView.VIEW_NAME,
                        LicenceDialogView.VIEW_NAME
                ));
        navigationGraph.put(VentaDetailView.VIEW_NAME, Arrays.asList(OrdenDetailFragmentView.VIEW_NAME));
    }

}

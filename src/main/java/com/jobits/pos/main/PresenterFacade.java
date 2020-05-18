/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.main;

import com.jobits.pos.controller.insumo.InsumoListController;
import com.jobits.pos.controller.login.MainMenuController;
import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.controller.login.UbicacionConexionController;
import com.jobits.pos.controller.productos.ProductoVentaListController;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionListController;
import com.jobits.pos.controller.trabajadores.NominasController;
import com.jobits.pos.controller.trabajadores.PersonalListController;
import com.jobits.pos.controller.trabajadores.PuestoTrabajoListController;
import com.jobits.pos.ui.View;
import com.jobits.pos.ui.dashboard.presenter.DashboardViewPresenter;
import com.jobits.pos.ui.MainMenuPresenter;
import com.jobits.pos.ui.dashboard.DashBoardView;
import com.jobits.pos.ui.MainMenuView;
import com.jobits.pos.ui.insumo.InsumoListView;
import com.jobits.pos.ui.insumo.presenter.InsumoListViewPresenter;
import com.jobits.pos.ui.login.LogInView;
import com.jobits.pos.ui.login.UbicacionView;
import com.jobits.pos.ui.login.presenter.LoginViewPresenter;
import com.jobits.pos.ui.login.presenter.UbicacionViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.productos.ProductoVentaDetailView;
import com.jobits.pos.ui.productos.ProductoVentaListView;
import com.jobits.pos.ui.productos.presenter.ProductoVentaListViewPresenter;
import com.jobits.pos.ui.puntoelaboracion.PuntoElaboracionListView;
import com.jobits.pos.ui.puntoelaboracion.presenter.PuntoElaboracionListViewPresenter;
import com.jobits.pos.ui.trabajadores.NominasDetailView;
import com.jobits.pos.ui.trabajadores.PersonalListView;
import com.jobits.pos.ui.trabajadores.PuestoTrabajoListView;
import com.jobits.pos.ui.trabajadores.presenter.NominasDetailPresenter;
import com.jobits.pos.ui.trabajadores.presenter.PersonalListViewPresenter;
import com.jobits.pos.ui.trabajadores.presenter.PuestoTrabajoListViewPresenter;

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
                return new LoginViewPresenter(new LogInController());
            case UbicacionView.VIEW_NAME:
                return new UbicacionViewPresenter(new UbicacionConexionController());
            case MainMenuView.VIEW_NAME:
                throw new IllegalStateException("Bad call on view: " + viewUIDName);
            case DashBoardView.VIEW_NAME:
                return new DashboardViewPresenter();
            case ProductoVentaListView.VIEW_NAME:
                return new ProductoVentaListViewPresenter(new ProductoVentaListController());
            case ProductoVentaDetailView.VIEW_NAME:
                throw new IllegalStateException("Bad call on view: " + viewUIDName);
            case InsumoListView.VIEW_NAME:
                return new InsumoListViewPresenter(new InsumoListController());
            case PersonalListView.VIEW_NAME:
                return new PersonalListViewPresenter(new PersonalListController());
            case PuestoTrabajoListView.VIEW_NAME:
                return new PuestoTrabajoListViewPresenter(new PuestoTrabajoListController());
            case NominasDetailView.VIEW_NAME:
                return new NominasDetailPresenter(new NominasController());
            case PuntoElaboracionListView.VIEW_NAME:
                return new PuntoElaboracionListViewPresenter(new PuntoElaboracionListController());
            default:
                throw new IllegalArgumentException("No presenter register for " + viewUIDName);
        }

    }

}

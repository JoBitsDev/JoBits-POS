/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.main;

import com.jobits.pos.main.PresenterFacade;
import com.jobits.pos.ui.MainMenuView;
import com.jobits.pos.ui.View;
import com.jobits.pos.ui.dashboard.DashBoardView;
import com.jobits.pos.ui.insumo.InsumoListView;
import com.jobits.pos.ui.login.LogInView;
import com.jobits.pos.ui.login.UbicacionView;
import com.jobits.pos.ui.presenters.AbstractListViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.productos.ProductoVentaDetailView;
import com.jobits.pos.ui.productos.ProductoVentaListView;
import com.jobits.pos.ui.puntoelaboracion.PuntoElaboracionListView;
import com.jobits.pos.ui.trabajadores.NominasDetailView;
import com.jobits.pos.ui.trabajadores.PersonalListView;
import com.jobits.pos.ui.trabajadores.PuestoTrabajoListView;
import com.jobits.pos.ui.trabajadores.presenter.NominasDetailPresenter;
import javax.swing.JPanel;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class ViewFacade {

    public static View getRootView(AbstractViewPresenter presenter) {
        return new LogInView(PresenterFacade.getPresenterFor(LogInView.VIEW_NAME));
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
             case PersonalListView.VIEW_NAME:
                return new PersonalListView((AbstractListViewPresenter) p);
            case PuestoTrabajoListView.VIEW_NAME:
                return new PuestoTrabajoListView((AbstractListViewPresenter) p);
            case NominasDetailView.VIEW_NAME:
                return new NominasDetailView(p);
            case PuntoElaboracionListView.VIEW_NAME:
                return new PuntoElaboracionListView((AbstractListViewPresenter) p);
           
            default:
                throw new IllegalArgumentException("Vista no registrada para UID " + viewUniqueName);
        }
    }
}

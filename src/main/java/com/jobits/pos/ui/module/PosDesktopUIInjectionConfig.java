/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.jobits.pos.cliente.core.usecase.ClienteUseCase;
import com.jobits.pos.cliente.core.usecase.DireccionEnvioUseCase;
import com.jobits.pos.controller.almacen.AlmacenListService;
import com.jobits.pos.controller.almacen.AlmacenManageService;
import com.jobits.pos.controller.areaventa.AreaDetailService;
import com.jobits.pos.reserva.core.usecase.ReservaUseCase;
import com.jobits.pos.client.webconnection.almacen.AlmacenListWCS;
import com.jobits.pos.client.webconnection.almacen.AlmacenManageWCS;
import com.jobits.pos.client.webconnection.area.AreaVentaWCS;
import com.jobits.pos.client.webconnection.area.MesaWCS;
import com.jobits.pos.client.webconnection.carta.CartaWCS;
import com.jobits.pos.client.webconnection.carta.SeccionWCS;
import com.jobits.pos.client.webconnection.cliente.ClienteWCS;
import com.jobits.pos.client.webconnection.cliente.DireccionEnvioWCS;
import com.jobits.pos.usecase.mesa.ReservaUseCaseRFImpl;
import com.jobits.pos.client.webconnection.configuracion.ConfiguracionWCS;
import com.jobits.pos.client.webconnection.configuracion.ImpresoraWCS;
import com.jobits.pos.client.webconnection.insumo.InsumoWCS;
import com.jobits.pos.client.webconnection.licence.LicenceWCS;
import com.jobits.pos.client.webconnection.login.LoginService;
import com.jobits.pos.client.webconnection.login.LoginWCS;
import com.jobits.pos.client.webconnection.login.model.UbicacionService;
import com.jobits.pos.client.webconnection.login.model.UbicacionServiceImpl;
import com.jobits.pos.client.webconnection.product.ProductoVentaWCS;
import com.jobits.pos.client.webconnection.puntoelaboracion.PuntoElaboracionWCS;
import com.jobits.pos.client.webconnection.trabajadores.PersonalWCS;
import com.jobits.pos.client.webconnection.trabajadores.PuestoTrabajoWCS;
import com.jobits.pos.client.webconnection.venta.VentaWCS;
import com.jobits.pos.controller.areaventa.AreaVentaService;
import com.jobits.pos.controller.areaventa.MesaService;
import com.jobits.pos.controller.configuracion.ConfiguracionService;
import com.jobits.pos.controller.insumo.InsumoService;
import com.jobits.pos.controller.licencia.LicenceService;
import com.jobits.pos.controller.productos.ProductoVentaService;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionService;
import com.jobits.pos.controller.seccion.CartaListService;
import com.jobits.pos.controller.seccion.SeccionListService;
import com.jobits.pos.controller.trabajadores.PersonalUseCase;
import com.jobits.pos.controller.trabajadores.PuestoTrabajoUseCase;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.controller.venta.resumen.VentaResumenUseCase;
import com.jobits.pos.servicios.impresion.ImpresoraService;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
class PosDesktopUIInjectionConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(ReservaUseCase.class).to(ReservaUseCaseRFImpl.class).in(Scopes.SINGLETON);
        bind(AlmacenManageService.class).to(AlmacenManageWCS.class).in(Scopes.SINGLETON);
        bind(AlmacenListService.class).to(AlmacenListWCS.class).in(Scopes.SINGLETON);
        bind(AreaDetailService.class).to(AreaDetailWCS.class).in(Scopes.SINGLETON);
        bind(AreaVentaService.class).to(AreaVentaWCS.class).in(Scopes.SINGLETON);
        bind(MesaService.class).to(MesaWCS.class).in(Scopes.SINGLETON);
        bind(ClienteUseCase.class).to(ClienteWCS.class).in(Scopes.SINGLETON);
        bind(DireccionEnvioUseCase.class).to(DireccionEnvioWCS.class).in(Scopes.SINGLETON);
        //
        //General
        //
        bind(LoginService.class).to(LoginWCS.class).in(Scopes.SINGLETON);
        bind(UbicacionService.class).to(UbicacionServiceImpl.class).in(Scopes.SINGLETON);
        bind(LicenceService.class).to(LicenceWCS.class).in(Scopes.SINGLETON);
        bind(ConfiguracionService.class).to(ConfiguracionWCS.class).in(Scopes.SINGLETON);
        bind(ImpresoraService.class).to(ImpresoraWCS.class).in(Scopes.SINGLETON);

        //
        //Core
        //
        bind(ProductoVentaService.class).to(ProductoVentaWCS.class).in(Scopes.SINGLETON);
        bind(PuntoElaboracionService.class).to(PuntoElaboracionWCS.class).in(Scopes.SINGLETON);
        bind(InsumoService.class).to(InsumoWCS.class).in(Scopes.SINGLETON);
        bind(CartaListService.class).to(CartaWCS.class).in(Scopes.SINGLETON);
        bind(SeccionListService.class).to(SeccionWCS.class).in(Scopes.SINGLETON);
        bind(AreaVentaService.class).to(AreaVentaWCS.class).in(Scopes.SINGLETON);
        bind(MesaService.class).to(MesaWCS.class).in(Scopes.SINGLETON);
        bind(PuestoTrabajoUseCase.class).to(PuestoTrabajoWCS.class).in(Scopes.SINGLETON);
        bind(PersonalUseCase.class).to(PersonalWCS.class).in(Scopes.SINGLETON);

        //
        //Ventas
        //
        bind(VentaDetailService.class).to(VentaWCS.class).in(Scopes.SINGLETON);
        bind(VentaResumenUseCase.class).to(VentaResumenUseCase.class).in(Scopes.SINGLETON);

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.almacen.ipv;

import com.jobits.pos.inventario.core.almacen.domain.Ipv;
import com.jobits.pos.inventario.core.almacen.domain.IpvRegistro;
import com.jobits.pos.inventario.core.almacen.domain.IpvVentaRegistro;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 *
 * @author Jorge
 */
public interface IpvWCI {

    public static final String BASE = "pos/ipv";

    public static final String DAR_ENTRADA_EXISTENCIA_INSUMO_PATH = "/insumos/{idVenta}/{codCocina}/{codInsumo}/entrada/{cantidad}";

    public static final String DAR_ENTRADA_IPV_PATH = "/productos/{idVenta}/{codProducto}/entrada/{cantidad}";

    public static final String AJUSTAR_CONSUMO_PATH = "/insumos/{idVenta}/{codCocina}/{codInsumo}/ajustar-consumo/{cantidad}";

    public static final String AJUSTAR_COSTO_PATH = "/insumos/{idVenta}/{codCocina}/{codInsumo}/ajustar-costo/{cantidad}";

    public static final String TRANSFERIR_IPV_REGISTRO_PATH = "/insumos/{idVenta}/{codCocina}/{codInsumo}/transferir-ipv/{codCocinaTransferir}/{cantidad}";

    public static final String TRANSFERIR_IPV_REGISTRO_TO_ALMACEN_PATH = "/insumos/{idVenta}/{codCocina}/{codInsumo}/transferir-almacen/{codAlmacen}/{cantidad}";

    public static final String REINICIAR_IPV_PATH = "/reiniciar/{idVenta}/{codCocina}";

    public static final String RECALCULAR_IPV_INSUMOS_PATH = "/insumos/{id_venta}/recalcular";

    public static final String RECALCULAR_IPV_VENTAS_PATH = "/productos/{id_venta}/recalcular";

    public static final String HAY_DISPONIBILIDAD_PATH = "/productos/hay-disponibilidad/{id_venta}/{codProducto}/{cantidad}";

    public static final String GET_IPV_REGISTRO_LIST_PATH = "/insumos/{idVenta}/{codCocina}/list";

    public static final String GET_IPV_REGISTRO_VENTA_LIST_PATH = "/productos/{idVenta}/{codCocina}/list";

    public static final String INICIALIZAR_EXISTENCIAS_PATH = "/insumos/{idVenta}/inicializar";

    public static final String INICIALIZAR_IPVS_PATH = "/productos/{idVenta}/inicializar";

    public static final String REGISTRAR_IPVS_PATH = "/insumos/{idVenta}/{codCocina}/registrar/{codInsumo}";

    public static final String DELETE_BY_ID = "/delete/{codCocina}/{codInsumo}";

    @PUT(BASE + DAR_ENTRADA_EXISTENCIA_INSUMO_PATH)
    public Call<IpvRegistro> darEntradaExistencia(@Path("codInsumo") String codInsumo,
            @Path("codCocina") String codCocina, @Path("idVenta") int idVenta, @Path("cantidad") float cantidad);

    @PUT(BASE + DAR_ENTRADA_IPV_PATH)
    public Call<IpvVentaRegistro> darEntradaIPV(@Path("codProducto") String codProductoVenta,
            @Path("idVenta") int codVenta, @Path("cantidad") float cantidad);

    @PUT(BASE + AJUSTAR_CONSUMO_PATH)
    public Call<IpvRegistro> ajustarConsumo(@Path("codInsumo") String codInsumo,
            @Path("codCocina") String codCocina, @Path("idVenta") int idVenta, @Path("cantidad") float cantidad);
    // public Call<IpvRegistro> ajustarConsumo(String codInsumo, String codCocina,
    // int codVenta, float cantidad);

    @PUT(BASE + AJUSTAR_COSTO_PATH)
    public Call<IpvRegistro> ajustarCosto(@Path("codInsumo") String codInsumo,
            @Path("codCocina") String codCocina, @Path("idVenta") int idVenta, @Path("cantidad") float cantidad);

    @PUT(BASE + TRANSFERIR_IPV_REGISTRO_PATH)
    public Call<IpvRegistro> transferirIPVRegistro(@Path("codInsumo") String codInsumo,
            @Path("codCocina") String codCocina, @Path("idVenta") int idVenta, @Path("codCocinaTransferir") String codCocinaDestino, @Path("cantidad") float cantidad);

    @PUT(BASE + TRANSFERIR_IPV_REGISTRO_TO_ALMACEN_PATH)
    public Call<IpvRegistro> transferirIPVRegistroToAlmacen(@Path("codInsumo") String codInsumo,
            @Path("codCocina") String codCocina, @Path("idVenta") int idVenta, @Path("codAlmacen") String codAlmacen,
            @Path("cantidad") float cantidad);

    @PUT(BASE + REINICIAR_IPV_PATH)
    public Call<Boolean> reiniciarIPV(@Path("codCocina") String codCocina, @Path("idVenta") int idVenta);

    @PUT(BASE + RECALCULAR_IPV_INSUMOS_PATH)
    public Call<Boolean> recalcularIPVInsumos(@Path("id_venta") int idVenta);

    @PUT(BASE + RECALCULAR_IPV_VENTAS_PATH)
    public Call<Boolean> recalcularIPVVentas(@Path("id_venta") int idVenta);

    @GET(BASE + HAY_DISPONIBILIDAD_PATH)
    public Call<Boolean> hayDisponibilidad(@Path("id_venta") int idVenta, @Path("codProducto") String codProducto,
            @Path("cantidad") float cantidad);

    @GET(BASE + GET_IPV_REGISTRO_LIST_PATH)
    public Call<List<IpvRegistro>> getIPVRegistroList(@Path("idVenta") int idVenta,
            @Path("codCocina") String codCocina);

    @GET(BASE + GET_IPV_REGISTRO_VENTA_LIST_PATH)
    public Call<List<IpvVentaRegistro>> getIPVRegistroVentaList(@Path("idVenta") int idVenta,
            @Path("codCocina") String codCocina);

    @PUT(BASE + INICIALIZAR_EXISTENCIAS_PATH)
    public Call<List<IpvRegistro>> inicializarExistencias(@Path("idVenta") int idVenta);

    @PUT(BASE + INICIALIZAR_IPVS_PATH)
    public Call<List<IpvVentaRegistro>> inicializarIPVS(@Path("idVenta") int idVenta);

    @PUT(BASE + REGISTRAR_IPVS_PATH)
    public Call<IpvRegistro> registrarIPVS(@Path("idVenta") int idVenta, @Path("codCocina") String codCocina,
            @Path("codInsumo") String codInsumo);

    public Call<Ipv> create(Ipv t) throws RuntimeException;

    public Call<Ipv> edit(Ipv t) throws RuntimeException;

    public Call<Ipv> destroy(Ipv t) throws RuntimeException;

    @DELETE(BASE + DELETE_BY_ID)
    public Call<Ipv> destroyById(
            @Path("codCocina") String codCocina,
            @Path("codInsumo") String codInsumo);

    public Call<Ipv> findBy(Object o) throws RuntimeException;

    public Call<List<Ipv>> findAll() throws RuntimeException;

}

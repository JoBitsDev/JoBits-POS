/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.almacen;

import com.jobits.pos.inventario.core.almacen.domain.InsumoAlmacen;
import com.jobits.pos.inventario.core.almacen.domain.Operacion;
import com.jobits.pos.inventario.core.almacen.domain.Transaccion;
import com.jobits.pos.inventario.core.almacen.domain.TransaccionTransformacion;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 *
 * @author Jorge
 */
public interface TransaccionWCI {

    public static final String BASE = "pos";

    public static final String FIND_ALL_BY_ALMACEN_PATH = "/almacen/{id}/list-transacciones";
    public static final String FIND_ALL_BY_ALMACEN_MERMA_PATH = "/almacen/{id}/list-transacciones-merma";
    public static final String PRINT_PATH = "/transacciones/print";

    @PUT(BASE + PRINT_PATH)
    public Call<Void> imprimirTransaccionesSeleccionadas(@Body List<Transaccion> selectedsObjects);

    @GET(BASE + FIND_ALL_BY_ALMACEN_PATH)
    public Call<List<Transaccion>> findAllByAlmacen(@Path("id") String codAlmacen);

    @GET(BASE + FIND_ALL_BY_ALMACEN_MERMA_PATH)
    public Call<List<Transaccion>> findMermasByAlmacen(@Path("id") String codAlmacen);

}

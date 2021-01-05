/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.almacen;

import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.InsumoAlmacen;
import com.jobits.pos.domain.models.Operacion;
import com.jobits.pos.domain.models.Transaccion;
import com.jobits.pos.domain.models.TransaccionEntrada;
import com.jobits.pos.domain.models.TransaccionMerma;
import com.jobits.pos.domain.models.TransaccionSalida;
import com.jobits.pos.domain.models.TransaccionTransformacion;
import java.awt.Container;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Home
 */
public interface TransaccionDetailService {

    void addTransaccionRebaja(Operacion o, Insumo insumo, Date fecha, Date hora, Almacen a, float cantidad, String causaRebaja);

    Transaccion addTransaccionSalida(Operacion o, Insumo insumo, Date fecha, Date hora, Almacen a, Cocina cocina, float cantidad, int idVenta);

    void addTransaccionTransformacion(InsumoAlmacen selected, Date fecha, Date hora, List<TransaccionTransformacion> items, float cantidad, float merma, Almacen destino);

    void addTransaccionTraspaso(Operacion o, Insumo insumo, Date fecha, Date hora, Almacen a, Almacen destinoTraspaso, float cantidad);

    void constructView(Container parent);

    Transaccion createNewInstance();

    void createNewTransaccionEntrada(TransaccionEntrada transaccion, Almacen a);

    void createNewTransaccionRebaja(TransaccionMerma transaccion, Almacen a);

    void createNewTransaccionSalida(TransaccionSalida transaccion, Almacen a, int idVenta);

    List<Cocina> getCocinaList();

    List<Insumo> getInsumoList();
    
}

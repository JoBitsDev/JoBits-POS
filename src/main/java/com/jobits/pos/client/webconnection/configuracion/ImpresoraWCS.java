/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.configuracion;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.servicios.impresion.Impresora;
import com.jobits.pos.servicios.impresion.ImpresoraService;
import java.util.List;
import javax.print.Doc;
import javax.print.PrintException;

/**
 *
 * @author Jorge
 */
public class ImpresoraWCS extends BaseConnection implements ImpresoraService {

    ImpresoraWCI s = retrofit.create(ImpresoraWCI.class);

    @Override
    public Impresora agregarImpresora(Impresora impresora) {
        return handleCall(s.agregarImpresora(impresora));
    }

    @Override
    public Impresora updateImpresora(Impresora impresora) {
        return handleCall(s.updateImpresora(impresora));
    }

    @Override
    public Impresora deleteImpresora(int impresora) {
        return handleCall(s.deleteImpresora(impresora));
    }

    @Override
    public Impresora findBy(String nombreVirtualImpresora) {
        return handleCall(s.findBy(nombreVirtualImpresora));
    }

    @Override
    public List<Impresora> findAll() {
        return handleCall(s.findAll());
    }

    @Override
    public void imprimirEnGrupo(String nombreGrupo, Doc docToPrint) throws PrintException {
        throw new UnsupportedOperationException("En desarrollo");
    }

    @Override
    public List<String> getNombreImpresorasSistema() {
        return handleCall(s.getNombreImpresorasSistema());
    }

    @Override
    public List<String> getNombreGrupos() {
        return handleCall(s.getNombreGrupos());
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.servicios.impresion;

import com.jobits.pos.domain.models.volatil.Impresora;
import java.util.List;
import javax.print.Doc;
import javax.print.PrintException;

/**
 *
 * @author Javier
 */
public interface ImpresoraService {

    public Impresora crear(Impresora impresora);

    public void update(Impresora impresora);

    public Impresora delete(Impresora impresora);

    public Impresora findBy(String nombreVirtualImpresora);

    public List<Impresora> findAll();

    public void imprimirEnGrupo(String nombreGrupo, Doc docToPrint) throws PrintException;

    public List<String> getNombreImpresorasSistema();
    
    public List<String> getNombreGrupos();

}

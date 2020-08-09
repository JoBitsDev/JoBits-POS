/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.servicios.impresion;

import java.util.Objects;
import javax.print.Doc;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

/**
 *
 * @author ERIK QUESADA
 */
public class Impresora {

    private int idImpresora;
    private String nombreImpresoraVirtual;
    private String nombreImpresoraSistema;
    private String grupo;
    boolean porDefecto;

    public Impresora() {
    }

    public Impresora(String nombreImpresoraVirtual, String nombreImpresoraSistema, String grupo, boolean porDefecto) {
        this.nombreImpresoraVirtual = nombreImpresoraVirtual;
        this.nombreImpresoraSistema = nombreImpresoraSistema;
        this.grupo = grupo;
        this.porDefecto = porDefecto;
    }

    public Impresora(int idImpresora, String nombreImpresoraVirtual, String nombreImpresoraSistema, String grupo, boolean porDefecto) {
        this.idImpresora = idImpresora;
        this.nombreImpresoraVirtual = nombreImpresoraVirtual;
        this.nombreImpresoraSistema = nombreImpresoraSistema;
        this.grupo = grupo;
        this.porDefecto = porDefecto;
    }

    public int getIdImpresora() {
        return idImpresora;
    }

    public void setIdImpresora(int idImpresora) {
        this.idImpresora = idImpresora;
    }

    public String getNombreImpresoraVirtual() {
        return nombreImpresoraVirtual;
    }

    public void setNombreImpresoraVirtual(String nombreImpresoraVirtual) {
        this.nombreImpresoraVirtual = nombreImpresoraVirtual;
    }

    public String getNombreImpresoraSistema() {
        return nombreImpresoraSistema;
    }

    public void setNombreImpresoraSistema(String nombreImpresoraSistema) {
        this.nombreImpresoraSistema = nombreImpresoraSistema;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public boolean isPorDefecto() {
        return porDefecto;
    }

    public void setPorDefecto(boolean porDefecto) {
        this.porDefecto = porDefecto;
    }

    public void imprimir(Doc doc) throws PrintException {
        PrintService[] systemPrinter = PrintServiceLookup.lookupPrintServices(null, null);

        for (PrintService printService : systemPrinter) {
            if (printService.getName().equals(getNombreImpresoraSistema())) {
                printService.createPrintJob().print(doc, null);
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Impresora other = (Impresora) obj;
        if (this.idImpresora != other.getIdImpresora()) {
            return false;
        }
        if (this.porDefecto != other.porDefecto) {
            return false;
        }
        if (!Objects.equals(this.nombreImpresoraVirtual, other.nombreImpresoraVirtual)) {
            return false;
        }
        if (!Objects.equals(this.nombreImpresoraSistema, other.nombreImpresoraSistema)) {
            return false;
        }
        if (!Objects.equals(this.grupo, other.grupo)) {
            return false;
        }
        return true;
    }

}

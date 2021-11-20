/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.mainmenu;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public enum MenuButtons {

    // 
    //Productos de Venta 
    // 
    MENU(1, "Productos"),
    INSUMO(3, "Insumos"),
    COCINA(3, "Puntos de Elaboracion"),
    SECCION(3, "Cartas"),
    SALON(3, "Areas de Venta"),
    // 
    // Almacen 
    // 
    ALMACEN(2, "Almacenes"),
    ACTIVOS(2),
    IPV(2, "IPV"),
    // 
    //Contabilidad 
    // 
    VENTAS(3, "Ventas"),
    ESTADISTICAS(3, "Estadisticas"),
    CUENTAS_CONTABLES(4),
    PRESUPUESTO(4),
    COMENZAR_VENTAS(0, "Comenzar Turno"),
    CLIENTES(3, "Clientes"),
    RESUMEN(3, "Resumenes"),
    RESERVAS(2, "Reservas"),
    // 
    //TRABAJADORES 
    // 

    TRABAJADORES(4, "Trabajadores"),
    PUESTOS_TRABAJO(4, "Puestos de trabajos"),
    NOMINAS(3, "Nominas"),
    // 
    //CONFIGURACION 
    // 

    COPIA_SEG(4, "Copias de seguridad"),
    LICENCIA(0, "Licencia"),
    CONFIGURACION(5, "Configuración");

    private final int nivelMinimoAcceso;
    private final String nombreVisible;

    private MenuButtons(int nivelMinimoAcceso) {
        this.nivelMinimoAcceso = nivelMinimoAcceso;
        this.nombreVisible = name();
    }

    private MenuButtons(int nivelMinimoAcceso, String nombreVisible) {
        this.nivelMinimoAcceso = nivelMinimoAcceso;
        this.nombreVisible = nombreVisible;
    }

    public int getNivelMinimoAcceso() {
        return nivelMinimoAcceso;
    }

    @Override
    public String toString() {
        return nombreVisible;
    }

}

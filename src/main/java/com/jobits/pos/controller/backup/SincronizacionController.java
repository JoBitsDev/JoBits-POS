/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.backup;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jobits.pos.controller.configuracion.ConfiguracionController;
import com.jobits.pos.controller.login.UbicacionConexionController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.domain.UbicacionConexionModel;
import com.jobits.pos.recursos.DBConnector;
import com.jobits.pos.recursos.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class SincronizacionController {

    private boolean habilitado;
    private UbicacionConexionModel ubicacion;
    private int tiempoLoop = 60 * 1000;
    private final Timer syncTask = new Timer(true);

    public SincronizacionController() {
        cargarConfiguracion();
        if (habilitado) {
            if (R.CURRENT_CONNECTION.getTipoUbicacion() == UbicacionConexionModel.TipoUbicacion.MASTER) {
                iniciarTask();
            }
        }

    }

    private void cargarConfiguracion() throws IllegalArgumentException, NumberFormatException {
        ConfiguracionController config = new ConfiguracionController();
        habilitado = (boolean) config.getConfiguracion(R.SettingID.SINCRONIZACION_HABILITAR);
        if (habilitado) {

            int ubicacionIndex = Integer.parseInt(config.getConfiguracion(R.SettingID.SINCRONIZACION_UBICACION).toString());
            UbicacionConexionController ubicaciones = new UbicacionConexionController();
            tiempoLoop = Integer.parseInt(config.getConfiguracion(R.SettingID.SINCRONIZACION_TIEMPO_LOOP).toString());
            if (ubicacionIndex >= 0) {
                ubicacion = ubicaciones.getUbicaciones().getUbicaciones()[ubicacionIndex];
                if (ubicacion.getTipoUbicacion() != UbicacionConexionModel.TipoUbicacion.SINCRONIZACION) {
                    throw new IllegalArgumentException("La sincronizacion solo se puede"
                            + " realizar a ubicaciones de tipo sincronizacion");
                }
            }
        }
    }

    private void iniciarTask() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                sincronizarDatosConServidor();
            }
        };
        syncTask.scheduleAtFixedRate(task, 0, tiempoLoop);
    }

    private void sincronizarDatosConServidor() {
        BackUpService backupService = new BackUpService(ubicacion);
        backupService.incluirDiaAbierto(true);
        Logger.getLogger(SincronizacionController.class.getName()).log(Level.INFO, "Ejecutando sincronizacion con servidor");
        String logRespuesta = "Sincronizacion con servidor completada ";
        if (backupService.EjecutarBackUpLineal(BackUpService.TipoBackUp.VENTA)) {
            logRespuesta += "exitosamente";
        } else {
            logRespuesta += "con errores";
        }
        Logger.getLogger(SincronizacionController.class.getName()).log(Level.INFO, logRespuesta);
    }

    @Override
    protected void finalize() throws Throwable {
        terminarSincronizacion();
        super.finalize(); //To change body of generated methods, choose Tools | Templates.

    }

    public void terminarSincronizacion() {
        Logger.getLogger(SincronizacionController.class.getName()).log(Level.INFO, "Limpiando Task");
        syncTask.cancel();
    }

}

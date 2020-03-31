/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.backup;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import restManager.controller.configuracion.ConfiguracionController;
import restManager.controller.login.UbicacionConexionController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.volatil.UbicacionConexionModel;
import restManager.resources.DBConnector;
import restManager.resources.R;

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
        BackUp backupService = new BackUp(ubicacion);
        backupService.incluirDiaAbierto(true);
        Logger.getLogger(SincronizacionController.class.getName()).log(Level.INFO, "Ejecutando sincronizacion con servidor");
        backupService.EjecutarBackUpLineal(BackUp.TipoBackUp.PERSONAL);
        backupService.EjecutarBackUpLineal(BackUp.TipoBackUp.PRODUCTOS);
        backupService.EjecutarBackUpLineal(BackUp.TipoBackUp.VENTA);
        Logger.getLogger(SincronizacionController.class.getName()).log(Level.INFO, "Sincronizacion con servidor completada");
    }

    @Override
    protected void finalize() throws Throwable {
        Logger.getLogger(SincronizacionController.class.getName()).log(Level.INFO, "Limpiando Task por JVM");
        syncTask.cancel();
        super.finalize(); //To change body of generated methods, choose Tools | Templates.

    }

}

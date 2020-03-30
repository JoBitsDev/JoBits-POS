/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.resources;

import GUI.Views.View;
import java.util.HashMap;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.volatil.UbicacionConexionModel;

/**
 *
 * @author Jorge
 */
public class DBConnector {

    private static boolean CONECTADO;

    private static final String persistenceUnitName = "DATA_SOURCE";

    private static final HashMap<String, String> properties = new HashMap<>();


    private static final String URL = "javax.persistence.jdbc.url",
            USER = "javax.persistence.jdbc.user",
            DRIVER = "javax.persistence.jdbc.driver",
            PASSWORD = "javax.persistence.jdbc.password";

    private DBConnector(UbicacionConexionModel connectionProperties) {
        if (connectionProperties.equals(R.CURRENT_CONNECTION)) {
            return;
        }
        properties.put(URL, connectionProperties.getUrl());
        properties.put(USER, connectionProperties.getUsuario());
        properties.put(PASSWORD, connectionProperties.getContrasena());
        properties.put(DRIVER, connectionProperties.getDriver());
        R.CURRENT_CONNECTION = connectionProperties;
        AbstractModel.setEMF(Persistence.createEntityManagerFactory(persistenceUnitName, properties));
        if (AbstractModel.getEMF() != null) {
            initConnections();

        } else {
            throw new NullPointerException(R.RESOURCE_BUNDLE.getString("null_pointer_EMF_not_Found"));
        }
    }

    private DBConnector(HashMap<String, String> properties) {
        AbstractModel.setEMF(Persistence.createEntityManagerFactory(persistenceUnitName, properties));
        if (AbstractModel.getEMF() != null) {
            initConnections();

        } else {
            throw new NullPointerException(R.RESOURCE_BUNDLE.getString("null_pointer_EMF_not_Found"));
        }
    }

    public static EntityManagerFactory createEmfFrom(UbicacionConexionModel connectionProperties) {
        HashMap<String, String> prop = new HashMap<>();
        prop.put(URL, connectionProperties.getUrl());
        prop.put(USER, connectionProperties.getUsuario());
        prop.put(PASSWORD, connectionProperties.getContrasena());
        prop.put(DRIVER, connectionProperties.getDriver());
        return Persistence.createEntityManagerFactory(persistenceUnitName, prop);
    }

    public static boolean isCONECTADO() {
        return CONECTADO;
    }

    public static void init(UbicacionConexionModel connection) {
        new DBConnector(connection);
    }

    public static void resetConnection(View view) {
        new DBConnector(properties);
    }

    private void initConnections() {
        try {
            AbstractModel.getEMF().getCache().evictAll();
            AbstractModel.setCurrentConnection(AbstractModel.getEMF().createEntityManager());
            CONECTADO = true;
        } catch (Exception e) {
            CONECTADO = false;
            System.out.println(e.getMessage());
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.recursos;

import com.jobits.pos.ui.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.adapters.repo.AbstractModel;
import com.jobits.pos.domain.UbicacionConexionModel;

/**
 *
 * @author Jorge
 */
public class DBConnector {

    private static boolean CONECTADO;

    private static final String persistenceUnitName = "DATA_SOURCE";

    private static final String URL = "javax.persistence.jdbc.url",
            USER = "javax.persistence.jdbc.user",
            DRIVER = "javax.persistence.jdbc.driver",
            PASSWORD = "javax.persistence.jdbc.password";

    private static List<EntityManagerFactoryCache> cachedEmf = new ArrayList<>();

    private DBConnector(UbicacionConexionModel connectionProperties) {
        if (connectionProperties.equals(R.CURRENT_CONNECTION)) {
            return;
        }
        AbstractModel.setEMF(getEmfFrom(connectionProperties));
        R.CURRENT_CONNECTION = connectionProperties;
        if (AbstractModel.getEMF() != null) {
            initConnections();

        } else {
            throw new NullPointerException(R.RESOURCE_BUNDLE.getString("null_pointer_EMF_not_Found"));
        }
    }

    public static EntityManagerFactory createEmfFrom(UbicacionConexionModel connectionProperties) {
        return getEmfFrom(connectionProperties);
    }

    public static boolean isCONECTADO() {
        return CONECTADO;
    }

    public static void init(UbicacionConexionModel connection) {
        new DBConnector(connection);
    }

    public static void resetConnection(View view) {
        new DBConnector(R.CURRENT_CONNECTION);
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

    private static EntityManagerFactory getEmfFrom(UbicacionConexionModel connectionsProperties) {
        for (EntityManagerFactoryCache cache : cachedEmf) {
            if (cache.getUbicaicon().equals(connectionsProperties)) {
                return cache.getFactory();
            }
        }
        EntityManagerFactory newFactory = Persistence.createEntityManagerFactory(persistenceUnitName, getConnectionsPropeties(connectionsProperties));
        EntityManagerFactoryCache cacheItem = new EntityManagerFactoryCache(newFactory, connectionsProperties);
        cachedEmf.add(cacheItem);
        return newFactory;

    }

    private static HashMap<String, String> getConnectionsPropeties(UbicacionConexionModel connectionProperties) {
        HashMap<String, String> prop = new HashMap<>();
        prop.put(URL, connectionProperties.getUrl());
        prop.put(USER, connectionProperties.getUsuario());
        prop.put(PASSWORD, connectionProperties.getContrasena());
        prop.put(DRIVER, connectionProperties.getDriver());
        return prop;
    }

    private static class EntityManagerFactoryCache {

        private final EntityManagerFactory factory;
        private final UbicacionConexionModel ubicaicon;

        public EntityManagerFactoryCache(EntityManagerFactory factory, UbicacionConexionModel ubicaicon) {
            this.factory = factory;
            this.ubicaicon = ubicaicon;
        }

        public EntityManagerFactory getFactory() {
            return factory;
        }

        public UbicacionConexionModel getUbicaicon() {
            return ubicaicon;
        }

    }

}

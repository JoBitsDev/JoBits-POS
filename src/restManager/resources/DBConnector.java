/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.resources;


import GUI.Views.View;
import javax.persistence.Persistence;
import restManager.persistencia.models.AbstractModel;

/**
 *
 * @author Jorge
 */
public class DBConnector {

    private static boolean CONECTADO;

    private static String persistenceUnitName;

    private DBConnector(String persistenceUnitName) {
        DBConnector.persistenceUnitName = persistenceUnitName;
        AbstractModel.setEMF(Persistence.createEntityManagerFactory(persistenceUnitName));
        if (AbstractModel.getEMF() != null) {
            initConnections();

        } else {
            throw new NullPointerException(R.RESOURCE_BUNDLE.getString("null_pointer_EMF_not_Found"));
        }
    }

    public static boolean isCONECTADO() {
        return CONECTADO;
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

    public static DBConnector init(String persistenceUnitName) {
        return new DBConnector(persistenceUnitName);
    }
    
    public static DBConnector resetConnection(View view){
        return new DBConnector(persistenceUnitName);
    }

    public static String getPersistenceUnitName() {
        return persistenceUnitName;
    }

}

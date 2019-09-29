/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.jpa;

import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;
import restManager.persistencia.models.AbstractModel;
import restManager.resources.R;

/**
 *
 * @author Jorge
 */
public class staticContent {

    private static boolean CONECTADO;

    private static String persistenceUnitName;

    private staticContent(String persistenceUnitName) {
        staticContent.persistenceUnitName = persistenceUnitName;
        AbstractModel.setEMF(Persistence.createEntityManagerFactory(persistenceUnitName));
        if (AbstractModel.getEMF() != null) {
            initJPAConnections();

        } else {
            throw new NullPointerException(R.RESOURCE_BUNDLE.getString("null_pointer_EMF_not_Found"));
        }
    }

    public static boolean isCONECTADO() {
        return CONECTADO;
    }

    private void initJPAConnections() {

        try {
            AbstractModel.setCurrentConnection(AbstractModel.getEMF().createEntityManager());
            CONECTADO = true;
        } catch (Exception e) {
            CONECTADO = false;
            System.out.println(e.getMessage());
        }

    }

    public static staticContent init(String persistenceUnitName) {
        return new staticContent(persistenceUnitName);
    }

    public static String getPersistenceUnitName() {
        return persistenceUnitName;
    }

}

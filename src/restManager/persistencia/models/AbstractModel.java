package restManager.persistencia.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;
import restManager.controller.AbstractController.PersistAction;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.ExceptionHandler;
import restManager.persistencia.AsistenciaPersonal;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 * @param <T>
 *
 */
public abstract class AbstractModel<T> implements Model {

    private final Class<T> entityClass;
    private static EntityManagerFactory EMF;
    private static EntityManager currentConnection;
    protected PropertyChangeSupport propertyChangeSupport;
    private static String persistenceUnitName = null;

    public AbstractModel(Class<T> entityClass) {
        this.entityClass = entityClass;
        propertyChangeSupport = new PropertyChangeSupport(this);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(R.PERIRSTENCE_UNIT_NAME);

        if (!R.PERIRSTENCE_UNIT_NAME.equals(persistenceUnitName)) {
            EMF = emf;
            currentConnection = EMF.createEntityManager();
            persistenceUnitName = R.PERIRSTENCE_UNIT_NAME;
        }
//        else {
//            Logger l = Logger.getLogger(getClass().getName());
//            l.log(Level.WARNING, R.RESOURCE_BUNDLE.getString("null_pointer_EMF_not_found") + "" + getClass().getName());
//        }
    }

    public EntityManager getEntityManager() {
        return currentConnection;
    }

    public void startTransaction() {
        if (!getEntityManager().getTransaction().isActive()) {
            getEntityManager().getTransaction().begin();
        }
    }

    public void commitTransaction() {
        if (getEntityManager().getTransaction().isActive()) {
            try {
                // getEntityManager().flush();
                getEntityManager().getTransaction().commit();
            } catch (PersistenceException e) {
                JOptionPane.showMessageDialog(null, "Los datos no se archivaron en la base de datos", "Error", JOptionPane.OK_OPTION);
                getEntityManager().getTransaction().rollback();
            }
        }
    }

    public void refresh(T a) {
        getEntityManager().refresh(a);
    }

    public void create(T entity) throws EntityExistsException {
        persist(PersistAction.CREATE, entity);
    }

    public void edit(T entity) {
        persist(PersistAction.UPDATE, entity);
    }

    public void remove(T entity) {
        persist(PersistAction.DELETE, entity);
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        try {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            return new ArrayList<>(getEntityManager().createQuery(cq).getResultList());
        } catch (Exception e) {
            getEntityManager().getTransaction().rollback();
            return new ArrayList<>(findAll());
        }
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    /**
     * Tu generate IDs for the relational model of the application
     *
     * @param prefix includign the '-' char ej: "P-"
     * @return
     */
    public String generateStringCode(String prefix) {
        int cont = 1;
        T a = find(prefix + "" + cont);
        while (a != null) {
            cont++;
            a = find(prefix + "" + cont);
        }

        return prefix + "" + cont;
    }

    /**
     * Tu generate IDs for the relational model of the application
     *
     * @return
     */
    public int generateIDCode() {
        int cont = 0;
        T a = find(cont);
        while (a != null) {
            cont++;
        }
        return cont;
    }

    public int generate(String idName) {
        return new ConfigDAO().generateNewId(idName);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public static EntityManagerFactory getEMF() {
        return EMF;
    }

    public static void setEMF(EntityManagerFactory EMF) {
        AbstractModel.EMF = EMF;
    }

    public static EntityManager getCurrentConnection() {
        return currentConnection;
    }

    public static void setCurrentConnection(EntityManager currentConnection) {
        AbstractModel.currentConnection = currentConnection;
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        this.propertyChangeSupport = propertyChangeSupport;
    }

    public static String getPersistenceUnitName() {
        return persistenceUnitName;
    }

    public static void setPersistenceUnitName(String persistenceUnitName) {
        AbstractModel.persistenceUnitName = persistenceUnitName;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    //
    // Private Methods
    //
    private void persist(PersistAction persistAction, T entity) {

        try {
            switch (persistAction) {
                case CREATE:
                    getEntityManager().persist(entity);
                    firePropertyChange(PropertyName.CREATE.toString(), null, entity);
                    break;
                case DELETE:
                    getEntityManager().remove(getEntityManager().merge(entity));
                    firePropertyChange(PropertyName.DELETE.toString(), entity, null);
                    break;
                case UPDATE:
                    getEntityManager().merge(entity);
                    firePropertyChange(PropertyName.UPDATE.toString(), entity, entity);
                    break;
            }
            getEntityManager().getEntityManagerFactory().getCache().evict(entityClass);
        } catch (Exception e) {
            ExceptionHandler.showExceptionToUser(e, "Error en base de datos \n " + e.getLocalizedMessage());
            if (getEntityManager().getTransaction().isActive()) {
                getEntityManager().getTransaction().rollback();
            }

        }
    }

}

package restManager.persistencia.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import org.postgresql.util.PSQLException;
import restManager.controller.AbstractDialogController;
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
        } else {
            Logger l = Logger.getLogger(getClass().getName());
            l.log(Level.WARNING, R.RESOURCE_BUNDLE.getString("null_pointer_EMF_not_found") +""+ getClass().getName());
        }
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
            getEntityManager().flush();
            getEntityManager().getTransaction().commit();

        }
    }

    public void create(T entity) {
        getEntityManager().persist(entity);
        firePropertyChange(PropertyName.CREATE.toString(), null, entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
        firePropertyChange(PropertyName.UPDATE.toString(), entity, entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        firePropertyChange(PropertyName.DELETE.toString(), entity, null);
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        try{
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
        }catch(Exception e){
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

}

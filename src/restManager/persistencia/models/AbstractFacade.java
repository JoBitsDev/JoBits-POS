package restManager.persistencia.models;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 * @param <T>
 *
 */
public abstract class AbstractFacade<T> {

    private final Class<T> entityClass;
    private static EntityManagerFactory EMF;
    private static EntityManager currentConnection;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(R.PERIRSTENCE_UNIT_NAME);
        if (!emf.equals(EMF)) {
            EMF = emf;
            currentConnection = EMF.createEntityManager();
        } else {
            Logger l = Logger.getLogger(getClass().getName());
            l.log(Level.WARNING, R.RESOURCE_BUNDLE.getString("null_pointer_EMF_not_Found"));
        }
    }

    public EntityManager getEntityManager() {
        return currentConnection;
    }

    public void startTransaction() {
            getEntityManager().getTransaction().begin();
    }

    public void commitTransaction() {
            getEntityManager().getTransaction().commit();
    }

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
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
     * @param prefix
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
    
    public int generate(String idName){
        return new ConfigDAO().generateNewId(idName);
    }

}

package inatel.eribox.test.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

public class GenericDAO<T, ID extends Serializable> {

    @PersistenceContext
    protected EntityManager entityManager;

    private final Class<T> oClass;

    public Class<T> getObjectClass() {
        return this.oClass;
    }

    @SuppressWarnings("unchecked")
    public GenericDAO() {
        /*Set class type to oClass attribute*/
        this.oClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public T searchParam(String query, Map<String, Object> params) {
        Query q = entityManager.createQuery(query);
        for (String key : params.keySet()) {
            q.setParameter(key, params.get(key));
        }
        return (T) q.getSingleResult();
    }

    public T findById(ID id) {
        return entityManager.find(oClass, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<T> listSearchParam(String query, Map<String, Object> params) {
        Query q = entityManager.createQuery(query);
        if (params != null) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.getResultList();
    }
}

package inatel.eribox.test.dao;

import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProductDAO extends GenericDAO<inatel.eribox.test.entity.Product, Integer> {

    @Transactional
    public void saveProduct(Object product) {
        try {
            entityManager.persist(product);
            
            flushAndClear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void updateProduct(Object product) {
        try {
            entityManager.merge(product);
            
            flushAndClear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void removeProduct(Long id, Class clazz) {
        try {
            entityManager.remove(entityManager.find(clazz, id));
            
            flushAndClear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }

    @Transactional
    public List getAllProducts() {
        return entityManager.createQuery("SELECT p FROM Product p ORDER BY id DESC").getResultList();
    }
}

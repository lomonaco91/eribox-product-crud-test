package inatel.eribox.test.service.impl;

import inatel.eribox.test.dao.ProductDAO;
import inatel.eribox.test.service.ProductService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    // Injeção ao PRODUTO DAO
    @Autowired
    private ProductDAO productDAO;

    @Override
    public boolean save(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("The informed object cannot be null");
        }

        productDAO.saveProduct(o);

        return true;
    }

    public List getAll() {
        return productDAO.getAllProducts();
    }

    @Override
    public boolean remove(Long id, Class clazz) {
        if (id == null) {
            throw new IllegalArgumentException("The informed object id cannot be null");
        }

        productDAO.removeProduct(id, clazz);

        return true;
    }

    @Override
    public boolean update(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("The informed object cannot be null");
        }

        productDAO.updateProduct(o);

        return true;
    }

    @Override
    public Object searchParam(String query, Map<String, Object> params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

package inatel.eribox.test.service;

import java.util.Map;

public interface ProductService {
    public boolean save(Object o);
    public boolean remove(Long id, Class clazz);
    public boolean update(Object o);
    public Object searchParam(String query, Map<String, Object> params);
    public Object findById(Long id);
}

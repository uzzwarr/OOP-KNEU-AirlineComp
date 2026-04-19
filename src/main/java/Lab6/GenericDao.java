package Lab6;

import java.util.List;

    public interface GenericDao<T> {
        void save(T entity);
        T findById(Long id);
        List<T> findAll();
        void update(T entity);
        void delete(T entity);

        List<T> findByNativeQuery(String sql);
        List<T> findByHqlQuery(String sql);
    }

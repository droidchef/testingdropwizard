package me.negotiatewith.app.db.dao.api;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface BaseDao<T, ID extends Serializable> {

    T saveEntity(T entity);

    T fetchEntity(ID id);

    Class<T> getEntityClass();

    EntityManager getEntityManager();

    List<T> findAll();

    List<T> findByQuery(Integer firstResult, Integer maxResults, String queryName, Object... params);

    List<T> findByQueryAndNamedParams(Integer firstResult, Integer maxResults, String queryName, Map<String, ? extends Object> params);

    List<T> findByExample(T exampleInstance);

    int countAll();

    int countByExample(T exampleInstance);

}

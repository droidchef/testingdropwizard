package me.negotiatewith.app.db.dao.jpa;

import com.google.inject.Inject;
import me.negotiatewith.app.db.dao.api.BaseDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


public class BaseDaoJpaImpl<T, ID extends Serializable> implements BaseDao<T, ID> {

    private final Provider<EntityManager> emf;
    protected Class<T> entityClass;

    @Inject
    public BaseDaoJpaImpl(Provider<EntityManager> entityManagerProvider) {
        this.emf = entityManagerProvider;
    }

    @Override
    public T saveEntity(T entity) {
        EntityManager em = getEntityManager();
        if (em.contains(entity) ) {
            em.merge(entity);
        } else {
            em.persist(entity);
        }
        em.flush();
        return entity;
    }

    @Override
    public T fetchEntity(final ID id) {
        EntityManager em = getEntityManager();
        T entity = em.find(getEntityClass(), id);
        em.flush();
        return entity;
    }

    @Override
    public EntityManager getEntityManager() {
        return emf.get();
    }

    @Override
    public Class<T> getEntityClass() {
        if (entityClass == null) {
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                ParameterizedType paramType = (ParameterizedType) type;
                entityClass = (Class<T>) paramType.getActualTypeArguments()[0];
            } else {
                throw new IllegalArgumentException("Could not guess entity class by reflection");
            }
        }
        return entityClass;
    }

    @Override
    public List<T> findAll() {
        return findByCriteria();
    }

    @Override
    public List<T> findByQuery(final Integer firstResult, final Integer maxResults, final String queryStr, Object... params) {
        Query query = getEntityManager().createQuery(queryStr);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }

        if(firstResult != null) query.setFirstResult(firstResult);
        if(maxResults != null) query.setMaxResults(maxResults);
        final List<T> result = (List<T>) query.getResultList();
        return result;
    }

    @Override
    public List<T> findByQueryAndNamedParams(final Integer firstResult, final Integer maxResults, @NotNull final String queryStr, @NotNull final Map<String, ?> params) {
        Query query = getEntityManager().createQuery(queryStr);
        for (final Map.Entry<String, ? extends Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        if(firstResult != null) query.setFirstResult(firstResult);
        if(maxResults != null) query.setMaxResults(maxResults);
        final List<T> result = (List<T>) query.getResultList();
        return result;
    }

    @Override
    public List<T> findByExample(final T exampleInstance) {
        Session session = (Session) getEntityManager().getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());
        final List<T> result = crit.list();
        return result;
    }

    protected List<T> findByCriteria(final Criterion... criterion) {
        return findByCriteria(0, 10, criterion);
    }

    protected List<T> findByCriteria(final int firstResult, final int maxResults, final Criterion... criterion) {
        Session session = (Session) getEntityManager().getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());

        for (final Criterion c : criterion) {
            crit.add(c);
        }

        if (firstResult > 0) {
            crit.setFirstResult(firstResult);
        }

        if (maxResults > 0) {
            crit.setMaxResults(maxResults);
        }

        final List<T> result = crit.list();
        return result;
    }

    @Override
    public int countAll() {
        return countByCriteria();
    }

    @Override
    public int countByExample(final T exampleInstance) {
        Session session = (Session) getEntityManager().getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());
        crit.setProjection(Projections.rowCount());
        crit.add(Example.create(exampleInstance));
        return (Integer) crit.list().get(0);
    }

    protected int countByCriteria(final Criterion... criterion) {
        Session session = (Session) getEntityManager().getDelegate();
        Criteria crit = session.createCriteria(getEntityClass());
        crit.setProjection(Projections.rowCount());

        for (final Criterion c : criterion) {
            crit.add(c);
        }

        return ((Long) crit.list().get(0)).intValue();
    }
}

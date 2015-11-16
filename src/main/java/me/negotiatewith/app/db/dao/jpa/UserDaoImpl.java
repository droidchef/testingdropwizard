package me.negotiatewith.app.db.dao.jpa;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import me.negotiatewith.app.db.model.entity.User;
import me.negotiatewith.app.db.dao.api.UserDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.inject.Provider;
import javax.persistence.EntityManager;

/**
 * Created by ishan on 13/11/15.
 */
public class UserDaoImpl extends BaseDaoJpaImpl<User, Long> implements UserDao{

    @Inject
    public UserDaoImpl(Provider<EntityManager> entityManagerProvider) {
        super(entityManagerProvider);
        this.entityClass = User.class;
    }

    @Override
    @Transactional
    public User findByEmailPassword(String email, String password) {
        Session session = (Session) getEntityManager().getDelegate();
        Criteria criteria = session.createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("email", email));
        criteria.add(Restrictions.eq("password", password));
        return (User) criteria.uniqueResult();
    }

    @Override
    @Transactional
    public User findById(Long id) {
        Session session = (Session) getEntityManager().getDelegate();
        Criteria criteria = session.createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        return (User) criteria.uniqueResult();
    }

}

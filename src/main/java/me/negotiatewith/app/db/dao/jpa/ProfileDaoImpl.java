package me.negotiatewith.app.db.dao.jpa;

import com.google.inject.Inject;
import me.negotiatewith.app.db.dao.api.ProfileDao;
import me.negotiatewith.app.db.model.entity.Profile;
import me.negotiatewith.app.db.model.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.inject.Provider;
import javax.persistence.EntityManager;

/**
 * Created by ishan on 16/11/15.
 */
public class ProfileDaoImpl extends BaseDaoJpaImpl<Profile, Long> implements ProfileDao {

    @Inject
    public ProfileDaoImpl(Provider<EntityManager> entityManagerProvider) {
        super(entityManagerProvider);
        this.entityClass = Profile.class;
    }


    @Override public Profile findById(Long profileId) {
        Session session = (Session) getEntityManager().getDelegate();
        Criteria criteria = session.createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", profileId));
        return (Profile) criteria.uniqueResult();
    }
}

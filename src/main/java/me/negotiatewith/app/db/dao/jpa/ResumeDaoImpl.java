package me.negotiatewith.app.db.dao.jpa;

import com.google.inject.Inject;
import me.negotiatewith.app.db.dao.api.ResumeDao;
import me.negotiatewith.app.db.model.entity.Resume;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.inject.Provider;
import javax.persistence.EntityManager;

public class ResumeDaoImpl extends BaseDaoJpaImpl<Resume, Long> implements ResumeDao {

    @Inject public ResumeDaoImpl(Provider<EntityManager> entityManagerProvider) {
        super(entityManagerProvider);
        this.entityClass = Resume.class;
    }

    @Override public Resume findById(Long Id) {
        Session session = (Session) getEntityManager().getDelegate();
        Criteria criteria = session.createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", Id));
        return (Resume) criteria.uniqueResult();
    }

    @Override
    public Resume findByProfileId(Long profileId) {
        Session session = (Session) getEntityManager().getDelegate();
        Criteria criteria = session.createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("profile_id", profileId));
        return (Resume) criteria.uniqueResult();
    }
}

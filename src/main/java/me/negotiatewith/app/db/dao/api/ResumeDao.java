package me.negotiatewith.app.db.dao.api;

import com.google.inject.ImplementedBy;
import me.negotiatewith.app.db.model.entity.Resume;
import me.negotiatewith.app.db.dao.jpa.ResumeDaoImpl;

@ImplementedBy(ResumeDaoImpl.class)
public interface ResumeDao extends BaseDao<Resume, Long> {

    Resume findById(Long id);

    Resume findByProfileId(Long profileId);
}

package me.negotiatewith.app.core.service.impl;

import com.google.inject.Inject;
import me.negotiatewith.app.core.dto.model.ResumeDto;
import me.negotiatewith.app.db.model.entity.Resume;
import me.negotiatewith.app.core.service.api.ResumeService;
import me.negotiatewith.app.db.dao.api.ResumeDao;
import org.joda.time.DateTime;


public class ResumeServiceImpl implements ResumeService {

    private final ResumeDao resumeDao;

    @Inject
    public ResumeServiceImpl(ResumeDao resumeDao) {
        this.resumeDao = resumeDao;
    }

    @Override
    public Resume saveEntity(ResumeDto resumeDto) {

        Resume resume = new Resume();

        return resumeDao.saveEntity(resume);
    }

    @Override
    public Resume findById(Long id) {
        return resumeDao.findById(id);
    }

    @Override
    public Resume findByProfileId(Long profileId) {
        return resumeDao.findByProfileId(profileId);
    }
}

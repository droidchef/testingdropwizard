package me.negotiatewith.app.core.service.api;

import com.google.inject.ImplementedBy;
import me.negotiatewith.app.core.dto.model.ResumeDto;
import me.negotiatewith.app.db.model.entity.Resume;
import me.negotiatewith.app.core.service.impl.ResumeServiceImpl;


@ImplementedBy(ResumeServiceImpl.class)

public interface ResumeService {

    Resume saveEntity(ResumeDto Dto);

    Resume findById(Long id);

    Resume findByProfileId(Long profileId);
}

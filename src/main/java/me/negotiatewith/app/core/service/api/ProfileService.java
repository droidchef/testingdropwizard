package me.negotiatewith.app.core.service.api;

import com.google.inject.ImplementedBy;
import me.negotiatewith.app.core.dto.model.ProfileDto;
import me.negotiatewith.app.core.service.impl.ProfileServiceImpl;
import me.negotiatewith.app.db.model.entity.Profile;
import me.negotiatewith.app.db.model.entity.User;


@ImplementedBy(ProfileServiceImpl.class)
public interface ProfileService {

    Profile saveEntity(ProfileDto profileDto, Long userId);

    Profile findById(Long id);

}

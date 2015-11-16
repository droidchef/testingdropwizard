package me.negotiatewith.app.core.service.api;

import com.google.inject.ImplementedBy;
import me.negotiatewith.app.core.service.impl.ProfileServiceImpl;
import me.negotiatewith.app.db.model.entity.Profile;
import me.negotiatewith.app.db.model.entity.User;

/**
 * Created by ishan on 16/11/15.
 */
@ImplementedBy(ProfileServiceImpl.class)
public interface ProfileService {

    Profile saveEntity(Profile profile);

    Profile findById(Long id);

}

package me.negotiatewith.app.core.service.impl;

import com.google.inject.Inject;
import me.negotiatewith.app.core.service.api.ProfileService;
import me.negotiatewith.app.db.dao.api.ProfileDao;
import me.negotiatewith.app.db.model.entity.Profile;

/**
 * Created by ishan on 16/11/15.
 */
public class ProfileServiceImpl implements ProfileService {

    private final ProfileDao profileDao;

    @Inject
    public ProfileServiceImpl(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @Override public Profile saveEntity(Profile profile) {
        return profileDao.saveEntity(profile);
    }

    @Override public Profile findById(Long id) {
        return profileDao.findById(id);
    }
}

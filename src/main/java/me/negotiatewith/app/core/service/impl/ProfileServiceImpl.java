package me.negotiatewith.app.core.service.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import me.negotiatewith.app.core.dto.model.ProfileDto;
import me.negotiatewith.app.core.service.api.ProfileService;
import me.negotiatewith.app.db.dao.api.ProfileDao;
import me.negotiatewith.app.db.dao.api.UserDao;
import me.negotiatewith.app.db.model.entity.Profile;
import me.negotiatewith.app.db.model.entity.User;
import org.joda.time.DateTime;


public class ProfileServiceImpl implements ProfileService {

    private final ProfileDao profileDao;
    private final UserDao userDao;

    @Inject
    public ProfileServiceImpl(ProfileDao profileDao, UserDao userDao) {
        this.profileDao = profileDao;
        this.userDao =  userDao;
    }

    @Override
    @Transactional
    public Profile saveEntity(ProfileDto profileDto, Long userId) {

        Profile profile = new Profile();
        profile.setDateOfBirth(profileDto.getDateOfBirth());
        profile.setIsHunter(profileDto.getIsHunter());
        profile.setIsSeeker(profileDto.getIsSeeker());
        profile.setCreatedAt(new DateTime(System.currentTimeMillis()));
        profile.setUpdatedAt(new DateTime(System.currentTimeMillis()));

        profile = profileDao.saveEntity(profile);

        User user = userDao.findById(userId);
        user.setProfile(profile);
        userDao.saveEntity(user);

        return profile;
    }

    @Override public Profile findById(Long id) {
        return profileDao.findById(id);
    }
}

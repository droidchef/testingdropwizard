package me.negotiatewith.app.db.dao.api;

import com.google.inject.ImplementedBy;
import me.negotiatewith.app.db.dao.jpa.ProfileDaoImpl;
import me.negotiatewith.app.db.dao.jpa.UserDaoImpl;
import me.negotiatewith.app.db.model.entity.Profile;


@ImplementedBy(ProfileDaoImpl.class)
public interface ProfileDao extends BaseDao<Profile, Long> {

    Profile findById(Long profileId);
}

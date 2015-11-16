package me.negotiatewith.app.core.service.api;

import com.google.inject.ImplementedBy;
import me.negotiatewith.app.db.model.entity.User;
import me.negotiatewith.app.core.service.impl.UserServiceImpl;

/**
 * Created by ishan on 13/11/15.
 */
@ImplementedBy(UserServiceImpl.class)
public interface UserService {

    User saveEntity(User user);

    User findByEmailPassword(String email, String password);

    User findById(Long id);
}

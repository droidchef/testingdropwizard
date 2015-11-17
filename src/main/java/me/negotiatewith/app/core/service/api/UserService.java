package me.negotiatewith.app.core.service.api;

import com.google.inject.ImplementedBy;
import me.negotiatewith.app.core.dto.model.UserDto;
import me.negotiatewith.app.db.model.entity.User;
import me.negotiatewith.app.core.service.impl.UserServiceImpl;


@ImplementedBy(UserServiceImpl.class)
public interface UserService {

    User saveEntity(UserDto userDto);

    User findByEmailPassword(String email, String password);

    User findById(Long id);
}

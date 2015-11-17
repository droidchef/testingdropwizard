package me.negotiatewith.app.core.service.impl;

import com.google.inject.Inject;
import me.negotiatewith.app.core.dto.model.UserDto;
import me.negotiatewith.app.db.model.entity.User;
import me.negotiatewith.app.core.service.api.UserService;
import me.negotiatewith.app.db.dao.api.UserDao;
import org.joda.time.DateTime;


public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    @Inject
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override public User saveEntity(UserDto userDto) {

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setCreatedAt(new DateTime(System.currentTimeMillis()));
        user.setUpdatedAt(new DateTime(System.currentTimeMillis()));

        return userDao.saveEntity(user);
    }

    @Override public User findByEmailPassword(String email, String password) {
        return userDao.findByEmailPassword(email, password);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }
}

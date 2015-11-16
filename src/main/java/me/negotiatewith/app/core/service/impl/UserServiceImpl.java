package me.negotiatewith.app.core.service.impl;

import com.google.inject.Inject;
import me.negotiatewith.app.db.model.entity.User;
import me.negotiatewith.app.core.service.api.UserService;
import me.negotiatewith.app.db.dao.api.UserDao;

/**
 * Created by ishan on 13/11/15.
 */
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    @Inject
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override public User saveEntity(User user) {
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

package me.negotiatewith.app.db.dao.api;

import com.google.inject.ImplementedBy;
import me.negotiatewith.app.db.model.entity.User;
import me.negotiatewith.app.db.dao.jpa.UserDaoImpl;


@ImplementedBy(UserDaoImpl.class)
public interface UserDao extends BaseDao<User, Long> {

    User findByEmailPassword(String email, String Password);

    User findById(Long id);
}

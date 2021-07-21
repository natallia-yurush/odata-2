package by.nyurush.odata.repository;

import by.nyurush.odata.entity.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    void delete(User user);

    List<User> findAll();

    User findById(Long id);
}

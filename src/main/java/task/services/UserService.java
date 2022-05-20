package task.services;

import task.models.User;

import java.util.List;

public interface UserService {

    void create(User user);

    User read(long id);

    void update(User user);

    void delete(User user);

    List<User> getUsers();
}

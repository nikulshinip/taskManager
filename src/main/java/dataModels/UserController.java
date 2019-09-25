package dataModels;

import java.util.List;

public interface UserController {
    User getUser(String name);
    void addUser(User user);
    void removeUser(User user);
    void updateUser(User user);
    List<User> getAllUsers();
}

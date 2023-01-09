package mapper;

import pojo.User;

public interface UserMapper{
    User selectForLogin(User user);

    User selectByUsername(User user);

    void insert(User user);
}

package mapper;

import pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper{
    List<User> selectAll();     //Corresponding to a select id in UserMapper.xml
    User selectById(Integer id);
    User selectForLogin(Map<String, String> map);
    List<User> selectDynamically(Map<String, String> map);      //Fuzzy search
    void insert(Map<String, String> map);
    void update(User user);
    void deleteById(Integer id);
}

import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperationWithMapper{
    public static void main(String[] args) throws IOException{
        //1. Building a SqlSessionFactory instance from an XML configuration file
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2. Acquiring a SqlSession from SqlSessionFactory
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3. This name can be directly mapped to a Mapper class of the same name as the namespace,
        //   with a method that matches the name, parameter, and return type as the mapped select statement.
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);


        List<User> users = userMapper.selectAll();
        System.out.println(users);

        userMapper.deleteById(5);
        sqlSession.commit();

        System.out.println(userMapper.selectAll());

        //4. Close SqlSession.
        sqlSession.close();
    }
}

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.User;

import java.io.InputStream;
import java.sql.*;
import java.util.List;

public class Operation {
    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/homework", "root", "123456");
        String sql = "select * from user";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.printf("%d %s %s %s\n", resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();



        //1. Building a SqlSessionFactory instance from an XML configuration file
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2. Acquiring a SqlSession from SqlSessionFactory
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3. Execute mapped SQL statements directly against the SqlSession instance.
        List<User> users = sqlSession.selectList("mapper.UserMapper.selectAll");
        System.out.println();
        System.out.println(users);

        //4. Close SqlSession.
        sqlSession.close();

    }
}

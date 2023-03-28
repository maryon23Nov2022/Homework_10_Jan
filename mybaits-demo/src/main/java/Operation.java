import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.User;

import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.logging.LogManager;

public class Operation {
    public static void main(String[] args) throws Exception{
//        java.util.logging.Logger.getLogger("com.mysql.cj.jdbc.Driver").setLevel(java.util.logging.Level.FINEST);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/homework?logger=com.mysql.cj.log.StandardLogger&profileSQL=true", "root", "123456");
        String condition = "'zhuqi' or true";

        String sql = "select * from user where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, condition);
        ResultSet preparedResultSet = preparedStatement.executeQuery();
        while(preparedResultSet.next()){
            System.out.printf("%d %s %s %s\n", preparedResultSet.getInt(1), preparedResultSet.getString(2), preparedResultSet.getString(3), preparedResultSet.getString(4));
        }
        System.out.printf("%s\n", "");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user where username = " + condition);
        while(resultSet.next()){
            System.out.printf("%d %s %s %s\n", resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
        }
        resultSet.close();
        preparedStatement.close();
        statement.close();
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

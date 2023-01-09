package service;

import com.alibaba.fastjson.JSON;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterService extends HttpServlet{

    private static SqlSessionFactory sqlSessionFactory;

    static{
        String resource = "mybatis-config.xml";
        try{
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4040");
        PrintWriter printWriter = resp.getWriter();
        BufferedReader bufferedReader = req.getReader();
        String line = bufferedReader.readLine();
        User user = JSON.parseObject(line, User.class);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User result = userMapper.selectByUsername(user);
        System.out.printf("%s %s\n", user.getUsername(), user.getPassword());
        if(result == null){
            userMapper.insert(user);
            sqlSession.commit();
            printWriter.write('1');
        } else{
            printWriter.write('0');
        }
        sqlSession.close();
    }
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4040");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
    }
}

package service;

import com.alibaba.fastjson.JSON;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginService extends HttpServlet{

    private static SqlSessionFactory sqlSessionFactory;

    static{
        System.out.printf("loadsfaded\n");
        String resource = "mybatis-config.xml";
        try{
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4040");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        PrintWriter printWriter = resp.getWriter();
        BufferedReader bufferedReader = req.getReader();
        String line = bufferedReader.readLine();
        User user = JSON.parseObject(line, User.class);

        HttpSession httpSession = req.getSession();
        System.out.printf("%s\n", httpSession.getId());
        resp.setHeader("Set-Cookie", "JSESSIONID="+httpSession.getId()+"; SameSite=None; Secure=0");
        System.out.printf("%s\n", httpSession);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User result = userMapper.selectForLogin(user);
        if(result == null){
            printWriter.write('0');
        } else{
            httpSession.setAttribute("username", result.getUsername());
            System.out.printf("result = %s\n%s\n", result.getUsername(), httpSession);
            printWriter.write(JSON.toJSONString(result));
        }
        sqlSession.close();
    }
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp){
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4040");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        System.out.printf("%s\n", req.toString());
    }
}

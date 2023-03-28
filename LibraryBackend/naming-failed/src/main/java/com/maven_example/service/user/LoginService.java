package com.maven_example.service.user;

import com.alibaba.fastjson2.JSON;
import com.maven_example.mapper.UserMapper;
import com.maven_example.pojo.User;
import com.maven_example.utils.SqlSessionFactoryUtility;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginService extends HttpServlet{
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp){
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4040");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        System.out.printf("%s\n", "loaded");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        System.out.printf("%s\n", "post loaded.");
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4040");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        req.setCharacterEncoding("UTF-8");
        BufferedReader bufferedReader = req.getReader();
        String line = bufferedReader.readLine();
        User user = JSON.parseObject(line, User.class);

        HttpSession httpSession = req.getSession();
        resp.setHeader("Set-Cookie", "JSESSIONID="+httpSession.getId()+"; SameSite=None; Secure=0");
        System.out.printf("step1 completed.");

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtility.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.printf("factory built\n");
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User result = userMapper.selectForLogin(user);
        System.out.printf("step2 completed");
        System.out.printf("%s %s\n", user.getUsername(), user.getPassword());
        if(result == null){
            printWriter.write('0');
        } else{
            httpSession.setAttribute("username", result.getUsername());
            httpSession.setAttribute("identity", result.getIdentity());
            httpSession.setAttribute("id", result.getId());
            printWriter.write(JSON.toJSONString(result));
            System.out.printf("%s %s\n", httpSession.getId(), httpSession.getAttribute("username"));
        }
        sqlSession.close();
        System.out.printf("All done\n");
    }
}

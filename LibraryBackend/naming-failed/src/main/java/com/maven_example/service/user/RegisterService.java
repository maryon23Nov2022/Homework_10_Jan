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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/register")
public class RegisterService extends HttpServlet{
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp){
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4040");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        Enumeration<String> enumeration = req.getHeaders("Origin");
        if(enumeration != null){
            while(enumeration.hasMoreElements()){
                System.out.printf("Register Origin: %s\n", enumeration.nextElement());
            }
        }

        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4040");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        req.setCharacterEncoding("UTF-8");
        BufferedReader bufferedReader = req.getReader();
        String line = bufferedReader.readLine();
        User user = JSON.parseObject(line, User.class);

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtility.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User result = userMapper.selectByUsername(user);
        if(result == null){
            userMapper.insert(user);
            sqlSession.commit();
            printWriter.write("注册成功！");
        } else{
            printWriter.write("用户名已存在！");
        }
        sqlSession.close();
    }
}

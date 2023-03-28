package com.maven_example.service.user;

import com.alibaba.fastjson2.JSON;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/getInfo")
public class GetInfoService extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        Enumeration<String> enumeration = req.getHeaders("Origin");
        if(enumeration != null){
            while(enumeration.hasMoreElements()){
                System.out.printf("Origin: %s\n", enumeration.nextElement());
            }
        }

        HttpSession httpSession = req.getSession();
        System.out.printf("%s\n", httpSession.getId());
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4040");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        Object username = httpSession.getAttribute("username");
        Object identity = httpSession.getAttribute("identity");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        if(username == null || username == ""){
            printWriter.write('0');
        } else{
            Map<String, String> data = new HashMap<>();
            data.put("username", username.toString());
            data.put("identity", identity.toString());
            printWriter.write(JSON.toJSONString(data));
        }
    }
}

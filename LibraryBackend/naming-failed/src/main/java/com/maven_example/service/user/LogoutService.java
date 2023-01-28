package com.maven_example.service.user;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutService extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        HttpSession httpSession = req.getSession();
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4040");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        httpSession.setAttribute("username", "");
        httpSession.setAttribute("identity", "");
        httpSession.setAttribute("id", "");
    }
}

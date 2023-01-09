package service;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getInfo")
public class GetInfoService extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4040");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        HttpSession httpSession = req.getSession();
        System.out.printf("getInfo = %s\n", httpSession);
        Object username = httpSession.getAttribute("username");
        PrintWriter printWriter = resp.getWriter();
        if(username == null){
            printWriter.write('0');
        } else{
            printWriter.write(username.toString());
            System.out.printf("username = %s\n", username);
        }
    }
}

import com.alibaba.fastjson.JSON;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

@WebServlet("/hello")
public class ServletServer extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String method = req.getMethod();
        System.out.printf("%s\n", method);

        String contextPath = req.getContextPath();
        System.out.printf("%s\n", contextPath);

        String url = req.getRequestURL().toString();
        System.out.printf("%s\n", url);

        String uri = req.getRequestURI();
        System.out.printf("%s\n", uri);

        String agent = req.getHeader("user-agent");
        System.out.printf("%s\n", agent);

        System.out.printf("%s\n", req.getParameter("username"));
        System.out.printf("%s\n", req.getParameter("password"));
        System.out.printf("%d\n", req.getParameterMap().size());

        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:56494");

        PrintWriter printWriter = resp.getWriter();

//        Iterator<Map.Entry<String, String[]>> iterator = req.getParameterMap().entrySet().iterator();
//        while(iterator.hasNext()){
//            Map.Entry<String, String[]> entry = iterator.next();
//            String value = entry.getValue()[0];
//            String realValue = new String(value.getBytes("ISO-8859-1"));
//            System.out.printf("%s: %s\n", entry.getKey(), realValue);
//            printWriter.write(realValue);
//        }
//        printWriter.write("hello");

        BufferedReader bufferedReader = req.getReader();
        String line = bufferedReader.readLine();
        System.out.printf("line = %s\n", line);
        User user = JSON.parseObject(line, User.class);
        System.out.printf("%s %s\n", user.getUsername(), user.getPassword());
        String string = JSON.toJSONString(user);
        System.out.printf("%s\n", string);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        this.doGet(req, resp);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:56494");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
    }
}
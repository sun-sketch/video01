package com.servlet;

import com.domain.User;
import com.service.UserService;
import com.service.VideoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registerUsernameServlet")
public class RegisterUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService=context.getBean("userServiceImpl",UserService.class);
        User user=userService.queryUserByUsername(request.getParameter("username"));
        PrintWriter out = response.getWriter();
        if(user!=null){
            out.write("用户名已经存在");//发送
            out.flush();
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

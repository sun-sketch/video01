package com.servlet;

import com.domain.User;
import com.service.CollectService;
import com.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteCollectServlet")
public class DeleteCollectServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CollectService collectService=context.getBean("collectServiceImpl",CollectService.class);
        System.out.println(request.getParameter("videoid"));
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        collectService.deleteCollect(user.getId(),Integer.parseInt(request.getParameter("videoid")));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

package com.servlet;

import com.domain.User;
import com.service.CollectService;
import org.apache.shiro.SecurityUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteSelectedCollectServlet")
public class DeleteSelectedCollectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CollectService collectService=context.getBean("collectServiceImpl",CollectService.class);
        String[] videoids = request.getParameterValues("videoid");
        collectService.delectSelectedCollect(videoids);
        request.getRequestDispatcher("/user/collect").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

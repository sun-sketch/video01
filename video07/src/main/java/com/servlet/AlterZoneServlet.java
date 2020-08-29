package com.servlet;

import com.service.ZoneService;
import com.service.impl.ZoneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/alterZoneServlet")
public class AlterZoneServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("zonename"));
        System.out.println(request.getParameter("id"));
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ZoneService zoneService =context.getBean("zoneServiceImpl", ZoneService.class);
        zoneService.alterZone(request.getParameter("zonename"),Integer.parseInt(request.getParameter("id")));
        request.getRequestDispatcher("/admin/cmszone").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

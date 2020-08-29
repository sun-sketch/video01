package com.servlet;
import com.domain.Video;
import com.service.ZoneService;
import com.service.impl.ZoneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/homeServlet")
public class HomeServlet extends javax.servlet.http.HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        ZoneService zoneService =context.getBean("zoneServiceImpl", ZoneService.class);
//        List<Video> videos= zoneService.queryZoneVideo(request.getParameter("zones").toString());
//        request.setAttribute("videos",videos);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        this.doPost(request, response);
    }
}

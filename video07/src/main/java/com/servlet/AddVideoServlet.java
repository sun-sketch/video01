package com.servlet;

import com.domain.Upload;
import com.service.UploadService;
import com.service.VideoService;
import com.service.ZoneService;
import com.service.impl.UploadServiceImpl;
import com.service.impl.VideoServiceImpl;
import com.service.impl.ZoneServiceImpl;
import com.util.CutFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/addVideoServlet")
public class AddVideoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        VideoService videoService=context.getBean("videoServiceImpl",VideoService.class);
        int id=Integer.parseInt(request.getParameter("id"));
        videoService.addVideo(id,request);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

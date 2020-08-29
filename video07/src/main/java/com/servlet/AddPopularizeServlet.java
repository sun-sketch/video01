package com.servlet;

import com.service.VideoService;
import com.service.impl.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addPopularizeServlet")
public class AddPopularizeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        VideoService videoService=context.getBean("videoServiceImpl",VideoService.class);
        int i=videoService.queryPopularizeByVideoid(id);
        PrintWriter out = response.getWriter();
        if(i>0){
            out.write("该视频已经推荐过，请选择其他视频");
            out.flush();
            out.close();
        }else {
            videoService.addPopularize(id);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

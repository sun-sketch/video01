package com.servlet;

import com.domain.Comment;
import com.service.CommentService;
import com.service.UserService;
import com.service.impl.CommentServiceImpl;
import com.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/commentlikeCountServlet")
public class CommentlikeCountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("index"));
        System.out.println(request.getParameter("i"));
        int index=Integer.parseInt(request.getParameter("index"));
        int video_id=Integer.parseInt(request.getParameter("i"));
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CommentService commentService=context.getBean("commentServiceImpl", CommentService.class);
        commentService.likecountadd(video_id,index);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

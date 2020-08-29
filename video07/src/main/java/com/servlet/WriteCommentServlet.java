package com.servlet;

import com.domain.User;
import com.service.CommentService;
import com.service.impl.CommentServiceImpl;
import com.service.impl.VideoServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/writeCommentServlet")
public class WriteCommentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String content=request.getParameter("comment");
        int video_id=Integer.parseInt(request.getParameter("i"));
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        int user_id=user.getId();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CommentService commentService=context.getBean("commentServiceImpl", CommentService.class);
        commentService.insertComment(content,video_id,user_id);
        request.getRequestDispatcher("/user/content").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

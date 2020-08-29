package com.servlet;

import com.dao.CollectDao;
import com.domain.User;
import com.service.CollectService;
import com.service.VideoService;
import org.apache.shiro.SecurityUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/videoCollectServlet")
public class VideoCollectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CollectService collectService=context.getBean("collectServiceImpl", CollectService.class);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        int user_id=user.getId();
        int video_id=Integer.parseInt(request.getParameter("videoid"));
        int count=collectService.queryJudgeVideoCollect(user_id,video_id);
        PrintWriter out = response.getWriter();
        if(count>0){
            out.write("已经收藏过该视频");//发送
        }else {
            collectService.addVideoCollect(user_id,video_id);
            out.write("视频收藏成功");
        }
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

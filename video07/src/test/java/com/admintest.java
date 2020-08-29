package com;

import com.domain.Upload;
import com.domain.User;
import com.service.*;
import com.service.impl.CommentServiceImpl;
import com.service.impl.UserServiceImpl;
import com.service.impl.VideoServiceImpl;
import com.service.impl.ZoneServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class admintest {

    //cmsuser test
    @Test
    public void test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService=context.getBean("userServiceImpl",UserService.class);
        List<User> users=userService.queryUserByReportcount();
        System.out.println(users);
//        userService.deleteUser(8);
    }

    @Test
    public void test02(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CommentService commentService=context.getBean("commentServiceImpl",CommentService.class);
        commentService.deleteComment(10);
    }


    //addcarousel test
    @Test
    public void test04(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        VideoService videoService=context.getBean("videoServiceImpl",VideoService.class);
//        videoService.addCarousel(8);
        int i=videoService.queryCarouselByVideoid(8);
        System.out.println(i);
//        videoService.deleteVideo(30);
    }

    //zone test
    @Test
    public void test05(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ZoneService zoneService =context.getBean("zoneServiceImpl", ZoneService.class);
//        zoneService.addZone("test");
//        zoneService.deleteZone(6);
    }

    @Test
    public void test06(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService=context.getBean("userServiceImpl",UserService.class);
        userService.deleteUser(8);
    }

    @Test
    public void test07(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UploadService uploadService=context.getBean("uploadServiceImpl",UploadService.class);
        Upload upload=uploadService.queryUploadById(8);
        System.out.println(upload);
    }


}

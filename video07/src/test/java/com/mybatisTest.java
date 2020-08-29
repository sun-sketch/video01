package com;

import com.domain.Comment;
import com.domain.Video;
import com.domain.Zone;
import com.service.CollectService;
import com.service.UserService;
import com.service.VideoService;
import com.service.ZoneService;
import com.service.impl.CommentServiceImpl;
import com.service.impl.VideoServiceImpl;
import com.service.impl.ZoneServiceImpl;
import com.util.CutFile;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class mybatisTest {

    //video test
    @Test
    public void test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        VideoService videoService=context.getBean("videoServiceImpl", VideoService.class);
        Video video=videoService.queryVideoById(7);
        System.out.println(video);
    }

    //comment test
    @Test
    public void test02(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CommentServiceImpl commentService=context.getBean("commentServiceImpl", CommentServiceImpl.class);
        List<Comment> comment=commentService.queryCommentByVideoid(2);
        System.out.println(comment);
    }

    //count test
    @Test
    public void test03(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CommentServiceImpl commentService=context.getBean("commentServiceImpl", CommentServiceImpl.class);
        int count=commentService.commentCountByVideoid(2);
        System.out.println(count);
    }

    //zonename test
    @Test
    public void test04(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ZoneServiceImpl zoneService=context.getBean("zoneServiceImpl",ZoneServiceImpl.class);
        String zonename=zoneService.queryZoneByVideoid(2);
        System.out.println(zonename);
    }

    //comment insert test
    @Test
    public void test05(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CommentServiceImpl commentService=context.getBean("commentServiceImpl", CommentServiceImpl.class);
        commentService.insertComment("手书等于动画【狗头】",1,1);
    }

    @Test
    public void test06(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ZoneServiceImpl zoneService =context.getBean("zoneServiceImpl", ZoneServiceImpl.class);
        List<Zone> zones=zoneService.queryAllZone();
        System.out.println(zones);
        List<Video> videos=zoneService.queryZoneVideo("动画");
        System.out.println(videos);
    }

    //home test
    @Test
    public void test07(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ZoneServiceImpl zoneService =context.getBean("zoneServiceImpl", ZoneServiceImpl.class);
        List<Zone> zones=zoneService.queryAllZone();
        System.out.println(zones);
        VideoServiceImpl videoService=context.getBean("videoServiceImpl",VideoServiceImpl.class);
        System.out.println(videoService.queryCarouselVideo());
        System.out.println(videoService.queryTopVideo());
        System.out.println(videoService.queryPopularizeVideo());
    }

    //zone test
    @Test
    public void test08(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ZoneService zoneService =context.getBean("zoneServiceImpl", ZoneService.class);
        List<Video> videos1=zoneService.queryZoneByLikecount("动画",1,3);
        System.out.println(videos1);
        List<Video> videos2=zoneService.queryZoneByTime("动画",1,3);
        System.out.println(videos2);
        int count=zoneService.queryZoneVideoCount("动画");
        System.out.println(count);
    }

    //search test
    @Test
    public void test09(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        VideoServiceImpl videoService=context.getBean("videoServiceImpl",VideoServiceImpl.class);
        List<Video> videos=videoService.queryVideoBySearch("情");
        System.out.println(videos);
    }

    @Test
    public void test10(){
        String uuid2 = UUID.randomUUID().toString().replace("-", "");
        System.out.println(uuid2);
    }
    @Test
    public void test11(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CollectService collectService=context.getBean("collectServiceImpl",CollectService.class);
        List<Video> videos=collectService.queryCollectVideo(2);
        System.out.println(videos);
    }
    @Test
    public void test12(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CollectService collectService=context.getBean("collectServiceImpl",CollectService.class);
        int count=collectService.queryJudgeVideoCollect(2,8);
        System.out.println(count);
    }

    //user delete
    @Test
    public void test13(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService=context.getBean("userServiceImpl",UserService.class);
        userService.deleteUser(9);
    }

    @Test
    public void test14(){
        File file1=new File("D:\\a.flv");
        File file2=new File("D:\\other\\a.flv");
        CutFile.cutFile(file1,file2);
    }

}

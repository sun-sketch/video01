package com.util;

import com.domain.Page;
import com.domain.Video;
import com.service.CommentService;
import com.service.UploadService;
import com.service.VideoService;
import com.service.ZoneService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ZonePageUtil{

    static public Page<Video> cutpage(int currentpage, int row, String status, String zonename){
        int count = 0;
        Page<Video> p=new Page<Video>();
        System.out.println("currentpage:"+currentpage);
        p.setCurrentPage(currentpage);
        int begin=(currentpage-1)*row;
        System.out.println("begin:"+begin);
        p.setBegin(begin);
        int page=0;
        p.setRows(row);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ZoneService zoneService =context.getBean("zoneServiceImpl", ZoneService.class);
        if(status.equals("zonelike")){
            List<Video> videos=zoneService.queryZoneByLikecount(zonename,begin,row);
            count=zoneService.queryZoneVideoCount(zonename);
            p.setList(videos);
        }
        if(status.equals("zonetime")){
            List<Video> videos=zoneService.queryZoneByTime(zonename,begin,row);
            count=zoneService.queryZoneVideoCount(zonename);
            p.setList(videos);
        }
        p.setTotalCount(count);
        page=count/row;
        System.out.println(page);
        if(count%row==0){
            p.setTotalPage(page);
        }else {
            p.setTotalPage(page+1);
        }
        System.out.println("totalpage:"+p.getTotalPage());
        return p;
    }
}

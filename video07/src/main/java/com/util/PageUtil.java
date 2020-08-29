package com.util;

import com.domain.Page;
import com.domain.Video;
import com.service.CommentService;
import com.service.UploadService;
import com.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class PageUtil<T> {

    public Page<T> cutpage(int currentpage, int row,String status){
        int count = 0;
        Page<T> p=new Page<T>();
        p.setCurrentPage(currentpage);
        int begin=(currentpage-1)*row;
        System.out.println("begin:"+begin);
        p.setBegin(begin);
        int page;
        p.setRows(row);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        if (status.equals("videoreport")||status.equals("videolike")){
            VideoService videoService=context.getBean("videoServiceImpl",VideoService.class);
            count=videoService.countVideo();
            if(status.equals("videoreport")){
                p.setList((List<T>) videoService.queryPageVideo(begin,p.getRows()));
            }
            if(status.equals("videolike")){
                p.setList((List<T>) videoService.queryPageVideoByLikecount(begin,p.getRows()));
            }
        }
        if(status.equals("comment")){
            CommentService commentService=context.getBean("commentServiceImpl",CommentService.class);
            count=commentService.countComment();
            p.setList((List<T>) commentService.queryPageCommentByReportcount(begin,p.getRows()));
        }
        if(status.equals("uploadtime")){
            UploadService uploadService=context.getBean("uploadServiceImpl",UploadService.class);
            count=uploadService.countUpload();
            p.setList((List<T>) uploadService.queryPageUpload(begin,p.getRows()));
        }
        p.setTotalCount(count);
        page=count/row;
        if(count%row==0){
            p.setTotalPage(page);
        }else {
            p.setTotalPage(page+1);
        }
        System.out.println("totalpage:"+p.getTotalPage());
        return p;
    }

}

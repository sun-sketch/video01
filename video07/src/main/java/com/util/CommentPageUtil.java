package com.util;

import com.domain.Page;
import com.service.VideoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class CommentPageUtil<T> {

    public Page<T> cutpage(int currentpage, int row, String order){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        VideoService videoService=context.getBean("videoServiceImpl",VideoService.class);
        Page<T> p=new Page<T>();
        p.setCurrentPage(currentpage);
        int begin=(currentpage-1)*row;
        System.out.println("begin:"+begin);
        p.setBegin(begin);
        int countvideo=videoService.countVideo();
        int page;
        p.setRows(row);
        if(order.equals("report")){
            p.setList((List<T>) videoService.queryPageVideo(begin,p.getRows()));
        }
        if(order.equals("like")){
            p.setList((List<T>) videoService.queryPageVideoByLikecount(begin,p.getRows()));
        }
        p.setTotalCount(countvideo);
        page=countvideo/row;
        if(countvideo%row==0){
            p.setTotalPage(page);
        }else {
            p.setTotalPage(page+1);
        }
        System.out.println("totalpage:"+p.getTotalPage());
        return p;
    }

}

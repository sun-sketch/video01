package com.controller;


import com.domain.*;
import com.service.*;
import com.util.MD5Util;
import com.util.PageUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private ZoneService zoneServiceImpl;
    @Autowired
    private VideoService videoServiceImpl;
    @Autowired
    private UploadService uploadServiceImpl;

    Page<Comment> p=new Page<Comment>();
    @RequestMapping("/cms")
    public String cms(Model model){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user",user);
        System.out.println(user);
        return "cms";
    }

    @RequestMapping("/login")
    public String login(String username,String password) throws Exception {
        System.out.println("login logic");
        //获取subject 调用login
        Subject subject = SecurityUtils.getSubject();
        System.out.println(username);
        username=new String(username.getBytes("ISO-8859-1"), "utf-8");
        password=new String(password.getBytes("ISO-8859-1"), "utf-8");
        String str=username+password;
        String salt= MD5Util.md5_salt(str);
        password=MD5Util.md5(password,salt);
        System.out.println(password);
        // 创建用于登录的令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 登录失败会抛出异常，则交由异常解析器处理
        subject.login(token);
        System.out.println("true........");
        return "redirect:cms";
    }

    @RequestMapping("/cmsuser")
    public String cmsuser(Model model){
        List<User> users=userServiceImpl.queryUserByReportcount();
        System.out.println(users);
        model.addAttribute("users",users);
        int page=users.size()/8;
        model.addAttribute("page",page);
        return "cmsuser";
    }
    @RequestMapping("/cmscomment")
    public String cmscomment(int currentPage,Model model){
        PageUtil<Comment> pageUtil =new PageUtil<Comment>();
        Page<Comment> p= pageUtil.cutpage(currentPage,8,"comment");
        model.addAttribute("p",p);
        return "cmscomment";
    }

    @RequestMapping("/cmsvideo")
    public String cmsvideo(int currentPage,Model model){
        PageUtil<Video> pageUtil =new PageUtil<Video>();
        Page<Video> p= pageUtil.cutpage(currentPage,8,"videoreport");
        System.out.println(p);
        model.addAttribute("p",p);
        return "cmsvideo";
    }

    @RequestMapping("/cmsaddvideo")
    public String cmsaddvideo(int currentPage,Model model){
        PageUtil<Upload> pageUtil =new PageUtil<Upload>();
        Page<Upload> p= pageUtil.cutpage(currentPage,8,"uploadtime");
        System.out.println(p);
        model.addAttribute("p",p);
        return "cmsaddvideo";
    }

    @RequestMapping("/cmsuploadcontent")
    public String cmsuploadcontent(int id,Model model){
        System.out.println(id);
        Upload upload=uploadServiceImpl.queryUploadById(id);
        System.out.println(upload);
        User user=userServiceImpl.queryUserByUserid(upload.getUser_id());
        if(user==null){
            String username=userServiceImpl.queryDeletedUser(upload.getUser_id());
            model.addAttribute("username",username);
        }else {
            model.addAttribute("username",user.getUsername());
        }
        model.addAttribute("upload",upload);
        return "cmsuploadcontent";
    }

    @RequestMapping("/cmscarousel")
    public String cmscarousel(Model model){
        List<Video> videos=videoServiceImpl.queryCarouselVideo();
        model.addAttribute("videos",videos);
        return "cmscarousel";
    }

    @RequestMapping("/cmsaddcarousel")
    public String cmsaddcarousel(int currentPage,Model model){
        PageUtil<Video> pageUtil =new PageUtil<Video>();
        Page<Video> p= pageUtil.cutpage(currentPage,8,"videolike");
        model.addAttribute("p",p);
        return "cmsaddcarousel";
    }

    @RequestMapping("/cmszone")
    public String cmszone(Model model){
        List<Zone> zones=zoneServiceImpl.queryAllZone();
        model.addAttribute("zones",zones);
        return "cmszone";
    }

    @RequestMapping("/cmspopularize")
    public String cmspopularize(Model model){
        List<Video> videos=videoServiceImpl.queryPopularizeVideo();
        model.addAttribute("videos",videos);
        int size=videos.size();
        model.addAttribute("size",size);
        return "cmspopularize";
    }

    @RequestMapping("/cmsaddpopularize")
    public String cmsaddpopularize(int currentPage,Model model,int popularizecount){
        PageUtil<Video> pageUtil =new PageUtil<Video>();
        Page<Video> p= pageUtil.cutpage(currentPage,8,"videolike");
        model.addAttribute("p",p);
        model.addAttribute("popularizecount",popularizecount);
        return "cmsaddpopularize";
    }

    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "index";
    }
}

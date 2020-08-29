package com.controller;


import com.domain.*;
import com.service.*;
import com.util.MD5Util;
import com.util.ZonePageUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private ZoneService zoneServiceImpl;
    @Autowired
    private VideoService videoServiceImpl;
    @Autowired
    private CollectService collectServiceImpl;
    @Autowired
    private CommentService commentServiceImpl;

    @GetMapping("/login")
    public String login(){
        System.out.println("goto login page");
        return "index";
    }

    @RequestMapping(value="/register",method= RequestMethod.GET)
    public String register(){
        System.out.println("goto register page");
        return "register";
    }
    @RequestMapping(value="/register",method=RequestMethod.POST)
    public String register(User user,HttpServletRequest request,String password2) throws UnsupportedEncodingException {
        User user1=userServiceImpl.queryUserByUsername(user.getUsername());
        if(user1!=null){
            request.setAttribute("register_msg","用户名已经存在。");
            return "register";
        }
        System.out.println(password2);
        if(!user.getPassword().equals(password2)){
            request.setAttribute("register_msg","密码不一致。");
            return "register";
        }
        System.out.println(" register");
        System.out.println(user);
        String str=user.getUsername()+user.getPassword();
        String salt=MD5Util.md5_salt(str);
        String password=MD5Util.md5(user.getPassword(),salt);
        user.setPassword(password);
        System.out.println(user.getPassword());
        //添加到用户信息
        userServiceImpl.insertUser(user);
        return "index";// 登录成功，跳转到登录页面
    }
    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "redirect:home";
    }
    @RequestMapping("/content")
    public String content(int i,Model model){
        Video video=videoServiceImpl.queryVideoById(i);
        System.out.println(video);
        model.addAttribute("video",video);
        String zonename=zoneServiceImpl.queryZoneByVideoid(i);
        model.addAttribute("zonename",zonename);
        User user=userServiceImpl.queryUserByUserid(video.getUser_id());
        if(user==null){
            String username=userServiceImpl.queryDeletedUser(video.getUser_id());
            model.addAttribute("username",username);
        }else {
            model.addAttribute("username",user.getUsername());
        }
        List<Comment> comments=commentServiceImpl.queryCommentByVideoid(i);
        System.out.println(comments);
        model.addAttribute("comments",comments);
//        int count=commentServiceImpl.commentCountByVideoid(i);
        model.addAttribute("count",comments.size());
        return "content";
    }

    @RequestMapping("/home")
    public String index(Model model){
        List<Zone> zones=zoneServiceImpl.queryAllZone();
        model.addAttribute("zones",zones);
        List<Video> carousels=videoServiceImpl.queryCarouselVideo();
        model.addAttribute("carousels",carousels);
        int carouselssize=carousels.size();
        model.addAttribute("carouselssize",carouselssize);
        List<Video> topvideos=videoServiceImpl.queryTopVideo();
        model.addAttribute("topvideos",topvideos);
        List<Video> popularizes=videoServiceImpl.queryPopularizeVideo();
        model.addAttribute("popularizes",popularizes);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user",user);
        return "home";
    }

    @RequestMapping("/zonelike")
    public String zonelike(Model model,String zonename,int currentPage){
        List<Zone> zones=zoneServiceImpl.queryAllZone();
        model.addAttribute("zones",zones);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user",user);
        System.out.println(zonename);
        Page<Video> p=ZonePageUtil.cutpage(currentPage,8,"zonelike",zonename);
        model.addAttribute("p",p);
        return "zonelike";
    }

    @RequestMapping("/zonetime")
    public String zonetime(Model model,String zonename,int currentPage){
        List<Zone> zones=zoneServiceImpl.queryAllZone();
        model.addAttribute("zones",zones);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user",user);
        System.out.println(zonename);
        Page<Video> p=ZonePageUtil.cutpage(currentPage,8,"zonetime",zonename);
        model.addAttribute("p",p);
        return "zonetime";
    }

    @RequestMapping("/user")
    public String user(Model model){
        User user1 = (User) SecurityUtils.getSubject().getPrincipal();
        User user=userServiceImpl.queryUserByUserid(user1.getId());
        model.addAttribute("user",user);
        return "user";
    }

    @RequestMapping("/search")
    public String search(Model model,String search){
        List<Zone> zones=zoneServiceImpl.queryAllZone();
        model.addAttribute("zones",zones);
        List<Video> videos=videoServiceImpl.queryVideoBySearch(search);
        model.addAttribute("videos",videos);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user",user);
        return "search";
    }

    @RequestMapping("/collect")
    public String collect(Model model){
        List<Zone> zones=zoneServiceImpl.queryAllZone();
        model.addAttribute("zones",zones);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user",user);
        List<Video> videos=collectServiceImpl.queryCollectVideo(user.getId());
        model.addAttribute("videos",videos);
        return "collect";
    }
    @RequestMapping("/managecollect")
    public String managecollect(Model model){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user",user);
        List<Video> videos=collectServiceImpl.queryCollectVideo(user.getId());
        model.addAttribute("videos",videos);
        return "managecollect";
    }

}
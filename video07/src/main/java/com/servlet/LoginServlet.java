package com.servlet;

import com.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        //2.1获取用户填写验证码
        String verifycode = request.getParameter("verifycode");

        //3.验证码校验
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        System.out.println(checkcode_server);
        session.removeAttribute("CHECKCODE_SERVER");//确保验证码一次性
        if(!checkcode_server.equalsIgnoreCase(verifycode)){
            //验证码不正确
            //提示信息
            out.write("验证码错误！");
            return;
        }
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        //获取subject 用于调用login
        Subject subject = SecurityUtils.getSubject();
        System.out.println(username);
        String str=username+password;
        String salt= MD5Util.md5_salt(str);
        password=MD5Util.md5(password,salt);
        System.out.println(password);
        // 创建用于登录的令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try{
            subject.login(token);
        }catch (Exception e){
            e.printStackTrace();
            out.write("用户名或者密码错误");
        }finally {
            out.flush();
            out.close();
        }
        System.out.println("true........");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

package com.controller;

import com.domain.Upload;
import com.domain.User;
import com.domain.Video;
import com.domain.Zone;
import com.service.UploadService;
import com.service.VideoService;
import com.service.ZoneService;
import com.service.impl.UploadServiceImpl;
import com.service.impl.VideoServiceImpl;
import com.service.impl.ZoneServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/updown")
public class UpDownController {
    @Autowired
    private UploadService uploadServiceImpl;
    @Autowired
    private ZoneService zoneServiceImpl;
    @Autowired
    private VideoService videoServiceImpl;

    @RequestMapping("/goupload")
    public String goupload(){
        return "upload";
    }


    @RequestMapping("/upload")
    @ResponseBody
    public void upload(HttpServletRequest request, MultipartFile uploadfile, MultipartFile uploadvideo, String zonename, String introduction,HttpServletResponse response) throws Exception {
        uploadServiceImpl.addVideo(request, uploadfile,uploadvideo,zonename, introduction,response);
    }

    //文件下载
    @RequestMapping("/download")
    @ResponseBody
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        System.out.println(request.getParameter("videoname"));
        String path="D:\\software\\red5-server-1.0.10-M4\\red5-server\\webapps\\oflaDemo\\streams\\";
        String fileName=request.getParameter("uuid")+".mp4";
        File file=new File(path+fileName);
        if(file.exists()){
            System.out.println("true.......");
            //设置MIME类型
            response.setContentType("application/octet-stream");
            //或者为response.setContentType("application/x-msdownload");

            //设置头信息,设置文件下载时的默认文件名，同时解决中文名乱码问题
            response.addHeader("Content-disposition", "attachment;filename="+new String(request.getParameter("videoname").getBytes(), "ISO-8859-1"));

            InputStream inputStream=new FileInputStream(file);
            ServletOutputStream outputStream=response.getOutputStream();
            byte[] bs=new byte[1024];
            while((inputStream.read(bs)>0)){
                outputStream.write(bs);
            }
            outputStream.close();
            inputStream.close();
        }
    }

    @RequestMapping("/uploadstatus")
    public String uploadstatus(Model model) {
        List<Zone> zones=zoneServiceImpl.queryAllZone();
        model.addAttribute("zones",zones);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user",user);
        List<Upload> uploads=uploadServiceImpl.queryUploadByUserid(user.getId());
        model.addAttribute("uploads",uploads);
        List<Video> videos=videoServiceImpl.queryVideoByUserid(user.getId());
        model.addAttribute("videos",videos);
        return "uploadstatus";
    }
}

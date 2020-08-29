package com.service.impl;

import com.dao.UploadDao;
import com.domain.Upload;
import com.domain.User;
import com.service.UploadService;
import com.util.ConvertVideo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private UploadDao uploadDao;

    @Override
    public void addVideo(HttpServletRequest request, MultipartFile uploadfile, MultipartFile uploadvideo, String zonename, String introduction, HttpServletResponse response)throws Exception{
        response.setCharacterEncoding("utf-8");
        System.out.println("springmvc文件上传...");
        System.out.println(zonename);
        System.out.println(introduction);
        String outputPath=request.getSession().getServletContext().getRealPath("/upload/");
        String ffmpegPath="D:\\software\\ffmpeg\\bin\\";
        PrintWriter out = response.getWriter();
        // 上传的位置
        String path = request.getSession().getServletContext().getRealPath("/static/img/");
        System.out.println(path);
        // 判断，该路径是否存在
        File file1 = new File(path);
        if(!file1.exists()){
            // 创建该文件夹
            file1.mkdirs();
        }
        // 说明上传文件项
        // 获取上传文件的名称
        String filename = uploadfile.getOriginalFilename();
        // 把文件的名称设置唯一值，uuid
        String uuid1 = UUID.randomUUID().toString().replace("-", "");
        filename = uuid1+"_"+filename;
        System.out.println("filename:"+filename);

        //上传视频
        // 上传的位置
        String videopath = "D:\\software\\ffmpeg\\bin\\input\\";

        // 判断，该路径是否存在
        File file2 = new File(videopath);
        if(!file2.exists()){
            // 创建该文件夹
            file2.mkdirs();
        }
        //获取文件名最后一个点的位置
        int i=uploadvideo.getOriginalFilename().lastIndexOf(".");
        //获取文件后缀名
        String suffix = uploadvideo.getOriginalFilename().substring(i);
        System.out.println(suffix);
        // 获取上传文件的名称
        String videoname=uploadvideo.getOriginalFilename().replace(suffix,"");
        System.out.println(videoname);
        System.out.println(videopath);
        // 把文件的名称设置唯一值，uuid
        String uuid2 = UUID.randomUUID().toString().replace("-", "");
        String newvideoname=uuid2+suffix;
        System.out.println(newvideoname);
        File inputfile=new File(videopath+newvideoname);
        try {
            // 完成图片上传
            uploadfile.transferTo(new File(path,filename));

            // 完成视频文件上传
            if(suffix.equals(".mp4")){
                uploadfile.transferTo(new File(outputPath,newvideoname));
            }else {
                uploadvideo.transferTo(new File(videopath,newvideoname));
            }
            //视频转码成MP4格式
            ConvertVideo convertVideo=new ConvertVideo(videopath+newvideoname,outputPath,ffmpegPath,uuid2);
            convertVideo.startConvert();
            inputfile.delete();
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            uploadDao.addVideo(videoname,uuid2,filename,introduction,zonename,user.getId());
            out.write("视频上传成功");//发送
        }catch (Exception e){
            e.printStackTrace();
            File imagefile=new File(path+filename);
            if(imagefile.exists()){
                imagefile.delete();
            }
            if (inputfile.exists()){
                inputfile.delete();
            }
            File outfile=new File(outputPath+uuid2+".mp4");
            if (outfile.exists()){
                outfile.delete();
            }
            out.write("视频上传失败，请重新上传");
        }finally {
            out.flush();
            out.close();
        }

    }

    @Override
    public List<Upload> queryPageUpload(int begin, int rows) {
        return uploadDao.queryPageUpload(begin,rows);
    }

    @Override
    public int countUpload() {
        return uploadDao.countUpload();
    }

    @Override
    public void deleteuploadvideo(int id, HttpServletRequest request) {
        Upload upload=uploadDao.queryUploadById(id);
        String imagepath=request.getSession().getServletContext().getRealPath("/static/img/");
        System.out.println(imagepath);
        File imagefile=new File(imagepath+upload.getImagename());
        String videopath=request.getSession().getServletContext().getRealPath("/upload/");;
        File videofile=new File(videopath+upload.getUuid()+".mp4");
        if(imagefile.exists()){
            imagefile.delete();
            System.out.println("图片已经被成功删除");
        }
        if(videofile.exists()){
            videofile.delete();
            System.out.println("视频已经被成功删除");
        }
        uploadDao.deleteuploadvideo(id);
    }

    @Override
    public Upload queryUploadById(int id) {
        return uploadDao.queryUploadById(id);
    }

    @Override
    public List<Upload> queryUploadByUserid(int user_id) {
        return uploadDao.queryUploadByUserid(user_id);
    }



}

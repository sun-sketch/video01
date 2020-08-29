package com.service.impl;

import com.dao.CollectDao;
import com.dao.UploadDao;
import com.dao.VideoDao;
import com.dao.ZoneDao;
import com.domain.Upload;
import com.domain.Video;
import com.service.VideoService;
import com.util.CutFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoDao videoDao;
    @Autowired
    private UploadDao uploadDao;
    @Autowired
    private ZoneDao zoneDao;
    @Autowired
    private CollectDao collectDao;

    @Override
    public Video queryVideoById(int id) {
        return videoDao.queryVideoById(id);
    }
    @Override
    public int queryVideoIdByVideoname(@Param("videoname") String videoname){
        return videoDao.queryVideoIdByVideoname(videoname);
    }
    @Override
    public int insertVideoIdCommentId(int video_id,int comment_id){
        return videoDao.insertVideoIdCommentId(video_id,comment_id);
    }
    @Override
    public void likecountadd(int videoid){
        videoDao.likecountadd(videoid);
    }
    @Override
    public void reportcountadd(int videoid){
        videoDao.reportcountadd(videoid);
    }
    @Override
    public List<Video> queryCarouselVideo(){
        return videoDao.queryCarouselVideo();
    }
    @Override
    public List<Video> queryTopVideo(){
        return videoDao.queryTopVideo();
    }
    @Override
    public List<Video> queryPopularizeVideo(){
        return videoDao.queryPopularizeVideo();
    }
    @Override
    public List<Video> queryVideoBySearch(String search){
        return videoDao.queryVideoBySearch(search);
    }
    @Override
    public List<Video> queryPageVideo(int begin,int rows){
        return videoDao.queryPageVideo(begin,rows);
    }

    @Override
    public int queryPopularizeByVideoid(int video_id) {
        return videoDao.queryPopularizeByVideoid(video_id);
    }

    @Override
    public int queryCarouselByVideoid(int video_id) {
        return videoDao.queryCarouselByVideoid(video_id);
    }

    public int countVideo(){
        return videoDao.countVideo();
    }

    public void deleteVideo(int id,HttpServletRequest request){
        Video video=videoDao.queryVideoById(id);
        String imagepath=request.getSession().getServletContext().getRealPath("/static/img/");
        System.out.println(imagepath);
        File imagefile=new File(imagepath+video.getImagename());
        String videopath="D:\\software\\red5-server-1.0.10-M4\\red5-server\\webapps\\oflaDemo\\streams\\";
        File videofile=new File(videopath+video.getUuid()+".mp4");
        if(imagefile.exists()){
            imagefile.delete();
            System.out.println("图片已经被成功删除");
        }
        if(videofile.exists()){
            videofile.delete();
            System.out.println("视频已经被成功删除");
        }
        videoDao.deleteVideo(id);
    }

    @Override
    public void deleteVideoComment(int video_id) {
        videoDao.deleteVideoComment(video_id);
    }
    public void deleteCarousel(int video_id){
        videoDao.deleteCarousel(video_id);
    }
    public void deletePopularize(int video_id){
        videoDao.deletePopularize(video_id);
    }
    public void addPopularize(int video_id){
        videoDao.addPopularize(video_id);
    }
    public void addCarousel(int video_id){
        videoDao.addCarousel(video_id);
    }
    public List<Video> queryPageVideoByLikecount(int begin,int rows){
        return videoDao.queryPageVideoByLikecount(begin,rows);
    }


    public void addVideo(int id, HttpServletRequest request) throws IOException {
        Upload upload=uploadDao.queryUploadById(id);
        videoDao.addVideo(upload.getVideoname(),upload.getUuid(),upload.getImagename(),upload.getIntroduction(),upload.getUser_id());
        int video_id=videoDao.querylastVideoid();
        int zone_id=zoneDao.queryZoneidByZonename(upload.getZonename());
        zoneDao.addZoneVideo(video_id,zone_id);
        String initpath= request.getSession().getServletContext().getRealPath("/upload/");
        String newpath="D:\\software\\red5-server-1.0.10-M4\\red5-server\\webapps\\oflaDemo\\streams\\";
        File initfile=new File(initpath+upload.getUuid()+".mp4");
        File newfile=new File(newpath+upload.getUuid()+".mp4");
        newfile.createNewFile();
        CutFile.cutFile(initfile,newfile);
        uploadDao.deleteuploadvideo(upload.getId());
        System.out.println("添加视频成功");
    }



    public int querylastVideoid(){
        return videoDao.querylastVideoid();
    }

    @Override
    public List<Video> queryVideoByUserid(int user_id) {
        return videoDao.queryVideoByUserid(user_id);
    }
}

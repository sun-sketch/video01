package com.service;

import com.domain.Video;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface VideoService {
    Video queryVideoById(int id);

    int queryVideoIdByVideoname(String videoname);

    int insertVideoIdCommentId(int video_id,int comment_id);

    void likecountadd(int videoid);

    void reportcountadd(int videoid);

    List<Video> queryCarouselVideo();

    List<Video> queryTopVideo();

    List<Video> queryPopularizeVideo();

    List<Video> queryVideoBySearch(String search);

    List<Video> queryPageVideo(int begin,int rows);

    int queryPopularizeByVideoid(int video_id);

    int queryCarouselByVideoid(int video_id);

    int countVideo();

    void deleteVideo(int id,HttpServletRequest request);

    void deleteVideoComment(int video_id);

    void deleteCarousel(int video_id);

    void deletePopularize(int video_id);

    void addPopularize(int video_id);

    void addCarousel(int video_id);

    List<Video> queryPageVideoByLikecount(int begin,int rows);

    void addVideo(int id, HttpServletRequest request) throws IOException;

    int querylastVideoid();

    List<Video> queryVideoByUserid(int user_id);
}

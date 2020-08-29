package com.dao;

import com.domain.Comment;
import com.domain.Video;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface VideoDao {
    @Select("select * from video where id=#{id}")
    Video queryVideoById(@Param("id") int id);

    @Select("select id from video where videoname=#{videoname}")
    int queryVideoIdByVideoname(@Param("videoname") String videoname);

    @Insert("insert into video_comment(video_id,comment_id)values(#{video_id},#{comment_id}) ")
    int insertVideoIdCommentId(@Param("video_id") int video_id,@Param("comment_id") int comment_id);

    @Update("UPDATE video SET likecount=likecount+1 WHERE id=#{videoid}")
    void likecountadd(@Param("videoid") int videoid);

    @Update("UPDATE video SET reportcount=reportcount+1 WHERE id=#{videoid}")
    void reportcountadd(@Param("videoid") int videoid);

    @Select("select video.id,video.videoname,video.imagename FROM video JOIN carousel ON video.id=carousel.video_id")
    List<Video> queryCarouselVideo();

    @Select("select * from (select * from video order by likecount desc) as tmp limit 4")
    List<Video> queryTopVideo();

    @Select("select video.id,video.videoname,video.imagename,video.likecount,video.reportcount FROM video JOIN popularize ON video.id=popularize.video_id")
    List<Video> queryPopularizeVideo();

    @Select("select COUNT(*) from popularize where video_id=#{video_id}")
    int queryPopularizeByVideoid(@Param("video_id") int video_id);

    @Select("select COUNT(*) from Carousel where video_id=#{video_id}")
    int queryCarouselByVideoid(@Param("video_id") int video_id);

    @Select("select video.id,video.videoname,video.imagename from video where videoname like concat('%',#{search},'%')")
    List<Video> queryVideoBySearch(@Param("search") String search);

    @Select("select * from video order by reportcount desc limit #{begin},#{rows}")
    List<Video> queryPageVideo(@Param("begin")int begin,@Param("rows")int rows);

    @Select("select * from video order by likecount desc limit #{begin},#{rows}")
    List<Video> queryPageVideoByLikecount(@Param("begin")int begin,@Param("rows")int rows);

    @Select("SELECT COUNT(*) FROM video")
    int countVideo();

    @Delete("delete from video WHERE id=#{id}")
    void deleteVideo(@Param("id")int id);

    @Delete("delete from comment WHERE video_id=#{video_id}")
    void deleteVideoComment(@Param("video_id")int video_id);

    @Delete("delete from carousel WHERE video_id=#{video_id}")
    void deleteCarousel(@Param("video_id")int video_id);

    @Delete("delete from popularize WHERE video_id=#{video_id}")
    void deletePopularize(@Param("video_id")int video_id);

    @Insert("insert into popularize(video_id)values(#{video_id})")
    void addPopularize(@Param("video_id")int video_id);

    @Insert("insert into carousel(video_id)values(#{video_id})")
    void addCarousel(@Param("video_id")int video_id);

    @Insert("insert into video(videoname,uuid,imagename,introduction,user_id)values(#{videoname},#{uuid},#{imagename},#{introduction},#{user_id})")
    void addVideo(@Param("videoname")String videoname,@Param("uuid")String uuid,@Param("imagename")String imagename,@Param("introduction")String introduction,@Param("user_id")int user_id);

    @Select("select max(id) from video")
    int querylastVideoid();

    @Select("select * from video where user_id=#{user_id}")
    List<Video> queryVideoByUserid(@Param("user_id") int user_id);
}

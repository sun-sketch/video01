package com.dao;

import com.domain.Video;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CollectDao {
    @Insert("insert into collect(user_id,video_id)values(#{user_id},#{video_id})")
    void addVideoCollect(@Param("user_id")int user_id, @Param("video_id")int video_id);

    @Select("select count(*) from collect where user_id=#{user_id} AND video_id=#{video_id}")
    int queryJudgeVideoCollect(@Param("user_id")int user_id,@Param("video_id")int video_id);

    @Select("select video.id,video.videoname,video.imagename FROM collect JOIN video ON video.id=collect.video_id JOIN user ON user.id=collect.user_id WHERE collect.user_id=#{user_id}")
    List<Video> queryCollectVideo(@Param("user_id") int user_id);

    @Delete("delete from collect WHERE user_id=#{user_id} AND video_id=#{video_id}")
    void deleteCollect(@Param("user_id")int user_id,@Param("video_id")int video_id);

    @Delete("delete from collect WHERE user_id=#{user_id}")
    void deleteUserCollect(@Param("user_id")int user_id);

    @Delete("delete from collect WHERE video_id=#{video_id}")
    void deleteVideoCollect(@Param("video_id")int video_id);
}

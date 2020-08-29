package com.dao;

import com.domain.Video;
import com.domain.Zone;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ZoneDao {

    @Select("select zone.zonename from zone JOIN video_zone ON zone.id=video_zone.zone_id JOIN video ON video_zone.video_id=video.id WHERE video.id=#{videoid}")
    String queryZoneByVideoid(@Param("videoid") int videoid);

    @Select("select * from zone")
    List<Zone> queryAllZone();

    @Select("select video.id,video.videoname,video.imagename from video JOIN video_zone ON video.id=video_zone.video_id JOIN zone ON zone.id=video_zone.zone_id WHERE zone.zonename=#{zonename}")
    List<Video> queryZoneVideo(@Param("zonename") String zonename);

    @Select("select * from (select video.id,video.videoname,video.imagename,video.likecount from video JOIN video_zone ON video.id=video_zone.video_id JOIN zone ON zone.id=video_zone.zone_id WHERE zone.zonename=#{zonename}) as tmp order by likecount desc limit #{begin},#{rows}")
    List<Video> queryZoneByLikecount(@Param("zonename") String zonename,@Param("begin")int begin,@Param("rows")int rows);

    @Select("select * from (select video.id,video.videoname,video.imagename,video.time from video JOIN video_zone ON video.id=video_zone.video_id JOIN zone ON zone.id=video_zone.zone_id WHERE zone.zonename=#{zonename}) as tmp order by time desc limit #{begin},#{rows}")
    List<Video> queryZoneByTime(@Param("zonename") String zonename,@Param("begin")int begin,@Param("rows")int rows);

    @Update("UPDATE zone SET zonename=#{zonename} WHERE id=#{id}")
    void alterZone(@Param("zonename")String zonename,@Param("id")int id);

    @Delete("delete from zone WHERE id=#{id}")
    void deleteZone(@Param("id")int id);

    @Insert("insert into zone(zonename)values(#{zonename})")
    void addZone(@Param("zonename")String zonename);

    @Delete("delete video,video_zone from video inner join video_zone on video.id = video_zone.video_id where video_zone.zone_id=#{zone_id}")
    void deleteZoneVideo(@Param("zone_id")int zone_id);

    @Select("select id from zone where zonename=#{zonename}")
    int queryZoneidByZonename(@Param("zonename")String zonename);

    @Select("select COUNT(*) from video JOIN video_zone ON video.id=video_zone.video_id JOIN zone ON zone.id=video_zone.zone_id WHERE zone.zonename=#{zonename}")
    int queryZoneVideoCount(@Param("zonename")String zonename);

    @Insert("insert into video_zone(video_id,zone_id)values(#{video_id},#{zone_id})")
    void addZoneVideo(@Param("video_id")int video_id,@Param("zone_id")int zone_id);
}

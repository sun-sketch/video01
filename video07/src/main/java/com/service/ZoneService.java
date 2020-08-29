package com.service;

import com.domain.Video;
import com.domain.Zone;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ZoneService {
    String queryZoneByVideoid(int videoid);

    List<Zone> queryAllZone();

    List<Video> queryZoneVideo(String zonename);

    List<Video> queryZoneByLikecount(String zonename,int begin,int rows);

    List<Video> queryZoneByTime(String zonename,int begin,int rows);

    void alterZone(String zonename,int id);

    void deleteZone(int id);

    void addZone(String zonename);

    int queryZoneidByZonename(String zonename);

    int queryZoneVideoCount(String zonename);

    void addZoneVideo(int video_id,int zone_id);
}

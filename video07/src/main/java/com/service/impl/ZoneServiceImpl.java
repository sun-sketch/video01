package com.service.impl;

import com.dao.ZoneDao;
import com.domain.Video;
import com.domain.Zone;
import com.service.ZoneService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {
    @Autowired
    private ZoneDao zoneDao;

    public String queryZoneByVideoid(int videoid){
        return zoneDao.queryZoneByVideoid(videoid);
    }

    public List<Zone> queryAllZone(){
        return zoneDao.queryAllZone();
    }

    public List<Video> queryZoneVideo(String zonename){
        return zoneDao.queryZoneVideo(zonename);
    }

    @Override
    public List<Video> queryZoneByLikecount(String zonename, int begin, int rows) {
        return zoneDao.queryZoneByLikecount(zonename,begin,rows);
    }

    @Override
    public List<Video> queryZoneByTime(String zonename, int begin, int rows) {
        return zoneDao.queryZoneByTime(zonename,begin,rows);
    }

    public void alterZone(String zonename,int id){
        zoneDao.alterZone(zonename,id);
    }

    public void deleteZone(int id){
        zoneDao.deleteZoneVideo(id);
        zoneDao.deleteZone(id);
    }
    public void addZone(String zonename){
        zoneDao.addZone(zonename);
    }
    public int queryZoneidByZonename(String zonename) {
        return zoneDao.queryZoneidByZonename(zonename);
    }

    @Override
    public int queryZoneVideoCount(String zonename) {
        return zoneDao.queryZoneVideoCount(zonename);
    }

    public void addZoneVideo(int video_id, int zone_id) {
        zoneDao.addZoneVideo(video_id,zone_id);
    }


}

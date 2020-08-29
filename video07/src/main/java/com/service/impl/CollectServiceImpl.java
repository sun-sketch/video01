package com.service.impl;

import com.dao.CollectDao;
import com.dao.VideoDao;
import com.domain.User;
import com.domain.Video;
import com.service.CollectService;
import com.service.VideoService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectDao collectDao;

    @Override
    public void addVideoCollect(int user_id, int video_id) {
        collectDao.addVideoCollect(user_id,video_id);
    }

    @Override
    public int queryJudgeVideoCollect(int user_id, int video_id) {
        return collectDao.queryJudgeVideoCollect(user_id,video_id);
    }
    @Override
    public List<Video> queryCollectVideo(int user_id){
        return collectDao.queryCollectVideo(user_id);
    }

    @Override
    public void deleteCollect(int user_id,int video_id) {
        collectDao.deleteCollect(user_id,video_id);
    }

    @Override
    public void delectSelectedCollect(String[] videoids) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(videoids != null && videoids.length > 0){
            //1.遍历数组
            for (String videoid : videoids) {
                //2.调用dao删除
                collectDao.deleteCollect(user.getId(),Integer.parseInt(videoid));
            }
        }
    }

    @Override
    public void deleteVideoCollect(int video_id) {
        collectDao.deleteVideoCollect(video_id);
    }
}

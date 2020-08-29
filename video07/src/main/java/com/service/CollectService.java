package com.service;

import com.domain.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectService {

    void addVideoCollect(int user_id,int video_id);

    int queryJudgeVideoCollect(int user_id,int video_id);

    List<Video> queryCollectVideo(int user_id);

    void deleteCollect(int user_id,int video_id);

    void delectSelectedCollect(String[] videoids);

    void deleteVideoCollect(int video_id);
}

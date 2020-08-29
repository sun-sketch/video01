package com.service;

import com.domain.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CommentService {
    List<Comment> queryCommentByVideoid(int videoid);

    int commentCountByVideoid(int videoid);

    void insertComment(String content,int video_id,int user_id);

    int querylastCommentid();


    void likecountadd(int video_id,int index);

    void reportcountadd(int video_id,int index);

    void deleteComment(int id);

    List<Comment> queryPageCommentByReportcount(int begin, int rows);

    int countComment();

    void deleteUserComment(int user_id);

}

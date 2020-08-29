package com.service.impl;

import com.dao.CommentDao;
import com.dao.UserDao;
import com.domain.Comment;
import com.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserDao userDao;

    public List<Comment> queryCommentByVideoid(int videoid){
        return commentDao.queryCommentByVideoid(videoid);
    }

    @Override
    public int commentCountByVideoid(int videoid) {
        return commentDao.commentCountByVideoid(videoid);
    }


    public void insertComment(String content,int video_id,int user_id){
        commentDao.insertComment(content,video_id,user_id);
    }

    public int querylastCommentid() {
        return commentDao.querylastCommentid();
    }


    public void likecountadd(int video_id,int index){
        List<Comment> comments=commentDao.queryCommentByVideoid(video_id);
        int comment_id=comments.get(index).getId();
        int user_id=comments.get(index).getUser_id();
        commentDao.likecountadd(comment_id);
        userDao.likecountadd(user_id);
    }

    public void reportcountadd(int video_id,int index){
        List<Comment> comments=commentDao.queryCommentByVideoid(video_id);
        int comment_id=comments.get(index).getId();
        int user_id=comments.get(index).getUser_id();
        commentDao.reportcountadd(comment_id);
        userDao.reportcountadd(user_id);
    }

    public void deleteComment(int id){
        commentDao.deleteComment(id);
    }

    public List<Comment> queryPageCommentByReportcount(int begin, int rows){
        return commentDao.queryPageCommentByReportcount(begin,rows);
    }

    public int countComment(){
        return commentDao.countComment();
    }

    @Override
    public void deleteUserComment(int user_id) {
        commentDao.deleteUserComment(user_id);
    }

}

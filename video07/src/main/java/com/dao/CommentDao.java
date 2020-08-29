package com.dao;

import com.domain.Comment;
import com.domain.Video;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommentDao {
    @Select("select comment.*,user.username from comment join user on comment.user_id=user.id WHERE video_id=#{videoid}")
    List<Comment> queryCommentByVideoid(@Param("videoid") int videoid);

    @Select("select count(*) from comment WHERE video_id=#{videoid}")
    int commentCountByVideoid(@Param("videoid") int videoid);

    @Insert("insert into comment(content,video_id,user_id)values(#{content},#{video_id},#{user_id}) ")
    void insertComment(@Param("content") String content,@Param("video_id")int video_id,@Param("user_id")int user_id);

    @Select("select max(id) from comment")
    int querylastCommentid();

    @Update("update comment SET likecount=likecount+1 WHERE id=#{comment_id}")
    void likecountadd(@Param("comment_id") int comment_id);

    @Update("update comment SET reportcount=reportcount+1 WHERE id=#{comment_id}")
    void reportcountadd(@Param("comment_id") int comment_id);

    @Select("select * from comment order by reportcount desc limit #{begin},#{rows}")
    List<Comment> queryPageCommentByReportcount(@Param("begin")int begin, @Param("rows")int rows);

    @Select("SELECT COUNT(*) FROM comment")
    int countComment();

    @Delete("delete from comment WHERE id=#{id}")
    void deleteComment(@Param("id")int id);

    @Delete("delete from comment WHERE user_id=#{user_id}")
    void deleteUserComment(@Param("user_id")int user_id);

}

package com.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
    private int id;
    private String content;
    private Date time;
    private int likecount;
    private int reportcount;
    private int video_id;
    private int user_id;
    private String username;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        String time1 = sdf.format(time);
        return time1;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }

    public int getReportcount() {
        return reportcount;
    }

    public void setReportcount(int reportcount) {
        this.reportcount = reportcount;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", likecount=" + likecount +
                ", reportcount=" + reportcount +
                ", video_id=" + video_id +
                ", user_id=" + user_id +
                ", username='" + username + '\'' +
                '}';
    }
}

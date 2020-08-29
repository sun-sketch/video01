package com.domain;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Video {
    private int id;
    private String videoname;
    private String imagename;
    private Date time;
    private String introduction;
    private int likecount;
    private int reportcount;
    private String uuid;
    private int user_id;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public String getTime() {
        String time1 = sdf.format(time);
        return time1;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
        return "Video{" +
                "id=" + id +
                ", videoname='" + videoname + '\'' +
                ", imagename='" + imagename + '\'' +
                ", time='" + time + '\'' +
                ", introduction='" + introduction + '\'' +
                ", likecount=" + likecount +
                ", reportcount=" + reportcount +
                ", uuid='" + uuid + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}

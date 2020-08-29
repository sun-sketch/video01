package com.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Upload {
    private int id;
    private String uuid;
    private String videoname;
    private String imagename;
    private Date time;
    private String introduction;
    private String zonename;
    private int user_id;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getZonename() {
        return zonename;
    }

    public void setZonename(String zonename) {
        this.zonename = zonename;
    }



    @Override
    public String toString() {
        return "Upload{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", videoname='" + videoname + '\'' +
                ", imagename='" + imagename + '\'' +
                ", time='" + time + '\'' +
                ", introduction='" + introduction + '\'' +
                ", zonename='" + zonename + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}

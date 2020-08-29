package com.domain;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String tel;
    private String e_mail;
    private String sex;
    private int likecount;
    private int reportcount;
    private String personality;

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", e_mail='" + e_mail + '\'' +
                ", sex='" + sex + '\'' +
                ", likecount=" + likecount +
                ", reportcount=" + reportcount +
                ", personality='" + personality + '\'' +
                '}';
    }
}

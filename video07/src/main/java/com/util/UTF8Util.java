package com.util;

import com.domain.Comment;
import com.domain.User;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class UTF8Util {
    public static User UserUtf8(User user) throws UnsupportedEncodingException {
        user.setUsername(new String(user.getUsername().getBytes("ISO-8859-1"), "utf-8"));
        user.setPassword(new String(user.getPassword().getBytes("ISO-8859-1"), "utf-8"));
        user.setTel(new String(user.getTel().getBytes("ISO-8859-1"), "utf-8"));
        user.setE_mail(new String(user.getE_mail().getBytes("ISO-8859-1"), "utf-8"));
        user.setSex(new String(user.getSex().getBytes("ISO-8859-1"), "utf-8"));
        user.setPersonality(new String(user.getPersonality().getBytes("ISO-8859-1"), "utf-8"));
        return user;
    }
    
//    public List<Comment> CommentUtf8(List<Comment> comments) throws Exception {
//        for (int i = 0; i < comments.size(); i++) {
//            comments.get(i).setContent(new String(comments.get(i).getContent().getBytes("ISO-8859-1"), "utf-8"));
//        }
//    }
}

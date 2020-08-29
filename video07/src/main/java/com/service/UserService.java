package com.service;

import com.domain.User;
import com.domain.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface UserService {
    User queryUserByUserid(int id);
    User queryUserByUsername(String username);
    Integer insertUser(User user);
    void likecountadd(int user_id);
    void reportcountadd(int user_id);
    void updateuser(String username,int tel,String e_mail,String sex,String personality);
    List<User> queryUserByReportcount();
    void deleteUser(int id);
    Set<String> queryUesrRole(String username);
    String queryDeletedUser(int user_id);
    void insertDeletedUser(int user_id,String username);
}

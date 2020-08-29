package com.dao;

import com.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

public interface UserDao {
    @Select("select * from user where id=#{id}")
    User queryUserByUserid(@Param("id") int id);

    @Select("select * from user where username=#{username}")
    User queryUserByUsername(@Param("username") String username);

    @Insert("insert into user (username,password,tel,e_mail,sex) values (#{username},#{password},#{tel},#{e_mail},#{sex})")
    Integer insertUser(User user);

    @Update("update user SET likecount=likecount+1 WHERE id=#{user_id}")
    void likecountadd(@Param("user_id") int user_id);

    @Update("update user SET reportcount=reportcount+1 WHERE id=#{user_id}")
    void reportcountadd(@Param("user_id") int user_id);

    @Update("update user set tel=#{tel},e_mail=#{e_mail},sex=#{sex},personality=#{personality} where username=#{username};")
    void updateuser(@Param("username")String username,@Param("tel")int tel,@Param("e_mail")String e_mail,@Param("sex")String sex,@Param("personality")String personality);

    @Select("select * from user where role=\"user\" order by reportcount desc")
    List<User> queryUserByReportcount();

    @Delete("delete from user where id=#{id}")
    void deleteUser(@Param("id")int id);

    @Select("select role from user WHERE username=#{username}")
    Set<String> queryUesrRole(@Param("username")String username);

    @Select("select username from deleteduser WHERE user_id=#{user_id}")
    String queryDeletedUser(@Param("user_id")int user_id);

    @Insert("insert into deleteduser (user_id,username) values (#{user_id},#{username})")
    void insertDeletedUser(@Param("user_id") int user_id,@Param("username") String username);
}

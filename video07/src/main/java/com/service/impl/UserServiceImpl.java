package com.service.impl;

import com.dao.CollectDao;
import com.dao.CommentDao;
import com.dao.UploadDao;
import com.dao.UserDao;
import com.domain.User;
import com.service.UploadService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

;import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private CollectDao collectDao;
    @Autowired
    private CommentDao commentDao;

    @Override
    public User queryUserByUserid(int id) {
        return userDao.queryUserByUserid(id);
    }

    @Override
    public User queryUserByUsername(String username) {
        return userDao.queryUserByUsername(username);
    }

    @Override
    public Integer insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public void likecountadd(int user_id) {
        userDao.likecountadd(user_id);
    }

    @Override
    public void reportcountadd(int user_id) {
        userDao.reportcountadd(user_id);
    }

    public void updateuser(String username,int tel,String e_mail,String sex,String personality){
        userDao.updateuser(username,tel,e_mail,sex,personality);
    }

    @Override
    public List<User> queryUserByReportcount() {
        return userDao.queryUserByReportcount();
    }
    public void deleteUser(int id){
        User user=userDao.queryUserByUserid(id);
        userDao.insertDeletedUser(user.getId(),user.getUsername());
        userDao.deleteUser(id);
    }

    @Override
    public Set<String> queryUesrRole(String username) {
        return userDao.queryUesrRole(username);
    }

    @Override
    public String queryDeletedUser(int user_id) {
        return userDao.queryDeletedUser(user_id);
    }

    @Override
    public void insertDeletedUser(int user_id, String username) {
        userDao.insertDeletedUser(user_id,username);
    }
}

package com.realm;

import com.domain.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    /**
     * 作用：查询身份信息，并返回即可，不用任何比对
     * 查询方式：通过用户名查询用户信息。
     * 何时触发：subject.login(token);
     * @param token
     * @return
     * @throws AuthenticationException
     */

    @Autowired
    private UserService userServiceImpl;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("在Realm中查询身份");
        // 获取用户登录时发送来的用户名
        String username = (String)token.getPrincipal();
        // 查询用户信息： UserService:public User queryUserByUsername(String username);
        //查询到用户信息
        User user = userServiceImpl.queryUserByUsername(username);
        System.out.println(user.toString());
        //判断用户信息是否为null
        if(user==null){//不存在用户名
            return null;//在后续流程中会抛出异常  UnknownAccountException
        }
        System.out.println(token.getPrincipal()+","+token.getCredentials());
        if(token.getCredentials().equals(user.getPassword())){
            System.out.println("true");
        }
        System.out.println("false");
        // 将用户信息封装在  AuthenticationInfo 中
        return new SimpleAuthenticationInfo(user,//数据库中用户名
                user.getPassword(), //数据库中的密码
                this.getName());// realm的标识


    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("在realm中 查询权限");
        // 获取当前用户的信息
        User user = (User)principalCollection.getPrimaryPrincipal();
        // 查询当前用户的角色信息
        Set<String> roles = userServiceImpl.queryUesrRole(user.getUsername());
        System.out.println(roles);
        // 将查询出的信息 封装
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
        return simpleAuthorizationInfo;
    }
}
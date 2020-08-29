<%@ page import="com.domain.User" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    User user = (User) SecurityUtils.getSubject().getPrincipal();
    request.setAttribute("user",user);
%>
<head>
    <title>Title</title>
    <style type="text/css">
        .home{
            font: normal 700 30px/100px "SimSun";
            color: #ad5d80;
        }
    </style>
</head>
<body>
<p class="home">欢迎管理员${requestScope.user.username}， ♪(^∇^*)</p>
</body>
</html>

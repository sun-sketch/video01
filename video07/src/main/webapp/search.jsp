<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>搜索</title>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <script type="text/javascript"
            src="${APP_PATH }/static/js/jquery-3.2.1.min.js">
    </script>
    <link
            href="${APP_PATH }/static/css/bootstrap.min.css"
            rel="stylesheet">
    <script
            src="${APP_PATH }/static/js/bootstrap.min.js">
    </script>
    <link href="${APP_PATH }/static/css/base.css">
    <style type="text/css">
        .middle-one{
            width: 1100px;
            margin: 0 auto;
        }
        .middle-one-a img{
            width: 240px;
            height: 133px;
        }
        .middle-one-a{
            margin: 10px 15px;
            float: left;
            width: 240px;
            height: 155px;
            overflow: hidden;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default">
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/user/home">首页 </a></li>

            <c:forEach items="${zones}" var="zone" varStatus="s">
                <li><a href="${pageContext.request.contextPath}/user/zonelike?zonename=${zone.zonename}&currentPage=1">${zone.zonename}</a></li>
            </c:forEach>
        </ul>
        <form class="navbar-form navbar-left" action="${pageContext.request.contextPath}/user/search">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Search" name="search">
            </div>
            <button type="submit" class="btn btn-default">搜索</button>
        </form>
        <ul class="nav navbar-nav navbar-right">
            <shiro:user>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${user.username} <span class="caret"></span></a>
                    <ul class="dropdown-menu user-menu">
                        <li><a href="${pageContext.request.contextPath}/user/user" target="_blank">个人信息</a></li>
                        <li><a href="${pageContext.request.contextPath}/upload.jsp" target="_blank">投稿</a></li>
                        <li><a href="${pageContext.request.contextPath}/user/collect">收藏夹</a></li>
                    </ul>
                </li>
                <li><a href="${pageContext.request.contextPath}/user/logout">退出登录</a></li>
                <shiro:hasRole name="admin">
                    <li><a href="${pageContext.request.contextPath}/admin/cms">返回管理系统</a></li>
                </shiro:hasRole>
            </shiro:user>
            <shiro:guest>
                <li><a href="${pageContext.request.contextPath}/user/login">登录</a></li>
                <li><a href="${pageContext.request.contextPath}/user/register">注册</a></li>
            </shiro:guest>
        </ul>
    </div><!-- /.navbar-collapse -->
</nav>
<div class="middle-one clearfix" id="video">
    <c:forEach items="${videos}" var="video" varStatus="s">
        <div class="middle-one-a">
            <a href="${pageContext.request.contextPath}/user/content?i=${video.id}">
                <img title="${video.videoname}" src="${pageContext.request.contextPath}/img/${video.imagename}"/><br/>
                <p>${video.videoname}</p>
            </a>
        </div>
    </c:forEach>
</div>
</body>
</html>

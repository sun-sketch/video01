<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>投稿状态</title>
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
</head>
<style type="text/css">
    .user-menu{
        background-color: #f8f8f8;
    }
    .middle{
        width: 1100px;
        margin: 0 auto;
    }
    .middle-h{
        margin-top: 10px;
        background-color: #f0f0f0;
        height: 50px;
    }
    .middle-h span{
        font: normal 700 30px/50px "SimSun";
        margin-left: 20px;
        color: #444444;
    }
</style>
<script>
    function deleteupload(r,id){
        if(confirm("确定删除该视频")){
            var i= r.parentNode.parentNode.rowIndex;
            $.ajax({  url:"${pageContext.request.contextPath}/deleteUploadVideoServlet?id="+id,
                success:function(){
                    document.getElementById("mytable").deleteRow(i);
                } });
        }

    }

    function deletevideo(r,id){
        if(confirm("确定删除该视频")){
            var i= r.parentNode.parentNode.rowIndex;
            $.ajax({  url:"${pageContext.request.contextPath}/deleteVideoServlet?id="+id,
                success:function(){
                    document.getElementById("mytable2").deleteRow(i);
                } });
        }

    }
</script>
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
<div class="middle">
    <div class="middle-h">
        <span>待审核</span>
    </div>
    <table class="table" id="mytable">
        <tr>
            <th>序号</th>
            <th>视频名</th>
            <th>上传时间</th>
            <th>删除视频</th>
        </tr>
        <c:forEach items="${uploads}" var="upload" varStatus="s">
            <tr>
                <td>${s.count}</td>
                <td>${upload.videoname}</td>
                <td>${upload.time}</td>
                <td><button class="btn btn-default" type="submit" onclick="deleteupload(this,${upload.id})">删除</button></td>
            </tr>
        </c:forEach>
    </table>
    <div class="middle-h">
        <span>审核通过</span>
    </div>
    <table class="table" id="mytable2">
        <tr>
            <th>序号</th>
            <th>视频名</th>
            <th>通过时间</th>
            <th>删除视频</th>
        </tr>
        <c:forEach items="${videos}" var="video" varStatus="s">
            <tr>
                <td>${s.count}</td>
                <td><a href="${pageContext.request.contextPath}/user/content?i=${video.id}">${video.videoname}</a></td>
                <td>${video.time}</td>
                <td><button class="btn btn-default" type="submit" onclick="deletevideo(this,${video.id})">删除</button></td>
            </tr>
        </c:forEach>
    </table>
    </div>
</div>
</body>
</html>

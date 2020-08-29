<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>管理首页</title>
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
        .top{
            position: relative;
            height: 100px;
            background-color: #fc97c5;
        }
        .cms{
            font: normal 700 30px/100px "SimSun";
            color: #ffffff;
            margin-left: 40px;
        }
        #left{
            width: 300px;
            height: 525px;
            border-right: solid 1px #837076;
            margin: 0;
            float: left;
        }
        .name{
            position: absolute;
            font: normal 700 20px/100px "SimSun";
            color: #ffffff;
            right: 20px;
        }
        .right{
            float: left;
            width: 1000px;
            height: 525px;
            text-align: center;
            padding: 0 20px;
        }
        .main{
            width: 1340px;
            height: 525px;
        }
    </style>
    <script>
        window.onload = function(){
            $.ajax({  url:"${pageContext.request.contextPath}/cmshome.jsp",
                type:"get",
                dataType:"html",
                success:function(msg){
                    $("#right").html(msg);
                } });
            $("#home").mousedown(function(){
                $.ajax({  url:"${pageContext.request.contextPath}/cmshome.jsp",
                    async:false,
                    type:"get",
                    dataType:"html",
                    success:function(msg){
                        $("#right").html(msg);
                    } });
            });
            $("#user").mousedown(function(){
                $.ajax({  url:"${pageContext.request.contextPath}/admin/cmsuser",
                    type:"get",
                    dataType:"html",
                    success:function(msg){
                        $("#right").html(msg);
                    } });
            });
            $("#comment").mousedown(function(){
                $.ajax({  url:"${pageContext.request.contextPath}/admin/cmscomment?currentPage=1",
                    type:"get",
                    dataType:"html",
                    success:function(msg){
                        $("#right").html(msg);
                    } });
            });
            $("#video").mousedown(function(){
                $.ajax({  url:"${pageContext.request.contextPath}/admin/cmsvideo?currentPage=1",
                    async:false,
                    type:"get",
                    dataType:"html",
                    success:function(msg){
                        $("#right").html(msg);
                    } });
            });
            $("#carousel").mousedown(function(){
                $.ajax({  url:"${pageContext.request.contextPath}/admin/cmscarousel",
                    type:"get",
                    dataType:"html",
                    success:function(msg){
                        $("#right").html(msg);
                    } });
            });
            $("#videozone").mousedown(function(){
                $.ajax({  url:"${pageContext.request.contextPath}/admin/cmszone",
                    type:"get",
                    dataType:"html",
                    success:function(msg){
                        $("#right").html(msg);
                    } });
            });

            $("#popularize").mousedown(function(){
                $.ajax({  url:"${pageContext.request.contextPath}/admin/cmspopularize",
                    type:"get",
                    dataType:"html",
                    success:function(msg){
                        $("#right").html(msg);
                    } });
            });
        }
    </script>
</head>
<body>
<div class="top">
    <span class="cms">运维管理系统</span>
    <span class="name">管理员：${user.username}</span>
</div>
<div class="main">
    <div class="list-group" id="left">
        <a href="#" class="list-group-item" id="home">首页</a>
        <a href="#" class="list-group-item" id="user">用户管理</a>
        <a href="#" class="list-group-item" id="comment">评论管理</a>
        <a href="#" class="list-group-item" id="carousel">轮播配置</a>
        <a href="#" class="list-group-item" id="videozone">视频分类管理</a>
        <a href="#" class="list-group-item" id="video">视频管理</a>
        <a href="#" class="list-group-item" id="popularize">推广管理</a>
        <a href="${pageContext.request.contextPath}/admin/logout" class="list-group-item" id="logout">退出登录</a>
        <a href="${pageContext.request.contextPath}/user/home" class="list-group-item">去前台</a>
    </div>
    <div class="right" id="right">

    </div>
</div>
</body>
</html>

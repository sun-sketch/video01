<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>投稿视频信息页</title>
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
        .content{
            width: 980px;
            margin: 10px auto;
        }
        #img{
            width: 300px;
            height: 168px;
        }

    </style>
    <script>

    </script>
</head>
<body>
<div>
    <h1>投稿视频信息</h1>
    <div class="content">

        <table class="table" id="mytable">
            <tr>
                <td>视频名</td>
                <td>${upload.videoname}</td>
            </tr>
            <tr>
                <td>投稿人</td>
                <td>${username}</td>
            </tr>
            <tr>
                <td>投稿时间</td>
                <td>${upload.time}</td>
            </tr>
            <tr>
                <td>分区</td>
                <td>${upload.zonename}</td>
            </tr>
            <tr>
                <td>视频封面</td>
                <td><img id="img" src="${pageContext.request.contextPath}/static/img/${upload.imagename}" alt=""/></td>
            </tr>
            <tr>
                <td>视频</td>
                <td>
                    <video src="${pageContext.request.contextPath}/upload/${upload.uuid}.mp4" controls="controls" width="300px" height="168px"></video>
                </td>
            </tr>
            <tr>
                <td>视频简介</td>
                <td>${upload.introduction}</td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>

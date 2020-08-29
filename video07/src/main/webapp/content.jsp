<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>视频详情页</title>
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
        .video,.comment,.content{
            width: 1280px;
            margin: 0 auto;
        }
        #img{
            width: 350px;
            height: 194px;
        }
        .img{
            width: 350px;
            height: 200px;
            float: left;
        }
        .title{
            position: relative;
            float: left;
            width: 600px;
            height: 194px;
        }
        .video{
            margin-top: 20px;
            height: 200px;
        }
        .video_name{
            position: absolute;
            text-decoration:none ;/*没有文本装饰*/
            font-size: 22px;
            top:10px;
        }
        .title_time{
            position: absolute;
            bottom: 20px;;
        }
        #download{
            color: #333;
        }
        #download:hover{
            color:#333;
            text-decoration: none;
        }
        .comment{
            margin-top: 20px;
        }
        .comment-content{
            padding: 20px 20px 0;
            border-bottom: 1px solid #d1d1d1;
        }
        .comment-area{
            margin-top: 20px;
            resize: none;
            width: 700px;
            height: 70px;
        }
        .usercommentlogin{
            width:1100px;
            height: 200px;
            text-align: center;
            line-height: 100px;
        }
        .pre-introduction{
            background-color: #ffffff;
        }
        .pre-comment{
            background-color: #ffffff;
            border: 0 none;
        }
    </style>
    <script>
        window.onload = function(){
            var likecount=${video.likecount};
            var reportcount=${video.reportcount};
            $("#comment2").hide();
            $("#comment1").show();
            $("#video-like").mousedown(function(){
                $.ajax({
                    url:"${pageContext.request.contextPath}/videolikeCountServlet",
                    data:"videoid=${video.id}",
                    success:function(msg){
                        likecount=likecount+1;
                        $("#likecount").html(likecount);
                        alert("视频点赞成功");
                        }
                    });

            });
            $("#video-report").mousedown(function(){
                $.ajax({
                    url:"${pageContext.request.contextPath}/videoReportCountServlet",
                    data:"videoid=${video.id}",
                    success:function(msg){
                        reportcount=reportcount+1;
                        $("#reportcount").html(reportcount);
                        alert("视频举报成功");
                    }
                });

            });
            $("#video-collect").mousedown(function(){
                $.ajax({
                    url:"${pageContext.request.contextPath}/videoCollectServlet",
                    data:"videoid=${video.id}",
                    success:function(msg){
                        alert(msg);
                    }
                });

            });
            $("#presentation1").mousedown(function(){
                $("#presentation1").addClass("active");
                $("#presentation2").removeClass("active");
                $("#comment2").hide();
                $("#comment1").show();
            });
            $("#presentation2").mousedown(function(){
                $("#presentation2").addClass("active");
                $("#presentation1").removeClass("active");
                $("#comment1").hide();
                $("#comment2").show();
            });
        }
        function commentlike(index) {
            $.ajax({
                url:"${pageContext.request.contextPath}/commentlikeCountServlet?i=${video.id}&index="+index,
                success:function(){
                    alert("点赞成功，可刷新后查看变化。");
                }
            });
        }
        function commentreport(index) {
            $.ajax({
                url:"${pageContext.request.contextPath}/commentReportCountServlet?i=${video.id}&index="+index,
                success:function(){
                    alert("举报成功，可刷新后查看变化。");
                }
            });
        }
    </script>
</head>
<body>
<div>
    <div class="video">
        <div  class="img" >
            <a href="http://127.0.0.1:5080/oflaDemo/video.jsp?videoname=${video.videoname}&uuid=${video.uuid}" target="_blank"><img src="${pageContext.request.contextPath}/static/img/${video.imagename}" id="img"/></a>
<%--            <a target="_blank" href="http://31135774vq.zicp.vip/oflaDemo/video.jsp?videoname=${requestScope.videoattr.videoname}&uuid=${requestScope.videoattr.uuid}"><img src="${pageContext.request.contextPath}/static/img/${requestScope.videoattr.imagename}" id="img"/></a>--%>
        </div>
        <div class="title">
            <a href="http://127.0.0.1:5080/oflaDemo/video.jsp?videoname=${video.videoname}&uuid=${video.uuid}" class="video_name" target="_blank"><span>${video.videoname}</span></a>
<%--            <a target="_blank" href="http://31135774vq.zicp.vip/oflaDemo/video.jsp?videoname=${requestScope.videoattr.videoname}&uuid=${requestScope.videoattr.uuid}" class="video_name"><span>${requestScope.videoattr.videoname}</span></a>--%>
            <p class="title_time">
                发布时间:${video.time}
            </p>
        </div>
    </div>
    <div class="content">
        <div class="zone">
            <span>分区：</span>
            <span>${zonename}</span>
        </div>
        <div>
            <p>投稿者：${username}</p>
            <p>视频简介：</p>
            <pre class="pre-introduction">${video.introduction}</pre>
            <shiro:user>
                <button type="button" class="btn btn-default btn-lg" id="video-like">
                    <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> 点赞<span id="likecount">${video.likecount}</span>
                </button>
                <button type="button" class="btn btn-default btn-lg" id="video-report">
                    <span class="glyphicon glyphicon-hand-down" aria-hidden="true"></span> 举报<span id="reportcount">${video.reportcount}</span>
                </button>
                <button type="button" class="btn btn-default btn-lg" id="video-download">
                    <a id="download" href="${pageContext.request.contextPath}/updown/download?videoname=${video.videoname}&uuid=${video.uuid}">
                        <span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span> 下载
                    </a>
                </button>
                <button type="button" class="btn btn-default btn-lg" id="video-collect">
                    <span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span> 收藏
                </button>
<%--                <a href="${pageContext.request.contextPath}/updown/download?videoname=${requestScope.videoattr.videoname}&uuid=${requestScope.videoattr.uuid}">下载</a>--%>
            </shiro:user>
        </div>
    </div>
    <div class="comment">
        <ul class="nav nav-tabs">
            <li role="presentation" class="active" id="presentation1"><a href="#">评论（${count}）</a></li>
            <li role="presentation" id="presentation2"><a href="#">进行评论</a></li>
        </ul>
        <div id="comment1">
                <c:forEach items="${comments}" var="comment" varStatus="s">
                    <div class="comment-content">
                        <p>用户：${comment.username}</p>
                        <pre class="pre-comment">${comment.content}</pre>
                        <shiro:user>
                            <a href="#" class="like" onclick="commentlike(${s.index})">点赞</a>
                            <span>(${comment.likecount})</span>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="#" class="report" onclick="commentreport(${s.index})">举报</a>
                            <span>(${comment.reportcount})</span>&nbsp;&nbsp;&nbsp;&nbsp;
                        </shiro:user>
                        <span>${comment.time}</span>
                    </div>
                </c:forEach>
        </div>
        <div id="comment2">
            <shiro:user>
                <form class="form-horizontal" id="writecomment" action="${pageContext.request.contextPath}/writeCommentServlet?i=${video.id}" method="post">
                    <div class="form-group">
                        <div class="col-sm-10">
                            <textarea name="comment" maxlength="200" placeholder="最多评论200字" class="comment-area" from="writecomment"></textarea><br/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">评价</button>
                        </div>
                    </div>
                </form>
            </shiro:user>
            <shiro:guest>
                <div class="usercommentlogin">
                    <p>请登录后再进行评论---><span><a href="${pageContext.request.contextPath}/user/login">登录</a></span></p>
                </div>
            </shiro:guest>
        </div>
    </div>
</div>
</body>
</html>

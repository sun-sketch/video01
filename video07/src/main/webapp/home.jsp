<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>首页</title>
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
        .banner{
            width: 700px;
            height: 400px;
            float: left;
        }
        .middle,.bottom{
            width: 1280px;
            margin: 0 auto;
        }
        .middle{
            height: 420px;
        }
        .middle-r,.bottom-one-a{
            float: left;
            width: 260px;
            height: 193px;
            overflow: hidden;
        }
        .middle-r{
            margin: 10px;
        }
        .middle-r img,.bottom img{
            width: 260px;
            height: 170px;
        }
        .carousel-a{
            color:#ffffff;
        }
        .carousel-a:hover{
            color:#ffffff;
        }
        .bottom-h{
            margin-top: 10px;
            background-color: #f0f0f0;
            height: 50px;
        }
        .bottom-h span{
            font: normal 700 30px/50px "SimSun";
            margin-left: 20px;
            color: #444444;
        }
        .bottom-one-a{
            margin: 10px 30px;
        }
        .foot{
            height: 200px;
            padding: 10px;
            background-color: #f0f0f0;
            text-align: center;
        }
        .foot a,.foot p{
            font: normal 14px/18px "SimSun";
            color:#888888 ;
        }
        .foot-one a{
            padding: 0 5px;
            border-right: solid 2px #888888;
        }
        .foot a:hover{
            color: #3e4cec;
            text-decoration: none;
        }
        .user-menu{
            background-color: #f8f8f8;
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
<div class="middle">
    <div id="carousel-example-generic" class="carousel slide banner" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="1" class="active"></li>
            <c:forEach begin="2" end="${carouselssize}" var="i">
                <li data-target="#carousel-example-generic" data-slide-to="${i}"></li>
            </c:forEach>
        </ol>
        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <c:forEach items="${carousels}" var="carousel" varStatus="s">
                <c:set var="index" scope="session" value="${s.index}"/>
                <c:if test="${index==0}">
                    <div class="item active">
                        <img src="${pageContext.request.contextPath}/img/${carousel.imagename}" alt="...">
                        <div class="carousel-caption">
                            <a class="carousel-a" href="${pageContext.request.contextPath}/user/content?i=${carousel.id}"><h3>${carousel.videoname}</h3></a>
                        </div>
                    </div>
                </c:if>
                <c:if test="${index!=0}">
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/img/${carousel.imagename}" alt="...">
                        <div class="carousel-caption carousel-a">
                            <a class="carousel-a" href="${pageContext.request.contextPath}/user/content?i=${carousel.id}"><h3>${carousel.videoname}</h3></a>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <c:forEach items="${topvideos}" var="topvideo" varStatus="s">
        <div class="middle-r">
            <a href="${pageContext.request.contextPath}/user/content?i=${topvideo.id}"><img title="${topvideo.videoname}" src="${pageContext.request.contextPath}/img/${topvideo.imagename}"/><br/><p>${topvideo.videoname}</p></a>
        </div>
    </c:forEach>
</div>
<div class="bottom">
    <div class="bottom-h">
        <span>推广</span>
    </div>
    <div class="bottom-one clearfix">
        <c:forEach items="${popularizes}" var="popularize" varStatus="s">
            <div class="bottom-one-a">
                <a href="${pageContext.request.contextPath}/user/content?i=${popularize.id}"><img title="${popularize.videoname}" src="${pageContext.request.contextPath}/img/${popularize.imagename}"/><br/><p>${popularize.videoname}</p></a>
            </div>
        </c:forEach>
    </div>
</div>
<div class="foot">
    <p class="foot-one">
        <a href="http://www.shjbzx.cn/">上海互联网举报中心</a>
        <a href="http://jb.ccm.gov.cn/">12318全国文化市场举报网站</a>
        <a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=31011002002436">沪公网安备31011002002436号</a>
        <a href="mailto:userreport@bilibili.com">儿童色情信息举报专区</a>
    </p>
    <p><span>网上有害信息举报专区：</span><a href="http://report.12377.cn:13225/toreportinputNormal_anis.do">中国互联网违法和不良信息举报中心</a></p>
    <p>亲爱的市民朋友，上海警方反诈劝阻电话“962110”系专门针对避免您财产被骗受损而设，请您一旦收到来电，立即接听。</p>
</div>
</body>
</html>

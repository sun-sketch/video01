<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title><%=request.getParameter("zonename")%></title>
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
        .nav{
            margin: 0;
        }
        .top{
            width: 1100px;
            height: 50px;
            margin: 0 auto;
            padding: 0 20px;
            background-color: #f8f8f8;
            font: normal 700 20px/50px "SimSun";
        }
        .middle-one,.order-by{
            width: 1100px;
            margin: 0 auto;
        }
        .order-by{
            height: 35px;
            margin-top: 15px;
            padding-left:20px ;
        }
        .middle-one{
            position: relative;
            height: 450px;
        }
        #user-menu{
            background-color: #f8f8f8;
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
        #page{
            position: absolute;
            bottom: 20px;
            width: 700px;
            left: 200px;
            text-align: center;
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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" id="user">${user.username} <span class="caret"></span></a>
                    <ul class="dropdown-menu" id="user-menu">
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
<div class="top"><%=request.getParameter("zonename")%></div>
<div class="order-by">
    <span>排序方式：</span>
    <a href="${pageContext.request.contextPath}/user/zonelike?zonename=<%=request.getParameter("zonename")%>&currentPage=1" class="btn btn-default" role="button" id="like">最多点赞</a>
    <a href="#" class="btn btn-primary" role="button" id="time">最近发布</a>
</div>
<div class="middle-one clearfix" id="video">
    <c:forEach items="${p.list}" var="video" varStatus="s">
        <div class="middle-one-a">
            <a href="${pageContext.request.contextPath}/user/content?i=${video.id}">
                <img title="${video.videoname}" src="${pageContext.request.contextPath}/img/${video.imagename}"/><br/>
                <p>${video.videoname}</p>
            </a>
        </div>
    </c:forEach>
    <c:set var="totalpage" scope="session" value="${p.totalPage}"/>
    <c:set var="currentPage" scope="session" value="${p.currentPage}"/>
    <c:if test="${totalpage>1}">
        <nav aria-label="Page navigation" id="page">
            <ul class="pagination" id="page_ul">
                <c:if test="${currentPage == 1}">
                    <li class="disabled">
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${currentPage != 1}">
                    <li>
                        <a href="${pageContext.request.contextPath}/user/zonetime?zonename=<%=request.getParameter("zonename")%>&currentPage=${currentPage - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${totalpage}" var="i" >
                    <c:if test="${currentPage == i}">
                        <li class="active"><a class="pa" href="${pageContext.request.contextPath}/user/zonetime?zonename=<%=request.getParameter("zonename")%>&currentPage=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${currentPage != i}">
                        <li><a href="${pageContext.request.contextPath}/user/zonetime?zonename=<%=request.getParameter("zonename")%>&currentPage=${i}">${i}</a></li>
                    </c:if>
                </c:forEach>
                <c:if test="${currentPage == p.totalPage}">
                    <li class="disabled">
                        <a href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${currentPage != p.totalPage}">
                    <li>
                        <a href="${pageContext.request.contextPath}/user/zonetime?zonename=<%=request.getParameter("zonename")%>&currentPage=${currentPage + 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <span style="font-size: 25px;margin-left: 5px;">共${p.totalCount}条记录，共${totalpage}页</span>
            </ul>
        </nav>
    </c:if>
</div>

</body>
</html>

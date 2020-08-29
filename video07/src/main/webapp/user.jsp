<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>用户信息页</title>
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
        body{
            background: url("${pageContext.request.contextPath}/img/login.jpg") no-repeat;
        }
        .top{
            position: relative;
            width: 1100px;
            height: 200px;
            background: url("${pageContext.request.contextPath}/img/user-bg.png") no-repeat;
            margin: 0 auto;
            padding: 0;
        }
        .size{
            width: 130px;
            height: 130px;
        }
        .top .username{
            font: normal 700 20px/18px "SimSun";
        }
        .user{
            width: 140px;
            height: 150px;
            text-align: center;
            margin-left: 20px;
            padding-top: 120px
        }
        .middle{
            width: 1100px;
            height: 431px;
            margin: 0 auto;
            background-color: #f8f8f8;
        }
        .middle-from{
            width: 600px;
            height: 360px;
            margin: 0 auto;
            padding: 20px 0;
        }
        .user-table {
            width: 700px;
            font: normal 700 18px/40px "SimSun";
            color: #222222;
        }
        .alter-btn{
            width: 110px;
            height: 34px;
            margin: 0 50%;
        }
        .alter{
            margin: 0 50%;
        }
    </style>
    <script>
        window.onload = function(){
            $("#alter-table").show();
            $("#alter-from").hide();
            $("#username").hide();
            $(".alter-btn").mousedown(function(){
                $("#alter-table").hide();
                $("#alter-from").show();
            });
        }
    </script>
</head>
<body>
<div class="top">
    <div class="user">
        <span class="username">${user.username}</span>
    </div>
    <label>个性签名：</label>
    <span class="character">${user.personality}</span>
</div>
<div class="middle" id="middle">

    <div class="middle-from">
        <div id="alter-table">
            <table class="table table-hover user-table">
                <tr>
                    <td>电话:</td>
                    <td>${user.tel}</td>
                </tr>
                <tr>
                    <td>邮箱:</td>
                    <td>${user.e_mail}</td>
                </tr>
                <tr>
                    <td>性别:</td>
                    <td>${user.sex}</td>
                </tr>
                <tr>
                    <td>个性签名:</td>
                    <td>${user.personality}</td>
                </tr>
            </table>
            <a href="#" class="btn btn-primary alter-btn">修改个人信息</a>
        </div>
        <form class="form-horizontal" id="alter-from" action="${pageContext.request.contextPath}/userServlet">
            <input type="text" id="username" name="username" value="${user.username}">
            <div class="form-group">
                <label for="tel" class="col-sm-2 control-label">电话</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="tel" placeholder="电话" name="tel" value="${user.tel}">
                </div>
            </div>
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">邮箱</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="inputEmail3" placeholder="邮箱" name="e_mail" value="${user.e_mail}">
                </div>
            </div>
            <div class="form-group">
                <label for="personality" class="col-sm-2 control-label">个性签名</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="personality" placeholder="最多40字" name="personality" value="${user.personality}">
                </div>
            </div>
            <c:set var="sex" scope="session" value="${user.sex}"/>
            <div class="form-group">
                <label class="col-sm-2 control-label">性别</label>
                <label class="radio-inline">
                    <input type="radio" name="sex" id="sexRadio1" value="男"
                    <c:if test="${sex eq '男'}"> checked="checked"</c:if>> 男
                </label>
                <label class="radio-inline">
                    <input type="radio" name="sex" id="sexRadio2" value="女"
                    <c:if test="${sex eq '女'}"> checked="checked"</c:if>>女
                </label>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default alter">修改</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>

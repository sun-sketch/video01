<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>登录</title>
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

    <style type="text/css">
        body{
            background: url("${pageContext.request.contextPath}/static/img/login.jpg") no-repeat;
        }
        .login{
            margin:100px auto;
            width: 550px;
            height: 400px;
            background: rgba(255,255,255,0.5);
            padding: 50px;
        }
        .user-or-admin{
            margin:10px ;
        }
        .guest{
            width: 100px;
            height: 20px;
            float: right;
        }
        .verifycode{
            display:inline;
        }
    </style>
    <script>
        window.onload = function(){
            $("#userlogin").show();
            $("#adminlogin").hide();
        }
        function admin() {
            $("#adminlogin").show();
            $("#userlogin").hide();
        }
        function user() {
            $("#userlogin").show();
            $("#adminlogin").hide();
        }

        function submit() {
            var username=$("[name='username']").val();
            var password=$("[name='password']").val();
            var verifycode=$("[name='verifycode']").val();
            if(verifycode==''){
                alert("请输入验证码。");
                return;
            }
            if(username!=''&&password!=''){
                $.ajax({
                    url:"${pageContext.request.contextPath}/loginServlet",
                    async:false,
                    type:"get",
                    data:{"username":username,
                        "password":password,
                        "verifycode":verifycode},
                    success:function(msg){
                        if(msg==null||msg==''){
                            window.location.href="${pageContext.request.contextPath}/user/home";
                        }else {
                            alert(msg);
                            refreshCode();//更换验证码
                        }
                    } });
            }else{
                alert("请填写用户名或密码");
            }


        }

        function adminsubmit() {
            var username=$("[name='admin-username']").val();
            var password=$("[name='admin-password']").val();
            var verifycode=$("[name='admin-verifycode']").val();
            if(verifycode==''){
                alert("请输入验证码。");
                return;
            }
            if(username!=''&&password!=''){
                $.ajax({
                    url:"${pageContext.request.contextPath}/loginServlet",
                    type:"get",
                    data:{"username":username,
                        "password":password,
                        "verifycode":verifycode},
                    success:function(msg){
                        if(msg==null||msg===''){
                            window.location="${pageContext.request.contextPath}/admin/cms";
                        }else {
                            alert(msg);
                            refreshCode2();
                        }
                    } });
            }else{
                alert("请填写用户名或密码");
            }

        }
        //切换用户验证码
        function refreshCode(){
            //1.获取验证码图片对象
            var vcode = document.getElementById("vcode");

            //2.设置其src属性，加时间戳
            vcode.src = "${pageContext.request.contextPath}/checkCodeServlet?time="+new Date().getTime();
        }
        //切换管理员验证码
        function refreshCode2(){
            //1.获取验证码图片对象
            var vcode = document.getElementById("vcode2");

            //2.设置其src属性，加时间戳
            vcode.src = "${pageContext.request.contextPath}/checkCodeServlet?time="+new Date().getTime();
        }
    </script>
</head>
<body>
<div class="form-horizontal login" id="userlogin">
    <ul class="nav nav-tabs user-or-admin">
        <li role="presentation" class="active"><a href="#">用户登录</a></li>
        <li role="presentation" onclick="admin()"><a href="#">管理员登录</a></li>
    </ul>
    <div class="form-group">
        <label class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="username" placeholder="用户名">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">密码</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" name="password" placeholder="密码">
        </div>
    </div>
    <div class="form-group">
        <label for="vcode" class="col-sm-2 control-label">验证码</label>
        <div class="col-sm-10">
            <input type="text" name="verifycode" class="form-control verifycode" id="verifycode" placeholder="请输入验证码" style="width: 120px;"/>
            <a href="javascript:refreshCode();">
                <img src="${pageContext.request.contextPath}/checkCodeServlet" title="看不清点击刷新" id="vcode"/>
            </a>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button class="btn btn-default" onclick="submit()">登录</button>
        </div>
    </div>
    <div class="guest">
        <a href="${pageContext.request.contextPath}/user/register">注册</a><br>
        <a href="${pageContext.request.contextPath}/user/home">游客入口--></a>
    </div>
</div>
<div class="form-horizontal login" id="adminlogin">
    <ul class="nav nav-tabs user-or-admin">
        <li role="presentation" onclick="user()"><a href="#">用户登录</a></li>
        <li role="presentation" class="active"><a href="#">管理员登录</a></li>
    </ul>
    <div class="form-group">
        <label class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-10">
            <input type="text" class="form-control"name="admin-username" placeholder="用户名">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">密码</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" name="admin-password" placeholder="密码">
        </div>
    </div>
    <div class="form-group">
        <label for="vcode2" class="col-sm-2 control-label">验证码</label>
        <div class="col-sm-10">
            <input type="text" name="admin-verifycode" class="form-control verifycode" id="verifycode2" placeholder="请输入验证码" style="width: 120px;"/>
            <a href="javascript:refreshCode2();">
                <img src="${pageContext.request.contextPath}/checkCodeServlet" title="看不清点击刷新" id="vcode2"/>
            </a>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button class="btn btn-default" onclick="adminsubmit()">登录</button>
        </div>
    </div>
    <div class="guest">
        <a href="${pageContext.request.contextPath}/user/home">游客入口--></a>
    </div>
</div>
</body>
</html>
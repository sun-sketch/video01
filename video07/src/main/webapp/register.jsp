<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页</title>
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
            background: url("${pageContext.request.contextPath}/static/img/login.jpg") no-repeat fixed;
        }
        #register {
            margin:100px auto;
            width: 600px;
            background: rgba(255,255,255,0.5);
            padding: 80px 40px 40px 40px;/*上右下左*/
            position: relative;
        }
        h1{
            position: absolute;
            top: 0;
            left: 50%;
        }
        #username_msg{
            color:#ff0000;
        }
    </style>
    <script>
        window.onload = function(){
            $("#username").blur(function(){
                var username=$("#username").val();
                if (username==''){
                    return;
                }
                $.ajax({
                    url:"${pageContext.request.contextPath}/registerUsernameServlet?username="+username,
                    success:function(msg){
                        $("#username_msg").html(msg);
                    }
                });
            });
        }
        function register_submit() {
            var username=$("#username").val();
            var password=$("#password").val();
            var tel=$("#tel").val();
            var email=$("#inputEmail3").val();
            var sex=$("input[name='sex']:checked").val();
            var password2=$("#password2").val();
            if (username==''||password==''||tel==''||email==''||sex==''||password2==''){
                alert("请将信息填写完整");
            }else{
                $("#register").submit();
            }


        }
    </script>
</head>
<body>
<form class="form-horizontal" id="register" action="${pageContext.request.contextPath}/user/register" method="post">
    <h1>注册</h1>
    <div class="form-group">
        <label for="username" class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="username" placeholder="用户名" name="username">
            <span id="username_msg"></span>
        </div>
    </div>
    <div class="form-group">
        <label for="inputPassword" class="col-sm-2 control-label">密码</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="inputPassword" placeholder="密码" name="password">
        </div>
    </div>
    <div class="form-group">
        <label for="inputPassword2" class="col-sm-2 control-label">确认密码</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="inputPassword2" placeholder="确认密码" name="password2">
        </div>
    </div>
    <div class="form-group">
        <label for="tel" class="col-sm-2 control-label">电话</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="tel" placeholder="电话" name="tel">
        </div>
    </div>
    <div class="form-group">
        <label for="inputEmail3" class="col-sm-2 control-label">邮箱</label>
        <div class="col-sm-10">
            <input type="email" class="form-control" id="inputEmail3" placeholder="邮箱" name="e_mail">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">性别</label>
        <label class="radio-inline">
            <input type="radio" name="sex" id="sexRadio1" value="男"> 男
        </label>
        <label class="radio-inline">
            <input type="radio" name="sex" id="sexRadio2" value="女">女
        </label>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" class="btn btn-default" onclick="register_submit()">注册</button>
        </div>
    </div>
    <!-- 出错显示的信息框 -->
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" >
            <span>&times;</span>
        </button>

        <strong>${requestScope.register_msg}</strong>
        <p>登录成功后会直接跳转到登录页面</p>
    </div>
</form>
</body>
</html>
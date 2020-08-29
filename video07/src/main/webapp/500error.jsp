<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>500error</title>
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
        .head{
            padding-left: 50px;
        }
    </style>
</head>
<body>
<div class="jumbotron head">
    <h1>Server exception</h1>
    <p>The server is busy or crashed. Please try again later. Please re-enter the website or click the button below to return to the home page.</p>
    <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/user/home" role="button">home</a></p>
</div>
</body>
</html>

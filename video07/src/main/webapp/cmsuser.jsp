<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
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

    </style>
    <script>
        function deleteUser(r,id){
            if(confirm("确定删除该用户")){
                var i= r.parentNode.parentNode.rowIndex;
                $.ajax({  url:"${pageContext.request.contextPath}/deleteUserServlet?id="+id,
                    success:function(){
                        document.getElementById("mytable").deleteRow(i);
                    } });
            }

        }
    </script>
</head>
<body>
<div class="list">

    <table class="table" id="mytable">
        <tr>
            <th>排名</th>
            <th>用户名</th>
            <th>被举报次数</th>
            <th>删除用户</th>
        </tr>
        <c:forEach items="${users}" var="user" varStatus="s">
            <tr>
                <td>${s.count}</td>
                <td>${user.username}</td>
                <td>${user.reportcount}</td>
                <td><button class="btn btn-default" type="submit" onclick="deleteUser(this,${user.id})">删除</button></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

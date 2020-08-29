<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
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
        #list,.content {
            width: 960px;
            height: 525px;
        }
        #list{
            position: relative;
            overflow: hidden;
        }
        .inner-container {
            position: absolute;
            left: 0;
            overflow-x: hidden;
            overflow-y: scroll;
        }
        /* for Chrome */
        .inner-container::-webkit-scrollbar {
            display: none;
        }
        #mytable tr{
            border-bottom: 1px solid #ddd;;
        }
        #mytable td{
            border: 0;
        }
        .add{
            text-align: left;
        }
    </style>
    <script>
        function alterzone(id) {
            if(confirm("确定修改该分区")){
                var zonename=prompt("请输入新的分区名：")
                $.ajax({
                    url:"${pageContext.request.contextPath}/alterZoneServlet?zonename="+zonename+"&id="+id,
                    type:"get",
                    dataType:"html",
                    success:function(msg){
                        $("#list").html(msg);
                    } });
            }
        }

        function deletezone(r,id){
            if(confirm("确定删除该分区")){
                var i= r.parentNode.parentNode.rowIndex;
                $.ajax({  url:"${pageContext.request.contextPath}/deleteZoneServlet?id="+id,
                    success:function(){
                        document.getElementById("mytable").deleteRow(i);
                    } });
            }

        }
        function addzone() {
            if(confirm("确定添加分区")){
                var zonename=prompt("请输入新的分区名：");
                if(zonename){
                //点击的是“确定”
                    $.ajax({
                        url:"${pageContext.request.contextPath}/addZoneServlet?zonename="+zonename,
                        type:"get",
                        dataType:"html",
                        success:function(msg){
                            $("#list").html(msg);
                            alert("添加成功！")
                        } });
                }else if(reason ===""){
                //用户没有输入内容点击的“确定”
                }else{
                //点击的是“取消”
                }
            }
        }
    </script>
</head>
<body>
<div id="list">
    <div class="inner-container">
        <div class="content">
            <div class="add">
                <button class="btn btn-primary" onclick="addzone()">添加分区</button>
            </div>
            <table class="table" id="mytable">
                <tr>
                    <th>分区</th>
                    <th>修改</th>
                    <th>删除</th>
                </tr>
                <c:forEach items="${zones}" var="zone" varStatus="s">
                    <tr>
                        <td>${zone.zonename}</td>
                        <td><button class="btn btn-default" onclick="alterzone(${zone.id})">修改</button></td>
                        <td><button class="btn btn-default" onclick="deletezone(this,${zone.id})">删除</button></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>

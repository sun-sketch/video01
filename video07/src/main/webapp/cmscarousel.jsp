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
        #mytable tr{
            border-bottom: 1px solid #ddd;;
        }
        #mytable td{
            border: 0;
        }
        .videoname{
            display:inline-block;
            width: 600px;
            white-space: nowrap;  /*禁止td换行*/
            overflow: hidden;  /*隐藏X,Y滚动条*/
            text-overflow: ellipsis;/*将显示不完的以...显示*/
        }
        .add{
            text-align: left;
        }
        .videoimage{
            width: 180px;
            height: 100px;
        }
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
    </style>
    <script>
        function addcarousel() {
            $.ajax({
                url:"${pageContext.request.contextPath}/admin/cmsaddcarousel?currentPage=1",
                type:"get",
                dataType:"html",
                success:function(msg){
                    $("#list").html(msg);
                } });
        }
        function deleteVideo(r,id){
            if(confirm("确定删除该轮播")){
                var i= r.parentNode.parentNode.rowIndex;
                $.ajax({  url:"${pageContext.request.contextPath}/deleteCarouselServlet?video_id="+id,
                    success:function(){
                        document.getElementById("mytable").deleteRow(i);
                    } });
            }

        }
    </script>
</head>
<body>
<div id="list">
    <div class="inner-container">
        <div class="content">
            <div class="add">
                <button type="button" class="btn btn-primary" onclick="addcarousel()">添加轮播</button>
            </div>
            <table class="table" id="mytable">
                <tr>
                    <th>序号</th>
                    <th>图片</th>
                    <th>视频名</th>
                    <th>删除轮播</th>
                </tr>
                <c:forEach items="${videos}" var="video" varStatus="s">
                    <tr>
                        <td>${s.count}</td>
                        <td ><img class="videoimage" src="${pageContext.request.contextPath}/static/img/${video.imagename}"></td>
                        <td class="videoname">${video.videoname}</td>
                        <td><button class="btn btn-default" type="submit" onclick="deleteVideo(this,${video.id})">删除</button></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</div>


</table>
</body>
</html>

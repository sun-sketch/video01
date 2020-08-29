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
        #list{
            width: 960px;
            height: 525px;
            position: relative;
        }
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
    </style>
    <script>
        var size=${size};
        function deleteVideo(r,id){
            if(confirm("确定删除该推广视频")){
                var i= r.parentNode.parentNode.rowIndex;
                $.ajax({  url:"${pageContext.request.contextPath}/deletePopularizeServlet?id="+id,
                    success:function(){
                        document.getElementById("mytable").deleteRow(i);
                        size=size-1;
                    } });
            }

        }

        function addpopularize(){
            if(size>=8){
                alert("推广栏已满，请删除视频后再进行添加");
            }else {
                $.ajax({
                    url:"${pageContext.request.contextPath}/admin/cmsaddpopularize?currentPage=1&popularizecount="+size,
                    type:"get",
                    dataType:"html",
                    success:function(msg){
                        $("#list").html(msg);
                    } });
            }
        }
    </script>
</head>
<body>
<div id="list">
    <div class="add">
        <button type="button" class="btn btn-primary add-btn" onclick="addpopularize()">添加推广视频</button>
    </div>
    <table class="table" id="mytable">
        <tr>
            <th>排名</th>
            <th>视频</th>
            <th>被点赞次数</th>
            <th>被举报次数</th>
            <th>删除视频</th>
        </tr>
        <c:forEach items="${videos}" var="video" varStatus="s">
            <tr>
                <td>${s.count}</td>
                <td class="videoname">${video.videoname}</td>
                <td>${video.likecount}</td>
                <td>${video.reportcount}</td>
                <td><button class="btn btn-default" type="submit" onclick="deleteVideo(this,${video.id})">删除</button></td>
            </tr>
        </c:forEach>
    </table>
</div>


</table>
</body>
</html>

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
        .delectSelected{
            width: 1100px;
            margin: 0 auto;
            text-align: center;
        }
        .videoimage{
            width: 180px;
            height: 100px;
        }
        .manage{
            width: 1100px;
            margin: 10px auto;
            padding-left: 20px;
        }
    </style>
    <script>
        window.onload = function(){
            $('#all').on('click',function(){
                if(this.checked) {
                    $("input[name='videoid']").attr('checked',true);
                }else {
                    $("input[name='videoid']").attr('checked',false);
                }
            });
        }
        function deletecollect(r,id){
            if(confirm("确定删除该视频收藏")){
                var i= r.parentNode.parentNode.rowIndex;
                $.ajax({  url:"${pageContext.request.contextPath}/deleteCollectServlet?videoid="+id,
                    success:function(){
                        document.getElementById("mytable").deleteRow(i);
                    } });
            }

        }

        //1.获取第一个cb
        document.getElementById("all").onclick = function(){
            //2.获取下边列表中所有的cb
            var videoids = document.getElementsByName("videoid");
            //3.遍历
            for (var i = 0; i < videoids.length; i++) {
                //4.设置这些cbs[i]的checked状态 = firstCb.checked
                videoids[i].checked = this.checked;

            }

        }

        //给删除选中按钮添加单击事件
        function deleteSelected() {
            if (confirm("您确定要删除选中条目吗？")) {

                var flag = false;
                //判断是否有选中条目
                var videoids = document.getElementsByName("videoid");
                for (var i = 0; i < videoids.length; i++) {
                    if (videoids[i].checked) {
                        //有一个条目选中了
                        flag = true;
                        break;
                    }
                }

                if (flag) {//有条目被选中
                    //表单提交
                    $("#form").submit();
                }

            }
        }
    </script>
</head>
<body>
<div class="manage">
    <a href="${pageContext.request.contextPath}/user/collect" class="btn btn-primary" onclick="back()">返回</a>
</div>
<form id="form" action="${pageContext.request.contextPath}/deleteSelectedCollectServlet" method="post">
    <table class="table" id="mytable">
        <tr>
            <th><input type="checkbox" id="all">全选</th>
            <th>图片</th>
            <th>视频名</th>
            <th>删除收藏</th>
        </tr>
        <c:forEach items="${videos}" var="video" varStatus="s">
            <tr>
                <td><input type="checkbox" name="videoid" value="${video.id}"></td>
                <td ><img class="videoimage" src="${pageContext.request.contextPath}/static/img/${video.imagename}"></td>
                <td class="videoname">${video.videoname}</td>
                <td><button class="btn btn-default" type="button" onclick="deletecollect(this,${video.id})">删除</button></td>
            </tr>
        </c:forEach>
    </table>
</form>
<div class="delectSelected">
    <button type="button" class="btn btn-primary add-btn" onclick="deleteSelected()">删除选中</button>
</div>
</body>
</html>

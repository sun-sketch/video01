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
            width: 700px;
            white-space: nowrap;  /*禁止td换行*/
            overflow: hidden;  /*隐藏X,Y滚动条*/
            text-overflow: ellipsis;/*将显示不完的以...显示*/
        }
        #page{
            position: absolute;
            bottom: 0;
            width: 960px;
            height: 40px;
            margin: 0;
            text-align: center;
        }
        #page_ul{
            margin: 0;
        }
        .delete{
            text-align: left;
        }
    </style>
    <script>
        var popularizecount=${popularizecount};
        function paclick(i) {
            $.ajax({
                url:"${pageContext.request.contextPath}/admin/cmsaddpopularize?currentPage="+i+"&popularizecount="+popularizecount,
                type:"get",
                dataType:"html",
                success:function(msg){
                    $("#list").html(msg);
                } });
        }
        function popularizeVideo(id){
            if(confirm("确定推广该视频")){
                if(popularizecount<8){
                    $.ajax({  url:"${pageContext.request.contextPath}/addPopularizeServlet?id="+id,
                        success:function(msg){
                            if(msg==null||msg===''){
                                popularizecount=popularizecount+1;
                                alert("推广成功。");
                                alert(popularizecount);
                            }else {
                                alert(msg);
                            }

                        } });
                }else {
                    alert("推广栏已满，请删除视频后再进行添加");
                }
            }

        }

        function deletepopularize() {
            $.ajax({
                url:"${pageContext.request.contextPath}/admin/cmspopularize",
                type:"get",
                dataType:"html",
                success:function(msg){
                    $("#list").html(msg);
                } });
        }
    </script>
</head>
<body>
<div id="list">
    <div class="delete">
        <button type="button" class="btn btn-primary" onclick="deletepopularize()">删除推广视频</button>
    </div>
    <table class="table" id="mytable">
        <tr>
            <th>排名</th>
            <th>视频</th>
            <th>被点赞次数</th>
            <th>推广视频</th>
        </tr>
        <c:forEach items="${p.list}" var="video" varStatus="s">
            <tr>
                <td>${s.count+p.begin}</td>
                <td class="videoname">${video.videoname}</td>
                <td>${video.likecount}</td>
                <td><button class="btn btn-default" type="submit" onclick="popularizeVideo(${video.id})">推广</button></td>
            </tr>
        </c:forEach>
    </table>
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
                        <a onclick="paclick(${currentPage - 1})" href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${totalpage}" var="i" >
                    <c:if test="${currentPage == i}">
                        <li class="active"><a class="pa" href="#" onclick="paclick(${i})">${i}</a></li>
                    </c:if>
                    <c:if test="${currentPage != i}">
                        <li><a href="#" onclick="paclick(${i})">${i}</a></li>
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
                        <a onclick="paclick(${currentPage + 1})" href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <span style="font-size: 25px;margin-left: 5px;">共${p.totalCount}条记录，共${totalpage}页</span>
            </ul>
        </nav>
    </c:if>
</div>


</table>
</body>
</html>

<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="com.service.ZoneService" %>
<%@ page import="com.domain.Zone" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>投稿</title>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ZoneService zoneService =context.getBean("zoneServiceImpl", ZoneService.class);
        List<Zone> zones=zoneService.queryAllZone();
        request.setAttribute("zones",zones);
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
    <link href="${APP_PATH }/static/css/bootstrap-fileinput.css" rel="stylesheet">
    <style type="text/css">
        .introduction{
            margin-top: 20px;
            width: 700px;
            height: 100px;
        }
        .upload-status{
            height: 40px;
            width: 100px;
            float: right;
            margin-top: 50px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="upload-status">
        <a href="${pageContext.request.contextPath}/updown/uploadstatus" class="btn btn-default">投稿状态</a>
    </div>
    <h4>上传视频</h4>
    <div class="form-group" id="uploadForm">
        <div class="fileinput fileinput-new" data-provides="fileinput"  id="exampleInputUpload">
            <div class="fileinput-new thumbnail" style="width: 200px;height: auto;max-height:150px;">
                <img id='picImg' style="width: 100%;height: auto;max-height: 140px;" src="${pageContext.request.contextPath}/static/img/noimage.png" alt="" />
            </div>
            <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 400px; max-height: 250px;"></div>
            <div>
                        <span class="btn btn-primary btn-file">
                            <span class="fileinput-new">视频封面</span>
                            <span class="fileinput-exists">换一张</span>
                            <input id="uploadfile" name="uploadfile" type="file" accept="image/*">
                        </span>
                <a href="javascript:;" class="btn btn-warning fileinput-exists" data-dismiss="fileinput" id="fileexist">移除</a>
            </div>
        </div><br/>
        <div class="fileinput fileinput-new" data-provides="fileinput"  id="exampleInputUploadvideo">

            <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 600px; max-height: 150px;"></div>
            <div>
                        <span class="btn btn-primary btn-file">
                            <span class="fileinput-new">选择视频文件</span>
                            <span class="fileinput-exists">换一个</span>
                            <input type="file" id="uploadvideo" name="uploadvideo" class="btn btn-primary" accept="video/*"/>
                        </span>
                <a href="javascript:;" class="btn btn-warning fileinput-exists" data-dismiss="fileinput" id="videoexist">移除</a>
            </div>
        </div>
        <br/><span>视频分区:</span>
        <select id="zoneSelect">
            <c:forEach items="${requestScope.zones}" var="zone" varStatus="s">
                <option>${zone.zonename}</option>
            </c:forEach>
        </select>
        <br/><textarea id="introduction" name="introduction" maxlength="300" class="introduction" placeholder="视频简介，最多输入300字"></textarea>
    </div>
    <button type="button" id="uploadSubmit" class="btn btn-info" onclick="upload()">提交</button>

</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap-fileinput.js"></script>
<script type="text/javascript">
    function upload(){
        //检测上传文件的类型
        var imgName = document.all.uploadfile.value;
        var ext,idx;
        if (imgName == ''){
            alert("请选择需要上传的文件!");
            return false;
        } else {
            idx = imgName.lastIndexOf(".");
            if (idx != -1){
                ext = imgName.substr(idx+1).toUpperCase();
                ext = ext.toLowerCase( );
                // alert("ext="+ext);
                if (ext != 'jpg' && ext != 'png' && ext != 'jpeg' && ext != 'gif'){
                    alert("只能上传.jpg  .png  .jpeg  .gif类型的文件!");
                    return false;
                }
            } else {
                alert("只能上传.jpg  .png  .jpeg  .gif类型的文件!");
                return false;
            }
        }
        var video = document.all.uploadvideo.value;
        if (video == ''){
            alert("请选择需要上传视频的文件!");
            return false;
        } else {
            idx = video.lastIndexOf(".");
            if (idx != -1){
                ext = video.substr(idx+1).toUpperCase();
                ext = ext.toLowerCase( );
                // alert("ext="+ext);
                if (ext != 'mp4'&& ext != 'flv' && ext != 'mpg' && ext != 'wmv'&& ext != '3gp' && ext != 'mov' && ext != 'asf'&& ext != 'asx'){
                    alert("只能上传mp4、flv、mpg、wmv、3gp、mov、asf、asx类型的文件!");
                    return false;
                }
            } else {
                alert("只能上传mp4、flv、mpg、wmv、3gp、mov、asf、asx类型的文件!");
                return false;
            }
        }
        var zonename=$('#zoneSelect option:selected').text();//选中的文本
        var formData = new FormData();
        var uploadfile = document.getElementById("uploadfile").files[0];
        var uploadvideo = document.getElementById("uploadvideo").files[0];
        // 检测文件大小 1024*1024=1048576(1M)
        if (uploadfile.size >= 1048576) {
            alert("上传图片大小不得超过1M")
            return false;
        }
        if (uploadvideo.size >= 1073741824) {
            alert("上传视频大小不得超过100M")
            return false;
        }
        var introduction=$("#introduction").val();
        formData.append("uploadfile", uploadfile);
        formData.append("uploadvideo",uploadvideo);
        formData.append("zonename",zonename);
        formData.append("introduction",introduction)
        $.ajax({
            type: 'POST',
            url: '${pageContext.request.contextPath}/updown/upload',
            data: formData,
            contentType: false,
            processData: false,
            async: true,
            success: function (msg) {
                alert(msg);
                $("#fileexist").click();
                $("#videoexist").click();
                $("#introduction").val("");
            },
            error: function (msg) {
                alert("上传异常，可查看投稿状态确认投稿是否成功。");
            }
        });
    }

</script>
</body>
</html>
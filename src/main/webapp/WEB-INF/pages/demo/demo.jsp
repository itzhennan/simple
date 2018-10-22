<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>AA</h1>
    <form id="videoForm" enctype="multipart/form-data" method="post">
        <input type="hidden" name="policy" id="vpolicy">
        <input type="hidden" name="signature" id="vsignature">
        <div class="videoFile1" data-select="0">
        <span>
            <input type="file"  accept="video/mp4,audio/*" class="videoFileId1" id="videoFileId" name="file" /><span style="color: red;">请选择MP3/WAV/MP4格式</span>
        </span>
        </div>
        <input type="button" onclick="sub_()" value="提交" >
    </form>



    <jsp:include page="/base/footer.jsp"/>
    <script>
        function sub(){
            var videoFile = $("#videoFileId")[0].files[0];
            var videoFileSize = videoFile.size;
            var videoFileName = videoFile.name;

            var reqJson={"videoFileSize":videoFileSize,"videoFileName":videoFileName};
            var fileParam = {};
            $IEC.ajaxCall({
                uri : "${ctx}/demo/getsign",
                type:"post",
                data:reqJson,
                timeout : 3600000,
                success : function(data) {
                    var signature = data.signature;
                    var url = data.url;
                    var formId = "";
                    // 续点上传文件
                    // 1. 初始化断点续传
                    var reqJosn = {"Content-Length":videoFileSize};
                    var currnDate = (new Date()).toGMTString();
                    var formData = new FormData();
                    formData.append("file", $('#videoFileId')[0].files[0]);
                    $.ajax({
                        type: "PUT",
                        url:url,
                        data:formData,
                        error: function(str) {
                            alert("上传失败");
                        },
                        success: function(data,textStatus) {
                            var data = eval('(' + str + ')');
                            if(data.code == 204){
                                // 上传成功
                                alert(data["x-upyun-multi-uuid"]);
                                alert(data["x-upyun-next-part-id"]);
                            }else{
                                alert("上传失败，请联系管理员！");
                            }
                        },
                        headers: {
                            "Authorization":signature,
                            "X-Date":currnDate,
                            "Content-Length":videoFileSize
                        }
                    });
                },
                error : function(){
                    alert("有误");
                }
            });
        }


        function sub_(){
            //http://static.fornow.com.cn/video/2018/10/16/wpf90uho1jmkrtb27cypvv6upifysqiw/bra.png
            $.ajax({
                type: "GET",
                url:"http://v0.api.upyun.com/fornow-videos/video/2018/10/16/wpf90uho1jmkrtb27cypvv6upifysqiw/bra.png/to/file",
                error: function(str) {
                    alert("下载失败");
                },
                success: function() {

                },
                headers: {
                    "Authorization":signature,
                    "X-Date":currnDate,
                }
            });
        }

    </script>
</body>
</html>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<title>影音列表</title>
	<jsp:include page="/common/header.jsp"/>
	<style>
		/**图片预览**/
		.pic-wrap{
			display: none;
			width: 100%;
			height: 100%;
			position: fixed;
			top: 0;
			background: rgba(0,0,0,.6);
			text-align: center;
			top:0;
			left:0;
		}
		.pic-wrap img{
			margin-top: 10%;
			margin-left: 10%;
		}

		.nov_div{
				width: 100%;
			    height: 36px;
			    line-height: 36px;
			    overflow: hidden;
			    background: #fff;}
		.nov_div span{
				width: 180px;
			    height: 36px;
			    display: inline-block;
			    line-height: 36px;
			    font-size: 18px;
			    text-align: center;
			    float: left;
			    cursor: pointer;
		}	
		.default{
			    color: #5c5c5c;
			    background: #ececec;
			    } 
		.active{background: #3379e2;color: #fff;}	 
		.table th, .table td{
			text-align:center;
			vertical-align:middle;
		}   
	</style>
</head>

<body>
	<div class="container wholeDiv">
		<div style="height: 10px"></div>
		<div class="nov_div">
			<a href="<c:url value='/super/video/list'/>">
				<span class="default active">影音列表</span>
			</a>
			<a href="<c:url value='/super/video/section/list'/>">
				<span  class="default">版块管理</span>
			</a>
		</div>
	
	
		<div class="search pageTitleDiv">
			<h3>影音列表</h3>
		</div>
		
		<form id="validateForm" class="form-horizontal" method="post">
			<div class="search">
	            <div class="row">
	                <div class="span12" style="width:95%;">
	                    <div class="control-group" style="line-height: 40px;">
	                    	<span style="margin-left: 10px;"></span> 
	                    	版块：
	                        <select name="sectionId" id="sectionId" class="input-medium">
	                            <option value="-99">-不限-</option>
	                            <c:forEach var="section" items="${sections}">
	                                <option value="${section.sectionId}"<c:if test="${section.sectionId eq cond.sectionId}">selected</c:if>>${section.sectionName}</option>
	                            </c:forEach>
	                        </select>
	                        <span style="margin-left: 10px;"></span> 
	                    	版块代表：
	                    	<select name="isRepresentative" class="input-medium">
	                            <option value="-99">-不限-</option>
	                            <option value="0" <c:if test="${0 eq cond.isRepresentative}">selected</c:if>>否</option>
	                            <option value="1" <c:if test="${1 eq cond.isRepresentative}">selected</c:if>>是</option>
	                        </select>
	                        <span style="margin-left: 10px;"></span>
	                                                            关键字：
                        	<input type="text" id="keyWords" name="keyWords" maxlength="30" autocomplete="off"  value="${cond.keyWords}" class="input-medium"
                               placeholder="标题名查询"/>
                        	<button type="submit" class="btn btn-primary">查询</button>
                        	<button type="button" class="btn btn-primary" onclick="addVideo()">新增影音</button>
	                    </div>
	                </div>
	            </div>
            </div>
          
		
			<div>
				<table class="table table-bordered table-striped">
					<tbody>
						<tr>
							<th>序号</th>
							<th>版块</th>
							<th style="width:50%;">标题</th>
							<th>类型</th>
							<th>版块代表</th>
							<th>操作</th>
						</tr>
						
						<c:choose>
							<c:when test="${not empty page.datas }">
								<c:forEach items="${page.datas }" var="data">
									<tr>
										<td>${data.videoId }</td>
										<td>${data.sectionName }</td>
										<td><p>${data.videoName }</p></td>
										<td>
											<c:choose>
												<c:when test="${data.videoType eq 0 }">视频</c:when>
												<c:otherwise>音频</c:otherwise>
											</c:choose>
										</td>
										<td>
											<c:choose>
												<c:when test="${data.isRepresentative eq 0 }">否</c:when>
												<c:when test="${data.isRepresentative eq 1 }">是</c:when>
												<c:otherwise>-</c:otherwise>
											</c:choose>
										</td>
										<td>
											<a href="javascript:;" onclick="editVideo(${data.videoId})">修改</a>
											<a href="javascript:;" onclick="deleteVideo(${data.videoId})">删除</a>
											<a href="javascript:;" onclick="sectionRepresentative(${data.videoId})">版块代表设置</a>
										</td>
									</tr>
								</c:forEach>
							 </c:when>
							<c:otherwise>
								<tr>
									<td colspan="6"><b style="color: blue;font-size: 16px; text-align: center; display: block;">暂无数据...</b></td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>			
				</table>
			</div>
			<jsp:include page="/common/page.jsp">
				<jsp:param name="actionURL" value=""/>
			</jsp:include>
		</form>
</div>

<div id="addVideoDiv">
	<iframe id="addVideoFrame" width="100%" height="100%" src =""></iframe>
</div>

<div id="editVideoDiv">
	<iframe id="editVideoFrame" width="100%" height="100%" src =""></iframe>
</div>

<div id="sectionRepresentativeDiv">
	<form id="sectionRepresentativeForm" action="<c:url value='/super/video/sectionRepresentative'/>" class="form-horizontal" method="post" target="_parent" >
		<input type="hidden" id="formVideoId" name="videoId">
		<div>
			<table class="table table-bordered table-striped">
				<tbody>
					<tr>
						<th style="width:100px">版块代表设置：</th>
						<td>
 							<input type="checkbox" name="isRepresentative" value="1" > 是
 							<input type="checkbox" name="isRepresentative" value="0" > 否
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>



</body>			
	<jsp:include page="/common/footer.jsp"/>
	<script type="text/javascript">
	$(document).ready(function(){
		$(function(){
			$("#sectionRepresentativeDiv").find(":checkbox").each(function(){
				$(this).click(function(){
					if($(this).is(':checked')){ 
						$(this).attr('checked',true).siblings().attr('checked',false);
					}else{
						$(this).attr('checked',false).siblings().attr('checked',false);
						}
					});
				});
			});
		});
		$("#sectionId").select2();
		var i=0; //定义变量i防止多次提交
		function addVideo(){
			$("#addVideoFrame").attr('src',"${ctx}/super/video/toAddVideoUI");
			$("#addVideoDiv").dialog("open");
		}
		
		function editVideo(videoId){
			$("#editVideoFrame").attr('src',"${ctx}/super/video/toEditVideoUI/"+videoId);
			$("#editVideoDiv").dialog("open");
		}
		function sectionRepresentative(videoId){
			$IEC.ajaxCall({
				uri : "${ctx}/super/video/checkRepresentative/"+videoId,
		        type:"get",
				success : function(data) {
					if(data.code == 0){
						$("#formVideoId").val(videoId);
						$("[name='isRepresentative']").removeAttr("checked");//取消全选
						$("input[type='checkbox'][name='isRepresentative'][value='"+data.returnId+"']").prop("checked",true);
						$("#sectionRepresentativeDiv").dialog("open");
					}else{
						alert(data.msg);
					}
		        },
				error : function(){
					alert("网络异常，请稍后再试!");
				}
		    });
		}
	
		$("#addVideoDiv").dialog({
		    autoOpen:false,
		    bgiframe:false,
		    closeOnEscape:false,//按esc退出默认的true
		    //窗口宽度
		  	width:550,
		  	height:500,
		  	//设置窗口的标题
		    modal:true, //遮罩效果默认是false不遮住
		    position:"center", //对话框弹出的位置，top,left,right,center,默认是center
			title:"新增影音",
			buttons: { 
				"确定": function () {
						var videoTitle =  $("#addVideoFrame").contents().find('#videoTitle').val();
						if(!videoTitle){
							alert("请输入标题!");
							return false;
						}
						var videoFileId = $("#addVideoFrame").contents().find('#videoFileId').val();
						if(!videoFileId){
							alert("请添加视频/音频！");
							return false;
						}
						var sectionId = $("#addVideoFrame").contents().find('#sectionId').val();
						if(sectionId == -99){
							alert("请选择版块!");
							return false;
						}
						var videoType = $("#addVideoFrame").contents().find('#videoType').val();
						if(videoType == -99){
							alert("请选择类型!");
							return false;
						}
						var imageWordFileId = $("#addVideoFrame").contents().find('#imageWordFileId').val();
						if(!imageWordFileId){
							alert("请添加图文！");
							return false;
						}
						var listImageFileId = $("#addVideoFrame").contents().find('#listImageFileId').val();
						if(!listImageFileId){
							alert("请添加列表图！");
							return false;
						}
						else{
// 							i++;
// 							if(i!=1){
// 								alert("请勿重复点击，等待跳转！");
// 								return false;
// 							}
						}
// 						$("#addVideoFrame").contents().find('#addVideoForm').submit();
						var childWindow = $("#addVideoFrame")[0].contentWindow;  
						childWindow.onRtn(); //调用添加页面 onRtn();方法  实现遮罩层。
						uploadFile(videoTitle,sectionId,videoType);
		          },
				  
		          "取消": function () {
					   $(this).dialog('close'); 
					 }
			   },
		});
		$("#editVideoDiv").dialog({
		    autoOpen:false,
		    bgiframe:false,
		    closeOnEscape:false,//按esc退出默认的true
		    //窗口宽度
			 width:550,
		  	height:500,
		  	//设置窗口的标题
		    modal:true, //遮罩效果默认是false不遮住
		    position:"center", //对话框弹出的位置，top,left,right,center,默认是center
			title:"修改影音",
			buttons: { 
				"确定": function () {
						var videoId = $.trim($("#editVideoFrame").contents().find('#videoId').val());
						var videoName = $.trim($("#editVideoFrame").contents().find('#videoTitle').val());	
						var videoType = $.trim($("#editVideoFrame").contents().find('#videoType').val());
						var sectionId = $.trim($("#editVideoFrame").contents().find('#sectionId').val());
						var videoFileId = $.trim($("#editVideoFrame").contents().find('#videoFileIds').val());
						var imageWordFileId = $.trim($("#editVideoFrame").contents().find('#imageWordFileIds').val());
						var listImageFileId = $.trim($("#editVideoFrame").contents().find('#listImageFileIds').val());
						if(!videoName){
							alert("请输入标题!");
							return false;
						}
						if(!videoFileId){
							alert("请选择视频/音频！");
							return false;
						}
						if(sectionId == -99){
							alert("请选择版块!");
							return false;
						}
						if(videoType == -99){
							alert("请选择类型!");
							return false;
						}
						if(!imageWordFileId){
							alert("请选择图文！");
							return false;
						}
						if(!listImageFileId){
							alert("请选择列表图！");
							return false;
						}
						else{
						i++;
// 						if(i!=1){
// 							alert("请勿重复点击，等待跳转！");
// 							return false;
// 							}
						}
						$("#editVideoFrame").contents().find('#editVideoForm').submit();
						var childWindow = $("#editVideoFrame")[0].contentWindow;  
						childWindow.onRtn(); //调用修改页面 onRtn();方法  实现遮罩层。
					/* 	var reqJson={"videoName":videoName,"videoId": videoId,"videoType":videoType,"sectionId":sectionId};
						$IEC.ajaxCall({
							 uri : "${ctx}/super/video/editVideo",
					        type:"post",
					        data:reqJson,
					        success : function(data) {
								window.location.reload();
					        },
							error : function(){
								alert("有误");
							}
					    }); */
          		},
			   "取消": function () {
				   $(this).dialog('close'); 
				}
			},
		});
		
		$("#sectionRepresentativeDiv").dialog({
		    autoOpen:false,
		    bgiframe:false,
		    closeOnEscape:false,//按esc退出默认的true
		    //窗口宽度
			width:600,
		  	height:230,
		  	//设置窗口的标题
		    modal:true, //遮罩效果默认是false不遮住
		    position:"center", //对话框弹出的位置，top,left,right,center,默认是center
			title:"版块代表设置",
			buttons: { 
				"确定": function () {
					if(confirm("确定设置该影音为版块代表吗?")){
					$('#sectionRepresentativeForm').submit();
						}
          		},
			   "取消": function () {
				   $(this).dialog('close'); 
				}
			},
		});
		
		// 删除
	function deleteVideo(videoId){
		if(confirm("确定删除该影音吗?")){
			$IEC.ajaxCall({
				success : function(data) {
					if(data.code==0){
						alert("删除成功");
						window.location.reload();
					}else{
						alert("删除失败");
					}
				},
				error : function(data) {
					alert("删除出错");
				},
				uri : "<c:url value='/super/video/deleteVideo/'/>" + videoId,
				type : "GET"
			});
		}
	}
	
	function uploadFile(videoTitle,sectionId,videoType){
		var videoFile = $("#addVideoFrame").contents().find('#videoFileId');
		
		var videoFileSize = $(videoFile)[0].files[0].size;
		var videoFileName = $(videoFile)[0].files[0].name;
		
		var reqJson={"videoName":videoTitle,"sectionId": sectionId,"videoType":videoType,"videoFileSize":videoFileSize,"videoFileName":videoFileName};
		var fileParam = {};
		$IEC.ajaxCall({
			uri : "${ctx}/super/video/addVideo",
	        type:"post",
	        data:reqJson,
	        timeout : 3600000,
	        success : function(data) {
	        	if(data.code == 0){
	        		var datas = data.datas;
	        		for(var i = 0;i< datas.length;i++){
	        			var policy = datas[i].policy;
	        			var signature = datas[i].signature;
	        			var url = datas[i].url;
	        			var formId = "";
	        			if(datas[i].type == 0){
	        				// 视频上传
// 	        				$("#addVideoFrame").contents().find('#vpolicy').val(policy);
// 	        				$("#addVideoFrame").contents().find('#vsignature').val(signature);
	        				formId = "videoForm";
	        			}else if(datas[i].type == 1){
	        				// 图文上传
	        				$("#addVideoFrame").contents().find('#ipolicy').val(policy);
	        				$("#addVideoFrame").contents().find('#isignature').val(signature);
	        				formId = "imageForm";
	        			}else if(datas[i].type == 2){
	        				// 列表图上传
	        				$("#addVideoFrame").contents().find('#ipolicy').val(policy);
	        				$("#addVideoFrame").contents().find('#isignature').val(signature);
	        				formId = "imageForm";
	        			}
	        			if(datas[i].type==0){
	        				// 续点上传文件
	        				// 1. 初始化断点续传
	        				var reqJosn = {"x-upyun-multi-stage":"initiate","x-upyun-multi-length":videoFileSize};
	        				var currnDate = (new Date()).toGMTString();
	        				$.ajax({
	        			        type: "PUT",
	        			        url:url,
	        			        data:reqJosn,
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
	        			            "Content-Length":Buffer.byteLength(JSON.stringify(reqJosn),'utf8')
	        			        }
	        			    });
	        				
	        			}else{
// 		        			var formData = new FormData($("#addVideoFrame").contents().find('#'+formId)[0]);
// 		        	        $.ajax({
// 		        	            type: 'POST',
// 		        	            url: url,
// 		        	            data: formData,
// 		        	            timeout : 7200000,
// 		        	            cache: false,
// 		        	            processData: false,
// 		        	            contentType: false,
// 		        	            async:false
// 		        	        }).success(function (str) {
// 		        	        	var data = eval('(' + str + ')'); 
// 		        	        	if(data.code == 200){
// 		        	        		// 上传成功
// 		        	        		var videoId = data["ext-param"];
// 	    							var fileUrl = data["url"];
// 		        	        		fileParam["videoId"] = videoId;
// 		        	        		if(datas[i].type == 0){
// 			        	        		fileParam["videoUrl"] = fileUrl;
// 		        	        		}else if(datas[i].type == 1){
// 		        	        			fileParam["imageUrl"] = fileUrl;
// 		        	        		}else if(datas[i].type == 2){
// 		        	        			fileParam["listUrl"] = fileUrl;
// 		        	        		}
// 		        	        	}else{
// 		        	        		alert("上传失败，请联系管理员！");
// 		        	        	}
// 		        	        }).error(function () {
// 		        	            alert("上传失败");
// 		        	        });
	        			}
	        		}
	        		// 上传成功
// 					$IEC.ajaxCall({
// 						uri : "${ctx}/super/video/uploadVideoCallBack",
// 				        type:"post",
// 				        data:fileParam,
// 				        timeout : 3600000,
// 				        success : function(data) {
// 				        	if(data.code == 0){
// 				        		$("#validateForm").submit();
// 				        	}
// 				        },
// 						error : function(){
// 							alert("有误");
// 						}
// 				    });
	        		
	        	}
	        },
			error : function(){
				alert("有误");
			}
	    });
	}
	
	</script>
</html>
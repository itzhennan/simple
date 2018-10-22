<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<title>添加影音</title>
</head>

<body>
	<div class="container wholeDiv">
		<div class="row">
<%-- 			<form id="addVideoForm" action="<c:url value='/super/video/addVideo'/>" enctype="multipart/form-data" class="form-horizontal" method="post" target="_parent" > --%>
				<table class="table table-bordered table-striped">
					<tr>
						<td>
							标题：
						</td>
						<td>
							<textarea rows="4" cols="450" name="videoName" id="videoTitle" style="width: 400px"></textarea>
						</td>
					</tr>
					<tr>
						<td>
							视频/音频：
						</td>
						<td class="videoFile">
							<form id="videoForm" enctype="multipart/form-data" method="post">
								<input type="hidden" name="policy" id="vpolicy">
								<input type="hidden" name="signature" id="vsignature">
								<div class="videoFile1" data-select="0">
									<span>
										<input type="file"  accept="video/mp4,audio/*" class="videoFileId1" id="videoFileId" name="file" onchange="fileVideoChange(this);"/><span style="color: red;">请选择MP3/WAV/MP4格式</span>
	<!-- 									<i class="icon-remove" class="videoFileRemove1" style="cursor: pointer;margin-left: 1%" title="删除" onclick="deleteFile(1,'videoFile')"></i> -->
									</span>
								</div>
							</form>
						</td>
					</tr>
					<tr>
						<td>
							版块：
						</td>
						<td>
							<select name="sectionId" id="sectionId" class="input-medium" style="width:150px;">
	                            <option value="-99">-请选择-</option>

	                        </select>
						</td>
					</tr>
					<tr>
						<td>
							类型：
						</td>
						<td>
							<select name="videoType" id="videoType" class="input-medium">
	                            <option value="-99">-请选择-</option>
	                            <option value="0">视频</option>
	                            <option value="1">音频</option>
	                        </select>
						</td>
					</tr>
					<tr>
						<td>
							图文：
						</td>
						<td class="imageWordFile">
							<form id="imageForm">
								<input type="hidden" name="policy" id="ipolicy">
								<input type="hidden" name="signature" id="isignature">
								<div class="imageWordFile1" data-select="0">
									<span>
										<input type="file"  accept="image/jpeg,image/png" class="imageWordFileId1" id="imageWordFileId" name="file" onchange="fileChange(this);"/><span style="color: red;">请选择JPG/PNG格式图片</span>
	<!-- 									<i class="icon-remove" class="imageWordFileRemove1" style="cursor: pointer;margin-left: 1%" title="删除" onclick="deleteFile(1,'imageWordFile')"></i> -->
									</span>
								</div>
							</form>
						</td>
					</tr>
					<tr>
						<td>
							列表图：
						</td>
						<td class="listImageFile">
							<form id="listForm">
								<input type="hidden" name="policy" id="lpolicy">
								<input type="hidden" name="signature" id="lsignature">
								<div class="listImageFile1" data-select="0">
									<span>
										<input type="file"  accept="image/jpeg,image/png" class="listImageFileId1" id="listImageFileId"  name="file" onchange="fileChange(this);"/><span style="color: red;">请选择JPG/PNG格式图片</span>
	<!-- 									<i class="icon-remove" class="listImageFileRemove1" style="cursor: pointer;margin-left: 1%" title="删除" onclick="deleteFile(1,'listImageFile')"></i> <input type="file"  accept="image/jpeg,image/png" class="listImageFileId1" id="listImageFileId"  name="listImageFile" onchange="showFiles(1,'listImageFile');"/>-->
									</span>
								</div>
							</form>
						</td> 
					</tr>
				</table>
<!-- 			</form> -->
		</div>
	</div>
</body>			
	<jsp:include page="/common/footer.jsp"/>
	<script type="text/javascript">
		$("#sectionId").select2();
 		function showFiles(i,node){
	/* 		var dataSelect = $("."+node+i).attr("data-select");		
			if(dataSelect == 0){
				$("."+node+i).attr("data-select","1");
				$("."+node+"Id"+i).after('<i class="icon-remove" class="'+node+'Remove'+i+'" style="cursor: pointer;margin-left: 1%" title="删除" onclick="deleteFile('+i+',\''+node+'\')"></i>');
				i = parseInt(i)+1;
				var str = '<div class="'+node+i+'" data-select="0" >'+
					'<span>'+
						'<input type="file" class="'+node+'Id'+i+'" name="'+node+'" onchange="showFiles('+i+',\''+node+'\');"/>'+
					'</span>'+
				'</div>';
	            $("."+node).append(str);
			} */
	    } 
		function deleteFile(i,node){
			$("."+node+i).remove();
		}
		function fileChange(target) {
		      var name=target.value;
		      var fileName = name.substring(name.lastIndexOf(".")+1).toLowerCase();
		      if(fileName !="jpg" && fileName !="jpeg" && fileName !="png"){
		         alert("请选择图片格式文件上传(jpg,png等)！");
		         target.value="";
		         return false;   //阻止submit提交
		      }
		}
		 function fileVideoChange(target) {
		      var name=target.value;
		      var fileName = name.substring(name.lastIndexOf(".")+1).toLowerCase();
		      if(fileName !="mp4" && fileName !="mp3" && fileName !="wav"){
		         alert("请选择音频/视频格式文件上传(mp3,mp4等)！");
		         target.value="";
		         return false;   //阻止submit提交
		      }
		}
		 
	</script>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<jsp:include page="/common/base/head.jsp"   />
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="/common/base/header.jsp"   />
			 <%--Main--%>
			<div id="main">
				<div class="col-12 col-md-10" id="content-body">
		            <div class="title-box">
		                <input type="text" id="txtTitle" maxlength="100" placeholder="输入文章标题" value="${bean.title}">
		            </div>
		            <div class="section">
		                <textarea id="editor" name="editor" rows="30" style="width: 99.4%; display: none; visibility: hidden;">${bean.cont}</textarea>
		            </div>
		
		            <div id="moreDiv">
		                <div class="pos-box">
		                    <div class="title fullscreen">发布博客<a class="btn-close"><i class="xheIcon icon-guanbi"></i></a></div>
		                        <div class="form-group row form-control-sm">
		                            <label class="labTitle col-form-label">文章标签：</label>
		                            <div class="txt-box">
		                                <div class="tag-box d-flex flex-row private-tag" id="articleTagBox">
		                                    <input type="hidden" name="hidTags" id="hidTags" value="${tagStr}">

											<c:forEach items="${bean.articleTags}" var="tag">
												<div class="tag">
													<span class="name" contenteditable="false">
														<font style="vertical-align: inherit;"><font style="vertical-align: inherit;">${tag.tagName}</font></font>
													</span>
													<i class="xheIcon icon-guanbi"><svg t="1535953077191" class="icon" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3356" xmlns:xlink="http://www.w3.org/1999/xlink" width="12" height="12"><defs><style type="text/css"></style></defs><path d="M896 0L512 384 128 0 0 128l384 384L0 896l128 128 384-384 384 384 128-128-384-384 384-384z" p-id="3357"></path></svg></i>
												</div>
											</c:forEach>

		                                    <button class="btn-add-tag" id="addTag">
		                                        <i class="xheIcon icon-tianjia mr8" aria-hidden="true">
		                                        <svg t="1535953779842" class="icon" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3712" xmlns:xlink="http://www.w3.org/1999/xlink" width="16" height="16"><defs><style type="text/css"></style></defs><path d="M736 64H288C164.8 64 64 164.8 64 288v448c0 123.2 100.8 224 224 224h448c123.2 0 224-100.8 224-224V288c0-123.2-100.8-224-224-224z m-28.8 504h-140.8v140.8c0 30.4-25.6 56-56 56s-56-25.6-56-56v-140.8h-140.8c-30.4 0-56-25.6-56-56s25.6-56 56-56h140.8v-140.8c0-30.4 25.6-56 56-56s56 25.6 56 56v140.8h140.8c30.4 0 56 25.6 56 56s-24 56-56 56z" p-id="3713" fill="#349edf"></path></svg>
		                                        </i>
		                                        添加标签
		                                    </button>
		                                </div>
		                                <p class="mt8"><span class="ipt-remark">最多添加5个标签</span></p>
		                            </div>
		                        </div>
		                        <div class="form-group row form-control-sm">
		                            <label class="labTitle col-form-label">个人分类：</label>
		                            <div class="txt-box">
		                                <div class="tag-box d-flex flex-row" id="categorieBox">
		                                    <input name="hidTags" id="hidCategories" value="${catStr}" type="hidden">
		                                    <button class="btn-add-tag" id="addCategorie">
		                                        <i class="xheIcon icon-tianjia mr8" aria-hidden="true">
		                                            <svg t="1535953779842" class="icon" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3712" xmlns:xlink="http://www.w3.org/1999/xlink" width="16" height="16"><defs><style type="text/css"></style></defs><path d="M736 64H288C164.8 64 64 164.8 64 288v448c0 123.2 100.8 224 224 224h448c123.2 0 224-100.8 224-224V288c0-123.2-100.8-224-224-224z m-28.8 504h-140.8v140.8c0 30.4-25.6 56-56 56s-56-25.6-56-56v-140.8h-140.8c-30.4 0-56-25.6-56-56s25.6-56 56-56h140.8v-140.8c0-30.4 25.6-56 56-56s56 25.6 56 56v140.8h140.8c30.4 0 56 25.6 56 56s-24 56-56 56z" p-id="3713" fill="#349edf"></path></svg>
		                                        </i>添加新分类
		                                    </button>
		                                </div>
		                                <div class="categorie-list" style="">
		                                    <div class="form-check">
		                                        <label class="form-check-label">
		                                            <input class="form-check-input" type="checkbox" value="JAVA" id="7720445">
		                                            <i class="lab-chk-icon" for="chk01">
		                                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" t="1514450759045" class="icon-checked" viewBox="0 0 1024 1024">
		                                                    <path d="M768 0H256C115.2 0 0 115.2 0 256v512c0 140.8 115.2 256 256 256h512c140.8 0 256-115.2 256-256V256c0-140.8-115.2-256-256-256z m17.6 377.6L460.8 728c-9.6 9.6-27.2 14.4-40 14.4-14.4 0-32-3.2-41.6-14.4l-142.4-153.6c-17.6-19.2-17.6-49.6 0-68.8 17.6-19.2 46.4-19.2 64 0l120 128 300.8-324.8c17.6-19.2 46.4-19.2 64 0s17.6 49.6 0 68.8z" p-id="2164" fill="#7ed321" data-spm-anchor-id="a313x.7781069.0.i4" class=""></path>
		                                                </svg>
		                                            </i>
		                                            <span class="spanIsAgree">JAVA</span>
		                                        </label>
		                                    </div>
		                                    <div class="form-check">
		                                        <label class="form-check-label">
		                                            <input class="form-check-input" type="checkbox" value="JVM" id="7720454">
		                                            <i class="lab-chk-icon" for="chk01">
		                                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" t="1514450759045" class="icon-checked" viewBox="0 0 1024 1024">
		                                                    <path d="M768 0H256C115.2 0 0 115.2 0 256v512c0 140.8 115.2 256 256 256h512c140.8 0 256-115.2 256-256V256c0-140.8-115.2-256-256-256z m17.6 377.6L460.8 728c-9.6 9.6-27.2 14.4-40 14.4-14.4 0-32-3.2-41.6-14.4l-142.4-153.6c-17.6-19.2-17.6-49.6 0-68.8 17.6-19.2 46.4-19.2 64 0l120 128 300.8-324.8c17.6-19.2 46.4-19.2 64 0s17.6 49.6 0 68.8z" p-id="2164" fill="#7ed321" data-spm-anchor-id="a313x.7781069.0.i4" class=""></path>
		                                                </svg>
		                                            </i>
		                                            <span class="spanIsAgree">JVM</span>
		                                        </label>
		                                    </div>
		                                </div>
		                            </div>
		                    </div>
		                    <div class="form-group row form-control-sm d-flex">
		                        <label class="labTitle col-form-label">文章类型：</label>
		                        <div class="txt-box">
		                            <select id="selType">
		                                <option value="0">请选择</option>
		                                <option value="1">原创</option>
		                                <option value="2">转载</option>
		                                <option value="3">翻译</option>
		                            </select>
		                            <span class="required">*</span>
		                        </div>
		                        <label class="labTitle col-form-label ml-24">博客分类：</label>
		                        <div class="txt-box">
									<select class="droBlogType" id="radChl" name="radChl">
										<option value="0">选择分类</option>
										<c:forEach items="${categorys}" var="category">
											<<option value="${category.smdId}">${category.smdName}</option>
										</c:forEach>
		                            </select>
		                            <span class="required">*</span>
		                        </div>
		                    </div>
		                    <div class="form-group row form-control-sm">
		                        <label class="labTitle col-form-label">私密文章：</label>
		                       <div class="switch-box">
		                            <input type="checkbox" name="public" class="form-control form-control-sm chk-switch" id="chkIsHasNotify">
		                            <label for="chkIsHasNotify" class="lab-switch"></label>
		                            <span class="ipt-remark chk-true online-txt">已设置成私密</span>
		                        </div>
		                    </div>
		                    <div class="opt-box row unfull">
		                        <label class="labTitle col-form-label"></label>
		                        <div class="txt-box">
		                            <input id="btnPublish" type="button" class="btn btn-outline-danger" value="发布博客" title="发布博客">
		                            <input id="btnDraft" type="button" class="btn btn-outline-danger ml-24 btn-baocun" value="保存为草稿" title="保存为草稿">
		                            <input id="btnCancel" type="button" class="btn btn-outline-secondary ml-24 btn-shanchu" title="返回列表页" value="返回">
		                        </div>
		                    </div>
		                </div>
		            </div>
		
		            <div class="alert-container finished-box" id="alertSuccess">
		                <div class="pos-box">
		                    <div class="text-center title"></div>
		                    <div class="text-center status-box">
		                        <i class="icon-success-font c-green"></i>文章发布成功
		                    </div>
		                    <p class="text-center zhaiyao">文章摘要由系统生成</p>
		                    <div class="opt-box text-center">
		                        <a class="btn  btn-outline-danger btn-new mr24" href="https://mp.csdn.net/postedit">写新文章</a>
		                            <a class="btn  btn-outline-danger tolist mr24" href="https://mp.csdn.net/postlist">管理博文</a>
		                            <a class="btn  btn-outline-danger toarticle">查看文章</a>
		                    </div>
		                </div>
		            </div>
		        </div>
			</div>

			<jsp:include page="/WEB-INF/pages/common/leftSidebar.jsp"   />
		</div>
	</body>
	<jsp:include page="/common/base/footer.jsp"   />
	<jsp:include page="/common/base/ckfooter.jsp" />

</html>

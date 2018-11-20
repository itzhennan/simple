<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>查看文章</title>
		<jsp:include page="/common/base/head.jsp"   />
		<link rel="stylesheet" href="${csshost}/assets/css/main.css"/>
		<%--代码主题css--%>
		<link rel="stylesheet" href="${csshost}/js/vendor/ckeditor/4.11.1/plugins/codesnippet/lib/highlight/styles/xcode.css">
		<style>
			#scrop{
				position: fixed;
				right: 74em;
			}
		</style>
	</head>
	<body>
		<div id="wrapper">

			<jsp:include page="/common/base/header.jsp"   />

			<!-- 右侧评论 -->
			<div id="right-sidebar-div">
				<div>
				<section id="right-sidebar">
				<!-- Posts List -->
				<section>
					<ul class="comment">
						<li>
							Java程序员-张凯： 您好，请问可以转载您的这篇文章吗？会注明原文作者，原文链接，谢谢！(1个月前)
						</li>
						<li>
							夏天的鼻涕怪： 队友不合作怎么办……(1个月前)
						</li>
						<li>
							qq_42840826： 代码的规范也很重要的(1个月前)
						</li>
						<li>
							Java程序员-张凯： 您好，请问可以转载您的这篇文章吗？会注明原文作者，原文链接，谢谢！(1个月前)
						</li>
						<li>
							夏天的鼻涕怪： 队友不合作怎么办……(1个月前)
						</li>
						<li>
							qq_42840826： 代码的规范也很重要的(1个月前)
						</li>
						<li>
							Java程序员-张凯： 您好，请问可以转载您的这篇文章吗？会注明原文作者，原文链接，谢谢！(1个月前)
						</li>
						<li>
							夏天的鼻涕怪： 队友不合作怎么办……(1个月前)
						</li>
						<li>
							qq_42840826： 代码的规范也很重要的(1个月前)
						</li>
						<li>
							Java程序员-张凯： 您好，请问可以转载您的这篇文章吗？会注明原文作者，原文链接，谢谢！(1个月前)
						</li>
						<li>
							夏天的鼻涕怪： 队友不合作怎么办……(1个月前)
						</li>
						<li>
							qq_42840826： 代码的规范也很重要的(1个月前)
						</li>
						<li>
							Java程序员-张凯： 您好，请问可以转载您的这篇文章吗？会注明原文作者，原文链接，谢谢！(1个月前)
						</li>
						<li>
							夏天的鼻涕怪： 队友不合作怎么办……(1个月前)
						</li>
						<li>
							qq_42840826： 代码的规范也很重要的(1个月前)
						</li>
						<li>
							Java程序员-张凯： 您好，请问可以转载您的这篇文章吗？会注明原文作者，原文链接，谢谢！(1个月前)
						</li>
						<li>
							夏天的鼻涕怪： 队友不合作怎么办……(1个月前)
						</li>
						<li>
							qq_42840826： 代码的规范也很重要的(1个月前)
						</li>
						<li id="comment">
							qq_42840826： 代码的规范也很重要的(1个月前)
						</li>


					</ul>
				</section>

			</section>
				</div>
			</div>
			<!-- Main -->
			<div id="main-sidebar-div">
				<div id="main">

				<article class="post">
					<!--文章头-->
					<header>
						<div class="title">
							<h2><a href="#">${bean.title}</a></h2>
							<p>文章描述</p>
						</div>
						<div class="meta">
							<a href="javascript:void(0);" class="icon fa-expand"></a>

							<time class="published" datetime="<u:dateFormat value="${bean.publicDateTime}" pattern="yyyy-MM-dd"></u:dateFormat>">
								<u:localDateFormat value="${bean.publicDateTime}"></u:localDateFormat>
							</time>
							<a href="#" class="author">
								<span class="name">${bean.user.userName}</span>
								<img src="${imgServer}${bean.user.uploadFiles.filePath}${bean.user.uploadFiles.fileExt}" alt="我的头像" />
							</a>
						</div>
					</header>

					<section>
						${bean.cont}
					</section>

				</article>

				<!-- Pagination -->
				<ul class="actions pagination">
					<li><a href="" class="disabled button big previous">Previous Page</a></li>
					<li><a href="#" class="button big next">Next Page</a></li>
				</ul>

			</div>
			</div>
			<div id="left-sidebar-div" style="display: none;">
				<jsp:include page="/WEB-INF/pages/common/leftSidebar.jsp"   />
			</div>
			<%--<div id="scrop"></div>--%>
		</div>
	</body>
	<jsp:include page="/common/base/footer.jsp"   />
	<%--文章详情--%>
	<script type="text/javascript" src="${jshost}/assets/js/dmain.js"></script>
	<%--代码高亮--%>
	<script type="text/javascript" src="${jshost}/js/vendor/ckeditor/4.11.1/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
	<%--数学公式--%>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.4/MathJax.js?config=TeX-AMS_HTML"></script>
</html>

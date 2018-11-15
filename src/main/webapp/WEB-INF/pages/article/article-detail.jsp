<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>查看文章</title>
		<%--<jsp:include page="/common/base/head.jsp"   />--%>
		<link rel="stylesheet" href="${csshost}/assets/css/main.css"/>
	</head>
	<body>
		<div id="wrapper">

			<jsp:include page="/common/base/header.jsp"   />

			<!-- Main -->
			<!-- Main -->
			<div id="main">

				<article class="post">
					<!--文章头-->
					<header>
						<div class="title">
							<h2><a href="#">${bean.title}</a></h2>
							<p>文章描述</p>
						</div>
						<div class="meta">
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
			<jsp:include page="/WEB-INF/pages/common/leftSidebar.jsp"   />
		</div>
	</body>
	<jsp:include page="/common/base/footer.jsp"   />
	<%--<jsp:include page="/common/base/ckfooter.jsp" />--%>

</html>

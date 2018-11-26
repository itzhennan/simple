<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>文章列表</title>
		<jsp:include page="/common/base/head.jsp"   />
		<link rel="stylesheet" href="${csshost}/css/assets/main.css"/>
		<%--<link rel="stylesheet" href="${csshost}/assets/css/mains.css"/>--%>
		<%--代码主题css--%>
		<link rel="stylesheet" href="${csshost}/js/vendor/ckeditor/4.11.1/plugins/codesnippet/lib/highlight/styles/xcode.css">
	</head>
	<body>
		<div id="wrapper">
			<jsp:include page="/common/base/header.jsp"   />

			<!-- Main -->
			<div id="main-sidebar-div">
				<div id="main">
					<!-- Post -->
					<article class="post">

						<c:forEach items="${page.datas}" var="data">

								<h2 class="abstracts">
									<c:choose>
											<c:when test="${data.typ eq 1}"><span class="article-type type-1">原</span></c:when>
											<c:when test="${data.typ eq 2}"><span class="article-type type-2">转</span></c:when>
											<c:otherwise><span class="article-type type-3">译</span></c:otherwise>
									</c:choose>
									<a href="${ctx}/article/${data.artid}" class="abstracts">${data.titl}</a>
								</h2>


							<time class="abstracts right" datetime="${data.publicDateTime}">${data.publicDateTime}</time>
							<p class="abstracts">${data.abstracts}</p>
						</c:forEach>


						<h2 class="abstracts"><span class="article-type type-1 ">原</span>知乎如何洞察你的真实喜好？首页信息流技术揭秘</h2>
						<time class="abstracts right" datetime="2015-10-25 00:00:00">2015-10-25 00:00:00</time>
						<p class="abstracts">11月8-9日，由中国 IT 社区 CSDN 与硅谷 AI 社区 A...</p>


						<h2 class="abstracts"><span class="article-type type-2">转</span>盛会再临，2018中国大数据技术大会（BDTC）首曝日程及议题</h2>
						<time class="abstracts right" datetime="2015-10-25 00:00:00">2015-10-25 00:00:00</time>
						<p class="abstracts">满目皆干货，俯仰尽拾珠。作为年度技术趋势与行业应用的风向标，连续成功举办十一年的中国大数据技术大会（BDTC）携主题“大数据新应用”再度强势来袭，稳踏技术时代浪潮，势将引爆今冬技术圈。   数据，让一切有迹可循，让一切有源可溯。2018 年12 月 6-8 日，由中国计算机学会主...</p>


						<h2 class="abstracts"><span class="article-type type-3">译</span>Mauris neque quam, fermentum ut nisl vitae</h2>
						<time class="abstracts right" datetime="2015-10-25 00:00:00">2015-10-25 00:00:00</time>
						<p class="abstracts">Mauris neque quam, fermentum ut nisl vitae, convallis maximus nisl. Sed mattis nunc id lorem euismod placerat. Vivamus porttitor magna enim, ac accumsan tortor cursus at. Phasellus sed ultricies mi non congue ullam corper. Praesent tincidunt sed tellus ut rutrum. Sed vitae justo condimentum, porta lectus vitae, ultricies congue gravida diam non fringilla...</p>

						<h2 class="abstracts"><span class="article-type type-3">译</span>Mauris neque quam, fermentum ut nisl vitae</h2>
						<time class="abstracts right" datetime="2015-10-25 00:00:00">2015-10-25 00:00:00</time>
						<p class="abstracts">Fringilla nisl. Donec accumsan interdum nisi, quis tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac a...</p>

					</article>

					<!-- Pagination -->
				<ul class="actions pagination">
					<li><a href="" class="disabled button big previous">Previous Page</a></li>
					<li><a href="#" class="button big next">Next Page</a></li>
				</ul>

			</div>
			</div>

			<div id="left-sidebar-div">
				<jsp:include page="/WEB-INF/pages/common/leftSidebar.jsp"   />
			</div>

		</div>
	</body>
	<jsp:include page="/common/base/footer.jsp"   />
	<%--文章详情--%>
	<script type="text/javascript" src="${jshost}/js/article/dmain.js"></script>
	<%--代码高亮--%>
	<script type="text/javascript" src="${jshost}/js/vendor/ckeditor/4.11.1/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
	<%--数学公式--%>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.4/MathJax.js?config=TeX-AMS_HTML"></script>
</html>

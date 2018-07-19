<!DOCTYPE HTML>
<!--
	Zznlin Blog by ZZNLIN
-->
<html>
	<head>
		<title>Zznlin Bolg</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="assets/css/main.css" />
		<link rel = "Shortcut Icon" href="images/logo.ico" />
		<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
		<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
	</head>
	<body>

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Header -->
					<header id="header">
						<h1><a href="${server.url}">${server.text}</a></h1>
						<nav class="links">
							<ul>
								<#list navigations as n>
									<li><a href="${n.url}">${n.text}</a></li>
								</#list>
							</ul>
						</nav>
						<nav class="main">
							<ul>
								<li class="search">
									<a class="fa-search" href="#search">Search</a>
									<form id="search" method="get" action="${search.url}">
										<input type="text" name="query" placeholder="${search.text}" />
									</form>
								</li>
								<li class="menu">
									<a class="fa-bars" href="#menu">Menu</a>
								</li>
							</ul>
						</nav>
					</header>

				<!-- Menu -->
					<section id="menu">

						<!-- Search -->
							<section>
								<form class="search" method="get" action="${search.url}">
									<input type="text" name="query" placeholder="${search.text}" />
								</form>
							</section>

						<!-- Links -->
							<section>
								<ul class="links">
									<#list menus as m>
                                        <li>
                                            <a href="${m.url}">
                                                <h3>${m.title}</h3>
                                                <p>${m.text}</p>
                                            </a>
                                        </li>
									</#list>
								</ul>
							</section>

						<!-- Actions -->
							<section>
								<ul class="actions vertical">
									<li><a href="${login.url}" class="button big fit">${login.text}</a></li>
								</ul>
							</section>

					</section>

				<!-- Main -->
					<div id="main">
						<!-- Post -->
						<#list mainInfo as info>
							<article class="post">
								<header>
									<div class="title">
										<h2><a href="${info.header.url}">${info.header.title}</a></h2>
										<p>${info.header.text}</p>
									</div>
									<div class="meta">
										<time class="published" datetime="${info.author.time}">${info.author.time}</time>
										<a href="${info.author.url}" class="author"><span class="name">${info.author.text}</span><img src="${info.author.imageUrl}" alt="" /></a>
									</div>
								</header>
								<a href="${info.body.url}" class="image featured"><img src="${info.body.imageUrl}" alt="" /></a>
								<p>${info.body.text}</p>
								<footer>
									<ul class="actions">
										<li><a href="${info.footer.readText.url}" class="button big">${info.footer.readText.text}</a></li>
									</ul>
									<ul class="stats">
                                        <li><a href="${info.footer.general.url}">${info.footer.general.text}</a></li>
										<li><a href="${info.footer.love.url}" class="icon fa-heart">${info.footer.love.text}</a></li>
										<li><a href="${info.footer.comment.url}" class="icon fa-comment">${info.footer.comment.text}</a></li>
									</ul>
								</footer>
							</article>
						</#list>
					</div>

				<!-- Sidebar -->
					<section id="sidebar">

						<!-- Intro -->
							<section id="intro">
								<a href="#" class="logo"><img src="${user.imageUrl}" alt="" /></a>
								<header>
                                    <h2>${user.text}</h2>
									<p>${user.content}</p>
								</header>
							</section>

						<!-- Mini Posts -->
							<section>
								<div class="mini-posts">

									<!-- Mini Post -->
									<#list categorys as c>
                                        <article class="mini-post">
                                            <header>
                                                <h3><a href="${c.header.url}">${c.header.title}</a></h3>
                                                <time class="published" datetime="${c.header.time}">${c.header.time}</time>
                                                <a href="${c.author.url}" class="author"><img src="${c.author.imageUrl}" alt="" /></a>
                                            </header>
                                            <a href="${c.header.url}" class="image"><img src="${c.header.imageUrl}" alt="" /></a>
                                        </article>
									</#list>
								</div>
							</section>

						<!-- Posts List -->
							<section>
								<ul class="posts">
									<li>
										<article>
											<header>
												<h3><a href="#">热门阅读1热门阅读1热门阅读1热门阅读1热门阅读1热门阅读1热门阅读1</a></h3>
												<footer>
													<time class="published" datetime="2015-10-20">2018年7月18日</time>
													<ul class="stats">
														<li><a href="javascript:void(0);" class="icon fa-heart">999999</a></li>
														<li><a href="javascript:void(0);" class="icon fa-comment">999999</a></li>
													</ul>
												</footer>
											</header>
											<a href="#" class="image"><img src="images/pic08.jpg" alt="" /></a>
										</article>
									</li>
									<li>
										<article>
											<header>
												<h3><a href="#">热门阅读1热门阅读1热门阅读1热门阅读1热门阅读1热门阅读1热门阅读1</a></h3>
												<footer>
													<time class="published" datetime="2015-10-20">2018年7月18日</time>
													<ul class="stats">
														<li><a href="javascript:void(0);" class="icon fa-heart">999999</a></li>
														<li><a href="javascript:void(0);" class="icon fa-comment">999999</a></li>
													</ul>
												</footer>
											</header>
											<a href="#" class="image"><img src="images/pic09.jpg" alt="" /></a>
										</article>
									</li>
									<li>
										<article>
											<header>
												<h3><a href="#">热门阅读1热门阅读1热门阅读1热门阅读1热门阅读1热门阅读1热门阅读1</a></h3>
												<footer>
													<time class="published" datetime="2015-10-20">2018年7月18日</time>
													<ul class="stats">
														<li><a href="#" class="icon fa-heart">999999</a></li>
														<li><a href="#" class="icon fa-comment">999999</a></li>
													</ul>
												</footer>
											</header>
											<a href="#" class="image"><img src="images/pic10.jpg" alt="" /></a>
										</article>
									</li>
								</ul>
							</section>

						<!-- About -->
							<section class="blurb">
								<h2>About</h2>
								<p>Mauris neque quam, fermentum ut nisl vitae, convallis maximus nisl. Sed mattis nunc id lorem euismod amet placerat. Vivamus porttitor magna enim, ac accumsan tortor cursus at phasellus sed ultricies.</p>
								<ul class="actions">
									<li><a href="#" class="button">Learn More</a></li>
								</ul>
							</section>

						<!-- Footer -->
							<section id="footer">
								<ul class="icons">
									<li><a href="#" class="fa-twitter"><span class="label">Twitter</span></a></li>
									<li><a href="#" class="fa-facebook"><span class="label">Facebook</span></a></li>
									<li><a href="#" class="fa-instagram"><span class="label">Instagram</span></a></li>
									<li><a href="#" class="fa-rss"><span class="label">RSS</span></a></li>
									<li><a href="#" class="fa-envelope"><span class="label">Email</span></a></li>
								</ul>
								<p class="copyright">&copy; Untitled. Design: <a rel="nofollow" href="http://html5up.net">HTML5 UP</a>. Images: <a rel="nofollow" href="http://unsplash.com">Unsplash</a>.</p>
							</section>

					</section>

			</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="assets/js/main.js"></script>

	</body>
</html>
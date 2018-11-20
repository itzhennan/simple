<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 左侧导航 -->
<section id="left-sidebar">

    <!-- Intro -->
    <section id="intro">
        <a href="#" class="logo"><img src="${imgServer}/images/logo.jpg" alt="" /></a>
        <header>

            <h2>Zznlin Blog</h2>
            <p>随时随地随想随写<br/>记录生活的<a href='#'>点点滴滴</a></p>
        </header>
    </section>

    <!-- Mini Posts -->
    <section>
        <div class="mini-posts">

            <!-- Mini Post -->
            <article class="mini-post">
                <header>
                    <h3><a href="#">学习</a></h3>
                    <time class="published" datetime="2015-10-20">2015年10月20日</time>
                    <a href="#" class="author"><img src="${imgServer}/images/avatar.jpg" alt="" /></a>
                </header>
                <a href="#" class="image"><img src="${imgServer}/images/pic04.jpg" alt="" /></a>
            </article>

            <!-- Mini Post -->
            <article class="mini-post">
                <header>
                    <h3><a href="#">个人随笔</a></h3>
                    <time class="published" datetime="2015-10-19">2015年10月19日</time>
                    <a href="#" class="author"><img src="${imgServer}/images/avatar.jpg" alt="" /></a>
                </header>
                <a href="#" class="image"><img src="${imgServer}/images/pic05.jpg" alt="" /></a>
            </article>
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
                            <%--<time class="published" datetime="2015-10-20">2018年7月18日</time>--%>
                            <ul class="stats">
                                <li><a href="javascript:void(0);" class="icon fa-heart">999999</a></li>
                                <li><a href="javascript:void(0);" class="icon fa-comment">999999</a></li>
                            </ul>
                        </footer>
                    </header>
                    <a href="#" class="image"><img src="${imgServer}/images/pic08.jpg" alt="" /></a>
                </article>
            </li>
            <li>
                <article>
                    <header>
                        <h3><a href="#">热门阅读1热门阅读1热门阅读1热门阅读1热门阅读1热门阅读1热门阅读1</a></h3>
                        <footer>
                            <%--<time class="published" datetime="2015-10-20">2018年7月18日</time>--%>
                            <ul class="stats">
                                <li><a href="javascript:void(0);" class="icon fa-heart">999999</a></li>
                                <li><a href="javascript:void(0);" class="icon fa-comment">999999</a></li>
                            </ul>
                        </footer>
                    </header>
                    <a href="#" class="image"><img src="${imgServer}/images/pic09.jpg" alt="" /></a>
                </article>
            </li>
            <li>
                <article>
                    <header>
                        <h3><a href="#">热门阅读1热门阅读1热门阅读1热门阅读1热门阅读1热门阅读1热门阅读1</a></h3>
                        <footer>
                            <%--<time class="published" datetime="2015-10-20">2018年7月18日</time>--%>
                            <ul class="stats">
                                <li><a href="#" class="icon fa-heart">999999</a></li>
                                <li><a href="#" class="icon fa-comment">999999</a></li>
                            </ul>
                        </footer>
                    </header>
                    <a href="#" class="image"><img src="${imgServer}/images/pic10.jpg" alt="" /></a>
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


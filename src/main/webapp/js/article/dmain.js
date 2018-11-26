/*
	Future Imperfect by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
*/

(function($) {

	skel.breakpoints({
		xlarge:	'(max-width: 1680px)',
		large:	'(max-width: 1280px)',
		medium:	'(max-width: 980px)',
		small:	'(max-width: 736px)',
		xsmall:	'(max-width: 480px)'
	});

	$(function() {

		var	$window = $(window),
			$body = $('body'),
			$menu = $('#menu'),

			$leftSidebar = $('#left-sidebar'),
			$leftSidebarDiv = $('#left-sidebar-div'),
            $leftSidebarHeight,
            $leftSidebarWidth,
            // 左边是否固定
            leftScrollFlag = false,


            $rightSidebar = $('#right-sidebar'),
            $rightSidebarDiv = $('#right-sidebar-div'),
            $rightSidebarHeight,
            $rightSidebarWidth,
            // 右边是否固定
            rightScrollFlag = false,

			$main = $('#main'),
            $mainSidebarDiv = $('#main-sidebar-div'),

            $mainHeight,
            paddWidth, // 单边padding的宽度
            rightSidebarFlag = false,

            scrollEnd = false;

		// Disable animations/transitions until the page has loaded.
			$body.addClass('is-loading');

			$window.on('load', function() {
				window.setTimeout(function() {
					$body.removeClass('is-loading');
				}, 100);
			});

		// Fix: Placeholder polyfill.
			$('form').placeholder();

		// Prioritize "important" elements on medium.
			skel.on('+medium -medium', function() {
				$.prioritize(
					'.important\\28 medium\\29',
					skel.breakpoint('medium').active
				);
			});

		// IE<=9: Reverse order of main and $leftSidebarDiv.
			if (skel.vars.IEVersion <= 9)
				$main.insertAfter($leftSidebarDiv);

		// Menu.
			$menu
				.appendTo($body)
				.panel({
					delay: 500,
					hideOnClick: true,
					hideOnSwipe: true,
					resetScroll: true,
					resetForms: true,
					side: 'right',
					target: $body,
					visibleClass: 'is-menu-visible'
				});

		// Search (header).
			var $search = $('#search'),
				$search_input = $search.find('input');

			$body
				.on('click', '[href="#search"]', function(event) {

					event.preventDefault();

					// Not visible?
						if (!$search.hasClass('visible')) {

							// Reset form.
								$search[0].reset();

							// Show.
								$search.addClass('visible');

							// Focus input.
								$search_input.focus();

						}

				});

			$search_input
				.on('keydown', function(event) {

					if (event.keyCode == 27)
						$search_input.blur();

				})
				.on('blur', function() {
					window.setTimeout(function() {
						$search.removeClass('visible');
					}, 100);
				});

				// Intro. 个人简介
				var $intro = $('#intro');

				// Move to main on <=large, back to $leftSidebarDiv on >large.
				// 屏幕像素断点变化
				skel.on('+large', function() {
                    	$rightSidebar.appendTo($main);
                    	$main.unbind();
                    	// 解绑滚动监听
                     	leftScrollFlag = false;
                     	rightScrollFlag = false;
                    	scrollBarEnd();
						$rightSidebar.css("position","");
						$rightSidebar.css("top","");
						$leftSidebar.css("position","");
						$leftSidebar.css("top","");
						$(".icon.fa-expand").hide();
					})
					.on('-large', function() {
						// 在电脑上  像素大于 1280px
                        $rightSidebar.appendTo($rightSidebarDiv);
                        // 个人信息展示
                        switchSideBar();
                        // 左右边框随滚动条滑动
                        scrollBarStart();
                        // 全屏开关
                        $(".icon.fa-expand").show();
					});



		// 代码高亮
        hljs.initHighlightingOnLoad();

        // 电脑上显示
        if($window.innerWidth() > 980){
            if($leftSidebarDiv.is(':hidden')) {　　// 左边不显示的时候，需要左中右切换
                // 个人信息展示
                switchSideBar();
            }
            // 左右边框随滚动条滑动
            scrollBarStart();
		}

		// 全屏显示文章，不展示 评论栏
		$(".icon.fa-expand").click(function(){
			if($rightSidebarDiv.is(':hidden')){　　//如果node是隐藏的则显示node元素，否则隐藏
				rightSidebarFlag = false;
				$rightSidebarDiv.show();
			}else{
				rightSidebarFlag = true;
				$rightSidebarDiv.hide();
			}
		});

        function switchSideBar(){
            $leftSidebarDiv.hide();
            $rightSidebarDiv.show();
            // 鼠标移出
            $main.mouseout(function(event){
                var tempX =event.clientX;
                if(tempX <= paddWidth){
                    // 是否滚动到底部
                    if($("body").outerHeight() == $(document).scrollTop()+$(window).height()){
                        scrollEnd = true;
                    }else{
                        scrollEnd = false;
                    }
                	if($main.css("position") == "fixed"){
                        $main.css({"position":"","right":"","width":"","bottom":""});
					}
                    $rightSidebarDiv.hide();
                    // 左 中判断 谁需要固定
                    $leftSidebarDiv.show();
                    leftScrollFlag = false;
                    rightScrollFlag = false;
                    if(scrollEnd){
                        // 之前滚动底部后，现在也需要在底部
                        $(document).scrollTop($("body").outerHeight()-$(window).height());
                    }
                    scrollBar();
                }
            });
            // 鼠标移入
            $main.mouseover(function(event){
                if(!$leftSidebarDiv.is(':hidden')) {　　//
                    // 是否滚动到底部
                    if($("body").outerHeight() == $(document).scrollTop()+$(window).height()){
                        scrollEnd = true;
                    }else{
                        scrollEnd = false;
                    }
                    $leftSidebarDiv.hide();
                    if($main.css("position") == "fixed"){
                        $main.css({"position":"","right":"","width":"","bottom":""});
                        $("#main-sidebar-div").css("width","");
                        // 左 中判断 谁需要固定
                    }
                    // 只有在用户允许显示的时候，才可以显示
                    if(!rightSidebarFlag){
                        $rightSidebarDiv.show();
                    }
                    leftScrollFlag = false;
                    rightScrollFlag = false;
                    if(scrollEnd){
                        // 之前滚动底部后，现在也需要在底部
                        $(document).scrollTop($("body").outerHeight()-$(window).height());
                    }
                    scrollBar();
                }
                // 单边padding的宽度一半
                paddWidth = $("#wrapper").css("padding").replace("px","");
            });
		}

        // 开启滚动监听
		function scrollBarStart(){
            $(window).on("scroll",scrollBar);
		}
		// 关闭滚动监听
        function scrollBarEnd(){
            $(window).unbind("scroll");
        }

		// 个人信息栏和评论栏 滚动监听
        function scrollBar() {
            var em = $body.css("font-size").replace("px","");
            $leftSidebarHeight = $('#left-sidebar').height();
            $rightSidebarHeight = $('#right-sidebar').height();

            $leftSidebarWidth = $('#left-sidebar-div').outerWidth(true);
            $rightSidebarWidth = $('#right-sidebar-div').outerWidth(true);

            $mainHeight = $('#main').height();
            var windowHeight = $(window).height();

            var bodyPaddingTop = parseInt($("body").css("padding-top").replace("px",""));
			var wrapperPadding = parseInt($("#wrapper").css("padding").replace("px",""));

            var bottomScrollTop,$tempBar,$tempCssUp,$tempCssDown;

            // 获取垂直滚动的距离
            var scrollTop = $(document).scrollTop();

            if(!$rightSidebarDiv.is(':hidden')){　　//
				// 中间小，中间需要悬浮
				if($mainHeight < $rightSidebarHeight){
                    bottomScrollTop = $mainHeight+(bodyPaddingTop+wrapperPadding)-windowHeight;
                    // $("#scrop").html(scrollTop+","+$(window).height()+","+$("body").outerHeight()+","+(6.5*parseInt(em))+","+bottomScrollTop);
                    $tempBar = $('#main');
                    var tempWidth = $(window).width()-(2*wrapperPadding)-$rightSidebarWidth;
                    $tempCssUp = {"position":"fixed","right":"19em","width":tempWidth+"px","bottom":"0em"};
                    $tempCssDown = {"position":"","right":"","width":"","bottom":""};
                    // 当前垂直滚动的距离 > 最底部距离  说明滑动到 最小的底部  需要绝对定位
                    if (scrollTop >= bottomScrollTop) {
                        if(!rightScrollFlag){
                            $tempBar.css($tempCssUp);
                        }
                        rightScrollFlag = true;
                    }else{
                        if(rightScrollFlag) {
                            $tempBar.css($tempCssDown);
                            rightScrollFlag = false;
                        }
                    }
				}else{
                    bottomScrollTop = $rightSidebarHeight+(bodyPaddingTop+wrapperPadding)-windowHeight;
                    // $("#scrop").html(scrollTop+","+$(window).height()+","+$("body").outerHeight()+","+(6.5*parseInt(em))+","+bottomScrollTop);
                    $tempBar = $rightSidebar;
                    $tempCssUp = {"position":"fixed","bottom":"0em"};
                    $tempCssDown = {"position":"","bottom":""};
                    // 当前垂直滚动的距离 > 最底部距离  说明滑动到 最小的底部  需要绝对定位
                    if (scrollTop >= bottomScrollTop) {
                        if(!rightScrollFlag){
                            $tempBar.css($tempCssUp);
                        }
                        rightScrollFlag = true;
                    }else{
                        if(rightScrollFlag) {
                            $tempBar.css($tempCssDown);
                            $("#main-sidebar-div").css("width","");
                            rightScrollFlag = false;
                        }
                    }
				}
            }
			// 左边
            if(!$leftSidebarDiv.is(':hidden')){　　//
                // 中间小，中间需要悬浮
                var bottomScrollTop,$tempBar,$tempCssUp,$tempCssDown;
                if($mainHeight < $leftSidebarHeight){
                    bottomScrollTop = $mainHeight+(bodyPaddingTop+wrapperPadding)-windowHeight;
                    // $("#scrop").html(scrollTop+","+$(window).height()+","+$("body").outerHeight()+","+(6.5*parseInt(em))+","+bottomScrollTop);
                    $tempBar = $('#main');
                    var tempWidth = $(window).width()-(2*wrapperPadding)-$leftSidebarWidth;
                    $tempCssUp = {"position":"fixed","right":"3em","width":tempWidth+"px","bottom":"0"};
                    $tempCssDown = {"position":"","right":"","width":"","bottom":""};

                    // 当前垂直滚动的距离 > 最底部距离  说明滑动到 最小的底部  需要绝对定位
                    if (scrollTop >= bottomScrollTop) {
                        if(!leftScrollFlag){
                            $tempBar.css($tempCssUp);
                            $("#main-sidebar-div").css("width",tempWidth+"px");
                        }
                        leftScrollFlag = true;
                    }else{
                        if(leftScrollFlag) {
                            $tempBar.css($tempCssDown);
                            leftScrollFlag = false;
                            $("#main-sidebar-div").css("width","");
                        }
                    }

                }else{
                    bottomScrollTop = $leftSidebarHeight+(bodyPaddingTop+wrapperPadding)-windowHeight;
                    // $("#scrop").html(scrollTop+","+$(window).height()+","+$("body").outerHeight()+","+(6.5*parseInt(em))+","+bottomScrollTop);
                    $tempBar = $leftSidebar;
                    $tempCssUp = {"position":"fixed","bottom":"0"};
                    $tempCssDown = {"position":"","bottom":""};
                    // 当前垂直滚动的距离 > 最底部距离  说明滑动到 最小的底部  需要绝对定位
                    if (scrollTop >= bottomScrollTop) {
                        if(!leftScrollFlag){
                            $tempBar.css($tempCssUp);
                        }
                        leftScrollFlag = true;
                    }else{
                        if(leftScrollFlag) {
                            $tempBar.css($tempCssDown);
                            leftScrollFlag = false;
                        }
                    }
                }
            }
        }

	});

})(jQuery);
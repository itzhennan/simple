/*
 Author: ZZNLin
 Website: http://www.zznlin.cn
 */
var currentIndex,lastIndex=0,switchInt, handleLoginPageChangeBackground = function () {
    $(document).on("click", '[data-click="change-bg"]', function (a) {
        a.preventDefault();
        clickBGI($(this));
    });
},clickBGI = function(obj){
    var n = "url(" + $(obj).attr("data-img") + ")";
    $('[data-id="login-cover-image"]').css("background-image", n), $('[data-click="change-bg"]').closest("li").removeClass("active"), $(obj).closest("li").addClass("active")
}, userLogin =function() {
    'use strict';
    window.addEventListener('load', function() {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                // Login
                var host = $IEC.getCurrentURI();
                $IEC.ajaxCall({
                    uri : host+"/user/login",
                    type : "post",
                    data : $IEC.getSwitchPost($(this).serialize()),
                    success : function(data) {
                        if(data.code == 0){
                            $IEC.doHref(host+"/admin/html/index_2.html");
                        }else {
                            // Login error msg and close msg after next click
                            $("#login-pwd").popover('show');
                            $("body").click(hideOnClick);
                        }
                    }
                });
                return false;
            }, false);
        });
    }, false);
},hideOnClick = function(){
    // login error hide and unbind click on body
    $("#login-pwd").popover('hide');
    $("body").unbind("click");
}, LoginV2 = function () {
    "use strict";
    return {
        init: function () {
            handleLoginPageChangeBackground();
            userLogin();
            // Auto Switch BackGroundImage<BGI>
            currentIndex = 1;
            var maxIndex = $(".clearfix").children().length;
            var switchInt = window.setInterval(function(){
                clickBGI($('.clearfix').children().eq(currentIndex).children().eq(0));
                currentIndex = currentIndex >= (maxIndex-1) ? 0: currentIndex + 1;
            },5000);
        }
    }
}();
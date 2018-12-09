$(function(){
    simpleInit();
});
var simpleInit = function(){
    $IEC.ajaxCall({
        uri : $IEC.getCurrentURI() + "/user/loginInfo",
        type : "GET",
        success : function(data) {
            if(data.code == 0 ){
                var datas = data.data;
                var name = datas.name,
                url = datas.url,
                address = datas.address;
                $("#header-name").text(name);
                $("#header-img").attr("src",url);

                $("#sidebar-name").text(name);
                $("#sidebar-img").attr("src",url);
                $("#sidebar-address").text(address);
            }else{

            }
        }
    });
};
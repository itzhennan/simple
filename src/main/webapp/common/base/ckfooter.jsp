<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript" src="https://cdn.ckeditor.com/4.10.0/standard-all/ckeditor.js"></script>
<script type="text/javascript" src="${jshost}/js/editor/autotag.js" ></script>
<script type="text/javascript" src="${jshost}/js/editor/simple.js" ></script>
<script type="text/javascript" src="${jshost}/js/editor/CKposteditnew.js" ></script>
<script>
    var config;
    $.getJSON('../json/ckeditor.json',function(result){
        config = result;
        CKEDITOR.replace( 'editor', config);
    });
    function selectSheet(sheetval){
        var sheet = $("#select").val();
        if ( CKEDITOR.instances.editor )
            CKEDITOR.instances.editor.destroy();

        CKEDITOR.replace( 'editor', CKEDITOR.tools.extend( {}, config, {
            codeSnippet_theme: sheet
        }, true ) );
    }
    // 文章标签和个人分类
    AutoTag(true,1);
    AutoTag(true,2);
</script>
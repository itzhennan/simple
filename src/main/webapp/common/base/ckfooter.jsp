<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<script type="text/javascript" src="https://cdn.ckeditor.com/4.10.0/standard-all/ckeditor.js"></script>--%>
<script type="text/javascript" src="${jshost}/js/vendor/ckeditor/4.11.1/ckeditor.js" ></script>
<script type="text/javascript" src="${jshost}/js/editor/autotag.js" ></script>
<script type="text/javascript" src="${jshost}/js/editor/simple.js" ></script>
<script type="text/javascript" src="${jshost}/js/editor/CKposteditnew.js" ></script>

<script>
    CKEDITOR.replace( 'editor');
    function selectSheet(sheetval){
        var sheet = $("#select").val();
        if ( CKEDITOR.instances.editor )
            CKEDITOR.instances.editor.destroy();

        CKEDITOR.replace( 'editor', {extraPlugins: 'codesnippet',codeSnippet_theme: sheet});
    };
    // 文章标签和个人分类
    AutoTag(true,1);
    AutoTag(true,2);
</script>
/**
 * @license Copyright (c) 2003-2018, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */
CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here.
	// For complete reference see:
	// http://docs.ckeditor.com/#!/api/CKEDITOR.config
    config.extraPlugins = 'image2,mathjax,codesnippet,font';
    config.removeButtons = 'Embedsemantic,Font';
    config.height = '800';
    config.mathJaxLib = 'https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.4/MathJax.js?config=TeX-AMS_HTML';
    config.codeSnippet_theme = 'monokai_sublime';

    config.filebrowserBrowseUrl = 'http://localhost:18800/';
    config.filebrowserImageBrowseUrl = 'http://localhost:18800/';
    config.filebrowserUploadUrl = 'http://localhost:9999/upload/uploadFile?file=file';
    config.filebrowserImageUploadUrl = 'http://localhost:9999/upload/uploadFile?type=image';

    // Define changes to default configuration here.
    // For complete reference see:
    // http://docs.ckeditor.com/#!/api/CKEDITOR.config

    // The toolbar groups arrangement, optimized for two toolbar rows.
    config.toolbarGroups = [
        { name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
        { name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
        { name: 'links' },
        { name: 'insert' },
        { name: 'forms' },
        { name: 'tools' },
        { name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
        { name: 'others' },
        '/',
        { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
        { name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
        { name: 'styles' },
        { name: 'colors' },
        { name: 'about' }
    ];

    // Remove some buttons provided by the standard plugins, which are
    // not needed in the Standard(s) toolbar.
    config.removeButtons = 'Underline,Subscript,Superscript';

    // Set the most common block elements.
    config.format_tags = 'p;h1;h2;h3;h4;h5;h6;pre;address;div';

    // Simplify the dialog windows.
    // config.removeDialogTabs = 'image:advanced;link:advanced';

    //设置快捷键
    config.keystrokes = [
        [ CKEDITOR.ALT + 121 /*F10*/, 'toolbarFocus' ],  //获取焦点
        [ CKEDITOR.ALT + 122 /*F11*/, 'elementsPathFocus' ],  //元素焦点
        [ CKEDITOR.SHIFT + 121 /*F10*/, 'contextMenu' ],  //文本菜单
        [ CKEDITOR.CTRL + 90 /*Z*/, 'undo' ],  //撤销
        [ CKEDITOR.CTRL + 89 /*Y*/, 'redo' ],  //重做
        [ CKEDITOR.CTRL + CKEDITOR.SHIFT + 90 /*Z*/, 'redo' ],  //
        [ CKEDITOR.CTRL + 76 /*L*/, 'link' ],  //链接
        [ CKEDITOR.CTRL + 66 /*B*/, 'bold' ],  //粗体
        [ CKEDITOR.CTRL + 73 /*I*/, 'italic' ],  //斜体
        [ CKEDITOR.CTRL + 85 /*U*/, 'underline' ],  //下划线
        [ CKEDITOR.ALT + 109 /*-*/, 'toolbarCollapse' ]
    ]
    //设置快捷键 可能与浏览器快捷键冲突 plugins/keystrokes/plugin.js.
    config.blockedKeystrokes = [
        CKEDITOR.CTRL + 66 /*B*/,
        CKEDITOR.CTRL + 73 /*I*/,
        CKEDITOR.CTRL + 85 /*U*/
    ]

    // Tab键时，使用编辑器向文本添加一些空格（）。如果设置为零，则该Tab键将用于将光标焦点移动到编辑器焦点之外的页面中的下一个元素。
    config.tabSpaces = 4;
    
    //是否在选择颜色时显示“其它颜色”选项 plugins/colorbutton/plugin.js
    config.colorButton_enableMore = true
    //区块的前景色默认值设置 plugins/colorbutton/plugin.js
    config.colorButton_foreStyle = {
        element : 'span',
        styles : { 'color' : '#(color)' }
    };
    // 字体默认大小 plugins/font/plugin.js
    config.fontSize_defaultLabel = '20px';
    //字体编辑时可选的字体大小 plugins/font/plugin.js
    config.fontSize_sizes ='8/8px;9/9px;10/10px;11/11px;12/12px;14/14px;16/16px;18/18px;20/20px;22/22px;24/24px;26/26px;28/28px;36/36px;48/48px;72/72px';

    //默认的字体名 plugins/font/plugin.js
    config.font_defaultLabel = '楷体';
    //字体编辑时的字符集 可以添加常用的中文字符：宋体、楷体、黑体等 plugins/font/plugin.js
    config.font_names = '宋体;楷体;黑体;Arial;Times New Roman;Verdana';
    //是否强制复制来的内容去除格式 plugins/pastetext/plugin.js
    config.forcePasteAsPlainText =false //不去除

    //对address标签进行格式化 plugins/format/plugin.js
    config.format_address = { element : 'address', attributes : { class : 'styledAddress' } };
    //对DIV标签自动进行格式化 plugins/format/plugin.js
    config.format_div = { element : 'div', attributes : { class : 'normalDiv' } };
    //对H1标签自动进行格式化 plugins/format/plugin.js
    config.format_h1 = { element : 'h1', attributes : { class : 'contentTitle1' } };
    //对H2标签自动进行格式化 plugins/format/plugin.js
    config.format_h2 = { element : 'h2', attributes : { class : 'contentTitle2' } };
    //对H3标签自动进行格式化 plugins/format/plugin.js
    config.format_h1 = { element : 'h3', attributes : { class : 'contentTitle3' } };
    //对H4标签自动进行格式化 plugins/format/plugin.js
    config.format_h1 = { element : 'h4', attributes : { class : 'contentTitle4' } };
    //对H5标签自动进行格式化 plugins/format/plugin.js
    config.format_h1 = { element : 'h5', attributes : { class : 'contentTitle5' } };
    //对H6标签自动进行格式化 plugins/format/plugin.js
    config.format_h1 = { element : 'h6', attributes : { class : 'contentTitle6' } };
    //对P标签自动进行格式化 plugins/format/plugin.js
    config.format_p = { element: 'p', attributes: { 'class': 'normalPara' } };

    //对PRE标签自动进行格式化 plugins/format/plugin.js
    config.format_pre = { element : 'pre', attributes : { class : 'code' } };
    //用分号分隔的标签名字 在工具栏上显示 plugins/format/plugin.js
    config.format_tags = 'p;h1;h2;h3;h4;h5;h6;pre;address;div';
    //是否使用完整的html编辑模式 如使用，其源码将包含：<html><body></body></html>等标签
    config.fullPage = false;
    //是否忽略段落中的空字符 若不忽略 则字符将以“”表示 plugins/wysiwygarea/plugin.js
    config.ignoreEmptyParagraph = true;
    //在清除图片属性框中的链接属性时 是否同时清除两边的<a>标签 plugins/image/plugin.js
    config.image_removeLinkByEmptyURL = true;

    //对应的表情图片 plugins/smiley/plugin.js
    config.smiley_images = [
        'regular_smile.gif','sad_smile.gif','wink_smile.gif','teeth_smile.gif','confused_smile.gif','tounge_smile.gif',
        'embaressed_smile.gif','omg_smile.gif','whatchutalkingabout_smile.gif','angry_smile.gif','angel_smile.gif','shades_smile.gif',
        'devil_smile.gif','cry_smile.gif','lightbulb.gif','thumbs_down.gif','thumbs_up.gif','heart.gif',
        'broken_heart.gif','kiss.gif','envelope.gif'];
    //表情的地址 plugins/smiley/plugin.js
    config.smiley_path = './plugins/smiley/images/';

    config.image2_alignClasses = [ 'image_left', 'image_fit', 'image_right' ];
    // 去掉BR
    // config.enterMode = CKEDITOR.ENTER_BR;
    // 去掉P
    config.shiftEnterMode = CKEDITOR.ENTER_P;

    //所需要添加的CSS文件 在此添加 可使用相对路径和网站的绝对路径
    config.contentsCss = '../../../../assets/css/ck-main.css';
};

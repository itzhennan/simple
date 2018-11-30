var IECommon = function() {
	function parseJson(text) {
		try {
			return JSON.parse(text);// ie 89 ff ch
		} catch (e) {
			return eval('(' + text + ')'); // ie7
		}
	}

	function json2str(jsonObj) {
		try {
			return JSON.stringify(jsonObj);// ie 89 ff ch
		} catch (e) {
			return jsonObj.toJSONString(); // ie7
		}
	}

	return {
		/**
		 * AJAX基础访问方法
		 *
		 * @param args
		 * @returns
		 */
		ajaxCall : function(args) {
			var defaults = {
				"success" : function() {
				},
				"erorr" : function() {
				},
				"data" : {},
				"timeout" : 30000,
				"headers" : {
					"Content-Type" : "application/json",
					"Accept" : "application/json"
				},
				"async" : {}
			};

			args = $.extend(true, defaults, args);

			var dataStr = "";
			return $.ajax({
				// url : args.uri + '?' + Math.floor(Math.random() * 100),
				url : args.uri,
				type : args.type,
				headers : args.headers,
				timeout : args.timeout,
				data : json2str(args.data),
				success : args.success,
				error : args.error,
				async : args.async
			});
		},

		/**
		 * 获得CHECKBOX集合数据
		 *
		 * @param ckName
		 * @returns
		 */
		getCheckboxVals : function(ckName) {
			var ckValAry = [];
			$("input[name='" + ckName + "']:checkbox").each(function() {
				if ($(this).attr("checked")) {
					ckValAry.push(parseInt($(this).val()));
				}
			});
			return ckValAry;
		},

		getCurrentURI : function(){
			var strFullPath=window.document.location.href;
			var strPath=window.document.location.pathname;
			var pos=strFullPath.indexOf(strPath);
			var prePath=strFullPath.substring(0,pos);
			var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
			return(prePath);
		},

        /**
		 * get 参数 转 post 参数
         * @param data
         * @returns {*}
         */
		getSwitchPost : function(data){
			var jsonData = {};
			if(!data || data == ""){
				return jsonData;
			}
			var dataArr = data.split("&");
			if(dataArr.length == 0){ return jsonData; }
			for (var i = 0;i<dataArr.length;i++){
                var obj = dataArr[i].split("=");
                if(dataArr.length == 0){ return true; }
                var key = obj[0];
                var value = obj[1];
                jsonData[key] = value;
			}
			return jsonData;
		},

		/**
		 * 网址验证
		 *
		 * @param url
		 * @returns
		 */
		//
		doWebSite : function(website) {
			var reg = /^([hH][tT]{2}[pP]:\/\/|[hH][tT]{2}[pP][sS]:\/\/)(([A-Za-z0-9-~]+)\.)+([A-Za-z0-9-~\/])+$/;
			return reg.test(website);
		},

		/**
		 * 手机固话验证
		 *
		 * @param phoneNum
		 * @returns
		 */
		doPhoneNum: function(phoneNum){
			//var result = /(^([0-9][0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^((\(\d{3}\))|(\d{3}\-))?(1[358]\d{9})$)/;
			var phoneRegexp=/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
			var mobileRegexp=/^(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9])[0-9]{8}$/;
			var isCheck = false;
			if (phoneRegexp.test(phoneNum)) {
				isCheck = true;
			} else {
				if (mobileRegexp.test(phoneNum)) {
					isCheck = true;
				}
			}
			return isCheck;
		},

		doPhone: function(phone){
			var phoneRegex = /^0?(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9])[0-9]{8}$/;
			return phoneRegex.test(phone);
		},

		/**
		 * 数字验证
		 *
		 * @param num
		 * @returns
		 */
		doNumCheck : function(num) {
			var regs = /^[0-9]+$/;
			return regs.test(num);
		},

		/**
		 * 数字加小数位验证
		 *
		 * @param num
		 * @returns
		 */
		doDecimalCheck : function(num) {
			var regs = /^\d+\.?\d*$/;
			return regs.test(num);
		},

		/**
		 * 数字加一位小数位验证
		 * @param num
		 * @returns
		 */
		doOnePointDecimalCheck : function(num) {
			var regs = /^[0-9]+([.]{1}[0-9]{1})?$/;
			return regs.test(num);
		},
		/**
		 * 数字加两位小数位验证
		 * @param num
		 * @returns
		 */
		doTwoPointDecimalCheck : function(num) {
			var regs = /^-?\d+(\.\d{1,2})?$/;
			return regs.test(num);
		},

		/**
		 * 是否超过长度限制
		 *
		 */
		isMaxLen : function(val, len) {
			val = $.trim(val);
			if (val && val.length > len) {
				return false;
			}

			return true;
		},

		/**
		 * 判断中英文字符长度
		 */
		strlen : function(str) {
			var len = 0;
			for ( var i = 0; i < str.length; i++) {
				var c = str.charCodeAt(i);

				if ((c >= 0x0001 && c <= 0x007e)
						|| (0xff60 <= c && c <= 0xff9f)) {
					// 英文字节加1
					len++;
				} else {
					// 中文字节加2
					len += 2;
				}
			}
			return len;
		},

		/**
		 * 身份证验证
		 *
		 * @param num
		 * @returns
		 */
		isIDCard : function(str) {
			var regs = /^[1-9]\d{16}[\d|x|X]$/;
			return regs.test(str);
		},

		/**
		 * Email验证
		 *
		 * @param str
		 * @returns
		 */
		isEmail : function(str) {
			var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			return reg.test(str);
		},

		/**
		 * 是否含有特殊字符
		 *
		 * @param str
		 * @returns
		 */
		isSpecial : function(str) {
		    //
            // var pattern = new RegExp("[~'!@#$%^&*()-+_=:]");
			var pattern = new RegExp("[~'!@#$%^&*()-+_=:]");
			return pattern.test(str);
		},

		/**
		 * 只能包含数字，大小写字母
		 *
		 * @param str
		 * @returns
		 */
		isNumberLetter : function(str) {
			var pattern = /^[0-9a-zA-Z]*$/g;
			return pattern.test(str);
		},

		/**
		 * 截取url字符串
		 *
		 */
		subUrl: function (url){
			if(url.indexOf(';') > 0){
				return url.substring(0, url.indexOf(';'));
			}

			return url;
		},

		/**
		 * 基本地址访问
		 *
		 * @param url
		 * @returns
		 */
		doHref : function(url) {
			window.location.href = url;
		},

		/**
		 * 基本PUSH方法
		 *
		 * @param args
		 * @returns
		 */
		doPush : function(args) {
			$IEC.ajaxCall({
				success : function(data) {
					alert(args.sucMsg);
				},
				error : function(data) {
					alert(args.eroMsg);
				},
				uri : args.uri,
				type : args.type
			});
		},
		doFileName : function(uploadFile, names) {
			var name = $("#" + uploadFile).val();
			name = name.substring(name.lastIndexOf("\\") + 1, name.lastIndexOf("."));
			if ($IEC.strlen(name) > 20) {
				name = name.substring(0, 20);
			}
			$("#" + names).val(name);
		},
		/**
		 * 删除之前需要确认
		 *
		 * @param url
		 * @returns
		 */
		doDelete : function(url) {
			if (confirm("确定要删除该记录？")) {
				window.location.href = url;
			}
		},
		/**
		 * 全选
		 */
		selectAll :function(id,name){
			var items=$("input[name='"+name+"']");
			$(items).each(function(){
				if($("#"+id).attr("checked")){
					$(this).attr("checked","true");
				}else{
					$(this).removeAttr("checked");
				}

			});
		},
		/**
		 * 反选
		 */
		setSelectAll :function(id,name){
			var items=$("input[name='"+name+"']");
			var checkedItems=$("input[name='"+name+"']:checked");
			if(items.length===checkedItems.length){
				$("#"+id).attr("checked","cheched");
			}else{
				$("#"+id).removeAttr("checked");
			}
		}
	};
}();
window.$IEC = IECommon;
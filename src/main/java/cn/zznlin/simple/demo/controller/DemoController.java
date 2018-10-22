package cn.zznlin.simple.demo.controller;

import cn.zznlin.simple.common.controller.CommonController;
import cn.zznlin.simple.common.helper.JSONHelper;
import cn.zznlin.simple.common.utils.HttpUtils;
import cn.zznlin.simple.common.utils.JsonUtils;
import cn.zznlin.simple.common.utils.UPYunRestUtils;
import cn.zznlin.simple.demo.pojo.BaseBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/** 
* 
* @author zhennan zhang
* @email  itzhennan@163.com
* @date   2018年8月15日 下午4:21:22
* 
* 类说明 :
*       
*/
@Controller
@RequestMapping("/demo")
public class DemoController extends CommonController {
	
	@RequestMapping(value="/demo")
	public String demo(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		model.addAttribute("msg", "Hello World!");
		return "demo/demo";
	}

	@RequestMapping(value="/getsign")
	public void getsign(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String reqJson = HttpUtils.getReqJson(request);
//		reqJson = URLDecoder.decode(reqJson, "UTF-8");
		BaseBean bean = JSONHelper.fromJson(reqJson, BaseBean.class);

		UPYunRestUtils upYunRestUtils = UPYunRestUtils.newUpYunRestUtils();
		Map<String, String> vparamMap = upYunRestUtils.getMultiUpload(bean.getVideoFileName(),bean.getVideoFileSize());

		HttpUtils.respWrite(response, JsonUtils.toJson(vparamMap));
	}

	@Override
	protected String getModule() {
		// TODO Auto-generated method stub
		return null;
	}

}

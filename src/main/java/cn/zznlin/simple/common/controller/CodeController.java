package cn.zznlin.simple.common.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Producer;

import cn.zznlin.simple.common.helper.KaptchaHelper;
import cn.zznlin.simple.common.utils.CommonJsonFlagBean;
import cn.zznlin.simple.common.utils.HttpUtils;
import cn.zznlin.simple.common.utils.JsonUtils;
import cn.zznlin.simple.common.utils.LoggerUtils;

/** 
* 
* @author zhennan zhang
* @email  itzhennan@163.com
* @date   2018年7月17日 下午4:11:41
* 
* 类说明 :
*       
*/
@Controller
@RequestMapping("/code")
public class CodeController {
	
	private static final String CLASS_NAME = CodeController.class.getName();
	
	@Autowired
	private Producer captchaProducer;
	
	@RequestMapping("/captcha-image")
	public void getCaptchaImage(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		
		// 设置响应头信息
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
		
		// 获得code
		String code = captchaProducer.createText();
		LoggerUtils.debug(CLASS_NAME, "登录验证码: "+code);
		session.setAttribute(KaptchaHelper.SEESSION_VALIDATE_CODE, code);
		// 获得图像
		BufferedImage bi = captchaProducer.createImage(code);
		
		ServletOutputStream os = response.getOutputStream();
		ImageIO.write(bi, "jpg", os);
		
		try {
			os.flush();
		} catch (Exception e) {
			LoggerUtils.debug(CLASS_NAME, "IO写出异常");
		}finally {
			os.close();
		}
	}
	
	@RequestMapping("/checkCode/{code}")
	public void checkCode(HttpServletRequest request,HttpServletResponse response,@PathVariable String code){
		CommonJsonFlagBean commonJsonFlagBean = null;
		try {
			boolean checkStatus = KaptchaHelper.getInstance().checkValidateCode(request, code);
			if(checkStatus)
				commonJsonFlagBean = new CommonJsonFlagBean();
			else
				commonJsonFlagBean = new CommonJsonFlagBean(false, "验证码错误");
		}catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				HttpUtils.respWrite(response, JsonUtils.toJson(commonJsonFlagBean));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}

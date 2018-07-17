package cn.zznlin.simple.common.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import cn.zznlin.simple.common.utils.LoggerUtils;

/** 
* 
* @author zhennan zhang
* @email  itzhennan@163.com
* @date   2018年7月17日 下午3:28:45
* 
* 类说明 :
*       二维码工具类
*/
public class KaptchaHelper {
	private static final String CLASS_NAME = KaptchaHelper.class.getName();
	public static final String SEESSION_VALIDATE_CODE = "sessionValidateCode";
	private static KaptchaHelper kaptchaHelper = new KaptchaHelper();
	
	private KaptchaHelper(){}
	
	public static KaptchaHelper getInstance(){
		return kaptchaHelper;
	}
	
	/**
	 * 验证输入的二维码
	 * @param request
	 * @param code
	 * @return
	 */
	public boolean checkValidateCode(HttpServletRequest request, String code){
		HttpSession session = request.getSession(false);
		if(session == null) return false;
		String validate = obtainSessionValidateCode(session);
		LoggerUtils.debug(CLASS_NAME, "session验证码:"+validate+";实际:"+code);
		if(StringUtils.isNotBlank(code) && validate.equalsIgnoreCase(code)){
			return true;
		}
		return false;
	}
	
	private String obtainSessionValidateCode(HttpSession session){
		Object obj = session.getAttribute(SEESSION_VALIDATE_CODE);
		return obj == null ? "":obj.toString();
	}
	
}

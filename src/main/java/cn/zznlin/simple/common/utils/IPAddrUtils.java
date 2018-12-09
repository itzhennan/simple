package cn.zznlin.simple.common.utils;

import cn.zznlin.simple.common.helper.JSONHelper;
import cn.zznlin.simple.common.pojo.IP4TaobaoBean;
import cn.zznlin.simple.common.pojo.TaobaoData;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class IPAddrUtils {

	/**
	 * 获得IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
	     ipAddress = request.getHeader("X-Real-IP");
	     if(isEmpty(ipAddress)) {
	      ipAddress = request.getHeader("X-Forwarded-For");
	     }
	     if(isEmpty(ipAddress)) {
	         ipAddress = request.getHeader("Proxy-Client-IP");
	     }
	     if(isEmpty(ipAddress)){
	    	 ipAddress = request.getHeader("WL-Proxy-Client-IP");
	     }
	     if(isEmpty(ipAddress)) {
	    	 ipAddress = request.getRemoteAddr();
	    	 if(ipAddress.equals("127.0.0.1")){
	    		 //根据网卡取本机配置的IP
	    		 InetAddress inet=null;
	    		 try {
	    			 inet = InetAddress.getLocalHost();
	    		 } catch (UnknownHostException e) {
	    			 e.printStackTrace();
	    		 }
	    		 ipAddress= inet.getHostAddress();
	    	 }
	     }

	     //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
	     if(ipAddress!=null && ipAddress.length()>15) { //"***.***.***.***".length() = 15
	         if(ipAddress.indexOf(",")>0){
	             ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
	         }
	     }
	     
	     return ipAddress; 
	}
	
	/**
	 * 根据IP地址获取省份和城市
	 * @param ipAddr
	 * @return
	 */
	public static TaobaoData getCityDate4TB(String ipAddr){
		if(isValidIP(ipAddr)){
			String url = "http://ip.taobao.com/service/getIpInfo.php?ip="+ipAddr;
			try{
				IP4TaobaoBean tbbean = JSONHelper.fromJson(HttpUtils.get(url), IP4TaobaoBean.class);
				if(ValidateUtils.isNotEmpty(tbbean)){
					Integer code = tbbean.getCode();
					if(code == 0){
						TaobaoData tdata = tbbean.getData();
						if(ValidateUtils.isNotEmpty(tdata)){
							return tdata;
						}
					}
				}
			}catch(Exception e){
//				e.printStackTrace();
			}			
		}
		return null;
	}
	
	/**
	 * 判断是否为合理的IP地址
	 * 
	 * @param ipAddr
	 * @return
	 */
	private static boolean isValidIP(String ipAddr){
		if(StringUtils.isNotEmpty(ipAddr)){
			if(ipAddr.indexOf(":")<0){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param ipAddress
	 * @return
	 */
	private static boolean isEmpty(String ipAddress){
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			return true;
		}
		
		return false;
	}
}

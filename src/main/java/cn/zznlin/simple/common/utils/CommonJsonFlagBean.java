package cn.zznlin.simple.common.utils;

import java.io.Serializable;

/** 
* 
* @author zhennan zhang
* @email  itzhennan@163.com
* @date   2018年7月17日 下午4:26:40
* 
* 类说明 :
*       
*/
public class CommonJsonFlagBean implements Serializable{

	private static final long serialVersionUID = 12345678910L;
	
	private boolean status = true;
	private String msg = "SUCCESS";

	public CommonJsonFlagBean() {
		super();
	}
	
	public CommonJsonFlagBean(boolean status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}

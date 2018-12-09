package cn.zznlin.simple.common.bean;

import java.util.ArrayList;
import java.util.List;

/** 
 *
 */
@SuppressWarnings("rawtypes")
public class ReturnJsonBean {

	public static final String MSG_SUCCES = "OK";
	public static final int CODE_SUCCESS = 0; // 正常
	public static final int CODE_FAIL = 1; // 参数有误
	public static final int CODE_LIMIT = 2; // 分数不够
	
	protected List datas;
	protected int code;
	protected String msg;


	public ReturnJsonBean(){
		datas = new ArrayList();
		msg = MSG_SUCCES;
		code = CODE_SUCCESS;		
	}

	public List getDatas() {
		return datas;
	}

	public void setDatas(List datas) {
		this.datas = datas;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static String getMsgSucces() {
		return MSG_SUCCES;
	}

	public static int getCodeSuccess() {
		return CODE_SUCCESS;
	}

	public static int getCodeFail() {
		return CODE_FAIL;
	}

	public static int getCodeLimit() {
		return CODE_LIMIT;
	}
}

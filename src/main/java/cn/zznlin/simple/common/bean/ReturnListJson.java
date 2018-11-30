package cn.zznlin.simple.common.bean;

import java.util.ArrayList;
import java.util.List;

/** 
 *
 */
@SuppressWarnings("rawtypes")
public class ReturnListJson {

	public static final String MSG_SUCCES = "OK";
	public static final int CODE_SUCCESS = 0; // 正常
	public static final int CODE_FAIL = 1; // 参数有误
	public static final int CODE_LIMIT = 2; // 分数不够
	
	protected List datas;
	protected int code;
	protected String msg;
	
	private int pageNum = 1;
	
	private int totalPage = 1;

	public ReturnListJson(){
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

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
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

package cn.zznlin.simple.common.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/** 
* 
* @author zhennan zhang
* @email  itzhennan@163.com
* @date   2018年7月17日 下午4:30:57
* 
* 类说明 :
*       http工具类
*/
public class HttpUtils {
	private static final String CLASS_NAME = HttpUtils.class.getName();
	private static int TIMEOUT = 60 * 1000;
	private static String HTTP_HEAD_CONTENT_TYPE = "Content-Type";
	private static String HTTP_HEAD_CONTENT_TYPE_VALUE = "application/json; charset=utf-8";
	
	/**
	 * 写出json数据
	 * @param response
	 * @param jsonStr
	 * @throws Exception
	 */
	public static void respWrite(HttpServletResponse response, String jsonStr)
			throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		LoggerUtils.debug(CLASS_NAME, "Response Json Data: "+jsonStr);
		writer.write(jsonStr);
		writer.flush();
		writer.close();
	}
	
	/**
	 * 获得request的post提交参数信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getReqJson(HttpServletRequest request)
			throws Exception {
		BufferedReader reader = request.getReader();
		StringBuffer buffer = new StringBuffer();
		String str;
		while ((str = reader.readLine()) != null) {
			buffer.append(str);
		}
		reader.close();

		return buffer.toString();
	}
	
	/**
	 * Get HttpClient
	 * 
	 * @return
	 */
	private static HttpClient getHttpClient() {
		HttpClient client = new DefaultHttpClient();
		HttpParams httpParams = client.getParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT);
		return client;
	}
	
	/**
	 * Get the HttpPost info
	 * 
	 * @param url
	 * @param contextString
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static HttpPost getHttpPost(String url, String contextString) throws UnsupportedEncodingException {
		HttpPost post = new HttpPost(url);
		post.setHeader(HTTP_HEAD_CONTENT_TYPE, HTTP_HEAD_CONTENT_TYPE_VALUE);
		post.setEntity(new StringEntity(contextString,"UTF-8"));
		return post;
	}
	
	/**
	 * Use Http to post message
	 * 
	 * @param url
	 * @param request
	 * @return
	 */
	public static String post(String url, String request) {
		LoggerUtils.debug(CLASS_NAME, String.format("Http Status url = [%s]", url));
		LoggerUtils.debug(CLASS_NAME, String.format("Http Status request = [%s]", request));
		HttpClient client = getHttpClient();
		String response = null;
		try {
			HttpPost post = getHttpPost(url, request);
			HttpResponse res = client.execute(post);
			int statusCode = res.getStatusLine().getStatusCode();
			LoggerUtils.debug(CLASS_NAME, String.format("Http Status Code = [%s]", statusCode));
			if (statusCode == 200) {
				response = EntityUtils.toString(res.getEntity(), "UTF-8");
			} else {

			}
		} catch (Exception e) {
			LoggerUtils.error(CLASS_NAME, "Http Component Send Post Request Execption.", e);
		}
		return response;
	}
	

	/**
	 *  Use HttpGet to get response message
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		HttpClient client = getHttpClient();
		LoggerUtils.debug(CLASS_NAME, url);
		HttpGet get = getHttpGet(url);
		String response = null;
		try {
			HttpResponse res = client.execute(get);
			int statusCode = res.getStatusLine().getStatusCode();
			LoggerUtils.debug(CLASS_NAME, String.format("Http Status Code = %s", statusCode));
			if (statusCode == 200) {
				response = EntityUtils.toString(res.getEntity(), "UTF-8");
			} else {
			}
		} catch (Exception e) {
			LoggerUtils.error(CLASS_NAME, "Http Component Send Get Request Execption.", e);
		}
		return response;
	}

	/**
	 * Create HttpGet
	 * 
	 * @param url
	 * @return
	 */
	private static HttpGet getHttpGet(String url) {
		HttpGet get = new HttpGet(url);
		return get;
	}

	public static String getReqJson() throws Exception {
        HttpServletRequest request = ToolUtils.getRequest();
        return getReqJson(request);
    }

    public static Map<String,String> getPostMap() throws Exception {
        return JsonUtils.parseMap(getReqJson());
    }
}

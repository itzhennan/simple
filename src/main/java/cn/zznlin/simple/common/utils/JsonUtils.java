package cn.zznlin.simple.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.zznlin.simple.base.entity.User;

/** 
* 
* @author zhennan zhang
* @email  itzhennan@163.com
* @date   2018年7月17日 下午5:03:09
* 
* 类说明 :
*       
*/
public class JsonUtils {
	private static final String CLASS_NAME = JsonUtils.class.getName();
	
	/**
	 * 将JSON字符串转化成实体Bean对象
	 * @param json
	 * @param classOfT
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> classOfT){
		return new Gson().fromJson(json, classOfT);
	}
	
	/**
	 * 将实体Bean对象转化成JSON字符串
	 * @param jsonElement
	 * @return
	 */
	public static String toJson(Object jsonElement){
		return new Gson().toJson(jsonElement);
	}
	
	/**
	 * 将JSON字符串转化成泛型的数据集合
	 * 
	 * @param json
	 * @param typeToken
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String json, TypeToken<T> typeToken) {
		return (T) new Gson().fromJson(json, typeToken.getType());
	}
	
	/*
	 * ***************************************************
	 * *                  Fast Json                      *
	 * *  @JSONField(serialize=false) 不会对该字段转换                 *
	 * ***************************************************
	 */
	/**
	 * 获得JSON的param参数值
	 * @param json
	 * @param param
	 * @return
	 */
	public static String getParamValue(String json,String param){
		return JSONObject.parseObject(json).getString(param);
	}
	
	/**
	 * 将JSON字符串转化成实体Bean对象
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T parseObject(String json,Class<T> clazz){
		return JSONObject.parseObject(json, clazz);
	}
	
	/**
	 * 将实体Bean对象转化成JSON字符串
	 * @param jsonElement
	 * @return
	 */
	public static String toJSONString(Object object){
		return JSONObject.toJSONString(object);
	}
	
	/**
	 * 将JSON字符串转化成集合对象
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> parseList(String json,Class<T> clazz){
		return JSONArray.parseArray(json, clazz);
	}
	
	
	/**
	 * 将集合对象转化成JSON字符串
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static String toJson(List list){
		return JSON.toJSONString(list);
	}
	

	/**
	 * 将集合对象转化成JSON字符串
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static String toJson(Map map){
		return JSON.toJSONString(map);
	}
	
	/**
	 * 将JSON字符串转化成集合对象
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static Map parseMap(String json){
		return JSONObject.parseObject(json,Map.class);
	}
}

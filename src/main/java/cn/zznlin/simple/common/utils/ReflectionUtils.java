package cn.zznlin.simple.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.joda.time.DateTime;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author Simon Lv
 * @since 2012-5-31
 */
public class ReflectionUtils {
	private static final String CLASS_NAME = ReflectionUtils.class.getName();

	/**
	 * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
	 */
	public static Object getFieldValue(final Object obj, final String fieldName) {
		Field field = getAccessibleField(obj, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
		}

		Object result = null;
		try {
			result = field.get(obj);
		} catch (IllegalAccessException e) {
			LoggerUtils.error(CLASS_NAME, "getFieldValue[{}]", e);
		}
		return result;
	}

	/**
	 * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
	 */
	public static void setFieldValue(final Object obj, final String fieldName, final Object value) {
		Field field = getAccessibleField(obj, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
		}

		try {
			field.set(obj, value);
		} catch (IllegalAccessException e) {
			LoggerUtils.error(CLASS_NAME, "setFieldValue[{}]", e);
		}
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredField, 并强制设置为可访问.
	 * 
	 * 如向上转型到Object仍无法找到, 返回null.
	 */
	public static Field getAccessibleField(final Object object, final String fieldName) {
		Assert.notNull(object, "'object' must not be empty.");
		Assert.hasText(fieldName, "fieldName");

		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				Field field = superClass.getDeclaredField(fieldName);
				field.setAccessible(true);
				return field;
			} catch (NoSuchFieldException e) {// NOSONAR
				// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
		if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException || e instanceof NoSuchMethodException) {
			return new IllegalArgumentException("Reflection Exception.", e);
		} else if (e instanceof InvocationTargetException) {
			return new RuntimeException("Reflection Exception.", ((InvocationTargetException) e).getTargetException());
		} else if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		return new RuntimeException("Unexpected Checked Exception.", e);
	}
	
	/**
	 * @author LE CHEN
	 * @param map
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Object buildMap(Map<?,?> map,Object obj){
		List<Field> fieldList = getAllFields(obj);
		
		for(Field field : fieldList){
			
			if(map.containsKey(field.getName())){
				Object value =valueManage(map.get(field.getName()),field);
				try {
					BeanUtils.setProperty(obj, field.getName(), value);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
				
		}
		
		return obj;
	}
	
	private static Object valueManage(Object value,Field field) {
		
		ConvertUtilsBean convertUtils = new ConvertUtilsBean();
		value =convertUtils.convert(value,field.getType());
		
		return value;
	}

	/**
	 * @author LE CHEN
	 * 
	 * @param objMap
	 * @param obj
	 *  
	 *  Fill in value in the Map to the class. including annotation way.
	 */
	public static Object fillClassForMap(Map<?,?> map,Object obj) {
		try {
			List<Field> fieldList = getAllFields(obj);

			for(Field field : fieldList){
				
				Column col = field.getAnnotation(Column.class);
				
				if(map.containsKey(field.getName()) || (ValidateUtils.isNotEmpty(col) && map.containsKey(col.name()))){
					Object objValue = map.get(field.getName());
					if(ValidateUtils.isEmpty(objValue) && ValidateUtils.isNotEmpty(col)){
						objValue =  map.get(col.name());
					}
					Object value =valueManage(objValue,field.getType());
					BeanUtils.setProperty(obj, field.getName(), value);
					
				}
	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return obj;
	}


	/**
	 * @author LE CHEN
	 * @param value
	 * @param typeClass 
	 * @return
	 * 
	 * The type conversion!
	 */
	private static Object valueManage(Object value,Class<?> typeClass) {

		ConvertUtilsBean convertUtils = new ConvertUtilsBean();
		value =convertUtils.convert(value,typeClass);
		ConvertUtils.register(new Converter(){
			@Override
			public Object convert(@SuppressWarnings("rawtypes") Class arg0, Object arg1) {
				// TODO Auto-generated method stub
				SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.PATTERN_DATETIME);
				if(ValidateUtils.isNotEmpty(arg1)){
					return DateUtils.toDateTime(sdf.format(arg1));
				}
				return null;
			}
			
		}, DateTime.class);
		
		return value;
	}

	/**
	 * @author LE CHEN
	 * @param object
	 * @return
	 * 
	 * For such a Class, including the parent Class
	 */
	public static List<Class<?>> getClasses(Object object){

		List<Class<?>> clazzs = Lists.newArrayList();

		//add Class to List
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			
			clazzs.add(superClass);

		}
		return clazzs;
	}


	/**
	 * @author LE CHEN
	 * 
	 * Get all the attributes of these, including the parent class.
	 */
	public static List<Field> getAllFields(Object object){
		
		Assert.notNull(object,"'object' must not be empty.");
		
		//The statement storage
		List<Field> fieldList = Lists.newArrayList();
		
		List<Class<?>> clazzs = getClasses(object);
		for(Class<?> clazz : clazzs){
			
			Field[] fields =clazz.getDeclaredFields();
			Field.setAccessible(fields, true);
			fieldList.addAll(Arrays.asList(fields));
			
		}

		return fieldList;
	}


	/**
	 * 将sql查出的 map 内数据 填充到 实体
	 * @author LE CHEN
	 * @param map 
	 * @param clazz 被赋值的实体Class
	 * @return
	 * 如果 实体内包括实体 请以 '实体内 类属性名称.类属性内的字段名称' 目前只支持单次递归
	 */
	public static  <T> T fillClassForMap(Map<String,Object> map,Class<T> clazz) {

		T t = null;

		try {
			t = clazz.newInstance();
			Map<String, Field> fieldMap = getAllFieldsMap(t);

			for(String key : map.keySet()){

				Field field = fieldMap.get(key);
				Object objValue = map.get(key); // sql字段值

				if(key.indexOf(".") >= 0){ //包含类 执行
					String[] clazzAndField = key.split("\\.");
					String clazzFieldName = clazzAndField[0]; //类中包含的 类变量名称
					field = fieldMap.get(clazzFieldName);
					if(ValidateUtils.isNotEmpty(field)){
						Object obj = field.get(t); //获取此类变量值 (直接获取跳过 get 方法)
						if(ValidateUtils.isEmpty(obj)){
							obj =  Class.forName(field.getType().getName()).newInstance();
						}
						Field clazzField = getAccessibleField(obj, clazzAndField[1]); //获取类变量内的 属性
						Object value =valueManage(objValue,clazzField.getType());
						BeanUtils.setProperty(obj, clazzField.getName(), value); //给T中 类变量赋值
						objValue = obj;
					}
				}


				if(field == null){
					continue;
					//					throw new  UnknownError("-----------["+key+"]  无对应 field 属性 --------------");
				}
				Object objvalue =valueManage(objValue,field.getType());
				BeanUtils.setProperty(t, field.getName(), objvalue); //给T中 类变量赋值

			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return t;
	}
	
	/**
	 * @author LE CHEN
	 * 
	 * Get all the attributes of these, including the parent class.
	 */
	public static Map<String,Field> getAllFieldsMap(Object object){

		Assert.notNull(object,"'object' must not be empty.");
		Map<String,Field> clazzMaps = Maps.newHashMap();
		//The statement storage

		List<Class<?>> clazzs = getClasses(object);
		for(Class<?> clazz : clazzs){

			Field[] fields =clazz.getDeclaredFields();
			Field.setAccessible(fields, true);
			for(Field field : fields){
				clazzMaps.put(field.getName(), field); //save field to map
			}
		}

		return clazzMaps;
	}


}

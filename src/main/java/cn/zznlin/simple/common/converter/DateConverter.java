package cn.zznlin.simple.common.converter;

import org.springframework.core.convert.converter.Converter;

// 将yyyy-MM-dd HH:mm:ss 转换为时间戳
public class DateConverter implements Converter<String,Long> {

	@Override
	public Long convert(String source) {
		
//		if(ValidateUtils.isEmpty(source)){
//			return null;
//		}
//
//		if(!RegexUtil.isDateTime(source)){
//			return StringUtils.getLong(source);
//		}
//
//		return DateUtils.strSLong(source);
		return 0l;
	}
	
}

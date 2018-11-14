package cn.zznlin.simple.common.tag;

import cn.zznlin.simple.common.utils.DateUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class Long2TimeTag extends TagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object value;
	
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		String outStr = "";
		
		try {
			
			if(value instanceof Integer)
				outStr = DateUtils.covertTime(((Integer)value)<0?0:((Integer)value).longValue());
			if(value instanceof Double)
				outStr = DateUtils.covertTime(((Double)value).longValue());
			if(value instanceof Long)
				outStr = DateUtils.covertTime((Long)value);
			
			
			out.print(outStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) throws JspException {
		this.value = value;
	}
	
	

	

	

	
	
	
	
	
	

}

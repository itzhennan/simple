/*****************************************************************************
 *
 *                      FORNOW PROPRIETARY INFORMATION
 *
 *          The information contained herein is proprietary to ForNow
 *           and shall not be reproduced or disclosed in whole or in part
 *                    or used for any design or manufacture
 *              without direct written authorization from ForNow.
 *
 *            Copyright (c) 2014 by ForNow.  All rights reserved.
 *
 *****************************************************************************/
package cn.zznlin.simple.common.tag;

import cn.zznlin.simple.common.utils.DateUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author Jiafa Lv
 * @email simon-jiafa@126.com
 * @date 2014-6-24 下午1:31:30
 */
@SuppressWarnings("serial")
public class Long2DateTag extends TagSupport {
	private Long value;
	private String pattern = DateUtils.PATTERN_DATETIME;
	
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
				out.print(DateUtils.long2DateString(value, pattern));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	/**
	 * @return the value
	 */
	public Long getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Long value) {
		this.value = value;
	}
	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}
	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}

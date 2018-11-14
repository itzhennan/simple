package cn.zznlin.simple.common.tag;

import cn.zznlin.simple.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;

/**
 * @author Simon Lv
 * @since 2012-8-9
 */
@SuppressWarnings("serial")
public class LocalDateFormat extends TagSupport {
	
	private String value;
	private String pattern = DateUtils.PATTERN_DATETIME_ENGLISH;
	private Locale locale = DateUtils.LOCAL_ENGLISH;

	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			if (StringUtils.isNotBlank(value)) {
				out.print(DateUtils.toLocalString(value,locale, pattern));
				return EVAL_PAGE;
			}
			out.print(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}

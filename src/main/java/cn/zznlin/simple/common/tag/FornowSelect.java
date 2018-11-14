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

import cn.zznlin.simple.common.utils.LoggerUtils;
import cn.zznlin.simple.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.sql.*;

/**
 * @author Jun Liu
 * @email 732839131@qq.com
 * @date 2014-6-13 下午4:59:46
 */
@SuppressWarnings("serial")
@Transactional
public class FornowSelect extends TagSupport {
	private static final String CLASS_NAME = FornowSelect.class.getName();
	private String tableName;// 表明
	private String label;// 下拉框要显示的名称
	private String value;// 下拉框的值
	private String where;// 条件
	private String name;// 下拉框的名称
	private String id;// 下拉款的ID
	private String selectValue;// 被选中的值

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		// String
		// hql="select "+label+","+value+" from "+name+" "+where+"";//定义sql语句
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ").append(label).append(",").append(value)
				.append(" from ").append(tableName);

		if (StringUtils.isNoEmpty(where)) {
			sb.append(" where ").append(where);
		}
		String sql = sb.toString();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet re = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/fornow?useUnicode=true&amp;characterEncoding=UTF-8&amp;createDatabaseIfNotExist=true";
			String username = "root";
			String password = "root";
			con = DriverManager.getConnection(url, username, password);
			LoggerUtils.debug(CLASS_NAME, "连接接成功=============>");
			statement = con.prepareStatement(sql);
			re = statement.executeQuery();
			out.print("<select id=\"" + id + "\" name=\"" + name + "\">");
			out.print("<option value=\"\">请选择</option>");
			while (re.next()) {
				String values = re.getString(value);
				String labels = re.getString(label);
				StringBuffer sbf = new StringBuffer();
				sbf.append("<option value=\"" + values + "\" title=\"" + labels
						+ "\"");
				if (StringUtils.isNoEmpty(selectValue)) {
					if (selectValue.equals(values)) {
						sbf.append(" selected='selected'");
					}
				}
				sbf.append(">" + labels + "</option>");
				out.print(sbf.toString());
			}
			out.print("</select>");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (re != null) {
					re.close();
					re = null;
				}
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return super.doEndTag();

	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSelectValue() {
		return selectValue;
	}

	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}

}

package cn.zznlin.simple.common.utils.bean;

/**
 * 
 * @author zhennan zhang
 * @email itzhennan@163.com
 * @date 2018年8月16日 下午4:43:59
 * 
 *       类说明 :
 * 
 */
public class DBInfo {
	// 表名
	private String tableName;
	// 字段名
	private String columnName;
	// 字段类型
	private String dataTypeName;
	// 字段大小
	private int columnSize;
	// 默认值
	private String columnDef;
	// 字段说明
	private String remarks;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataTypeName() {
		return dataTypeName;
	}

	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public String getColumnDef() {
		return columnDef;
	}

	public void setColumnDef(String columnDef) {
		this.columnDef = columnDef;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public DBInfo(String tableName, String columnName, String dataTypeName, int columnSize, String columnDef,
			String remarks) {
		super();
		this.tableName = tableName;
		this.columnName = columnName;
		this.dataTypeName = dataTypeName;
		this.columnSize = columnSize;
		this.columnDef = columnDef;
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "[表名=" + tableName + ", 字段名=" + columnName + ", 字段类型(大小)=" + dataTypeName
				+ "(" + columnSize + ") , 默认值=" + columnDef + ", 字段说明=" + remarks + "]";
	}

}

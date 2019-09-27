package org.stones.reality.metadata;

public class ColumnInfo {
	
	private String columnType; // data type
	private String columnTypeName; // type name
	private String columnName; // column name
	private String columnSize; // column size
	private String nulllable; // is_nullable
	private String columnDef; // column_def
	
	public String getNulllable() {
		return nulllable;
	}
	public void setNulllable(String nulllable) {
		this.nulllable = nulllable;
	}
	public String getColumnDef() {
		return columnDef;
	}
	public void setColumnDef(String columnDef) {
		this.columnDef = columnDef;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getColumnTypeName() {
		return columnTypeName;
	}
	public void setColumnTypeName(String columnTypeName) {
		this.columnTypeName = columnTypeName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnSize() {
		return columnSize;
	}
	public void setColumnSize(String columnSize) {
		this.columnSize = columnSize;
	}
}

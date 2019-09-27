package org.stones.reality.metadata;

import java.util.ArrayList;


public interface IMetaData {
	
	public void connection(String url, String username, String password);
	
	public DatabaseInfo getDatabaseMetaData();
	
	public ArrayList<String> getSchemaList();
	
	public ArrayList<TableInfo> getTableInfo(String schemaName1);
	
	public ArrayList<ColumnInfo> getColumnInfo(String schemaName, String tableName);

}

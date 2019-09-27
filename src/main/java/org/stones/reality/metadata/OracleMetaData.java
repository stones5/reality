package org.stones.reality.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class OracleMetaData implements IMetaData {

	private static final Logger LOGGER = LoggerFactory.getLogger(OracleMetaData.class);

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	ResultSet table = null;
	ResultSet column = null;
	ResultSetMetaData rsmd = null;
	DatabaseMetaData data = null;
	DatabaseMetaData data2 = null;

	public void connection(String url, String username, String password) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("연결");
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("", e);
		}
	}

	@Override
	public DatabaseInfo getDatabaseMetaData() {

		DatabaseInfo databaseInfo = new DatabaseInfo();

		try {
			data = conn.getMetaData();

			databaseInfo.setDriverName(data.getDriverName());
			databaseInfo.setProductName(data.getDatabaseProductName());
			databaseInfo.setProductVersion(data.getDatabaseProductVersion());
		} catch (SQLException e) {
			LOGGER.error("", e);
		}

		return databaseInfo;
	}

	@Override
	public ArrayList<String> getSchemaList() {

		ArrayList<String> schemaList = new ArrayList<String>();
		try {
			rs = data.getSchemas();

			while (this.rs.next()) {
				schemaList.add(rs.getString(1));
			}
			/*
			 * for (int i=0; i<schemaList.size(); i++) {
			 * System.out.println(schemaList.get(i)); }
			 */
		} catch (SQLException e) {
			LOGGER.error("", e);
		}
		return schemaList;
	}

	@Override
	public ArrayList<TableInfo> getTableInfo(String schemaName1) {

		ArrayList<TableInfo> tableList = new ArrayList<TableInfo>();

		try {
			data = conn.getMetaData();
			// System.out.println("schemaName : " + schemaName1);
			table = data.getTables(null, schemaName1, null, new String[] { "TABLE" });

			while (table.next()) {

				TableInfo tableInfo = new TableInfo();

				tableInfo.setTableName(table.getString("TABLE_NAME"));
				tableInfo.setTableCat(table.getString("TABLE_CAT"));
				tableInfo.setTableType(table.getString("TABLE_TYPE"));
				tableInfo.setTableSchem(table.getString("TABLE_SCHEM"));

				tableList.add(tableInfo);
			}
		} catch (SQLException e) {
			LOGGER.error("", e);
		}
		return tableList;
	}

	@Override
	public ArrayList<ColumnInfo> getColumnInfo(String schemaName, String tableName) {

		ArrayList<ColumnInfo> columnList = new ArrayList<ColumnInfo>();
		try {
			data2 = conn.getMetaData();

			column = data2.getColumns(null, schemaName, tableName, null);
			while (column.next()) {

				ColumnInfo columnInfo = new ColumnInfo();

				columnInfo.setColumnName(column.getString("COLUMN_NAME"));
				columnInfo.setColumnSize(column.getString("COLUMN_SIZE"));
				columnInfo.setColumnTypeName(column.getString("TYPE_NAME"));
				columnInfo.setColumnType(column.getString("DATA_TYPE"));
				columnInfo.setColumnDef(column.getString("COLUMN_DEF"));
				columnInfo.setNulllable(column.getString("IS_NULLABLE"));
				/*
				 * System.out.println(column.getString("COLUMN_NAME"));
				 * System.out.println(column.getString("COLUMN_SIZE"));
				 * System.out.println(column.getString("DATA_TYPE"));
				 */
				columnList.add(columnInfo);
			}
		} catch (SQLException e) {
			LOGGER.error("", e);
		}
		return columnList;
	}

	public static void main(String[] args) {

		/*String userName = "user01";
		String pw = "user01";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";

		String schemaName1 = "APPQOSSYS";
		String tableName1 = "WLM_CLASSIFIER_PLAN";

		OracleMetaData d = new OracleMetaData();
		d.connection(url, userName, pw);
		d.getDatabaseMetaData();
		d.getSchemaList();

		Gson gson = new Gson();
		ArrayList<TableInfo> t1 = new ArrayList<TableInfo>();
		t1 = d.getTableInfo(schemaName1);

		for (int i = 0; i < 2; i++) {
			TableInfo t = new TableInfo();
			t = t1.get(i);
			System.out.println(gson.toJson(t)); 
		} */
	}
}

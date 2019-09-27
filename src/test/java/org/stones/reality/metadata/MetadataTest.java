package org.stones.reality.metadata;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.Test;
import com.google.gson.Gson;

public class MetadataTest {

	@Test
	public void test() {

		String userName = "user01";
		String pw = "user01";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String schemaName1 = "APPQOSSYS";
		String tableName1 = "WLM_CLASSIFIER_PLAN";

		Gson gson = new Gson();

		IMetaData imeta = MetaDataFactory.getInstance().getExecution();

		imeta.connection(url, userName, pw);
		
		// getTableInfo
		ArrayList<TableInfo> tableList = new ArrayList<TableInfo>();
		ArrayList<TableInfo> tableList2 = new ArrayList<TableInfo>();
		
		tableList = imeta.getTableInfo(schemaName1);
		TableInfo t1 = new TableInfo();
		t1.setTableName("WLM_CLASSIFIER_PLAN");
		t1.setTableType("TABLE");
		t1.setTableSchem("APPQOSSYS");
		
		tableList2.add(t1);

		TableInfo t2 = new TableInfo();
		t2.setTableName("WLM_FEATURE_USAGE");
		t2.setTableType("TABLE");
		t2.setTableSchem("APPQOSSYS");
		
		tableList2.add(t2);
		
		for (int i = 0; i < 2; i++) {
			TableInfo x = new TableInfo();
			TableInfo y = new TableInfo();
			x = tableList.get(i);
			y = tableList2.get(i);
			assertEquals(gson.toJson(x), gson.toJson(y));
		}
		
		// getColumnInfo
		ArrayList<ColumnInfo> colList = new ArrayList<>();
		ArrayList<ColumnInfo> colList1 = new ArrayList<ColumnInfo>();

		colList = imeta.getColumnInfo(schemaName1, tableName1);

		ColumnInfo column1 = new ColumnInfo();
		column1.setColumnType("3");
		column1.setColumnTypeName("NUMBER");
		column1.setColumnName("OPER");
		column1.setColumnSize("0");
		column1.setNulllable("YES");

		colList1.add(column1);

		ColumnInfo column2 = new ColumnInfo();
		column2.setColumnType("3");
		column2.setColumnTypeName("NUMBER");
		column2.setColumnName("NCLSRS");
		column2.setColumnSize("0");
		column2.setNulllable("YES");

		colList1.add(column2);

		for (int i = 0; i < 2; i++) {
			ColumnInfo x = new ColumnInfo();
			ColumnInfo y = new ColumnInfo();
			x = colList.get(i);
			y = colList1.get(i);
			assertEquals(gson.toJson(x), gson.toJson(y));
		}
	}
}

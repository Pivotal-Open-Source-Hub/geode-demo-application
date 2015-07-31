package org.apache.geode.demo.fastfootshoes.clusterside.util;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.geode.demo.fastfootshoes.model.Alert;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * This class is used in the AsyncEvent listener to update an external DB
 * 
 * @author lshannon
 *
 */
public class AlertsDerbyDAO extends JdbcDaoSupport {

	private String insertSQL;
	private String createDB;
	private String createTable;

	/**
	 * Sets the data source and will create the schema if it does not exist
	 * @param dataSource
	 */
	public AlertsDerbyDAO(DataSource dataSource) {
		super.setDataSource(dataSource);
		//check if the table exists
		DatabaseMetaData dbmd;
		boolean createSchema = false;
		try {
			dbmd = dataSource.getConnection().getMetaData();
			ResultSet rs = dbmd.getTables(null, "APP", "MESSAGE_TABLE", null);

			System.out.println("Checking if schema exists...Hello??");
			if (!rs.next()) {
				createSchema = true;
				System.out.println("Need to create the schema...");
			} else {
				System.out.println("Results are valid, no need to work on schema... ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (createSchema) {
			createSchema();
		}
	}

	/**
	 * Inserts and Alert into the external DB
	 * @param alert
	 */
	public void insert(Alert alert) {
		insertSQL = "INSERT INTO message_table (message) VALUES (?)";
		System.out.println("Result of inserting to table: " + getJdbcTemplate().update(insertSQL, new Object[] { alert.getMessage() }));
	}
	
	/**
	 * Selects the most recent row from Derby
	 * 
	 */
	public Alert selectMostRecentRow() {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Alert> alert = getJdbcTemplate().query("SELECT * FROM MESSAGE_TABLE ORDER BY id ASC", new BeanPropertyRowMapper(Alert.class));
		return alert.get(0);
		
	}

	/**
	 * Creates the table called in the Constructor of the class if its detected the Table does not exist
	 */
	private void createSchema() {
		createTable = "CREATE TABLE message_table (id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), message VARCHAR(100))";
		System.out.println("Result of creating schema: " + getJdbcTemplate().update(createTable));
	}

	public String getInsertSQL() {
		return insertSQL;
	}

	public void setInsertSQL(String insertSQL) {
		this.insertSQL = insertSQL;
	}

	public String getCreateDB() {
		return createDB;
	}

	public void setCreateDB(String createDB) {
		this.createDB = createDB;
	}

	public String getCreateTable() {
		return createTable;
	}

	public void setCreateTable(String createTable) {
		this.createTable = createTable;
	}
	
	
	

}

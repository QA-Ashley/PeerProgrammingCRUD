package crudExercise;

import java.sql.*;

public class DB {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/gamedb?useSSL=false";
	static final String USER = "root";
	static final String PASS = "root";

	public Connection conn = null;
	public Statement stmt = null;

	public DB() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Database connected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void viewDB(String table) {
		String[] columns = new String[2];
		if (table.equals("customers")) {
			columns[0] = "customer_ID";
			columns[1] = "name";
		} else if (table.equals("orders")) {
			columns[0] = "order_ID";
			columns[1] = "product_ID";
		} else {
			System.out.println("Incorrect table name");
			return;
		}
		String sql = "SELECT * FROM " + table;
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				int id = rs.getInt(columns[0]);
				String name = rs.getString(columns[1]);
				System.out.println("ID: " + id + " Name: " + name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String create(String Table, int id, String Name, String Address, String email, String password) {
		String sql = "INSERT INTO " + Table + " VALUES (" + id + ",'" + Name + "','" + Address + "','" + email + "','"
				+ password + "')";
		try {
			stmt.executeUpdate(sql);
			System.out.println("Record Inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sql;
	}

	public void deleteCustomer(String table, int id) {
		String sql = "DELETE FROM " + table + " WHERE customer_ID=" + id;

		try {
			stmt.executeUpdate(sql);
			System.out.println("Deleted record " + id + " from " + table);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error deleting record, check that " + id + " exists in table " + table);
		}
	}

	public String update(String Table, int id, String Address) {
		String sql2 = "UPDATE " + Table + " SET address = '" + Address + "' WHERE customer_ID = " + id;
		try {
			stmt.executeUpdate(sql2);
			System.out.println("Record updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sql2;
	}
}

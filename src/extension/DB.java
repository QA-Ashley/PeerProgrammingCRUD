package extension;

import java.sql.*;

public class DB {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/games?useSSL=false";
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

	protected boolean createUser(String username, String password, String firstname, String lastname) {
		String sql = "INSERT INTO Users VALUES(0,'" + username + "','" + password + "','" + firstname + "','" + lastname
				+ "');";

		try {
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	protected boolean createProduct(String productName, double price, int stock) {
		String sql = "INSERT INTO Products VALUES(0,'" + productName + "','" + price + "','" + stock + "');";

		try {
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	protected boolean createOrder(int productId, int userId, int quantity) {
		String sql = "SELECT price, stock FROM Products WHERE productID=" + productId;
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		double price = 0;
		int stock = 0;
		try {
			while (rs.next()) {
				price = rs.getDouble("price");
				stock = rs.getInt("stock");
				System.out.println(price + " " + stock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		double totalPrice = price * quantity;
		if (stock < quantity) {
			System.out.println("Current stock: " + stock + " not enough for order");
			return false;
		}

		String sql2 = "INSERT INTO Orders VALUES(0," + productId + "," + userId + "," + quantity + "," + totalPrice
				+ ")";
		try {
			stmt.executeUpdate(sql2);
			stock = stock - quantity;
			String sql3 = "UPDATE Products SET stock=" + stock + " WHERE productId=" + productId;
			stmt.executeUpdate(sql3);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void viewUsers(String table) {
		String sql = "SELECT * FROM " + table;
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				int userID = rs.getInt("userID");
				String name = rs.getString("userName");
				System.out.println("ID: " + userID + " Name: " + name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void viewProducts(String table) {
		String sql = "SELECT * FROM " + table;
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				int productID = rs.getInt("productID");
				String name = rs.getString("productName");
				double price = rs.getDouble("price");
				int stock = rs.getInt("stock");
				System.out.println("ID: " + productID + " Name: " + name + " Price: " + price + " Stock: " + stock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void viewOrders(String table) {

		String sql = "SELECT * FROM " + table;
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				int orderID = rs.getInt("orderID");
				String userID = rs.getString("userID");
				double price = rs.getDouble("price");
				System.out.println("Order: " + orderID + " Username: " + userID + " Price: " + price);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void deleteCustomer(int userID) {
		String cleanStmt = "DELETE FROM Orders WHERE userID='" + userID + "';";

		try {
			stmt.executeUpdate(cleanStmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sql = "DELETE FROM Users WHERE userID='" + userID + "';";

		try {
			stmt.executeUpdate(sql);
			System.out.println("Deleted user under userID: " + userID);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error deleting user");
		}
	}

	protected void deleteProduct(int productID) {
		String cleanStmt = "DELETE FROM Orders WHERE productID='" + productID + "';";

		try {
			stmt.executeUpdate(cleanStmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sql = "DELETE FROM Products WHERE productID='" + productID + "';";

		try {
			stmt.executeUpdate(sql);
			System.out.println("Deleted product under productID: " + productID);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error deleting product");
		}
	}

	protected void deleteOrder(int orderID) {
		String sql = "DELETE FROM Orders WHERE orderID='" + orderID + "';";

		try {
			stmt.executeUpdate(sql);
			System.out.println("Deleted order under orderID: " + orderID);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error deleting order");
		}
	}

	public String updateUser(String Table, int id, String lastName) {
		String sql = "UPDATE " + Table + " SET lastName= '" + lastName + "' WHERE userID = " + id;
		try {
			stmt.executeUpdate(sql);
			System.out.println("Record updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sql;
	}

	public String updateProducts(String Table, int id, String name) {
		String sql = "UPDATE " + Table + " SET productName= '" + name + "' WHERE productID = " + id;
		try {
			stmt.executeUpdate(sql);
			System.out.println("Record updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sql;
	}

	public String updateOrder(String table, int quantity, int productid, int orderid) {
		String sql = "SELECT price FROM Products WHERE productID=" + productid;

		ResultSet rs = null;

		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		double price = 0;

		try {
			while (rs.next()) {
				price = rs.getDouble("price");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sql2 = "UPDATE " + table + " SET quantity= " + quantity + " WHERE orderID= " + orderid;

		try {
			stmt.executeUpdate(sql2);
			double totalPrice = price * quantity;
			String sql3 = "UPDATE Orders SET price =" + totalPrice + " WHERE orderId=" + orderid;
			stmt.executeUpdate(sql3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sql2;
	}
}

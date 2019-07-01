package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBcon {
	static Connection con;

	private DBcon() {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String pass = "123456";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("Á¢¼Ó");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() throws Exception {
		if (con == null) {
			new DBcon();
		}
		return con;
	}
}

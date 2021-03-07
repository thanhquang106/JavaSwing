package QlyHocVien.Connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {

	public static Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/nvduy";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");			
			conn = DriverManager.getConnection(url, "root", null);
			System.out.println("Connect successfully!!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connect failed!!");
		}
		return conn;
	}
}

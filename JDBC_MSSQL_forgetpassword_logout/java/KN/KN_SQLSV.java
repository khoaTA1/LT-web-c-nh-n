package KN;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class KN_SQLSV {
	private final String serverName = "KHOATRUONG";
	private final String dbName = "test";
	private final String portNumber = "1433";
	private final String instance = "";
	private final String userID = "MailSV_ADM";
	private final String password = "MSVADM1234";
	
	public Connection getConnection() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber +
		";databaseName=" + dbName + ";encrypt=true; trustServerCertificate=true";
		
		if (instance == null || instance.trim().isEmpty())
		url = "jdbc:sqlserver://"+serverName+":"+portNumber
		+";databaseName="+dbName + ";encrypt=true; trustServerCertificate=true";
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, userID, password);
	}
	
	public static void main(String[] args) {
		try {
			Connection conn = new KN_SQLSV().getConnection();			// crate statement
			Statement stmt = conn.createStatement();
			// insert ‘GiaoVien'
			stmt.executeUpdate("INSERT INTO ttck(id, usern, email) " + "VALUES (1, 'KhoaTA', 'khoatdmk2@gmail.com')");
			// get data from table ‘GiaoVien'
			ResultSet rs = stmt.executeQuery("SELECT * FROM ttck");
			// show data
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
			conn.close(); // close connection
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

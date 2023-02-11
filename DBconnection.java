package busManagementSystem;
import java.sql.*;

public class DBconnection{

	private static final String url = "jdbc:mysql://localhost:3306/busManagementSystem";
	
	private static final String username = "root";
	
	private static final String password = "695289";
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, username, password);
	}
}

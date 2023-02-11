package busManagementSystem;

import java.sql.*;

public class BusData {
	
	

	public void displaybusInfo() throws SQLException {

		Connection c = DBconnection.getConnection();
		Statement s = c.createStatement();
		String query = "SELECT * FROM bus";
		
		ResultSet r = s.executeQuery(query);
		while(r.next())
		{
			System.out.println("Bus number id "+r.getInt(1)+
					" Ac "+r.getBoolean(2)+
					" Capacity "+r.getInt(3));
		}
		c.close();
		
	}
	
	public int getCapacity(int busNumber) throws SQLException{
		Connection c = DBconnection.getConnection();
		String query = "Select capacity from bus where id = "+busNumber;
		Statement s = c.createStatement();
		ResultSet r = s.executeQuery(query);
		r.next();
		return r.getInt(1);
	}
	
	
}

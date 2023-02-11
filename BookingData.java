package busManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BookingData{
	int busnumber=1;
	String name="vignesh";
//	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-year");
	Date date;
	
	
	
	public BookingData(int busnumber,String name,String date) throws ParseException {
		this.busnumber=busnumber;
		this.name=name;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println(date);
		this.date = formatter.parse(date);
		
	}
	
		
	public boolean isAvailable() throws SQLException {
		Connection c = DBconnection.getConnection();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		String query = "select count(*) from booking where busNumber ="+this.busnumber +" and date =" +sqlDate +";";
		Statement s = c.createStatement();
		ResultSet r = s.executeQuery(query);
		r.next();
		int currentCapacity = r.getInt(1);
        BusData bus = new BusData();
        int totalCapacity = bus.getCapacity(busnumber); 
        return totalCapacity > currentCapacity;
	}
	public void bootTicket(BookingData booking) throws SQLException {
		
		if(isAvailable())
		{
			String query = "Insert into booking(busNumber,name,date) values(?,?,?)";
			java.sql.Date sqlDate = new java.sql.Date(booking.date.getTime());
			Connection c = DBconnection.getConnection();
			PreparedStatement pst = c.prepareStatement(query);
			pst.setInt(1, booking.busnumber);
			pst.setString(2, booking.name);
			pst.setDate(3, sqlDate);
			pst.executeUpdate();
			System.out.println("Your booking is confirmed");
		}else {
			System.out.println("Not able to book");
		}
		
	}
	public static void bookingInfo() throws SQLException {
		Connection c = DBconnection.getConnection();
		Statement s = c.createStatement();
		String query = "SELECT * FROM booking";
		
		ResultSet r = s.executeQuery(query);
		while(r.next())
		{
			System.out.println("Bus number is "+r.getInt(2)+
					" Ac "+r.getString(3)+
					" Capacity "+r.getDate(4));
		}
		c.close();
		
	}
}

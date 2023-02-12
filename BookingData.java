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
	Date date;
	private String bookingNumber;
	
	
	
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
			String query = "Insert into booking(busNumber,name,date,bookingNumber) values(?,?,?,?)";
			java.sql.Date sqlDate = new java.sql.Date(booking.date.getTime());
			Connection c = DBconnection.getConnection();
			PreparedStatement pst = c.prepareStatement(query);
			pst.setInt(1, booking.busnumber);
			pst.setString(2, booking.name);
			pst.setDate(3, sqlDate);
			pst.setString(4, bookingNumber(booking));
			pst.executeUpdate();
			System.out.println("Your booking is confirmed and Your booking Number is "+ this.bookingNumber);
			
		}else {
			System.out.println("Not able to book");
		}
		
	}
	private String bookingNumber(BookingData booking) {
		
		int randomNumber = (int)Math.random()*9999;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String dateString = formatter.format(date);
		
		String bookingNumber = randomNumber+"-" + dateString +"-"+ this.busnumber;
		this.bookingNumber=bookingNumber;
		return this.bookingNumber;
		
	}


	public void bookingInfo() throws SQLException {
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
	public static void bookingInfo(String number) throws Exception{
		Connection c = DBconnection.getConnection();
		Statement s = c.createStatement();
		String query = "SELECT * FROM booking WHERE bookingNumber = '"+number+"';";
		ResultSet r = s.executeQuery(query);
		System.out.println(query);
		r.next();
		
			System.out.println("Bus number is "+r.getInt(2)+
					" Ac "+r.getString(3)+
					" Capacity "+r.getDate(4));
		
		c.close();
	}
	public static void cancelBooking(String number) throws Exception
	{
		
		
		Connection c = DBconnection.getConnection();
		Statement s =c.createStatement();
		String available = "SELECT count(*) FROM booking WHERE bookingNumber = '"+number+"';";
		ResultSet r = s.executeQuery(available);
		r.next();
		
		if(r.getInt(1) != 1)
		{
			System.out.println("No booking available with the booking number"+number);
			return;
		}
		
		String delete = "DELETE FROM booking WHERE bookingNumber = '"+number+"';";
		bookingInfo(number);
		s.executeUpdate(delete);
	}
}

package busManagementSystem;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class DemoUse {

	public static void main(String[] args) throws SQLException {

		BusData busInfo = new BusData();
		
		busInfo.displaybusInfo();
		
		BookingData.bookingInfo();
		
		addBooking();

	}

	public static void addBooking() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter 1 to Book a ticket or 2 to exit");
		int userChoice = s.nextInt();
		while(userChoice == 1)
		{
			try {
				BookingData booking = new BookingData(2,"Vignesh","22-05-2023");
				booking.bootTicket(booking);
			} catch (ParseException | SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Enter 1 to Book a ticket or 2 to exit");
			userChoice = s.nextInt();
		}
		return ;
		
	}

}

public class FlightScheduler {

	public static void main(String[] args) {

		Schedule schedule1 = new Schedule(101, "SIN - Changi", "BKK - Suvarnabhumi", "Airbus A330-300", "22/01/2021",
				"14:45", "CONFIRMED");
		Schedule schedule2 = new Schedule(102, "BKK - Suvarnabhumi", "SIN - Changi", "Boeing 777-300", "24/01/2021",
				"12:30", "CONFIRMED");
		Schedule schedule3 = new Schedule(103, "KUL - KLIA", "BKK - Suvarnabhumi", "Boeing 777-300", "18/01/2021",
				"12:30", "CONFIRMED");

		ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();
		scheduleList.add(schedule1);
		scheduleList.add(schedule2);
		scheduleList.add(schedule3);

		Staff staff = new Staff("Stefan Chan", "stefan_chan", "654321", 5, "admin");

		while (true) {

			FlightScheduler.loginMenu();
			String uName = Helper.readString("Enter username > ");
			String uPassword = Helper.readString("Enter password > ");

			boolean isStaff = FlightScheduler.doStaffLogin(staff, uName, uPassword);

			if (isStaff == false) {
				System.out.println("Either your username or password was incorrect. Please try again!");
			}


			while (isStaff) {
				FlightScheduler.staffMenu();
				int staffChoice = Helper.readInt("Enter choice > ");

				if (staffChoice == 1) { 

					String allFlightSchedules = FlightScheduler.scheduleListToString(scheduleList);
					System.out.println(allFlightSchedules);
					System.out
							.println("Total number of flights: " + FlightScheduler.getNumFlightSchedules(scheduleList));

				} else if (staffChoice == 2) {

					int scheduleId = Helper.readInt("Enter flight schedule ID > ");
					String departAP = Helper.readString("Enter departure airport > ");
					String arriveAP = Helper.readString("Enter arrival airport > ");
					String aircraft = Helper.readString("Enter aircraft > ");
					String flightDate = Helper.readString("Enter flight date (dd/mm/yyyy) > ");
					String flightTime = Helper.readString("Enter flight time (hh:mm) > ");

					Schedule newSchedule = new Schedule(scheduleId, departAP, arriveAP, aircraft, flightDate,
							flightTime);

					boolean result = FlightScheduler.addFlightSchedule(scheduleList, newSchedule);

					if (result == true) {
						System.out.println("Flight schedule added!");
					} else {
						System.out.println("Flight schedule NOT added, you must include all details!");
					}

				} else if (staffChoice == 3) { 

					int editFlightID = Helper.readInt("Enter Flight Schedule ID > ");

					String flightScheduledetails = FlightScheduler.getFlightScheduleById(scheduleList, editFlightID);

					if (!flightScheduledetails.isEmpty()) {
						System.out.println(flightScheduledetails);
						String statusUpdate = Helper.readString("Enter new schedule status > ");
						boolean isEdited = FlightScheduler.editFlightScheduleStatus(scheduleList, editFlightID,
								statusUpdate);

						if (isEdited) {
							flightScheduledetails = FlightScheduler.getFlightScheduleById(scheduleList, editFlightID);
							System.out.println(String.format("Flight schedule status %d updated to %s", editFlightID,
									statusUpdate.toUpperCase()));
							System.out.println(flightScheduledetails);
						} else {
							System.out.println("The status you entered is invalid.");
						}
					} else {
						System.out.println("That Flight Schedule ID does not exist!");
					}

				} else if (staffChoice == 4) {

					int deleteFlightID = Helper.readInt("Enter Flight Schedule ID > ");

					String flightScheduledetails = FlightScheduler.getFlightScheduleById(scheduleList, deleteFlightID);

					if (!flightScheduledetails.isEmpty()) {
						System.out.println(flightScheduledetails);
						char toDelete = Helper.readChar("Do you wish to delete this flight?(y/n) > ");

						if (toDelete == 'y') {
							boolean deleted = FlightScheduler.removeFlightSchedule(scheduleList, deleteFlightID);

							if (deleted == true) {
								System.out.println(String.format("Flight schedule id %d was deleted successfully.",
										deleteFlightID));
							} else {
								System.out.println("Something went wrong, flight schedule was not deleted.");
							}
						}

					} else {
						System.out.println("That Flight Schedule ID does not exist!");
					}

				} else if (staffChoice == 5) {
					String airportSearch = Helper.readString("Enter airport to search > ");
					ArrayList<Schedule> resultList = getFlightSchedulesByAirport(scheduleList, airportSearch);

					if (!resultList.isEmpty()) {
						System.out.println(FlightScheduler.scheduleListToString(resultList));
					} else {
						System.out.println("No flight schedules with that airport.");
					}

				} else if (staffChoice == 6) {

					char resetPw = Helper.readChar("Are you sure you want to reset your password? (y/n) > ");

					if (resetPw == 'y') {
						staff = FlightScheduler.resetPassword(staff);
						System.out.println("Password reset successful.");
						System.out.println("Your new password is: " + staff.getPassword());
					}

				} else if (staffChoice == 7) {
					isStaff = false;
					System.out.println("Goodbye!");

				} else {
					System.out.println("Invalid choice");
				}

			}

		}

	}

	public static void loginMenu() {
		Helper.line(30, "-");
		System.out.println("FLIGHT SCHEDULER - LOGIN");
		Helper.line(30, "-");
	}

	public static void staffMenu() {

		Helper.line(30, "-");
		System.out.println("FLIGHT SCHEDULER - STAFF");
		Helper.line(30, "-");

		System.out.println("1. View all flight schedules");
		System.out.println("2. Add a new flight schedule");
		System.out.println("3. Update a flight schedule status");
		System.out.println("4. Remove a flight schedule");
		System.out.println("5. Search for flight schedules by airport");
		System.out.println("6. Reset password");
		System.out.println("7. Log out");

	}

	public static int getNumFlightSchedules(ArrayList<Schedule> scheduleList) {

		return scheduleList.size();

	}

	public static String getFlightScheduleById(ArrayList<Schedule> scheduleList, int flightScheduleId) {

		String output = "";

		for (int i = 0; i < scheduleList.size(); i++) {
			Schedule s = scheduleList.get(i);

			if (s.getId() == flightScheduleId) {
				output += String.format("%-3s %-20s %-20s %-15s %-15s %-20s %-10s\n", "ID", "DEPARTURE", "ARRIVAL",
						"FLIGHT DATE", "FLIGHT TIME", "AIRCRAFT", "STATUS");
				output += String.format("%-3d %-20s %-20s %-15s %-15s %-20s %-10s\n", s.getId(),
						s.getDepartureAirport(), s.getArrivalAirport(), s.getFlightDate(), s.getFlightTime(),
						s.getAircraft(), s.getStatus());
				break;
			}
		}

		return output;
	}

/* END: DO NOT change this part of the code */
	
	
	
	
/*
 *   Refer to the assignment document for the specification of the methods below.
 */

	public static boolean doStaffLogin(Staff staff, String uName, String uPassword) 
	{
		
		if(staff.getUsername().equals(uName) && staff.getPassword().equals(uPassword)) 
		{
			return true;
		}
		else 
		{
			return false;
		}
				

	}

	public static ArrayList<Schedule> getFlightSchedulesByAirport(ArrayList<Schedule> scheduleList, String airport) 
	{
		ArrayList<Schedule> airportNamehasWord = new ArrayList<Schedule>(); // new array list to store the needed output // cos it returns an arraylist
		

        for (int x = 0; x < scheduleList.size(); x++) {
            if (scheduleList.get(x).getDepartureAirport().toLowerCase().contains(airport.toLowerCase()) || // change to lower case to make it easier
            		scheduleList.get(x).getArrivalAirport().toLowerCase().contains(airport.toLowerCase())) 
            {
                airportNamehasWord.add
                (new Schedule(scheduleList.get(x).getId(),
                		scheduleList.get(x).getDepartureAirport(),
                		scheduleList.get(x).getArrivalAirport(),
                		scheduleList.get(x).getAircraft(),
                		scheduleList.get(x).getFlightDate(), 
                		scheduleList.get(x).getFlightTime(),
                		scheduleList.get(x).getStatus()));
            }
        }

        return airportNamehasWord;
		
		
		
	}

	public static String scheduleListToString(ArrayList<Schedule> scheduleList) 
	{
		String HEADER = String.format("%-30s%-30s%-30s%-30s%-30s%-30s%-30s","ID","DEPARTURE","ARRIVAL","FLIGHT DATE","FLIGHT TIME","AIRCRAFT","STATUS");
		System.out.println(HEADER); // heading for the output
		String OUTPUTOBJECTS = ""; // output for the objects
		
		if (!scheduleList.isEmpty()) // if the array List is not empty
		{
			for(int i = 0;i < scheduleList.size();i++) // funneling
			{
				
				OUTPUTOBJECTS += 
						String.format("%-30d%-30s%-30s%-30s%-30s%-30s%-30s\n",
						scheduleList.get(i).getId(),
						scheduleList.get(i).getDepartureAirport(),
						scheduleList.get(i).getArrivalAirport(),
						scheduleList.get(i).getFlightDate(),
						scheduleList.get(i).getFlightTime(),
						scheduleList.get(i).getAircraft(),
						scheduleList.get(i).getStatus());
			}
			
		}
		return OUTPUTOBJECTS;// to return the array list objects
		
	
	}

	public static boolean addFlightSchedule(ArrayList<Schedule> scheduleList, Schedule schedule) 
	{
		if(schedule.getId() == 0 ||
				schedule.getDepartureAirport().isEmpty() ||
				schedule.getDepartureAirport().isEmpty() ||
				schedule.getArrivalAirport().isEmpty() ||
				schedule.getAircraft().isEmpty() ||
				schedule.getFlightDate().isEmpty() ||
				schedule.getFlightTime().isEmpty())  // if every input is empty
		{
			return false; // false
		}
		else
		{
			scheduleList.add(schedule); // add  the schedule
			return true; 
		}
		
		
	}

	public static boolean editFlightScheduleStatus(ArrayList<Schedule> scheduleList, int scheduleId, String newStatus) 
	{
         if (newStatus.toUpperCase().equals("DELAYED")  // if the input matches with the 
        		 || newStatus.toUpperCase().equals("PENDING") 
        		 ||  newStatus.toUpperCase().equals("CONFIRMED"))
         {
        	 for (int i = 0; i < scheduleList.size(); i++) // funnel
        	 {
 				if (scheduleList.get(i).getId() == scheduleId) // if the input id matches the existing id
 				{
 					scheduleList.get(i).setStatus(newStatus.toUpperCase()); // set new status with the function
 					
 					return true;
 				}
 			}
 		}
 		return false;
         
			
			
		
	}

	public static boolean removeFlightSchedule(ArrayList<Schedule> scheduleList, int scheduleId) 
	{
		for (int y  = 0; y < scheduleList.size(); y++) 
		{ // funneling through array
			
			if (scheduleList.get(y).getId() == scheduleId) 
			{ // if the input id exists with the id in the array list.
				
				scheduleList.remove(y); 
				
				return true;
			}
		}
		
		return false; // outside for loop

	}

	public static Staff resetPassword(Staff staff) {
		
		// Complete code here
        Random RandomPass = new Random();
		
		char[] UpperLowerCaseAndNumbers = {'A', 'a', 'B', 'b', 'C', 'c', // char array filled with alphabets
				'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 
				'h', 'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 
				'M', 'm', 'N', 'n', 'O', 'o', 'P','p', 'Q','q','R', 'r', 'S',
				's', 'T', 't', 'U', 'u', 'V', 'v', 'W',
				'w', 'X', 'x', 'Y', 'y', 'Z', 'z',
				'0','1','2','3','4','5','6','7','8','9'};
		
		String NewPassword = "";
		
		int randomNumber8 = 0; // to store the new pass // the 8 random char + number
		
		int TotalBigAndSmallAlphabetsAndNumbers = 62; // no of alphabets lower and upper
		
		for (int x = 0; x < 8; x++) 
		{ // 0 , 8 is the limit
			 
			randomNumber8 = RandomPass.nextInt(TotalBigAndSmallAlphabetsAndNumbers);// range of elements to choose from
			                                                             //  only be 8 because of for loop 
			NewPassword += UpperLowerCaseAndNumbers[randomNumber8];     // appending the new password to the String variable
			
			
		}
		staff.setPassword(NewPassword); // update the password using function from class
		return staff; // return the password 
		 
		

	}

}

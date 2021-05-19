package GA2;

public class Schedule {
	private int id;
	private String departureAirport;
	private String arrivalAirport;
	private String aircraft;
	private String flightDate;
	private String flightTime;
	private String status;
	
	public Schedule(int id , String departureAirport, String arrivalAirport, String aircraft, String flightDate,String flightTime, String status) //
	{
		this.id = id;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.aircraft = aircraft;
		this.flightDate = flightDate;
		this.flightTime = flightTime;
		this.status = status;
	}
	public Schedule(int id, String departureAirport, String arrivalAirport, String aircraft, String flightDate,String flightTime) // pending
	{
		this.id = id;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.aircraft = aircraft;
		this.flightDate = flightDate;
		this.flightTime = flightTime;
		this.status = "PENDING"; // so it auto sets to pending for added new flights
		
	}
	public int getId() 
	{
		return id;
	}
	public String getDepartureAirport() 
	{
		return departureAirport;
	}
	public String getArrivalAirport() 
	{
		return arrivalAirport;
	}
	public String getAircraft() 
	{
		return aircraft;
	}
	public String getFlightDate() 
	{
		return flightDate;
	}
	public String getFlightTime() 
	{
		return flightTime;
	}
	public String getStatus() 
	{
		return status;
	}
	public void setStatus(String status) 
	{
		this.status = status; // setting new status based on argument(input)
	}

}

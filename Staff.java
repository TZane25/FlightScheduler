public class Staff {
	private String name;
	private String username;
	private String password;
	private int staffId;
	private String role;
	
	public Staff(String Name , String username , String Password , int staffId , String role) 
	{
		this.name = Name;
		this.username = username;
		this.password = Password;
		this.staffId = staffId;
		this.role = role;
	}
	public String getName() 
	{
		return name;
	}
	public String getUsername() 
	{
		return username;
	}
	public String getPassword() 
	{
		return password;
	}
	public int getStaffId() 
	{
		return staffId;
	}
	public String getRole() 
	{
		return role;
	}
	public void setPassword(String password) 
	{
		this.password = password; // setter method 
		// takes in password input and changes original with new pass word
	}
}

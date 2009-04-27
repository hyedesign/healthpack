package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserInfoSQL 
{
	private DBAccess dba = new DBAccess();
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String description;
	private String password;
	private String password2;
	
	public UserInfoSQL(String firstName, String lastName, String email,
			String phone, String description, String password, String password2) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.description = description;
		this.password = password;
		this.password2 = password2;
	}
	
	public void updateUserInfo(int id)
	{
		System.out.println(this.firstName+" "+this.lastName+
				" "+this.description+" "+this.email+" "+this.password+" "+this.password2
				+" "+this.phone);
		
		dba.connect(); // connect to the database
		try
		{
			Statement statement = dba.connection.createStatement();
			if(description == null)
			{
				statement.executeUpdate("UPDATE cmsc345.users SET userpassword='"
						+this.password+"', useremail='"+this.email+
						"', userphone='"+this.phone+"', userfirstname='"
						+this.firstName+"', userlastname='"+this.lastName+"' WHERE userid='"
						+id+"'");
			}
			else
			{
				statement.executeUpdate("UPDATE cmsc345.users SET userpassword='"
						+this.password+"', useremail='"+this.email+
						"', userphone='"+this.phone+"', userdescription='" +this.description+
						"', userfirstname='"+this.firstName+"', userlastname='"+
						this.lastName+"' WHERE userid='"
						+id+"'");
			}
			statement.close();
			dba.disconnect();
		}
		catch (SQLException e)
		{
			System.err.println (e.toString());
			System.out.println("Failed to update.");
			dba.disconnect();
		}
	}

	
}

/**********************************************************
* File: core.GetUserInfoBean.java
* Author: Han Dong
* Date Created: 4/28/2009
*
* Description: Fetches user information for database and 
* 			   forwards info to editUserInfo.jsp to fill
*			   the fields.
*
**********************************************************/
package core;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class GetUserInfoBean implements ActionBean
{
	//instance variables corresponding to fields in editUserInfo.jsp
	private ActionBeanContext context;
	private int userID;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String description;
	
	
	//getters and setters
	public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
    public int getUserID() { return userID; }
	public void setUserID(int userID) { this.userID = userID; }
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.phone = phone; }
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	/**
	 * submit - access the database and gets the entry with the 
	 * 			corresponding userID. Assigns values to instance
	 * 			variables of GetUserInfoBean, then forwards 
	 * 			information to editUserInfo.jsp
	 * @return
	 */
	@DefaultHandler
    public Resolution submit() 
	{
		/* 
		  creates object of type UserSQL and looks up the user
		  with the same userID 
		 */
		UserSQL temp = new UserSQL();
		temp.lookupUser(this.userID);
		
		//gets all necessary fields to be displayed
		this.firstName = temp.getUserFirstName();
		this.lastName = temp.getUserLastName();
		this.email = temp.getUserEmail();
		this.phone = temp.getUserPhone();
		this.description = temp.getUserDescription();
		
		//forwards to next page
        return new ForwardResolution("editUserInfo.jsp");
    }
}

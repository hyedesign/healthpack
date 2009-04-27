package core;


import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class GetUserInfoBean implements ActionBean
{
	private ActionBeanContext context;
	private int userID;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String description;
	
	
	public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
    
    public int getUserID() { return userID; }
	public void setUserID(int userID) { this.userID = userID; }
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@DefaultHandler
    public Resolution edit() 
	{
		UserSQL temp = new UserSQL();
		temp.lookupUser(this.userID);
		
		this.firstName = temp.getUserFirstName();
		this.lastName = temp.getUserLastName();
		this.email = temp.getUserEmail();
		this.phone = temp.getUserPhone();
		this.description = temp.getUserDescription();
		
        return new ForwardResolution("editUserInfo.jsp");
    }
}

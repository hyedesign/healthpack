/**********************************************************
* File: core.DeleteTestBean.java
* Author: Han Dong
* Date Created: 4/30/2009
*
* Description: DeleteTestBean.java shall let the user add
*              a test he/she has selected.
*
**********************************************************/

package core;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class DeleteTestBean implements ActionBean
{
	//instance variables
	private ActionBeanContext context;
	private int testID;
	
	//getters and setters
	public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
    
    /**
     * gets the test id and makes a call to the deleteTest
     * method to delete that entry from the database
     * @return
     */
    @DefaultHandler
    public Resolution submit()
	{
    	//deletes
    	EditTestSQL.deleteTest(this.testID);
    	
    	//goes back to home page
		return new ForwardResolution("patientHome.jsp");
	}

}

package core;

/**********************************************************
* File: core.AllergiesBean.java
* Author: Taylor Evans
* Date Created: 4/19/2009
*
* Description: The AllergiesBean class works with 
*              editAllergies.jsp to verify form inputs
*
* Edited  : 4/20/2009 by Taylor Evans
* Changes : added header comment
*
**********************************************************/

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.validation.Validate;


public class AllergiesBean implements ActionBean {
    private ActionBeanContext context;
    @Validate(required=true) private String allergyName;
    @Validate(required=false) private String description;

    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }

    public String getAllergyName() { return allergyName; }
    public void setAllergyName(String allergyName) { this.allergyName = allergyName; }
    
	public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    @DefaultHandler
    public Resolution update() {
        return new ForwardResolution("patientHome.jsp");
    }
}

package core;

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

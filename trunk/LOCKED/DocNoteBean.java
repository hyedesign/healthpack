/**********************************************************
* File: core.DocNoteBean.java
* Author: Jon Conti-Vock
* Date Created: 4/24/2009
*
* Description: The DocNoteBean class handles editing and updating
*              the Doctor's Note in the HealthPack database.
*
**********************************************************/

package core;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

public class DocNoteBean implements ActionBean {
    private ActionBeanContext context;
    @Validate(required=true, maxlength=255) private String docnote;

    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }

    public String getNote() { return docnote; }
    public void setNote(String docnote) { this.docnote = docnote; }

    @DefaultHandler
    public Resolution submit() {
        return new ForwardResolution("docPatientHome.jsp");
    }
    
    @ValidationMethod(on="submit")
    public void noSpecialCharacters(ValidationErrors errors) {
	    if (hasSpecialCharacters(this.docnote))
	        errors.add("docnote", new SimpleError("These characters are not allowed: <> () [] \\ / | = + * @ $ # ^ : ; "));
    }
}
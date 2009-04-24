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

/**********************************************************
* File: core.DocNoteBean.java
* Author: Jon Conti-Vock
* Date Created: 4/19/2009
*
* Description: The DocNoteBean class handles editing and updating
*              the Doctor's Note in the HealthPack database.
*
**********************************************************/

public class DocNoteBean implements ActionBean {
    private ActionBeanContext context;
    @Validate(required=true, maxlength=255) private String docnote;

    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }

    public String getNote() { return docnote; }
    public void setNote(String docnote) { this.docnote = docnote; }

    @DefaultHandler
    public Resolution setnote() {
        return new ForwardResolution("docPatientHome.jsp");
    }
}
/**********************************************************
* File: core.HPActionBeanContext.java
* Author: Alex Bassett
* Date Created: 4/13/2009
*
* Description: The extended context allows stripes
*				beans to easily interact with the session
*
**********************************************************/
package core;

import net.sourceforge.stripes.action.ActionBeanContext;

public class HPActionBeanContext extends ActionBeanContext{

    public void setUserId(int userid) {
        getRequest().getSession().setAttribute("userid", userid);
    }

    public Integer getUserId() {
        return (Integer) getRequest().getSession().getAttribute("userid");
    }
    
    public void setUserIsDoctor(boolean userisdoctor) {
        getRequest().getSession().setAttribute("userisdoctor", userisdoctor);
    }

    public Boolean getUserIsDoctor() {
        return (Boolean) getRequest().getSession().getAttribute("userisdoctor");
    }

    public void setPatientId(int patientid) {
        getRequest().getSession().setAttribute("patientid", patientid);
    }

    public Integer getPatientId() {
        return (Integer) getRequest().getSession().getAttribute("patientid");
    }
}

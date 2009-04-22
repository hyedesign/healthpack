/**********************************************************
* File: core.Patient.java
* Author: Vahan Kristosturyan
* Date Created: 4/19/2009
*
* Description: The Patient class handles loading and updating
*              the patient table in the HealthPack database.
*
**********************************************************/


package core;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

public class PatientListBean implements ActionBean {
   
	/*
	 * PRIVATE VARIABLES
	 */
	private ActionBeanContext context;
		
	private String patientId = "0";
	private int PATID;
	
   

	 
/********************************************************************************************************/
   
	public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }
   
    @DefaultHandler
    public Resolution view() {
    	PATID = Integer.parseInt(patientId);
    	getContext().getRequest().setAttribute("patientId", PATID);
    	return new ForwardResolution("patientHome.jsp");
    }

    public Resolution edit() {
    	PATID = Integer.parseInt(patientId);
    	getContext().getRequest().setAttribute("patientId", PATID);
        return new ForwardResolution("editPatient.jsp");
    }

    public Resolution delete() {
    	PATID = Integer.parseInt(patientId);
    	Patient.DeletePatien(PATID);
        return new ForwardResolution("patientList.jsp");
    }

    public Resolution add() {
    	getContext().getRequest().setAttribute("patientId", 0);
        return new ForwardResolution("editPatient.jsp");
    }
  
 
}
/**********************************************************
* File: core.PatientListBean.java
* Author: Alex Bassett
* Date Created: 4/28/2009
*
* Description: This ActionBean controls loading patient
* 				names and IDs so that the user may select
* 				them from patientList
*
**********************************************************/
package core;

import java.util.ArrayList;

public class PatientListBean {
	
	private int userId;
	private ArrayList<Integer> patientIds = new ArrayList<Integer>();
	private ArrayList<String> patientNames = new ArrayList<String>();
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public ArrayList<Integer> getPatientIds() {
		return patientIds;
	}
	public ArrayList<String> getPatientNames() {
		patientIds = PatientSQL.lookupPatientsByUserID(userId);
		for (Integer i : patientIds)
			patientNames.add(PatientSQL.lookupPatientNamebyID(i));
		return patientNames;
	}	
}

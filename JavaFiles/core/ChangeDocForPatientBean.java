/**********************************************************
 * File: core.ChangeDocForPatientBean.java
 * Author: Vahan Kristosturyan
 * Date Created: 4/26/2009
 *
 * Description: This class handles the call from Patient Home.
 * 				It is needed to populate to lists of all doctor 
 * 				ids and their names.
 *
 **********************************************************/
package core;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class ChangeDocForPatientBean implements ActionBean {
	public class Doctors {
		private String id;
		private String name;

		protected Doctors(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	/*
	 * CONSTRUCTOR
	 */
	public ChangeDocForPatientBean() {
		allDoctorIds = UserSQL.getDoctorList();

		// populates all of their names
		System.out.println("ChangeDocForPatientBean: IM HERE !!!!!!!");
		for (int i = 0; i < allDoctorIds.size(); i++) {
			Doctors d = new Doctors(allDoctorIds.get(i), UserSQL
					.getUserFirstAndLastName(allDoctorIds.get(i)));
			allDoctors.add(d);
			System.out.println("doctor name: " + allDoctors.get(i).getName()
					+ " doctor id: " + allDoctors.get(i).getId());
		}
	}

	public HPActionBeanContext context;
	private ArrayList<String> allDoctorIds;
	private int docId = 0;

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	private List<Doctors> allDoctors = new ArrayList<Doctors>();

	// GETTERS AND SETTERS

	public ArrayList<String> getAllDoctorIds() {
		return allDoctorIds;
	}

	public void setAllDoctorIds(ArrayList<String> allDoctorIds) {
		this.allDoctorIds = allDoctorIds;
	}

	public List<Doctors> getAllDoctors() {
		return allDoctors;
	}

	public void setAllDoctors(ArrayList<Doctors> allDoctors) {
		this.allDoctors = allDoctors;
	}

	public HPActionBeanContext getContext() {
		return this.context;
	}
	public void setContext(ActionBeanContext context) {
		this.context = (HPActionBeanContext) context;
	}

	@DefaultHandler
	public Resolution cancel() {
		return new ForwardResolution("patientList.jsp");
	}

	public Resolution update() {
		System.out.println("attempting to change patient: " + context.getPatientId()
				+ " to doctor: " + docId);
		PatientSQL.updatePatientDoctor(context.getPatientId(), docId);
		
		return new ForwardResolution("patientList.jsp");
	}

}

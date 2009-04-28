

package core;

import net.sourceforge.stripes.action.ActionBeanContext;

public class HPActionBeanContext extends ActionBeanContext{

	public void setUserId(int userid) {
			getRequest().getSession().setAttribute("userid", userid);
	}

	public int getUserId() {
		return (Integer)getRequest().getSession().getAttribute("userid");
	}

		
}

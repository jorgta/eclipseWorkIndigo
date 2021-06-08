/*
 * Created on Apr 15, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.bituos;

/**
 * @author David Sanchez Aroche (dsa1972@yahoo.com)
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import javax.servlet.http.*;

public class Notification {

	public Notification( String msg , HttpServletRequest req)
	  {
		HttpSession ses = req.getSession();
		String message = new String( msg );

		ses.setAttribute("msgNotification", message );
	  }
}

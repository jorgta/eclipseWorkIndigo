/*
 * Created on Apr 15, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.tecunsa;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import javax.servlet.http.*;

public class Error {

	public Error( String msg , HttpServletRequest req)
	  {
		HttpSession ses = req.getSession();
		String error = new String( msg );

		ses.setAttribute("msgError", error );
	  }
}

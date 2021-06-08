package contactcenter.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tecunsa.*;

/**
 * Form bean for a Struts application.
 * Users may access 4 fields on this form:
 * <ul>
 * <li>rfc - [your comment here]
 * <li>address - [your comment here]
 * <li>name - [your comment here]
 * <li>finish - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class ClientChangeForm extends ActionForm {

	private int id;
	private String name;
	private String address;
	private String rfc;
	private String email;
	private String telephone;
	private String email1;
	private String contact;

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		if(  name==null  )
			errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));
		else
		  {
			name=name.trim();
			if (  name.length() == 0  )
				errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));
		  }


		if(email!=null)
		  {
			if (!Email.isEmail(email))
			  errors.add("email", new org.apache.struts.action.ActionError("error.field.invalid.format"));
		  }
		else
		  errors.add("email", new org.apache.struts.action.ActionError("error.field.required"));

		if(email1!=null)
		  {
			if ( !email1.equals("") )
			  if (!Email.isEmail(email1))
				errors.add("email1", new org.apache.struts.action.ActionError("error.field.invalid.format"));
		  }
		else
		  email1 = "";

		return errors;

	}



	

	/**
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	

	/**
	 * @return
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * @return
	 */
	public String getTelephone() {
		return telephone;
	}



	/**
	 * @param string
	 */
	public void setAddress(String string) {
		address = string;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
	}

	

	/**
	 * @param i
	 */
	public void setId(int i) {
		id = i;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	

	/**
	 * @param string
	 */
	public void setRfc(String string) {
		rfc = string;
	}

	/**
	 * @param string
	 */
	public void setTelephone(String string) {
		telephone = string;
	}

	/**
	 * @return
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @return
	 */
	public String getEmail1() {
		return email1;
	}

	/**
	 * @param string
	 */
	public void setContact(String string) {
		contact = string;
	}

	/**
	 * @param string
	 */
	public void setEmail1(String string) {
		email1 = string;
	}

}

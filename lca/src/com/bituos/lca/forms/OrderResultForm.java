package com.bituos.lca.forms;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.bituos.lca.*; 
import com.bituos.*;
import com.bituos.lca.Beans.*;

import java.util.LinkedList; 


/**
 * Form bean for a Struts application.
 * Users may access 4 fields on this form:
 * <ul>
 * <li>rfc - [your comment here]
 * <li>address - [your comment here]
 * <li>password - [your comment here]
 * <li>name - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class OrderResultForm extends ActionForm {

	private String  id_order = null;
	private String  id_test = null;
	private String  id_test_paremeter = null;
	private String[]  id_test_result = null;
	private String[] is_numeric = null;
	private String[] value = null;
	private String[] value_str = null;
	
	private String[] min_value = null;
	private String[] max_value = null;
	
	private String[] checkboxAgree = null;
	
	
	
	private TestResult[] beanTestResults;
	
	private String[] date_reg = null;
	

	
	 
	private String process = null;

	private LinkedList<TestResult> lstItems;

	/*public OrderResultForm()
    {
		lstItems = new LinkedList<TestResult>();
    }*/
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.
		HttpSession ses = request.getSession();
		lstItems = new LinkedList<TestResult>();
		id_order = null;

		id_test = null;
		id_test_paremeter = null;
		id_test_paremeter = null;
		
		Integer beanTestResultsSize = (Integer) ses.getAttribute( "beanTestResultsSize" );
		if ( beanTestResultsSize != null )
		{
			if(beanTestResults == null && beanTestResultsSize != 0)
			{				
				
				beanTestResults = new TestResult[beanTestResultsSize];
				this.checkboxAgree  = new String[beanTestResultsSize];
				/*date_reg = new String[beanTestResultsSize];
				value = new String[beanTestResultsSize];
				value_str = new String[beanTestResultsSize];
				is_numeric = new String[beanTestResultsSize];*/
				
				 
				
				
				
			}
		}

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		
		if ( process == null)
		  {
			errors.add("name", new org.apache.struts.action.ActionError("error.undefined.process"));
		  }
		else if ( process.equals( "selectOrder" ) )
		  {
			if ( id_order != null )
			  {
				id_order = id_order.trim().toUpperCase();
				if ( id_order.length() == 0 )
				  errors.add("id_order", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
				    try
				    {
				    	Integer i = Integer.valueOf(id_order);
				    }
				    catch(Exception e)
				    {
				    	errors.add("id_order", new org.apache.struts.action.ActionError("error.field.invalid.format"));
				    	
				    }
				  }
			  }
			else
			  errors.add("id_order", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else if ( process.equals( "captureValue" ) )
		  {
			
			
			int index = 0;
			
			for(TestResult testResult: this.lstItems) 
			{
			  if(testResult != null)	
			  {
				 String indexFieldValueName = "item["+String.valueOf( index ) +"].value";
				 String indexFieldValueStringName = "item["+String.valueOf( index ) +"].value_str";
				 String indexFieldfree_textStringName = "item["+String.valueOf( index ) +"].free_text";
				 
				// if(testResult.getIs_free_text().equals("0") )
				// {					 
					 if(testResult.getIs_numeric().equals("1") )
					 {
						 if ( testResult.getValue() != null )
						  {
							 testResult.setValue( testResult.getValue().trim());
							if ( testResult.getValue().length() == 0 )
							  errors.add(indexFieldValueName, new org.apache.struts.action.ActionError("error.field.required"));
						  }
						else
					      errors.add(indexFieldValueName, new org.apache.struts.action.ActionError("error.field.required"));
					 }else
					 {	
						 if ( testResult.getIs_free_text().equals("0"))
						 {
							 if ( testResult.getValue_str() != null )
							  {
								 testResult.setValue_str( testResult.getValue_str().trim());
								if ( testResult.getValue_str().length() == 0 )
								  errors.add(indexFieldValueStringName, new org.apache.struts.action.ActionError("error.field.required"));
							  }
							else
						      errors.add(indexFieldValueStringName, new org.apache.struts.action.ActionError("error.field.required"));
						 }
						 else
						 {
							 if ( testResult.getFree_text() != null )
							  {
								 testResult.setFree_text( testResult.getFree_text().trim());
								if ( testResult.getFree_text().length() == 0 )
								  errors.add(indexFieldfree_textStringName, new org.apache.struts.action.ActionError("error.field.required"));
							  }
							else
						      errors.add(indexFieldfree_textStringName, new org.apache.struts.action.ActionError("error.field.required")); 
						 }
						 
					 }
				 //}
			  }
				 
				 index++;
			}
			
			


			

		  }
		
		
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.trim().length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }
		return errors;

	}

	 


	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}


	
	

	public String getId_test_paremeter() {
		return id_test_paremeter;
	}

	public void setId_test_paremeter(String id_test_paremeter) {
		this.id_test_paremeter = id_test_paremeter;
	}

	public String getId_test() {
		return id_test;
	}

	public void setId_test(String id_test) {
		this.id_test = id_test;
	}

	public String getId_test_result(int index) {
		return id_test_result[index];
	}

	public void setId_test_result(int index, String  id_test_result) {
		if(this.beanTestResults[index] == null)
		{
			TestResult t=new TestResult();
			t.setId(Integer.valueOf( id_test_result ));
			this.beanTestResults[index] = t;
		}else
		{
			this.beanTestResults[index].setId(Integer.valueOf( id_test_result ));
		}
		
		//this.id_test_result = id_test_result;
	}

	public String getId_order() {
		return id_order;
	}

	public void setId_order(String id_order) {
		this.id_order = id_order;
	}

	
	

	public String  getValue_str(int index) {
		return value_str[index];
	}

	public void setValue_str(int index, String value_str) {
		if(this.beanTestResults[index] == null)
		{
			TestResult t=new TestResult();
			t.setValue_str(value_str);
			this.beanTestResults[index] = t;
		}else
		{
			this.beanTestResults[index].setValue_str(value_str);
		}
		//this.value_str = value_str;
	}

	public String getValue(int index) {
		return value[index];
	}

	public void setValue(int index, String value) {
		if(this.beanTestResults[index] == null)
		{
			TestResult t=new TestResult();
			t.setValue(value);
			this.beanTestResults[index] = t;
		}else
		{
			this.beanTestResults[index].setValue(value);
		}
		
		//this.value[index] = value;
	}

 

	public String getIs_numeric(int index) {
		return is_numeric[index];
	}

	public void setIs_numeric(int index, String is_numeric) {
		
		if(this.beanTestResults[index] == null)
		{
			TestResult t=new TestResult();
			t.setIs_numeric(is_numeric);
			this.beanTestResults[index] = t;
		}else
		{
			this.beanTestResults[index].setIs_numeric(is_numeric);
		}
		
		//this.is_numeric[index] = is_numeric;
	}

	

	public TestResult[] getBeanTestResults() {
		return beanTestResults;
	}

	public void setBeanTestResults(TestResult[] beanTestResults) {
		this.beanTestResults = beanTestResults;
	}

	public String getDate_reg(int index) {
		return date_reg[index];
	}
	public void setDate_reg(int index,String date_reg) {
		
		if(this.beanTestResults[index] == null)
		{
			TestResult t=new TestResult();
			t.setDate_reg(date_reg);
			this.beanTestResults[index] = t;
		}else
		{
			this.beanTestResults[index].setDate_reg(date_reg);
		}
	}

	
	
	
	
	
	

	

	public String getCheckboxAgree(int index) {
		return checkboxAgree[index];
	}

	public void setCheckboxAgree(int index, String value) {
		
			this.checkboxAgree[index] = value;
		
		
		//this.value[index] = value;
	}


	
	public  TestResult getItem(int index)
	{
		/*int size = lstItems.size();
	    /*if  (size > 0 )
	    	lstItems.get(index);
	    else
	    	lstItems = new LinkedList<TestResult>();*/
		
		while (this.lstItems.size() <=  index) {
			lstItems.add(new TestResult());
		}
		
	    
		return lstItems.get(index);
	}
	

	/*public  void setItem(int index, String value)
	{
	    lstItems.get(index);
	    
	}*/
	
	/*public  void setItem(int index, String value)
	{
	    lstItems.get(index).setId_order(value);
	}*/

	public LinkedList<TestResult> getLstItems() {
		return lstItems;
	}

	public void setLstItems(LinkedList<TestResult> lstItems) {
		this.lstItems = lstItems;
	}


	
	
	
}

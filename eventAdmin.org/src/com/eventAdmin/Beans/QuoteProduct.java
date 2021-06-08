package com.eventAdmin.Beans;

import java.util.Iterator;
import java.util.List;

/**
 * @author dsa1972
*/

public class QuoteProduct {

	private int id_product;
	private String description;
	private double price;
	private int qty;
	private String perPerson;
	
	
	


	public int indexOf(List list)
	  {
		Iterator iter = list.iterator();
		int index = 0;
		
		while ( iter.hasNext() )
		  {
			QuoteProduct qp = (QuoteProduct) iter.next();
			
			if ( id_product == qp.getId_product() )
		      return index;
			
			index++;
		  }
		
		return -1;
	  }

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	

	




	public String getPerPerson() {
		return perPerson;
	}

	public void setPerPerson(String perPerson) {
		this.perPerson = perPerson;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string) {
		description = string;
	}

	




	/**
	 * @return
	 */
	public int getId_product() {
		return id_product;
	}

	/**
	 * @param i
	 */
	public void setId_product(int i) {  
		id_product = i;
	}

	/**
	 * @return
	 */
	public int getQty() {
		return qty;
	}

	/**
	 * @param i
	 */
	public void setQty(int i) {
		qty = i;
	}








	public double getPrice() {
		return price;
	}








	public void setPrice(double price) {
		this.price = price;
	}

	
	
	
	
	
}

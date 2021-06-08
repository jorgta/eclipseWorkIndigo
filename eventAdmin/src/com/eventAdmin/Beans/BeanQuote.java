/*
 * Created on June 30, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.eventAdmin.Beans;


import java.util.Date;

/**
 * @author cirm
*/

public class BeanQuote{

	private int id;
	private Date date_reg;
	private BeanSaloon id_saloon;	
	private String saloon_description;
	private BeanClient id_client; 
	private String client_name; 
	private String client_address; 
	private int people_count; 
	private BeanList id_list; 
	private String list_description; 
	private int list_min; 
	private int list_max; 
	private BeanListOptions id_list_option; 
	private double total;
	private BeanCompany id_company;
	private BeanUser id_user;
	private BeanListOptionMenu id_list_option_menu; 
	private String option_description; 
	private String menu_description; 
	private BeanTypeHour id_hour;
	private int hourQuantity; 
	private Date date_event;
	private String anotherEmail;
	private double menu_price;
	private String client_phone;
	private String client_company;
	private String client_email;
	private int id_intern;
	private String notes;
	
	
	
	public int getId_intern() {
		return id_intern;
	}



	public void setId_intern(int id_intern) {
		this.id_intern = id_intern;
	}



	public String getClient_email() {
		return client_email;
	}



	public void setClient_email(String client_email) {
		this.client_email = client_email;
	}



	public double getMenu_price() {
		return menu_price;
	}



	public void setMenu_price(double menu_price) {
		this.menu_price = menu_price;
	}



	public String getClient_phone() {
		return client_phone;
	}



	public void setClient_phone(String client_phone) {
		this.client_phone = client_phone;
	}



	public String getClient_company() {
		return client_company;
	}



	public void setClient_company(String client_company) {
		this.client_company = client_company;
	}





	public String getAnotherEmail() {
		return anotherEmail;
	}



	public void setAnotherEmail(String anotherEmail) {
		this.anotherEmail = anotherEmail;
	}



	public Date getDate_event() {
		return date_event;
	}



	public void setDate_event(Date date_event) {
		this.date_event = date_event;
	}



	public BeanTypeHour getId_hour() {
		return id_hour;
	}



	public void setId_hour(BeanTypeHour id_hour) {
		this.id_hour = id_hour;
	}



	public int getHourQuantity() {
		return hourQuantity;
	}



	public void setHourQuantity(int hourQuantity) {
		this.hourQuantity = hourQuantity;
	}



	public BeanSaloon getId_saloon() {
		return id_saloon;
	}



	public void setId_saloon(BeanSaloon id_saloon) {
		this.id_saloon = id_saloon;
	}



	public String getSaloon_description() {
		return saloon_description;
	}



	public void setSaloon_description(String saloon_description) {
		this.saloon_description = saloon_description;
	}



	public BeanListOptionMenu getId_list_option_menu() {
		return id_list_option_menu;
	}



	public void setId_list_option_menu(BeanListOptionMenu id_list_option_menu) {
		this.id_list_option_menu = id_list_option_menu;
	}



	public String getOption_description() {
		return option_description;
	}



	public void setOption_description(String option_description) {
		this.option_description = option_description;
	}



	public String getMenu_description() {
		return menu_description;
	}



	public void setMenu_description(String menu_description) {
		this.menu_description = menu_description;
	}



	public BeanCompany getId_company() {
		return id_company;
	}



	public void setId_company(BeanCompany id_company) {
		this.id_company = id_company;
	}



	public BeanUser getId_user() {
		return id_user;
	}



	public void setId_user(BeanUser id_user) {
		this.id_user = id_user;
	}



	public String getClient_address() {
		return client_address;
	}



	public void setClient_address(String client_address) {
		this.client_address = client_address;
	}



	public String getClient_name() {
		return client_name;
	}



	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}









	public Date getDate_reg() {
		return date_reg;
	}



	public void setDate_reg(Date date_reg) {
		this.date_reg = date_reg;
	}



	public BeanClient getId_client() {
		return id_client;
	}



	public void setId_client(BeanClient id_client) {
		this.id_client = id_client;
	}



	public BeanList getId_list() {
		return id_list;
	}



	public void setId_list(BeanList id_list) {
		this.id_list = id_list;
	}



	public BeanListOptions getId_list_option() {
		return id_list_option;
	}



	public void setId_list_option(BeanListOptions id_list_option) {
		this.id_list_option = id_list_option;
	}






	public String getList_description() {
		return list_description;
	}



	public void setList_description(String list_description) {
		this.list_description = list_description;
	}



	public int getList_max() {
		return list_max;
	}



	public void setList_max(int list_max) {
		this.list_max = list_max;
	}



	public int getList_min() {
		return list_min;
	}



	public void setList_min(int list_min) {
		this.list_min = list_min;
	}




	public int getPeople_count() {
		return people_count;
	}



	public void setPeople_count(int people_count) {
		this.people_count = people_count;
	}



	


	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}



	/**
	 * @return
	 */
	public int getId() {
		return id;
	}



	/**
	 * @param i
	 */
	public void setId(int i) {
		id = i;
	}



	public String getNotes() {
		return notes;
	}



	public void setNotes(String notes) {
		this.notes = notes;
	}



}

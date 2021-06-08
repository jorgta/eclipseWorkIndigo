/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.struts2.Beans;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class BeanItem {

			private int id;
			private String title;
			private String alias;
			private int category_id;
			private int published;
			private String introtext;
			private String fulltext;
			private String video;
			private String gallery;
			private Date created;
			private int created_by;			 
			private int trash;
			private int access;
			private int ordering;		
			private String image;
			private String params;

		  	//TODO: terminar este bean 
			  
}

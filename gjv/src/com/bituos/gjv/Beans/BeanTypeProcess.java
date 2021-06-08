/*
 * Created on February, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and

Comments
 */
package com.bituos.gjv.Beans;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class BeanTypeProcess{

	private int id;
	private String description;

	/**
	 * @return
           * Obtiene el valor de la variable id
	 */
	public int getId() {
		return id;
	}


	/**
	* @return
	 * Obtiene el valor de la variable description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param string
	 * Asigna un valor a  la variable description
	 */
	public void setDescription(String string) {
		description = string;
	}

	/**
	 * @param i
	 */
	public void setId(int i) {
		id = i;
	}

}
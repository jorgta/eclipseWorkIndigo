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

public class BeanProcess{

	private int id;
	private String description;
	private String url;
	private String img_name;
	private String action;
	private BeanTypeProcess id_type_process;
	private String target;
	private String name;

	/**
	* @return
	* Obtiene el valor de la variable description
	 */
	public String getDescription() {
		return description;
	}

	/**
	  * @param string
	 * Asigna un valor a la variable description
	 */
	public void setDescription(String string) {
		description = string;
	}

	/**
	* @return
	* Obtiene el valor de la variable id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param i
	* Asigna el valor de i a  la variable id
	 */
	public void setId(int i) {
		id = i;
	}

	/**
	* @return
	* Obtiene el valor de la variable img_name
	 */
	public String getImg_name() {
		return img_name;
	}

	/**
	* @return
	* Obtiene el valor de la variable url
	 */
	public String getUrl() {
		return url;
	}

	/**
	  * @param string
	* Asigna un valor a la variable img_name
	 */
	public void setImg_name(String string) {
		img_name = string;
	}

	/**
	  * @param string
	* Asigna un valor a la variable url
	 */
	public void setUrl(String string) {
		url = string;
	}


	/**
	* @return
	* Obtiene el valor de la variable action
	 */
	public String getAction() {
		return action;
	}

	/**
	  * @param string
	* Asigna un valor a la variable action
	 */
	public void setAction(String string) {
		action = string;
	}

	/**
	* @return
	* Obtiene el valor de la variable id_type_process
	 */
	public BeanTypeProcess getId_type_process() {
		return id_type_process;
	}

	/**
	 * @param type
	* Asigna un objeto de tipo  BeanTypeProcess a la variable id_type_process
	 */
	public void setId_type_process(BeanTypeProcess type) {
		id_type_process = type;
	}

	/**
	* @return
	* Obtiene el valor de la variable target
	 */
	public String getTarget() {
		return target;
	}

	/**
	  * @param string
	* Asigna un valor a la variable target
	 */
	public void setTarget(String string) {
		target = string;
	}

	/**
	* @return
	* Obtiene el valor de la variable name
	 */
	public String getName() {
		return name;
	}

	/**
	  * @param string
	* Asigna un valor a la variable name
	 */
	public void setName(String string) {
		name = string;
	}

}

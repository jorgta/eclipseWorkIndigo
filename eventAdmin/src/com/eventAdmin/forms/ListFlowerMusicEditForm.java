package com.eventAdmin.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author     	Bituos Tools S de RL MI
 */

import com.bituos.Utils;

public class ListFlowerMusicEditForm extends ActionForm {

	private int id;

	private String process;
	
	private String id_flower;
	private String flowerDescription;
	private String flowerPrice;
	private String flowerPerPerson;
	
	private String id_music;
	private String musicDescription;
	private String musicPrice;
	private String musicPerPerson;
	
	
	
	
	public ActionErrors validate(
			ActionMapping mapping,
			HttpServletRequest request) {

			ActionErrors errors = new ActionErrors();
			// Validate the fields in your form, adding
			// adding each error to this.errors as found, e.g.

			// if ((field == null) || (field.length() == 0)) {
			//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
			// }

			if( process != null )
			  {
				if( process.equals("createFlower") || process.equals("updateFlower")  )
				  {
					if( flowerDescription==null  )
						errors.add("flowerDescription", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						flowerDescription=flowerDescription.trim().toUpperCase();
						if (  flowerDescription.length() == 0  )
							errors.add("flowerDescription", new org.apache.struts.action.ActionError("error.field.required"));
					  }
					
					if( flowerPrice ==null  )
						errors.add("flowerPrice", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						flowerPrice=flowerPrice.trim();
						if (  flowerPrice.length() == 0  )
							errors.add("flowerPrice", new org.apache.struts.action.ActionError("error.field.required"));
						else
							if( !Utils.isDouble(flowerPrice) )
								errors.add("flowerPrice", new org.apache.struts.action.ActionError("error.field.invalid.format"));
					  }
					
					if( flowerPerPerson == null  )
					  flowerPerPerson = "off";
					
					if( process.equals("updateFlower") )
						if( id_flower==null  )
							errors.add("id_flower", new org.apache.struts.action.ActionError("error.field.required"));
						else
						  {
							id_flower=id_flower.trim();
							if (  id_flower.length() == 0  )
								errors.add("id_flower", new org.apache.struts.action.ActionError("error.field.required"));
						  }
				  }
				else if( process.equals("createMusic")  || process.equals("updateMusic")  )
				  {
					if( musicDescription==null  )
						errors.add("musicDescription", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						musicDescription=musicDescription.trim().toUpperCase();
						if (  musicDescription.length() == 0  )
							errors.add("musicDescription", new org.apache.struts.action.ActionError("error.field.required"));
					  }
					
					if( musicPrice ==null  )
						errors.add("musicPrice", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						musicPrice=musicPrice.trim();
						if (  musicPrice.length() == 0  )
							errors.add("musicPrice", new org.apache.struts.action.ActionError("error.field.required"));
						else
							if( !Utils.isDouble(musicPrice) )
								errors.add("musicPrice", new org.apache.struts.action.ActionError("error.field.invalid.format"));
					  }
					
					
					if( musicPerPerson == null  )
					  musicPerPerson = "off";

					if( process.equals("updateMusic") )
						if( id_music==null  )
							errors.add("id_music", new org.apache.struts.action.ActionError("error.field.required"));
						else
						  {
							id_music=id_music.trim();
							if (  id_music.length() == 0  )
								errors.add("id_music", new org.apache.struts.action.ActionError("error.field.required"));
						  }
				  }
				else if( process.equals("deleteFlower")   )
				  {
					if( id_flower ==null  )
						errors.add("id_flower", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						id_flower=id_flower.trim();
						if (  id_flower.length() == 0  )
							errors.add("id_flower", new org.apache.struts.action.ActionError("error.field.required"));
					  }
				  }
			  }

			return errors;

		}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getProcess() {
		return process;
	}




	public void setProcess(String process) {
		this.process = process;
	}




	public String getId_flower() {
		return id_flower;
	}




	public void setId_flower(String id_flower) {
		this.id_flower = id_flower;
	}




	public String getFlowerDescription() {
		return flowerDescription;
	}




	public void setFlowerDescription(String flowerDescription) {
		this.flowerDescription = flowerDescription;
	}




	public String getFlowerPrice() {
		return flowerPrice;
	}




	public void setFlowerPrice(String flowerPrice) {
		this.flowerPrice = flowerPrice;
	}




	public String getFlowerPerPerson() {
		return flowerPerPerson;
	}




	public void setFlowerPerPerson(String flowerPerPerson) {
		this.flowerPerPerson = flowerPerPerson;
	}




	public String getId_music() {
		return id_music;
	}




	public void setId_music(String id_music) {
		this.id_music = id_music;
	}




	public String getMusicDescription() {
		return musicDescription;
	}




	public void setMusicDescription(String musicDescription) {
		this.musicDescription = musicDescription;
	}




	public String getMusicPrice() {
		return musicPrice;
	}




	public void setMusicPrice(String musicPrice) {
		this.musicPrice = musicPrice;
	}




	public String getMusicPerPerson() {
		return musicPerPerson;
	}




	public void setMusicPerPerson(String musicPerPerson) {
		this.musicPerPerson = musicPerPerson;
	}

	
	
	
	

	
	





	


	
}

package com.macdevelopers.moofwd.Contacts;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ContactsPOJO{

	@SerializedName("detail")
	private Detail detail;

	@SerializedName("status")
	private String status;

	public void setDetail(Detail detail){
		this.detail = detail;
	}

	public Detail getDetail(){
		return detail;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ContactsPOJO{" + 
			"detail = '" + detail + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
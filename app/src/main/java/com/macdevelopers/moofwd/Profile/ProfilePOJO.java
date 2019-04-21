package com.macdevelopers.moofwd.Profile;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ProfilePOJO{

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
			"ProfilePOJO{" + 
			"detail = '" + detail + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
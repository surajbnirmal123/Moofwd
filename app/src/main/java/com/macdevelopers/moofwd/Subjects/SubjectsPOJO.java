package com.macdevelopers.moofwd.Subjects;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class SubjectsPOJO{

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
			"SubjectsPOJO{" + 
			"detail = '" + detail + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
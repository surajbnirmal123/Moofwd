package com.macdevelopers.moofwd.Contacts;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ListItem{

	@SerializedName("phone")
	private String phone;

	@SerializedName("__v")
	private int V;

	@SerializedName("name")
	private String name;

	@SerializedName("_id")
	private String id;

	@SerializedName("email")
	private String email;

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setV(int V){
		this.V = V;
	}

	public int getV(){
		return V;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"ListItem{" + 
			"phone = '" + phone + '\'' + 
			",__v = '" + V + '\'' + 
			",name = '" + name + '\'' + 
			",_id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}
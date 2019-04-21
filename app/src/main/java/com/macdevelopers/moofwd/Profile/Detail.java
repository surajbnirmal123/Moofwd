package com.macdevelopers.moofwd.Profile;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Detail{

	@SerializedName("image")
	private String image;

	@SerializedName("career")
	private String career;

	@SerializedName("address")
	private String address;

	@SerializedName("phone")
	private String phone;

	@SerializedName("city")
	private String city;

	@SerializedName("codebar")
	private long codebar;

	@SerializedName("campus")
	private String campus;

	@SerializedName("__v")
	private int V;

	@SerializedName("name")
	private String name;

	@SerializedName("_id")
	private String id;

	@SerializedName("email")
	private String email;

	@SerializedName("dni")
	private long dni;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setCareer(String career){
		this.career = career;
	}

	public String getCareer(){
		return career;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setCodebar(long codebar){
		this.codebar = codebar;
	}

	public long getCodebar(){
		return codebar;
	}

	public void setCampus(String campus){
		this.campus = campus;
	}

	public String getCampus(){
		return campus;
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

	public void setDni(long dni){
		this.dni = dni;
	}

	public long getDni(){
		return dni;
	}

	@Override
 	public String toString(){
		return 
			"Detail{" + 
			"image = '" + image + '\'' + 
			",career = '" + career + '\'' + 
			",address = '" + address + '\'' + 
			",phone = '" + phone + '\'' + 
			",city = '" + city + '\'' + 
			",codebar = '" + codebar + '\'' + 
			",campus = '" + campus + '\'' + 
			",__v = '" + V + '\'' + 
			",name = '" + name + '\'' + 
			",_id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			",dni = '" + dni + '\'' + 
			"}";
		}
}
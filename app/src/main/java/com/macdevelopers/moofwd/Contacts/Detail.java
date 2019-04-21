package com.macdevelopers.moofwd.Contacts;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Detail{

	@SerializedName("list")
	private List<ListItem> list;

	public void setList(List<ListItem> list){
		this.list = list;
	}

	public List<ListItem> getList(){
		return list;
	}

	@Override
 	public String toString(){
		return 
			"Detail{" + 
			"list = '" + list + '\'' + 
			"}";
		}
}
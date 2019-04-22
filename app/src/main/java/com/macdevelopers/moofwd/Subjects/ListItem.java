package com.macdevelopers.moofwd.Subjects;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ListItem{

	@SerializedName("lectureEmail")
	private String lectureEmail;

	@SerializedName("period")
	private String period;

	@SerializedName("courseName")
	private String courseName;

	@SerializedName("lectureName")
	private String lectureName;

	@SerializedName("__v")
	private int V;

	@SerializedName("_id")
	private String id;

	@SerializedName("type")
	private String type;

	@SerializedName("subjectCode")
	private String subjectCode;

	@SerializedName("subjectOverview")
	private String subjectOverview;

	@SerializedName("subjectName")
	private String subjectName;

	public void setLectureEmail(String lectureEmail){
		this.lectureEmail = lectureEmail;
	}

	public String getLectureEmail(){
		return lectureEmail;
	}

	public void setPeriod(String period){
		this.period = period;
	}

	public String getPeriod(){
		return period;
	}

	public void setCourseName(String courseName){
		this.courseName = courseName;
	}

	public String getCourseName(){
		return courseName;
	}

	public void setLectureName(String lectureName){
		this.lectureName = lectureName;
	}

	public String getLectureName(){
		return lectureName;
	}

	public void setV(int V){
		this.V = V;
	}

	public int getV(){
		return V;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setSubjectCode(String subjectCode){
		this.subjectCode = subjectCode;
	}

	public String getSubjectCode(){
		return subjectCode;
	}

	public void setSubjectOverview(String subjectOverview){
		this.subjectOverview = subjectOverview;
	}

	public String getSubjectOverview(){
		return subjectOverview;
	}

	public void setSubjectName(String subjectName){
		this.subjectName = subjectName;
	}

	public String getSubjectName(){
		return subjectName;
	}

	@Override
 	public String toString(){
		return 
			"ListItem{" + 
			"lectureEmail = '" + lectureEmail + '\'' + 
			",period = '" + period + '\'' + 
			",courseName = '" + courseName + '\'' + 
			",lectureName = '" + lectureName + '\'' + 
			",__v = '" + V + '\'' + 
			",_id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",subjectCode = '" + subjectCode + '\'' + 
			",subjectOverview = '" + subjectOverview + '\'' + 
			",subjectName = '" + subjectName + '\'' + 
			"}";
		}
}
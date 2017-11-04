package com.example.model;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel
{  
	private String npm;
    private String name;
    private double gpa;
    private List<CourseModel> courses;
    
	public String getNpm() {
		return npm;
	}
	public void setNpm(String npm) {
		this.npm = npm;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public List<CourseModel> getCourses() {
		return courses;
	}
	public void setCourses(List<CourseModel> courses) {
		this.courses = courses;
	}
}


package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.model.CourseModel;
import com.example.model.StudentModel;
import com.example.dao.CourseDAO;
import com.example.dao.StudentDAO;

import groovy.util.logging.Slf4j;


@Slf4j
@Service
@Primary
public class StudentServiceRest implements StudentService{

	@Autowired
	private StudentDAO StudentDAO;
	
	@Autowired
	private CourseDAO CourseDAO;
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Override
	public StudentModel selectStudent (String npm)
	{
		//log.info ("REST - select student with npm {}", npm);
		return StudentDAO.selectStudent (npm);
	}
	
	@Override
	public List<StudentModel> selectAllStudents ()
	{
		//log.info ("REST - select all students");
		return StudentDAO.selectAllStudents();
	}
	
	@Override
	public void addStudent (StudentModel student){}
	@Override
	public void deleteStudent (String npm){}
	@Override
	public void updateStudent(StudentModel student) {}

	
	@Override
	public List<CourseModel> selectAllCourse() {
		// TODO Auto-generated method stub
		return CourseDAO.selectAllCourse();
	}

	@Override
	public CourseModel viewCourse(String idCourse) {
		// TODO Auto-generated method stub
		return CourseDAO.viewCourse(idCourse);
	}
}

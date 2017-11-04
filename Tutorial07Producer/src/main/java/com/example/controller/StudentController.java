package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.CourseModel;
import com.example.model.StudentModel;
import com.example.service.StudentService;

@Controller
public class StudentController
{
	@Autowired
	StudentService studentDAO;


	@RequestMapping("/")
	public String index ()
	{
		return "index";
	}


	@RequestMapping("/student/add")
	public String add ()
	{
		return "form-add";
	}


	@RequestMapping("/student/add/submit")
	public String addSubmit (
			@RequestParam(value = "npm", required = false) String npm,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "gpa", required = false) double gpa)
	{
		StudentModel student = new StudentModel (npm, name, gpa, null);
		studentDAO.addStudent (student);

		return "success-add";
	}


	@RequestMapping("/student/view")
	public String view (Model model,
			@RequestParam(value = "npm", required = false) String npm)
	{
		StudentModel student = studentDAO.selectStudent (npm);

		if (student != null) {
			model.addAttribute ("student", student);
			return "view";
		} else {
			model.addAttribute ("npm", npm);
			return "not-found";
		}
	}


	@RequestMapping("/student/view/{npm}")
	public String viewPath (Model model,
			@PathVariable(value = "npm") String npm)
	{
		StudentModel student = studentDAO.selectStudent (npm);

		if (student != null) {
			model.addAttribute ("student", student);
			return "view";
		} else {
			model.addAttribute ("npm", npm);
			return "not-found";
		}
	}


	@RequestMapping("/student/viewall")
	public String view (Model model)
	{
		List<StudentModel> students = studentDAO.selectAllStudents ();
		model.addAttribute ("students", students);

		return "viewall";
	}


	//method deleteStudent
	@RequestMapping("/student/delete/{npm}")
	public String delete (Model model, @PathVariable(value = "npm") String npm)
	{
		StudentModel student = studentDAO.selectStudent(npm);
		if (student != null) {
			studentDAO.deleteStudent(npm);
			return "delete";
		} else {
			model.addAttribute ("npm", npm);
			return "not-found";
		}
	}

	//method update untuk menampilkan form update
	@RequestMapping("/student/update/{npm}")
	public String update (Model model, @PathVariable(value = "npm") String npm)
	{
		StudentModel student = studentDAO.selectStudent(npm);

		if (student != null) {
			model.addAttribute("student", student);
			return "form-update";
		} else {
			model.addAttribute ("npm", npm);
			return "not-found";
		}
	}

	/** method updateSubmit untuk latihan menambahkan update
	@RequestMapping(value = "/student/update/submit", method = RequestMethod.POST)
	public String updateSubmit (@RequestParam(value = "npm", required = false) String npm,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "gpa", required = false) double gpa)
	{

		StudentModel student = new StudentModel(npm, name, gpa);
		studentDAO.updateStudent(student);; 
		return "success-update";
	}
	 **/

	//method updateSubmit untuk latihan menggunakan objek sebagai parameter
	@RequestMapping(value = "/student/update-objek/submit", method = RequestMethod.POST)
	public String updateSubmit(Model model, @ModelAttribute StudentModel student)
	{
		StudentModel newStudent = student;
		studentDAO.updateStudent(newStudent);
		return "success-update";	
	}

	//method untuk menampilkan course dengan id course tertentu dan mahasiswa yang mengambilnya
	@RequestMapping("/course/view/{id}")
	public String viewCourse (Model model, @PathVariable(value = "id") String idCourse)
	{
		CourseModel courses = studentDAO.viewCourse(idCourse);

		if (courses != null) {
			model.addAttribute ("course", courses);
			return "viewIdCourse";
		}else {
			model.addAttribute ("id_course", idCourse);
			return "not-foundCourse";
		}	
	}

	@RequestMapping("/course/viewall")
	public String viewAllCourse (Model model)
	{
		List<CourseModel> courses = studentDAO.selectAllCourse();
		model.addAttribute ("courses", courses);
		return "viewall-course";
	}

}

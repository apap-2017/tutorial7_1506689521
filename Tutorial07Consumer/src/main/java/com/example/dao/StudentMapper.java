package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Results;

import com.example.model.CourseModel;
import com.example.model.StudentModel;

@Mapper
public interface StudentMapper
{
	@Select("SELECT npm, name, gpa from student where npm = #{npm}")
	@Results(value = {
			@Result(property="npm", column="npm"),
			@Result(property="name", column="name"),
			@Result(property="gpa", column="gpa"),
			@Result(property="courses", column="npm", javaType = List.class, many = @Many(select="selectCourses"))
	})
	StudentModel selectStudent (@Param("npm") String npm);
	
	

	@Select("SELECT npm, name, gpa from student")
	@Results(value = {
			@Result(property="npm", column="npm"),
			@Result(property="name", column="name"),
			@Result(property="gpa", column="gpa"),
			@Result(property="courses", column="npm", javaType = List.class, many = @Many(select="selectCourses"))
	})
	List<StudentModel> selectAllStudents ();
	

	@Insert("INSERT INTO student (npm, name, gpa) VALUES (#{npm}, #{name}, #{gpa})")
	void addStudent (StudentModel student);

	@Delete("DELETE FROM student where npm = #{npm}")
	void deleteStudent(@Param("npm") String npm);

	@Update("UPDATE student s SET name = #{name}, gpa = #{gpa} WHERE s.npm = #{npm}")
	void updateStudent (StudentModel student);	

	@Select("SELECT course.id_course, name, credits " + "from studentcourse join course " + "on studentcourse.id_course = course.id_course " + "where studentcourse.npm = #{npm}") 
	@Results(value = {
			@Result(property="idCourse", column="id_course"),
			@Result(property="name", column="name"),
			@Result(property="credits", column="credits"),
			@Result(property="students", column="id_course", javaType = List.class, many = @Many(select="selectStudent"))
	})
	List <CourseModel> selectCourses (@Param("npm") String npm);
	
	
	@Select("SELECT * from course where id_course = #{id_course}")
	@Results(value = {
			@Result(property="idCourse", column="id_course"),
			@Result(property="name", column="name"),
			@Result(property="credits", column="credits"),
			@Result(property="students", column="id_course", javaType = List.class, many = @Many(select="selectCourseStudent"))
	})
	CourseModel selectCourse(@Param("id_course") String id_course);
	
	@Select("SELECT * from student join studentcourse on student.npm = studentcourse.npm where studentcourse.id_course = #{id_course}")
	List<StudentModel> selectCourseStudent (@Param("id_course") String id_course);
	
	@Select("SELECT * from course")
	@Results(value = {
			@Result(property="idCourse", column="id_course"),
			@Result(property="name", column="name"),
			@Result(property="credits", column="credits"),
			@Result(property="students", column="id_course", javaType = List.class, many = @Many(select="selectCourseStudent"))
	})
	List<CourseModel> selectAllCourse();
	
}

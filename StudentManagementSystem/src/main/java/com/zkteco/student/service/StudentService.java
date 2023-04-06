package com.zkteco.student.service;

import com.zkteco.student.dto.Result;
import com.zkteco.student.entity.Student;

public interface StudentService {

	public Result addStudent(Student std);

	public Result getStudent(String id);

	public Result getStudentsByFirstName(String firstName);

	public Result getStudentsByBranch(String branch);

	public Result getAllStudents();

	public Result updateStudent(String id, Student std);

	public Result deleteStudent(String id);

}

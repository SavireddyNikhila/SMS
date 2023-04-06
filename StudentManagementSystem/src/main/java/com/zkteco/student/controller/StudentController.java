package com.zkteco.student.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkteco.student.dto.Result;
import com.zkteco.student.dto.StudentDto;
import com.zkteco.student.entity.Student;
import com.zkteco.student.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService stdService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	public Result addStudent(@RequestBody StudentDto stdDto) {
		return stdService.addStudent(convertToEntity(stdDto));
	}

	@GetMapping("/{id}")
	public Result getStudent(@PathVariable("id") String id) {
		return stdService.getStudent(id);
	}

	@GetMapping("/byfName/{fname}")
	public Result getStudentsByFirstName(@PathVariable("fname") String firstName) {
		return stdService.getStudentsByFirstName(firstName);
	}

	@GetMapping("/byBranch/{branch}")
	public Result getStudentsByBranch(@PathVariable("branch") String branch) {
		return stdService.getStudentsByBranch(branch);
	}

	@GetMapping
	public Result getAllStudents() {
		return stdService.getAllStudents();
	}

	@PutMapping("/{id}")
	public Result updateStudent(@PathVariable("id") String id, @RequestBody StudentDto stdDto) {
		return stdService.updateStudent(id, convertToEntity(stdDto));
	}

	@DeleteMapping("/{id}")
	public Result deleteStudent(@PathVariable("id") String id) {
		return stdService.deleteStudent(id);
	}

	private Student convertToEntity(StudentDto stdDto) {
		Student std = modelMapper.map(stdDto, Student.class);
		return std;
	}
}
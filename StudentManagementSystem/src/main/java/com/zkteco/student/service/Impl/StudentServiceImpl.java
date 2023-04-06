package com.zkteco.student.service.Impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkteco.student.dto.Result;
import com.zkteco.student.dto.StudentDto;
import com.zkteco.student.entity.Student;
import com.zkteco.student.repository.StudentRepository;
import com.zkteco.student.service.StudentService;
import com.zkteco.student.validations.ValidateStudent;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository stdRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ValidateStudent validateStd;

	Date d = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	@Override
	public Result addStudent(Student std) {

		Result res = validateStd.validate(std);
		if (res.getMessage().equals("OK")) {
			std.setCreatedDate(df.format(d));
			std.setUpdatedDate(df.format(d));
			stdRepo.save(std);
			return new Result("SMSI001", "Student added Successully", convertToDto(std));
		}
		return res;
	}

	@Override
	public Result getStudent(String id) {
		Student std = stdRepo.findById(id).get();
		if (std == null) {
			return new Result("SMSE001", "Failed to fetch! No student found with the given id", "[]");
		}
		return new Result("SMSI002", "Student fetched successfully", convertToDto(std));
	}

	@Override
	public Result getStudentsByFirstName(String firstName) {
		List<Student> students = stdRepo.findByFirstName(firstName);
		if (students.size() == 0) {
			return new Result("SMSE002", "Failed to fetch! No student found with the given First name", "[]");
		}
		List<StudentDto> studentsDto = new ArrayList<>();
		for (Student std : students) {
			studentsDto.add(convertToDto(std));
		}
		return new Result("SMSI003", "Students fetched successfully", studentsDto);
	}

	@Override
	public Result getStudentsByBranch(String branch) {
		List<Student> students = stdRepo.findByBranch(branch);
		if (students.size() == 0) {
			return new Result("SMSE003", "Failed to fetch! No student found with the given branch", "[]");
		}
		List<StudentDto> studentsDto = new ArrayList<>();
		for (Student std : students) {
			studentsDto.add(convertToDto(std));
		}
		return new Result("SMSI003", "Students fetched successfully", studentsDto);
	}

	@Override
	public Result getAllStudents() {
		List<Student> students = stdRepo.findAll();
		if (students.size() == 0) {
			return new Result("SMSE004", "Failed to fetch! No student found", "[]");
		}
		List<StudentDto> studentsDto = new ArrayList<>();
		for (Student std : students) {
			studentsDto.add(convertToDto(std));
		}
		return new Result("SMSI003", "Students fetched successfully", studentsDto);
	}

	private StudentDto convertToDto(Student std) {
		StudentDto stdDto = modelMapper.map(std, StudentDto.class);
		return stdDto;
	}

	@Override
	public Result updateStudent(String id, Student std) {
		Student s = stdRepo.findById(id).get();
		if (s == null) {
			return new Result("SMSE005", "Failed to update! No student found with the given id", "[]");
		} else {
			Result res = validateStd.validUpdate(std);
			if (!res.getMessage().equals("OK")) {
				return res;
			} else {
				if (Objects.nonNull(std.getFirstName()) && !"".equals(std.getFirstName()))
					s.setFirstName(std.getFirstName());
				if (Objects.nonNull(std.getLastName()) && !"".equals(std.getLastName()))
					s.setLastName(std.getLastName());
				if (Objects.nonNull(std.getGender()) && !"".equals(std.getGender()))
					s.setGender(std.getGender());
				if (Objects.nonNull(std.getBranch()) && !"".equals(std.getBranch()))
					s.setBranch(std.getBranch());
				if (Objects.nonNull(std.getDateOfBirth()))
					s.setDateOfBirth(std.getDateOfBirth());
				if (Objects.nonNull(std.getMobile()) && !"".equals(std.getMobile()))
					s.setMobile(std.getMobile());
				if (Objects.nonNull(std.getEmail()) && !"".equals(std.getEmail()))
					s.setEmail(std.getEmail());
				if (Objects.nonNull(std.getAddress()) && !"".equals(std.getAddress()))
					s.setAddress(std.getAddress());
				if (Objects.nonNull(std.getProfile()) && !"".equals(std.getProfile()))
					s.setProfile(std.getProfile());

				s.setUpdatedDate(df.format(d));
			}
		}

		stdRepo.save(s);
		return new Result("SMSI004", "Student Updated successfully", convertToDto(s));
	}

	@Override
	public Result deleteStudent(String id) {
		Student std = stdRepo.findById(id).get();
		if (std == null) {
			return new Result("SMSE006", "Failed to delete! No student found with the given id", "[]");
		}
		stdRepo.deleteById(id);
		return new Result("SMSI005", "Student deleted successfully", convertToDto(std));
	}
}

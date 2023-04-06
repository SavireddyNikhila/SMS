package com.zkteco.student.validations;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zkteco.student.dto.Result;
import com.zkteco.student.entity.Student;
import com.zkteco.student.repository.StudentRepository;

@Component
public class ValidateStudent {

	@Autowired
	private StudentRepository stdRepo;

	public Result validate(Student std) {

		// validating firstname
		if (Objects.nonNull(std.getFirstName()) && !"".equals(std.getFirstName())) {
			if (!(std.getFirstName().length() <= 50)) {
				return new Result("SMSE007", "Length of firstname should not exceed 50 characters", "[]");
			}
		} else
			return new Result("SMSE008", "firstName should not be null", "[]");

		// validating lastname
		if (Objects.nonNull(std.getLastName()) && !"".equals(std.getLastName())) {
			if (!(std.getLastName().length() <= 50))
				return new Result("SMSE009", "Length of lastname should not exceed 50 characters", "[]");
		}

		// validating gender
		if (Objects.nonNull(std.getGender()) && !"".equals(std.getGender())) {
			if (std.getGender().length() == 1) {
				if (!(std.getGender().matches("(?:[M|F|O])"))) {
					return new Result("SMSE010", "Provide proper gender[M|F|O]", "[]");
				}
			} else {
				return new Result("SMSE011", "Length of gender should be 1", "[]");
			}
		}
		
		//validating branch
		if(!(Objects.nonNull(std.getBranch()) && !"".equals(std.getBranch())))
			return new Result("SMSE012","Branch should not be null","[]");

		// validating mobile
		if (Objects.nonNull(std.getMobile()) && !"".equals(std.getMobile())) {
			if ((std.getMobile().matches("[\\\\\\\\\\\\\\\\+]+[0-9]{2}+[-]+[0-9]{10}"))) {
				Student st = stdRepo.findByMobile(std.getMobile());
				if (st != null) {
					return new Result("SMSE013", "Student already exists with the given mobile", "[]");
				}
			} else
				return new Result("SMSE014",
						"Mobile number should match with proper format like (+[countrycode]-[10 digit mobile number]",
						"[]");
		} else {
			return new Result("SMSE015", "Mobile number should not be null", "[]");
		}

		// validating email
		if (Objects.nonNull(std.getEmail()) && !"".equals(std.getEmail())) {
			if ((std.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[a-zA-Z]{2,}"))) {
				Student st = stdRepo.findByEmail(std.getEmail());
				if (st != null) {
					return new Result("SMSE016", "Student already exists with the given email", "[]");
				}
			} else {
				return new Result("SMSE017", "Email should match with proper format like (abc@gmail.com)", "[]");
			}
		} else {
			return new Result("SMSE018", "Email should not be null", "[]");
		}

		return new Result("SMSI006", "OK", "[]");

	}

	public Result validUpdate(Student std) {

		// validating firstname
		if (Objects.nonNull(std.getFirstName()) && !"".equals(std.getFirstName())) {
			if (!(std.getFirstName().length() <= 50))
				return new Result("SMSE007", "Length of firstname should not exceed 50 characters", "[]");
		}

		// validating firstname
		if (Objects.nonNull(std.getLastName()) && !"".equals(std.getLastName())) {
			if (!(std.getLastName().length() <= 50))
				return new Result("SMSE009", "Length of lastname should not exceed 50 characters", "[]");
		}

		// validating gender
		if (Objects.nonNull(std.getGender()) && !"".equals(std.getGender())) {
			if (std.getGender().length() == 1) {
				if (!(std.getGender().matches("(?:[M|F|O])"))) {
					return new Result("SMSE010", "Provide proper gender[M|F|O]", "[]");
				}
			} else {
				return new Result("SMSE011", "Length of gender should be 1", "[]");
			}
		}

		// validating mobile
		if (Objects.nonNull(std.getMobile()) && !"".equals(std.getMobile())) {
			if ((std.getMobile().matches("[+]+[0-9]{2}+[-]+[0-9]{10}"))) {
				Student st = stdRepo.findByMobile(std.getMobile());
				if (st != null) {
					return new Result("SMSE013", "Student already exists with the given mobile", "[]");
				}
			} else
				return new Result("SMSE014",
						"PhoneNumber should match with proper format like (+[countrycode]-[10 digit mobile number]",
						"[]");
		}

		// validating email
		if (Objects.nonNull(std.getEmail()) && !"".equals(std.getEmail())) {
			if ((std.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[a-zA-Z]{2,}"))) {
				Student st = stdRepo.findByEmail(std.getEmail());
				if (st != null) {
					return new Result("SMSE016", "Student already exists with the given email", "[]");
				}
			} else {
				return new Result("SMSE017", "Email should match with proper format like (abc@gmail.com)", "[]");
			}
		}
		return new Result("SMSI006", "OK", "[]");

	}
}
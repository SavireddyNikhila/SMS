package com.zkteco.student.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class StudentDto {

	private String id;
	private String firstName;
	private String lastName;
	private String gender;
	private String branch;
	private Date dateOfBirth;
	private String mobile;
	private String email;
	private String address;
	private String profile;
	private String createdDate;
	private String updatedDate;
}

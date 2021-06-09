package project.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="candidates")
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {

	@Id
	@Column(name="user_id")
	private int userId;
	
	@Column(name="first_name")
	private String name;
	
	@Column(name="last_name")
	private String surName;
	
	@Column(name="national_id")
	private String nationalId;
	
	@Column(name="birth_of_day")
	private LocalDate birthOfDay;
	
	@OneToOne()
	@MapsId
	@JoinColumn(name="user_id")
	private User user;
}

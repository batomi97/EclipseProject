package project.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.hrms.core.entities.User;

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
	@NotBlank(message = "İsim boş olamaz!")
	@NotNull(message = "Lütfen isim giriniz!")
	private String name;
	
	@Column(name="last_name")
	@NotBlank(message = "Soyisim boş olamaz!")
	@NotNull(message = "Lütfen soyisim giriniz!")
	private String surName;
	
	@Column(name="national_id")
	@NotBlank(message = "Kimlik numarası boş olamaz!")
	@NotNull(message = "Lütfen kimlik numarası giriniz!")
	private String nationalId;
	
	@Column(name="birth_of_day")
	private LocalDate birthOfDay;
	
	@Valid
	@OneToOne()
	@MapsId
	@JoinColumn(name="user_id")
	private User user;
}

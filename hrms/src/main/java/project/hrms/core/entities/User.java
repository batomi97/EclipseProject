package project.hrms.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="email")
	@Email(message = "Email formatında yazınız!")
	@NotBlank(message = "Email boş olamaz!")
	@NotNull(message = "Lütfen email giriniz!")
	private String email;
	
	@Column(name="password")
	@NotBlank(message = "Şifre boş olamaz!")
	@NotNull(message = "Lütfen şifre giriniz!")
	private String password;
}

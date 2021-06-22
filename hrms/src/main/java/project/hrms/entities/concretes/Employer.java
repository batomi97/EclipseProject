package project.hrms.entities.concretes;

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
@Table(name="employers")
@AllArgsConstructor
@NoArgsConstructor
public class Employer {

	@Id
	@Column(name="user_id")
	private int userId;
	
	@Column(name="company_name")
	@NotBlank(message = "Şirket ismi boş olamaz!")
	@NotNull(message = "Lütfen şirket ismini giriniz!")
	private String companyName;
	
	@Column(name="web_adress")
	@NotBlank(message = "Web adresi boş olamaz!")
	@NotNull(message = "Lütfen web adresi giriniz!")
	private String webAdress;
	
	@Column(name="phone_number")
	@NotBlank(message = "Telefon no boş olamaz!")
	@NotNull(message = "Lütfen telefon no giriniz!")
	private String phoneNumber;
	
	@Valid
	@OneToOne()
	@MapsId
	@JoinColumn(name="user_id")
	private User user;
}

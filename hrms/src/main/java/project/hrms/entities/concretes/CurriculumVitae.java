package project.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="curriculum_vitaes")
public class CurriculumVitae {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="candidate_id")
	private int candidateId;
	
	@Column(name="language_id")
	private int languageId;
	
	@Column(name="schools_attended")
	@NotBlank(message = "Okuduğunuz okulu belirtiniz!")
	@NotNull
	private String schoolsAttended;
	
	@Column(name="year_of_attended")
	private LocalDate yearOfAttended;
	
	@Column(name="year_of_graduation")
	private LocalDate yearOfGraduation;
	
	@Column(name="former_workplace_name")
	@NotBlank(message = "Eski çalıştığınız firmayı belirtiniz!")
	@NotNull
	private String formerWorkplaceName;
	
	@Column(name="former_workplace_position")
	@NotBlank(message = "Eski çalıştığınız firmadaki pozisyonunuzu belirtiniz!")
	@NotNull
	private String formerWorkplacePosition;
	
	@Column(name="year_of_former_workplace")
	private LocalDate yearOfFormerWorkplace;
	
	@Column(name="photo_path")
	@NotBlank(message = "Fotoğrafınızı giriniz!")
	@NotNull
	private String photoPath;
	
	@Column(name="url_path")
	private String urlPath;
	
	@Column(name="github_adress")
	@NotBlank(message = "Github adresi belirtiniz!")
	@NotNull
	private String githubAdress;
	
	@Column(name="linkedin_adress")
	@NotBlank(message = "Linkedin hesabı belirtiniz!")
	@NotNull
	private String linkedinAdress;
	
	@Column(name="programmer_language")
	@NotBlank(message = "Bildiğiniz programlama dilini giriniz!")
	@NotNull
	private String programmerLanguage;
	
	@Column(name="description")
	@NotBlank(message = "Kendiniz hakkında kısa bir açıklamada bulununuz!")
	@NotNull
	private String description;
	

	
}

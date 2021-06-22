package project.hrms.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumVitaeDto {

	private int id;
	private String candidateFirstName;
	private String candidateLastName;
	private String candidateNatioalId;
	private LocalDate candidateBirthOfDay;
	private String languageName;
	private String schoolsAttended;
	private LocalDate yearOfAttended;
	private LocalDate yearOfGraduation;
	private String formerWorkplaceName;
	private String formerWorkplacePosition;
	private LocalDate yearOfFormerWorkplace;
	private String photoPath;
	private String urlPath;
	private String githubAdress;
	private String linkedinAdress;
	private String programmerLanguage;
	private String description;
}

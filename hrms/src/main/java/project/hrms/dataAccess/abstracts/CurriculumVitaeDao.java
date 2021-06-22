package project.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import project.hrms.entities.concretes.CurriculumVitae;
import project.hrms.entities.dtos.CurriculumVitaeDto;

public interface CurriculumVitaeDao extends JpaRepository<CurriculumVitae, Integer> {

	List<CurriculumVitae> getByCandidateIdEquals(String candidateId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("Update CurriculumVitae Set urlPath =:urlPath where id=:id")
	void updateImageUrlPath(@Param("urlPath") String urlPath, @Param("id") int id);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("Update CurriculumVitae Set schoolsAttended= :schoolsAttended where id= :id and yearOfGraduation= null")
	void updateNotGraduation(@Param("schoolsAttended") String schoolsAttended, @Param("id") int id);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("Update CurriculumVitae Set formerWorkplaceName= :formerWorkplaceName where id= :id and yearOfFormerWorkplace= null")
	void updateContinueFormerWorkPlace(@Param("formerWorkplaceName") String formerWorkplaceName, @Param("id") int id);
	
	@Query("Select new project.hrms.entities.dtos.CurriculumVitaeDto(cv.id, c.name, c.surName, c.nationalId, c.birthOfDay, l.name, cv.schoolsAttended, cv.yearOfAttended,"
			+ "cv.yearOfGraduation, cv.formerWorkplaceName, cv.formerWorkplacePosition, cv.yearOfFormerWorkplace, cv.photoPath, cv.urlPath, cv.githubAdress,"
			+ "cv.linkedinAdress, cv.programmerLanguage, cv.description) From CurriculumVitae cv "
			+ "Inner Join Language l on l.id = cv.languageId "
			+ "Inner Join Candidate c on c.userId = cv.candidateId")
	List<CurriculumVitaeDto> getCurriculumVitaeDetails();
	
	List<CurriculumVitae> getByOrderByYearOfGraduationDesc();
	List<CurriculumVitae> getByOrderByYearOfFormerWorkplaceDesc();
}

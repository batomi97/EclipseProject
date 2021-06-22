package project.hrms.dataAccess.abstracts;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import project.hrms.entities.concretes.ActivationEmployer;
import project.hrms.entities.dtos.ActivationEmployerDto;

public interface ActivationEmployerDao extends JpaRepository<ActivationEmployer, Integer> {

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("Update ActivationCode Set isConfirmed = true, dateConfirm=:date where id=:id")	
	void updateDateConfirm(@Param("id") int id, @Param("date") LocalDate date);
	
	@Query("Select new project.hrms.entities.dtos.ActivationEmployerDto(ac.id, ac.code, ac.isConfirmed, ac.dateConfirm, sp.firstName, sp.lastName, e.companyName, e.webAdress) From ActivationEmployer ae "
			+ "Inner Join ae.activationCode ac "
			+ "Inner Join SystemPersonnel sp on sp.userId = ae.systemPersonnelId "
			+ "Inner Join Employer e on e.userId = ae.employerId")
	List<ActivationEmployerDto> getActivationEmployerDetails();
	
}

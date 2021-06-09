package project.hrms.dataAccess.abstracts;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import project.hrms.entities.concretes.ActivationCandidate;

public interface ActivationCandidateDao extends JpaRepository<ActivationCandidate, Integer> {

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("Update ActivationCode Set isConfirmed = true, dateConfirm=:date where id=:id")	
	void updateDateConfirm(@Param("id") int id, @Param("date") LocalDate date);
}

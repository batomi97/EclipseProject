package project.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import project.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {

	List<JobAdvertisement> getByIsActivatedIsTrue();
	List<JobAdvertisement> getByIsActivatedIsTrueOrderByReleaseDateDesc();
	List<JobAdvertisement> getByIsActivatedIsTrueOrderByReleaseDateAsc();
	List<JobAdvertisement> getByCompanyNameAndIsActivatedIsTrue(String companyName);
	List<JobAdvertisement> getBySalaryBetween(double minSalary, double maxSalary);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("Update JobAdvertisement Set isActivated = :isActivate where id = :id")	
	void updateIsActivatedFromEmployer(@Param("isActivate") boolean isActivate ,@Param("id") int id);
}

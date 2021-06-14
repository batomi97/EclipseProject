package project.hrms.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.Employer;

public interface EmployerDao extends JpaRepository<Employer, Integer> {

	Employer getByUser_Email(String email);
	Employer getByUser_EmailContains(String email);
	
	Employer getByUserIdEquals(int employerId);
}

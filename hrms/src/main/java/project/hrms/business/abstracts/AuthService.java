package project.hrms.business.abstracts;


import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.Candidate;
import project.hrms.entities.concretes.Employer;

public interface AuthService {

	Result registerCandidate(Candidate candidate, String passwordAgain);
	Result registerEmployer(Employer employer, String passwordAgain);
	Result login(Candidate candidate);
	
	Candidate getByNationalId(String nationalId);
	Candidate getByUser_EmailCandidateContains(String email);
	Employer getByUser_EmailEmployerContains(String email);
	Employer getByEmail(String email);
}

package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.ActivationCandidate;
import project.hrms.entities.concretes.Candidate;

public interface ActivationCandidateService {

	DataResult<List<ActivationCandidate>> getAll();
	boolean createActivationCode(int userId, Candidate candidate);
	Result checkActivationCode(String activationCode, int activationId);
	
}

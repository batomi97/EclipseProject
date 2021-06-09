package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.ActivationEmployer;
import project.hrms.entities.concretes.Employer;
import project.hrms.entities.concretes.SystemPersonnel;

public interface ActivationEmployerService {

	DataResult<List<ActivationEmployer>> getAll();
	boolean createActivationCode(int userId, Employer employer);
	Result checkActivationCode(String activationCode, int activationId);
	Result checkActivationCodeFromSystemPersonnel(int activationId, int personnelId);
	
	SystemPersonnel getByUserId(int userId);
}

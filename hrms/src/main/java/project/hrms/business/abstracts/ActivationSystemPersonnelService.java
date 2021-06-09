package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.ActivationSystemPersonnel;

public interface ActivationSystemPersonnelService {

	DataResult<List<ActivationSystemPersonnel>> getAll();
	Result add(ActivationSystemPersonnel activationSystemPersonnel);
}

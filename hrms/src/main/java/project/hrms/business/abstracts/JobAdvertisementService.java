package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementService {

	DataResult<List<JobAdvertisement>> getAll();
	Result add(JobAdvertisement jobAdvertisement);
	
	DataResult<List<JobAdvertisement>> getByIsActivatedIsTrue();
	DataResult<List<JobAdvertisement>> getByIsActivatedIsTrueOrderByReleaseDateDescOrAsc(String descOrAsc);
	DataResult<List<JobAdvertisement>> getByCompanyNameAndIsActivatedIsTrue(String companyName);
	DataResult<List<JobAdvertisement>> getBySalaryBetween(double minSalary, double maxSalary);
	Result updateIsActivatedFromEmployer(boolean isActivate, int id, int employerId);
}

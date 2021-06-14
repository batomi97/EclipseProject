package project.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.JobAdvertisementService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorDataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataAccess.abstracts.EmployerDao;
import project.hrms.dataAccess.abstracts.JobAdvertisementDao;
import project.hrms.entities.concretes.Employer;
import project.hrms.entities.concretes.JobAdvertisement;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

	@Autowired
	private JobAdvertisementDao jobAdvertisementDao;
	
	@Autowired
	private EmployerDao employerDao;
	
	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(), "Data listelendi.");
	}

	@Override
	public Result add(JobAdvertisement jobAdvertisement) {
		jobAdvertisement.setReleaseDate(LocalDate.now());
		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult("İş ilanı eklendi.");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByIsActivatedIsTrue() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByIsActivatedIsTrue());
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByIsActivatedIsTrueOrderByReleaseDateDescOrAsc(String descOrAsc) {
		if(descOrAsc.equals("desc")) {
			return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByIsActivatedIsTrueOrderByReleaseDateDesc());
		}else if(descOrAsc.equals("asc")) {
			return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByIsActivatedIsTrueOrderByReleaseDateAsc());
		}else {
			return new ErrorDataResult<List<JobAdvertisement>>("Lütfen asc veya desc olarak belirtiniz!");
		}
		
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByCompanyNameAndIsActivatedIsTrue(String companyName) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByCompanyNameAndIsActivatedIsTrue(companyName));
	}

	@Override
	public DataResult<List<JobAdvertisement>> getBySalaryBetween(double minSalary, double maxSalary) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getBySalaryBetween(minSalary, maxSalary));
	}

	@Override
	public Result updateIsActivatedFromEmployer(boolean isActivate,int id, int employerId) {
		int abc = 1;
		Employer emp = this.employerDao.getByUserIdEquals(employerId);
		if(this.employerDao.getByUserIdEquals(employerId) != null) {
			this.jobAdvertisementDao.updateIsActivatedFromEmployer(isActivate, id);
			return new SuccessResult("İş ilanının aktiflik durumu değiştirildi.");
		}else {
			return new ErrorResult("Bu işlemi sadece iş verenler yapabilir!");
		}
	}

}

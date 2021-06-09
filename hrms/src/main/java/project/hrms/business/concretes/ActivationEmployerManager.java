package project.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.ActivationEmployerService;
import project.hrms.core.adapters.EmailVerificationService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataAccess.abstracts.ActivationEmployerDao;
import project.hrms.dataAccess.abstracts.SystemPersonnelDao;
import project.hrms.entities.concretes.ActivationCode;
import project.hrms.entities.concretes.ActivationEmployer;
import project.hrms.entities.concretes.Employer;
import project.hrms.entities.concretes.SystemPersonnel;

@Service
public class ActivationEmployerManager implements ActivationEmployerService {

	@Autowired
	private ActivationEmployerDao activationEmployerDao;
	
	@Autowired
	private EmailVerificationService emailVerificationService;
	
	@Autowired
	private SystemPersonnelDao systemPersonnelDao;
	
	@Override
	public DataResult<List<ActivationEmployer>> getAll() {
		return new SuccessDataResult<List<ActivationEmployer>>(this.activationEmployerDao.findAll());
	}

	@Override
	public boolean createActivationCode(int userId, Employer employer) {
		ActivationEmployer activationEmployer = new ActivationEmployer();
		ActivationCode activationCode = new ActivationCode();
		activationCode.setCode(codeGenerator());
		activationCode.setConfirmed(false);
		activationCode.setDateConfirm(null);
		activationEmployer.setEmployerId(userId);
		activationEmployer.setActivationCode(activationCode);
		this.activationEmployerDao.save(activationEmployer);
		if(this.emailVerificationService.isSendVerificationEmail(employer.getUser().getEmail())) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Result checkActivationCode(String activationCode, int activationId) {
		if(isSuccessCode(activationCode, activationId)) {
			return new SuccessResult("Aktivasyon kodu doğrulandı.");
		}else {
			return new ErrorResult("Aktivasyon kodu doğrulanamadı.");
		}
	}
	
	@Override
	public Result checkActivationCodeFromSystemPersonnel(int activationId, int personnelId) {
		if(getByUserId(personnelId) == null) {
			return new ErrorResult("Yalnızca sistem kullanıcıları bu işlemi yapabilir");
		}else {
			this.activationEmployerDao.updateDateConfirm(activationId, LocalDate.now());
			return new SuccessResult("Kullanıcı aktif edildi.");
		}
	}
	
	public boolean isSuccessCode(String activationCode, int activationId) {
		boolean isActivationCode = false;
		for(ActivationEmployer checkActivationCode:this.activationEmployerDao.findAll()) {
			if( checkActivationCode.getActivationCode().getCode().equals(activationCode) && checkActivationCode.getActivationCode().getId() == activationId) {
				this.activationEmployerDao.updateDateConfirm(activationId, LocalDate.now());
				isActivationCode = true;
			}
		}
		return isActivationCode;
	}
	
	@Override
	public SystemPersonnel getByUserId(int userId) {
		return this.systemPersonnelDao.getByUserId(userId);
	}
	
	private String codeGenerator() {
	    int upperBound = 9999, lowerBound = 1000;
		int randomCode = lowerBound + (int) (Math.random()*(upperBound - lowerBound)+1);
		String code = String.valueOf(randomCode);
		return code;
	}

}

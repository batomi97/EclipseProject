package project.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.ActivationCandidateService;
import project.hrms.core.adapters.EmailVerificationService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataAccess.abstracts.ActivationCandidateDao;
import project.hrms.entities.concretes.ActivationCandidate;
import project.hrms.entities.concretes.ActivationCode;
import project.hrms.entities.concretes.Candidate;

@Service
public class ActivationCandidateManager implements ActivationCandidateService {

	@Autowired
	private ActivationCandidateDao activationCandidateDao;
	
	@Autowired
	private EmailVerificationService emailVerificationService;
	
	@Override
	public DataResult<List<ActivationCandidate>> getAll() {
		return new SuccessDataResult<List<ActivationCandidate>>(this.activationCandidateDao.findAll(), "Datalar listelendi.");
	}

	@Override
	public boolean createActivationCode(int userId, Candidate candidate) {
		ActivationCandidate activationCandidate = new ActivationCandidate();
		ActivationCode activationCode = new ActivationCode();
		activationCode.setCode(codeGenerator());
		activationCode.setConfirmed(false);
		activationCode.setDateConfirm(null);
		activationCandidate.setCandidateId(userId);
		activationCandidate.setActivationCode(activationCode);
		this.activationCandidateDao.save(activationCandidate);
		if(this.emailVerificationService.isSendVerificationEmail(candidate.getUser().getEmail())) {
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
	
	public boolean isSuccessCode(String activationCode, int activationId) {
		boolean isActivationCode = false;
		for(ActivationCandidate checkActivationCode:this.activationCandidateDao.findAll()) {
			if( checkActivationCode.getActivationCode().getCode().equals(activationCode) && checkActivationCode.getActivationCode().getId() == activationId) {
				this.activationCandidateDao.updateDateConfirm(activationId, LocalDate.now());
				isActivationCode = true;
			}
		}
		return isActivationCode;
	}	
	
	private String codeGenerator() {
	    int upperBound = 9999, lowerBound = 1000;
		int randomCode = lowerBound + (int) (Math.random()*(upperBound - lowerBound)+1);
		String code = String.valueOf(randomCode);
		return code;
	}

}

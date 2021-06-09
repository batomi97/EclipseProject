package project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.ActivationCandidateService;
import project.hrms.business.abstracts.ActivationEmployerService;
import project.hrms.business.abstracts.AuthService;
import project.hrms.business.abstracts.CandidateService;
import project.hrms.business.abstracts.EmployerService;
import project.hrms.core.adapters.CandidateCheckService;
import project.hrms.core.utilities.results.ErrorResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataAccess.abstracts.CandidateDao;
import project.hrms.dataAccess.abstracts.EmployerDao;
import project.hrms.entities.concretes.Candidate;
import project.hrms.entities.concretes.Employer;

@Service
public class AuthManager implements AuthService {

	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private CandidateCheckService candidateCheckService;
	
	@Autowired
	private CandidateDao candidateDao;
	
	@Autowired
	private EmployerDao employerDao;
	
	@Autowired
	private EmployerService employerService;
	
	@Autowired
	private ActivationCandidateService activationCandidateService;
	
	@Autowired
	private ActivationEmployerService activationEmployerService;
	
	@Override
	public Result registerCandidate(Candidate candidate, String passwordAgain) {
		if(!isUserEmptyInformationCheck(candidate)) {
			return new ErrorResult("Lütfen istenilen bilgilerde boş alan girmeyiniz!");
		}else if(!isUserMernisCheck(candidate)) { 
			return new ErrorResult("Lütfen gerçek kişi bilgilerini giriniz!");
		}else if(!isTcNumberCheck(candidate)) {
			return new ErrorResult("Girilen TC no sistemde kayıtlıdır!");
		}else if(!candidate.getUser().getPassword().equals(passwordAgain)) {
			return new ErrorResult("Lütfen şifre tekrarında aynısını giriniz!");
		}else if(getByUser_EmailCandidateContains(candidate.getUser().getEmail())!= null) {
			return new ErrorResult("Bu email kullanılmaktadır!");
		}
		else {	
			this.candidateService.add(candidate);
			
			if(!this.activationCandidateService.createActivationCode(getByNationalId(candidate.getNationalId()).getUserId(),candidate)) {
				return new ErrorResult("Emaile doğrulama kodu gönderilemedi.");
			}else {
				return new SuccessResult("Emaile doğrulama kodu gönderildi.");
			}
		}
	
	}
	
	@Override
	public Result registerEmployer(Employer employer, String passwordAgain) {
		if(isWebAdressAndEmailCheck(employer)) {
			return new ErrorResult("Lütfen şirket domainli mail giriniz!");
		}else if(!isCompanyNameCheck(employer)) {
			return new ErrorResult("Lütfen şirket adını giriniz!");
		}else if(!isPhoneNumberCheck(employer)) {
			return new ErrorResult("Lütfen telefon numarasını giriniz!");
		}else if(!isWebAdressCheck(employer)) {
			return new ErrorResult("Lütfen web sitesini giriniz!");
		}else if(!isPasswordEmployerCheck(employer)) {
			return new ErrorResult("Lütfen şifrenizi giriniz!");
		}else if(getByUser_EmailEmployerContains(employer.getUser().getEmail())!= null) {
			return new ErrorResult("Bu email kullanılmaktadır!");
		}else if(!employer.getUser().getPassword().equals(passwordAgain)) {
			return new ErrorResult("Lütfen şifre tekrarında aynısını giriniz!");
		}else {
			this.employerService.add(employer);
			
			if(!this.activationEmployerService.createActivationCode(getByEmail(employer.getUser().getEmail()).getUserId(),employer)) {
				return new ErrorResult("Emaile doğrulama kodu gönderilemedi.");
			}else {
				return new SuccessResult("Emaile doğrulama kodu gönderildi.");
			}
		}
	}

	@Override
	public Result login(Candidate candidate) {
		return null;
	}
	
	@Override
	public Candidate getByNationalId(String nationalId) {
		return this.candidateDao.getByNationalId(nationalId);
	}

	@Override
	public Candidate getByUser_EmailCandidateContains(String email) {
		return this.candidateDao.getByUser_EmailContains(email);
	}
	
	@Override
	public Employer getByUser_EmailEmployerContains(String email) {
		return this.employerDao.getByUser_EmailContains(email);
	}
	
	@Override
	public Employer getByEmail(String email) {
		return this.employerDao.getByUser_Email(email);
	}

	public boolean isUserEmptyInformationCheck(Candidate candidate) {
		if(isNameCheck(candidate) && isSurNameCheck(candidate) && isNationalIdCheck(candidate) && isBirthOfDayCheck(candidate)
			&& isEmailCheck(candidate) && isPasswordCheck(candidate)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isUserMernisCheck(Candidate candidate) {
		if(candidateCheckService.checkIfRealPerson(candidate)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isEmailCheck(Candidate candidate) {
		if(candidate.getUser().getEmail().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isPasswordCheck(Candidate candidate) {
		if(candidate.getUser().getPassword().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isTcNumberCheck(Candidate candidate) {
		boolean result = true;
		for(Candidate checkCandidate:this.candidateDao.findAll()) {
			if(checkCandidate.getNationalId().equals(candidate.getNationalId())) {
				result = false;
			}
		}
		
		return result;
	}

	public boolean isNameCheck(Candidate candidate) {
		if(candidate.getName().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isSurNameCheck(Candidate candidate) {
		if(candidate.getSurName().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isNationalIdCheck(Candidate candidate) {
		if(candidate.getNationalId().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isBirthOfDayCheck(Candidate candidate) {
		if(candidate.getBirthOfDay() == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isCompanyNameCheck(Employer employer) {
		if(employer.getCompanyName().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isWebAdressAndEmailCheck(Employer employer) {
		if(employer.getUser().getEmail().contains(employer.getWebAdress())) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isPhoneNumberCheck(Employer employer) {
		if(employer.getPhoneNumber().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isWebAdressCheck(Employer employer) {
		if(employer.getWebAdress().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isPasswordEmployerCheck(Employer employer) {
		if(employer.getUser().getPassword().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}

}

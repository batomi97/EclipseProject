package project.hrms.core.adapters;

import org.springframework.stereotype.Service;

@Service
public class EmailVerification implements EmailVerificationService {

	@Override
	public boolean isSendVerificationEmail(String email) {
		return true;
	}

	@Override
	public boolean isSuccesVerificationEmail(boolean success) {
		if(success) {
			return true;
		}else {
			return false;
		}
	}

}

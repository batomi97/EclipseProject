package project.hrms.core.adapters;

public interface EmailVerificationService {

	boolean isSendVerificationEmail(String email);
	boolean isSuccesVerificationEmail(boolean success);
}

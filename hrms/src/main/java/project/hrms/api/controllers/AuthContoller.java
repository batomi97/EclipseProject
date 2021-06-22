package project.hrms.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.AuthService;
import project.hrms.core.utilities.results.ErrorDataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.Candidate;
import project.hrms.entities.concretes.Employer;

@RestController
@RequestMapping("/api/auth")
public class AuthContoller {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/registercandidate")
	public ResponseEntity<?> registerCandidate(@Valid @RequestBody Candidate candidate, String passwordAgain) {
		return ResponseEntity.ok(this.authService.registerCandidate(candidate, passwordAgain));
	}

	@PostMapping("/registeremployer")
	public ResponseEntity<?> registerEmployer(@Valid @RequestBody Employer employer, String passwordAgain) {
		return ResponseEntity.ok(this.authService.registerEmployer(employer,passwordAgain));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları; ");
		return errors;
	}
}

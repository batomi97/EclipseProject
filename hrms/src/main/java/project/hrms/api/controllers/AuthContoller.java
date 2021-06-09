package project.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.AuthService;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.Candidate;
import project.hrms.entities.concretes.Employer;

@RestController
@RequestMapping("/api/auth")
public class AuthContoller {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/registercandidate")
	public Result registerCandidate(@RequestBody Candidate candidate, String passwordAgain) {
		return this.authService.registerCandidate(candidate, passwordAgain);
	}

	@PostMapping("/registeremployer")
	public Result registerEmployer(@RequestBody Employer employer, String passwordAgain) {
		return this.authService.registerEmployer(employer,passwordAgain);
	}
}

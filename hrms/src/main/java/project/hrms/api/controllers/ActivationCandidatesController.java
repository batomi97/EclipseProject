package project.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.ActivationCandidateService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.ActivationCandidate;

@RestController
@RequestMapping("/api/activationcandidates")
public class ActivationCandidatesController {

	@Autowired
	private ActivationCandidateService activationCandidateService;
	
	@GetMapping("/getall")
	public DataResult<List<ActivationCandidate>> getAll() {
		return this.activationCandidateService.getAll();
	}
	
	@PostMapping("/checkactivationcode")
	public Result checkActivationCode(@RequestBody String activationcode, int activationId) {
		return this.activationCandidateService.checkActivationCode(activationcode, activationId);
	}
}

package project.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.ActivationEmployerService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.ActivationEmployer;

@RestController
@RequestMapping("/api/activationemployers")
public class ActivationEmployersController {

	@Autowired
	private ActivationEmployerService activationEmployerService;
	
	@GetMapping("/getall")
	public DataResult<List<ActivationEmployer>> getAll() {
		return this.activationEmployerService.getAll();
	}
	
	@PostMapping("/checkactivationcode")
	public Result checkActivationCode(@RequestBody String activationcode, int activationId) {
		return this.activationEmployerService.checkActivationCode(activationcode, activationId);
	}
	
	@PostMapping("/checkactivationcodesystempersonnel")
	public Result checkActivationCodeFromSystemPersonnel(@RequestBody int activationId, int personnelId) {
		return this.activationEmployerService.checkActivationCodeFromSystemPersonnel(activationId, personnelId);
	}
}

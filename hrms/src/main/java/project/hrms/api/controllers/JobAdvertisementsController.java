package project.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.JobAdvertisementService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.JobAdvertisement;

@RestController
@RequestMapping("/api/jobadvertisements")
public class JobAdvertisementsController {
	
	@Autowired
	private JobAdvertisementService jobAdvertisementService;

	@GetMapping("/getall")
	public DataResult<List<JobAdvertisement>> getAll() {
		return this.jobAdvertisementService.getAll();
	}
	
	@GetMapping("/getbyisactivatedistrue")
	public DataResult<List<JobAdvertisement>> getByIsActivatedIsTrue(){
		return this.jobAdvertisementService.getByIsActivatedIsTrue();
	}
	
	@GetMapping("/getbyisactivatedistrueorderbyreleasedatedescorasc")
	public DataResult<List<JobAdvertisement>> getByIsActivatedIsTrueOrderByReleaseDateDesc(@RequestParam String descOrAsc) {
		return this.jobAdvertisementService.getByIsActivatedIsTrueOrderByReleaseDateDescOrAsc(descOrAsc);
	}
	
	@GetMapping("/getbycompanynameandisactivatedistrue")
	public DataResult<List<JobAdvertisement>> getByCompanyNameAndIsActivatedIsTrue(@RequestParam String companyName) {
		return this.jobAdvertisementService.getByCompanyNameAndIsActivatedIsTrue(companyName);
	}
	
	@GetMapping("/getbysalarybetween")
	public DataResult<List<JobAdvertisement>> getBySalaryBetween(@RequestParam double minSalary,@RequestParam double maxSalary) {
		return this.jobAdvertisementService.getBySalaryBetween(minSalary, maxSalary);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobAdvertisement jobAdvertisement) {
		return this.jobAdvertisementService.add(jobAdvertisement);
	}
	
	@PostMapping("/updateisactivatedfromemployer")
	public Result updateIsActivatedFromEmployer(boolean isActivate, int id, int employerId) {
		return this.jobAdvertisementService.updateIsActivatedFromEmployer(isActivate, id, employerId);
	}
}

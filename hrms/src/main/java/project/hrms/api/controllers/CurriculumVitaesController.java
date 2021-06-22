package project.hrms.api.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.CurriculumVitaeService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.ErrorDataResult;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.entities.concretes.CurriculumVitae;
import project.hrms.entities.dtos.CurriculumVitaeDto;

@RestController
@RequestMapping("/api/curriculumvitaes")
public class CurriculumVitaesController {

	@Autowired
	private CurriculumVitaeService curriculumVitaeService;
	
	@GetMapping("/getall")
	public DataResult<List<CurriculumVitae>> getAll() {
		return this.curriculumVitaeService.getAll();
	}
	
	@GetMapping("/getbycandidatenatioanlidequals")
	public DataResult<List<CurriculumVitae>> getByCandidateIdEquals(@RequestParam String candidateId) {
		return this.curriculumVitaeService.getByCandidateIdEquals(candidateId);
	}
	
	@GetMapping("/getcurriculumvitaedetails")
	public DataResult<List<CurriculumVitaeDto>> getCurriculumVitaeDetails() {
		return this.curriculumVitaeService.getCurriculumVitaeDetails();
	}
	
	@GetMapping("/getbyorderbyyearofgraduationdesc")
	public DataResult<List<CurriculumVitae>> getByOrderByYearOfGraduationDesc() {
		return this.curriculumVitaeService.getByOrderByYearOfGraduationDesc();
	}
	
	@GetMapping("/getbyorderbyyearofformerworkplacedesc")
	public DataResult<List<CurriculumVitae>> getByOrderByYearOfFormerWorkplaceDesc() {
		return this.curriculumVitaeService.getByOrderByYearOfFormerWorkplaceDesc();
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody CurriculumVitae curriculumVitae) throws IOException {
		return ResponseEntity.ok(this.curriculumVitaeService.add(curriculumVitae));
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

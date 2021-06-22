package project.hrms.business.abstracts;

import java.io.IOException;
import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.CurriculumVitae;
import project.hrms.entities.dtos.CurriculumVitaeDto;

public interface CurriculumVitaeService {

	DataResult<List<CurriculumVitae>> getAll();
	DataResult<List<CurriculumVitae>> getByCandidateIdEquals(String candidateId);
	Result add(CurriculumVitae curriculumVitae) throws IOException;
	
	DataResult<List<CurriculumVitaeDto>> getCurriculumVitaeDetails();
	DataResult<List<CurriculumVitae>> getByOrderByYearOfGraduationDesc();
	DataResult<List<CurriculumVitae>> getByOrderByYearOfFormerWorkplaceDesc();
	
}

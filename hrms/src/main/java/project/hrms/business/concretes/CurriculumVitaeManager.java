package project.hrms.business.concretes;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.CurriculumVitaeService;
import project.hrms.core.adapters.imageManagement.ImageServiceAdapter;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.core.utilities.results.SuccessDataResult;
import project.hrms.core.utilities.results.SuccessResult;
import project.hrms.dataAccess.abstracts.CurriculumVitaeDao;
import project.hrms.entities.concretes.CurriculumVitae;
import project.hrms.entities.dtos.CurriculumVitaeDto;

@Service
public class CurriculumVitaeManager implements CurriculumVitaeService {

	@Autowired
	private CurriculumVitaeDao curriculumVitaeDao;
	
	@Autowired
	private ImageServiceAdapter imageServiceAdapter;
	
	@Override
	public DataResult<List<CurriculumVitae>> getAll() {
		return new SuccessDataResult<List<CurriculumVitae>>(this.curriculumVitaeDao.findAll(), "Datalar listelendi.");
	}

	@Override
	public DataResult<List<CurriculumVitae>> getByCandidateIdEquals(String candidateId) {
		return new SuccessDataResult<List<CurriculumVitae>>(this.curriculumVitaeDao.getByCandidateIdEquals(candidateId));
	}

	@Override
	public Result add(CurriculumVitae curriculumVitae) throws IOException {
		this.curriculumVitaeDao.save(curriculumVitae);
		this.curriculumVitaeDao.updateNotGraduation(curriculumVitae.getSchoolsAttended() + " - Devam ediyor", curriculumVitae.getId());
		this.curriculumVitaeDao.updateContinueFormerWorkPlace(curriculumVitae.getFormerWorkplaceName() + " - Devam ediyor", curriculumVitae.getId());
		updateImageUrlPath(this.imageServiceAdapter.upload(curriculumVitae.getPhotoPath()), curriculumVitae.getId());
		return new SuccessResult("CV eklendi.");
	}

	@Override
	public DataResult<List<CurriculumVitaeDto>> getCurriculumVitaeDetails() {
		return new SuccessDataResult<List<CurriculumVitaeDto>>(this.curriculumVitaeDao.getCurriculumVitaeDetails(), "Datalar listelendi.");
	}
	
	@Override
	public DataResult<List<CurriculumVitae>> getByOrderByYearOfGraduationDesc() {
		return new SuccessDataResult<List<CurriculumVitae>>(this.curriculumVitaeDao.getByOrderByYearOfGraduationDesc());
	}
	
	@Override
	public DataResult<List<CurriculumVitae>> getByOrderByYearOfFormerWorkplaceDesc() {
		return new SuccessDataResult<List<CurriculumVitae>>(this.curriculumVitaeDao.getByOrderByYearOfFormerWorkplaceDesc());
	}
	
	public void updateImageUrlPath(String urlPath, int id) {
		this.curriculumVitaeDao.updateImageUrlPath(urlPath, id);
	}

}

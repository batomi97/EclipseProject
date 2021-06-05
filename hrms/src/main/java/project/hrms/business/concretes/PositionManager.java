package project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.hrms.business.abstracts.PositionService;
import project.hrms.dataAccess.abstracts.PositionDao;
import project.hrms.entities.concretes.Position;

@Service
public class PositionManager implements PositionService {

	private PositionDao positionDao;
	
	@Autowired
	public PositionManager(PositionDao positionDao ) {
		super();
		this.positionDao = positionDao;
	}
	
	@Override
	public List<Position> getAll() {
		return this.positionDao.findAll();
	}

}

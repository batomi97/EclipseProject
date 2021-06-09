package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.Position;

public interface PositionService {

	DataResult<List<Position>> getAll();
	Result add(Position position);
	Position getByPositionNameContains(String positionName);
}

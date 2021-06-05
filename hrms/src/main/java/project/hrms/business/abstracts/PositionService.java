package project.hrms.business.abstracts;

import java.util.List;

import project.hrms.entities.concretes.Position;

public interface PositionService {

	List<Position> getAll();
}

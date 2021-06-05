package project.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.Position;

public interface PositionDao extends JpaRepository<Position, Integer> {

}

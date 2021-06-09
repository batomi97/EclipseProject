package project.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.ActivationSystemPersonnel;

public interface ActivationSystemPersonnelDao extends JpaRepository<ActivationSystemPersonnel, Integer> {

}

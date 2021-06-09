package project.hrms.core.adapters;


import project.hrms.entities.concretes.Candidate;

public interface CandidateCheckService {

	boolean checkIfRealPerson(Candidate candidate);
}

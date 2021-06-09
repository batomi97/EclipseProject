package project.hrms.core.adapters;


import org.springframework.stereotype.Service;

import project.hrms.entities.concretes.Candidate;

@Service
public class MernisServiceAdapter implements CandidateCheckService {
	
	@Override
	public boolean checkIfRealPerson(Candidate candidate) {
		
		//Burası mernise göre uyarlanacak.
		if(candidate.getName().isEmpty() && candidate.getSurName().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}

}

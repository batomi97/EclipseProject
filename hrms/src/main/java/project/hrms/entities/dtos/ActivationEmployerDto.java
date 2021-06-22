package project.hrms.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivationEmployerDto {
	private int id;
	private String code;
	private boolean isConfirmed;
	private LocalDate dateConfirm;
	private String personnelFirstName;
	private String personnelLastName;
	private String companyName;
	private String webAdress;
}

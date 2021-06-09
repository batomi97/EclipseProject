package project.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="activation_employers")
@AllArgsConstructor
@NoArgsConstructor
public class ActivationEmployer {

	@Id
	@Column(name="activation_id")
	private int activationId;
	
	@Column(name="system_personnel_id")
	private int systemPersonnelId;
	
	@Column(name="employer_id")
	private int employerId;
	
	@OneToOne()
	@MapsId
	@JoinColumn(name="activation_id")
	private ActivationCode activationCode;
}

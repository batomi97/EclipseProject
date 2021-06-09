package project.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="activation_candidates")
@AllArgsConstructor
@NoArgsConstructor
public class ActivationCandidate {

	@Id
	@Column(name="activation_id")
	private int activationId;
	
	
	@Column(name="candidate_id")
	private int candidateId;
	
	@OneToOne()
	@MapsId
	@JoinColumn(name="activation_id")
	private ActivationCode activationCode;
	
}

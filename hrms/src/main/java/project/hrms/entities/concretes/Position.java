package project.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="Positions")
@Entity
public class Position {

	@Id
	@GeneratedValue
	@Column(name="Id")
	private int id;
	
	@Column(name="PositionName")
	private String positionName;
	
	public Position() {
		
	}

	public Position(int id, String positionName) {
		super();
		this.id = id;
		this.positionName = positionName;
	}
}

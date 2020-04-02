package cmpe172.BloodDonation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hospital")
public class Hospital {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer hospital_id;

	@Column
	private String name;

	@Column 
	private String location;

	@Override
	public String toString() {
		return "Hospital [id= " + hospital_id + ", name=" + name + ", location=" + location + "]";
	}

	public Integer getHospital_id() {
		return hospital_id;
	}
	
	public void setHospital_id(Integer hospital_id) {
		this.hospital_id = hospital_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
}

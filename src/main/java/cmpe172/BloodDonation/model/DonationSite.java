package cmpe172.BloodDonation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity      
@Table(name = "site")
public class DonationSite {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer site_id;

	@Column
	private String name;

	@Column 
	private String location;
	
	@Column
	private Integer capacity;

	@Override
	public String toString() {
		return "Donation site [id= " + site_id + ", name=" + name + ", location=" + location + ", capacity=" + capacity + "]";
	}

	public Integer getSite_id() {
		return site_id;
	}
	
	public void setSite_id(Integer site_id) {
		this.site_id = site_id;
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
	
	public Integer getCapacity() {
		return capacity;
	}
	
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
}

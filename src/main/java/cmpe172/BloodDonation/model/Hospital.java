package cmpe172.BloodDonation.model;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hospital")
public class Hospital {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column
	private String name;

	@Column 
	private String location;
	
	@Column
	private Integer capacity;
	
	//This is the foreign key mapping in Donation
	@OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
	private List<Donation> donations;	
	
	//This is the many-to-many mapping that represents the site_hospital relationship
	@ManyToMany(mappedBy = "hospitals")
	private List<DonationSite> sites;
	
	@Override
	public String toString() {
		return "Hospital [id= " + id + ", name=" + name + ", location=" + location + ", capacity=" + capacity + "]";
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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
		
	/**
	 * This returns the donation site_id that delivers to this hospital.
	 * @return the site_id of the donation site that delivers to this hospital.
	 * 
	 * NOTE: I use 'acquire' as opposed to 'get' to not return the site ids in the JSON - I do not know how to ignore them otherwise
	 */
	public Integer acquireSiteId() {
		//use set to get hospital_ids sorted
		return sites.get(0).getId();
	}

	public void setSites(List<DonationSite> sites) {
		this.sites = sites;
	}
	
	/**
	 * This returns the donation_ids of the donations that are present at this hospital.
	 * @return of donation_ids that are present at this hospital.
	 * 
	 * NOTE: I use 'acquire' as opposed to 'get' to not return the donation ids in the JSON - I do not know how to ignore them otherwise
	 */
	public Set<Integer> acquireDonations(){
		Set<Integer> donation_ids = new HashSet<>();
		for(Donation d : donations) {
			donation_ids.add(d.getId());
		}
		return donation_ids;
	}	

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}

}

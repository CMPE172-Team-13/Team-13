package cmpe172.BloodDonation.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity      
@Table(name = "site")
public class DonationSite {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column
	private String name;

	@Column 
	private String location;	
	
//	//This is from the foreign key mapping in Donation
//	@OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
//	private List<Donation> donations;
		
	//here is the many-to-many mapping representing the site_hospital relationship
	@ManyToMany(cascade = CascadeType.ALL)//, orphanRemoval = true)
	@JoinTable(name = "site_hospital", 
			joinColumns = {@JoinColumn(name = "site_id")},
			inverseJoinColumns = {@JoinColumn(name = "hospital_id")})
	private List<Hospital> hospitals;
	
	//here is the many-to-many mapping representing the donation_site relationship
	@ManyToMany(cascade = CascadeType.ALL)//, orphanRemoval = true)
	@JoinTable(name = "donation_site", 
			joinColumns = {@JoinColumn(name = "site_id")},
			inverseJoinColumns = {@JoinColumn(name = "donation_id")})
	private List<Donation> donationsViaSite;
	
	@Override
	public String toString() {
		return "Donation site [id= " + id + ", name=" + name + ", location=" + location + "]";
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
	
	/**
	 * This returns Set<Integer> of hospital_ids of all the hospitals that this donation site delivers blood donations to.
	 * @return a set of all the hospitals that this donation site delivers blood donations to.
	 * 
	 * NOTE: I use 'acquire' as opposed to 'get' to not return the hospital ids in the JSON - I do not know how to ignore them otherwise
	 */
	public Set<Integer> acquireHospitalIds() {
		//use set to get hospital_ids sorted
		Set<Integer> hospital_ids = new HashSet<>();
		for(Hospital h : hospitals) {
			hospital_ids.add(h.getId());
		}
		return hospital_ids;
	}

	public void setHospitals(List<Hospital> hospitals) {
		this.hospitals = hospitals;
	}
		
	/**
	 * This returns a Set<Integer> of the donation_ids of all donations that came out of this donation site.
	 * @return a set of the donation_ids of all donations that came out of this donation site.
	 * 
	 * NOTE: I use 'acquire' as opposed to 'get' to not return the donation ids in the JSON - I do not know how to ignore them otherwise
	 */
	public Set<Integer> acquireDonationsViaSite() {
		//use set to get hospital_ids sorted
		Set<Integer> donation_ids = new HashSet<>();
		for(Donation d : donationsViaSite) {
			donation_ids.add(d.getId());
		}
		return donation_ids;
	}

	public void setDonationsViaSite(List<Donation> donationsViaSite) {
		this.donationsViaSite = donationsViaSite;
	}
	
}

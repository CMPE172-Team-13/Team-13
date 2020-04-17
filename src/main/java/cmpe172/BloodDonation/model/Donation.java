package cmpe172.BloodDonation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "donation")
public class Donation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
		
	//This is the foreign key mapping with site
	@ManyToOne
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private DonationSite site;		
		
	//This is the foreign key mapping with hospital
	@ManyToOne
	@JoinColumn(name = "hospital_id", referencedColumnName = "id")
	private Hospital hospital;
	
	//here is the many-to-many mapping representing the donation_site relationship
	@ManyToMany(mappedBy = "donationsViaSite")
	private List<DonationSite> sites;
		
	@Column
	private String blood_type;

	@Column 
	private String donation_number;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date aDate;

	@Override
	public String toString() {
		return "Donation [id= " + id + "site_id=" + site.getId() + ", hospital_id=" + hospital.getId() + ", blood_type=" + blood_type + ", donation_number=" + donation_number + ", aDate=" + aDate + "]";
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSite(DonationSite site) {
		this.site = site;
	}
	
	public Integer getSiteId() {
		return site.getId();
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	
	public Integer getHospitalId() {
		return hospital.getId();
	}

 
	public String getBlood_type() {
		return blood_type;
	}	


	public void setBlood_type(String blood_type) {
		this.blood_type = blood_type;
	}
	
	public String getDonation_number() {
		return donation_number;
	}
	
	public void setDonation_number(String donation_number) {
		this.donation_number = donation_number;
	}


	public Date getaDate() {
		return aDate;
	}


	public void setaDate(Date aDate) {
		this.aDate = aDate;
	}
	
	//returns the site that is associated with this donation via a join on the donation_site table, 
	//but it is not required because we already have the site_id via the foreign key
//	public Integer getSiteViaDonation() {
//		return sites.get(0).getId();
//	}
}

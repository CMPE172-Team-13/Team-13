package cmpe172.BloodDonation.model;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//import java.sql.Date;		//did not work
import java.time.*;			//also does not work
//import java.util.Date;	//did not work

@Entity
@Table(name = "donation")
public class Donation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
		
	@ManyToOne
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private DonationSite site;
		
	@ManyToOne
	@JoinColumn(name = "hospital_id", referencedColumnName = "id")
	private Hospital hospital;

	@Column
	private String blood_type;

	@Column 
	private String donation_number;
	
	@Column
	//private java.sql.Date aDate;
	private LocalDate aDate;

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
	
//	public DonationSite getSite() {
//		return site;
//	}


	public void setSite(DonationSite site) {
		this.site = site;
	}
	
	public Integer getSiteId() {
		return site.getId();
	}

//	public Hospital getHospital() {
//		return hospital;
//	}


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


	public LocalDate getaDate() {
		return aDate;
	}


	public void setaDate(LocalDate aDate) {
		this.aDate = aDate;
	}
	
	

}

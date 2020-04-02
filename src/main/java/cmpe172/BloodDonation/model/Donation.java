package cmpe172.BloodDonation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import java.sql.Date;
import java.time.*;
import java.util.Date;

@Entity
@Table(name = "donation")
public class Donation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer donation_id;

	@Column
	private String blood_type;

	@Column 
	private String donation_number;
	
	@Column
	private LocalDate aDate;

	@Override
	public String toString() {
		return "Donation [id= " + donation_id + ", blood_type=" + blood_type + ", donation_number=" + donation_number + ", aDate=" + aDate + "]";
}

	public Integer getDonation_id() {
		return donation_id;
	}
	
	public void setDonation_id(Integer donation_id) {
		this.donation_id = donation_id;
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

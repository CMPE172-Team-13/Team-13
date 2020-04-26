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
@Table(name = "donation_site")
public class DonationToSite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "donation_id")
	private Integer donation_id;
	
	@Column(name = "site_id")
	private Integer site_id;
	
	@Override
	public String toString() {
		return "DonationToSiteInfo [id= " + id + " donation_id= " + donation_id + ", site_id= " + site_id + " ]";
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSiteId(int id) {
		this.site_id = id;//site.getId();
	}
	
	public Integer getSiteId() {
		//System.out.println("\nDEBUG MESSAGE from DonationToSite model: " + this + "\n");
		return this.site_id;
	}

	public void setDonationId(int id) {
		this.donation_id = id;
	}
	
	public Integer getDonationId() {
		return this.donation_id;
	}
	
}

package cmpe172.BloodDonation.dao;

import java.util.List;

import cmpe172.BloodDonation.model.Donation;
import cmpe172.BloodDonation.model.DonationSite;
import cmpe172.BloodDonation.model.Hospital;

public interface DonationSiteDAO {

	List<DonationSite> get();
	
	DonationSite get(int site_id);
	
	void save(DonationSite donationSite);
	
	void delete(int site_id);
	
	public List<Donation> getDonationsBySiteId(int site_id);
	
	public List<Hospital> getHospitalsBySiteId(int site_id);
	
	public List<Donation> getDonationByBloodType(int site_id, String blood_type);
}

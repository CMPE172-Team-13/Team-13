package cmpe172.BloodDonation.service;

import java.util.List;

import cmpe172.BloodDonation.model.Donation;
import cmpe172.BloodDonation.model.DonationSite;
import cmpe172.BloodDonation.model.Hospital;

public interface DonationSiteService {

	List<DonationSite> get();
	
	DonationSite get(int site_id);
	
	public List<Hospital> getHospitalsBySiteId(int site_id);
	
	public List<Donation> getDonationByBloodType(int site_id, String blood_type);
	
	public List<Object> getSiteWithMostDonation();
}

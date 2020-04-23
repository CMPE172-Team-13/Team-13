package cmpe172.BloodDonation.service;

import java.util.List;

import cmpe172.BloodDonation.model.Donation;
import cmpe172.BloodDonation.model.DonationSite;
import cmpe172.BloodDonation.model.Hospital;

public interface DonationService {
	
	List<Donation> get();
	
	Donation get(int donation_id);
		
	void delete(int donation_id);	
	
	void save(Donation donation, int site_id, int hospital_id);
}

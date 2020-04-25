package cmpe172.BloodDonation.service;

import java.util.List;

import cmpe172.BloodDonation.model.Donation;
import cmpe172.BloodDonation.model.DonationToSite;

public interface DonationService {
	
	List<Donation> get();
	
	Donation get(int donation_id);
	
	Donation getLast();
	
	void save(Donation donation, DonationToSite donationInfo);
	
	void delete(int donation_id);
}

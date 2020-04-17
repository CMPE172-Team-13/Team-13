package cmpe172.BloodDonation.dao;

import java.util.List;

import cmpe172.BloodDonation.model.Donation;

public interface DonationDAO {

	List<Donation> get();
	
	Donation get(int donation_id);
	
	void save(Donation donation);
	
	void delete(int donation_id);
	}

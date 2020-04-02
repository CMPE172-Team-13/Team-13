package cmpe172.BloodDonation.dao;

import java.util.List;

import cmpe172.BloodDonation.model.DonationSite;

public interface DonationSiteDAO {

	List<DonationSite> get();
	
	DonationSite get(int site_id);
	
	void save(DonationSite donationSite);
	
	void delete(int site_id);
	
}

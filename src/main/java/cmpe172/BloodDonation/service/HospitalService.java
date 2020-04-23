package cmpe172.BloodDonation.service;

import java.util.List;

import cmpe172.BloodDonation.model.Donation;
import cmpe172.BloodDonation.model.DonationSite;
import cmpe172.BloodDonation.model.Hospital;

public interface HospitalService {

	List<Hospital> get();
	
	Hospital get(int hospital_id);
	
	void save(Hospital hospital);
	
	void delete(int hospital_id);
	
	public DonationSite getDonationSiteByHospitalId(int hospital_id);
			
	public List<Donation> getDonationByBloodType(int hospital_id, String blood_type);
	
	public List<Donation> getDonationByHospitalId(int hospital_id);
}

package cmpe172.BloodDonation.dao;

import java.util.List;

import cmpe172.BloodDonation.model.Donation;
import cmpe172.BloodDonation.model.Hospital;

public interface HospitalDAO {

	List<Hospital> get();
	
	Hospital get(int hospital_id);
			
	public List<Donation> getDonationByBloodType(int hospital_id, String blood_type);
	
	public List<Object> getHospitalWithMostBloodType(String blood_type);
	
}

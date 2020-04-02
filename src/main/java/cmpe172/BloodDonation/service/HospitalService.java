package cmpe172.BloodDonation.service;

import java.util.List;

import cmpe172.BloodDonation.model.Hospital;

public interface HospitalService {

	List<Hospital> get();
	
	Hospital get(int hospital_id);
	
	void save(Hospital hospital);
	
	void delete(int hospital_id);
}

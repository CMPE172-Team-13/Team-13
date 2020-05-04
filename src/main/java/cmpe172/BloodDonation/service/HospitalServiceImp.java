package cmpe172.BloodDonation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cmpe172.BloodDonation.dao.HospitalDAO;
import cmpe172.BloodDonation.model.Donation;
import cmpe172.BloodDonation.model.Hospital;

@Service
public class HospitalServiceImp implements HospitalService{
	
	@Autowired
	private HospitalDAO hospitalDao;

	@Transactional
	@Override
	public List<Hospital> get() {
		return hospitalDao.get();
	}

	@Transactional
	@Override
	public Hospital get(int hospital_id) {
		return hospitalDao.get(hospital_id);
	}

	@Transactional
	public List<Donation> getDonationByBloodType(int hospital_id, String blood_type){
		return hospitalDao.getDonationByBloodType(hospital_id, blood_type);
	}
	
	@Transactional
	public List<Object> getHospitalWithMostBloodType(String blood_type){
		return hospitalDao.getHospitalWithMostBloodType(blood_type);
	}
}

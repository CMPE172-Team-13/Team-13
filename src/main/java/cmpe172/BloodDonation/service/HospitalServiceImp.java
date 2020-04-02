package cmpe172.BloodDonation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cmpe172.BloodDonation.dao.HospitalDAO;
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
	@Override
	public void save(Hospital hospital) {
		hospitalDao.save(hospital);
	}

	@Transactional
	@Override
	public void delete(int hospital_id) {
		hospitalDao.delete(hospital_id);
	}

}

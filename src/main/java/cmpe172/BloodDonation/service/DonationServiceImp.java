package cmpe172.BloodDonation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cmpe172.BloodDonation.dao.DonationDAO;
import cmpe172.BloodDonation.model.Donation;

@Service
public class DonationServiceImp implements DonationService{
	
	@Autowired
	private DonationDAO donationDao;

	@Transactional
	@Override
	public List<Donation> get() {		
		return donationDao.get();
	}
	
	@Transactional
	@Override
	public Donation get(int donation_id) {
		return donationDao.get(donation_id);
	}

	@Transactional
	@Override
	public void save(Donation donation) {
		donationDao.save(donation);
	}

	@Transactional
	@Override
	public void delete(int donation_id) {
		donationDao.delete(donation_id);
	}
}

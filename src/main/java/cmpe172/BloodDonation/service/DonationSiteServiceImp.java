package cmpe172.BloodDonation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cmpe172.BloodDonation.dao.DonationSiteDAO;
import cmpe172.BloodDonation.model.Donation;
import cmpe172.BloodDonation.model.DonationSite;
import cmpe172.BloodDonation.model.Hospital;

@Service
public class DonationSiteServiceImp implements DonationSiteService{

	@Autowired
	private DonationSiteDAO donationSiteDao;

	@Transactional
	@Override
	public List<DonationSite> get() {
		return donationSiteDao.get();
	}

	@Transactional
	@Override
	public DonationSite get(int site_id) {
		return donationSiteDao.get(site_id);
	}

	@Transactional
	public List<Hospital> getHospitalsBySiteId(int site_id) {
		return donationSiteDao.getHospitalsBySiteId(site_id);
	}	
	
	@Transactional
	public List<Donation> getDonationByBloodType(int site_id, String blood_type) {
		return donationSiteDao.getDonationByBloodType(site_id, blood_type);
	}
	
	@Transactional
	public List<Object> getSiteWithMostDonation(){
		return donationSiteDao.getSiteWithMostDonation();
	}
}

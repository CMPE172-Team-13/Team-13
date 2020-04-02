package cmpe172.BloodDonation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cmpe172.BloodDonation.dao.DonationSiteDAO;
import cmpe172.BloodDonation.model.DonationSite;

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
	@Override
	public void save(DonationSite donationSite) {
		donationSiteDao.save(donationSite);
	}

	@Transactional
	@Override
	public void delete(int site_id) {
		donationSiteDao.delete(site_id);
	}	
}

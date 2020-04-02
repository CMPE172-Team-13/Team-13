package cmpe172.BloodDonation.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cmpe172.BloodDonation.model.DonationSite;

@Repository
public class DonationSiteDAOImp implements DonationSiteDAO{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<DonationSite> get() {
		Session currSession = entityManager.unwrap(Session.class);
		Query<DonationSite> query = currSession.createQuery("from DonationSite", DonationSite.class);
		List<DonationSite> list = query.getResultList();
		return list;
	}

	@Override
	public DonationSite get(int site_id) {
		Session currSession = entityManager.unwrap(Session.class);
		DonationSite donoSite = currSession.get(DonationSite.class, site_id);
		return donoSite;
	}

	@Override
	public void save(DonationSite donationSite) {
		Session currSession = entityManager.unwrap(Session.class);
		currSession.saveOrUpdate(donationSite);		
	}

	@Override
	public void delete(int site_id) {
		Session currSession = entityManager.unwrap(Session.class);
		DonationSite donoSite = currSession.get(DonationSite.class, site_id);
		currSession.delete(donoSite);
	}
}

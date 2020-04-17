package cmpe172.BloodDonation.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cmpe172.BloodDonation.model.Donation;
import cmpe172.BloodDonation.model.DonationSite;
import cmpe172.BloodDonation.model.Hospital;

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

	/**
	 * This returns the donations that have came out of this site with id site_id
	 * @param site_id the is of the site
	 * @return a list of donations that have came out of the site
	 */
	public List<Donation> getDonationsBySiteId(int site_id) {
		Session currSession = entityManager.unwrap(Session.class);
		List<Donation> d = currSession.createNativeQuery(
				"SELECT * FROM donation WHERE id IN (SELECT donation_id FROM donation_site WHERE site_id = :site_id)", Donation.class)
				.setParameter("site_id", site_id)
				.getResultList();
		return d;		
	}

	/**
	 * This returns the hospitals that this site with id site_id delivers to
	 * @param site_id the id of the site
	 * @param a list of all hospitals that receive blood from the site
	 */
	public List<Hospital> getHospitalsBySiteId(int site_id) {
		Session currSession = entityManager.unwrap(Session.class);
		List<Hospital> h = currSession.createNativeQuery(
				"SELECT * FROM hospital WHERE id IN (SELECT hospital_id FROM site_hospital WHERE site_id = :site_id)", Hospital.class)
				.setParameter("site_id", site_id)
				.getResultList();
		return h;		
	}

	/**
	 * This returns the donations of blood type blood_type that came out of site with id given by site_id
	 * @param site_id the id of the site
	 * @param blood_type the blood type of the donations you are interested from hospital with id hospital_id
	 * @return a list of all donations with blood type blood_type that have came out of the site
	 */
	public List<Donation> getDonationByBloodType(int site_id, String blood_type) {
		Session currSession = entityManager.unwrap(Session.class);
		List<Donation> d = currSession.createNativeQuery(
				"SELECT * FROM donation WHERE id IN (SELECT donation_id FROM donation_site WHERE site_id = :site_id) AND blood_type = :blood_type", Donation.class)
				.setParameter("blood_type", blood_type)
				.setParameter("site_id", site_id)				
				.getResultList();
		return d;
	}
}

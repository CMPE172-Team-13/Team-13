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
public class DonationDAOImp implements DonationDAO{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Donation> get() {
		Session currSession = entityManager.unwrap(Session.class);
		Query<Donation> query = currSession.createQuery("from Donation", Donation.class);
		List<Donation> list = query.getResultList();
		return list;
	}

	@Override
	public Donation get(int donation_id) {
		Session currSession = entityManager.unwrap(Session.class);
		Donation dono = currSession.get(Donation.class, donation_id);
		return dono;
	}

	@Override
	public void delete(int donation_id) {
		Session currSession = entityManager.unwrap(Session.class);
		Donation dono = currSession.get(Donation.class, donation_id);
		currSession.delete(dono);
	}

	@Override
	public void save(Donation donation, int site_id, int hospital_id) {
		Session currSession = entityManager.unwrap(Session.class);
		currSession.save(donation);
		System.out.println(donation.getId());		
		currSession.createNativeQuery(
				"INSERT INTO donation_site "
				+ "(donation_id, site_id) "
				+ "VALUES (:donation_id, :site_id)", Donation.class)
				.setParameter("donation_id", donation.getId())
				.setParameter("site_id", site_id).executeUpdate();		
	}

}

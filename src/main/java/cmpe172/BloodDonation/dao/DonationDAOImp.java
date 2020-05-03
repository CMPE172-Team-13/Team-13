package cmpe172.BloodDonation.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cmpe172.BloodDonation.model.Donation;
import cmpe172.BloodDonation.model.DonationToSite;

@Repository
public class DonationDAOImp implements DonationDAO{
	
	@Autowired
	private EntityManager entityManager;
	
	private static final String table_name = "donation";

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
	public Donation getLast() {
		TypedQuery<Donation> typedQuery= entityManager.createQuery("SELECT d FROM Donation d "
				+ "ORDER BY d.id DESC", Donation.class);
		List<Donation> donations = typedQuery.getResultList();
		return donations.get(0);
	}
	
	@Override
	public void save(Donation donation, DonationToSite donationInfo) {
		Session currSession = entityManager.unwrap(Session.class);
		Serializable serial = currSession.save(donation);
		donationInfo.setDonationId((int)serial);
		//System.out.println(donationInfo);
		currSession.save(donationInfo);
	}

}

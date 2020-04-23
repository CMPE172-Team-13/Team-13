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
public class HospitalDAOImp implements HospitalDAO{

	@Autowired
	private EntityManager entityManager;	

	@Override
	public List<Hospital> get() {
		Session currSession = entityManager.unwrap(Session.class);
		Query<Hospital> query = currSession.createQuery("from Hospital", Hospital.class);
		List<Hospital> list = query.getResultList();
		return list;
	}

	@Override
	public Hospital get(int hospital_id) {
		Session currSession = entityManager.unwrap(Session.class);
		Hospital hospital = currSession.get(Hospital.class, hospital_id);
		return hospital;
	}

	@Override
	public void save(Hospital hospital) {
		Session currSession = entityManager.unwrap(Session.class);
		currSession.saveOrUpdate(hospital);	
	}

	@Override
	public void delete(int hospital_id) {
		Session currSession = entityManager.unwrap(Session.class);
		Hospital hospital = currSession.get(Hospital.class, hospital_id);
		currSession.delete(hospital);
	}
	
	/**
	 * This returns the donation site that delivers blood donations to the hospital
	 * @param hospital_id the is of the hospital
	 * @return the site that the hospital receives blood from
	 */
	public DonationSite getDonationSiteByHospitalId(int hospital_id) {
		Session currSession = entityManager.unwrap(Session.class);
		DonationSite d = currSession.createNativeQuery(
				"SELECT * FROM site WHERE id IN (SELECT site_id FROM site_hospital WHERE hospital_id= :hospital_id)", DonationSite.class)
				.setParameter("hospital_id", hospital_id).getSingleResult();
		return d;
	}
	
	/**
	 * This returns the donations of blood type blood_type from hospital with id given by hospital_id
	 * @param hospital_id the id of the hospital 
	 * @param blood_type the blood type of the donations you are interested from hospital with id hospital_id
	 * @return a list of donations with blood type blood_type that are present at the hospital
	 */
	public List<Donation> getDonationByBloodType(int hospital_id, String blood_type) {		
		Session currSession = entityManager.unwrap(Session.class);
		List<Donation> d = currSession.createNativeQuery(
				"SELECT * FROM donation WHERE blood_type = :blood_type AND hospital_id = :hospital_id", Donation.class)
				.setParameter("blood_type", blood_type)
				.setParameter("hospital_id", hospital_id)				
				.getResultList();
		return d;
	}

	/**
	 * This returns the donations from hospital with id given by hospital_id
	 * @param hospital_id the id of the hospital
	 * @return a list of donations that are present at the hospital
	 */
	public List<Donation> getDonationByHospitalId(int hospital_id) {
		Session currSession = entityManager.unwrap(Session.class);
		List<Donation> d = currSession.createNativeQuery(
				"SELECT * FROM donation WHERE hospital_id = :hospital_id", Donation.class)
				.setParameter("hospital_id", hospital_id)				
				.getResultList();
		return d;
	}
}

package cmpe172.BloodDonation.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cmpe172.BloodDonation.model.Donation;
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
	
	@SuppressWarnings("unchecked")
	public List<Object> getHospitalWithMostBloodType(String blood_type){
		Session currSession = entityManager.unwrap(Session.class);
		List<Object> o = currSession.createNativeQuery("SELECT hospital.name, COUNT(blood_type) as blood_type_count "
				+ "FROM donation, hospital WHERE hospital_id = hospital.id AND blood_type = :blood_type GROUP BY hospital.id "
				+ "ORDER BY blood_type_count DESC LIMIT 1")
				.setParameter("blood_type", blood_type).getResultList();
		return o;
	}
}

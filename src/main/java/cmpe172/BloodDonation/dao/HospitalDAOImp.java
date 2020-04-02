package cmpe172.BloodDonation.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}

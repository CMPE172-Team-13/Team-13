package cmpe172.BloodDonation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cmpe172.BloodDonation.service.HospitalService;
import cmpe172.BloodDonation.model.Donation;
import cmpe172.BloodDonation.model.Hospital;

@RestController
@RequestMapping("/api")
public class HospitalControler {

	@Autowired
	private HospitalService hospitalService;
	
	@GetMapping("/hospital")
	public List<Hospital> get() {
		return hospitalService.get();
	}
	
	@GetMapping("/hospital/{hospital_id}")
	public Hospital get(@PathVariable int hospital_id) {
		return hospitalService.get(hospital_id);
	}

	@GetMapping("/hospital/{hospital_id}/{blood_type}")
	public List<Donation> getDonationByBloodType(@PathVariable int hospital_id, @PathVariable String blood_type) {
		return hospitalService.getDonationByBloodType(hospital_id, blood_type);
	}
	
	@GetMapping("/topHospital/{blood_type}")
	public List<Object> getHospitalWithMostBloodType(@PathVariable String blood_type){
		return hospitalService.getHospitalWithMostBloodType(blood_type);
	}
}

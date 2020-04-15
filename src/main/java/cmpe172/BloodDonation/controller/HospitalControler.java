package cmpe172.BloodDonation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cmpe172.BloodDonation.service.HospitalService;
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
	
	@PostMapping("/hospital")
	public Hospital save(@RequestBody Hospital hospital) {
		hospitalService.save(hospital);
		return hospital;
	}
	
	@GetMapping("/hospital/{hospital_id}")
	public Hospital get(@PathVariable int hospital_id) {
		return hospitalService.get(hospital_id);
	}
	
	@DeleteMapping("/hospital/{hospital_id}")
	public String delete(@PathVariable int hospital_id) {
		hospitalService.delete(hospital_id);		
		return "Hospital removed with id " + hospital_id;		
	}
	
	@PutMapping("/hospital")
	public Hospital update(@RequestBody Hospital hospital) {
		hospitalService.save(hospital);
		return hospital;
	}
	
	
}

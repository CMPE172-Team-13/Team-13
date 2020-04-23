package cmpe172.BloodDonation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cmpe172.BloodDonation.service.DonationService;
import cmpe172.BloodDonation.service.DonationSiteService;
import cmpe172.BloodDonation.service.HospitalService;
import cmpe172.BloodDonation.model.Donation;
import cmpe172.BloodDonation.model.DonationSite;
import cmpe172.BloodDonation.model.Hospital;
@RestController
@RequestMapping("/api")
public class DonationController {
	
	@Autowired
	private DonationService donationService;
	
	@Autowired
	private DonationSiteService donationSiteService;
	
	@Autowired
	private HospitalService hospitalService;
		
	@GetMapping("/donation")
	public List<Donation> get() {
		return donationService.get();
	}

	@PostMapping("/site/{site_id}/hospital/{hospital_id}/donation")
	public Donation save(@PathVariable (value = "site_id") int site_id, 
						@PathVariable (value = "hospital_id") int hospital_id, 
						@Valid @RequestBody Donation donation) {
		
		Hospital h = hospitalService.get(hospital_id);
		donation.setHospital(h);
		DonationSite s = donationSiteService.get(site_id);
		donation.setSite(s);
		donationService.save(donation, site_id, hospital_id);
		return donation;
	}

	@GetMapping("/donation/{donation_id}")
	public Donation get(@PathVariable int donation_id) {
		return donationService.get(donation_id);
	}
	
	@DeleteMapping("/donation/{donation_id}")
	public String delete(@PathVariable int donation_id) {
		donationService.delete(donation_id);		
		return "Donation removed with id " + donation_id;		
	}	
}

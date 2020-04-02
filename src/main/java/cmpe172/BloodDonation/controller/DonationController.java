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

import cmpe172.BloodDonation.service.DonationService;
import cmpe172.BloodDonation.model.Donation;

@RestController
@RequestMapping("/api")
public class DonationController {
	
	@Autowired
	private DonationService donationService;
	
	@GetMapping("/donation")
	public List<Donation> get() {
		return donationService.get();
	}
	
	@PostMapping("/donation")
	public Donation save(@RequestBody Donation donation) {
		donationService.save(donation);
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
	
	@PutMapping("/donation")
	public Donation update(@RequestBody Donation donation) {
		donationService.save(donation);
		return donation;
	}
}

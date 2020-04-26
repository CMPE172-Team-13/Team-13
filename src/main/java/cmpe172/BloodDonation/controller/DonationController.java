package cmpe172.BloodDonation.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import cmpe172.BloodDonation.model.DonationToSite;

@RestController
@RequestMapping("/api")
public class DonationController {
	
	@Autowired
	private DonationService donationService;
	
	@GetMapping("/donation")
	public List<Donation> get() {
		return donationService.get();
	}
	
	@PostMapping("/donation/{siteId}/{hospitalId}/{bloodType}/{donationNum}/{date}")
	public Donation save(@PathVariable int siteId,
			@PathVariable int hospitalId,
			@PathVariable String bloodType,
			@PathVariable String donationNum,
			@PathVariable String date) throws ParseException {
		
		Donation donation = new Donation();
		donation.setSite(siteId);
		donation.setHospital(hospitalId);
		donation.setBlood_type(bloodType);
		donation.setDonation_number(donationNum);
		donation.setaDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		
		System.out.println("\nDEBUG MESSAGE: JSON donation in controller " + donation + "\n");
		
		DonationToSite donationSiteInfo = new DonationToSite();
		donationSiteInfo.setSiteId(siteId);
		donationSiteInfo.setDonationId(0);//donation_id

		System.out.println("\nDEBUG MESSAGE: JSON donationSiteInfo in controller " + donationSiteInfo + "\n");
		
		donationService.save(donation, donationSiteInfo);
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
	
//	@PutMapping("/donation")
//	public Donation update(@RequestBody Donation donation) {
//		donationService.save(donation);
//		return donation;
//	}
}

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

import cmpe172.BloodDonation.service.DonationSiteService;
import cmpe172.BloodDonation.model.DonationSite;

@RestController
@RequestMapping("/api")
public class DonationSiteController {

	@Autowired
	private DonationSiteService donationSiteService;
	
	@GetMapping("/site")
	public List<DonationSite> get() {
		return donationSiteService.get();
	}
	
	@PostMapping("/site")
	public DonationSite save(@RequestBody DonationSite donationSite) {
		donationSiteService.save(donationSite);
		return donationSite;
	}
	
	@GetMapping("/site/{site_id}")
	public DonationSite get(@PathVariable int site_id) {
		return donationSiteService.get(site_id);
	}
	
	@DeleteMapping("/site/{site_id}")
	public String delete(@PathVariable int site_id) {
		donationSiteService.delete(site_id);		
		return "Donation site removed with id " + site_id;		
	}
	
	@PutMapping("/site")
	public DonationSite update(@RequestBody DonationSite donationSite) {
		donationSiteService.save(donationSite);
		return donationSite;
	}
}

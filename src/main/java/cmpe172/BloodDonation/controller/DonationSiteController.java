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
import cmpe172.BloodDonation.model.Donation;
import cmpe172.BloodDonation.model.DonationSite;
import cmpe172.BloodDonation.model.Hospital;

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
	
	@GetMapping("/donationBySite/{site_id}")
	public List<Donation> getDonationsBySiteId(@PathVariable int site_id){
		return donationSiteService.getDonationsBySiteId(site_id);
	}
	
	@GetMapping("/hospitalBySite/{site_id}")
	public List<Hospital> getHospitalsBySiteId(@PathVariable int site_id){
		return donationSiteService.getHospitalsBySiteId(site_id);
	}
	
	@GetMapping("/site/{site_id}/{blood_type}")
	public List<Donation> getDonationByBloodType(@PathVariable int site_id, @PathVariable String blood_type) {
		return donationSiteService.getDonationByBloodType(site_id, blood_type);
	}
}

package cmpe172.BloodDonation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/site/{site_id}")
	public DonationSite get(@PathVariable int site_id) {
		return donationSiteService.get(site_id);
	}
	
	@GetMapping("/hospitalBySite/{site_id}")
	public List<Hospital> getHospitalsBySiteId(@PathVariable int site_id){
		return donationSiteService.getHospitalsBySiteId(site_id);
	}
	
	@GetMapping("/site/{site_id}/{blood_type}")
	public List<Donation> getDonationByBloodType(@PathVariable int site_id, @PathVariable String blood_type) {
		return donationSiteService.getDonationByBloodType(site_id, blood_type);
	}
	
	@GetMapping("/siteWithMostDonations")
	public List<Object> getSiteWithMostDonation(){
		return donationSiteService.getSiteWithMostDonation();
	}
}

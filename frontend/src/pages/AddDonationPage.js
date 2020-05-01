import React from "react";
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import { Link } from "react-router-dom";
import Grid from "@material-ui/core/Grid";
import LocalHospitalIcon from "@material-ui/icons/LocalHospital";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import CircularProgress from "@material-ui/core/CircularProgress";
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';


const useStyles = makeStyles(theme => ({
  paper: {
    marginTop: theme.spacing(7),
    display: "flex",
    flexDirection: "column",
    alignItems: "center"
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main
  },
  form: {
    width: "100%", // Fix IE 11 issue.
    marginTop: theme.spacing(3)
  },
  submit: {
    margin: theme.spacing(3, 0, 2)
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: "100%"
  }
}));

const AddDonationPage = props => {
	const classes = useStyles();
	const [firstLoad, setLoad] = React.useState(true);
	
	const [todaysDate, setTodaysDate] = React.useState("");
	const [blood_type, setBloodType] = React.useState("");
	const [site_id, setSiteId] = React.useState("");
	const [hospital_id, setHospitalId] = React.useState("");
	
	const [donationSites, setDonationSites] = React.useState([]);
	const [hospitals, setHospitals] = React.useState([]);

	const [submitted, setSubmitted] = React.useState(false);

	let isLoading = true;
	
	// Used for debugging
	const [message, setMessage] = React.useState("Nothing saved in the session");

	const handleBloodTypeChange = event => setBloodType(event.target.value);
	
	const handleSiteChange = event => {
		setSiteId(event.target.value);
		getHospitals(event.target.value);
	}
	
	const handleHospitalChange = event => {
		setHospitalId(event.target.value);
	}
	
	const handleSubmit = variables => {
		var donation_number = makeid(10);
		const toInput = { site_id, hospital_id, blood_type, donation_number, aDate: todaysDate };
		postDonation(toInput);
		setSubmitted(true);
	};
	
	async function postDonation(toInput) {
		const response = await fetch("/api/donation/" + toInput.site_id +
				"/" + toInput.hospital_id +
				"/" + toInput.blood_type +
				"/" + toInput.donation_number +
				"/" + toInput.aDate,
				{ method: "POST"});
//		const response = await fetch("/api/donation", {
//			method: "POST", // *GET, POST, PUT, DELETE, etc.
//			mode: "cors", // no-cors, *cors, same-origin
//			cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
//			credentials: "same-origin", // include, *same-origin, omit
//			headers: {
//				"Content-Type": "application/json"
//					// 'Content-Type': 'application/x-www-form-urlencoded',
//			},
//			redirect: "follow", // manual, *follow, error
//			referrerPolicy: "no-referrer", // no-referrer, *client
//			body: JSON.stringify(toInput) // body data type must match "Content-Type" header
//		});
		let body = await response.json();
		console.log(body.id);
		setMessage(body.id ? "Data sucessfully updated" : "Data failed to update");
	}
	
	async function getDonationSites() {
		let response = await fetch("/api/site");
		let body = await response.json();
		setDonationSites(body);
	}

	async function getHospitals(siteId) {
		let response = await fetch("/api/hospitalBySite/" + siteId);
		let body = await response.json();
		setHospitals(body);
		//console.log("DEBUG: got the hospitals: " + body);
	}
	
	function makeid(length) {
	   var result           = '';
	   var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
	   var charLength = characters.length;
	   for ( var i = 0; i < length; i++ ) {
	      result += characters.charAt(Math.floor(Math.random() * charLength));
	   }
	   return result;
	}
	
	if (firstLoad) {
		console.log("DEBUG: This is first load.");
		
		let today = new Date();
		let date = today.getFullYear() + "-" +
					("0" + (today.getMonth() + 1)) + "-" +
					("0" + today.getDate()).slice(-2);
		setTodaysDate(date);
		getDonationSites();
		setLoad(false);
	}
	
	if (donationSites.length > 0) isLoading = false;
	
	return (
			<Container component="main" maxWidth="xs">
			<CssBaseline />
			<div className={classes.paper}>
				<Avatar className={classes.avatar}>
					<LocalHospitalIcon />
				</Avatar>
				<Typography component="h1" variant="h5">
					Record Blood Donation
				</Typography>
				
				{isLoading ? ( <CircularProgress /> ) 
				: submitted ? (
					<div style={{textAlign:"center"}}>
						<h2>Thank you for donating!</h2>
						<p>To learn more about donating blood, go <a href="https://www.redcross.org/give-blood.html">here</a>.</p>
					</div>) 
				: (
				
					<form className={classes.form} noValidate>
						<Grid container spacing={2}>
							<Grid item xs={12}>
								<InputLabel htmlFor="hospital-label">Donation Site</InputLabel>
									<Select
										variant="outlined"
										required
										fullWidth
										id="siteId"
										value={site_id}
										label="Donation Site"
										name="name"
										autoComplete="Site ID"
										onChange={handleSiteChange}
									>
										{donationSites?.map(donationSite => (
											<MenuItem value={donationSite.id}>
												{donationSite.name}
											</MenuItem>
										))}
									</Select>
							</Grid>
							<Grid item xs={12}>
								<InputLabel htmlFor="hospital-label">Hospital</InputLabel>
									<Select
										variant="outlined"
										required
										fullWidth
										label="Hospital"
										autoComplete="Hospital"
										labelId="hospitalsMenuLabel"
										id="hospitalsMenu"
										value={hospital_id}
										onChange={handleHospitalChange}
									>
										{hospitals?.map(hospital => (
											<MenuItem value={hospital.id}>
												{hospital.name}
											</MenuItem>
										))}
									</Select>
							</Grid>
							<Grid item xs={12}>
								<InputLabel htmlFor="blood-label">Blood Type</InputLabel>
									<Select
										variant="outlined"
										required
										fullWidth
										label="bloodType"
										autoComplete="Blood Type"
										labelId="Blood Type"
										id="bloodType"
										value={blood_type}
										onChange={handleBloodTypeChange}
									>
										{['A', 'B', 'AB', 'O']?.map(bloodType => (
											<MenuItem value={bloodType}>
												{bloodType}
											</MenuItem>
										))}
									</Select>
							</Grid>
						</Grid>
						<Button
							fullWidth
							variant="contained"
							color="primary"
							preventDefault
							className={classes.submit}
							onClick={handleSubmit}
						>
							Submit
						</Button>
						<Grid container justify="center">
							<Grid item>
								<Link to="/donations">
									<Button 
									variant="contained"
									color="primary"
									>
									View Donations
									</Button>
								</Link>
							</Grid>
						</Grid>
					</form>
				)}
					
				<Typography style={{ margin: 7 }} variant="body1">
					
				</Typography>
			</div>
		</Container>
	);
}
export default AddDonationPage;
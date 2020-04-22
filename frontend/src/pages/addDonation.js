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
	
	//const [id, setId] = React.useState("101");
	const [todaysDate, setTodaysDate] = React.useState("");
	const [blood_type, setBloodType] = React.useState("");
	const [donation_number, setDonationNum] = React.useState("");
	const [site_id, setSiteId] = React.useState("");
	const [hospital_id, setHospitalId] = React.useState("");
	
	const [hospitals, setHospitals] = React.useState([]);
	let isLoading = true;
	
	// Used for debugging
	const [message, setMessage] = React.useState("Nothing saved in the session");
	
	//const handleIdChange = event => setId(event.target.value);
	//const handleDateChange = date => setTodaysDate(date);
	const handleBloodTypeChange = event => setBloodType(event.target.value);
	const handleDonationNumChange = event => setDonationNum(event.target.value);
	
	const handleSiteChange = event => {
		setSiteId(event.target.value);
	}
	
	const handleHospitalChange = event => {
		setHospitalId(event.target.value);
	}
	
	const handleSubmit = variables => {
		const toInput = { site_id, hospital_id, blood_type, donation_number, aDate: todaysDate };
		postDonation(toInput);
	};
	
	async function postDonation(toInput) {
		const response = await fetch("/api/donation", {
			method: "POST", // *GET, POST, PUT, DELETE, etc.
			mode: "cors", // no-cors, *cors, same-origin
			cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
			credentials: "same-origin", // include, *same-origin, omit
			headers: {
				"Content-Type": "application/json"
					// 'Content-Type': 'application/x-www-form-urlencoded',
			},
			redirect: "follow", // manual, *follow, error
			referrerPolicy: "no-referrer", // no-referrer, *client
			body: JSON.stringify(toInput) // body data type must match "Content-Type" header
		});
		let body = await response.json();
		console.log(body.id);
		setMessage(body.id ? "Data sucessfully updated" : "Data failed to update");
	}
	
	async function getHospitals() {
		let response = await fetch("/api/hospital");
		let body = await response.json();
		setHospitals(body);
		//console.log("DEBUG: got the hospitals: " + body);
	}

	if (firstLoad) {
		console.log("DEBUG: This is first load.");
		
		let today = new Date();
		let date = today.getFullYear() + "-" +
					("0" + (today.getMonth() + 1)) + "-" +
					("0" + today.getDate()).slice(-2);
		setTodaysDate(date);
		
		// Test getting hospitals
		getHospitals();
		
		setLoad(false);
	}
	
	if (hospitals.length > 0) isLoading = false;
	
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
				
				{isLoading ? ( <CircularProgress /> ) : (
				
					<form className={classes.form} noValidate>
						<Grid container spacing={2}>
							<Grid item xs={12}>
								<TextField
									variant="outlined"
									required
									fullWidth
									id="donationNum"
									value={donation_number}
									label="Donation Number"
									name="name"
									autoComplete="Donation Number"
									onChange={handleDonationNumChange}
									onInput={(input) => {
										input.target.value = input.target.value.slice(0, 10);
									}}
								/>
							</Grid>
							<Grid item xs={12}>
								<TextField
									variant="outlined"
									required
									fullWidth
									id="siteId"
									value={site_id}
									label="Donation Site"
									name="name"
									autoComplete="Site ID"
									onChange={handleSiteChange}
									onInput={(input) => {
										input.target.value = input.target.value.slice(0, 11);
									}}
								/>
							</Grid>
							<Grid item xs={12} sm={6}>
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
							<Grid item xs={12} sm={6}>
								<TextField
									variant="outlined"
									required
									fullWidth
									id="bloodType"
									value={blood_type}
									label="Blood Type"
									name="bloodType"
									autoComplete="Blood Type"
									onChange={handleBloodTypeChange}
									onInput={(input) => {
										input.target.value = input.target.value.slice(0, 2);
									}}
								/>
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
								<Link to="/donations">View Donations</Link>
							</Grid>
						</Grid>
					</form>
				)}
					
				<Typography style={{ margin: 7 }} variant="body1">
					Debug: {message}
				</Typography>
			</div>
		</Container>
	);
}
export default AddDonationPage;
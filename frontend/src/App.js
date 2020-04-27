import React from 'react';
import logo from './logo.svg';
import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";

import Table from "./components/Table";
import AnalyticsPage from './pages/AnalyticsPage';
import HeaderMenu from './components/HeaderMenu';
import HospitalSiteListPage from './pages/HospitalSiteListPage';
import AddDonationPage from './pages/AddDonationPage';

function App() {
  return (
    <Router>
      <div>
        <HeaderMenu/>
        <Switch>
          <Route path="/hospitals">
            <Hospitals />
          </Route>
          <Route path="/sites">
            <Sites />
          </Route>
          <Route path="/analytics">
            <AnalyticsPage />
	        </Route>
          <Route path="/donations">
            <Donations />
          </Route>
          <Route path="/list">
            <HospitalSiteListPage/>
          </Route>
          <Route path="/">
            <AddDonationPage />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

function Donations() {
  return (
  <Table 
    fetchUrl="/api/donation" 
    labels={["donation_id","blood_type", "donation_number", "aDate", "site_id", "hospital_id"]}
  />);
}

function Hospitals() {
  return (
    <Table 
      fetchUrl="/api/hospital" 
      labels={["hospital_id","name", "location", "capacity"]}
    />);
}

function Sites() {
  return (
    <Table 
      fetchUrl="/api/site" 
      labels={["site_id","name", "location"]}
    />);
}
export default App;

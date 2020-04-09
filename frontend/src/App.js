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

function App() {
  return (
    <Router>
      <div>
        <nav>
          <ul>
          <li>
              <Link to="/">Analytics</Link>
            </li>
            <li>
              <Link to="/donations">Donations</Link>
            </li>
            <li>
              <Link to="/hospitals">Hospitals</Link>
            </li>
            <li>
              <Link to="/sites">Sites</Link>
            </li>
          </ul>
        </nav>

        <Switch>
          <Route path="/hospitals">
            <Hospitals />
          </Route>
          <Route path="/sites">
            <Sites />
          </Route>
          <Route path="/donations">
            <Donations />
          </Route>
          <Route path="/">
            <AnalyticsPage />
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
    labels={["donation_id","blood_type", "donation_number", "aDate"]}
  />);
}

function Hospitals() {
  return (
    <Table 
      fetchUrl="/api/hospital" 
      labels={["hospital_id","name", "location"]}
    />);
}

function Sites() {
  return (
    <Table 
      fetchUrl="/api/site" 
      labels={["site_id","name", "location", "capacity"]}
    />);
}
export default App;

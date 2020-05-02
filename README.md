# San Jose State University
## Enterprise Software-CMPE172/Spring2020
### Team 13
### Angel Nguyen, Francisco Romero, Julio Mercado Soto

## Project Introduction
This application is a three tier web application that simulates the blood donation process. Users are able to virtually “donate” blood at particular donation sites. The user can then track their blood donation and learn details about its journey. Users will be able to view analytics such as the capacity of the hospitals, which hospitals receive the most of a certain blood type, and which donation sites receive the most donations. The project showcases various components that were introduced in CMPE 172 and sheds some light on the process that goes into donating blood. To learn more about donating blood, go [here](https://www.redcross.org/give-blood.html).

## Sample Demo Screeshots
This is the add donation page, which is where users can input a new donation.  They first choose a donation site, which narrows down the hospital options to those that are near the donation site.  Then the blood type is chosen before submission into the system.

![alt text](./assets/AddDonation.PNG)

From the add donation page, when the "view donations" button is pressed, the list of current donations in the system will be displayed.

![alt text](./assets/DonationList.PNG)

There is also a page that displays the list of hospitals and donation sites, along with the overall analytics for the site with the greatest number of donations and the hospital with the greatest amounts of a particular blood type.

![alt text](./assets/HospitalDonationAnalytics.PNG)

From that same page, there are buttons to view data specific to each hospital and donation site.
On each specific page of either a donation site or hospital, when a user scrolls above the month region, more information on which blood type was donated will be shown.
Below is an example of data for a hospital.

![alt text](./assets/HosAnalytics.png)

Here is an example of data for a donation site.

![alt text](./assets/SiteAnalytics.PNG)

## Setup Environment

## Build Locally
The frontend is a React application and the backend is a Java Spring Boot application run using Maven. 

Starting the client: <br>
`cd` to frontend folder <br>
`npm install` <br>
`npm start` <br>
Runs the app in the development mode.
Open http://localhost:3000 to view it in the browser.

Start the server:
<br>`mvn clean install`<br>`mvn spring-boot:run` 

## Schema

![alt text](./assets/BloodDonationERD.png)

## Database Queries

There are three database access object (DAO) classes containing queries for the donation, donation site and hospital data.  Each class has similar sets of queries and below are the ones belonging to the donation site table (DonationSiteDAOImp.java).
- **To get the entire list of donation sites:**

![alt text](./assets/DatabaseQueries/GetDonationList.PNG)

- **To get a particular site:**

![alt text](./assets/DatabaseQueries/GetDonationSite.PNG)

- **To post a donation site:**

![alt text](./assets/DatabaseQueries/PostDonationSite.PNG)

- **To delete a donation site:**

![alt text](./assets/DatabaseQueries/DeleteDonationSite.PNG)

- **To get the list of nearby hospitals around a particular donation site:**

![alt text](./assets/DatabaseQueries/GetHospitalList.PNG)

- **To get the list of donations of a particular blood type at a particular donation site:**

![alt text](./assets/DatabaseQueries/GetDonationList.PNG)

- **To get the donation site with the greatest number of donations:**

![alt text](./assets/DatabaseQueries/GetMostDonations.PNG)

## Mid tier APIs

## UI Data Transport
JSON is used as the data transport format between the server and client. 

# San Jose State University
## Enterprise Software-CMPE172/Spring2020
### Team 13
### Angel Nguyen, Francisco Romero, Julio Mercado Soto

## Project Introduction
This application is a three tier web application that simulates the blood donation process. Users are able to virtually “donate” blood at particular donation sites. The user can then track their blood donation and learn details about its journey. Users will be able to view analytics such as the capacity of the hospitals, which hospitals receive the most of a certain blood type, and which donation sites receive the most donations. The project showcases various components that were introduced in CMPE 172 and sheds some light on the process that goes into donating blood. To learn more about donating blood, go [here](https://www.redcross.org/give-blood.html).

## Sample Demo Screenshots
This is the add donation page, which is where users can input a new donation.  They first choose a donation site, which narrows down the hospital options to those that are near the donation site.  Then the blood type is chosen before submission into the system.

![alt text](./assets/AddDonation.PNG)

From the add donation page, when the "view donations" button is pressed, the list of current donations in the system will be displayed.

![alt text](./assets/DonationList.PNG)

There is also a page that displays the list of hospitals and donation sites, along with the overall analytics for the site with the greatest number of donations and the hospital with the greatest amounts of a particular blood type.

![alt text](./assets/HospitalDonationAnalytics.PNG)

From that same page, there are buttons to view data specific to each hospital and donation site.
On each specific page of either a donation site or hospital, when a user scrolls above the month region, more information on which blood type was donated will be shown.
Below is an example of data for a hospital.

![alt text](./assets/AnalyticsHospital.PNG)

Here is an example of data for a donation site.

![alt text](./assets/AnalyticsDonationSite.PNG)

## Setup Environment
In order to run the project locally, you must have the following software installed: <br>
* Node.Js version 10.13 or later. If you don't have it, download [here](https://nodejs.org/en/).
* Java 8 JDK. If you don't have it, download [here](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html).
* A SQL Client. We recommend MySQL and MySQL Workbench. If you don't have it, download [here](https://dev.mysql.com/downloads/). 
* Git. If you don't have it, download [here](https://git-scm.com/downloads).

To set up the environment:
1. Clone this repository.
2. Open your sql client and copy the commands from the MySQL_init.sql file to initialize the database.The file can be found in the BloodDonationDatabase directory. 
3. Open the project in your editor of choice. In the application.properties file, enter your database credentials.This file can be found under /src/main/resources. Your file may look like this:
`spring.datasource.url=jdbc:mysql://localhost:3306/BloodDonation` <br>
`spring.datasource.username=root` <br>
`spring.datasource.password=root`
4. Run the project as a maven build. Once that is complete, you are ready to build the project locally.

Make note: After running the last step, if the server runs on a different port than localhost:8080 than change the line
`"proxy": "http://localhost:8080"` <br>
in the package.json file, under the frontend file, to your server's port number.

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

## Sequence Diagrams
For our application, we only used one POST request to save the donation and the rest were GET requests. Aside from the naming, all of our GET requests followed the same format. Below is the sequence diagram for the POST request and an example of a GET request from our application.

![alt text](./assets/SequenceDiagramPOST.png)

![alt text](./assets/SequenceDiagramGET.png)


## Schema

![alt text](./assets/BloodDonationERD.png)

## Database Queries

There are three database access object (DAO) classes containing queries for the donation, donation site and hospital data.  We used a mix of queries pertaining to the EntityManager interface and custom queries.
<br>
#### Here are the queries inside of DonationDAOImp.java:

- To get the entire list of donations:

![alt text](./assets/DatabaseQueries/DonationGetList.PNG)

- To get a particular donation:

![alt text](./assets/DatabaseQueries/DonationGetById.PNG)

- To save donation:

![alt text](./assets/DatabaseQueries/DonationSave.PNG)

#### Here are the queries inside of DonationSiteDAOImp.java:

- To get the entire list of donation sites:

![alt text](./assets/DatabaseQueries/GetDonationList.PNG)

- To get a particular site:

![alt text](./assets/DatabaseQueries/GetDonationSite.PNG)

- To get the list of nearby hospitals around a particular donation site:

![alt text](./assets/DatabaseQueries/GetHospitalsBySiteID.PNG)

- To get the list of donations of a particular blood type at a particular donation site:

![alt text](./assets/DatabaseQueries/GetDonations.PNG)

- To get the donation site with the greatest number of donations:

![alt text](./assets/DatabaseQueries/GetMostDonations.PNG)

#### Here are the queries inside of HospitalDAOImp.java:

- To get the entire list of hospitals:

![alt text](./assets/DatabaseQueries/HospitalGetList.PNG)

- To get a particular hospital:

![alt text](./assets/DatabaseQueries/HospitalGetById.PNG)

- To get the list of donations of a particular blood type at a particular hospital:

![alt text](./assets/DatabaseQueries/HospitalGetDonationByBloodType.PNG)

- To get the hospital with the greatest number of donations of a certain blood type:

![alt text](./assets/DatabaseQueries/HospitalGetWithMostBloodType.PNG)

## Mid tier APIs
The APIS are disributed across three classes: DonationController, DonationSiteController, and HospitalController. <br><br>

#### Inside of DonationController:
- Class declaration for DonationController:

![alt text](./assets/APIs/DonationController.PNG)

- Get all of the donations in the database as a list:

![alt text](./assets/APIs/GetDonationList.PNG)

- Get a particular donation from the database using the donation_id:

![alt text](./assets/APIs/GetDonationById.PNG)

- Insert a donation into the database:

![alt text](./assets/APIs/PostDonation.PNG)

#### Inside of DonationSiteController:

- Class declaration for DonationSiteController:

![alt text](./assets/APIs/DonationSiteController.PNG)

- Get all of the sites in the database as a list:

![alt text](./assets/APIs/GetDonationSiteList.PNG)

- Get a particular site from the database using the site_id:

![alt text](./assets/APIs/GetDonationSiteById.PNG)

- Get the hospitals that a particular site delivers blood to using site_id

![alt text](./assets/APIs/GetHospitalsBySiteId.PNG)

- Get donations of a certain blood type present at a particular site:

![alt text](./assets/APIs/GetDonationByBloodTypeDonationSite.PNG)

- Get the site in which most blood donations occur:

![alt text](./assets/APIs/GetSiteWithMostDonations.PNG)

#### Inside of HospitalController:

- Class declaration for HospitalController:

![alt text](./assets/APIs/HospitalController.PNG)

- Get all of the hospitals in the database as a list:

![alt text](./assets/APIs/GetHospitalList.PNG)

- Get a particular hospital from the database using the hospital_id:

![alt text](./assets/APIs/GetHospitalById.PNG)

- Get donations of a certain blood type present at a particular hospital:

![alt text](./assets/APIs/GetDonationByBloodTypeHospital.PNG)

- Get the hospital that has the most of a particular blood type:

![alt text](./assets/APIs/GetHospitalWithMostDonations.PNG)

## UI Data Transport
JSON is used as the data transport format between the server and client. 

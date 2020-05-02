import React, {useState, useEffect} from 'react';
import Pagination from "@material-ui/lab/Pagination";
import { useHistory } from "react-router-dom";
import {CircularProgress} from '@material-ui/core';

import { paginate, getNumberOfPages } from '../utils/paginate';
import './HospitalSiteListPage.css';
import Card from './../components/Card';

const HospitalSiteListPage = props => {
    let history = useHistory();

    const [hospitalData, setHospitalData] = useState(null);
    const [siteData , setSiteData] = useState(null);
    const [data, setData] = useState(null);
    const [selectedIndex, setSelectedIndex] = useState(0);
    const [page, setPage] = useState(1);
    const labels = ["name", "location"];
    const [pagedData, setPagedData] = useState(null);
    const[hospitalWithMostTypeA, setHospitalWithMostTypeA] = useState(null);
    const[hospitalWithMostTypeB, setHospitalWithMostTypeB] = useState(null);
    const[hospitalWithMostTypeAB, setHospitalWithMostTypeAB] = useState(null);
    const[hospitalWithMostTypeO, setHospitalWithMostTypeO] = useState(null);
    const[siteWithMostDonations, setSiteWithMostDonations] = useState(null);

    var pageSize = 5;

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async() => {
        try{
            const hospitalDataResponse = await fetch("/api/hospital");
            const hospitalResponsebody = await hospitalDataResponse.json();
            if(Array.isArray(hospitalResponsebody)){
                const convertedHospitalData = hospitalResponsebody.map(value => {
                    return {id:value.id, name:value.name, location:value.location}
                });
                setHospitalData(convertedHospitalData);
                setData(convertedHospitalData);
                setPagedData(paginate(convertedHospitalData,1,pageSize));
            }

            const getHospitalTypeAResponse = await fetch("/api/topHospital/A");
            const getHospitalTypeABody = await getHospitalTypeAResponse.json();
            const getHospitalTypeBResponse = await fetch("/api/topHospital/B");
            const getHospitalTypeBBody = await getHospitalTypeBResponse.json();
            const getHospitalTypeABResponse = await fetch("/api/topHospital/AB");
            const getHospitalTypeABBody = await getHospitalTypeABResponse.json();
            const getHospitalTypeOResponse = await fetch("/api/topHospital/O");
            const getHospitalTypeOBody = await getHospitalTypeOResponse.json();

            setHospitalWithMostTypeA(getHospitalTypeABody);
            setHospitalWithMostTypeB(getHospitalTypeBBody);
            setHospitalWithMostTypeAB(getHospitalTypeABBody);
            setHospitalWithMostTypeO(getHospitalTypeOBody);

            const siteDataResponse = await fetch("/api/site");
            const siteResponsebody = await siteDataResponse.json();
            if(Array.isArray(siteResponsebody)){
                const convertedSiteData = siteResponsebody.map(value => {
                    return {id:value.id, name:value.name, location:value.location}
                });
                setSiteData(convertedSiteData);   
            }

            const getSiteWithMostDonationsResponse = await fetch("/api/siteWithMostDonations");
            const getSiteWithMostDonationsBody = await getSiteWithMostDonationsResponse.json();
            setSiteWithMostDonations(getSiteWithMostDonationsBody);
        } catch {
            console.log("The fetch calls failed");
        }
    }

    const handleChange = (newValue) => {
        setSelectedIndex(newValue);
        if(newValue === 1){
            setData(siteData);
            setPagedData(paginate(siteData, 1, pageSize));
            setPage(1);
        }
        else{
            setData(hospitalData);
            setPagedData(paginate(hospitalData, 1, pageSize));
            setPage(1);
        }
    };

    const handlePageChange = (event, page) => {
        setPagedData(paginate(data, page, pageSize));
        setPage(page);
    };

    if(!pagedData || !hospitalWithMostTypeA || !hospitalWithMostTypeB 
        || !hospitalWithMostTypeAB || !hospitalWithMostTypeO 
        || !siteWithMostDonations)
    {
        return( 
        <div className="centered">
            <CircularProgress/>
        </div>
        );
    }

    return (
        <div>
            <div className="card-container">
                <h2 style={{backgroundColor:"lightblue"}}>Hospitals that received the most of a particular blood type to date:</h2>
                <div>
                    <Card 
                        topText={hospitalWithMostTypeA[0][0]} 
                        middleText="Type A"
                        bottomText={hospitalWithMostTypeA[0][1] + " Units"}
                    />
                    <Card 
                        topText={hospitalWithMostTypeB[0][0]} 
                        middleText="Type B"
                        bottomText={hospitalWithMostTypeB[0][1] + " Units"}
                    />
                    <Card 
                        topText={hospitalWithMostTypeAB[0][0]} 
                        middleText="Type AB"
                        bottomText={hospitalWithMostTypeAB[0][1] + " Units"}
                    />
                    <Card 
                        topText={hospitalWithMostTypeO[0][0]} 
                        middleText="Type O"
                        bottomText={hospitalWithMostTypeO[0][1] + " Units"}
                    />
                </div>
                <div className="site-container">
                    <h2>Donation site with the most donations to date:</h2>
                    <h3>{siteWithMostDonations[0][0]}</h3>
                    <h3>{siteWithMostDonations[0][1]} Donations</h3>
                </div>
            </div>

            <div className="table-container">
                <div className="tab-container">
                    <div className={selectedIndex === 0 ? "tab tab-active" : "tab tab-inactive"} id="first-tab" onClick={() => handleChange(0)}>
                            <p>Hospitals</p>
                    </div>
                    <div className={selectedIndex === 0 ? "tab tab-inactive" : "tab tab-active"} onClick={() => handleChange(1)}>
                            <p>Donation Sites</p>
                    </div>
                </div>
                <table style={{width:"100%", border: "1px solid black"}}>
                    <thead>
                            <tr>
                                {labels.map(column => (
                                        <th key={column}>{column}</th>
                                ))}
                                <th></th>
                            </tr>
                    </thead>
                    <tbody>
                            {pagedData.map((row, index) => (
                                <tr key={row.name}>
                                    {Object.values(row).filter((value, index) => {return index !== 0}).map(value => (
                                        <td className={index%2 === 1 ? "alternate-row" :"row"} key={value}>{value}</td>
                                    ))}
                                    <td className={index%2 === 1 ? "alternate-row" :"row"}>
                                        <button 
                                            onClick={() => {
                                                if(selectedIndex == 0){
                                                    history.push(`/analytics?type=hospital&name=${row.name}&id=${row.id}`);
                                                } else {
                                                    history.push(`/analytics?type=site&name=${row.name}&id=${row.id}`);
                                                }
                                            }}>
                                            View Analytics
                                        </button>
                                    </td>
                                </tr>
                            ))}
                    </tbody>
                </table>

                <div className="pagination-container">
                    <Pagination 
                        count={getNumberOfPages(data,pageSize)} 
                        variant="outlined" 
                        shape="rounded" 
                        onChange={handlePageChange}
                        page={page}
                    />
                </div>
            </div>
        </div>
    );
}

export default HospitalSiteListPage; 
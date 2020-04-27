import React, {useState, useEffect} from 'react';
import Pagination from "@material-ui/lab/Pagination";
import { useHistory } from "react-router-dom";
import {CircularProgress} from '@material-ui/core';

import { paginate, getNumberOfPages } from '../utils/paginate';
import './HospitalSiteListPage.css';

const HospitalSiteListPage = props => {
    let history = useHistory();

    const [hospitalData, setHospitalData] = useState(null);
    const [siteData , setSiteData] = useState(null);
    const [data, setData] = useState(null);
    const [selectedIndex, setSelectedIndex] = useState(0);
    const [page, setPage] = useState(1);
    const labels = ["name", "location"];
    const [pagedData, setPagedData] = useState(null);

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

            const siteDataResponse = await fetch("/api/site");
            const siteResponsebody = await siteDataResponse.json();
            if(Array.isArray(siteResponsebody)){
                const convertedSiteData = siteResponsebody.map(value => {
                    return {id:value.id, name:value.name, location:value.location}
                });
                setSiteData(convertedSiteData);   
            }
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

    if(!pagedData){
        return( 
        <div className="centered">
            <CircularProgress/>
        </div>
        );
    }

    return (
        <div>
            <div className="tab-container">
               <div className={selectedIndex === 0 ? "tab tab-active" : "tab tab-inactive"} id="first-tab" onClick={() => handleChange(0)}>
                    <p>Hospitals</p>
               </div>
               <div className={selectedIndex === 0 ? "tab tab-inactive" : "tab tab-active"} onClick={() => handleChange(1)}>
                    <p>Donation Sites</p>
               </div>
            </div>
            <div className="centered">
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
            </div>
            <div className="centered">
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
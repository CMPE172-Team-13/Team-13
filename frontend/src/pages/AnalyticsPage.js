import React, {useEffect, useState, useCallback} from 'react';
import {
    PieChart, Pie, Sector, Cell,
    BarChart, Bar, XAxis, YAxis, 
    CartesianGrid, Tooltip, Legend,
  } from 'recharts';
import {useLocation} from 'react-router-dom';
import {CircularProgress} from '@material-ui/core';

import './AnalyticsPage.css';
import { getMonth } from './../utils/getMonth';
  
const AnalyticsPage = props => {
    let query = new URLSearchParams(useLocation().search);

    const name = query.get("name");
    const id = query.get("id");
    const type = query.get("type");

    const [data, setData] = useState(null);
    const [barData, setBarData] = useState(null);
    const [hospitalCapacity, setHospitalCapacity] = useState(null);

    const fetchCurrentBloodTypeData = useCallback(async() => {
        var currentDate = new Date();
        try {
            if(type === "hospital"){
                const typeAResponse = await fetch(`/api/hospital/${id}/A`);
                const typeAResponseBody = await typeAResponse.json();
                
                const typeBResponse = await fetch(`/api/hospital/${id}/B`);
                const typeBResponseBody = await typeBResponse.json();
                
                const typeABResponse = await fetch(`/api/hospital/${id}/AB`);
                const typeABResponseBody = await typeABResponse.json();
                
                const typeOResponse = await fetch(`/api/hospital/${id}/O`);
                const typeOResponseBody = await typeOResponse.json();

                let data = [
                    { name: 'Type A', value: getDataByMonth(typeAResponseBody, currentDate.getMonth()+1).length},
                    { name: 'Type B', value: getDataByMonth(typeBResponseBody, currentDate.getMonth()+1).length},
                    { name: 'Type AB',value: getDataByMonth(typeABResponseBody, currentDate.getMonth()+1).length},
                    { name: 'Type O', value: getDataByMonth(typeOResponseBody, currentDate.getMonth()+1).length}
                ];
                
                let barData = [
                    {name: getMonth(currentDate.getMonth()-2), "Type A": getDataByMonth(typeAResponseBody, currentDate.getMonth()-1).length,
                    "Type B":getDataByMonth(typeBResponseBody, currentDate.getMonth()-1).length, "Type AB": getDataByMonth(typeABResponseBody, currentDate.getMonth()-1).length,
                    "Type O": getDataByMonth(typeOResponseBody, currentDate.getMonth()-1).length},
                    {name: getMonth(currentDate.getMonth()-1), "Type A": getDataByMonth(typeAResponseBody, currentDate.getMonth()).length,
                    "Type B":getDataByMonth(typeBResponseBody, currentDate.getMonth()).length, "Type AB": getDataByMonth(typeABResponseBody, currentDate.getMonth()).length,
                    "Type O": getDataByMonth(typeOResponseBody, currentDate.getMonth()).length},
                    {name: getMonth(currentDate.getMonth()), "Type A": getDataByMonth(typeAResponseBody, currentDate.getMonth()+1).length,
                    "Type B":getDataByMonth(typeBResponseBody, currentDate.getMonth()+1).length, "Type AB": getDataByMonth(typeABResponseBody, currentDate.getMonth()+1).length,
                    "Type O": getDataByMonth(typeOResponseBody, currentDate.getMonth()+1).length}
                ];

                const hospitalCapacityResponse = await fetch(`/api/hospital/${id}`);
                const hospitalCapacityResponseBody = await hospitalCapacityResponse.json();
                
                console.log(hospitalCapacityResponseBody);
                setHospitalCapacity(hospitalCapacityResponseBody.capacity);
                setData(data);
                setBarData(barData);
                
            } else {
                const typeAResponse = await fetch(`/api/site/${id}/A`);
                const typeAResponseBody = await typeAResponse.json();
                
                const typeBResponse = await fetch(`/api/site/${id}/B`);
                const typeBResponseBody = await typeBResponse.json();
                
                const typeABResponse = await fetch(`/api/site/${id}/AB`);
                const typeABResponseBody = await typeABResponse.json();
                
                const typeOResponse = await fetch(`/api/site/${id}/O`);
                const typeOResponseBody = await typeOResponse.json();

                let data = [
                    { name: 'Type A', value: getDataByMonth(typeAResponseBody, currentDate.getMonth()+1).length},
                    { name: 'Type B', value: getDataByMonth(typeBResponseBody, currentDate.getMonth()+1).length},
                    { name: 'Type AB',value: getDataByMonth(typeABResponseBody, currentDate.getMonth()+1).length},
                    { name: 'Type O', value: getDataByMonth(typeOResponseBody, currentDate.getMonth()+1).length}
                ];

                let barData = [
                    {name: getMonth(currentDate.getMonth()-2), "Type A": getDataByMonth(typeAResponseBody, currentDate.getMonth()-1).length,
                    "Type B":getDataByMonth(typeBResponseBody, currentDate.getMonth()-1).length, "Type AB": getDataByMonth(typeABResponseBody, currentDate.getMonth()-1).length,
                    "Type O": getDataByMonth(typeOResponseBody, currentDate.getMonth()-1).length},
                    {name: getMonth(currentDate.getMonth()-1), "Type A": getDataByMonth(typeAResponseBody, currentDate.getMonth()).length,
                    "Type B":getDataByMonth(typeBResponseBody, currentDate.getMonth()).length, "Type AB": getDataByMonth(typeABResponseBody, currentDate.getMonth()).length,
                    "Type O": getDataByMonth(typeOResponseBody, currentDate.getMonth()).length},
                    {name: getMonth(currentDate.getMonth()), "Type A": getDataByMonth(typeAResponseBody, currentDate.getMonth()+1).length,
                    "Type B":getDataByMonth(typeBResponseBody, currentDate.getMonth()+1).length, "Type AB": getDataByMonth(typeABResponseBody, currentDate.getMonth()+1).length,
                    "Type O": getDataByMonth(typeOResponseBody, currentDate.getMonth()+1).length}
                ];

                setData(data);
                setBarData(barData);
            }
        } catch (error) {
            console.log("The blood type fetches failed.")
        }
    },[id, type]);

    useEffect(() => {
        fetchCurrentBloodTypeData();
     },[fetchCurrentBloodTypeData]);

    const getDataByMonth = (data, month) => {
        if(month <= 0){
            var currentDate = new Date();
            return data.filter((item) => {
                return (item.aDate.substring(5,7) == (month+12)) && 
                (item.aDate.substring(0,4) == (currentDate.getFullYear()-1));
            });
        }
        return data.filter((item) => {
            return item.aDate.substring(5,7) == month;
        });
    }

    const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042'];

    const RADIAN = Math.PI / 180;
    const renderCustomizedLabel = ({
    cx, cy, midAngle, innerRadius, outerRadius, percent, index,
    }) => {
        const radius = innerRadius + (outerRadius - innerRadius) * 0.5;
        const x = cx + radius * Math.cos(-midAngle * RADIAN);
        const y = cy + radius * Math.sin(-midAngle * RADIAN);

        return (
            <text x={x} y={y} fill="white" textAnchor={x > cx ? 'start' : 'end'} dominantBaseline="central">
            {`${data[index].name}: ${(percent * 100).toFixed(0)}%`}
            </text>
        );
    };

    if(!data || !barData){
        return( 
        <div className="centered">
            <CircularProgress/>
        </div>
        );
    }

    return (
    <div style={{position:"relative"}}>
        <div className="centered">
            <h1>{name}</h1>
        </div>
        <div style={{float:"left"}}>
            <div className="centered">
                <h3>Current Month Blood Types </h3>
            </div>
            {data.every((item) => {return item.value === 0}) ? 
                <div className="centered">
                    <h2>No Current Data Available</h2>
                </div>
            :
                <div id="pieChartContainer">
                    <PieChart width={500} height={500}>
                        <Pie
                            data={data}
                            cx={200}
                            cy={200}
                            labelLine={false}
                            label={renderCustomizedLabel}
                            outerRadius={200}
                            fill="#8884d8"
                            dataKey="value"
                        >
                        {
                            data.map((entry, index) => <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />)
                        }
                        </Pie>
                    </PieChart>
                </div>
            }
            {type === "hospital" ?
                <div id="capacityContainer" className="centered">
                    <h3>Total Capacity: {hospitalCapacity}</h3>
                </div> :""
            }
        </div>
        <div style={{float:"left"}}>
            <div className="centered">
                <h3>Units of Blood Type A, Type B, Type AB, and Type O </h3>
            </div>
            <div>
                <BarChart width={730} height={500} data={barData}>
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="name" />
                    <YAxis 
                        label={{
                                value: 'Units', 
                                angle: -90, 
                                position: 'insideLeft' 
                        }}
                    />
                    <Tooltip />
                    <Legend />
                    <Bar dataKey="Type A" fill="#0088FE" />
                    <Bar dataKey="Type B" fill="#00C49F" />
                    <Bar dataKey="Type AB" fill="#FFBB28" />
                    <Bar dataKey="Type O" fill="#FF8042" />
                </BarChart>
            </div>
        </div>
        
    </div>
    );
}

export default AnalyticsPage;
import React,  { PureComponent } from 'react';
import {
    PieChart, Pie, Sector, Cell,
    BarChart, Bar, XAxis, YAxis, 
    CartesianGrid, Tooltip, Legend,
  } from 'recharts';

import './AnalyticsPage.css';
  
const AnalyticsPage = props => {
    const hospital_name = "Stanford Hospital";
    const data = [
    { name: 'Type A', value: 100 },
    { name: 'Type B', value: 300 },
    { name: 'Type AB', value: 300 },
    { name: 'Type O', value: 200 },
    ];

    const barData = [
        { name: '2017', "Type A": 30,"Type B": 20, "Type AB": 25, "Type O": 10},
        { name: '2018', "Type A": 20,"Type B": 40, "Type AB": 55, "Type O": 30},
        { name: '2019', "Type A": 80,"Type B": 30, "Type AB": 35, "Type O": 20}
    ]

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

        return (
        <div style={{position:"relative"}}>
            <div className="centered">
                <h1>{hospital_name}</h1>
            </div>
            <div style={{float:"left"}}>
                <div className="centered">
                    <h3>Current Blood Types in {hospital_name} </h3>
                </div>
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
            <div style={{float:"left"}}>
                <div className="centered">
                    <h3>Units of Blood Type A, Type B, Type AB, and Type O </h3>
                </div>
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
        );
}

export default AnalyticsPage;
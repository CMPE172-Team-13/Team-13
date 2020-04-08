import React,  { PureComponent } from 'react';
import {
    PieChart, Pie, Sector, Cell,
    BarChart, Bar, XAxis, YAxis, 
    CartesianGrid, Tooltip, Legend,
  } from 'recharts';
  
const AnalyticsPage = props => {
    const hospital_name = "Stanford Hospital";
    const data = [
    { name: 'Type A', value: 100 },
    { name: 'Type B', value: 300 },
    { name: 'Type AB', value: 300 },
    { name: 'Type O', value: 200 },
    ];

    const barData = [
        { name: '2017', type_A: 30, type_B: 20, type_AB: 25, type_O: 10},
        { name: '2018', type_A: 20, type_B: 40, type_AB: 55, type_O: 30},
        { name: '2019', type_A: 80, type_B: 30, type_AB: 35, type_O: 20}
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
        <div>
            <div style={{margin:"auto", width:"50%", textAlign:"center"}}>
                <h1>{hospital_name}</h1>
            </div>
            <div style={{width: "40%"}}>
                <div>
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
            <div style={{width: "60%"}}>
            <BarChart width={730} height={250} data={barData}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="name" />
                <YAxis />
                <Tooltip />
                <Legend />
                <Bar dataKey="type_A" fill="#0088FE" />
                <Bar dataKey="type_B" fill="#00C49F" />
                <Bar dataKey="type_AB" fill="#FFBB28" />
                <Bar dataKey="type_O" fill="#FF8042" />
                </BarChart>
            </div>
            
        </div>
        );
}

export default AnalyticsPage;
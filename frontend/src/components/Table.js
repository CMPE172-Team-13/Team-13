import React, { useState } from "react";
import {CircularProgress} from '@material-ui/core';
const Table = props => {
    const [data, setData] = useState([]);
    const [firstLoad, setFirstLoad] = useState(true);
    let isLoading = true;

    async function getData() {
        let response = await fetch(props.fetchUrl);
        let body = await response.json();
        setData(body);
        console.log(body);  
    }

    if(firstLoad){
        getData();
        setFirstLoad(false);
    }

    if(data.length > 0) isLoading = false;

    return(
        <div>
            {isLoading ? (
                <CircularProgress/>
            ) : (
               <table style={{width:"100%", border: "1px solid black"}}>
                   <thead>
                        <tr>
                            {props.labels.map(column => (
                                    <th style={{border: "1px solid black"}}>{column}</th>
                            ))}
                        </tr>
                   </thead>
                   <tbody>
                        {data.map(row => (
                            <tr>
                                {Object.values(row).map(value => (
                                        <td style={{border: "1px solid black", textAlign:"center"}}>{value}</td>
                                ))}
                            </tr>
                        ))}
                   </tbody>
               </table>
            )
            }
        </div>
    );
}

export default Table;
import React from 'react';

import './Card.css';

const Card = props => {
    return (
        <div className="card">
            <div className="container">
                <h2>{props.topText}</h2>
                <h3>{props.middleText}</h3>
                <h3>{props.bottomText}</h3>
            </div>
        </div>
    );
}

export default Card;
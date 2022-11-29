import React from 'react';
import Button from '@mui/material/Button';
import './App.css';


// MATCH OPTIONS PAGE

function MatchOptions (){
    return (

        <div style={{ display: 'inline',  color:"blue" }}>
             <p style ={{padding: 100}}> Match options below: </p>
            <Button style ={{margin: 100}} variant="outlined">Study Buddy</Button>
            <Button style ={{margin: 100}} variant="outlined">Friend</Button>
            <Button style ={{margin: 100}} variant="outlined">Date</Button>
        </div>
        
        ) 

}

export default MatchOptions;
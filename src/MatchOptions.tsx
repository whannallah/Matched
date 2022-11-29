import React from 'react';
import Button from '@mui/material/Button';
import './App.css';
import { Link} from 'react-router-dom';


// MATCH OPTIONS PAGE

function MatchOptions (){
    return (

        <div style={{ display: 'inline',  color:"blue" }}>
             <p style ={{padding: 100}}> Match options below: </p>
            
            <Link to='/Questionnaire'><Button style ={{margin: 100}} variant="outlined">Study Buddy</Button></Link>
            <Link to='/Questionnaire'><Button style ={{margin: 100}} variant="outlined">Friend</Button></Link>
            <Link to='/Questionnaire'><Button style ={{margin: 100}} variant="outlined">Date</Button></Link>
        </div>
        
        ) 

}

export default MatchOptions;
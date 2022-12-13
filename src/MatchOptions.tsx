import React from 'react';
import Button from '@mui/material/Button';
import './App.css';
import { Link} from 'react-router-dom';

// MATCH OPTIONS PAGE

function MatchOptions (){
    return (

        <div>
             <p id="text"> Match options below: </p>
            
            <Link to='/QuestionnaireStudy'><Button id ="button-style" variant="contained">Study Buddy</Button></Link>
            <Link to='/QuestionnaireFriend'><Button id="button-style" variant="contained">Friend</Button></Link>
            <Link to='/QuestionnaireDate'><Button id="button-style" variant="contained">Date</Button></Link>
        </div>
        
        ) 

}

export default MatchOptions;
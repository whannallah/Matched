import React from 'react';
import Button from '@mui/material/Button';
import './App.css';
import { Link} from 'react-router-dom';
import pencil from './images/pencil.png';
import friend from './images/friend.png' 
import heart from './images/heart.png'

// MATCH OPTIONS PAGE

function MatchOptions (){
    return (

        <div>
            <div id="center-text">
                <p>Match options below: </p>
            </div>
            
            <div id="button-row">

                <Link id="circ-item" to='/QuestionnaireStudy'>
                <div >
                    <p id ="button-style" >STUDY</p>
                    <div>
                        <img id="icon-img" src={pencil} alt="pencil"/>
                    </div>
                </div>
                </Link>

                <Link id="circ-item" to='/QuestionnaireFriend'>
                <div >
                    <p id="button-style">FRIEND</p>
                    <div>
                        <img id="icon-img" src={friend} alt="friend"/>
                    </div>
                </div>
                </Link>

                <Link id="circ-item" to='/QuestionnaireDate'>
                <div >
                    <p id="button-style" >DATE</p>
                    <div>
                        <img id="icon-img" src={heart}  alt="heart"/>
                    </div>
                </div>
                </Link>

            </div>

        </div>
        
        ) 

}

export default MatchOptions;
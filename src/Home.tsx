import React from 'react';
import './App.css';

// HOME PAGE 

function Home(){
    return (
        <div style ={{padding: 100}}>
        <p id="heading"> Welcome to Matched. </p>
        <p id="body-text">  Our goal is to increase connection among students at Brown. 
        To form a new connection, all you have to do is specify your match type (study buddy, date, or friend) then fill out a quick questionnaire.
         We then use a natural language processing model, Cohere, to score the similarity between your answers to the questionnaire and others' ansers. Our 
         algorithm takes into account the similarity between each question equally and returns your top 3 matches! </p>
         <p id="body-text">
         When using Matched, keep in mind that meeting new people based on similarity isn't the only way. We encourage
         you to find many ways to meet new people, including ways that that promote connections with people who have different interests than you do</p>
         </div>

        
    )
}

export default Home;
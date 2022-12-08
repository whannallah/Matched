import React from 'react';
import './App.css';

// NEW MATCH PAGE 

function Home(){
    return (
        <div style ={{padding: 100}}>
        <p id="heading"> Welcome to Matched. </p>
        <p id="body-text">  Our goal is to increase connection among students at Brown. 
        All you have to do is specify your match type (study buddy, date, or friend) then fill out a quick questionnaire.
         We then use a natural language processing model, Cohere, to score the similarity between answers and our 
         algorithm takes into account the similarity between all answers ranks your best matches for you. </p>
         </div>

        
    )
}

export default Home;
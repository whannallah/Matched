import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Home from './Home';
import Login from './Login';
import Profile from './Profile';
import MatchOptions from './MatchOptions';
import Questionnaire from './Questionnaire';


function Main(){
    console.log("hi")
return (    
    
    <Routes>
      <Route path='/' element={<Home/>} />
      <Route path='/Login' element={<Login/>} />
      <Route path='/Profile' element={<Profile/>} />
      <Route path='/MatchOptions' element={<MatchOptions/>} />
      <Route path='/Questionnaire' element={<Questionnaire/>} />
    </Routes>
);
}
export default Main;
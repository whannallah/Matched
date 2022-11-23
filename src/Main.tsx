import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Home from './Home';
import Profile from './Profile';


function Main(){
    console.log("hi")
return (    
    
    <Routes>
    <Route path='/' element={<Home/>} />
    <Route path='/Profile' element={<Profile/>} />
  </Routes>
);
}
export default Main;
import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Home from './Home';
import Profile from './Profile';

const Main = () => {
return (         
    <Routes>
    <Route path='/' element={<Home/>} />
    <Route path='/topics' element={<Profile/>} />
  </Routes>
);
}
export default Main;
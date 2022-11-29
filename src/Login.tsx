import React from 'react';
import Button from '@mui/material/Button';
import './App.css';



// LOGIN PAGE

function Login (){
    return (

        <div style={{ display: 'inline',  color:"blue" }}>
             <p style ={{padding: 100}}> Please login with your Brown credentials below: </p>
            <Button style ={{margin: 100}} variant="outlined">Login</Button>
        </div>
        
        ) 

}

export default Login;
// import React from 'react';
// import Button from '@mui/material/Button';
// import './App.css';
// import { Link} from 'react-router-dom';


import React, { useState, useEffect , createContext, useContext, Component } from "react";
import {Link } from 'react-router-dom'; //Redirect
import Main from './Main';

import { GoogleLogin, GoogleLogout} from 'react-google-login';
import {gapi} from 'gapi-script';
import { EmailOutlined } from "@mui/icons-material";



// LOGIN PAGE

export default function Login (){
    const clientId = "952054202634-sh0ga8ll4khean9j7gfvcmqkdak5ssaq.apps.googleusercontent.com"
    const [profile, setProfile] = useState(null);
    const [emailID, setEmailID] = useState(null);
    
    //   const[header, setHeader] = useState("");
    
      useEffect(() => {
          const initClient = () => {
                  gapi.auth2.init({
                  clientId: clientId,
                  scope: ''
              });
          }
          gapi.load('client:auth2', initClient);
      })
    
      const onSuccess = (res: any) => {
        setProfile(res.profileObj)
        setEmailID(res.profileObj.email)
        
      }

    
      const onFailure = (err: any) => {
          console.log('failed', err);
      }
    
      const logOut = () => {
          setProfile(null);
          setEmailID(null)
        
      }
    
    
    
      return (
          <div >
             <h1>
              React Auth
             </h1>
             {profile ? (
                        <div>
                              <ul >
                                    <ol >
                                        <Link to='/' style = {{color: "grey",   margin: 100, textDecorationLine: 'none', fontSize: 28, fontFamily: "Georgia", position: "relative", top:70}}>HOME</Link>
                                        <Link to='/MatchOptions' style = {{color: "grey",  margin: 100,  textDecorationLine: 'none', fontSize: 28, fontFamily: "Georgia", position: "relative", top:70}} >NEW MATCHES </Link>
                                        <Link to='/Profile' style = {{color: "grey",  margin: 100,  textDecorationLine: 'none', fontSize: 28, fontFamily: "Georgia", position: "relative", top:70}}>MY MATCHES</Link> 
                                    </ol>
                                    </ul>
                            {/* <IconButton style = {{position: 'absolute', right: 90, display: 'inline'}} onClick= {()=> logOut()}> <LogoutIcon /> </IconButton> */}
                           
                            <GoogleLogout 
                                clientId={clientId} 
                                buttonText = "Log Out" 
                                onLogoutSuccess={logOut}
                                 />
                             <Main/>   
                        </div>
                        
                   ) :(
                
                    <GoogleLogin    
                        clientId = {clientId}
                        buttonText = "Sign in with Google"
                        onSuccess={onSuccess}
                        onFailure={onFailure}
                        cookiePolicy={'single_host_origin'}
                        isSignedIn={true}
                        
                        
                        />
                        
                        )   }
    
          </div>
          
          )
    
      


        // <div style={{ display: 'inline',  color:"blue" }}>
        //      <p style ={{padding: 100}}> Please login with your Brown credentials below: </p>
        //      <Link to='/'><Button style ={{margin: 100}} variant="outlined">Login</Button></Link>
        // </div>
        
        

}

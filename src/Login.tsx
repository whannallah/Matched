// import React from 'react';
// import Button from '@mui/material/Button';
// import './App.css';
// import { Link} from 'react-router-dom';


import React, { useState, useEffect , createContext, useContext, Component } from "react";
import {Link } from 'react-router-dom'; //Redirect
import Main from './Main';

import { GoogleLogin, GoogleLogout} from 'react-google-login';
import {gapi} from 'gapi-script';
import { EmailOutlined, FreeBreakfastSharp } from "@mui/icons-material";



// LOGIN PAGE

export default function Login (){
    let mainuseremail = ""
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
        console.log(res.profileObj.email)
        if (res.profileObj.email.endsWith('@brown.edu')){
            setProfile(res.profileObj)
            mainuseremail = res.profileObj.email
       }
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
             {/* <h1>
              React Auth
             </h1> */}
             {profile ? (
                        <div className="navbar-container">
                              <ul >
                                    <ol >
                                        {/* style = {{color: "grey",   margin: 100, textDecorationLine: 'none', fontSize: 28, fontFamily: "Georgia", position: "relative", top:70}} */}
                                        <Link className="nav-link" to='/' >HOME</Link>
                                        <Link className="nav-link" to='/MatchOptions'  >NEW MATCHES </Link>
                                        <Link className="nav-link" to='/Profile' >MY MATCHES</Link> 
                                        
                                        <GoogleLogout 
                                            clientId={clientId} 
                                            buttonText = "Log Out" 
                                            onLogoutSuccess={logOut}
                                            style={{ width:50, height:50, margin: 10 }}
                                        />
                                          
                                    </ol>
                                    </ul>
                            {/* <IconButton style = {{position: 'absolute', right: 90, display: 'inline'}} onClick= {()=> logOut()}> <LogoutIcon /> </IconButton> */}
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
                        style={{ width:50, height:50, margin: 10 }}
                        
                        
                        />
                        
                        )   }
    
          </div>
          
          )
    
      


        // <div style={{ display: 'inline',  color:"blue" }}>
        //      <p style ={{padding: 100}}> Please login with your Brown credentials below: </p>
        //      <Link to='/'><Button style ={{margin: 100}} variant="outlined">Login</Button></Link>
        // </div>
        
        

}

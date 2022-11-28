import { NONAME } from 'dns';
import * as React from 'react';
import { Link} from 'react-router-dom';
import Main from './Main';
import LogoutIcon from '@mui/icons-material/Logout';
import { IconButton } from '@mui/material';
import './App.css';

/**
 * This class sets up the react app for the map.
 * @returns the created app
 */
function App(){
  return (
    <div style={{ display: 'inline',  color:"blue" }}>
     
      <ul >
      <ol >
        <Link to='/' style = {{color: "grey",  margin: 300,  textDecorationLine: 'none', fontSize: 28, fontFamily: "Georgia", position: "relative", top:70}} >NEW MATCHES </Link>
        <Link to='/Profile' style = {{color: "grey",   textDecorationLine: 'none', fontSize: 28, fontFamily: "Georgia", position: "relative", top:70}}>MY MATCHES</Link>
        </ol>
      </ul>
     
      <IconButton style = {{position: 'absolute', right: 90, display: 'inline'}}> <LogoutIcon /> </IconButton>
      <Main />
    </div>   

  );
}

export default App;
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
          <Link to='/' id="nav-bar" >HOME</Link>
          <Link to='/MatchOptions' id="nav-bar" >NEW MATCHES </Link>
          <Link to='/Profile' id="nav-bar" >MY MATCHES</Link> 
        </ol>
      </ul>
     
      <Link to='/Login'><IconButton style = {{position: 'absolute', right: 90, display: 'inline'}}> <LogoutIcon /> </IconButton></Link>
      <Main />
    </div>   

  );
}

export default App;
import * as React from 'react';
import { Link} from 'react-router-dom';
import Main from './Main';

/**
 * This class sets up the react app for the map.
 * @returns the created app
 */
function App(){
  return (
    <div className = "router">
      <ul>
        <li><Link to='/'>Home</Link></li>
        <li><Link to='/topics'>Topics</Link></li>
        <li><Link to='/settings'>Settings</Link></li>
      </ul>
      <hr />
      <Main />       
    </div>   

  );
}

export default App;